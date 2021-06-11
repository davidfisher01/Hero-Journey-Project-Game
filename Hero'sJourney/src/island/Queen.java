package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Queen extends Extra {
	Text text;

	public Queen(int x, int y, int width, int height, int charSize) {
		super("queen.png", x, y, height, height);
		
		text = new Text("Congrats. \r\n" + 
				"I heard you were helping out the community, out of the good of your heart. \r\n" + 
				"You did this town a lot of favors, you deserve something in return. \r\n" +
				"I shall fly you off this island, thank you again", "queen.png", charSize);
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
	
	//if the protagonist intersects with the west rectangle
	public boolean isIntersect(Protagonist p) {
		return p.westRect().intersects(getRect());
	}

	public void setPrint(boolean b) {
		text.setPrint(b);
	}
	
	public boolean didPrint() {
		return text.isDidPrint();
	}
	
}
