package Server.ServerNetWork;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class AcceptConnection {
	
	public static final int PORT = 8888;
    
    public static  Hashtable<Integer, String> Map_id_User = new  Hashtable<Integer, String>();
    public static  Hashtable<ServerSomething, Integer> Map_Thread_id = new  Hashtable<ServerSomething,Integer>();
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(PORT);
        Integer id = 0;
            try {
            while (true) {
                // Блокируется до возникновения нового соединения:
                Socket socket = server.accept();

                try {
                	 Map_Thread_id.put(new ServerSomething(id, socket),id++);
                } catch (IOException e) {
                    // Если завершится неудачей, закрывается сокет,
                    // в противном случае, нить закроет его при завершении работы:
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
