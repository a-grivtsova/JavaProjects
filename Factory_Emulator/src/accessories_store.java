import java.util.Vector;

public class accessories_store {//склад аксессуаров
	
	public int count_current = 0;
	private int accessories;
	String ID;
	
	Vector<accessory> vector_accessories = new Vector<accessory>(); 
	
	public accessories_store(int count_accessories)
	{
		this.accessories = count_accessories;
	}
	
	   public synchronized accessory get() {
	      while (count_current < 1) {
	         try {
	            wait();
	         }
	         catch (InterruptedException e) {
	         }
	      }
	      ID = vector_accessories.get(count_current-1).uniqueID_accessory;
	      accessory accessory = vector_accessories.get(count_current-1);
	      vector_accessories.remove(count_current-1);
	      count_current--;
	   
	      System.out.println("Рабочий взял 1 аксессуар");
	      System.out.println("Аксессуаров на складе: " + count_current);
	      notifyAll();
	      return accessory;
	   }
	   public synchronized accessory put(accessory accessory) {  
	       while (count_current >= accessories) {
	    	
	         try {
	            wait();
	         }
	         catch (InterruptedException e) { 
	         } 
	      }	  

		  vector_accessories.add(accessory);
		  count_current++;
	      System.out.println("Поставщик добавил 1 аксессуар");
	      System.out.println("Аксессуаров на складе: " + count_current);
	      notifyAll();
	      return accessory;
	   }
}
