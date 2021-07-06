package Server.ServerNetWork;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import Server.ServerDB.CardQuery;
import Server.ServerDB.ConnectionDB;
import Server.ServerDB.DeskQuery;
import Server.ServerDB.TaskListQuery;

public class CardHandler 
{
	ServerMessage Message;
	
	CardHandler(ServerMessage Message)
	{
		this.Message = Message;
	}
	
	public void NewCard(String card_name, String id_tasklist) throws UnsupportedEncodingException, ClassNotFoundException, SQLException
	{
		String id_card = null; //по имени найдем id
		//System.out.println("card_name + id_tasklist = " + card_name + " " + id_tasklist);
		if(CardNameCheck(card_name))
		{
			//подключение к бд
			ConnectionDB connect = new ConnectionDB();
			connect.Connection();
			
			//создаем если еще нет таблицу
			CardQuery query = new CardQuery(connect.conn, connect.statmt, connect.resSet);

			query.CreateTableCards(); 
			
			if(!query.CheckCard(card_name, id_tasklist)) //проверили нет ли уже такой карточки у этого списка
			{
				query.InsertTable(card_name, id_tasklist); //добавили
				id_card = query.Card_id(card_name, id_tasklist); 
				//System.out.println("NewCard id = " + id_card);
				Message.Send_Server_Answer(Message.Answer_Success()); 
				Message.Send_Server_Answer(Message.Answer_New_Card(card_name, id_tasklist /*id_card*/));
			}
			else
			{
				System.out.println("exist such card");
				Message.Send_Server_Answer(Message.Answer_Error(" арточка с таким именем в этом списке уже существует!!!"));
			}
			//close
			query.CloseDB();
			
		}
		else
		{
			Message.Send_Server_Answer(Message.Answer_Error("Ќеверно введено им€ карточки!!!"));
		}	
	}
	
	boolean CardNameCheck(String tasklist_name)
    {
    	if(tasklist_name.equals(""))
    		return false;	
		return true;
    }


	public void CardList(String id_tasklist)  throws ClassNotFoundException, SQLException, UnsupportedEncodingException
	{
		String xml_message =  ""; 
		ArrayList listcards = new ArrayList();

		//подключение к бд
		ConnectionDB connect = new ConnectionDB();
		connect.Connection();
		
		CardQuery query = new CardQuery(connect.conn, connect.statmt, connect.resSet);
		listcards  = query.ReadDB(id_tasklist);

		query.CloseDB();
		
		for(int i = 0; i < listcards.size();i++) //получили список досок дл€ ответа
		{
			String card_name = (String) listcards.get(i);
			xml_message = xml_message + "<card><card_name>" + card_name + "</card_name></card>";
		}
		Message.Send_Server_Answer(Message.Answer_Success());
		Message.Send_Server_Answer(Message.Answer_ListCard(xml_message, id_tasklist));
		//надо добавить проверку, вдруг досок нет
	}
	

}
