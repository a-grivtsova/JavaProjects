package Server.ServerNetWork;

import java.io.BufferedWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import Server.ServerDB.ConnectionDB;
import Server.ServerDB.RegisterQuery;

public class RegisterHandler 
{
	ServerSomething servsome;
	ServerMessage Message;
	
	RegisterHandler(ServerSomething servsome, ServerMessage Message)
	{
		this.servsome = servsome;
		this.Message = Message;
	} 
	
	public void Register(String name, String password) throws ClassNotFoundException, SQLException, UnsupportedEncodingException
	{	
		if(UserParametrsCheck(name, password))
		{
			//����������� � ��
			ConnectionDB connect = new ConnectionDB();
			connect.Connection();
			
			//������� ���� ��� ��� �������
			RegisterQuery query = new RegisterQuery(connect.conn, connect.statmt, connect.resSet);
			query.CreateTableUsers();
			
			//������� ������������, ���� ��� ��� ��� � �������
			if(!query.CheckUser(name, password))
			{
				String id = query.InsertTable(name, password); //�������� - id?????
				AcceptConnection.Map_id_User.put(servsome.id, servsome.nickname);
				Message.Send_Server_Answer(Message.Answer_Success()); 
				Message.Send_Server_Answer(Message.Answer_Registration(id));
			}
			else
			{
				System.out.println("exist such user");
				Message.Send_Server_Answer(Message.Answer_Error("������������ � ����� ������� ��� ����������!!!"));
	    		AcceptConnection.Map_id_User.remove(servsome.id);
	    		AcceptConnection.Map_Thread_id.remove(this.servsome);
			}

			//close
			query.CloseDB();
		}
		else 
		{
			Message.Send_Server_Answer(Message.Answer_Error("������� ������� ����� ��� ������!!!"));
		}
	}
	
	boolean UserParametrsCheck(String nick, String pass)
    {
    	if(nick.equals("") || pass.equals(""))
    		return false;	
		return true;
    }

}
