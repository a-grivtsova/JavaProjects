package Server.ServerDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TaskListQuery {

	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;
	
	public TaskListQuery(Connection conn, Statement statmt, ResultSet resSet) throws SQLException
	{
		this.conn = conn;
		this.statmt = conn.createStatement();
		this.resSet = resSet;
	}	
	
	public static void CreateTableTaskLists() throws SQLException 
	{
		String query = "CREATE TABLE if not exists 'tasklists'('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'id_desk' INTEGER, "
		+ "FOREIGN KEY('id_desk') REFERENCES desks('id'))";
		statmt.execute(query);							
		
		System.out.println("Таблица создана или уже существует.");
	}
	
	public static void InsertTable(String name, String id_desk) throws SQLException
	{
		statmt.execute("INSERT INTO 'tasklists' ('name','id_desk') VALUES ('"+name+"',"+id_desk +")");
		  
		System.out.println("Таблица заполнена");
	}
	
	public static String TaskList_id(String tasklist_name, String id_desk) throws SQLException
	{
		String taskList_id = null;
		resSet = statmt.executeQuery("SELECT * FROM 'tasklists' where name = '"+tasklist_name+"' and id_desk = '"+id_desk+"'");
		while(resSet.next())
		{
			Integer id = resSet.getInt("id");
			taskList_id = id.toString();
		}
		return taskList_id;
	}
	
	//проверка на наличие списка: да - если список есть
	public boolean CheckTaskList(String tasklist_name, String id_desk) throws SQLException 
	{
		System.out.println("SELECT * FROM 'tasklists' where name = '" +tasklist_name + "'and id_desk = "+id_desk);

		resSet = statmt.executeQuery("SELECT * FROM 'tasklists' where name = '" +tasklist_name +"' and id_desk = "+id_desk);
		return resSet.next(); //если такой список есть, то строка не null
	}
		
	
	public static String Desk_id(String desk_name, String id_user) throws SQLException
	{
		String id_desk = null;
		resSet = statmt.executeQuery("SELECT * FROM 'desks' where name = '"+desk_name+"' and id_user = '"+id_user+"'");
		while(resSet.next())
		{
			Integer id = resSet.getInt("id");
			id_desk = id.toString();
		}
		return id_desk;
	}
	
	// -------- Вывод таблицы--------
	public static ArrayList ReadDB(String id_desk) throws ClassNotFoundException, SQLException
	{
		ArrayList listtasklists = new ArrayList();

		resSet = statmt.executeQuery("SELECT * FROM 'tasklists' where id_desk = "+id_desk);	
		System.out.println("ArrayList ReadDB   " +id_desk);
		while(resSet.next())
		{
			String  name = resSet.getString("name");
		    System.out.println( "name = " + name );
		    listtasklists.add(name);
		}			
		System.out.println("Таблица выведена");
		return listtasklists;
	}
	
	// --------Закрытие--------
	public static void CloseDB() throws ClassNotFoundException, SQLException
	{
		conn.close();
		statmt.close();
		resSet.close();
			
		System.out.println("Соединения закрыты");
	}

	public void Delete_TaskList(String id_tasklist) throws SQLException {
		String id_card = null;

		resSet = statmt.executeQuery("SELECT * FROM 'cards' where id_tasklist = "+id_tasklist);
		while(resSet.next())
		{
			Integer id = resSet.getInt("id");
			id_card = id.toString();
		}

		statmt.execute("DELETE FROM 'tasklists' where id = "+ id_tasklist);
		statmt.execute("DELETE FROM 'cards' where id_tasklist = "+ id_tasklist);
		statmt.execute("DELETE FROM 'notes' where id_card = "+ id_card);
	}
}
