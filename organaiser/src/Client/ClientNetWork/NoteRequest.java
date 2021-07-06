package Client.ClientNetWork;

import java.io.IOException;
import java.net.UnknownHostException;

public class NoteRequest 
{
	public String NewNoteRequest(String card_name, String id_tasklist, String note) throws UnknownHostException, IOException 
	{
		return
			"<command name='newnote'>"
			+"<note>"+note+"</note>"
			+"<card_name>"+card_name+"</card_name>"
			+"<id_tasklist>"+id_tasklist+"</id_tasklist>"
			+"</command>\n";
	}
	
	public String NoteRequest(String card_name, String id_tasklist) throws UnknownHostException, IOException 
	{
		return
			"<command name='note'>"
			+"<card_name>"+card_name+"</card_name>"
			+"<id_tasklist>"+id_tasklist+"</id_tasklist>"
			+"</command>\n";
	}

	public String DeleteNoteRequest(String card_name, String id_tasklist) {
		return
				"<command name='delete_card'>"
				+"<card_name>"+card_name+"</card_name>"
				+"<id_tasklist>"+id_tasklist+"</id_tasklist>"
				+"</command>\n";
	}

}
