package Server.ServerNetWork;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class ServerMessage 
{
	private BufferedWriter Out; // поток записи в сокет
	private String nickname; // имя клиента
	private String password; // пароль клиента
	private Integer id;
	
	ServerMessage(BufferedWriter Out, String nickname, String password, Integer id)
	{
		this.id = id;
		this.nickname = nickname;
		this.password = password;
		this.Out = Out;
	}
	
	public void Send_Server_Answer(String answer) {
        try {      	
            Out.write(answer);
            Out.flush();
        } catch (IOException ignored) {}
    }
    
	public String Answer_Success() throws UnsupportedEncodingException
	{
		String id_a = id.toString();
		
		String xml_messsage =  "<success><session>" + id_a + "</session></success>\n"; 
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer_success= new String(byteText , "UTF-8");

		return Answer_success;
	}	
    
	public String Answer_Error(String msg) throws UnsupportedEncodingException
	{
		String xml_message =  "<error><message>" + msg + "</message></error>\n"; 
		byte[] byteText = xml_message.getBytes(Charset.forName("UTF-8"));

		String Answer_Error= new String(byteText , "UTF-8");

		return Answer_Error;
	}	
    
	public String Answer_Registration(String id_user) throws UnsupportedEncodingException
    {

		String xml_messsage =  "<event name='registration'>" 
		+"<user_name>" + this.nickname + "</user_name>"
		+"<user_password>"+ this.password +"</user_password>"
		+"<id_user>"+ id_user +"</id_user>"
		+"<session>"+id.toString() + "</session></event>\n"; 	
		
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer_Registration = new String(byteText , "UTF-8");
		return Answer_Registration;
	}
	
	public String Answer_NewDesk(String desk_name) throws UnsupportedEncodingException
    {

		String xml_messsage =  "<event name='newdesk'>" 
		+"<desk_name>" + desk_name + "</desk_name>"
		+"<session>"+id.toString() + "</session></event>\n"; 	
		
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer_NewDesk = new String(byteText , "UTF-8");
		return Answer_NewDesk;
	}
	
	
	public String Answer_ListDesk(String desks_list) throws UnsupportedEncodingException
    {

		String xml_messsage = "<event name='listdesks'><listdesks>"
		+ desks_list + "</listdesks></event>\n";	
		
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer_ListDesk = new String(byteText , "UTF-8");
		return Answer_ListDesk;
	}
    
	public String Answer_SignIn(String id_user) throws UnsupportedEncodingException
    {

		String xml_messsage =  "<event name='signin'>" 
		+"<user_name>" + this.nickname + "</user_name>"
		+"<user_password>"+ this.password +"</user_password>"
		+"<id_user>"+ id_user +"</id_user>"
		+"<session>"+id.toString() + "</session></event>\n"; 	
		
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer_SignIn = new String(byteText , "UTF-8");
		return Answer_SignIn;
	}
    
	public String Answer_New_Desk(String id_user, String id_desk, String desk_name) throws UnsupportedEncodingException
    {

		String xml_messsage =  "<event name='newdesk'>" 
		+"<id_desk>" + id_desk +"</id_desk>"
		+"<desk_name>" + desk_name +"</desk_name>"
		+"<id_user>" + id_user + "</id_user></event>\n";	
		
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer_New_Desk = new String(byteText , "UTF-8");
		return Answer_New_Desk;
	}
	
	public String Answer_New_TaskList(String tasklist_name, String id_tasklist, String id_desk) throws UnsupportedEncodingException
    {
		String xml_messsage =  "<event name='newtasklist'>" 
		+"<tasklist_name>" + tasklist_name +"</tasklist_name>"
		+"<id_tasklist>" + id_tasklist +"</id_tasklist>"
		+"<id_desk>" + id_desk + "</id_desk></event>\n";	
		
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer_New_TaskList = new String(byteText , "UTF-8");
		return Answer_New_TaskList;
	}

	public String Answer_New_Card(String card_name, String id_tasklist /*id_card*/) throws UnsupportedEncodingException 
	{
		String xml_messsage =  "<event name='newcard'>" 
		+"<card_name>" + card_name +"</card_name>"
		+"<id_tasklist>" + id_tasklist + "</id_tasklist></event>\n";
		//+"<id_card>" + id_card +"</id_card></event>\n";
				
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer_New_Card = new String(byteText , "UTF-8"); 
		return Answer_New_Card;
	}

	public String Answer_ListTaskList(String list, String id_desk) throws UnsupportedEncodingException 
	{
		String xml_messsage = "<event name='listtasklists'>"+"<id_desk>" + id_desk + "</id_desk>"
		+"<listtasklists>" + list + "</listtasklists></event>\n";	
		
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer_List = new String(byteText , "UTF-8");
		return Answer_List;
	}

	public String Answer_ListCard(String list, String id_tasklist) throws UnsupportedEncodingException 
	{
		String xml_messsage = "<event name='listcards'>"+"<id_tasklist>" + id_tasklist + "</id_tasklist>"
		+"<listcards>"+ list + "</listcards></event>\n";	
		
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer_List = new String(byteText , "UTF-8");
		return Answer_List;
	}
	
	public String Answer_New_Note(String id_note, String id_card) throws UnsupportedEncodingException 
	{
		String xml_messsage =  "<event name='newnote'>" 
		+"<id_note>" + id_note +"</id_note>"
		+"<id_card>" + id_card +"</id_card></event>\n";
				
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer_New_Note = new String(byteText , "UTF-8"); 
		return Answer_New_Note;
	}

	public String Answer_Note(String note, String id_tasklist, String card_name) throws UnsupportedEncodingException 
	{
		String xml_messsage =  "<event name='note'>" 
		+"<note>" + note +"</note>"
		+"<id_tasklist>" + id_tasklist + "</id_tasklist>"
		+"<card_name>" + card_name +"</card_name></event>\n";
						
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer_Note = new String(byteText , "UTF-8"); 
		return Answer_Note;
	}

	public String Delete_Note(String id_tasklist, String card_name) throws UnsupportedEncodingException 
	{
		String xml_messsage =  "<event name='note_delete'>" 
		+"<id_tasklist>" + id_tasklist + "</id_tasklist>"
		+"<card_name>" + card_name +"</card_name></event>\n";
								
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer = new String(byteText , "UTF-8"); 
		return Answer;
	}

	public String Delete_Desk(String id_desk) throws UnsupportedEncodingException {
		String xml_messsage =  "<event name='desk_delete'>" 
		+"<id_desk>" + id_desk + "</id_desk></event>\n";	
										
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer = new String(byteText , "UTF-8"); 
		return Answer;
	}

	public String Delete_TaskList(String id_tasklist) throws UnsupportedEncodingException {
		String xml_messsage =  "<event name='tasklist_delete'>" 
		+"<id_tasklist>" + id_tasklist + "</id_tasklist></event>\n";	
												
		byte[] byteText = xml_messsage.getBytes(Charset.forName("UTF-8"));

		String Answer = new String(byteText , "UTF-8"); 
		return Answer;
	}
}
