import javax.swing.Timer;

import Game.Control;
import Game.Model;
import Game.View;
import Game.View.TestPane;

import javax.swing.JButton;
import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		Control c = new Control();
		Model model = new Model(c);
		View d = new View(model, c, 100);
	} 
}
   
