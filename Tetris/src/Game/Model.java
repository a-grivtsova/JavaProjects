package Game;
import java.util.concurrent.TimeUnit;
import Game.View.CellPane;

public class Model 
{	
	Control b ;
	long ptime = 0;
	
	Cell [] figure_current = new Cell [4]; //массив для клеток текущей фигуры
	Cell [] figure_future = new Cell [4];  //массив для клеток будующей фигуры
	
    boolean[][] cells = new boolean[20][10]; //массив для занятых клеток
    
    int type; //тип фигуры
    int angle; //угол поворота
    
    int num = 1;
    int flag = 0;
    int new_game = 0;
    
    int high_scores = 0;
    
    boolean state = true; //состояние игры(пауза, конец..)
    boolean state_end = true;
    
    
	public void new_game(View v)
	{
		for (int row = 0; row < 20; row++) //нет фигур на дне
        {
	         for (int col = 0; col < 10; col++)
	         {
	        	 v.draw_cell(row, col, false);
	        	 cells[row][col] = false;
	         }
        }
		
		b.commands.clear(); //очищаем массив команд
		
		type = 0;
		angle = 0;
		high_scores = 0;
		
		state = true;
		state_end = true;
		flag = 0;
		num = 1;
		
		figure_current[0].coordinate_setting(0, 0); 
		figure_current[1].coordinate_setting(0, 0); 
		figure_current[2].coordinate_setting(0, 0); 
		figure_current[3].coordinate_setting(0, 0); 
		
		figure_future[0].coordinate_setting(0, 0); 
		figure_future[1].coordinate_setting(0, 0); 
		figure_future[2].coordinate_setting(0, 0); 
		figure_future[3].coordinate_setting(0, 0); 
		
		figure_initialization(type);
	}
	
	
    Cell [][][] matrix = new Cell[][][] 
    {
    //фигура //тип - куб
    	{//угол
    		{//cellы
    			new Cell(0,0), new Cell(0,0), new Cell(0,0),new Cell(0,0)
    		},
    		{
    			new Cell(0,0), new Cell(0,0), new Cell(0,0),new Cell(0,0)
    		},
    		{
    			new Cell(0,0), new Cell(0,0), new Cell(0,0),new Cell(0,0)
    		},
    		{
    			new Cell(0,0), new Cell(0,0), new Cell(0,0),new Cell(0,0)
    		}  		
    	},
    	
    	
    	//фигура //тип - I
    	{//угол
    		{//cellы
    			new Cell(-3,1), new Cell(-2,0), new Cell(-1,-1),new Cell(0,-2)
    		},
    		{
    			new Cell(3,-1), new Cell(2,0), new Cell(1,1),new Cell(0,2)
    		},
    		{
    			new Cell(-3,1), new Cell(-2,0), new Cell(-1,-1),new Cell(0,-2)
    		},
    		{
    			new Cell(3,-1), new Cell(2,0), new Cell(1,1),new Cell(0,2)
    		}  		
    	},
    	
    	
    	//фигура//тип - T
    	{//угол
    		{//cellы
    			new Cell(0,2), new Cell(-2,2), new Cell(-1,1),new Cell(0,0)
    		},
    		{
    			new Cell(1,0), new Cell(1,2), new Cell(0,1),new Cell(-1,0)
    		},
    		{
    			new Cell(-1,0), new Cell(1,0), new Cell(0,1),new Cell(-1,2)
    		},
    		{
    			new Cell(0,2), new Cell(0,0), new Cell(1,1),new Cell(2,2)
    		}  		
    	},
    	
    	
    	//фигура//тип -L1
    	{//угол
    		{//cellы
    			new Cell(-1,2), new Cell(-2,1), new Cell(-1,0),new Cell(0,-1)
    		},
    		{
    			new Cell(2,0), new Cell(1,1), new Cell(0,0),new Cell(-1,-1)
    		},
    		{
    			new Cell(0,-2), new Cell(1,-1), new Cell(0,0),new Cell(-1,1)
    		},
    		{
    			new Cell(-1,1), new Cell(0,0), new Cell(1,1),new Cell(2,2)
    		}  		
    	},

    	//фигура//тип -L2
    	{//угол
    		{//cellы
    			new Cell(1,0), new Cell(-2,1), new Cell(-1,0),new Cell(0,-1)
    		},
    		{
    			new Cell(0,-2), new Cell(1,1), new Cell(0,0),new Cell(-1,-1)
    		},
    		{
    			new Cell(-2,0), new Cell(1,-1), new Cell(0,0),new Cell(-1,1)
    		},
    		{
    			new Cell(1,2), new Cell(0,-1), new Cell(1,0),new Cell(2,1)
    		}  		
    	},
    	
    	//фигура//тип -Z1
    	{//угол
    		{//cellы
    			new Cell(-1,1), new Cell(0,0), new Cell(-1,-1),new Cell(0,-2)
    		},
    		{
    			new Cell(2,1), new Cell(1,0), new Cell(0,1),new Cell(-1,0)
    		},
    		{
    			new Cell(0,-2), new Cell(-1,-1), new Cell(0,0),new Cell(-1,1)
    		},
    		{
    			new Cell(-1,0), new Cell(0,1), new Cell(1,0),new Cell(2,1)
    		}  		
    	},
    	
    	
    	//фигура//тип -Z2
    	{//угол
    		{//cellы
    			new Cell(0,0), new Cell(1,-1), new Cell(-2,0),new Cell(-1,-1)
    		},
    		{
    			new Cell(1,0), new Cell(0,-1), new Cell(1,2),new Cell(0,1)
    		},
    		{
    			new Cell(-1,-1), new Cell(-2,0), new Cell(1,-1),new Cell(0,0)
    		},
    		{
    			new Cell(0,1), new Cell(1,2), new Cell(0,-1),new Cell(1,0)
    		}  		
    	}
	
    };

