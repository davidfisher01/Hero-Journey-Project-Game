package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Text {
	private String str;
	private boolean isPrint, didPrint;
	private Extra character;
	private String filename;
	private int charSize;
	
	public Text(String str, String filename, int charSize) {
		this.str = str;
		isPrint = false;
		didPrint = false;
		this.filename = filename;
		this.charSize = charSize;
		
		character = new Extra(filename, 50, 50, charSize, charSize);
		
	}
	
	public void print(Graphics g, Font f, int width, int height) {
		//TODO add speaking capabilities specific to ninjas
		g.setColor(Color.orange);
		g.setFont(f);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		drawString(g, str, 0, 0);
		
		character.setX(width - charSize);
		character.setY(0);
		character.paint(g);
		
		didPrint = true;
	}
	
	public void notPrint(Graphics g, Font f, int width, int height) {
		//TODO add speaking capabilities specific to ninjas
		g.setColor(Color.orange);
		g.setFont(f);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		drawString(g, "press 'r' to print", 0, 0);
		
	}
	
	public void drawString(Graphics g, String text, int x, int y) {
	    for (String line : text.split("\n"))
	        g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public boolean isPrint() {
		return isPrint;
	}
	
	public boolean isDidPrint() {
		return didPrint;
	}

	public void setDidPrint(boolean didPrint) {
		this.didPrint = didPrint;
	}
	public void setPrint(boolean isPrint) {
		this.isPrint = isPrint;
	}
}
