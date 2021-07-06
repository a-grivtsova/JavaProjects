package Server.ServerDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CardQuery {
	
	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;
	
	public CardQuery(Connection conn, Statement statmt, ResultSet resSet) throws SQLException
	{
		this.conn = conn;
		this.statmt = conn.createStatement();
		this.resSet = resSet;
	}	
	
	public static void CreateTableCards() throws SQLException 
	{
		String query = "CREATE TABLE if not exists 'cards'('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'id_tasklist' INTEGER, "
		+ "FOREIGN KEY('id_tasklist') REFERENCES tasklists('id'))";
		statmt.execute(query);							
		
		System.out.println("Таблица создана или уже существует.");
	}
	
	public static void InsertTable(String name, String id_tasklist) throws SQLException
	{
		statmt.execute("INSERT INTO 'cards' ('name','id_tasklist') VALUES ('"+name+"',"+id_tasklist+")");
		  
		System.out.println("Таблица заполнена");
	}
	
	//проверка на наличие карточки: да - если карточка есть
	public boolean CheckCard(String name, String id_tasklist) throws SQLException 
	{
		System.out.println("SELECT * FROM cards where name = '" + name +" and id_tasklist = "+id_tasklist);

		resSet = statmt.executeQuery("SELECT * FROM cards where name = '" + name +"' and id_tasklist = "+id_tasklist);
		return resSet.next(); //если такой список есть, то строка не null
	}
		
	
	public static String TaskList_id(String tasklist_name, String id_desk) throws SQLException
	{
		String id_tasklist = null;
		resSet = statmt.executeQuery("SELECT * FROM 'tasklists' where name = '"+tasklist_name+"' and id_desk = "+id_desk);
		while(resSet.next())
		{
			Integer id = resSet.getInt("id");
			id_tasklist = id.toString();
		}
		return id_tasklist;
	}

	public static String Card_id(String card_name, String id_tasklist) throws SQLException
	{
		String id_card = null;
		System.out.println("SELECT * FROM 'cards' where name = '"+card_name+"' and id_tasklist = "+id_tasklist);
		resSet = statmt.executeQuery("SELECT * FROM 'cards' where name = '"+card_name+"' and id_tasklist = "+id_tasklist);
		while(resSet.next())
		{
			Integer id = resSet.getInt("id");
			id_card = id.toString();
			System.out.println("public static String Card_id	" + id_card);
		}
		return id_card; 
	}
	
	// --------Закрытие--------
	public static void CloseDB() throws ClassNotFoundException, SQLException
	{
		conn.close();
		statmt.close();
		resSet.close();
			
		System.out.println("Соединения закрыты");
	}

	// -------- Вывод таблицы--------
	public static ArrayList ReadDB(String id_tasklist) throws ClassNotFoundException, SQLException
	{
		ArrayList cardlists = new ArrayList();
			
		resSet = statmt.executeQuery("SELECT * FROM 'cards' where id_tasklist = "+id_tasklist);	
		System.out.println("AAAAAAAAAA33" +id_tasklist);
		while(resSet.next())
		{
			String  name = resSet.getString("name");
			System.out.println( "name = " + name );
			cardlists.add(name);
		}			
		System.out.println("Таблица выведена");
		return cardlists;
	}
}
