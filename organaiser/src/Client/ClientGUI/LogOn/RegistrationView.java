package Client.ClientGUI.LogOn;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.xml.sax.SAXException;

import Client.ClientNetWork.ConnectionServer;
import Client.ClientNetWork.ControllerAnswer;
import Client.ClientNetWork.RegisterRequest;
import Client.ClientNetWork.SignInRequest;
import Client.ClientNetWork.Transmitter;

public class RegistrationView extends JFrame {
    static JFrame Frame = new JFrame("Вход");
    
    static JTextField Name_Text = new JTextField(40);
    static JTextField Password_Text = new JTextField(40);
    static JTextField ServerAdress_Text = new JTextField(40);
    
    JLabel Name_Label = new JLabel("Имя");
    JLabel Password_Label = new JLabel("Пароль");
    JLabel ServerAdress_Label = new JLabel("Адрес сервера");
    
    static JButton Registration_Button = new JButton("Registration");
    JButton SignIn_Button = new JButton("SignIn");
    
    static String Name;
    static String Password;
    static String ServerAdress;
    static ControllerAnswer Controller;  
    
    public static ConnectionServer Connect;
	
	RegistrationView() {
		Frame.setSize(300,300);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setLocationRelativeTo(null);
		Frame.setLayout(new GridBagLayout());

		Frame.add(Name_Label, new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1,0,0,0),0,0));

		Frame.add(Password_Label, new GridBagConstraints(0,1,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1,0,0,0),0,0));
		
		Frame.add(ServerAdress_Label, new GridBagConstraints(0,2,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1,0,0,0),0,0));
		
		Frame.add(Name_Text, new GridBagConstraints(1,0,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1,0,0,0),0,0));
		
		Frame.add(Password_Text, new GridBagConstraints(1,1,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1,0,0,0),0,0));
		
		Frame.add(ServerAdress_Text, new GridBagConstraints(1,2,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1,0,0,0),0,0));

		Frame.add(Registration_Button, new GridBagConstraints(0,4,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1,0,0,0),0,0));
		
		Frame.add(SignIn_Button, new GridBagConstraints(0,5,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(1,0,0,0),0,0));
		
		Frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
    	
		Frame.setVisible(true);
		
		Registration_Button_Listener();
		SignIn_Button_Listener();
    }
	
	private static void Registration_Button_Listener()
	{
		Registration_Button.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) 
        	{
        		Name = Name_Text.getText();
        		Password = Password_Text.getText();
        		ServerAdress = ServerAdress_Text.getText();
        		
        	    RegisterRequest Request = new RegisterRequest();
        		
        	    Connect = new ConnectionServer(ServerAdress);
        	    try {
					Connect.ConnectionServer();
				} catch (IOException e4) {
					e4.printStackTrace();
				}
        	    
        		Transmitter Trans = new Transmitter(Connect.out);
        		try {
					Trans.SendRequest(Request.Request(ServerAdress, Name, Password));
				} catch (IOException e3)  
        		{
					e3.printStackTrace();
				}     		
        	    
				try {
					Controller = new ControllerAnswer(Connect.in, Connect.out);
				} catch (IOException e2) 
				{
					e2.printStackTrace();
				}

        	    try {
					Controller.RegistrationResponse(Frame);
				} catch (SAXException | IOException e1) 
        	    {
					e1.printStackTrace();
				} 
        	}
        });
	}

	private void SignIn_Button_Listener()
	{
		SignIn_Button.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) 
        	{
        		Name = Name_Text.getText();
        		Password = Password_Text.getText();
        		ServerAdress = ServerAdress_Text.getText();
        		SignInRequest Request = new SignInRequest();
        		Connect = new ConnectionServer(ServerAdress);
        		try {
					Connect.ConnectionServer();
				} catch (IOException e4) {
					e4.printStackTrace();
				}
  
        		Transmitter Trans = new Transmitter(Connect.out);
        		try {
					Trans.SendRequest(Request.Request(ServerAdress, Name, Password));
				} catch (IOException e3)  
        		{
					e3.printStackTrace();
				}
        		
        		try {
					Controller = new ControllerAnswer(Connect.in, Connect.out);
				} catch (IOException e2) {
					e2.printStackTrace();
				}

        	    try {
					Controller.SignInResponse(Frame); 
				} catch (SAXException | IOException e1) {
					e1.printStackTrace();
				} 
        	}
        });
	}
	
	public static void main(String[] args) 
	{
		RegistrationView t = new RegistrationView();
	}
}
