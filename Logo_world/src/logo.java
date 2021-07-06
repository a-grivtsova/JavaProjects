
import pac.Canvas;
import pac.FACTORY;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import pac.File_command_work;

public class logo {

	public static void main(String[] args) 
	{	
		Map<String, String> table = new HashMap<String, String>();
		
		Canvas s = new Canvas();
		FACTORY f = new FACTORY();
		
		File_command_work fs = new File_command_work();

		fs.file_work("com.txt");
		fs.line_break();
		f.run(fs, s, fs.filling_map("LOGO_commands.txt", table));
	} 
}
