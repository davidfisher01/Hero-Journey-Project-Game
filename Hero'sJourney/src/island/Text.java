package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Text {
	private String str;

	public Text(String str) {
		this.str = str;
	}
	
	public void print(Graphics g, Font f, int width, int height) {
		//TODO add speaking capabilities specific to ninjas
		g.setColor(Color.orange);
		g.setFont(f);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		drawString(g, str, 0, 0);
		
	}
	
	public void drawString(Graphics g, String text, int x, int y) {
	    for (String line : text.split("\n"))
	        g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}
}
