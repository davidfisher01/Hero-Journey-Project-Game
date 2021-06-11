package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class WalkToTown extends Task{
	Rectangle r;
	
	public WalkToTown() {
		System.out.println("Created task WalkToTown");
	}
	
	public void update(Protagonist p, int x, int y, Graphics g) {
		if(isAtTown(p, x, y)) {
			setCompleted();
		}
	}
	
	public boolean isAtTown(Protagonist p, int x, int y) {
		r = new Rectangle(x + 300, y - 1000, 1700, 50);// adjust these #'s
		return r.intersects(p.getRect());
	}
}
