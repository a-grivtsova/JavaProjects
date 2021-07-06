package hhg;
import pac.Canvas;
import pac.Factory_object;

public class DRAW implements Factory_object 
{
	
	public void execution(Canvas logo, String options)
	{
		logo.drawing_mode = "DRAW";
	}
	
}
