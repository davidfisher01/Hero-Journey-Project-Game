import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Protagonist {
	
	private int x, y;
	private int width, height;
	
	private Image img;
	
	public Protagonist(String file, int width, int height) {
		x = 0;
		y = 0;
		this.width = width;
		this.height = height;
		
		img = getImage(file);
		img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	}
	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(img, tx, null);
		
		tx.setToTranslation(x, y);
	}
	
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Protagonist.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
