package Server.ServerNetWork;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import Server.ServerDB.ConnectionDB;
import Server.ServerDB.DeskQuery;
import Server.ServerDB.NoteQuery;
import Server.ServerDB.RegisterQuery;

public class DeskHandler 
{
	ServerMessage Message;
	
	DeskHandler(ServerMessage Message)
	{
		this.Message = Message;
	}
	
	public void NewDesk(String desk_name, String id_user) throws UnsupportedEncodingException, ClassNotFoundException, SQLException
	{
		if(DeskNameCheck(desk_name))
		{
			//����������� � ��
			ConnectionDB connect = new ConnectionDB();
			connect.Connection();
			
			//������� ���� ��� ��� �������
			DeskQuery query = new DeskQuery(connect.conn, connect.statmt, connect.resSet);
			query.CreateTableDesks();
			
			if(!query.CheckDesk(desk_name, id_user))
			{
				query.InsertTable(desk_name, id_user); //��������
				String id_desk = query.Desk_id(desk_name, id_user);//������ id
				Message.Send_Server_Answer(Message.Answer_Success()); 
				Message.Send_Server_Answer(Message.Answer_New_Desk(id_user, id_desk, desk_name));
			}
			else
			{
				System.out.println("exist such desk");
				Message.Send_Server_Answer(Message.Answer_Error("����� � ����� ������ ��� ����������!!!"));
			}
			//close
			query.CloseDB();
			
		}
		else
		{
			Message.Send_Server_Answer(Message.Answer_Error("������� ������� ��� �����!!!"));
		}	
	}
	
	public void ListDesk(String id_user) throws ClassNotFoundException, SQLException, UnsupportedEncodingException
	{
		String xml_message =  ""; 
		ArrayList listdesks = new ArrayList();

		//����������� � ��
		ConnectionDB connect = new ConnectionDB();
		connect.Connection();
		
		DeskQuery query = new DeskQuery(connect.conn, connect.statmt, connect.resSet);
		listdesks = query.ReadDB(id_user);
		query.CloseDB();
		
		for(int i = 0; i < listdesks.size();i++) //�������� ������ ����� ��� ������
		{
			String desk_name = (String) listdesks.get(i);
			xml_message = xml_message + "<desk><desk_name>" + desk_name + "</desk_name></desk>";
		}
		Message.Send_Server_Answer(Message.Answer_Success());
		Message.Send_Server_Answer(Message.Answer_ListDesk(xml_message));
		//���� �������� ��������, ����� ����� ���
	}
	
	boolean DeskNameCheck(String desk_name)
    {
    	if(desk_name.equals(""))
    		return false;	
		return true;
    }

	public void DeskDelete(String desk_name, String id_user) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
		String id_desk = null; 
		String note = null;
		
		//����������� � ��
		ConnectionDB connect = new ConnectionDB();
		connect.Connection();
		
		DeskQuery query = new DeskQuery(connect.conn, connect.statmt, connect.resSet);

		id_desk = query.Desk_id(desk_name, id_user);
		query.Delete_Desk(id_desk);
		
		Message.Send_Server_Answer(Message.Answer_Success()); 
		System.out.println("public void NoteDelete	"+Message.Delete_Desk(id_desk));
		Message.Send_Server_Answer(Message.Delete_Desk(id_desk));
		
		//close
		query.CloseDB();
		
	}	
}
