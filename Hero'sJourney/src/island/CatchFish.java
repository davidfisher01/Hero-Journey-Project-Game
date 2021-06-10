package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class CatchFish extends Task {
	private int fishCaught;
	private boolean caught;
	private Text text;
	private Font stringFont;
	
	public CatchFish(int charSize) {
		fishCaught = 0;
		caught = false;
		stringFont = new Font( "SansSerif", Font.PLAIN, 15);
		text = new Text("Wow I remember something about my father teaching me to fish \r\n" +
				"I have to press c to fish, and I should hear a noise.", "bronc.png", charSize);
		
		System.out.println("Created task Catch Fish");
	}
	
	public void update(Protagonist p, int x, int y, Graphics g, Font f, int textWidth, int textHeight) {
		if(isAtPier(p, x, y) && !didCatchAllFish()) {
			text.print(g, f, textWidth, textHeight);
			g.setColor(Color.black);
			g.setFont(stringFont);
			g.drawString("I have caught " + fishCaught + " fish.", 65, 595);
		} else {
			g.setColor(Color.black);
			g.drawString("Go to the Pier", 65, 595);
			g.drawRect(x - 3286, y - 1181, 100, 100);
		}
		
		if (didCatchAllFish()) {
			setCompleted();
		}
	}
	
	public void catchFish(KeyEvent e, Protagonist p, int x, int y) {
		if(e.getKeyCode() == 67 && isAtPier(p, x, y)) {
			fishCaught++;
		}
	}
	
	public boolean didCatchAllFish() {
		return fishCaught == 3;
	}
	
	public boolean isAtPier(Protagonist p, int x, int y) {
		Rectangle pier = new Rectangle(x - 3286, y - 1181, 100, 100);// adjust these #'s
		if (pier.intersects(p.getRect())) {
			return true;
		} else {
			return false;
		}
	
	}
}
