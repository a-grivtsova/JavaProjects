import java.util.Vector;

public class engine_store {//склад двигателей

	private int engines;
	public int count_current = 0;
	String ID;
	
	Vector<engine> vector_engine = new Vector<engine>(); 
	
	public engine_store(int count_engine)
	{
		this.engines = count_engine;
	}
	   public synchronized engine get() {
	      while (count_current < 1) {
	         try {
	            wait();
	         }
	         catch (InterruptedException e) {
	         }
	      }
	      ID = vector_engine.get(count_current-1).uniqueID_engine;
	      engine engine = vector_engine.get(count_current-1);
	      vector_engine.remove(count_current-1);
	      count_current--;
	      
	      System.out.println("Рабочий взял 1 двигатель");
	      System.out.println("Двигателей на складе: " + count_current);
	      notifyAll();
	      return engine;
	   } 
	   public synchronized engine put(engine engine) {
	       while (count_current >= engines) {
	         try {
	            wait();
	         }
	         catch (InterruptedException e) { 
	         } 
	      }
	      vector_engine.add(engine);
	      count_current++;
	      System.out.println("Поставщик добавил 1 двигатель");
	      System.out.println("Двигателей на складе: " + count_current);
	      notifyAll();
	      return engine;
	   }
}
