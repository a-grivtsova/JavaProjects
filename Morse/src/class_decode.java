import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;


public class class_decode implements code_interface
{
	
	private String file_code;
	private Map<String, String> alphabet_code;
	private Map<String, String> alphabet_decode;
	
	public class_decode(String s, Map<String, String> al_code, Map<String, String> al_decode)
	{
		this.file_code = s;
		this.alphabet_code = al_code;
		this.alphabet_decode = al_decode;
	}
	
	public String DelSpace(String str)  // удаление лишних пробелов в строке
	{
		String j = str.trim();
		while(j.contains("   ")) 
		{
		    String replace = j.replace("   ", "  ");
		    j=replace;
		}
		return j;
	}
	
	public StringBuilder encoding_string(String line)//кодирование одной строки
	{
		StringBuilder words = new StringBuilder("");
		String[] character = new String[line.length()];// массив из символов закодированных
		System.out.println(line);
		for(int i = 0; i < line.length(); i++)
		{			    
			char[] arr_char = line.toCharArray();	     
			String charToString = Character.toString(arr_char[i]);// char to String
				            		
			String ch = alphabet_code.get(charToString.toUpperCase());//ch - один символ строки
				            		
			if(ch == null)
				character[i] = "  ";
			else
				character[i] = ch + " ";
	
			words.append(character[i]);
		}
		System.out.println(words.toString());
		return words;
	}
	
	public void writing_to_file(ArrayList<String> arr)
	{
		Writer writer = null;
		try
		{
			writer = new FileWriter("file.txt");
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
	
	public void decode() //кодирование файла 
	{	
		BufferedReader reader = null;
		try
		{
			File file = new File(file_code);
	        //создаем объект FileReader для объекта File
	        FileReader fr = new FileReader(file);
	        //создаем BufferedReader с существующего FileReader для построчного считывания
	        reader = new BufferedReader(fr);
	        // считаем сначала первую строку
	        String line = reader.readLine();
	        line.trim();// удаляем пробелы вначале и в конце строки

	        ArrayList<String> arr = new ArrayList<String>();
	            
		    while (line != null) 
		    {			    		            	   
				arr.add(encoding_string(line).toString());
				line = reader.readLine();			
		    }   
		    writing_to_file(arr);
		}
		    
		catch (FileNotFoundException e) 
		{
		    e.printStackTrace();
		} 
		    
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
		
		finally 
		{
			if (reader != null) 
			{
				try 
				{
					reader.close();
				} 
				catch (IOException ex) 
				{
					ex.toString();
				}
			}
		}
	}
	
	public StringBuilder coding_string(String line)//декодирование строки
	{
		StringBuilder str = new StringBuilder("");
		String line1 = DelSpace(line);
		System.out.println(line1);
		String[] words = line1.split(" ");
		String[] character = new String[words.length];// массив из символов декодированных
			 
		for(int i = 0; i < words.length; i++)
		{       		
			String ch = alphabet_decode.get(words[i]);//ch - одно слово строки
			            		
			if(ch== null)
			ch = " ";
		    character[i] = ch;
		    str.append(character[i]);
	    }	       
		System.out.println(str);
		return str;
	}
	
	public void code()
	{	
		BufferedReader reader = null;
		try
		 {
			File file = new File(file_code);
		    //создаем объект FileReader для объекта File
		    FileReader fr = new FileReader(file);
		    //создаем BufferedReader с существующего FileReader для построчного считывания
		    reader = new BufferedReader(fr);
		    // считаем сначала первую строку
		    String line = reader.readLine();

		    ArrayList<String> arr = new ArrayList<String>();
		            
			while (line != null) 
			{	
				arr.add(coding_string(line).toString());
				line = reader.readLine();					  
			 }  
			writing_to_file(arr);
			}
			    
			catch (FileNotFoundException e) 
			{
			    e.printStackTrace();
			} 
			    
			catch (IOException e) 
			{
				e.printStackTrace();
			}	
			
			finally 
			{
				if (reader != null) 
				{
					try 
					{
						reader.close();
					} 
					catch (IOException ex) 
					{
						ex.toString();
					}
				}
			}
	}
}