	public Model(Control c)
	{
		this.b = c;
		
		figure_current[0] = new Cell(0,0);
		figure_current[1] = new Cell(0,0);
		figure_current[2] = new Cell(0,0);
		figure_current[3] = new Cell(0,0);
		
		figure_future[0] = new Cell(0,0);
		figure_future[1] = new Cell(0,0);
		figure_future[2] = new Cell(0,0);
		figure_future[3] = new Cell(0,0);
		
		figure_initialization(0);
		
		for (int row = 0; row < 20; row++)
        {
	         for (int col = 0; col < 10; col++)
	         {
	        	 cells[row][col] = false;
	         }
        }
	}
	
	int w = 20;
    int h = 2;
    int i = 0;

	int time_falling_cube = 500; // 1 клетка в  секунду
		
	public void figure_initialization(int type) //заполнение текущих координат фигуры в зависимости от ее типа
	{
		if(type == 0)//тип - куб
		{ 
			figure_current[0].coordinate_setting(0,5); 
			figure_current[1].coordinate_setting(0,6); 
			figure_current[2].coordinate_setting(1,5); 
			figure_current[3].coordinate_setting(1,6); 

		}
		if(type == 1)//тип - I
		{
			figure_current[0].coordinate_setting(0,5); 
			figure_current[1].coordinate_setting(0,6); 
			figure_current[2].coordinate_setting(0,7); 
			figure_current[3].coordinate_setting(0,8); 
		}
		if(type == 2)//тип - T
		{
			figure_current[0].coordinate_setting(0,6); 
			figure_current[1].coordinate_setting(1,5); 
			figure_current[2].coordinate_setting(1,6); 
			figure_current[3].coordinate_setting(1,7); 
		}
		if(type == 3)//тип -L1
		{
			figure_current[0].coordinate_setting(0,5); 
			figure_current[1].coordinate_setting(1,5); 
			figure_current[2].coordinate_setting(1,6); 
			figure_current[3].coordinate_setting(1,7); 
		}
		if(type == 4)//тип -L2
		{
			figure_current[0].coordinate_setting(0,7); 
			figure_current[1].coordinate_setting(1,5); 
			figure_current[2].coordinate_setting(1,6); 
			figure_current[3].coordinate_setting(1,7); 
		}
		if(type == 5)//тип -Z1
		{
			figure_current[0].coordinate_setting(0,5); 
			figure_current[1].coordinate_setting(0,6); 
			figure_current[2].coordinate_setting(1,6); 
			figure_current[3].coordinate_setting(1,7); 
		}
		if(type == 6)//тип -Z2
		{
			figure_current[0].coordinate_setting(0,6); 
			figure_current[1].coordinate_setting(0,7); 
			figure_current[2].coordinate_setting(1,5); 
			figure_current[3].coordinate_setting(1,6); 
		}
	}
	
