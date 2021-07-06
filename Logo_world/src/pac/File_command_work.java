package pac;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class File_command_work 
{
	ArrayList<String> arr_command;
	public ArrayList<String> arr_cmd;
	public ArrayList<String> arr_prm;
	
	
	public void file_work(String file_name)
	{
		BufferedReader reader = null;
		arr_command = new ArrayList<String>();
		try
		{
			File file = new File(file_name);
	        //������� ������ FileReader ��� ������� File
	        FileReader fr = new FileReader(file);
	        //������� BufferedReader � ������������� FileReader ��� ����������� ����������
	        reader = new BufferedReader(fr);
	        // ������� ������� ������ ������
	        String line = reader.readLine();
	        line.trim();// ������� ������� ������� � � ����� ������
	            
		    while (line != null) 
		    {			    		            	   
		    	arr_command.add(line);
				line = reader.readLine();			
		    }   
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
	
	public void line_break()
	{
		arr_cmd = new ArrayList<String>();
		arr_prm = new ArrayList<String>();
		String s = "";

		for(int i = 0; i < arr_command.size(); i++)
		{
			String[] words = arr_command.get(i).split(" ");
			arr_cmd.add(words[0]); //������ � ������ ���������� � ������
				

				for(int k = 1; k < words.length; k++)
				{
					s = s + words[k] + " ";
				}
				arr_prm.add(s);
				s = "";
			}
	}
	
	public Map<String, String> filling_map(String file_name, Map<String, String> table) // ���������� ���-������� ������� +														
	{														// ������ ��� ������
	    try{
	        FileInputStream fstream = new FileInputStream(file_name);
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        String strLine;
	        while ((strLine = br.readLine()) != null){
	        	String[] words1 = strLine.split(" ");
            	
				table.put(words1[0], words1[2]);
	        }
	     }catch (IOException e){
	        System.out.println("������");
	     }
	    return table;
	}
	
	
	
}
