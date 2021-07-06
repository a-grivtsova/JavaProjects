package Server.ServerDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignInQuery 
{
	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;
	
	public SignInQuery(Connection conn, Statement statmt, ResultSet resSet)
	{
		this.conn = conn;
		this.statmt = statmt;
		this.resSet = resSet;
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
		
		public static String User_id(String name, String password) throws SQLException
		{
			String id_user = null;
			resSet = statmt.executeQuery("SELECT * FROM 'users' where name = '"+name+"' and password = '"+password+"'");
			while(resSet.next())
			{
				Integer id = resSet.getInt("id");
				id_user = id.toString();
			}
			return id_user;
		}
		
		
		//проверка на наличие пользовател€: да - если пользоваетель есть
		public boolean CheckUser(String name, String password) throws SQLException 
		{
			statmt = conn.createStatement();
			System.out.println("SELECT * FROM users where name ='" +name +"'and password='" + password+"'");
			resSet = statmt.executeQuery("SELECT * FROM users where name ='" +name +"'and password='" + password+"'");
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
