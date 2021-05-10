package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.sun.org.apache.bcel.internal.generic.D2F;

public class Menu extends JPanel implements ActionListener, MouseListener {
	
	//handles drawing animation
	Timer animationTimer;
	//Create a JFrame Object with a title bar text
	JFrame f = new JFrame("Dino Crossing");

	Rectangle start;
	Extra d;
	Extra d2;
	
	Font verdana = new Font("Verdana", Font.BOLD, 40);
	
	public int midX, midY;
	public int width, height;
	public boolean isStart;
	
	public void paint(Graphics g) {
		//calling this line ensures the frame is redrawn
		super.paintComponent(g);
		
		//call paint methods of objects or through g.drawRect etc
		g.setFont(verdana);
		g.setColor(Color.orange);
		g.fillRect(0, 0, width, height);
		d2.paint(g);
		g.setColor(Color.black);
		g.drawLine(midX, 0, midX, height);
		g.drawLine(0, midY, width, midY);
		g.drawRect(start.x, start.y - start.height, start.width, start.height);
		g.drawString("Start :)", midX, midY);
		g.drawString("Dino Crossing", 205, 500);
		d.paint(g);
		
		update();
	}
	
	/* constructor for MainPain class */
	public Menu() {
		//Set the size of the window
		f.setSize(800,600); //width and height
		
		//set default action for x button
		//without this, your code will run behind the scenes until
		//you force exit
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add this panel to the JFrame
		//allows connection with "drawing"
		f.add(this);
		
		//connect jframe to mouse listening code
		f.addMouseListener(this);
		
		//setup animation timer
		animationTimer = new Timer(16, this);
		
		//do not forget to start the timer
		animationTimer.start();
		
		//instantiate the rest of the instance variables
		midX = f.getWidth()/2;
		midY = f.getHeight()/2;
		width = f.getWidth();
		height = f.getHeight();
		isStart = false;
		
		start = new Rectangle(midX, midY, 200, 50);
		d = new Extra("bronc.png", 50, 70, 300, 100);
		d2 = new Extra("bronc.png", midX, midY, midX, midY);
		
		f.setVisible(true);
	}
	
	public void update() {
		midX = f.getWidth()/2;
		midY = f.getHeight()/2;
		width = f.getWidth();
		height = f.getHeight();
		
	}
	
	public boolean getStart() {
		return isStart;
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
	public void mouseClicked(MouseEvent m) {
		// TODO Auto-generated method stub
		Rectangle mouse = new Rectangle(m.getX(), m.getY(), 50, 50);
		
		if (mouse.intersects(start)) {
			f.setVisible(false);
			isStart = true;
		}
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
