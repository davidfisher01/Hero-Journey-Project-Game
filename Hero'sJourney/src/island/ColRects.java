package island;

import java.awt.Rectangle;

public class ColRects {
	
	private int x, y, width, height;
	
	
	public ColRects(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void updatePosition(int vx, int vy) {
		x += vx;
		y += vy;
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
	
	public int getHeight() {
		return height;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
}
