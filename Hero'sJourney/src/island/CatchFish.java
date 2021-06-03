package island;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class CatchFish extends Task {
	Rectangle r = new Rectangle(0, 0, 50, 50);
	private boolean caught = false;
	public CatchFish() {
		System.out.println("Created task Catch Fish");
	}
	public boolean isFished(Protagonist p, int x, int y) {
		Rectangle r = new Rectangle(x, y, 50, 50);// adjust these #'s
		if (r.intersects(p.getRect())) {
			return true;
		} else {
			return false;
		}
	
	}
	public void update(Protagonist p, int x, int y, Graphics g) {
		if(isFished(p, x, y)) {
			setCompleted();
		}else {
			g.drawString("Press c to start catching fish", 0, 100);
		}
		if(caught) {
			g.drawString("Fish Caught!!!!", 0, 100);
		}
	}
	public void catchFish(KeyEvent e) {
		if(e.getKeyCode() == 67) {
			caught = true; 
			
		}
	}
}
