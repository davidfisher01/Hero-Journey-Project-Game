package island;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import java.awt.Font;

public class Island extends JPanel implements ActionListener, KeyListener, MouseListener {
	
	//handles drawing animation
	Timer animationTimer;
	//Create a JFrame Object with a title bar text
	JFrame f = new JFrame("Dino Crossing");
	JTextArea t;
	
	IslandBackground i;
	Protagonist p;
	Extra e1;
	Fisherman fisherman;
	
	Text fishermanText;
	
	ArrayList<Music> bg = new ArrayList<Music>();
	Font verdana = new Font("Verdana", Font.BOLD, 40);
	Font verdanaSmall = new Font("Verdana", Font.BOLD, 20);
	public int k = 0;
	WalkToTown test;
	public int x, y;
	public int vx, vy;
	public int midX, midY;
	public int width, height;
	public int songNum;
	public boolean canShuffle = false;
	public boolean isLoading = true, isPaused = false;
	public boolean isMoveN, isMoveS, isMoveW, isMoveE;
	
	public void paint(Graphics g) {
		//calling this line ensures the frame is redrawn
		super.paintComponent(g);
		//update methods
		updateBackground();
		updateVar();
		
		//loading screen
		if (isLoading) {
			g.setFont(verdana);
			g.setColor(Color.orange);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.black);
			g.drawString("Loading, please wait", 0, 100); 
			
			return;
		}
		
		//paused screen
		if (isPaused) {
			g.setFont(verdana);
			g.setColor(Color.orange);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.black);
			g.drawString("Paused, press esc to continue", 0, 100);
			g.drawString("Now Playing:", 0, 300);
			g.drawString(bg.get(songNum).getSongName(), 0, 350);
			
