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
	private Image img;

	public Checklist() {
		wtt = new WalkToTown();
	}
	
	public void updateTasks(Protagonist p, int x, int y, Graphics g) {
		Font stringFont = new Font( "SansSerif", Font.PLAIN, 15 );
		img = getImage("output-onlinepngtools.png");
		g.drawImage(img, 0, 500, null);
		g.setFont(stringFont);
		g.setColor(Color.BLACK);
		
		wtt.update(p, x, y, g);
		
		if (wtt.isCompleted() == false) {
			g.setColor(Color.black);
			g.drawString("Navigate to the town", 70, 580);
		} else {
			g.setColor(Color.blue);
			g.drawString("Navigate to the town", 70, 580);
		}
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
