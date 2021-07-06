package Client.ClientNetWork;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class SignInRequest 
{
	public String Request(String ServerAdress, String name, String pass) throws UnknownHostException, IOException 
	{
		return
		"<command name='signin'>"
		+"<user_name>"+name+"</user_name>"
		+"<user_password>"+pass+"</user_password>"	
		+"</command>\n";
		
	}

}
