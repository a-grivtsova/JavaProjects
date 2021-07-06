package Client.ClientNetWork;

import java.io.IOException;
import java.net.UnknownHostException;

public class TaskListRequest {
	public String NewTaskListRequest(String tasklist_name, String id_desk, String id_user) throws UnknownHostException, IOException 
	{
		return
			"<command name='newtasklist'>"
			+"<tasklist_name>"+tasklist_name+"</tasklist_name>"
			+"<id_desk>"+id_desk+"</id_desk>"
			+"<id_user>"+id_user+"</id_user>"
			+"</command>\n";
	}

	public String ListTaskListRequest(String id_desk) 
	{
		return
				"<command name='listtasklist'>"
				+"<id_desk>"+id_desk+"</id_desk>"
				+"</command>\n";
	}

	public String TaskListDeleteRequest(String tasklist_name, String id_desk) {
		// TODO Auto-generated method stub
		return
		"<command name='delete_tasklist'>"
		+"<tasklist_name>"+tasklist_name+"</tasklist_name>"
		+"<id_desk>"+id_desk+"</id_desk>"
		+"</command>\n";
	}
}
