package Client.ClientGUI.Card;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Client.ClientNetWork.NoteRequest;
import Client.ClientNetWork.TaskListRequest;
import Client.ClientNetWork.Transmitter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class CardView {

	private JFrame frmNamecard;
	public JTextField textField;
	private String card_name;
	private String note;
	private String id_tasklist;
	private OutputStreamWriter out;

	public CardView(OutputStreamWriter out, String card_name, String id_tasklist, String note) throws UnknownHostException, IOException 
	{
		this.card_name = card_name;
		this.note = note;
		this.id_tasklist = id_tasklist;
		this.out = out;
		initialize();
		frmNamecard.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	private void initialize() throws UnknownHostException, IOException {
		frmNamecard = new JFrame();
		frmNamecard.setTitle(card_name);
		frmNamecard.setBounds(100, 100, 450, 300);
		//frmNamecard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNamecard.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Описание:");
		lblNewLabel.setBounds(53, 25, 81, 26);
		frmNamecard.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		textField.setBounds(53, 52, 310, 113);
		frmNamecard.getContentPane().add(textField);
		textField.setColumns(10);
		
		if(! note.equals("no note"))
		{
			textField.setText(note);
		}
		
		JButton cancelButton = new JButton(new ImageIcon("res/выход.jpg","Выход"));
	      cancelButton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  frmNamecard.dispose();
	          }
	      });
	      frmNamecard.getContentPane().add(cancelButton);
		
		JButton btnNewButton = new JButton("Сохранить описание");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String note = textField.getText();
				//запрос к бд на описание
				NoteRequest request = new NoteRequest();
				Transmitter Trans = new Transmitter(out);
					try {
						Trans.SendRequest(request.NewNoteRequest(card_name, id_tasklist, note));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		btnNewButton.setBounds(53, 168, 185, 23);
		frmNamecard.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Удалить карточку");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				NoteRequest request = new NoteRequest();
				Transmitter Trans = new Transmitter(out);
					try {
						Trans.SendRequest(request.DeleteNoteRequest(card_name, id_tasklist));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		btnNewButton_1.setBounds(266, 227, 158, 23);
		frmNamecard.getContentPane().add(btnNewButton_1);
	}
}
