package TEST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import hhg.INIT;
import hhg.MOVE;
import hhg.TELEPORT;
import hhg.DRAW;
import pac.Canvas;

public class JUnit 
{
	@Test
	public void test_Teleport() //throws IOException
	{
		TELEPORT t = new TELEPORT();
		Canvas c = new Canvas();
		try {
			t.execution(c, "-3 4");
		}
		catch(Exception e) 
		{
		    System.out.print("ERROR"); 
		}

	}
	
	@Test
	public void test_Move()
	{
		MOVE m = new MOVE();
		INIT i = new INIT();
		Canvas c = new Canvas();
		i.execution(c, "10 10 0 0");
		m.execution(c, "D 3");
		assertEquals(c.X, 3);
		assertEquals(c.Y, 0);
	}
	
	@Test
	public void test_Draw()
	{
		DRAW d = new DRAW();
		Canvas c = new Canvas();
		
		d.execution(c, "DRAW");
		assertEquals(c.drawing_mode, "DRAW");
	}
	
	@Test
	public void test_Init()
	{
		INIT i = new INIT();
		Canvas c = new Canvas();
		i.execution(c, "10 10 6 0");
		assertEquals(c.X, 6);
		assertEquals(c.Y, 0);
	}
}
