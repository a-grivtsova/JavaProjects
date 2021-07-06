package Client.ClientGUI.Card;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Client.ClientNetWork.CardRequest;
import Client.ClientNetWork.TaskListRequest;
import Client.ClientNetWork.Transmitter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.awt.event.ActionEvent;

public class NewCardNameView {

	private JFrame frame;
	private JTextField textField;
	private OutputStreamWriter out;
	String id_tasklist;



	/**
	 * Create the application.
	 */
	public NewCardNameView(OutputStreamWriter out, String id_tasklist) { 
		this.out = out;
		this.id_tasklist = id_tasklist;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 447, 199);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("¬ведите заголовок дл€ карточки:");
		lblNewLabel.setBounds(32, 21, 270, 27);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		textField.setBounds(32, 59, 248, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton cancelButton = new JButton(new ImageIcon("res/выход.jpg","¬ыход"));
	      cancelButton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  frame.dispose();
	          }
	      });
	    frame.getContentPane().add(cancelButton);
		
		JButton btnNewButton = new JButton("ƒобавить карточку");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String card_name = textField.getText();
				//здесь запрос к серверу на новую карточку
				CardRequest Request = new CardRequest();
				Transmitter Trans = new Transmitter(out);
				
				try {//посылаем запрос на новый список
					Trans.SendRequest(Request.NewCardtRequest(card_name, id_tasklist));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
			}
		});
		btnNewButton.setBounds(32, 109, 161, 23);
		frame.getContentPane().add(btnNewButton);
	}

}
