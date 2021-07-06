package Client.ClientGUI.Desk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;

import Client.ClientGUI.TaskList.NewTaskListName;
import Client.ClientNetWork.DeskRequest;
import Client.ClientNetWork.NoteRequest;
import Client.ClientNetWork.TaskListRequest;
import Client.ClientNetWork.Transmitter;
import Server.ServerDB.ConnectionDB;
import Server.ServerDB.DeskQuery;

import java.awt.Font;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeskView {

	private JFrame frmDesk;
	String desk_name;
	String id_user;
	String id_desk;
	OutputStreamWriter out;

	/**
	 * Create the application.
	 */
	public DeskView(OutputStreamWriter out,String desk_name, String id_user)	
	{
		this.desk_name = desk_name;
		this.id_user = id_user;
		this.out = out;
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public JFrame initialize() throws SQLException, ClassNotFoundException {
		//узнаем id доски
		//подключение к бд
		ConnectionDB connect = new ConnectionDB();
		connect.Connection();
		
		//создаем если еще нет таблицу
		DeskQuery query = new DeskQuery(connect.conn, connect.statmt, connect.resSet);
		this.id_desk = query.Desk_id(desk_name, id_user);
		
		frmDesk = new JFrame();
		frmDesk.getContentPane().setFont(frmDesk.getContentPane().getFont().deriveFont(frmDesk.getContentPane().getFont().getSize() + 1f));
		frmDesk.setTitle(this.desk_name);
		frmDesk.setBounds(100, 100, 1176, 642);
		//frmDesk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDesk.getContentPane().setLayout(null);
		
		JButton cancelButton = new JButton(new ImageIcon("res/выход.jpg","¬ыход"));
	      cancelButton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  frmDesk.dispose();
	          }
	      });
	    frmDesk.getContentPane().add(cancelButton);
		
		JButton btnNewButton_1 = new JButton("Delete Desk");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeskRequest request = new DeskRequest();
				Transmitter Trans = new Transmitter(out);
					try {
						Trans.SendRequest(request.DeleteDeskRequest(desk_name, id_user));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		btnNewButton_1.setBounds(1028, 11, 102, 31);
		frmDesk.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New Task List");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//здесь вызываетс€ вьюшка дл€ имени нового списка задач
				NewTaskListName TaskList = new NewTaskListName(out, id_desk, id_user); 
			}
		});
		btnNewButton_2.setBounds(216, 11, 115, 40);
		frmDesk.getContentPane().add(btnNewButton_2);
		
		
		JButton btnNewButton_3 = new JButton("Open Task List");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//здесь показываютс€ все списки задач
				//запрос на список досок существующих
				TaskListRequest request = new TaskListRequest();
				Transmitter Trans = new Transmitter(out);
				try {
					Trans.SendRequest(request.ListTaskListRequest(id_desk));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(416, 11, 130, 40);
		frmDesk.getContentPane().add(btnNewButton_3);
		
		return frmDesk;
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
