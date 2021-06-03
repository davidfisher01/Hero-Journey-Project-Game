package island;

import java.awt.Graphics;
import java.awt.Rectangle;

public class CollectFlowers {
	
	public boolean flowerOne, flowerTwo, flowerThree;
	public int x1, y1; //x and y of flower one
	public int x2, y2; //x and y of flower two
	public int x3, y3; // x and y of flower three
	
	public CollectFlowers() {
		flowerOne = false;
		flowerTwo = false;
		flowerThree = false;
		x1 = 0;
		y1 = 0;
		x2 = 100;
		y2 = 100;
		x3 = 200;
		y3 = 200;
		
		System.out.println("created collectFlowers task");
	}
	
	public boolean isCollected(Protagonist p, int x, int y) {
		Rectangle r = new Rectangle(x, y, 50, 50);// adjust these #'s
		if (r.intersects(p.getRect())) {
			return true;
		} else {
			return false;
		}
	}
	
	public void update(Protagonist p, int x, int y, Graphics g) {
		//flowerone
		if (isCollected(p, x + x1, y + y1) && !flowerOne) {
			flowerOne = true;
			System.out.println("flower one picked");
		}
		
		//flowerone
		if (isCollected(p, x + x2, y + y2) && !flowerTwo) {
			flowerTwo = true;
			System.out.println("flower two picked");
		}
				
		//flowerone
		if (isCollected(p, x + x3, y + y3) && !flowerThree) {
			flowerThree = true;
			System.out.println("flower three picked");
		}
		
		if (finishedFlowerPicking()) {
			//setCompleted(); 		//uncomment once you push code
		} else {
			g.drawString("pic some flowers g", 0, 100);
		}
		if (!flowerOne) {
			g.drawRect(x + x1, y + y1, 50, 50);
		}
		if (!flowerTwo) {
			g.drawRect(x + x2, y + y2, 50, 50);
		}
		if (!flowerThree) {
			g.drawRect(x + x3, y + y3, 50, 50);
		}
		
	}
	
	public boolean finishedFlowerPicking() {
		if (flowerOne == true && flowerTwo == true && flowerThree == true	) {
			return true;
		} else {
			return false;
		}
	}
}
