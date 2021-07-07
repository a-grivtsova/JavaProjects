import java.util.UUID;

public class main2 {

	public static void main(String[] args) 
	{
	    
	    file_work f = new file_work("file_configuration.txt");
	    f.fill_map();
	    
	    if(! f.check_map())
	    {
	    	System.out.print("Incorrectly entered data");
	    }
	    else
	    {	    
		    factory_emulator e = new factory_emulator(f.table);
		    Controller control = new  Controller(e);
		    new Thread(control).start();
	
		    View g = new View(e);
		    g.start();	    
	    }
	}
}
 