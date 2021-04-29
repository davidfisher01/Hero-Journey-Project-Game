package island;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Island extends JPanel implements ActionListener{
	
	//handles drawing animation
	Timer animationTimer;
	Protagonist p;
	
	public void paint(Graphics g) {
		//calling this line ensures the frame is redrawn
		super.paintComponent(g);
		
		//call paint methods of objects or through g.drawRect etc
		p.paint(g);
	}
	
	/* constructor for MainPain class */
	public Island() {
		
		//Create a JFrame Object with a title bar text
		JFrame f = new JFrame("Example Drawing");
		
		//Set the size of the window
		f.setSize(800,600); //width and height
		
		//set default action for x button
		//without this, your code will run behind the scenes until
		//you force exit
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add this panel to the JFrame
		//allows connection with "drawing"
		f.add(this);
		
		//setup animation timer
		animationTimer = new Timer(16, this);
		
		//do not forget to start the timer
		animationTimer.start();
			
		//create all instnace variable objects in the constructorr
		p = new Protagonist("bronc.png", 50, 50);
		
		f.setVisible(true);
	}

	/* this method is invoked/called by the titmer */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//call the frame to refresh
		//recall that actitonPerformed is called by the
		//timer object every 16ms
		repaint();
		
	}
}