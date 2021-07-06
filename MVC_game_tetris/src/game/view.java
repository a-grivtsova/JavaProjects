package game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class view extends javax.swing.JFrame{
	 		
		public TestPane d;
		int width = 10;
	    int height = 20;

	    public model m;
	    public controller c;

		int time;
	    
	    CellPane[][] cells = new CellPane[height][width];


		public void start_game()
		{
		     Timer t = new Timer(time, this.listener);
			 t.start(); 
		}
		
		public view this_view()
		{
			return this;
		
		}
		
	    public view(model m1, controller c1, int t) {
	    	
	    	this.m = m1;
	    	this.c = c1;
	    	this.time = t;    	
	    	
	       // EventQueue.invokeLater(new Runnable() {
	           // @Override
	           // public void run() {
	              //  try {
	              //      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	             //   } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	             //   }
	                JFrame frame = new JFrame("Testing");
	               // frame.addKeyListener((KeyListener) c);//?
	                frame.addKeyListener(new KeyAdapter() {
	                    
	                    public void keyPressed(KeyEvent a) 
	                    {
	                    	if((a.getKeyCode() == 39)||(a.getKeyCode() == 38)||(a.getKeyCode() == 37)||(a.getKeyCode() == 40)||(a.getKeyCode() == 32))
	            			{
	            				c.commands.add(a.getKeyCode());             				
	            			}	                    	
	                    }	                             
	                });	               
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
	                    			new_game();
	                    	}
	                    
	                    });	
	                frame.setJMenuBar(menubar);              
	                //
	                d = new TestPane();
	                frame.add(d);
	                frame.pack(); // автоматически настраиваем размер окна под содержимое
	                frame.setLocationRelativeTo(null); // центрирование окна 
	                frame.setVisible(true);
	         //   }
	       // });
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

	    
	    public void new_game()
		{
			for (int row = 0; row < 20; row++) //нет фигур на дне
	        {
		         for (int col = 0; col < 10; col++)
		         {
		        	 draw_cell(row, col, false);
		        	 m.cells[row][col] = false;
		         }
	        }
			
			c.commands.clear(); //очищаем массив команд
			
			m.type = 0;
			m.angle = 0;
			m.high_scores = 0;
			
			m.state = true;
			m.state_end = true;
			m.flag = 0;
			m.num = 1;
			
			m.figure_current[0].coordinate_setting(0, 0); 
			m.figure_current[1].coordinate_setting(0, 0); 
			m.figure_current[2].coordinate_setting(0, 0); 
			m.figure_current[3].coordinate_setting(0, 0); 
			
			m.figure_future[0].coordinate_setting(0, 0); 
			m.figure_future[1].coordinate_setting(0, 0); 
			m.figure_future[2].coordinate_setting(0, 0); 
			m.figure_future[3].coordinate_setting(0, 0); 
			
			m.figure_initialization(m.type);
		}
		
	    public void move_down()
		{
			if(m.state)
			{
				draw_figure();
				for(int i = 0; i < 4 ; i++)
				{
					m.figure_future[i].y = m.figure_current[i].y;
					m.figure_future[i].x = m.figure_current[i].x + 1;
				}
				
				if(m.position_check())
				{
					clean_figure();
					for(int i = 0; i < 4 ; i++)
					{
						m.figure_current[i].y = m.figure_future[i].y;
						m.figure_current[i].x = m.figure_future[i].x;
					}
					draw_figure();
					redrawing_field(); 
				}
				else
				{
					for(int i = 0; i < 4 ; i++)
					{
						m.cells[m.figure_current[i].x][m.figure_current[i].y] = true;				
					}
					m.creation_new_figure();
					draw_figure();
				}
			}
		}
		
		public void redrawing_field()
		{
			int num;
			
			for(int t = 19; t >= 0; t--)
			{
				num = 0;
				for(int i = 0; i < 10; i++)
				{
					if(m.cells[t][i])
					{
						num++;
					}
				}
				if(num == 10)//число закрашеных клеточек в строке равно 10(с нуля)
				{
					for(int r = t; r > 0; r--)
					{
						for(int f = 0; f < 10; f++)
						{
							m.cells[r][f] = m.cells[r-1][f];
							
							draw_cell(r, f, m.cells[r][f]);
						}
					}
					t++;
					m.high_scores = m.high_scores + 10;
				}
			}
		}

		public void step_falling_cube()
		{
			long ctime = System.currentTimeMillis(); 
			
			if(! m.end_check())
				m.state_end = false; // конец игры
			
			//цикл обраб команд
			for(; c.commands.size() > 0;)
			{	
				draw_figure();		//проверяем список команд
				
				if((int)c.commands.get(0) == 37)
				{
					m.move_left();
					c.commands.remove(0);
					if(m.position_check())
					{
						clean_figure();
						for(int i = 0; i < 4 ; i++)
						{
							m.figure_current[i].y = m.figure_future[i].y;
							m.figure_current[i].x = m.figure_future[i].x;
						}
						draw_figure();
					}
				}
				else if((int)c.commands.get(0) == 39)
				{
					m.move_right();
					c.commands.remove(0);
					if(m.position_check())
					{
						clean_figure();
						for(int i = 0; i < 4 ; i++)
						{
							m.figure_current[i].y = m.figure_future[i].y;
							m.figure_current[i].x = m.figure_future[i].x;
						}
						draw_figure();
					} 
				}
				else if((int)c.commands.get(0) == 40)
				{
					c.commands.remove(0);
					if(m.state)
					{
						for(;m.position_check();)
						{
							for(int i = 0; i < 4 ; i++)
							{
								m.figure_future[i].y = m.figure_current[i].y;
								m.figure_future[i].x = m.figure_current[i].x + 1;
							}
							
							if(m.position_check())
							{
								clean_figure();
								for(int i = 0; i < 4 ; i++)
								{
									m.figure_current[i].y = m.figure_future[i].y;
									m.figure_current[i].x = m.figure_future[i].x;
								}
								draw_figure();
							}
							else
							{
								for(int i = 0; i < 4 ; i++)
								{
									m.cells[m.figure_current[i].x][m.figure_current[i].y] = true;				
								}
								m.creation_new_figure();
								draw_figure();
								m.ptime = ctime;
								return;
							}
						}
					}
				}			
				else if((int)c.commands.get(0) == 38)
				{
					c.commands.remove(0);

					for(int i = 0; i < 4 ; i++)
					{
						m.figure_future[i].x = m.figure_current[i].x + m.matrix[m.type][m.angle][i].x;
						m.figure_future[i].y = m.figure_current[i].y + m.matrix[m.type][m.angle][i].y;
					}	
					
					 m.check_rotation();
					
					if(m.position_check())
					{
						if(m.angle < 3)
							m.angle++;
						else
							m.angle = 0;
		
						clean_figure();
						for(int i = 0; i < 4 ; i++)
						{
							m.figure_current[i].y = m.figure_future[i].y;
							m.figure_current[i].x = m.figure_future[i].x;
						}
						draw_figure();
					}
				}
				else if((int)c.commands.get(0) == 32)
				{
					if(m.num == 1)
					{
						m.state = false;  //пауза
						m.num++;
						c.commands.remove(0);
					}
					else
					{
						m.state = true;  //не пауза
						m.num = 1;
						c.commands.remove(0);
					}
				}

				else 
				{
					c.commands.remove(0);
				}
			}
			if (ctime - m.ptime >= m.time_falling_cube)
			{		
				move_down();
				m.ptime = ctime;
			}
		} 	

	    public void model_timer_worker()
	    {
	    		step_falling_cube();
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
