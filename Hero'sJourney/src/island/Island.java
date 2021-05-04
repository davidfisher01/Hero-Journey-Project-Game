package island;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Island extends JPanel implements ActionListener, KeyListener, MouseListener {
	
	//handles drawing animation
	Timer animationTimer;
	
	IslandBackground i;
	Protagonist p;
	
	public int x, y;
	public int vx, vy;
	
	public void paint(Graphics g) {
		//calling this line ensures the frame is redrawn
		super.paintComponent(g);
		
		updateBackground();
		
		//call paint methods of objects or through g.drawRect etc
		i.paint(g);
		p.paint(g);
		
		i.setVx(vx);
		i.setVy(vy);
	}
	
	/* constructor for MainPain class */
	public Island() {
		
		//Create a JFrame Object with a title bar text
		JFrame f = new JFrame("Dino Crossing");
		
		//Set the size of the window
		f.setSize(800,600); //width and height
		

		f.addKeyListener(this);
		
		//f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//f.setUndecorated(false);
		
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
		
		f.setVisible(true);
			
		//create all instnace variable objects in the constructorr
		p = new Protagonist("bronc.png", f.getWidth()/2, f.getHeight()/2, 50, 50);
		i = new IslandBackground("testIsland.png", f.getWidth()*2, f.getHeight()*2);
	}
	
	public void updateBackground() {
		x += vx;
		y += vy;
	}

	/* this method is invoked/called by the titmer */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//call the frame to refresh
		//recall that actitonPerformed is called by the
		//timer object every 16ms
		repaint();
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 83 || e.getKeyCode() == 40) {
			System.out.println("s");
			vy = -5;
		}
		if (e.getKeyCode() == 87 || e.getKeyCode() == 38) {
			System.out.println("n");
			vy = 5;
		}
		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
			System.out.println("e");
			vx = -5;
		}
		if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
			System.out.println("w");
			vx = 5;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 83 || e.getKeyCode() == 40) {
			System.out.println("s released");
			vy = 0;
		}
		if (e.getKeyCode() == 87 || e.getKeyCode() == 38) {
			System.out.println("n released");
			vy = 0;
		}
		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
			System.out.println("e released");
			vx = 0;
		}
		if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
			System.out.println("w released");
			vx = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}