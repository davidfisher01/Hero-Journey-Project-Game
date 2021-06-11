package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Menu extends JPanel implements ActionListener, MouseListener, KeyListener{
	
	//handles drawing animation
	Timer animationTimer;
	//Create a JFrame Object with a title bar text
	JFrame f = new JFrame("Dino Crossing");

	Rectangle start;
	Rectangle credits;
	Extra d;
	Extra d2;
	Extra d3;
	
	Music bg;
	Font verdana = new Font("Verdana", Font.BOLD, 40);
	Font verdanaSmall = new Font("Verdana", Font.BOLD, 20);
	
	public int midX, midY;
	public int width, height;
	public boolean isStart, isCredits, isCreditsMusic, isCreditsArt, isLoading = true;
	
	public void paint(Graphics g) {
		//calling this line ensures the frame is redrawn
		super.paintComponent(g);
		
		updateVar();
		
		//paint background
		g.setFont(verdana);
		g.setColor(Color.orange);
		g.fillRect(0, 0, width, height);		//fills background orange
		
		//paint loading
		if (isLoading) {
			g.setColor(Color.black);
			g.drawString("the Fantastic Four Friends", midX/4, midY/4);
			g.drawString("presents", midX - 120, height - midY/4);
			d3.paint(g);
			
			return;
		}
		
		//paint credits
		if (isCredits) {
			//paint music, but not the credits
			if (isCreditsMusic) {
				//seperators
				g.setColor(Color.black);
				g.drawLine(0, 165 - 35, width, 165 - 35);
				g.drawLine(0, 290 - 35, width, 290 - 35);
				g.drawLine(0, 415 - 35, width, 415 - 35);
				
				//names
				g.drawString("Music", 0, 125);
				g.drawString("David", 0, 165);
				g.drawString("Francis", 0, 290);
				g.drawString("Eric", 0, 415);
				
				//songs
				g.setFont(verdanaSmall);
				g.drawString("Title Song: Stegosaurus by Howdytoons", 200, 100);
				g.drawString("Boss Music: ", 200, 125);
				g.drawString("22 by Taylor Swift", 200, 150);
				g.drawString("Californication by Red Hot Chili Peppers", 200, 175);
				g.drawString("Even Flow by Pearl Jam", 200, 200);
				g.drawString("Everlong by Foo Fighters", 200, 225);
				g.drawString("Shake it Off by Taylor Swift", 200, 250);
				g.drawString("Gravity by Brent Faiyaz", 200, 275);
				g.drawString("Blessed by Juls", 200, 300);
				g.drawString("Room in Here by Anderson .Paak", 200, 325);
				g.drawString("Skeletons by Travis Scott", 200, 350);
				g.drawString("Triumph by J Hus", 200, 375);
				g.drawString("Come Together by The Internet", 200, 400);
				g.drawString("NEW MAGIC WAND by Tyler, the Creator", 200, 425);
				g.drawString("PAC-MAN by Gorillaz", 200, 450);
				g.drawString("Take the Long Way Home by Supertramp", 200, 475);
				g.drawString("Weed in LA by Koreatown Oddity", 200, 500);
				
				g.drawString("press 'm' to go back", 0, 525);
				g.drawString("press 'a' to see art credits", 0, 550);
				
				return;
			}
			
			//paints art, but not credits
			if (isCreditsArt) {
				g.setColor(Color.black);
				g.drawString("Art", 0, 125);
				
				g.setFont(verdanaSmall);
				g.drawString("eric loves pokimane", 0, 150);
				
				g.drawString("press 'a' to go back", 0, 525);
				g.drawString("press 'm' to see music credits", 0, 550);
				
				return;
			}
			
			//paints normal credits
			g.setColor(Color.black);
			g.drawString("Credits", 0, 100);
			g.drawString("Created by", 0, 200);
			g.drawString("the Fantastic Four Friends", 0, 250);
			g.drawString("With Help and Inspiration from", 0, 400);
			g.setFont(verdanaSmall);
			g.drawString("press 'esc' to go back", 0, 50);
			g.drawString("David, Eric, Francis", 0, 285);
			g.drawString("Mr. Domingo David", 0, 435);
			g.drawString("press 'm' to see music credits", 0, 525);
			g.drawString("press 'a' to see art credits", 0, 550);
			
			return;
		}
		
		//paint main menu
		d2.paint(g);							//paint dinosaur 4th quadrant
		g.setColor(Color.black);				//set color to black
		g.drawRect(start.x, start.y - start.height, start.width, start.height);		//draw rectangle to click
		g.drawRect(credits.x, credits.y - credits.height, credits.width, credits.height);		//draw rectangle to click
		g.drawString("Start", 0, 100);			//draw string to click on
		g.drawString("Credits", 0, 300);		//draw string to click on
		g.drawString("Dino Crossing", width - 350, 100);	//title of game
		d.paint(g);								//paint dinosaur 2nd quadrant
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
		
		//connect jframe to mouse and key listening code
		f.addMouseListener(this);
		f.addKeyListener(this);
		
		f.setVisible(true);
		
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
		isCredits = false;
		
		start = new Rectangle(0, 100, 150, 50);
		credits = new Rectangle(0, 300, 200, 50);
		d = new Extra("brach.png", 155, 70, 300, 100);
		d2 = new Extra("stego.png", midX, midY, midX, midY);
		d3 = new Extra("ninja.png", midX - 150, midY - 150 - 50, 300, 300);
		
		bg = new Music("Stegosaurus.wav", true, "Stegosaurus by Howdytoons");
		bg.loop();
		
		try
		{
		    Thread.sleep(3000);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		
		isLoading = false;
	}
	
	public void updateVar() {
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
		Rectangle mouse = new Rectangle(m.getX(), m.getY(), 25, 25);
		
		if (mouse.intersects(start) && !isLoading && !isCredits) {
			f.setVisible(false);
			isStart = true;
			bg.stop();
		}
		
		if (mouse.intersects(credits)) {
			isCredits = true;
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

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 27 ) {
			isCredits = false;
			isCreditsMusic = false;
			isCreditsArt = false;
		}
		
		if (e.getKeyCode() == 77) {
			if (isCreditsMusic) {
				isCreditsMusic = false;
			} else {
				isCreditsMusic = true;
				isCreditsArt = false;
			}
		}
		
		if (e.getKeyCode() == 65) {
			if (isCreditsArt) {
				isCreditsArt = false;
			} else {
				isCreditsArt = true;
				isCreditsMusic = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
