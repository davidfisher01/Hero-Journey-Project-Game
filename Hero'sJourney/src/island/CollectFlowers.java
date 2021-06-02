package island;

import java.awt.Graphics;
import java.awt.Rectangle;

public class CollectFlowers {
	
	public boolean flowerOne, flowerTwo, flowerThree;
	
	public CollectFlowers() {
		flowerOne = false;
		flowerTwo = false;
		flowerThree = false;
		
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
		if (isCollected(p, x, y)) {
			flowerOne = true;
			System.out.println("flower one picked");
		}
		
		//flowerone
		if (isCollected(p, x + 100, y)) {
			flowerTwo = true;
			System.out.println("flower two picked");
		}
				
		//flowerone
		if (isCollected(p, x + 200, y)) {
			flowerThree = true;
			System.out.println("flower three picked");
		}
		
		if (finishedFlowerPicking()) {
			//setCompleted(); 		//uncomment once you push code
		} else {
			g.drawString("pic some flowers g", 0, 100);
		}
		
	}
	
	public boolean finishedFlowerPicking() {
		if (flowerOne == true && flowerTwo == true && flowerThree == true) {
			return true;
		} else {
			return false;
		}
	}
}
