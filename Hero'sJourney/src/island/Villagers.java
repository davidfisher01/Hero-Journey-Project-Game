package island;

import java.awt.Graphics;
import java.util.ArrayList;

public class Villagers {
	public ArrayList<Extra> v;
	private ArrayList<Music> sfx;
	private boolean play;
	
	public Villagers() {
		v = new ArrayList<Extra>();
		sfx = new ArrayList<Music>();
		
		v.add(new Extra("stego.png", -761, -2221, 100, 100));
		v.add(new Extra("brach.png", 279, -2271, 100, 100));
		v.add(new Extra("para.png", -1481, -681, 100, 100));
		v.add(new Extra("tricera.png", -1771, -581, 100, 100));
		v.add(new Extra("stego.png", -2471, -91, 100, 100));
		v.add(new Extra("para.png", -2211, -2041, 100, 100));
		v.add(new Extra("stego.png", -1306, 139, 100, 100));
		v.add(new Extra("trex.png", -1556, 399, 100, 100));
		
		sfx.add(new Music("sfx1.wav", false, "sound effect 1"));
		sfx.add(new Music("sfx2.wav", false, "sound effect 2"));
		sfx.add(new Music("sfx3.wav", false, "sound effect 3"));
		
		play = true;
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < v.size(); i++) {
			v.get(i).paint(g);
		}
	}
	
	public void playSound() {
		int temp = (int) (Math.random()*sfx.size());
		
		sfx.get(temp).play();
	}
	
	public boolean isIntersect(Protagonist p) {
		for (int i = 0; i < v.size(); i++) {
			if (v.get(i).getRect().intersects(p.getRect())) {
				return true;
			}
		}
		return false;
	}
	
	public void setVx(int vx) {
		for (int i = 0; i < v.size(); i++) {
			v.get(i).setVx(vx);
		}
	}
	
	public void setVy(int vy) {
		for (int i = 0; i < v.size(); i++) {
			v.get(i).setVy(vy);
		}
	}

	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

}
