package Client.ClientNetWork;
import java.io.IOException;
import java.net.UnknownHostException;

public class DeskRequest 
{
	public String NewDeskRequest(String desk_name, String id_user) throws UnknownHostException, IOException 
	{
		return
			"<command name='newdesk'>"
			+"<desk_name>"+desk_name+"</desk_name>"
			+"<id_user>"+id_user+"</id_user>"
			+"</command>\n";
	}
	
	public String ListDeskRequest(String id_user) throws UnknownHostException, IOException 
	{
		return
			"<command name='listdesk'>"
			+"<id_user>"+id_user+"</id_user>"
			+"</command>\n";
	}

	public String DeleteDeskRequest(String desk_name, String id_user) {
		return
				"<command name='delete_desk'>"
				+"<desk_name>"+desk_name+"</desk_name>"
				+"<id_user>"+id_user+"</id_user>"
				+"</command>\n";
	}
}