	public void creation_new_figure()
	{
		int a = 0;
		int b = 6;
		int random_number = a + (int) (Math.random() * b);	
		
		figure_initialization(random_number);	
		type = random_number;
		angle = 0;
	}
	
	public boolean position_check()
	{
		for(int i = 0; i < 4; i++)
		{
			if(((figure_future[i].y < 0)||(figure_future[i].y >= 10)||(figure_future[i].x >= 20)))
				return false;
		}		
		
		for (int i = 0; i < 4; i++)
        {
			if(cells[figure_future[i].x][figure_future[i].y])
				return false;
        }		
		return true;
	}
	
	public void move_left()
	{
		if(state)
		{
			for(int i = 0; i < 4 ; i++)
			{
				figure_future[i].x = figure_current[i].x;
				figure_future[i].y = figure_current[i].y - 1;
			}
		}
	}
	
	public void move_right()
	{
		if(state)
		{
			for(int i = 0; i < 4 ; i++)
			{
				figure_future[i].x = figure_current[i].x;
				figure_future[i].y = figure_current[i].y + 1;
			}
		}
	}
	
	public void move_down(View v)
	{
		if(state)
		{
			v.draw_figure();
			for(int i = 0; i < 4 ; i++)
			{
				figure_future[i].y = figure_current[i].y;
				figure_future[i].x = figure_current[i].x + 1;
			}
			
			if(position_check())
			{
				v.clean_figure();
				for(int i = 0; i < 4 ; i++)
				{
					figure_current[i].y = figure_future[i].y;
					figure_current[i].x = figure_future[i].x;
				}
				v.draw_figure();
				redrawing_field(v); 
			}
			else
			{
				for(int i = 0; i < 4 ; i++)
				{
					cells[figure_current[i].x][figure_current[i].y] = true;				
				}
				creation_new_figure();
				v.draw_figure();
			}
		}
	}
	
