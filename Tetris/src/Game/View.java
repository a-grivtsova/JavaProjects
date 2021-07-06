package Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class View extends javax.swing.JFrame{	 
	
	public TestPane d;
	int width = 10;
    int height = 20;

    public Model m;
    public Control c;

	int time;
    
    CellPane[][] cells = new CellPane[height][width];


	public void start_game()
	{
	     Timer t = new Timer(time, this.listener);
		 t.start(); 
	}
	
	public View this_view()
	{
		return this;
	
	}
	
    public View(Model m1, Control c1, int t) {
    	
    	this.m = m1;
    	this.c = c1;
    	this.time = t;
    	
    	
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
                JFrame frame = new JFrame("Testing");
                frame.addKeyListener(c);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout()); // добавляем менеджер BorderLayout
                
                //для меню
                JMenuBar menubar = new JMenuBar();
                
                JMenu menu1 = new JMenu("Exit");
                JMenuItem menuItem1 = new JMenuItem("exit");
                menu1.add(menuItem1);
                
                JMenu menu2 = new JMenu("About");
                JMenuItem menuItem2 = new JMenuItem("rules of the game");
                menu2.add(menuItem2);
                
                JMenu menu3 = new JMenu("New Game");
                JMenuItem menuItem3 = new JMenuItem("new game");
                menu3.add(menuItem3);
                
                JMenu menu4 = new JMenu("High Scores");
                JMenuItem menuItem4 = new JMenuItem("high scores");
                menu4.add(menuItem4);
                
                menubar.add(menu1);
                menubar.add(menu2);
                menubar.add(menu3);
                menubar.add(menu4);
                
                menuItem1.addActionListener(new ActionListener(){
                	
                	@Override
                	public void actionPerformed(ActionEvent event)
                	{
                		System.exit(1);
                	}
                
                });
                
                
                menuItem2.addActionListener(new ActionListener(){
                		
                	@Override
                	public void actionPerformed(ActionEvent event)
                	{
                	JOptionPane.showMessageDialog(frame,
                            new String[] {"Правила игры :",
                                          " - Случайные фигурки тетрамино падают сверху в прямоугольный стакан шириной 10 и высотой 20 клеток.",
                                          " - В полёте игрок может поворачивать фигурку на 90° и двигать её по горизонтали.",
                                          " - Также можно «сбрасывать» фигурку, то есть ускорять её падение, когда уже решено, куда фигурка должна упасть.",
                                          " - Фигурка летит до тех пор, пока не наткнётся на другую фигурку либо на дно стакана.",
                                          " - Если при этом заполнился горизонтальный ряд из 10 клеток, он пропадает и всё, что выше него, опускается на одну клетку.",
                                          " - Игра заканчивается, когда новая фигурка не может поместиться в стакан. ",
                                          " - Игрок получает 10 очков за каждый заполненный ряд, поэтому его задача — заполнять ряды,",
                                          "   не заполняя сам стакан (по вертикали) как можно дольше, чтобы таким образом получить как можно больше очков."},
                                          "About",
                                          JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                
                
                	menuItem4.addActionListener(new ActionListener(){
                	
                	@Override
                	public void actionPerformed(ActionEvent event)
                	{	
                		JOptionPane.showMessageDialog(frame,
                                new String[] {"Очки :",
                                		String.valueOf(m.high_scores)},
                                              "High_Scores",
                                              JOptionPane.INFORMATION_MESSAGE);
                	}
                });
               
                	
                	menuItem3.addActionListener(new ActionListener(){
                    	
                    	@Override
                    	public void actionPerformed(ActionEvent event)
                    	{
                    		if(m.new_game == 0)
                    		{
                    			start_game();
                    			m.new_game = 1;
                    		}
                    		else
                    			m.new_game(this_view());
                    	}
                    
                    });
                	
                frame.setJMenuBar(menubar);              
                //
                d = new TestPane();
                frame.add(d);
                frame.pack(); // автоматически настраиваем размер окна под содержимое
                frame.setLocationRelativeTo(null); // центрирование окна по центру
                frame.setVisible(true);
            }
        });
    }

	public class TestPane extends JPanel 
    {    
         public TestPane() 
         {
	         setLayout(new GridBagLayout());
	
	         GridBagConstraints gbc = new GridBagConstraints();	//создаем объект класса GridBagConstraints,
	            													//поля которого будут определять параметры
															//размещения отдельных компонент
	           
	         for (int row = 0; row < height; row++)
	         {
		         for (int col = 0; col < width; col++)
		         {
			         gbc.gridx = col;
			         gbc.gridy = row;
			
			         CellPane cellPane = new CellPane();
			         Border border = new MatteBorder(1, 1, (row == height ? 1 : 0), (col == width ? 1 : 0), Color.GRAY);
			
			         cellPane.setBorder(border);
			         this.add(cellPane, gbc);
			         cells[row][col] = cellPane;
		         }
	         }
        }     
    }

    public class CellPane extends JPanel {

        public CellPane() 
        {
        	setBackground(Color.PINK); 
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(30, 30);
        }
    }
    
    public void clean_figure()
    {
    	
    	cells[m.figure_current[0].x][m.figure_current[0].y].setBackground(Color.PINK);
		cells[m.figure_current[1].x][m.figure_current[1].y].setBackground(Color.PINK);
		cells[m.figure_current[2].x][m.figure_current[2].y].setBackground(Color.PINK);
		cells[m.figure_current[3].x][m.figure_current[3].y].setBackground(Color.PINK);
    	
    }
    
    public void draw_figure()
    {  	
    	cells[m.figure_current[0].x][m.figure_current[0].y].setBackground(Color.cyan);
		cells[m.figure_current[1].x][m.figure_current[1].y].setBackground(Color.cyan);
		cells[m.figure_current[2].x][m.figure_current[2].y].setBackground(Color.cyan);
		cells[m.figure_current[3].x][m.figure_current[3].y].setBackground(Color.cyan);  	
    }
    
    public void draw_cell(int x, int y, boolean ye)
    {
    	if(ye)
    	{
    		cells[x][y].setBackground(Color.cyan);
    	}
    	else
    	{
    		cells[x][y].setBackground(Color.PINK);
    	}
    }
    
    public void model_timer_worker()
    {
    		m.step_falling_cube(this);
    }
    
    public ActionListener listener = new ActionListener() 
    {
		@Override
		public void actionPerformed(ActionEvent arg0) {			
		{   
			if(m.state_end)
			{
				model_timer_worker();			
				if(m.flag == 1)
				{
					JOptionPane.showMessageDialog(null, "THE END!!!");
				}
			}
		}
		}
    };
 }


