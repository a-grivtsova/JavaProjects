package Game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JComponent;


public class Control extends JComponent  implements KeyListener
{
	public ArrayList commands = new ArrayList();//сохраняем клавиши нажатые 

	public Control() 
	{
		addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent a) {//нажатие
		if((a.getKeyCode() == 39)||(a.getKeyCode() == 38)||(a.getKeyCode() == 37)||(a.getKeyCode() == 40)||(a.getKeyCode() == 32))
		{
			commands.add(a.getKeyCode()); 
		}
	}

	@Override
	public void keyReleased(KeyEvent t) 
	{

	}

	@Override
	public void keyTyped(KeyEvent a) {		
	}
		
}