			return;
		}
		
		//call paint methods of objects
		i.paint(g);
		e1.paint(g);
		fisherman.paint(g);
		
		//paint player last
		p.paint(g);
		
		//move all objects but the player
		i.setVx(vx);
		i.setVy(vy);
		e1.setVx(vx);
		e1.setVy(vy);
		fisherman.setVx(vx);
		fisherman.setVy(vy);
		
		//collision
		p.collisionFalse(e1);
		p.collisionFalse(fisherman);
		p.collisionTrue(e1);
		p.collisionTrue(fisherman);
		updateCollision();
		
		//shuffle
		shuffleMusic();
		
		if (fisherman.isIntersectN(p)) {
			if (fishermanText.isPrint()) {
				fishermanText.print(g, verdanaSmall, width, height/4);
			} else {
				fishermanText.notPrint(g, verdanaSmall, width, height/4);
			}
		} else {
			fishermanText.setPrint(false);
		}
		
		g.setColor(Color.red);
		/*g.drawLine(midX - 25, 0, midX - 25, height);	//left
		g.drawLine(0, midY - 25, width, midY - 25);		//top
		g.drawLine(midX + 25, 0, midX+25, height);		//right
		g.drawLine(0, midY+25, width, midY+25);			//bot
		g.drawLine(midX-37, 0, midX-37, height);		//draw line down middle
		g.drawLine(0, midY-37, width, midY-37);			//draw line across middle
		g.drawLine(midX+37, 0, midX+37, height);		//draw line down middle
		g.drawLine(0, midY+37, width, midY+37);			//draw line across middle*/
		
		//g.setColor(Color.white);
		//g.setFont(verdana);
		//g.fillRect(0, 0, f.getWidth(), f.getHeight()/4);
		//g.drawString("and then he touched with his lips, together we became. One Forever. And when he took of his shirt I laughed fo he was an outie", 0, 0);
		
		//displayText("and then he touched with his lips, together we became. One Forever. And when he took of his shirt I laughed fo he was an outie");
		if(k == 0) {
			test = new WalkToTown();
			k++;
		
		}
		Checklist a = new Checklist(g);
		g.setFont(verdana);
		test.update(p, x, y, g);
		g.drawRect(x, y, 50, 50);
	}
	
	public void displayText(String c) {
		t = new JTextArea();
    	f.add(t);
    	t.setText(c);
    	t.setFont(new Font("Serif", Font.PLAIN, 20));
    	t.setPreferredSize(new Dimension(250,250));
    	t.setLineWrap(true);
    	t.setWrapStyleWord(true);
    	//t.setBackground(Color.white);
    	t.setCaretColor(t.getBackground());
    	t.getCaret().setBlinkRate(0);
    	t.setEditable(false);
    	t.setVisible(true);
    	//f.pack();
    	//setSize(300, 300);
    }
	
	public void shuffleMusic() {
		if (canShuffle) {
			if (bg.get(songNum).isStopped()) {
				int temp;
				
				do {
					temp = (int) (Math.random()*bg.size());
				} while (songNum == temp);
				
				songNum = temp;
				System.out.println("NEXT SONG: " + songNum);
				bg.get(songNum).play();
			}
		}
	}
	
	public void updateBackground() {
		x += vx;
		y += vy;
	}
	
	public void updateCollision() {
		if (p.isColN() && isMoveN) {
			vy = 0;
		}
		if (p.isColS() && isMoveS) {
			vy = 0;
		}
		if (p.isColE() && isMoveE) {
			vx = 0;
		}
		if (p.isColW() && isMoveW) {
			vx = 0;
		}
	}
	
	/* constructor for MainPain class */
	public Island() {
		
		//Set the size of the window
		f.setSize(800,600); //width and height

		f.addKeyListener(this);
		
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		f.setUndecorated(false);
		
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
		midX = f.getWidth()/2;
		midY = f.getHeight()/2;
		width = f.getWidth();
		height = f.getHeight();
		
		i = new IslandBackground("BackgroundSandTEst.png", width*4, height*5);
		e1 = new Extra("blacksmith.png", 400, 100, 100, 100);
		fisherman = new Fisherman("queen.png", 500, 100, 100, 100);
		p = new Protagonist("princess.png", midX - 50, midY - 50, 100, 100);
		
		fishermanText = new Text("and then he touched with his lips, \r\n" + 
				"together we became. One Forever. \r\n" + 
				"And when he took of his shirt \r\n" +
				"I laughed fo he was an outie", "fisherman.png");
		
		bg.add(new Music("Gravity.wav", true, "Gravity by Brent Faiyaz"));
		bg.add(new Music("Blessed.wav", true, "Blessed by Juls"));
		bg.add(new Music("22.wav", true, "22 by Taylor Swift"));
		bg.add(new Music("Californiacation.wav", true, "Californication by Red Hot Chili Peppers"));
		bg.add(new Music("Even Flow.wav", true, "Even Flow by Pearl Jam"));
		bg.add(new Music("Everlong.wav", true, "Everlong by Foo Fighters"));
		bg.add(new Music("Room in Here.wav", true, "Room in Here by Anderson .Paak"));
		bg.add(new Music("Shake It Off.wav", true, "Shake it Off by Taylor Swift"));
		bg.add(new Music("Skeletons.wav", true, "Skeletons by Travis Scott"));
		bg.add(new Music("Triumph.wav", true, "Triumph by J Hus"));
		bg.add(new Music("Come Together.wav", true, "Come Together by The Internet"));
		bg.add(new Music("NEW MAGIC WAND.wav", true, "NEW MAGIC WAND by Tyler, the Creator"));
		bg.add(new Music("PAC-MAN.wav", true, "PAC-MAN by Gorillaz"));
		bg.add(new Music("Take The Long Way Home.wav", true, "Take the Long Way Home by Supertramp"));
		bg.add(new Music("Weed In L.A..wav", true, "Weed in LA by Koreatown Oddity"));
		
		//start music
		songNum = (int) (Math.random()*bg.size());
		bg.get(songNum).play();
		System.out.println("ORIGINAL SONG: " + songNum);
		
		//update variables to start game
		canShuffle = true;
		isLoading = false;
	}
	
	public void updateVar() {
		midX = f.getWidth()/2;
		midY = f.getHeight()/2;
		width = f.getWidth();
		height = f.getHeight();
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
			//System.out.println("player: moved south");
			if (!p.isColS()) {
				vy = -5;
				isMoveS = true;
			}
		}
		if (e.getKeyCode() == 87 || e.getKeyCode() == 38) {
			//System.out.println("player: moved north");
			if (!p.isColN()) {
				vy = 5;
				isMoveN = true;
			}
		}
		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
			//System.out.println("player: moved east");
			if (!p.isColE()) {
				vx = -5;
				isMoveE = true;
			}
		}
		if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
			//System.out.println("player: moved west");
			if (!p.isColW()) {
				vx = 5;
				isMoveW = true;
			}
		}
		if (e.getKeyCode() == 27 ) {
			if (isPaused) {
				isPaused = false;
			} else {
				isPaused = true;
			}
		}
		
		if (fisherman.isIntersectN(p)) {
			if (e.getKeyCode() == 82) {
				fishermanText.setPrint(true);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 83 || e.getKeyCode() == 40) {
			//System.out.println("player: stopped south");
			vy = 0;
			isMoveS = false;
		}
		if (e.getKeyCode() == 87 || e.getKeyCode() == 38) {
			//System.out.println("player: stopped north");
			vy = 0;
			isMoveN = false;
		}
		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
			//System.out.println("player: stopped east");
			vx = 0;
			isMoveE = false;
		}
		if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
			//System.out.println("player: stopped west");
			vx = 0;
			isMoveW = false;
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