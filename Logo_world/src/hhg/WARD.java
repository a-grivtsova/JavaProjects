package hhg;
import pac.Canvas;
import pac.Factory_object;

public class WARD implements Factory_object 
{
	
	public void execution(Canvas logo, String options)
	{
		logo.drawing_mode = "WARD";
	}
}
