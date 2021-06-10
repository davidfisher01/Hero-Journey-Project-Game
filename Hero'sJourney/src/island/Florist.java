package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Florist extends Extra {
	Text text;

	public Florist(int x, int y, int width, int height, int charSize) {
		super("florist.png", x, y, height, height);
		
		text = new Text("Hey sir! The blacksmith told me you were from around here. \r\n" + 
				"He said you were stranded and needed the queen mother's help. \r\n" + 
				"Probably because she's the ONLY pterodactyl in the WHOLE island. \r\n" +
				"I play dino ball with her on the weekends. I know right! Just girl thangs. \r\n" +
				"Crazy right? Nice to meet you, I'm the town florist! \r\n" +
				"Anyway, if you collect three flowers to add to my collection, I'll put in a good word for you! \r\n" +
				"You should be able to tell by the green box around them.", "florist.png", charSize);
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
