import java.util.Vector;

public class store_cars 
{
	int cars;
	public int count_current = 0;
	String ID;
	
	Vector<car> vector_cars = new Vector<car>(); 
	
	public store_cars(int count_cars)
	{
		this.cars = count_cars;
	}
	
	   public synchronized car get() {
	      while (count_current < 1) {
	         try {
	            wait();
	         }
	         catch (InterruptedException e) {
	         }
	      }
	      ID = vector_cars.get(count_current-1).uniqueID_car;
	      car car = vector_cars.get(count_current-1);
	      vector_cars.remove(count_current-1);
	      count_current--;
	      
	      System.out.println("Покупатель купил 1 автомобиль");
	      System.out.println("Автомобилей на складе: " + count_current);
	      notifyAll();
	      return car;
	   }
	   
	   public synchronized car put(car car) {
	       while (count_current >= cars) {
	         try {
	            wait();
	         }
	         catch (InterruptedException e) { 
	         } 
	      }
	      
	      vector_cars.add(car);
	      count_current++;
	      System.out.println("Рабочий сделал 1 автомобиль");
	      System.out.println("Автомобилей на складе: " + count_current);
	      notifyAll();
	      return car;
	   }
}
