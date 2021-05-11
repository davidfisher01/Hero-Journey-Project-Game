package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Protagonist extends Sprite{
	
	private boolean isColN, isColS, isColE, isColW;
	
	public Protagonist (String fileName, int x, int y, int width, int height) {
		super(fileName, width, height);
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		System.out.println("Protagonist x/y: " + x + ", " + y);
	}
	
	public void isColliding (Extra e) {
		if (northRect().intersects(e.getRect())) {
			System.out.println("n");
			isColN = true;
		}
		if (southRect().intersects(e.getRect())) {
			System.out.println("s");
			isColS = true;
		}
		if (eastRect().intersects(e.getRect())) {
			System.out.println("e");
			isColE = true;
		}
		if (westRect().intersects(e.getRect())) {
			System.out.println("w");
			isColW = true;
		}
	}
	
	public boolean isColN() {
		return isColN;
	}

	public void setColN(boolean isColN) {
		this.isColN = isColN;
	}

	public boolean isColS() {
		return isColS;
	}

	public void setColS(boolean isColS) {
		this.isColS = isColS;
	}

	public boolean isColE() {
		return isColE;
	}

	public void setColE(boolean isColE) {
		this.isColE = isColE;
	}

	public boolean isColW() {
		return isColW;
	}

	public void setColW(boolean isColW) {
		this.isColW = isColW;
	}

	public Rectangle northRect() {
		Rectangle rect = new Rectangle(x,y - height,width,height);
		return rect;
	}
	
	public Rectangle southRect() {
		Rectangle rect = new Rectangle(x,y + height,width,height);
		return rect;
	}
	
	public Rectangle eastRect() {
		Rectangle rect = new Rectangle(x + width,y,width,height);
		return rect;
	}
	
	public Rectangle westRect() {
		Rectangle rect = new Rectangle(x - width,y,width,height);
		return rect;
	}
}
