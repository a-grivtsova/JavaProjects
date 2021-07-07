
public class bodywork_supplier implements Runnable{//поставщик кузовов
	
	bodies_store store;
	int time = 10;

	bodywork_supplier(bodies_store store){
       this.store=store; 
    }
    public void run(){
        for (; ; ) 
        {
            store.put(new body());
            try {
				Thread.sleep(time*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
   }	
}
