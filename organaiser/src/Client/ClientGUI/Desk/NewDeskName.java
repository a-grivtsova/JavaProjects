package Client.ClientGUI.Desk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import Client.ClientNetWork.DeskRequest;
import Client.ClientNetWork.Transmitter;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class NewDeskName {

	public JFrame frmNewDeskName;
	public JTextField textField;
	public String desk_name;
	public String id_user;
	private JButton btnNewButton;
	private OutputStreamWriter out;
	private JFrame frmUserProfile;
	/**
	 * Create the application.
	 */
	public NewDeskName(OutputStreamWriter out, JFrame frmUserProfile, String id_user) {
		this.out = out;
		this.frmUserProfile = frmUserProfile;
		this.id_user = id_user;
		initialize();
		frmNewDeskName.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewDeskName = new JFrame();
		frmNewDeskName.setTitle("New Desk Name");
		frmNewDeskName.setBounds(100, 100, 425, 184);
		//frmNewDeskName.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNewDeskName.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter a name for the new desk:");
		lblNewLabel.setBounds(0, 0, 395, 28);
		frmNewDeskName.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(48, 33, 288, 38);
		frmNewDeskName.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton cancelButton = new JButton(new ImageIcon("res/выход.jpg","Выход"));
	      cancelButton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  frmNewDeskName.dispose();
	          }
	      });
	    frmNewDeskName.getContentPane().add(cancelButton);
	      
		btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				desk_name = textField.getText();
				//запрос на новую доску
				DeskRequest Request = new DeskRequest();
				Transmitter Trans = new Transmitter(out);
        		try {
					Trans.SendRequest(Request.NewDeskRequest(desk_name, id_user)); 
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}	
        		frmNewDeskName.dispose();
        		frmUserProfile.dispose();
			}
		});
		btnNewButton.setBounds(62, 82, 97, 35);
		frmNewDeskName.getContentPane().add(btnNewButton);
	}

}
