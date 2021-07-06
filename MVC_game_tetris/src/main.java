import javax.swing.Timer;
import game.controller;
import game.model;
import game.view;
import game.view.TestPane;
import javax.swing.JButton;
import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		
		model model = new model();
		controller c = new controller();
		view d = new view(model, c, 100);
	}
}
