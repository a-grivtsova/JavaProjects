import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Client_Gui  extends JPanel{
		
		JFrame frame = new JFrame("Chatter");
		JFrame frame1 = new JFrame("Registration");
		
	    JTextField textField = new JTextField(40);
	    JTextArea messageArea = new JTextArea(8, 40);
	    
	    JLabel Username;
	    JLabel IP;
	    JTextField Usernametext;
	    JTextField IPtext;
	    JButton Exit;
	  
	    private Socket socket;
	    private BufferedReader in; // поток чтения из сокета
	    private PrintWriter out; // поток чтения в сокет
	    private BufferedReader inputUser; // поток чтения с консоли
	    private String addr; // ip адрес клиента
	    private int port; // порт соединения
	    private String nickname; // имя клиента
	    private Date time;
	    private String dtime;
	    private SimpleDateFormat dt1;

	    Client_Gui(String addr, int port)
	    {
	    	frame1.setSize(300,300);
	    	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame1.setLocationRelativeTo(null);
			frame1.setLayout(new GridBagLayout());
	    	
	    	Username = new JLabel("Username");
	    	IP = new JLabel("IP");
	    	
	    	Usernametext = new JTextField(20);
	    	IPtext = new JTextField(20); 
	    	IPtext.setText("localhost");
	    	Exit = new JButton("Exit");

	    	frame1.add(Username, new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.NORTH,
					GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));

			frame1.add(IP, new GridBagConstraints(0,1,1,1,1,1,GridBagConstraints.NORTH,
					GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
			
			frame1.add(Usernametext, new GridBagConstraints(1,0,1,1,1,1,GridBagConstraints.NORTH,
					GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
			
			frame1.add(IPtext, new GridBagConstraints(1,1,1,1,1,1,GridBagConstraints.NORTH,
					GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));

			frame1.add(Exit, new GridBagConstraints(0,4,1,1,1,1,GridBagConstraints.NORTH,
					GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
			
			frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    	
			frame1.setVisible(true);
			
	    	 try {
	             this.socket = new Socket(addr, port);
	         } catch (IOException e) {
	             System.err.println("Socket failed");
	         }
	         try {
	             // потоки чтения из сокета / записи в сокет, и чтения с консоли
	             inputUser = new BufferedReader(new InputStreamReader(System.in));
	             in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	             //out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

	             out = new PrintWriter(socket.getOutputStream(), true);

	             new ReadMsg().start(); // нить читающая сообщения из сокета в бесконечном цикле
	             //new WriteMsg().start(); // нить пишущая сообщения в сокет приходящие с консоли в бесконечном цикле
	         } catch (IOException e) {
	             // Сокет должен быть закрыт при любой
	             // ошибке, кроме ошибки конструктора сокета:
	        	 Client_Gui.this.downService();
	         }
	    	
		   // textField.setEditable(false);
	        messageArea.setEditable(false);
	        frame.getContentPane().add(textField,"South");
	        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
	        frame.pack();
	        
	     // Add Listeners
	        textField.addActionListener(new ActionListener() {

	        	@Override
	            public void actionPerformed(ActionEvent e) {
	        		//messageArea.append(textField.getText() + "\n");
	                out.println(textField.getText());
	                textField.setText("");
	            }
	        });

	        Exit.addActionListener(new ActionListener() {
	        	@Override
	            public void actionPerformed(ActionEvent e) {
	        		out.println("К нам подключился : " + "<" + Usernametext.getText()+ ">");     
	        		frame1.dispose();
	            }
	        });
	    }

	 // нить чтения сообщений с сервера
	    private class ReadMsg extends Thread {
	        @Override
	        public void run() {
	            
	            String str;
	            try {
	                while (true) {
	                    str = in.readLine(); // ждем сообщения с сервера
	                    if (str.equals("stop")) {
	                    	Client_Gui.this.downService(); // харакири
	                        break; // выходим из цикла если пришло "stop"
	                    }
	                    messageArea.append(str + "\n"); // пишем сообщение с сервера в текстовую область
	                }
	            } catch (IOException e) {
	            	Client_Gui.this.downService();
	            }
	        }
	    }
	    
	    private void downService() {
	        try {
	            if (!socket.isClosed()) {
	                socket.close();
	                in.close();
	                out.close();
	            }
	        } catch (IOException ignored) {}
	    }
	    
	public static void main(String[] args) {
		Client_Gui client = new Client_Gui("localhost", 8888);
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        //client.run();
	}
}
