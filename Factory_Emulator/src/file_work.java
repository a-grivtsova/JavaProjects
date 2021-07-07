import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class file_work {

		String config_file_name;
		
		HashMap table = new HashMap<String, String>();
		
		file_work(String f_r)
		{
			this.config_file_name = f_r;
		}
						 
		void fill_map()
		{
			try
			{
				File file = new File(config_file_name);
				//создаем объект FileReader для объекта File
				FileReader fr = new FileReader(file);
				//создаем BufferedReader с существующего FileReader для построчного считывания
				BufferedReader reader = new BufferedReader(fr);
				// считаем сначала первую строку
				String line = reader.readLine();
				while (line != null) 
				{
					//считываем остальные строки в цикле
					String[] words1 = line.split("=");
					table.put(words1[0], words1[1]); //
					line = reader.readLine();
				}
				reader.close();
			}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		boolean check_map()
		{
			if(table.size() != 8)
				return false;		
		
			return true;	
		}	
		
		public void writing_to_file(ArrayList<String> arr, String write_file_name)
		{
			Writer writer = null;
			try
			{
				writer = new FileWriter(write_file_name);
				for (String lines : arr) 
				{
					writer.write(lines);
					writer.write(System.getProperty("line.separator"));
				}
				writer.flush();
			} 
			catch (Exception e) 
			{
				e.toString();
			}
			finally 
			{
				if (writer != null) 
				{
					try 
					{
						writer.close();
					} 
					catch (IOException ex) 
					{
						ex.toString();
					}
				}
			}
		}
		
}
