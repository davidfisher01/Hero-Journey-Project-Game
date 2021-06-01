package island;

import java.awt.Graphics;
import java.awt.Rectangle;

public class WalkToTown extends Task{

	
	
	public WalkToTown(Graphics g) {
		g.drawString("Navigate towards the town to find the blacksmith!!!", 0, 100);
		System.out.println("Created task");
	}
	public boolean isAtTown(Protagonist p) {
		Rectangle r = new Rectangle(500, 800, 100, 100);  // adjust these #'s
		return r.intersects(p.getRect());
	
	}
	public void update(Protagonist p) {
		if(isAtTown(p)) {
			setCompleted();
			System.out.println("Done");
		}
	}
}
