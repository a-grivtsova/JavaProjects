
public class engine_supplier implements Runnable{//поставщик двигателей
	
	engine_store store;
	int time = 10;
	
	engine_supplier(engine_store store){
       this.store = store; 
    }
    public void run(){
        for (; ; ) 
        {       	
            try {
				Thread.sleep(time*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            store.put(new engine());
        }
   }	
}