	public void redrawing_field(View v)
	{
		int num;
		
		for(int t = 19; t >= 0; t--)
		{
			num = 0;
			for(int i = 0; i < 10; i++)
			{
				if(cells[t][i])
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
						cells[r][f] = cells[r-1][f];
						
						v.draw_cell(r, f, cells[r][f]);
					}
				}
				t++;
				high_scores = high_scores + 10;
			}
		}
	}
		

	public boolean check_rotation()
	{
		int bias_x = 0;
		int bias_y = 0;
		
		for(int i = 0; i < 4 ; i++) //находим максимальное смещение по х или у
		{
			if(figure_future[i].x < 0)
			{
				if(bias_x > figure_future[i].x)
					bias_x = figure_future[i].x; 
			}
			if(figure_future[i].x > 19)
			{
				if(bias_x < figure_future[i].x)
					bias_x = figure_future[i].x; 
			}
			if(figure_future[i].y < 0)
			{
				if(bias_y > figure_future[i].y)
					bias_y = figure_future[i].y; 
			}
			if(figure_future[i].y > 9)
			{
				if(bias_y < figure_future[i].y)
					bias_y = figure_future[i].y; 
			}
		}
		
		if(bias_x < 0)
		{
			for(int i = 0; i < 4 ; i++) //смещаем вправо
			{
				figure_future[i].x = figure_future[i].x + (- bias_x); 
				
			}
		}
		if(bias_x > 0)
		{
			for(int i = 0; i < 4 ; i++) //смещаем влево
			{
				figure_future[i].x = figure_future[i].x - bias_x; 
			}
		}
		if(bias_y < 0)
		{
			for(int i = 0; i < 4 ; i++) //смещаем вниз
			{
				figure_future[i].y = figure_future[i].y + (-bias_y); 
			}
		}
		if(bias_y > 0)
		{
			for(int i = 0; i < 4 ; i++) //смещаем вверх
			{
				figure_future[i].y = figure_future[i].y - bias_y; 
			}
		}
		
		return true;
	}
	
	public boolean end_check()
	{
		for(int i = 0; i < 10 ; i++) 
		{
			if((cells[0][i])&&(flag == 0))
			{
				flag++;
				return false;
			}
		}
		return true;
	}
	
 	public void step_falling_cube(View v)
	{
		long ctime = System.currentTimeMillis(); 
		
		if(! end_check())
			state_end = false; // конец игры
		
		//цикл обраб команд
		for(;b.commands.size() > 0;)
		{	
			v.draw_figure();		//проверяем список команд
			
			if((int)b.commands.get(0) == 37)
			{
				move_left();
				b.commands.remove(0);
				if(position_check())
				{
					v.clean_figure();
					for(int i = 0; i < 4 ; i++)
					{
						figure_current[i].y = figure_future[i].y;
						figure_current[i].x = figure_future[i].x;
					}
					v.draw_figure();
				}
			}
			else if((int)b.commands.get(0) == 39)
			{
				move_right();
				b.commands.remove(0);
				if(position_check())
				{
					v.clean_figure();
					for(int i = 0; i < 4 ; i++)
					{
						figure_current[i].y = figure_future[i].y;
						figure_current[i].x = figure_future[i].x;
					}
					v.draw_figure();
				} 
			}
			else if((int)b.commands.get(0) == 40)
			{
				b.commands.remove(0);
				if(state)
				{
					for(;position_check();)
					{
						for(int i = 0; i < 4 ; i++)
						{
							figure_future[i].y = figure_current[i].y;
							figure_future[i].x = figure_current[i].x + 1;
						}
						
						if(position_check())
						{
							v.clean_figure();
							for(int i = 0; i < 4 ; i++)
							{
								figure_current[i].y = figure_future[i].y;
								figure_current[i].x = figure_future[i].x;
							}
							v.draw_figure();
						}
						else
						{
							for(int i = 0; i < 4 ; i++)
							{
								cells[figure_current[i].x][figure_current[i].y] = true;				
							}
							creation_new_figure();
							v.draw_figure();
							ptime = ctime;
							return;
						}
					}
				}
			}			
			else if((int)b.commands.get(0) == 38)
			{
				b.commands.remove(0);

				for(int i = 0; i < 4 ; i++)
				{
					figure_future[i].x = figure_current[i].x + matrix[type][angle][i].x;
					figure_future[i].y = figure_current[i].y + matrix[type][angle][i].y;
				}	
				
				 check_rotation();
				
				if(position_check())
				{
					if(angle < 3)
						angle++;
					else
						angle = 0;
	
					v.clean_figure();
					for(int i = 0; i < 4 ; i++)
					{
						figure_current[i].y = figure_future[i].y;
						figure_current[i].x = figure_future[i].x;
					}
					v.draw_figure();
				}
			}
			else if((int)b.commands.get(0) == 32)
			{
				if(num == 1)
				{
					state = false;  //пауза
					num++;
					b.commands.remove(0);
				}
				else
				{
					state = true;  //не пауза
					num = 1;
					b.commands.remove(0);
				}
			}

			
			else 
			{
				b.commands.remove(0);
			}
		}
		if (ctime - ptime >= time_falling_cube)
		{		
			move_down(v);
			ptime = ctime;
		}
	} 	
}
 	
 	
 	
 	
 	
 	
 	
 	
 	