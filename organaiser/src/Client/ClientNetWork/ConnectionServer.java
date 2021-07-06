package Client.ClientNetWork;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class ConnectionServer 
{
	private int PORT = 8888;
	public Socket socket;
    public InputStreamReader in; // ����� ������ �� ������
    public OutputStreamWriter out; // ����� ������ � �����
    private String server_addr;
    
    public ConnectionServer(String server_addr)
    {
    	this.server_addr = server_addr;
    }
    
    public void ConnectionServer() throws UnknownHostException, IOException
	{
		try {
            this.socket = new Socket(server_addr, PORT);//������������� ������� ���� Socket, ������, �������� ��� �����������,
            this.in = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
            this.out = new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8);
		} catch (IOException ex) {					//��������� � ����, ��� ����� ����������� � �������� ��� ������������ ������ � ������ �����
            System.err.println("Open socket failed");
        }
	}

}
