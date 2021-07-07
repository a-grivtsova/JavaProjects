
public class accessory_supplier implements Runnable{//поставщик аксессуаров

		accessories_store store;
		factory_emulator fac;
		accessory_supplier(accessories_store store,	factory_emulator fac)
		{
	       this.store=store; 
	       this.fac = fac;
	    }
		
	    public void run(){
	        for (; ; ) 
	        {	                 	
	            try {
					Thread.sleep(fac.time_accessory*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            store.put(new accessory()); 
				//System.out.println("00000   "+ "   "+ fac.time_accessory);
	        }
	   }
}

