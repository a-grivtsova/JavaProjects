package game;
import java.util.concurrent.TimeUnit;
import game.view.CellPane;

public class model 
{
		long ptime = 0;
		
		cell [] figure_current = new cell [4]; //массив для клеток текущей фигуры
		cell [] figure_future = new cell [4];  //массив для клеток будующей фигуры
		
	    public boolean[][] cells = new boolean[20][10]; //массив для занятых клеток
	    
	    int type; //тип фигуры
	    int angle; //угол поворота
	    
	    int num = 1;
	    int flag = 0;
	    int new_game = 0;
	    
	    int high_scores = 0;
	    
	    boolean state = true; //состояние игры(пауза, конец..)
	    boolean state_end = true;
	    
	    cell [][][] matrix = new cell[][][] 
	    {
	    //фигура //тип - куб
	    	{//угол
	    		{//cellы
	    			new cell(0,0), new cell(0,0), new cell(0,0),new cell(0,0)
	    		},
	    		{
	    			new cell(0,0), new cell(0,0), new cell(0,0),new cell(0,0)
	    		},
	    		{
	    			new cell(0,0), new cell(0,0), new cell(0,0),new cell(0,0)
	    		},
	    		{
	    			new cell(0,0), new cell(0,0), new cell(0,0),new cell(0,0)
	    		}  		
	    	},
	    	
	    	
	    	//фигура //тип - I
	    	{//угол
	    		{//cellы
	    			new cell(-3,1), new cell(-2,0), new cell(-1,-1),new cell(0,-2)
	    		},
	    		{
	    			new cell(3,-1), new cell(2,0), new cell(1,1),new cell(0,2)
	    		},
	    		{
	    			new cell(-3,1), new cell(-2,0), new cell(-1,-1),new cell(0,-2)
	    		},
	    		{
	    			new cell(3,-1), new cell(2,0), new cell(1,1),new cell(0,2)
	    		}  		
	    	},
	    	
	    	
	    	//фигура//тип - T
	    	{//угол
	    		{//cellы
	    			new cell(0,2), new cell(-2,2), new cell(-1,1),new cell(0,0)
	    		},
	    		{
	    			new cell(1,0), new cell(1,2), new cell(0,1),new cell(-1,0)
	    		},
	    		{
	    			new cell(-1,0), new cell(1,0), new cell(0,1),new cell(-1,2)
	    		},
	    		{
	    			new cell(0,2), new cell(0,0), new cell(1,1),new cell(2,2)
	    		}  		
	    	},
	    	
	    	
	    	//фигура//тип -L1
	    	{//угол
	    		{//cellы
	    			new cell(-1,2), new cell(-2,1), new cell(-1,0),new cell(0,-1)
	    		},
	    		{
	    			new cell(2,0), new cell(1,1), new cell(0,0),new cell(-1,-1)
	    		},
	    		{
	    			new cell(0,-2), new cell(1,-1), new cell(0,0),new cell(-1,1)
	    		},
	    		{
	    			new cell(-1,1), new cell(0,0), new cell(1,1),new cell(2,2)
	    		}  		
	    	},

	    	//фигура//тип -L2
	    	{//угол
	    		{//cellы
	    			new cell(1,0), new cell(-2,1), new cell(-1,0),new cell(0,-1)
	    		},
	    		{
	    			new cell(0,-2), new cell(1,1), new cell(0,0),new cell(-1,-1)
	    		},
	    		{
	    			new cell(-2,0), new cell(1,-1), new cell(0,0),new cell(-1,1)
	    		},
	    		{
	    			new cell(1,2), new cell(0,-1), new cell(1,0),new cell(2,1)
	    		}  		
	    	},
	    	
	    	//фигура//тип -Z1
	    	{//угол
	    		{//cellы
	    			new cell(-1,1), new cell(0,0), new cell(-1,-1),new cell(0,-2)
	    		},
	    		{
	    			new cell(2,1), new cell(1,0), new cell(0,1),new cell(-1,0)
	    		},
	    		{
	    			new cell(0,-2), new cell(-1,-1), new cell(0,0),new cell(-1,1)
	    		},
	    		{
	    			new cell(-1,0), new cell(0,1), new cell(1,0),new cell(2,1)
	    		}  		
	    	},
	    	
	    	
	    	//фигура//тип -Z2
	    	{//угол
	    		{//cellы
	    			new cell(0,0), new cell(1,-1), new cell(-2,0),new cell(-1,-1)
	    		},
	    		{
	    			new cell(1,0), new cell(0,-1), new cell(1,2),new cell(0,1)
	    		},
	    		{
	    			new cell(-1,-1), new cell(-2,0), new cell(1,-1),new cell(0,0)
	    		},
	    		{
	    			new cell(0,1), new cell(1,2), new cell(0,-1),new cell(1,0)
	    		}  		
	    	}
		
	    };

		public model()
		{
			figure_current[0] = new cell(0,0);
			figure_current[1] = new cell(0,0);
			figure_current[2] = new cell(0,0);
			figure_current[3] = new cell(0,0);
			
			figure_future[0] = new cell(0,0);
			figure_future[1] = new cell(0,0);
			figure_future[2] = new cell(0,0);
			figure_future[3] = new cell(0,0);
			
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
	}
