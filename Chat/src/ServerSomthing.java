import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerSomthing extends Thread{
	private Socket socket; // �����, ����� ������� ������ �������� � ��������,
    // ����� ���� - ������ � ������ ����� �� �������
    private BufferedReader in; // ����� ������ �� ������
    private BufferedWriter out; // ����� ������ � �����

    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        // ���� ������ �����/������ �������� � ������������� ����������, ��� ������������ ������
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start(); // �������� run()
    }
    @Override
    public void run() {
        String word;
        try {

            while (true) {
                word = in.readLine();
                if(word.equals("stop")) {
                    break;                }
                for (ServerSomthing vr : Server.serverList) {
                    vr.send(word); // �������� �������� ��������� �
                   // ������������ ������� ���� ��������� ������� ���
                }
            }

        } catch (IOException e) {
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }

}
