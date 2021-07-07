
public class Controller implements Runnable//����� ��������
{			
	store_cars cars;
	factory_emulator factory;
	
	public Controller(factory_emulator factory)
	{
		this.factory = factory;
		this.cars = factory.store1;
	}
	
	public void run(){
	synchronized (cars) 
	{
		while(true)
		{
			int num = (int) ((factory.count_workers + cars.cars) - (cars.count_current + factory.workerlist.getTaskCount()));
			
			for (int i = 0; i < num; i++)//������� ������� ��� ���������� �����
			{
				Runnable worker2 = new worker(factory.store1,factory.store2,factory.store3,factory.store4);
				factory.workerlist.addTask(worker2); 
			}

		    while (! (cars.count_current + factory.workerlist.getTaskCount() < (factory.count_workers + cars.cars)))
		    {
		    	try {
			    	   cars.wait();//���� �����(����������� � �������������) ������ ��� ����������� ��������			          
			        }
			    catch (InterruptedException e) {}		       
			  }		      	  
		}  			     
	}
	}
}
