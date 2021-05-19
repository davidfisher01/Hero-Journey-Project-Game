package island;

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
	
	public void collision (Extra e) {
		if (northRect().intersects(e.getRect())) {
			System.out.println("Player: intersected north");
			isColN = true;
		} else {
			isColN = false;
		}
		if (southRect().intersects(e.getRect())) {
			System.out.println("Player: intersected south");
			isColS = true;
		} else {
			isColS = false;
		}
		if (eastRect().intersects(e.getRect())) {
			System.out.println("Player: intersected east");
			isColE = true;
		} else {
			isColE = false;
		}
		if (westRect().intersects(e.getRect())) {
			System.out.println("Player: intersected west");
			isColW = true;
		}
		else {
			isColW = false;
		}
		if (getRect().intersects(e.getRect())) {
			System.out.println("Player: intersected rect");
			isColG = true;
		} else {
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
		Rectangle rect = new Rectangle(x,y - height/4,width,height/4);
		return rect;
	}
	
	public Rectangle southRect() {
		Rectangle rect = new Rectangle(x,y + height,width,height/4);
		return rect;
	}
	
	public Rectangle eastRect() {
		Rectangle rect = new Rectangle(x + width,y,width/4,height);
		return rect;
	}
	
	public Rectangle westRect() {
		Rectangle rect = new Rectangle(x - width/4,y,width/4,height);
		return rect;
	}
}
