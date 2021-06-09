package island;

import java.awt.Color;
import java.awt.Graphics;

public class Extra extends Sprite {
	private int vx, vy;
	private boolean doPaint;

	public Extra(String fileName, int x, int y, int width, int height) {
		super(fileName, width, height);
		vx = 0;
		vy = 0;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		doPaint = true;
	}
	
	public void paint(Graphics g) {
		if (doPaint) {
			super.paint(g);
		} else {
			update();
		}
		
		g.setColor(Color.orange);
		g.drawLine(x, y, x, y + height);					//left
		g.drawLine(x, y, x + width, y);						//top
		g.drawLine(x + width, y, x + width, y + height);	//right
		g.drawLine(x, y + height, x + width, y + height);	//bot
	}
	
	public void update() {
		x += vx;
		y += vy;
		
		tx.setToTranslation(x, y);
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}
	
	public boolean getDoPaint() {
		return doPaint;
	}
	
	public void setDoPaint(boolean b) {
		doPaint = b;
	}
	
}
