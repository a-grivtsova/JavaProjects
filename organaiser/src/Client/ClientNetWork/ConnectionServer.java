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
    public InputStreamReader in; // поток чтени€ из сокета
    public OutputStreamWriter out; // поток чтени€ в сокет
    private String server_addr;
    
    public ConnectionServer(String server_addr)
    {
    	this.server_addr = server_addr;
    }
    
    public void ConnectionServer() throws UnknownHostException, IOException
	{
		try {
            this.socket = new Socket(server_addr, PORT);//инициализации объекта типа Socket, клиент, которому тот принадлежит,
            this.in = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
            this.out = new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8);
		} catch (IOException ex) {					//объ€вл€ет в сети, что хочет соединитьс€ с сервером про определЄнному адресу и номеру порта
            System.err.println("Open socket failed");
        }
	}

}
