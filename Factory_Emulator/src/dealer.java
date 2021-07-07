
public class dealer implements Runnable{//диллер

	store_cars store;
	factory_emulator fac;
	Integer number;
	String log;
	
	dealer(factory_emulator fac){
       this.store = fac.store1;
       this.fac = fac;
    }
    public void run(){
        for (; ;) {
            
            try {
				Thread.sleep(fac.time_dealer*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            car car = store.get();
            
            if(fac.log)
            {
	            number = (int) (Thread.currentThread().getId()%fac.count_dealers+1);
	            
	            log = "Dealer <" + number.toString()+">:Auto <"+car.uniqueID_car+"> (Body: <"+car.body.uniqueID_body+">,"
	            + "Motor: <"+car.engine.uniqueID_engine+">, Accessory: <"+car.accessory.uniqueID_accessory+">)\n";
	            fac.arr.add(log);
	            fac.f.writing_to_file(fac.arr, "file.txt");
            }
        }
    }
}

