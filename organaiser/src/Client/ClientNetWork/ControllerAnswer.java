package Client.ClientNetWork;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import Client.ClientGUI.UserProfileView;
import Client.ClientGUI.Card.CardView;
import Client.ClientGUI.Desk.DeskView;
import Client.ClientGUI.TaskList.TaskListView;


public class ControllerAnswer 
{
	private ParserServerAnswer parser = new ParserServerAnswer();
    private String server_answer;
    public Object lock_wait_server_response;
    private TaskListView TaskList;
    
    private OutputStreamWriter out;
	private InputStreamReader in; // поток чтения из сокета
	private String id_user;
	private String id_desk;
	private DeskView desk_view; 
	private JFrame deskfrm;
	private JFrame taskfrm;
	private JFrame user_profl_frm;  
	
    public ControllerAnswer(InputStreamReader in, OutputStreamWriter out) throws IOException 
    {
			this.in = in;
			this.out = out;
	    	lock_wait_server_response = new Object();
    }
	  
    public void RegistrationResponse(JFrame Frame) throws SAXException, IOException
    {
    	new ReadMessage().start(); // нить читающая сообщения из сокета в бесконечном цикле

	    server_answer = "no answer";

	    synchronized(lock_wait_server_response) 
	    {
	    	try 
	    	{
	    		lock_wait_server_response.wait(1000);
	    	}
	    	catch (InterruptedException e1) {}
	    }	 
	    if(server_answer.equals("no answer"))
	    {System.out.println("нет ответа от сервера!!!!");}
	    else if(server_answer.equals("error"))
	    {System.out.println("ошибка!!!!");}
	    else
	    { 
	    	Frame.dispose();
	    }
    }
        
    public void SignInResponse(JFrame Frame) throws SAXException, IOException
    {
    	new ReadMessage().start(); // нить читающая сообщения из сокета в бесконечном цикле

	    server_answer = "no answer";

	    synchronized(lock_wait_server_response) 
	    {
	    	try 
	    	{
	    		lock_wait_server_response.wait(1000);
	    	}
	    	catch (InterruptedException e1) {}
	    }	 
	    if(server_answer.equals("no answer"))
	    {System.out.println("нет ответа от сервера!!!!");}
	    else if(server_answer.equals("error"))
	    {System.out.println("ошибка!!!!");}
	    else
	    { 
	    	Frame.dispose();
	    }
    }
    

	private class ReadMessage extends Thread
	{
		String type_answer;
		String type_event_answer;
		String answer;
		
