package island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ninja extends Extra {

	public Ninja(String fileName, int x, int y, int width, int height) {
		super(fileName, x, y, height, height);
		
	}
	
	public boolean isIntersectN(Protagonist p) {
		return p.northRect().intersects(getRect());
	}
	
}
