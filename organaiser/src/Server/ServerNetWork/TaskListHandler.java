package Server.ServerNetWork;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import Server.ServerDB.ConnectionDB;
import Server.ServerDB.DeskQuery;
import Server.ServerDB.SignInQuery;
import Server.ServerDB.TaskListQuery;

public class TaskListHandler {
	ServerMessage Message;
	
	TaskListHandler(ServerMessage Message)
	{
		this.Message = Message;
	}
	
	public void NewTaskList(String tasklist_name, String id_desk, String id_user) throws UnsupportedEncodingException, ClassNotFoundException, SQLException
	{
		String id_tasklist = null; //по имени найдем id
		
		if(TaskListNameCheck(tasklist_name))
		{
			//подключение к бд
			ConnectionDB connect = new ConnectionDB();
			connect.Connection();
			
			//создаем если еще нет таблицу
			TaskListQuery query = new TaskListQuery(connect.conn, connect.statmt, connect.resSet);
			query.CreateTableTaskLists(); 
			
			if(!query.CheckTaskList(tasklist_name, id_desk)) //проверили нет ли уже такого списка у этой доски
			{
				query.InsertTable(tasklist_name, id_desk); //добавили
				id_tasklist = query.TaskList_id(tasklist_name, id_desk);
				Message.Send_Server_Answer(Message.Answer_Success()); 
				Message.Send_Server_Answer(Message.Answer_New_TaskList(tasklist_name, id_tasklist, id_desk));
			}
			else
			{
				System.out.println("exist such tasklist");
				Message.Send_Server_Answer(Message.Answer_Error("Список задач с таким именем уже существует!!!"));
			}
			query.CloseDB();
			
		}
		else
		{
			Message.Send_Server_Answer(Message.Answer_Error("Неверно введено имя списка!!!"));
		}	
	}
	
	boolean TaskListNameCheck(String tasklist_name)
    {
    	if(tasklist_name.equals(""))
    		return false;	
		return true;
    }

	public void ListTaskList(String id_desk) throws ClassNotFoundException, SQLException, UnsupportedEncodingException
	{
		String xml_message =  "";  
		ArrayList listtasklists = new ArrayList();

		//подключение к бд
		ConnectionDB connect = new ConnectionDB();
		connect.Connection();
		
		TaskListQuery query = new TaskListQuery(connect.conn, connect.statmt, connect.resSet);
		listtasklists = query.ReadDB(id_desk);
		//id_desk = query.Desk_id(desk_name, id_user);
		query.CloseDB();
		
		for(int i = 0; i < listtasklists.size();i++) //получили лист списков для ответа
		{
			String tasklist = (String) listtasklists.get(i);
			xml_message = xml_message + "<tasklist><tasklist_name>" + tasklist + "</tasklist_name></tasklist>";
		}
		Message.Send_Server_Answer(Message.Answer_Success());
		System.out.println("public void ListTaskList     "+Message.Answer_ListTaskList(xml_message, id_desk));
		Message.Send_Server_Answer(Message.Answer_ListTaskList(xml_message, id_desk));
		//надо добавить проверку, вдруг списков нет
	}

	public void TaskListDelete(String id_desk, String tasklist_name) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
		String id_tasklist = null; 
		String id_card = null;
		
		//подключение к бд
		ConnectionDB connect = new ConnectionDB();
		connect.Connection();
		
		TaskListQuery query = new TaskListQuery(connect.conn, connect.statmt, connect.resSet);

		id_tasklist = query.TaskList_id(tasklist_name, id_desk);
		query.Delete_TaskList(id_tasklist);
		
		Message.Send_Server_Answer(Message.Answer_Success()); 
		System.out.println("public void NoteDelete	"+Message.Delete_TaskList(id_tasklist));
		Message.Send_Server_Answer(Message.Delete_TaskList(id_tasklist));
		
		//close
		query.CloseDB();
		
	}

}