		@Override
		public void run() 
		{	  

	        while (true) 
	        {
	        	try {
					answer = Read();
	            	System.out.println(answer);
				} catch (IOException e) {
					e.printStackTrace();
				}

	            if(answer.length() > 2)
	            	try {
						type_answer = parser.type_answer(answer);
					} catch (ParserConfigurationException | SAXException | IOException e) {
						e.printStackTrace();
					}

	            synchronized(lock_wait_server_response) 
		       	{
	            	System.out.println(type_answer);
		            switch(type_answer) 
			        {
			            case "error":
			            	server_answer = "error";
			                lock_wait_server_response.notify();
			            break;
			            case "success":		                    
			                server_answer = "success";
			                lock_wait_server_response.notify();	
			            break; 
			            case "event":

						try {
							type_event_answer = parser.type_event_answer(answer);
						} catch (ParserConfigurationException | SAXException | IOException e) {
							e.printStackTrace();
						}

			            	if(type_event_answer.equals("registration"))
			            	{
			            		System.out.println("УРААААА!!!!1");// здесь должна стартовать страничка профиля
			        			try {
									id_user = parser.Parser_User_Id(answer);
								} catch (ParserConfigurationException | SAXException | IOException e) {
									e.printStackTrace();
								}
			            		UserProfileView profile = new UserProfileView(out, id_user);
			            		user_profl_frm = profile.initialize();
			            		user_profl_frm.setVisible(true);
			            	}	
			            	
			            	if(type_event_answer.equals("signin"))
			            	{
			            		System.out.println("УРААААА!!!!2");// здесь должна стартовать страничка профиля
			            		try {
									id_user = parser.Parser_User_Id(answer);
								} catch (ParserConfigurationException | SAXException | IOException e) {
									e.printStackTrace();
								}
			            		UserProfileView profile = new UserProfileView(out, id_user);
			            		user_profl_frm = profile.initialize();
			            		user_profl_frm.setVisible(true);
			            	}
			            	
			            	if(type_event_answer.equals("listdesks"))
			            	{
			            		ArrayList listdesks = null;
			            		System.out.println("УРААААА!!!!3");
			            		try {
			            			listdesks = parser.parser_list_desks(answer);//получили список досок
								} catch (ParserConfigurationException | SAXException | IOException e) {
									e.printStackTrace();
								}

								Client.ClientGUI.Desk.OpenDeskView opendesk = new Client.ClientGUI.Desk.OpenDeskView();
								JFrame opendeskframe = opendesk.ShowDesksList(out, listdesks, id_user); 
								if(!listdesks.equals(null))
								{	//на выбранную доску она должна открыться
									user_profl_frm.dispose();
									opendesk.comboBox.addActionListener(new ActionListener() 
									{
										public void actionPerformed(ActionEvent arg0) 
										{
											String desk_name = null;
											desk_name = (opendesk.comboBox.getSelectedItem().toString());
											opendeskframe.dispose();
											desk_view = new DeskView(out, desk_name, id_user);
											try {
												deskfrm = desk_view.initialize();
											} catch (ClassNotFoundException | SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											deskfrm.setVisible(true);
										}
									});
								}
			            	}
			            	
			            	if(type_event_answer.equals("newdesk"))
			            	{
			            		user_profl_frm.dispose();
			            		String desk_name = null;
			            		System.out.println("УРААААА!!!!4");// здесь должна стартовать вьюшка доски
			            		try {
									id_user = parser.Parser_User_Id(answer);
									desk_name = parser.Parser_Desk_Name(answer);
								} catch (ParserConfigurationException | SAXException | IOException e) {
									e.printStackTrace();
								}
								desk_view = new DeskView(out, desk_name, id_user);
								try {
									deskfrm = desk_view.initialize(); 
								} catch (ClassNotFoundException | SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								deskfrm.setVisible(true);
			            	}
			            	
			            	if(type_event_answer.equals("listtasklists")) //хотим открыть существующий список
			            	{
			            		ArrayList listtasklists = null;
			            		System.out.println("УРААААА!!!!5");	            		
			            		
								try {
			            			listtasklists = parser.parser_list_tasklists(answer);
								} catch (ParserConfigurationException | SAXException | IOException e) {
									e.printStackTrace();
								}

								Client.ClientGUI.TaskList.OpenTaskListView opentasklist = new Client.ClientGUI.TaskList.OpenTaskListView();
								JFrame opentasklistframe = opentasklist.ShowTaskList(out, listtasklists); 
								//на выбранный список он должен открыться,окно выбора закрыться
								if(!listtasklists.equals(null))
								{
									opentasklist.comboBox.addActionListener(new ActionListener() 
									{
										public void actionPerformed(ActionEvent arg0) 
										{
											String tasklist_name = null;
						            		String id_desk = null;
					            			try 
					            			{
												id_desk = parser.Parser_Desk_Id(answer);
											} catch (ParserConfigurationException | SAXException | IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											tasklist_name = (opentasklist.comboBox.getSelectedItem().toString());	
											opentasklistframe.dispose();
											TaskList = new TaskListView(out, tasklist_name, id_desk); 
						            		try {
												taskfrm = TaskList.initialize();
											} catch (ClassNotFoundException | SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}		            		
						            		taskfrm.setVisible(true);
						            		int state = taskfrm.getExtendedState();
						            		System.out.println(state);
										}
									});
								}
			            	}
			            	
			            	if(type_event_answer.equals("newtasklist"))
			            	{
			            		String tasklist_name = null;
			            		String id_desk = null;
			            		System.out.println("УРААААА!!!!6");
			            		try {
									tasklist_name = parser.Parser_TaskList_Name(answer);
									id_desk = parser.Parser_Desk_Id(answer);
								} catch (ParserConfigurationException | SAXException | IOException e) {
									e.printStackTrace();
								}//список успешно добавился в бд-> должно появиться окошечко с именем списка и кнопкой для добавления карточек
			            		TaskList = new TaskListView(out, tasklist_name, id_desk); 
			            		try {
									taskfrm = TaskList.initialize();
								} catch (ClassNotFoundException | SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}		            		
			            		taskfrm.setVisible(true); 
			            	}
			            	
			            	if(type_event_answer.equals("newcard")) //добавим карточку в выпадающий список
			            	{
			            		System.out.println("УРААААА!!!!7");
			            		//должны открыть карточку
			            		try {
									String card_name = parser.Parser_Card_Name(answer);
									String note = "no note";
									String id_tasklist = parser.Parser_TaskList_Id(answer);
									CardView card = new CardView(out, card_name, id_tasklist, note);
								} catch (ParserConfigurationException | SAXException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			            	}
			            	
			            	if(type_event_answer.equals("listcards")) //хотим открыть существующую карточку
			            	{
			            		ArrayList listcards = null;
			            		System.out.println("УРААААА!!!!8");	            		
			            		
								try {
									listcards = parser.parser_list_cards(answer);
								} catch (ParserConfigurationException | SAXException | IOException e) {
									e.printStackTrace();
								}

								Client.ClientGUI.Card.OpenCardView opencard = new Client.ClientGUI.Card.OpenCardView();
								JFrame frame = opencard.ShowCardList(out, listcards); 
								if(!listcards.equals(null))
								{
									//на выбранную карточку она должна открыться
									opencard.comboBox.addActionListener(new ActionListener() 
									{
										String card_name = null;
										String id_tasklist= null;
										public void actionPerformed(ActionEvent arg0) 
										{
											card_name = (opencard.comboBox.getSelectedItem().toString());

											//должны открыть карточку и закрыть окно выбора если список не пуст
											frame.dispose();
											//запрос на описание если оно уже есть и только после этого делаем CardView 
											NoteRequest request = new NoteRequest();
											Transmitter Trans = new Transmitter(out);
											try {
												id_tasklist= parser.Parser_TaskList_Id(answer);
												Trans.SendRequest(request.NoteRequest(card_name, id_tasklist));
											} catch (IOException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											} catch (ParserConfigurationException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											} catch (SAXException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											} 											
										}
									});
								}
			            	}
			            	
			            	if(type_event_answer.equals("note"))
			            	{
			            		System.out.println("УРААААА!!!!9");	     
			            		String card_name = null;
								String id_tasklist= null;
								String note = null;
			            		try {
									id_tasklist= parser.Parser_TaskList_Id(answer);
									card_name = parser.Parser_Card_Name(answer);
									note = parser.Parser_Card_Note(answer);
								} catch (ParserConfigurationException | SAXException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								try {
									CardView card = new CardView(out, card_name, id_tasklist, note);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			            	}
			            	
			            	if(type_event_answer.equals("note_delete"))
			            	{
			            		System.out.println("УРААААА!!!!10");	     
			            	}
			            	
			            	if(type_event_answer.equals("desk_delete")) 
			            	{
			            		System.out.println("УРААААА!!!!11");	
			            		deskfrm.dispose();
			            	}
			            	
			            	if(type_event_answer.equals("tasklist_delete")) 
			            	{
			            		System.out.println("УРААААА!!!!12");	
			            		taskfrm.dispose();
			            	}
			            break;
			        }
	            }
	        }
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
		
	    while((c=in.read())!= 10)
	    {	         
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
