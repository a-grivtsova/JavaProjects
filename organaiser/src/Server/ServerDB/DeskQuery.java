package Server.ServerDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DeskQuery 
{
	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;
	
	public DeskQuery(Connection conn, Statement statmt, ResultSet resSet) throws SQLException
	{
		this.conn = conn;
		this.statmt = conn.createStatement();
		this.resSet = resSet;
	}

	public static void CreateTableDesks() throws SQLException //PRIMARY KEY AUTOINCREMENT
	{
		statmt.execute("CREATE TABLE if not exists 'desks'('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'id_user' INTEGER, FOREIGN KEY('id_user') REFERENCES users('id'))");							
		
		System.out.println("Таблица создана или уже существует.");
	}
	
	public static void InsertTable(String desk_name, String id_user) throws SQLException
	{
		statmt.execute("INSERT INTO 'desks' ('name','id_user') VALUES ('"+desk_name+"',"+id_user+") ");
		  
		System.out.println("Таблица заполнена");
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
	
	public static String Desk_name(String id_desk) throws SQLException
	{
		String  name = null;
		resSet = statmt.executeQuery("SELECT * FROM 'desks' where id = '"+id_desk+"'");
		while(resSet.next())
		{
			name = resSet.getString("name");
		}
		return name;
	}
	
	// -------- Вывод таблицы--------
	public static ArrayList ReadDB(String id_user) throws ClassNotFoundException, SQLException
	{
		ArrayList listdesks = new ArrayList();
		resSet = statmt.executeQuery("SELECT * FROM desks where id_user = "+id_user);	
		System.out.println("AAAAAAAAAArrr  "+id_user );
		while(resSet.next())
		{
			String  name = resSet.getString("name");
	        System.out.println( "name = " + name );
	        listdesks.add(name);
		}			
		System.out.println("Таблица выведена");
		return listdesks;
	 }
	
	//проверка на наличие доски: да - если доска есть
	public boolean CheckDesk(String desk_name, String id_user) throws SQLException 
	{
		System.out.println("SELECT * FROM desks where name = '" +desk_name +"' and id_user = "+id_user);

		resSet = statmt.executeQuery("SELECT * FROM desks where name = '" +desk_name +"' and id_user = "+id_user);
		return resSet.next(); //если такая доска есть, то строка не null
	}
	
	// --------Закрытие--------
	public static void CloseDB() throws ClassNotFoundException, SQLException
	{
		conn.close();
		statmt.close();
		resSet.close();
		
		System.out.println("Соединения закрыты");
	}

	public void Delete_Desk(String id_desk) throws SQLException, ClassNotFoundException 
	{
		String id_tasklist = null;
		String id_card = null;
		
		resSet = statmt.executeQuery("SELECT * FROM 'tasklists' where id_desk = "+id_desk);
		while(resSet.next())
		{
			Integer id = resSet.getInt("id");
			id_tasklist = id.toString();
		}
		
		resSet = statmt.executeQuery("SELECT * FROM 'cards' where id_tasklist = "+id_tasklist);
		while(resSet.next())
		{
			Integer id = resSet.getInt("id");
			id_card = id.toString();
		}

		statmt.execute("DELETE FROM 'desks' where id = "+ id_desk);
		statmt.execute("DELETE FROM 'tasklists' where id_desk = "+ id_desk);
		statmt.execute("DELETE FROM 'cards' where id_tasklist = "+ id_tasklist);
		statmt.execute("DELETE FROM 'notes' where id_card = "+ id_card);
	}

}
