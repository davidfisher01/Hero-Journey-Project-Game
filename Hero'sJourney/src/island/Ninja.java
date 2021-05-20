package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ninja extends Extra {

	public Ninja(String fileName, int x, int y, int width, int height) {
		super(fileName, x, y, height, height);
		
	}
	
	public void speak(Graphics g, Protagonist p, Font f, int width, int height) {
		//TODO add speaking capabilities specific to ninjas
		if (p.northRect().intersects(getRect())) {
			g.setColor(Color.orange);
			g.setFont(f);
			g.fillRect(0, 0, width, height/4);
			g.setColor(Color.black);
			drawString(g, "and then he touched with his lips, \r\n" + 
					"together we became. One Forever. \r\n" + 
					"And when he took of his shirt \r\n" +
					"I laughed fo he was an outie", 0, 0);
		}
		
	}
	
	public void drawString(Graphics g, String text, int x, int y) {
	    for (String line : text.split("\n"))
	        g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}
	
}
