package island;

import java.awt.Color;
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
import javax.swing.Timer;
import java.awt.Font;

public class Island extends JPanel implements ActionListener, KeyListener, MouseListener {
	Timer animationTimer; //handles drawing animation
	JFrame f = new JFrame("Dino Crossing"); //Create a JFrame Object with a title bar text
	
	IslandBackground i;
	Protagonist p;
	Blacksmith blacksmith;
	Florist florist;
	Fisherman fisherman;
	Queen queen;
	Extra princess;
	Villagers villagers;
	Extra bridge;
	
	Checklist c; //tasks
	
	ArrayList<Music> bg = new ArrayList<Music>();
	Font verdana = new Font("Verdana", Font.BOLD, 40);
	Font verdanaSmall = new Font("Verdana", Font.BOLD, 20);

	//variables
	public int x, y;
	public int vx, vy;
	public int midX, midY;
	public int width, height;
	public int songNum;
	public boolean canShuffle = false, playSong = true, skipSong = false;
	public boolean isLoading = true, isPaused = false, isMove = true;
	public boolean isMoveN, isMoveS, isMoveW, isMoveE;
	public boolean isDone = false;

	//position debug
	public int x1, x2, y1, y2;
	public int currX;
	public int currY;
	
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
		
		//finished screen
		if (isDone) {
			g.setFont(verdana);
			g.setColor(Color.orange);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.black);
			g.drawString("The game is finished!", 0, 100);
			g.drawString("Congratulations", 0, 150);
			g.drawString("Now Playing:", 0, 300);
			g.drawString(bg.get(songNum).getSongName(), 0, 350);
			
			g.setFont(verdanaSmall);
			g.drawString("Close the application to start over", 0, 525);
			
			//update music
			updateMusic();
			
