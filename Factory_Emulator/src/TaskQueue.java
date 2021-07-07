import java.util.Vector;

public class TaskQueue {
	
	private final Vector vector;
	 
    public TaskQueue() {
        vector = new Vector();
    }
 
    public void push(Runnable r) {
        vector.addElement(r);
    }
 
    public Runnable pop() {
        if(vector.size() > 0) {
            synchronized(vector) {
                Runnable r = (Runnable)vector.firstElement();
                vector.removeElement(r);
                return r;
            }
        }
        return null;
    }
 
    public boolean isEmpty() {
        return vector.isEmpty();
    }
    
    public int size_queue()
    {
		return vector.size();   	
    }
    
}
