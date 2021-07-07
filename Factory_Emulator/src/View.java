import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.Timer;
public class View extends javax.swing.JFrame{

	factory_emulator f;
	int time = 1;
	
	public void start()
	{
	     Timer t = new Timer(time, this.listener);
		 t.start(); 
	}
	
	JLabel accessories_store_field = new JLabel("0");
	JLabel accessories_field = new JLabel("0");
	JLabel bodies_store_field = new JLabel("0");
	JLabel bodies_field = new JLabel("0");
	JLabel engine_store_field = new JLabel("0");
	JLabel engine_field = new JLabel("0");
	JLabel cars_store_field = new JLabel("0");
	JLabel cars_field = new JLabel("0"); 


	class MyPanel extends JPanel{
		int length = 5;
			
		public void paintComponent(Graphics g) {
		super.paintComponent(g);	
		g.fillRect(5, 5, length, 5);
		}
		
		public void setlength(int legth)
		{
			this.length = (length > 0 ? length:5);
			repaint();
		}	
	}
	
	public View(factory_emulator f) 
	{	
		MyPanel panel_accserrory;
		JSlider slider_accserrory;
		
		this.f = f;
		
		panel_accserrory = new MyPanel();
		slider_accserrory = new JSlider(SwingConstants.HORIZONTAL,0,20,10);
		slider_accserrory.setMajorTickSpacing(5);
		slider_accserrory.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				panel_accserrory.setlength(slider_accserrory.getValue());	
				int value = ((JSlider)e.getSource()).getValue();
				f.time_accessory = value;
			}		
		});
		
		
		MyPanel panel_bodies;
		JSlider slider_bodies;
		
		panel_bodies = new MyPanel();
		slider_bodies = new JSlider(SwingConstants.HORIZONTAL,0,20,10);
		slider_bodies.setMajorTickSpacing(5);
		slider_bodies.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				panel_bodies.setlength(slider_bodies.getValue());	
				int value = ((JSlider)e.getSource()).getValue();
				f.bodywork.time = value;
			}		
		});
		
		MyPanel panel_engine;
		JSlider slider_engine;
		
		panel_engine = new MyPanel();
		slider_engine = new JSlider(SwingConstants.HORIZONTAL,0,20,10);
		slider_engine.setMajorTickSpacing(5);
		slider_engine.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				panel_engine.setlength(slider_engine.getValue());		
				int value = ((JSlider)e.getSource()).getValue();
				f.engine.time = value;
			}		
		});
		

		MyPanel panel_cars;
		JSlider slider_cars;
		
		panel_cars = new MyPanel();
		slider_cars = new JSlider(SwingConstants.HORIZONTAL,0,20,10);
		slider_cars.setMajorTickSpacing(5);
		
		slider_cars.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				panel_cars.setlength(slider_cars.getValue());	
				int value = ((JSlider)e.getSource()).getValue();
				f.time_dealer = value;
			}		
		});
		
		JFrame frame = new JFrame("Interface");
					
		frame.setSize(400,400);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridBagLayout());
		
		JLabel accessories_store = new JLabel("Количество аксессуаров на складе");
		JLabel accessories = new JLabel("Количество произведенных аксессуаров");	
		JLabel bodies_store = new JLabel("Количество кузовов на складе");
		JLabel bodies= new JLabel("Количество произведенных кузовов");
		JLabel engine_store = new JLabel("Количество двигателей на складе");
		JLabel engine = new JLabel("Количество произведенных  двигателей ");
		JLabel cars_store = new JLabel("Количество автомобилей на складе");
		JLabel cars = new JLabel("Количество произведенных  автомобилей ");

		
		frame.add(accessories_store, new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));

		frame.add(accessories, new GridBagConstraints(0,1,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		frame.add(bodies_store, new GridBagConstraints(0,2,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		frame.add(bodies, new GridBagConstraints(0,3,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));

		frame.add(engine_store, new GridBagConstraints(0,4,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		frame.add(engine, new GridBagConstraints(0,5,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		frame.add(cars_store, new GridBagConstraints(0,6,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		frame.add(cars, new GridBagConstraints(0,7,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		//
		

		frame.add(accessories_store_field, new GridBagConstraints(1,0,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));

		frame.add(accessories_field, new GridBagConstraints(1,1,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		frame.add(bodies_store_field, new GridBagConstraints(1,2,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		frame.add(bodies_field, new GridBagConstraints(1,3,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));

		frame.add(engine_store_field, new GridBagConstraints(1,4,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		frame.add(engine_field, new GridBagConstraints(1,5,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		frame.add(cars_store_field, new GridBagConstraints(1,6,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		frame.add(cars_field, new GridBagConstraints(1,7,1,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		//
		
		frame.add(slider_accserrory, new GridBagConstraints(2,1,0,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		frame.add(slider_bodies, new GridBagConstraints(2,3,0,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		frame.add(slider_engine, new GridBagConstraints(2,5,0,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));
		
		frame.add(slider_cars, new GridBagConstraints(2,7,0,1,1,1,GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2,2,2,2),0,0));


		frame.setVisible(true);
	}

	
    public ActionListener listener = new ActionListener() 
    {
		@Override
		public void actionPerformed(ActionEvent arg0) {			
		{   
			accessories_store_field.setText(String.valueOf(f.store2.count_current)); 
			bodies_store_field.setText(String.valueOf(f.store3.count_current));
			engine_store_field.setText(String.valueOf(f.store4.count_current));
			cars_store_field.setText(String.valueOf(f.store1.count_current));
		}
		}
    };
 }


