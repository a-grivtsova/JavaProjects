package Client.ClientGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import Client.ClientNetWork.DeskRequest;
import Client.ClientNetWork.RegisterRequest;
import Client.ClientNetWork.Transmitter;

public class UserProfileView {

	private JFrame frmUserProfile;
	private OutputStreamWriter out;
	private String desk_name;
	public String id_user;

	/**
	 * Create the application.
	 */
	public UserProfileView(OutputStreamWriter out, String id_user) 
	{
		this.out = out;
		this.id_user = id_user;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public JFrame initialize() 
	{
		frmUserProfile = new JFrame();
		frmUserProfile.setTitle("User Profile");
		frmUserProfile.setBounds(100, 100, 378, 243);
		//frmUserProfile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(new BoxLayout(layeredPane, BoxLayout.X_AXIS));
		
		JButton cancelButton = new JButton(new ImageIcon("res/выход.jpg","Выход"));
	      cancelButton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  frmUserProfile.dispose();
	          }
	      });
	      frmUserProfile.getContentPane().add(cancelButton);
		
		JButton btnNewButton = new JButton("New Desk");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{	//вывести текстовое поле для имени доски
				Client.ClientGUI.Desk.NewDeskName DeskName = new Client.ClientGUI.Desk.NewDeskName(out, frmUserProfile, id_user);		
				frmUserProfile.dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Open Desk");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
					//запрос на список досок существующих
					DeskRequest request = new DeskRequest();
					Transmitter Trans = new Transmitter(out);
					Trans.SendRequest(request.ListDeskRequest(id_user));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmUserProfile.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addComponent(btnNewButton)
							.addGap(56)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(123)
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(75)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(89))
		);
		frmUserProfile.getContentPane().setLayout(groupLayout);
		return frmUserProfile; 
	}
}
