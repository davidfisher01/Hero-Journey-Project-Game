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
				"I play dino ball with her on the weekends. I know, just girl thangs. \r\n" +
				"Crazy right? Nice to meet you, I'm the town florist! \r\n" +
				"Anyway, if you collect three flowers to add to my collection, I'll put in a good word for you! \r\n" +
				"You should be able to tell by the green box around them.", "fisherman.png", charSize);
	}
	
	public void print(Graphics g, Font f, int textWidth, int textHeight) {
		if (text.isPrint()) {
			text.print(g, f, textWidth, textHeight);
		} else {
			text.notPrint(g, f, textWidth, textHeight);
		}
	}
	
	public void keyPrint(KeyEvent e) {
		if (e.getKeyCode() == 82) {
			text.setPrint(true);
		}
	}
	
	//if the protagonist intersects with the west or south rectangle
	public boolean isIntersect(Protagonist p) {
		return p.westRect().intersects(getRect()) || p.southRect().intersects(getRect());
	}

	public void setPrint(boolean b) {
		text.setPrint(b);
	}
	
	public boolean didPrint() {
		return text.isDidPrint();
	}
	
}
