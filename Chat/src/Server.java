import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
	
	public static final int PORT = 8888;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<>(); // ������ ���� �����

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
            try {
            while (true) {
                // ����������� �� ������������� ������ ����������:
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomthing(socket)); // �������� ����� ����������� � ������
                } catch (IOException e) {
                    // ���� ���������� ��������, ����������� �����,
                    // � ��������� ������, ���� ������� ��� ��� ���������� ������:
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }

}
