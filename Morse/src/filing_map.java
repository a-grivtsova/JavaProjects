import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class filing_map
{
	String file_name;
	Map<String, String> table;
	
	filing_map(String f_n, Map<String, String> a)
	{
		this.file_name = f_n;
		this.table = a;
	}
					 
	Map<String, String> fill_map()
	{
		try
		{
			File file = new File(file_name);
			//создаем объект FileReader для объекта File
			FileReader fr = new FileReader(file);
			//создаем BufferedReader с существующего FileReader для построчного считывания
			BufferedReader reader = new BufferedReader(fr);
			// считаем сначала первую строку
			String line = reader.readLine();
			while (line != null) 
			{
				//считываем остальные строки в цикле
				String[] words1 = line.split(" ");
					            	
				table.put(words1[0], words1[1]);
					            	
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
		return table;
	}
}
