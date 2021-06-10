package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Checklist {
	
	private WalkToTown wtt;
	private BuildBridge bb;
	private CollectFlowers cfl;
	private CatchFish cfi;
	private Image img;
	private boolean tOne, tTwo, tThree, tFour;
	private boolean canTalkTwo, canTalkThree, canTalkFour;
	private Font stringFont;

	public Checklist(int charSize) {
		wtt = new WalkToTown();
		bb = new BuildBridge(charSize);
		cfl = new CollectFlowers();
		cfi = new CatchFish();
		
		tOne = true;
		tTwo = false;
		tThree = false;
		tFour = false;
		
		canTalkTwo = true;
		canTalkThree = false;
		canTalkFour = false;
		
		stringFont = new Font( "SansSerif", Font.PLAIN, 15);
	}
	
	public void updateTasks(Protagonist p, int x, int y, Graphics g, Font f, int textWidth, int textHeight) {
		img = getImage("output-onlinepngtools.png");
		g.drawImage(img, 0, 500, null);
		g.setFont(stringFont);
		
		if (tOne) {
			taskOne(p, x, y, g);
		}
		
		if (tTwo) {
			taskTwo(p, x, y, g, f, textWidth, textHeight);
		}
		
		if (tThree) {
			taskThree(p, x, y, g);
		}
		
		if (tFour) {
			taskFour(p, x, y, g);
		}
	}
	
	//arrive at the blacksmith
	private void taskOne(Protagonist p, int x, int y, Graphics g) {
		wtt.update(p, x, y, g);
		g.setFont(stringFont);
		
		if (wtt.isCompleted() == false) {
			g.setColor(Color.black);
			g.drawString("Navigate to the town", 65, 580);
		} else {
			g.setColor(Color.blue);
			g.drawString("Found the town!", 65, 745);
			
			tOne = false;
		}
	}
	
	//build the bridge
	private void taskTwo(Protagonist p, int x, int y, Graphics g, Font f, int textWidth, int textHeight) {
		bb.update(p, x, y, g, f, textWidth, textHeight);
		g.setFont(stringFont);
		
		if (bb.isCompleted() == false) {
			g.setColor(Color.black);
			g.drawString("Build Bridge", 65, 580);
		} else {
			g.setColor(Color.blue);
			g.drawString("Bridge Built!", 65, 730);
			
			tTwo = false;
			canTalkTwo = false;
			canTalkThree = true;
		}
	}

	//collect the flowers
	private void taskThree(Protagonist p, int x, int y, Graphics g) {
		cfl.update(p, x, y, g);
		g.setFont(stringFont);
		
		if (cfl.isCompleted() == false) {
			g.setColor(Color.black);
			g.drawString("Collect Flowers", 65, 580);
		} else {
			g.setColor(Color.blue);
			g.drawString("Flowers Collected!", 65, 715);
			
			tThree = false;
			canTalkThree = false;
			canTalkFour = true;
			tFour = true;
		}
	}
	
	//catch the fish
	private void taskFour(Protagonist p, int x, int y, Graphics g) {
		
	}
	

	
	public boolean istOne() {
		return tOne;
	}

	public void settOne(boolean tOne) {
		this.tOne = tOne;
	}

	public boolean istTwo() {
		return tTwo;
	}

	public void settTwo(boolean tTwo) {
		this.tTwo = tTwo;
	}

	public boolean istThree() {
		return tThree;
	}

	public void settThree(boolean tThree) {
		this.tThree = tThree;
	}

	public boolean istFour() {
		return tFour;
	}

	public void settFour(boolean tFour) {
		this.tFour = tFour;
	}
	
	public boolean isBridgeBuilt() {
		return bb.isCompleted();
	}
	
	public boolean isCanTalkTwo() {
		return canTalkTwo;
	}

	public void setCanTalkTwo(boolean canTalkTwo) {
		this.canTalkTwo = canTalkTwo;
	}

	public boolean isCanTalkThree() {
		return canTalkThree;
	}

	public void setCanTalkThree(boolean canTalkThree) {
		this.canTalkThree = canTalkThree;
	}

	public boolean isCanTalkFour() {
		return canTalkFour;
	}

	public void setCanTalkFour(boolean canTalkFour) {
		this.canTalkFour = canTalkFour;
	}

	public WalkToTown getWtt() {
		return wtt;
	}

	public BuildBridge getBb() {
		return bb;
	}

	public CollectFlowers getCfl() {
		return cfl;
	}

	public CatchFish getCfi() {
		return cfi;
	}

	public Image getImg() {
		return img;
	}

	public Font getStringFont() {
		return stringFont;
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Sprite.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
