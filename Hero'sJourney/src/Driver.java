import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Driver implements ActionListener {
	
	public int vx, vy;	//global velocity
	public int x, y;
	
	public void paint(Graphics g) {
		
	}
	
	public Driver() {
		x = 0;
		y = 0;
		
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(800, 600);
		frame.add(frame, this);
		
		Timer t = new Timer(16, this);
		t.start();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] arg) {
		Island i = new Island();
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
}