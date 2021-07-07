import java.util.Vector;

public class bodies_store {//склад кузовов

	private int bodies;
	public int count_current = 0;
	String ID;
	
	Vector<body> vector_bodies = new Vector<body>(); 
	
	public bodies_store(int count_bodies)
	{
		this.bodies = count_bodies;
	}
	
	   public synchronized body get() {
	      while (count_current < 1) {
	         try {
	            wait();
	         }
	         catch (InterruptedException e) {
	         }
	      }
	      ID = vector_bodies.get(count_current-1).uniqueID_body;
	      body body = vector_bodies.get(count_current-1);
	      vector_bodies.remove(count_current-1);
	      count_current--;
	      
	      System.out.println("Рабочий взял 1 кузов");
	      System.out.println("----------"+count_current);
	      System.out.println("Кузовов на складе: " + count_current);
	      notifyAll();
	      return body;
	   }
	   public synchronized body put(body body) {
	       while (count_current >= bodies) {
	         try {
	            wait();
	         }
	         catch (InterruptedException e) { 
	         } 
	      }
	      vector_bodies.add(body);
	      count_current++;
	      System.out.println("Поставщик добавил 1 кузов");
	      System.out.println("Кузовов на складе: " + count_current);
	      notifyAll();
	      return body;
	   }
}
