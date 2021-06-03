package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class WalkToTown extends Task{

	
	Rectangle r = new Rectangle(0, 0, 50, 50);
	public WalkToTown() {
		System.out.println("Created task");
	}
	public boolean isAtTown(Protagonist p, int x, int y) {
		Rectangle r = new Rectangle(x, y, 50, 50);// adjust these #'s
		if(r.intersects(p.getRect())) {
			
			return true;
		}
		return false;
	
	}
	public void update(Protagonist p, int x, int y, Graphics g) {
		
		if(isAtTown(p, x, y)) {
			setCompleted();
		}else {
			g.drawString("Navigate towards the town to find the blacksmith!!!", 0, 100);
		}
	}
}
