package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Blacksmith extends Extra {
	Text text;

	public Blacksmith(int x, int y, int width, int height, int charSize) {
		super("blacksmith.png", x, y, height, height);
		
		text = new Text("Hey kind sir, it looks like you're new around here! \r\n" + 
				"Oh whats that? You don't know where you come from? \r\n" + 
				"Well you need to talk to the queen mother. She can help you out. \r\n" +
				"But to do that, you need to get across into the town, problem is the bridge is broken. \r\n" +
				"It's causing the town some issues getting to this side of the island. \r\n" +
				"I have to walk all the way around to get to the town. \r\n" +
				"If you repair the bridge, I'm sure the queen will be willing to help you out. \r\n" +
				"Collect a flower, tree, and some dirt, and you can build a bridge for us! \r\n" +
				"You'll be able to tell by the green box around the items. \r\n" +
				"Once you collect those items, go west on this road and it should appear!", "blacksmith.png", charSize);
		
		text.setFinishedStr("Hey, you should talk to the florist if you haven't already. \r\n" +
				"She'll help you out a lot!");
	}
	
	public void print(Graphics g, Font f, int textWidth, int textHeight) {
		if (text.isPrint()) {
			text.print(g, f, textWidth, textHeight);
		} else {
			text.notPrint(g, f, textWidth, textHeight);
		}
	}
	
	public void finishedPrint(Graphics g, Font f, int textWidth, int textHeight) {
		if (text.isPrint()) {
			text.finishedPrint(g, f, textWidth, textHeight);
		} else {
			text.notPrint(g, f, textWidth, textHeight);
		}
	}
	
	public void keyPrint(KeyEvent e) {
		if (e.getKeyCode() == 82) {
			text.setPrint(true);
		}
	}
	
	//if the protagonist intersects with the north rectangle
	public boolean isIntersect(Protagonist p) {
		return p.northRect().intersects(getRect());
	}
	
	public void setPrint(boolean b) {
		text.setPrint(b);
	}
	
	public boolean didPrint() {
		return text.isDidPrint();
	}
	
}
