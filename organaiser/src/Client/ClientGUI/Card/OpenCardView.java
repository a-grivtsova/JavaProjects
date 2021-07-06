package Client.ClientGUI.Card;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class OpenCardView {

	private JFrame frmOpenCard;  
	OutputStreamWriter out;
	public JComboBox comboBox = new JComboBox();

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */

	public JFrame ShowCardList(OutputStreamWriter out, ArrayList listcards)   
	{
		
		frmOpenCard = new JFrame();
		frmOpenCard.getContentPane().setEnabled(false);
		frmOpenCard.setTitle("Open Card");
		frmOpenCard.setBounds(100, 100, 450, 300);
		//frmOpenCard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOpenCard.getContentPane().setLayout(null);
		
		
		JLabel lblChooseDesk = new JLabel("Choose Card:");
		lblChooseDesk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblChooseDesk.setBounds(79, 24, 118, 30);
		frmOpenCard.getContentPane().add(lblChooseDesk);
	
		JButton cancelButton = new JButton(new ImageIcon("res/выход.jpg","Выход"));
	      cancelButton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  frmOpenCard.dispose();
	          }
	      });
	    frmOpenCard.getContentPane().add(cancelButton);
		
		//добавляем доски
		for(int i = 0; i < listcards.size();i++) 
		{
			comboBox.addItem(listcards.get(i));
		}		

		comboBox.setEditable(true);
		comboBox.setBounds(89, 65, 198, 58);
		frmOpenCard.getContentPane().add(comboBox);
		frmOpenCard.setVisible(true);
		return frmOpenCard;	 
	}

}
