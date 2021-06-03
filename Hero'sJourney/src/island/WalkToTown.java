package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class WalkToTown extends Task{

	
	Rectangle r = new Rectangle(0, 0, 50, 50);
	private Image img;
	public WalkToTown() {
		System.out.println("Created task WalkToTown");
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
			g.setColor(Color.BLACK);
			img = getImage("tickmark.png");
			img = img.getScaledInstance(10, 10, Image.SCALE_DEFAULT);
			g.drawImage(img, 100, 580, null);
		}else {
			g.drawString("Navigate up the path to the town to find the blacksmith!!!", 0, 100);
		}
	}
}
