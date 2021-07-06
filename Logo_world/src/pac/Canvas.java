package pac;

public class Canvas 
{
	public char [][] arr;
	
	public int width;
	public int height;
	
	public int X;
	public int Y;
	
	public String drawing_mode;//режим рисования Draw или Ward
	
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(Canvas.class != obj.getClass())
		{
			return false;
		}
		if( obj instanceof Canvas)
		{
			
		}
		Canvas ff = (Canvas) obj;
		return ff.equals(obj);		
	}
	
	public Canvas()
	{
		this.arr = null;
		
		this.height = 0;
		this.width = 0;
		
		this.X = 0;
		this.Y = 0;		
		
		this.drawing_mode = "WARD";
	}
	
	public void draw_field()//вывод массива на экран
	{
		for (int i = 0; i <= width; i++) {
			for (int j = 0; j < height; j++) {
				if (i == 0)
				{
					if (j == 0)
					{
						System.out.print(' ');
					}
					System.out.print(j);
				}
				if (i > 0)
				{
					if (j == 0)
					{
						if (i == (height - 1))
							System.out.print(height - 2);
						else
							System.out.print(i - 1);
					}
					System.out.print(arr[i - 1][j]) ;
				}
			}
			System.out.print("\n");
		}
	}
	
}
