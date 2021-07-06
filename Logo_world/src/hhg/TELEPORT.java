package hhg;
import java.io.IOException;

import pac.Canvas;
import pac.Factory_object;

public  class TELEPORT implements Factory_object 
{
	int x;
	int y;
	
	public void execution(Canvas logo, String options)   //перемещение в заданную точку без следов
	{
		String f [] = new String [2];
		f = options.split(" ");
			
		if(f.length == 2)
		{
			x = Integer.parseInt(f[0]);
			y = Integer.parseInt(f[1]);
		}
		else
			System.out.println("Invalid command!!!");

		if((x>0)&&(y>0))
			{
				logo.X = x;
				logo.Y = y;
			} 
		else
			throw new IllegalArgumentException("error command");
	}
}
