package Server.ServerDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterQuery 
{
	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;
	
	public RegisterQuery(Connection conn, Statement statmt, ResultSet resSet)
	{
		this.conn = conn;
		this.statmt = statmt;
		this.resSet = resSet;
	}
	
	public static void CreateTableUsers() throws SQLException
	{
		statmt = conn.createStatement();
		statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'password' text);");
		
		System.out.println("“аблица создана или уже существует.");
	}
	
	public static String InsertTable(String name, String password) throws SQLException
	{
		String id_user = null;
		statmt.execute("INSERT INTO 'users' ('name', 'password') VALUES ('"+name+"', '"+password+"'); ");
		resSet = statmt.executeQuery("SELECT * FROM 'users' where name = '"+name+"' and password = '"+password+"'"); //where 'name' = '"+name+"' and 'password' = '"+password+"'
		while(resSet.next())
		{
			Integer id = resSet.getInt("id");
			id_user = id.toString();
		}
		
		System.out.println("“аблица заполнена"); 
		return id_user;
	}
	
	// -------- ¬ывод таблицы--------
	public static void ReadDB() throws ClassNotFoundException, SQLException
	{
		resSet = statmt.executeQuery("SELECT * FROM users");	
		while(resSet.next())
		{
			int id = resSet.getInt("id");
			String  name = resSet.getString("name");
			String  password = resSet.getString("password");
	         System.out.println( "ID = " + id );
	         System.out.println( "name = " + name );
	         System.out.println( "password = " + password );
		}			
		System.out.println("“аблица выведена");
	 }
	
	
	//проверка на наличие пользовател€: да - если пользоваетель есть
	public boolean CheckUser(String name, String password) throws SQLException 
	{
		System.out.println("SELECT * FROM users where name = '"+name +"' and password = '"+password+"'");
		resSet = statmt.executeQuery("SELECT * FROM users where name = '"+name+"' and password = '"+password+"'");
		return resSet.next(); //если такой пользователь есть, то строка не null
	}
	
	// --------«акрытие--------
	public static void CloseDB() throws ClassNotFoundException, SQLException
	{
		conn.close();
		statmt.close();
		resSet.close();
		
		System.out.println("—оединени€ закрыты");
	}
	
}
