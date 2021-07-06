package Client.ClientGUI.TaskList;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;

import Client.ClientGUI.Card.NewCardNameView;
import Client.ClientNetWork.CardRequest;
import Client.ClientNetWork.TaskListRequest;
import Client.ClientNetWork.Transmitter;
import Server.ServerDB.ConnectionDB;
import Server.ServerDB.DeskQuery;
import Server.ServerDB.TaskListQuery;

import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;

public class TaskListView {

	public JFrame tasklist; 
	public String tasklist_name;
	public String id_tasklist;
	public String id_desk;
	public OutputStreamWriter out;

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public TaskListView(OutputStreamWriter out, String tasklist_name, String id_desk) 
	{
		this.out = out;
		this.id_desk = id_desk;
		this.tasklist_name = tasklist_name; 
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @wbp.parser.entryPoint
	 */
	public JFrame initialize() throws ClassNotFoundException, SQLException {
		//узнаем id списка
		//узнаем id доски
		//подключение к бд 
		ConnectionDB connect = new ConnectionDB();
		connect.Connection();
				
		//создаем если еще нет таблицу
		TaskListQuery query = new TaskListQuery(connect.conn, connect.statmt, connect.resSet);
		this.id_tasklist = query.TaskList_id(tasklist_name, id_desk);
		
		tasklist = new JFrame();
		tasklist.setTitle(tasklist_name);
		tasklist.setBounds(100, 100, 311, 550);
		//tasklist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tasklist.getContentPane().setLayout(null);
		
		JButton cancelButton = new JButton(new ImageIcon("res/выход.jpg","¬ыход"));
	      cancelButton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  tasklist.dispose();
	          }
	      });
	      
	     tasklist.getContentPane().add(cancelButton);
		
		JButton btnNewButton = new JButton(" + ƒобавить еще одну карточку ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//здесь при нажатии на кнопку должно по€витьс€ окошко дл€ имени новой карточки
				NewCardNameView window = new NewCardNameView(out, id_tasklist);
			}
		});
		
		
		btnNewButton.setBounds(10, 11, 241, 23);
		tasklist.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("ќткрыть карточку");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//здесь показываютс€ все списки задач
				//запрос на список досок существующих
				CardRequest request = new CardRequest();
				Transmitter Trans = new Transmitter(out);
				try {
					Trans.SendRequest(request.CardListRequest(id_tasklist));
					System.out.println("TaskListView  "+request.CardListRequest(id_tasklist));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(10, 60, 240, 23);
		tasklist.getContentPane().add(btnNewButton_3);
		
		
		JButton btnNewButton_4 = new JButton("”далить список");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				TaskListRequest request = new TaskListRequest();
				Transmitter Trans = new Transmitter(out);
				try {
					Trans.SendRequest(request.TaskListDeleteRequest( tasklist_name, id_desk));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(10, 250, 241, 23);
		tasklist.getContentPane().add(btnNewButton_4);

		
		return tasklist; 
	}
}
