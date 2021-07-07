
public class worker implements Runnable {//сборка машин

	store_cars cars;
	accessories_store accessories;
	bodies_store bodies;
	engine_store engines;
	
	accessory accessory;
	body body;
	engine engine;
	
	    worker(store_cars s1, accessories_store s2, bodies_store s3,engine_store s4)
	    {
	    this.cars = s1; 
	    this.accessories = s2; 
	    this.bodies = s3;
	    this.engines = s4; 

	    }
	    public void run()
	    { 
	       //ждем части
	    	accessory = accessories.get();

	    	body = bodies.get();

	    	engine = engines.get();
		    
		    try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  	    	
		    //отправляем на склад
		    cars.put(new car(body, engine, accessory));

	    }
	}