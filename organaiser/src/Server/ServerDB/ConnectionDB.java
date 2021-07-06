package Server.ServerDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB 
{
	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;
	
	// --------ÏÎÄÊËŞ×ÅÍÈÅ Ê ÁÀÇÅ ÄÀÍÍÛÕ--------
	public static void Connection() throws ClassNotFoundException, SQLException 
	{
		conn = null;
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:db_org.s3db");
			   
		System.out.println("Áàçà Ïîäêëş÷åíà!");
	}
	

}
