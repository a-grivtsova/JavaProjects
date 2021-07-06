package Server.ServerNetWork;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import Server.ServerDB.ConnectionDB;
import Server.ServerDB.RegisterQuery;
import Server.ServerDB.SignInQuery;

public class SignInHandler 
{
	ServerSomething servsome;
	ServerMessage Message;
	
	SignInHandler(ServerSomething servsome, ServerMessage Message)
	{
		this.servsome = servsome;
		this.Message = Message;
	}
	
	public void SignIn( String name, String password) throws ClassNotFoundException, SQLException, UnsupportedEncodingException
	{	
		if(UserParametrsCheck(name, password))
		{
			//подключение к бд
			ConnectionDB connect = new ConnectionDB();
			connect.Connection();
			
			SignInQuery query = new SignInQuery(connect.conn, connect.statmt, connect.resSet);
			
			//проверим, есть ли пользователь в таблице
			if(query.CheckUser(name, password))
			{
				String id = query.User_id(name, password);
				System.out.println("SSSSSSS");
				System.out.println(id);
				System.out.println("yes such user");
				Message.Send_Server_Answer(Message.Answer_Success()); 
				Message.Send_Server_Answer(Message.Answer_SignIn(id));
			}
			else
			{
				System.out.println("no such user");
				Message.Send_Server_Answer(Message.Answer_Error("Пользователя с таким логином и паролем не существует!!!"));
	    		AcceptConnection.Map_id_User.remove(servsome.id);
	    		AcceptConnection.Map_Thread_id.remove(this.servsome);
			}

			//close
			query.CloseDB();
		}
		else 
		{
			Message.Send_Server_Answer(Message.Answer_Error("Неверно введены логин или пароль!!!"));
		}
	}
	
	boolean UserParametrsCheck(String nick, String pass)
    {
    	if(nick.equals("") || pass.equals(""))
    		return false;	
		return true;
    }


}
