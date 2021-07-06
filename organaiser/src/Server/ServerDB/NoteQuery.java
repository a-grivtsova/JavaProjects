package Server.ServerDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NoteQuery {
	
	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;
	
	public NoteQuery(Connection conn, Statement statmt, ResultSet resSet) throws SQLException
	{
		this.conn = conn;
		this.statmt = conn.createStatement();
		this.resSet = resSet;
	}	
	
	public static void CreateTableNotes() throws SQLException 
	{
		String query = "CREATE TABLE if not exists 'notes'('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'note' text, 'id_card' INTEGER, "
		+ "FOREIGN KEY('id') REFERENCES cards('id'))";
		statmt.execute(query);							
		
		System.out.println("“аблица создана или уже существует.");
	}
	
	public static void InsertTable(String note, String id_card) throws SQLException
	{
		statmt.execute("INSERT INTO 'notes' ('note','id_card') VALUES ('"+note+"',"+id_card+")");
		  //"INSERT INTO 'cards' ('name','id_tasklist') VALUES ('"+name+"',"+id_tasklist+")"
		System.out.println("“аблица заполнена");
	}
	
	//проверка на наличие записи: да - если запись есть:у одной карточки одна запись
	public boolean CheckNote(String id_card) throws SQLException 
	{
		System.out.println("SELECT * FROM 'notes' where id_card = " + id_card);

		resSet = statmt.executeQuery("SELECT * FROM 'notes' where id_card = " + id_card);
		return resSet.next(); //если такой список есть, то строка не null
	}
	
	public static String Note_id(String id_card) throws SQLException
	{
		String id_note = null;
		System.out.println("SELECT * FROM 'notes' where id_card = " + id_card);
		resSet = statmt.executeQuery("SELECT * FROM 'notes' where id_card = " + id_card );
		while(resSet.next())
		{
			Integer id = resSet.getInt("id");
			id_note = id.toString();
			System.out.println("String Note_id = " + id_note);
		}
		return id_note; 
	}
	
	public static String Read_Note(String id_card) throws SQLException
	{
		String note = null;
		System.out.println("SELECT * FROM 'notes' where id_card = " + id_card);
		resSet = statmt.executeQuery("SELECT * FROM 'notes' where id_card = " + id_card );
		while(resSet.next())
		{
			note = resSet.getString("note");
			System.out.println("String Note_id = " + note);
		}
		return note; 
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
	
	// --------«акрытие--------
	public static void CloseDB() throws ClassNotFoundException, SQLException
	{
		conn.close();
		statmt.close();
		resSet.close();
			
		System.out.println("—оединени€ закрыты");
	}

	public void Delete_Note(String id_card, String id_note) throws SQLException 
	{
		statmt.execute("DELETE FROM 'notes' where id = "+ id_note);
		statmt.execute("DELETE FROM 'cards' where id = "+ id_card);	
	}


}
