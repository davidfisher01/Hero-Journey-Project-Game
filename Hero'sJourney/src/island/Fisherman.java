package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Fisherman extends Extra {
	Text text;

	public Fisherman(int x, int y, int width, int height, int charSize) {
		super("fisherman.png", x, y, height, height);
		
		text = new Text("Okay look atchu lookn' all fine and stuff. \r\n" + 
				"I seen the blacksmith and the florist and we was getting brunch. \r\n" + 
				"Don't worry tho, they told me aaaaalllll about it. You in good hands. \r\n" +
				"This town eats mostly fish instead of each other.  \r\n" +
				"But we done run dry of fishes. \r\n" +
				"If you collect some fish, it would do the town a real favor, especially since it stops them from going animalistical. \r\n" +
				"Walk over to the pier east of me and start fishin.", "fisherman.png", charSize);
		
		text.setFinishedStr("Heard the queen mother wants to talk to you. \r\n" +
				"shes north of the village.");
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
	
	//if the protagonist intersects with the north or south rectangle
	public boolean isIntersect(Protagonist p) {
		return p.northRect().intersects(getRect()) || p.southRect().intersects(getRect());
	}

	public void setPrint(boolean b) {
		text.setPrint(b);
	}
	
	public boolean didPrint() {
		return text.isDidPrint();
	}
	
}
