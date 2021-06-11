package island;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Protagonist extends Sprite{
	private boolean isColN, isColS, isColE, isColW, isColG;

	public Protagonist (String fileName, int x, int y, int width, int height) {
		super(fileName, width, height);
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		System.out.println("Protagonist x/y: " + x + ", " + y);
		System.out.println("gen rect: " + getRect());
		System.out.println("north rect: " + northRect());
		System.out.println("south rect: " + southRect());
		System.out.println("east rect: " + eastRect());
		System.out.println("west rect: " + westRect());
	}
	
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	public void collisionTrue (ColRects e) {
		if (northRect().intersects(e.getRect())) {
			isColN = true;
		}
		if (eastRect().intersects(e.getRect())) {
			isColE = true;
		}
		if (southRect().intersects(e.getRect())) {
			isColS = true;
		}
		if (westRect().intersects(e.getRect())) {
			isColW = true;
		}
		if (getRect().intersects(e.getRect())) {
			isColG = true;
		}
	}
	
	public void collisionFalse (ColRects e) {
		if (!northRect().intersects(e.getRect())) {
			isColN = false;
		}
		if (!southRect().intersects(e.getRect())) {
			isColS = false;
		}
		if (!eastRect().intersects(e.getRect())) {
			isColE = false;
		}
		if (!westRect().intersects(e.getRect())) {
			isColW = false;
		}
		if (!getRect().intersects(e.getRect())) {
			isColG = false;
		}
	}
	
	public void collisionTrue (Extra e) {
		if (northRect().intersects(e.getRect())) {
			isColN = true;
		}
		if (southRect().intersects(e.getRect())) {
			isColS = true;
		}
		if (eastRect().intersects(e.getRect())) {
			isColE = true;
		}
		if (westRect().intersects(e.getRect())) {
			isColW = true;
		}
		if (getRect().intersects(e.getRect())) {
			isColG = true;
		}
	}
	
	public void collisionFalse (Extra e) {
		if (!northRect().intersects(e.getRect())) {
			isColN = false;
		}
		if (!southRect().intersects(e.getRect())) {
			isColS = false;
		}
		if (!eastRect().intersects(e.getRect())) {
			isColE = false;
		}
		if (!westRect().intersects(e.getRect())) {
			isColW = false;
		}
		if (!getRect().intersects(e.getRect())) {
			isColG = false;
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

	public boolean isColG() {
		return isColG;
	}

	public void setColG(boolean isColG) {
		this.isColG = isColG;
	}

	public Rectangle northRect() {
		Rectangle rect = new Rectangle(x,y - height/25,width,height/25);
		return rect;
	}
	
	public Rectangle southRect() {
		Rectangle rect = new Rectangle(x,y + height,width,height/25);
		return rect;
	}
	
	public Rectangle eastRect() {
		Rectangle rect = new Rectangle(x + width,y,width/25,height);
		return rect;
	}
	
	public Rectangle westRect() {
		Rectangle rect = new Rectangle(x - width/25,y,width/25,height);
		return rect;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}

}
