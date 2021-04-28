import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements MouseListener, ActionListener {
	
	public int vx, vy;	//global velocity
	public int x, y;
	
	//objects
	//Island i = new Island();
	Protagonist p;
	Font verdana = new Font("Verdana", Font.BOLD, 30);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		p.paint(g);
		
		//updateBackground(g, 100);
		
	}
	
	
	
	public Driver() {
		x = 0;
		y = 0;
		
		p = new Protagonist("bronc.png", 50, 50);
		
		JFrame f = new JFrame();
		f.setTitle("Dino Nuggets");
		f.setSize(500, 500);
		f.setResizable(false);
		f.addMouseListener(this);
		f.add(this);
		Timer t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	
	public static void main(String[] arg) {
		Driver d = new Driver();
	}
	
	//update global x and y to render background
	public void updateBackground(Graphics g, int max) {
		x += vx;
		y += vy;
		
		g.drawRect(x, y, max, max);
		g.setColor(Color.red);
		g.drawLine(400, 0, 400, 600);
		g.drawLine(0, 300, 800, 300);
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}