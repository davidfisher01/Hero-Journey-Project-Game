package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

public class BuildBridge extends Task{
	private Rectangle r;
	private Text text;
	private Image bridge;
	public boolean makeBridge;
	public boolean one, two, three;
	public int x1, y1; //x and y of flower one
	public int x2, y2; //x and y of flower two
	public int x3, y3; // x and y of flower three
	
	public BuildBridge(int charSize) {
		makeBridge = true;
		one = false;
		two = false;
		three = false;
		x1 = 563;
		y1 = -743;
		x2 = 459;
		y2 = -136;
		x3 = 1399;
		y3 = -261;
		
		text = new Text("I need to collect a flower, tree, and some dirt \r\n" +
							"before I can build this bridge for the blacksmith.", "bronc.png", charSize);
		
		System.out.println("Created task BuildBridge");
	}
	
	public void update(Protagonist p, int x, int y, Graphics g, Font f, int textWidth, int textHeight) {
		//one
		if (isCollected(p, x + x1, y + y1, 50, 50) && !one) {
			one = true;
			System.out.println("bridge one picked");
		}
		
		//two
		if (isCollected(p, x + x2, y + y2, 260, 210) && !two) {
			two = true;
			System.out.println("bridge two picked");
		}
				
		//three
		if (isCollected(p, x + x3, y + y3, 100, 50) && !three) {
			three = true;
			System.out.println("bridge three picked");
		}
		
		if (finishedCollecting() && !isCompleted()) {
			g.setColor(Color.blue);
			g.drawString("Wood Collected!", 65, 595);
		} 

		if (!finishedCollecting()) {
			g.setColor(Color.black);
			g.drawString("Get Wood(Pause)", 65, 595);
		}
		
		if (finishedCollecting() && isCollected(p, x + 329, y - 1201, 100, 100) && makeBridge) {
			bridge = getImage("bridge.png");
			g.drawImage(bridge, 0, 500, null);
			makeBridge = false;
			System.out.println("bridge is built");
			setCompleted();
		}
		
		if (isCollected(p, x + 329, y - 1201, 100, 100) && makeBridge) {
			text.print(g, f, textWidth, textHeight);
		}

		if (makeBridge) {
			g.drawRect(x + 329, y - 1201, 100, 100);
		}
		
		//debug
		g.setColor(Color.green);
		if (!one) {
			g.drawRect(x + x1, y + y1, 50, 50);
		}
		if (!two) {
			g.drawRect(x + x2, y + y2, 260, 210);
		}
		if (!three) {
			g.drawRect(x + x3, y + y3, 100, 50);
		}
	}
	
	public boolean isCollected(Protagonist p, int x, int y, int width, int height) {
		r = new Rectangle(x, y, width, height);// adjust these #'s
		return r.intersects(p.getRect());
	}
	
	public boolean finishedCollecting() {
		if (one == true && two == true && three == true) {
			return true;
		} else {
			return false;
		}
	}
}
