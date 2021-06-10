package island;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
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
	Blacksmith blacksmith;
	Florist florist;
	Extra bridge;
	
	Checklist c;
	
	ArrayList<Music> bg = new ArrayList<Music>();
	Font verdana = new Font("Verdana", Font.BOLD, 40);
	Font verdanaSmall = new Font("Verdana", Font.BOLD, 20);
	
	public int k = 0;
	public int x, y;
	public int x1, x2, y1, y2;
	public int vx, vy;
	public int midX, midY;
	public int width, height;
	public int songNum;
	public int currX;
	public int currY;
	public boolean canShuffle = false, playSong = true, skipSong = false;
	public boolean isLoading = true, isPaused = false, isMove = true;
	public boolean isMoveN, isMoveS, isMoveW, isMoveE;
	
	public void paint(Graphics g) {
		//calling this line ensures the frame is redrawn
		super.paintComponent(g);
		
		//loading screen
		if (isLoading) {
			g.setFont(verdana);
			g.setColor(Color.orange);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.black);
			g.drawString("Loading, please wait", 0, 100); 
			
			return;
		}
		
		//update methods
		updateBackground();
		updateVar();
		
		//paused screen
		if (isPaused) {
			g.setFont(verdana);
			g.setColor(Color.orange);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.black);
			g.drawString("Paused, press esc to continue", 0, 100);
			g.drawString("Now Playing:", 0, 300);
			g.drawString(bg.get(songNum).getSongName(), 0, 350);
			g.drawString("Volume:", 0, 400);
			g.drawString(""+ bg.get(songNum).getVolume(), 0, 450);
			
			g.setFont(verdanaSmall);
			if (playSong) {
				g.drawString("press m to mute song", 0, 525);
			} else {
				g.drawString("press m to unmute song", 0, 525);
			}
			g.drawString("press n to skip song", 0, 550);
			
			//update music
			updateMusic();
			
			return;
		}
		
		//update if the bridge should be painted or not
		if (c.isBridgeBuilt()) {
			bridge.setDoPaint(true);
		} else {
			bridge.setDoPaint(false);
		}
		
		//call paint methods of objects
		i.paint(g);
		g.setColor(Color.black);
		for(int k = 0; k < i.getColSize(); k++) {
			g.drawRect(i.iCol.get(k).getX(), i.iCol.get(k).getY(), i.iCol.get(k).getWidth(), i.iCol.get(k).getHeight());
			//System.out.println("" + iCol.get(i).getX() + ", " + iCol.get(i).getY() + ", " + iCol.get(i).getWidth() + ", " + iCol.get(i).getHeight());
		}
		g.setColor(Color.orange);
		blacksmith.paint(g);
		florist.paint(g);
		bridge.paint(g);
		
		//paint player and checklist last
		c.updateTasks(p, x, y, g, verdanaSmall, width, height/4);
		p.paint(g);
		
		//move all objects but the player
		i.setVx(vx);
		i.setVy(vy);
		blacksmith.setVx(vx);
		blacksmith.setVy(vy);
		florist.setVx(vx);
		florist.setVy(vy);
		bridge.setVx(vx);
		bridge.setVy(vy);
		
		//collision; false first, then true after
		p.collisionFalse(blacksmith);
		p.collisionFalse(florist);
		for(int j = 0; j < i.getColSize(); j++) {
			p.collisionFalse(i.iCol.get(j));
		}
		
		p.collisionTrue(blacksmith);
		p.collisionTrue(florist);
		for(int j = 0; j < i.getColSize(); j++) {
			p.collisionTrue(i.iCol.get(j));
		}
		
		updateCollision();
		
		//protagonist intersecting with blacksmith
		if (blacksmith.isIntersect(p) && c.isCanTalkTwo()) {
			blacksmith.print(g, verdanaSmall, width, height/4);
		} else {
			blacksmith.setPrint(false);
		}
		
		if (florist.isIntersect(p) && c.isCanTalkThree()) {
			florist.print(g, verdanaSmall, width, height/4);
		} else {
			florist.setPrint(false);
		}
		
		//switching between tasks
		if (blacksmith.didPrint()) {
			c.settTwo(true);
		}
		if (florist.didPrint()) {
			c.settThree(true);
		}
		
		
		/* 
		 * DEBUGGING
		 */
		
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
	
		//update music last so it doesn't play twice
		updateMusic();
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
	
	public void updateMusic() {
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
		
		if (!playSong) {
			bg.get(songNum).setVolume(0);
		} else {
			bg.get(songNum).setVolume(1);
		}
		
		if (skipSong) {
			bg.get(songNum).stop();
			skipSong = false;
		}
	}
	
	public void updateBackground() {
		x += vx;
		y += vy;
		for(int j = 0; j < i.getColSize(); j++) {
			i.iCol.get(j).updatePosition(vx, vy);
		}
		currX -= vx;
		currY -= vy;
		
		
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
		f.setSize(1938, 1048); //width and height

		f.addKeyListener(this);
		
		//f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
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
		
		System.out.println(width + ", " + height);
		
		i = new IslandBackground("IslandWithoutBridge.png", width*4, height*5);
		p = new Protagonist("bronc.png", midX - 50, midY - 50, 100, 100);
		blacksmith = new Blacksmith(1199, -1306, 100, 100, height/4);
		florist = new Florist(-2066, -911, 100, 100, height/4);
		bridge = new Extra("newbridge.png", -30, -1175, 2*213, 2*60);
		
		c = new Checklist(height/4);
		
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
		
		currX = midX-50;
		currY = midY-50;
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
		
		if (blacksmith.isIntersect(p)) {
			blacksmith.keyPrint(e);
		}
		
		if (florist.isIntersect(p) ) {
			florist.keyPrint(e);
		}
		
		if (e.getKeyCode() == 77 && isPaused) {
			if (playSong) {
				playSong = false;
			} else {
				playSong = true;
			}
		}
		if (e.getKeyCode() == 78 && isPaused) {
			if (skipSong) {
				skipSong = false;
			} else {
				skipSong = true;
			}
		}
		
		//enter
		if (e.getKeyCode() == 10) {
			System.out.println("iCol.add(new ColRects(" + x1 + ", " + y1 + ", " + (x2 - x1) + ", " + (y2 - y1) + "));");
		}
		//p
		if(e.getKeyCode() == 80) {
			x2 = currX;
			y2 = currY;
		}
		//0
		if(e.getKeyCode() == 79) {
			x1 = currX;
			y1 = currY;
			
			
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