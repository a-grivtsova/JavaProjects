package Client.ClientGUI.TaskList;

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

public class OpenTaskListView {
	
	private JFrame frmOpenTaskList; 
	OutputStreamWriter out;
	public JComboBox comboBox = new JComboBox();

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */

	public JFrame ShowTaskList(OutputStreamWriter out, ArrayList listdesks)  
	{
		
		frmOpenTaskList = new JFrame();
		frmOpenTaskList.getContentPane().setEnabled(false);
		frmOpenTaskList.setTitle("Open Task List");
		frmOpenTaskList.setBounds(100, 100, 450, 300);
		//frmOpenTaskList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOpenTaskList.getContentPane().setLayout(null);
		
		
		JLabel lblChooseDesk = new JLabel("Choose Task List:");
		lblChooseDesk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblChooseDesk.setBounds(79, 24, 118, 30);
		frmOpenTaskList.getContentPane().add(lblChooseDesk);
	
		JButton cancelButton = new JButton(new ImageIcon("res/выход.jpg","Выход"));
	      cancelButton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  frmOpenTaskList.dispose();
	          }
	      });
	    frmOpenTaskList.getContentPane().add(cancelButton);

		//добавляем доски
		for(int i = 0; i < listdesks.size();i++) 
		{
			comboBox.addItem(listdesks.get(i));
		}		

		comboBox.setEditable(true);
		comboBox.setBounds(89, 65, 198, 58);
		frmOpenTaskList.getContentPane().add(comboBox);
		frmOpenTaskList.setVisible(true);
		return frmOpenTaskList;	
	}

}
