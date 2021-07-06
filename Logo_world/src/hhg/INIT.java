package hhg;
import pac.Canvas;
import pac.Factory_object;

public class INIT implements Factory_object 
{
	int height;
	int width;
	int X;
	int Y;
	
	public void execution(Canvas logo, String options) // создание поля и задание координаты
	{
		String f [] = new String [4];
		f = options.split(" ");
		
		if(f.length == 4)
		{
			height = Integer.parseInt(f[0]);
			width = Integer.parseInt(f[1]);
			X = Integer.parseInt(f[2]);
			Y = Integer.parseInt(f[3]);
		}
		else
			System.out.println("Invalid command!!!");
		
		
		logo.height = height;
		logo.width = width;
		logo.X = X;
		logo.Y = Y;
		logo.arr = new char [width][height]; //определили массив
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				logo.arr[i][j] = ' '; //заполняем пробелами
			}
		}
		
		logo.arr[X][Y] = '*';
		logo.draw_field();
	}

}