			return;
		}
		
		//update if the bridge should be painted or not
		if (c.isBridgeBuilt()) {
			bridge.setDoPaint(true);
			i.removeBridge();
		} else {
			bridge.setDoPaint(false);
		}
		
		//update if princess or queen should be painted or not
		if (c.isCollectedFish()) {
			princess.setDoPaint(true);
			queen.setDoPaint(true);
		} else {
			princess.setDoPaint(false);
			queen.setDoPaint(false);
		}
		
		//call paint methods of objects
		i.paint(g);
		g.setColor(Color.black);
		for(int k = 0; k < i.getColSize(); k++) {
			g.drawRect(i.iCol.get(k).getX(), i.iCol.get(k).getY(), i.iCol.get(k).getWidth(), i.iCol.get(k).getHeight());
		}
		g.setColor(Color.orange);
		blacksmith.paint(g);
		florist.paint(g);
		fisherman.paint(g);
		queen.paint(g);
		princess.paint(g);
		villagers.paint(g);
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
		fisherman.setVx(vx);
		fisherman.setVy(vy);
		queen.setVx(vx);
		queen.setVy(vy);
		princess.setVx(vx);
		princess.setVy(vy);
		villagers.setVx(vx);
		villagers.setVy(vy);
		bridge.setVx(vx);
		bridge.setVy(vy);
		
		//collision; false first
		p.collisionFalse(blacksmith);
		p.collisionFalse(florist);
		p.collisionFalse(fisherman);
		p.collisionFalse(queen);
		p.collisionFalse(princess);
		for(int j = 0; j < villagers.v.size(); j++) {
			p.collisionFalse(villagers.v.get(j));
		}
		for(int j = 0; j < i.getColSize(); j++) {
			p.collisionFalse(i.iCol.get(j));
		}
		
		//collision; then true after
		p.collisionTrue(blacksmith);
		p.collisionTrue(florist);
		p.collisionTrue(fisherman);
		p.collisionTrue(queen);
		p.collisionTrue(princess);
		for(int j = 0; j < villagers.v.size(); j++) {
			p.collisionTrue(villagers.v.get(j));
		}
		for(int j = 0; j < i.getColSize(); j++) {
			p.collisionTrue(i.iCol.get(j));
		}
		
		updateCollision();
		
		//protagonist talking with people
		if (blacksmith.isIntersect(p) && c.isCanTalkTwo()) {
			blacksmith.print(g, verdanaSmall, width, height/4);
		} else if (blacksmith.isIntersect(p)) {
			blacksmith.finishedPrint(g, verdanaSmall, width, height/4);
		} else {
			blacksmith.setPrint(false);
		}
		if (florist.isIntersect(p) && c.isCanTalkThree()) {
			florist.print(g, verdanaSmall, width, height/4);
		} else if (florist.isIntersect(p)) {
			florist.finishedPrint(g, verdanaSmall, width, height/4);
		} else {
			florist.setPrint(false);
		}
		if (fisherman.isIntersect(p) && c.isCanTalkFour()) {
			fisherman.print(g, verdanaSmall, width, height/4);
		} else if (fisherman.isIntersect(p)) {
			fisherman.finishedPrint(g, verdanaSmall, width, height/4);
		} else {
			fisherman.setPrint(false);
		}
		if (queen.isIntersect(p) && c.isCollectedFish()) {
			queen.print(g, verdanaSmall, width, height/4);
		} else {
			queen.setPrint(false);
		}
		
		//switching between tasks
		if (blacksmith.didPrint() && !florist.didPrint()) {
			c.settTwo(true);
		}
		if (florist.didPrint() && !fisherman.didPrint()) {
			c.settThree(true);
		}
		if (fisherman.didPrint()) {
			c.settFour(true);
		}
		
		//making noises with villagers
		if (villagers.isIntersect(p)) {
			if (villagers.isPlay()) {
				villagers.playSound();
			}
			villagers.setPlay(false);
		} else {
			villagers.setPlay(true);
		}
		
		//is the game finished?
		if (!queen.isIntersect(p) && queen.didPrint()) {
			isDone = true;
		}
		
		//update music last so it doesn't play twice
		updateMusic();
	}
	
	public void updateMusic() {
		if (canShuffle && bg.get(songNum).isStopped()) {
			int temp;
			
			do {
				temp = (int) (Math.random()*bg.size());
			} while (songNum == temp);
			
			songNum = temp;
			System.out.println("NEXT SONG: " + songNum);
			bg.get(songNum).play();
			
		}
		
		if (!playSong) { //muting the song
			bg.get(songNum).setVolume(0);
		} else {
			bg.get(songNum).setVolume(1);
		}
		
		if (skipSong) { //skipping the song
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
		f.setSize(1938, 1048); //width and height
		f.addKeyListener(this); //listen to keyboard
		f.setUndecorated(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default action for x button
		f.add(this); //add this panel to the JFrame, allows connection with "drawing"
		animationTimer = new Timer(16, this); //setup and startanimation timer
		animationTimer.start();
		f.setVisible(true);
			
		//set variables
		midX = f.getWidth()/2;
		midY = f.getHeight()/2;
		width = f.getWidth();
		height = f.getHeight();
		
		System.out.println("Frame: " + width + ", " + height);
		
		i = new IslandBackground(width*4, height*5);
		p = new Protagonist("brach.png", midX - 50, midY - 50, 100, 100);
		blacksmith = new Blacksmith(1199, -1306, 100, 100, height/4);
		florist = new Florist(-2066, -911, 100, 100, height/4);
		fisherman = new Fisherman(-2951, -1321, 100, 100, height/4);
		queen = new Queen(-2366, -2506, 100, 100, height/4);
		princess = new Extra("princess.png", -2556, -2506, 100, 100);
		villagers = new Villagers();
		bridge = new Extra("bridge.png", -30, -1175, 2*213, 2*60);
		
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
		repaint(); //call the frame to refresh by the timer object every 16ms
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//s/down
		if (e.getKeyCode() == 83 || e.getKeyCode() == 40) {
			if (!p.isColS()) {
				vy = -5;
				isMoveS = true;
			}
		}
		//w/up
		if (e.getKeyCode() == 87 || e.getKeyCode() == 38) {
			if (!p.isColN()) {
				vy = 5;
				isMoveN = true;
			}
		}
		//d/right
		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
			if (!p.isColE()) {
				vx = -5;
				isMoveE = true;
			}
		}
		//a/left
		if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
			if (!p.isColW()) {
				vx = 5;
				isMoveW = true;
			}
		}
		//esc
		if (e.getKeyCode() == 27 ) {
			if (isPaused) {
				isPaused = false;
			} else {
				isPaused = true;
			}
		}
		//r
		if (blacksmith.isIntersect(p)) {
			blacksmith.keyPrint(e);
		}
		//r
		if (florist.isIntersect(p) ) {
			florist.keyPrint(e);
		}
		//r
		if (fisherman.isIntersect(p)) {
			fisherman.keyPrint(e);
		}
		//r
		if (queen.isIntersect(p)) {
			queen.keyPrint(e);
		}
		//c
		c.catchFish(e, p, x, y);
		
		//m
		if (e.getKeyCode() == 77 && isPaused) {
			if (playSong) {
				playSong = false;
			} else {
				playSong = true;
			}
		}
		//n
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
		//s/down
		if (e.getKeyCode() == 83 || e.getKeyCode() == 40) {
			vy = 0;
			isMoveS = false;
		}
		//w/up
		if (e.getKeyCode() == 87 || e.getKeyCode() == 38) {
			vy = 0;
			isMoveN = false;
		}
		//d/right
		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
			vx = 0;
			isMoveE = false;
		}
		//a/left
		if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
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