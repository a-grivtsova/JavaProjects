package Client.ClientGUI.Desk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

import Client.ClientNetWork.DeskRequest;
import Client.ClientNetWork.Transmitter;

import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class OpenDeskView {

	private JFrame frmOpenDesk;
	OutputStreamWriter out;
	public JComboBox comboBox = new JComboBox();

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */

	public JFrame ShowDesksList(OutputStreamWriter out, ArrayList listdesks, String id_user)  
	{
		
		frmOpenDesk = new JFrame();
		frmOpenDesk.getContentPane().setEnabled(false);
		frmOpenDesk.setTitle("Open Desk");
		frmOpenDesk.setBounds(100, 100, 450, 300);
		//frmOpenDesk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOpenDesk.getContentPane().setLayout(null);
		
		
		JLabel lblChooseDesk = new JLabel("Choose Desk:");
		lblChooseDesk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblChooseDesk.setBounds(79, 24, 118, 30);
		frmOpenDesk.getContentPane().add(lblChooseDesk);
	
		JButton cancelButton = new JButton(new ImageIcon("res/выход.jpg","Выход"));
	      cancelButton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  frmOpenDesk.dispose();
	          }
	      });
	     frmOpenDesk.getContentPane().add(cancelButton);

		//добавляем доски
		for(int i = 0; i < listdesks.size();i++) 
		{
			comboBox.addItem(listdesks.get(i));
		}		

		comboBox.setEditable(true);
		comboBox.setBounds(89, 65, 198, 58);
		frmOpenDesk.getContentPane().add(comboBox);
		frmOpenDesk.setVisible(true);
		return frmOpenDesk;	 
	}
}
 