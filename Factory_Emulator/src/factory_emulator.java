import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class factory_emulator
{	
	/*ExecutorService*/ 
	public MyThreadPoolExecutor dealerlist;
	public MyThreadPoolExecutor workerlist;
	/*ExecutorService*/ 
	public MyThreadPoolExecutor accessory_supplierlist;
	
	int count_accessory_supplier;
	int count_workers;
	int count_dealers;
	
	int count_cars;
	int count_accessories;
	int count_bodies;
	int count_engine;
	
	store_cars store1;
	accessories_store store2;
	bodies_store store3;
	engine_store store4;
	
	engine_supplier engine;
	bodywork_supplier bodywork;
	
	boolean log;
	
	int time_accessory = 100;
	int time_dealer = 100;
	
	 ArrayList<String> arr = new ArrayList<String>();
	 file_work f = new file_work("file_configuration.txt");
	 
	 public factory_emulator(HashMap<String, String> t)
	 { 
		 this.count_accessory_supplier = Integer.valueOf(t.get("AccessorySuppliers"));
		 this.count_workers = Integer.valueOf(t.get("Workers"));
		 this.count_dealers = Integer.valueOf(t.get("Dealers"));
		 
		 this.count_bodies = Integer.valueOf(t.get("StorageBodySize"));
		 this.count_cars = Integer.valueOf(t.get("StorageAutoSize"));
		 this.count_engine = Integer.valueOf(t.get("StorageMotorSize"));
		 this.count_accessories = Integer.valueOf(t.get("StorageAccessorySize"));
		 this.log = Boolean.valueOf(t.get("LogSale"));
		 
		 this.store1 = new store_cars(count_cars);
		 this.store2 = new accessories_store(count_accessories);
		 this.store3 = new bodies_store(count_bodies);
		 this.store4 = new engine_store(count_engine);
		 
		 this.engine = new engine_supplier(store4);
		 this.bodywork = new bodywork_supplier(store3);
		 
		 new Thread(engine).start();
		 new Thread(bodywork).start();
		 
		 dealerlist = new MyThreadPoolExecutor(count_dealers);
		 {
			 for (int i = 0; i < count_dealers; i++) 
				{
					Runnable worker1 = new dealer(this);
					dealerlist.addTask( worker1);
				}
		 }
		 
		 workerlist = new MyThreadPoolExecutor(count_workers);
		 {}
		 
		 
		 accessory_supplierlist = new MyThreadPoolExecutor(count_accessory_supplier);
		 {
			 for (int i = 0; i < count_accessory_supplier; i++) 
				{
					Runnable worker3 = new accessory_supplier(store2,this);
					accessory_supplierlist.addTask(worker3);
				}
		 }
		 
		 
		 
		/* dealerlist = Executors.newFixedThreadPool(count_dealers);
		 {
			for (int i = 0; i < count_dealers; i++) 
			{
				Runnable worker1 = new dealer(this);
				dealerlist.execute(worker1);
			}
		 }
		 
		 workerlist = (ThreadPoolExecutor)Executors.newFixedThreadPool(count_workers);
		 {}
		 
		 accessory_supplierlist = Executors.newFixedThreadPool(count_accessory_supplier);
		 {
			for (int i = 0; i < count_accessory_supplier; i++) 
			{
				Runnable worker3 = new accessory_supplier(store2,this);
				accessory_supplierlist.execute(worker3);
			}
		 }*/
	 }
}










