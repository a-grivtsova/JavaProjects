package Server.ServerNetWork;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class ServerSomething extends Thread{
	
	private Socket socket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    private BufferedReader In; // поток чтения из сокета
    BufferedWriter Out; // поток записи в сокет
    String nickname; // имя клиента
    String password; // пароль клиента
	String id_user; //id клиента в базе данных
	String desk_name;
	String tasklist_name;
	String id_desk;
	String id_card;
	String id_tasklist;
	String card_name;
    Integer id;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset ISO = Charset.forName("ISO-8859-1");
    private ParserClientRequest Parser = new ParserClientRequest();
	//для сообщений от сервера
	ServerMessage Message;

	public ServerSomething(Integer id, Socket socket) throws IOException {
		this.socket = socket; 
        this.id = id;
        In = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        Out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        start(); // вызываем run()
	}
	
    public void run() {
    	String Request;
    	String Request_Name;
        try {
            while (true) {
            	Request = Read();
            	System.out.println("Request" +Request);
            	Request_Name = Parser.Parser_Request_Name(Request);
            	System.out.println("Request_Name" +Request_Name);
            	
                switch(Request_Name) 
                {
	                case "registration":
	                	this.nickname = Parser.Parser_Client_Name(Request);
	                	this.password = Parser.Parser_Client_Password(Request);
	                	Message = new ServerMessage(this.Out, this.nickname, this.password, this.id);
	                	//работа с бд
	                	RegisterHandler hand_reg = new RegisterHandler(this, Message); 
	                	hand_reg.Register(this.nickname, this.password);
	                break; 
	                
	                case "signin":
	                	this.nickname = Parser.Parser_Client_Name(Request);
	                	this.password = Parser.Parser_Client_Password(Request);
	                	Message = new ServerMessage(this.Out, this.nickname, this.password, this.id);
	                	SignInHandler hand_sign = new SignInHandler(this, Message); 
	                	hand_sign.SignIn(this.nickname, this.password);               	
	                break; 
	                
	                case "newdesk":
	                	this.desk_name = Parser.Parser_Desk_Name(Request);
	                	this.id_user = Parser.Parser_Client_Id(Request);
	                	DeskHandler hand_desk = new DeskHandler(Message);
	                	hand_desk.NewDesk(desk_name, id_user); 
	                break;
	                
	                case "newtasklist":
	                	this.id_desk = Parser.Parser_Desk_Id(Request);  
	                	this.tasklist_name = Parser.Parser_TaskList_Name(Request);
	                	this.id_user = Parser.Parser_Client_Id(Request);
	                	TaskListHandler hand_tasklist = new TaskListHandler(Message);
	                	hand_tasklist.NewTaskList(tasklist_name, id_desk, id_user);
	                break;
	                
	                case "newcard":
	                	this.card_name = Parser.Parser_Card_Name(Request);
	                	this.id_tasklist = Parser.Parser_TaskList_Id(Request);
	                	CardHandler hand_card = new CardHandler(Message);
	                	hand_card.NewCard(card_name, id_tasklist); 
	                break;
	                
	                case "listdesk":
	                	this.id_user = Parser.Parser_Client_Id(Request); 
	                	DeskHandler hand_desk_list = new DeskHandler(Message);
	                	hand_desk_list.ListDesk(id_user);
	                break;
	                
	                case "listtasklist":
	                	this.id_desk = Parser.Parser_Desk_Id(Request); 
	                	TaskListHandler hand_tasklist_list = new TaskListHandler(Message);
	                	hand_tasklist_list.ListTaskList(id_desk);
	                break;
	                	
	                case "listcards":
	                	this.id_tasklist = Parser.Parser_TaskList_Id(Request); 
	                	CardHandler hand_card_list = new CardHandler(Message);
	                	hand_card_list.CardList(id_tasklist); 
	                break;
	                
	                case "newnote":
	                	this.id_tasklist = Parser.Parser_TaskList_Id(Request); 
	                	String note = Parser.Parser_Note(Request); 
	                	this.card_name = Parser.Parser_Card_Name(Request);
	                	NoteHandler hand_newnote = new NoteHandler(Message);
	                	hand_newnote.NewNote(note, id_tasklist, card_name); 
	                break;
	                
	                case "note":
	                	this.id_tasklist = Parser.Parser_TaskList_Id(Request); 
	                	this.card_name = Parser.Parser_Card_Name(Request);
	                	NoteHandler hand_note = new NoteHandler(Message);
	                	hand_note.Note(id_tasklist, card_name); 
	                break;
	                
	                case "delete_card":
	                	this.id_tasklist = Parser.Parser_TaskList_Id(Request); 
	                	this.card_name = Parser.Parser_Card_Name(Request);
	                	NoteHandler hand_note_delete = new NoteHandler(Message);
	                	hand_note_delete.NoteDelete(id_tasklist, card_name); 
	                break;
	                
	                case "delete_desk":
	                	this.desk_name = Parser.Parser_Desk_Name(Request);
	                	this.id_user = Parser.Parser_Client_Id(Request);
	                	DeskHandler hand_desk_delete = new DeskHandler(Message);
	                	hand_desk_delete.DeskDelete(desk_name, id_user); 
	                break;
	                
	                case "delete_tasklist":
	                	this.id_desk = Parser.Parser_Desk_Id(Request);  
	                	this.tasklist_name = Parser.Parser_TaskList_Name(Request);
	                	TaskListHandler hand_tasklist_delete = new TaskListHandler(Message);
	                	hand_tasklist_delete.TaskListDelete(id_desk, tasklist_name); 
	                break;
              }
            }
                
       } catch (IOException e) {
        } catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    String Read() throws IOException
	{
		int c;
		ArrayList buffer = new ArrayList();
		ArrayList buffer1 = new ArrayList();
		char s = 0 ;
		String d = " ";
		char[]  ds = null;
		
	    while((c=In.read())!= 10){//читаем до энтера	         
	    	buffer.add(c);
	    }
	    if(buffer.size() > 0)
	    {
	    	ds = new char[buffer.size()];
		   for(int i = 0; i < (buffer.size() ); i++)
		   {
			   int f = (int) buffer.get(i);
			   s = (char)f;
			   ds[i] = s;
			   buffer1.add(s);
		   } 
	    }
	    String myStr = new String(ds);
	    buffer.clear();
	    buffer1.clear();
		return myStr;
	}
    
}
