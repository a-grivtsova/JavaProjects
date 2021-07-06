package Client.ClientGUI.TaskList;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Client.ClientNetWork.DeskRequest;
import Client.ClientNetWork.TaskListRequest;
import Client.ClientNetWork.Transmitter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.awt.event.ActionEvent;

public class NewTaskListName {

	private JFrame frame;  
	private JTextField textField;
	private OutputStreamWriter out;
	private String id_user;
	private String id_desk;
	public String tasklist_name;

	/**
	 * Create the application.
	 */
	public NewTaskListName(OutputStreamWriter out, String id_desk, String id_user) {
		this.id_desk = id_desk;
		this.id_user = id_user;
		this.out = out;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 424, 157);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Введите заголовок списка: ");
		lblNewLabel.setBounds(49, 11, 190, 23);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		textField.setBounds(49, 35, 208, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton cancelButton = new JButton(new ImageIcon("res/выход.jpg","Выход"));
	      cancelButton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  frame.dispose();
	          }
	      });
	    frame.getContentPane().add(cancelButton);
	    
		JButton btnNewButton = new JButton("Добавить список");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				tasklist_name = textField.getText();
				TaskListRequest Request = new TaskListRequest();
				Transmitter Trans = new Transmitter(out);
				
				try {//посылаем запрос на новый список
					Trans.SendRequest(Request.NewTaskListRequest(tasklist_name, id_desk, id_user));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
			}
		});
		btnNewButton.setBounds(49, 77, 157, 30);
		frame.getContentPane().add(btnNewButton);
	}
}
