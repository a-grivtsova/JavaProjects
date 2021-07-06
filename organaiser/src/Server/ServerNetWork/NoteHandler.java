package Server.ServerNetWork;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import Server.ServerDB.CardQuery;
import Server.ServerDB.ConnectionDB;
import Server.ServerDB.NoteQuery;

public class NoteHandler {
	
ServerMessage Message;
	
	NoteHandler(ServerMessage Message)
	{
		this.Message = Message;
	}
	
	public void NewNote(String note, String id_tasklist, String  card_name/*id_card*/) throws UnsupportedEncodingException, ClassNotFoundException, SQLException
	{
		String id_note = null; //по имени найдем id
		String id_card = null;
		
		if(NoteCheck(note))
		{
			//подключение к бд
			ConnectionDB connect = new ConnectionDB();
			connect.Connection();
			
			//создаем если еще нет таблицу
			NoteQuery query = new NoteQuery(connect.conn, connect.statmt, connect.resSet);

			query.CreateTableNotes();  
			id_card = query.Card_id(card_name, id_tasklist); 
			
			if(!query.CheckNote(id_card)) //проверили нет ли уже описания
			{
				query.InsertTable(note, id_card);  //добавили
				id_note = query.Note_id(id_card);
				Message.Send_Server_Answer(Message.Answer_Success()); 
				System.out.println("public void NewNote"+Message.Answer_New_Note(id_note, id_card));
				Message.Send_Server_Answer(Message.Answer_New_Note(id_note, id_card));
			}
			else//должны удалить старое и сделать новое
			{
				System.out.println("exist such note");

				id_card = query.Card_id(card_name, id_tasklist);
				id_note = query.Note_id(id_card);
				query.Delete_Note(id_card, id_note);
				
				Message.Send_Server_Answer(Message.Answer_Success()); 
				System.out.println("public void NoteDelete	"+Message.Delete_Note(id_tasklist, card_name));
				Message.Send_Server_Answer(Message.Delete_Note(id_tasklist, card_name));
				
				
				query.InsertTable(note, id_card);  //добавили
				id_note = query.Note_id(id_card);
				Message.Send_Server_Answer(Message.Answer_Success()); 
				System.out.println("public void NewNote"+Message.Answer_New_Note(id_note, id_card));
				Message.Send_Server_Answer(Message.Answer_New_Note(id_note, id_card));
				//Message.Send_Server_Answer(Message.Answer_Error("Описание уже существует!!!"));
			}
			//close
			query.CloseDB();
			
		}
		else
		{
			Message.Send_Server_Answer(Message.Answer_Error("Ничего не введено в описание!!!"));
		}	
	}
	
	boolean NoteCheck(String note)
    {
    	if(note.equals(""))
    		return false;	
		return true;
    }

	public void Note(String id_tasklist, String card_name) throws ClassNotFoundException, SQLException, UnsupportedEncodingException 
	{
		String id_card = null; 
		String note = null;
		
		//подключение к бд
		ConnectionDB connect = new ConnectionDB();
		connect.Connection();
		
		NoteQuery query = new NoteQuery(connect.conn, connect.statmt, connect.resSet);

		id_card = query.Card_id(card_name, id_tasklist);
		note = query.Read_Note(id_card);
		
		Message.Send_Server_Answer(Message.Answer_Success()); 
		Message.Send_Server_Answer(Message.Answer_Note(note, id_tasklist, card_name));		
		
		//close
		query.CloseDB();
	}

	public void NoteDelete(String id_tasklist, String card_name) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
		String id_card = null; 
		String id_note = null;
		String note = null;
		
		//подключение к бд
		ConnectionDB connect = new ConnectionDB();
		connect.Connection();
		
		NoteQuery query = new NoteQuery(connect.conn, connect.statmt, connect.resSet);

		id_card = query.Card_id(card_name, id_tasklist);
		id_note = query.Note_id(id_card);
		query.Delete_Note(id_card, id_note);
		
		Message.Send_Server_Answer(Message.Answer_Success()); 
		System.out.println("public void NoteDelete	"+Message.Delete_Note(id_tasklist, card_name));
		Message.Send_Server_Answer(Message.Delete_Note(id_tasklist, card_name));
		
		//close
		query.CloseDB();
		
	}

}
