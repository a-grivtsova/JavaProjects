import java.util.*;

public class mors 
{

	public static void main(String[] args) 
	{
		String answer1 = "n";
		Scanner in = new Scanner(System.in);
		Boolean d = true;
		Map<String, String> alphabet_code = new HashMap<String, String>();
		filing_map map_code = new filing_map("Morse_alphabet.txt",alphabet_code);		 
		alphabet_code = map_code.fill_map(); //заполнение таблицы алфавитом Морзе для кодирования
		
		Map<String, String> alphabet_decode = new HashMap<String, String>();
		filing_map map_decode = new filing_map("Morse_2.txt",alphabet_decode);		 
		alphabet_decode = map_decode.fill_map(); //заполнение таблицы алфавитом Морзе для декодирования
		do
		{
			System.out.println("Enter a command and file name:");
			String answer = in.nextLine();
			 
			String[] words = answer.split(" ");
			if(words.length == 2) 
			{	 
				String command = words[0];
				if(command.equals("code") || command.equals("decode"))
				{
					String file_name = words[1];		
					code_interface code = new class_decode(file_name, alphabet_code, alphabet_decode);
				 		 
					if((command.compareTo("code")) == 0)
					{
						code.decode();		 
					}
								
					if((command.compareTo("decode")) == 0)
					{
						code.code();		 
					}
		
					do
					{
						System.out.println("Would you like to enter the following command?(y/n):");		
						answer1 = in.nextLine();
						d = answer1.equals("y") || answer1.equals("n");
					}while(!d);
				}
				else
					System.out.println("You entered an incorrect command.");	
			 }
			 else
				 System.out.println("You entered an incorrect number of parameters.");	

		}while(!"n".equals(answer1));
		System.out.println("The end.");	
		in.close();
	}
}
 
 