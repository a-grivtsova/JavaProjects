
public class MyThreadPoolExecutor implements Runnable{
	
	private final TaskQueue taskQueue;
    private final Thread[] workers;
 
    public MyThreadPoolExecutor(int numThreads) {
        taskQueue = new TaskQueue();
        workers = new Thread[numThreads];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Thread(this, "Worker-thread-" + i);
            workers[i].start();
        }
    }
 
    public void addTask(Runnable r) {
        taskQueue.push(r);
        synchronized (taskQueue) {
            taskQueue.notify();
        }
    }
 
    public void shutdown() {
        for (int i = 0; i < workers.length; i++) {
            if(workers[i].isAlive()) {
                workers[i].interrupt();
            }
        }
    }
 
    public void run() {
        while(true) {
            Runnable r = taskQueue.pop();
            if (r == null) {
                synchronized(taskQueue) {
                    try {
                        while(taskQueue.isEmpty()) taskQueue.wait();
                    } catch (InterruptedException ex) {
                    }
                }
            } else {
                r.run();
            }
        }
    }
    
    public int getTaskCount()
    {
		return taskQueue.size_queue();   	
    }

}
