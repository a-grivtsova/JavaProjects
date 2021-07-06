package hhg;

import pac.Canvas;
import pac.Factory_object;

public class MOVE implements Factory_object 
{
	String move;
	int steps;
	
	public void execution(Canvas logo, String options)//движение на N шагов
	{
		String f [] = new String [2];
		f = options.split(" ");
		
		
		if(f.length == 2)
		{
			move = f[0];
			steps = Integer.parseInt(f[1]);	
		}
		else
			System.out.println("Invalid command!!!");	
		
		System.out.println("------------");	
		System.out.println(move);	
		System.out.println(steps);
		do
		{
			if(move.equals("L"))
			{					
				if(logo.drawing_mode.equals("WARD"))
				{
					logo.arr[logo.X][logo.Y] = ' ';
				}
				if(logo.Y > 0)
				{
					--logo.Y;
					logo.arr[logo.X][logo.Y] = '*';	
				}
				else
				{
					logo.Y = (logo.width - 1);
					logo.arr[logo.X][logo.Y] = '*';	
				}
				
			}
			else if(move.equals("R"))
			{		
	
				if(logo.drawing_mode.equals("WARD"))
				{
					logo.arr[logo.X][logo.Y] = ' ';
				}
				if((logo.Y + 1) < logo.width)
				{
					++logo.Y;
					logo.arr[logo.X][logo.Y] = '*';	
				}
				else
				{
					logo.Y = 0;
					logo.arr[logo.X][logo.Y] = '*';	
				}

			}
			else if(move.equals("U"))
			{	
				if(logo.drawing_mode.equals("WARD"))
				{
					logo.arr[logo.X][logo.Y] = ' ';
				}
				if(logo.X > 0)
				{
					--logo.X;
					logo.arr[logo.X][logo.Y] = '*';	
				}
				else
				{
					logo.X = (logo.height - 1);
					logo.arr[logo.X][logo.Y] = '*';	
				}
			}
			else if(move.equals("D"))
			{	
				if(logo.drawing_mode.equals("WARD"))
				{
					logo.arr[logo.X][logo.Y] = ' ';
				}
				if((logo.X + 1) < logo.height)
				{
					++logo.X;
					logo.arr[logo.X][logo.Y] = '*';	
				}
				else 
				{
					logo.X = 0;
					logo.arr[logo.X][logo.Y] = '*';	
				}
			}	
			--steps;
		}while (steps != 0);
		logo.draw_field();
	}
}
