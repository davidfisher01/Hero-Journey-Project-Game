package island;

import java.util.ArrayList;

public class IslandBackground extends Sprite{
	private int vx, vy;
	public ArrayList<ColRects> iCol = new ArrayList<ColRects>();

	public IslandBackground(String fileName, int width, int height) {
		super(fileName, width, height);
		vx = -4700;
		vy = -4200;
		this.width = width;
		this.height = height;
		
		//trees
		iCol.add(new ColRects(464, -131, 250, 200));
		iCol.add(new ColRects(1344, 34, 250, 200));
		iCol.add(new ColRects(779, -491, 250, 200));
		iCol.add(new ColRects(-246, 49, 250, 200));
		iCol.add(new ColRects(1414, -476, 250, 200));
		iCol.add(new ColRects(1299, -906, 250, 200));
		iCol.add(new ColRects(779, -1041, 250, 200));
		iCol.add(new ColRects(1509, -2541, 250, 200));
		iCol.add(new ColRects(-1546, -2476, 250, 200));
		iCol.add(new ColRects(-1751, -2611, 250, 200));
		iCol.add(new ColRects(-1826, -2261, 250, 200));
		iCol.add(new ColRects(-2056, -2361, 250, 200));
		iCol.add(new ColRects(-2321, -2371, 250, 200));
		iCol.add(new ColRects(-2581, -2371, 250, 200));
		iCol.add(new ColRects(-2841, -2506, 250, 200));
		iCol.add(new ColRects(-3401, -2276, 250, 200));
		iCol.add(new ColRects(-456, -1051, 250, 200));
		iCol.add(new ColRects(-1126, -826, 250, 200));
		iCol.add(new ColRects(-2791, -621, 250, 200));
		iCol.add(new ColRects(-1806, -181, 250, 200));
		iCol.add(new ColRects(-1341, -2131, 250, 200));
		
		//beach tree
		iCol.add(new ColRects(-1651, 249, 140, 125));
		iCol.add(new ColRects(-2836, 114, 140, 125));
		
		
		//houses
		iCol.add(new ColRects(-2806, -1081, 555, 280));
		iCol.add(new ColRects(-2796, -1671, 530, 425));
		iCol.add(new ColRects(-2231, -1846, 535, 605));
		iCol.add(new ColRects(-1441, -1846, 320, 590));
		iCol.add(new ColRects(-3216, -3176, 1690, 520));
		iCol.add(new ColRects(-2831, -3316, 930, 800));
		iCol.add(new ColRects(619, -1601, 535, 395));
		
		//cliff
		iCol.add(new ColRects(-721, 294, 1075, 210));
		iCol.add(new ColRects(374, 304, 675, 95));
		iCol.add(new ColRects(1269, 299, 465, 90));
		
		//water
		iCol.add(new ColRects(-3061, 604, 3580, 80));
		iCol.add(new ColRects(519, 619, 0, 65));
		iCol.add(new ColRects(564, 669, 845, 60));
		iCol.add(new ColRects(1414, 589, 95, 195));
		iCol.add(new ColRects(1464, 559, 135, 175));
		iCol.add(new ColRects(1504, 529, 150, 185));
		iCol.add(new ColRects(1549, 499, 480, 180));
		iCol.add(new ColRects(1934, 374, 125, 300));
		iCol.add(new ColRects(1989, -2706, 90, 3290));
		iCol.add(new ColRects(-41, -2886, 2080, 280));
		iCol.add(new ColRects(-126, -2861, 190, 185));
		iCol.add(new ColRects(-336, -2981, 260, 200));
		iCol.add(new ColRects(-501, -3076, 310, 195));
		iCol.add(new ColRects(-646, -3191, 380, 200));
		iCol.add(new ColRects(-786, -3306, 385, 205));
		iCol.add(new ColRects(-901, -3476, 370, 305));
		iCol.add(new ColRects(-3726, -3471, 2645, 155));
		iCol.add(new ColRects(-3906, -3381, 260, 1560));
		iCol.add(new ColRects(-3781, -1946, 765, 200));
		iCol.add(new ColRects(-3176, -1946, 155, 705)); //this is the one 3286, 1181
		iCol.add(new ColRects(-3421, -1386, 350, 145)); //this is the one
		iCol.add(new ColRects(-3541, -1321, 215, 380));
		iCol.add(new ColRects(-3496, -1056, 480, 120));
		iCol.add(new ColRects(-3166, -1066, 160, 1820));
		iCol.add(new ColRects(-696, -771, 45, 15));
		iCol.add(new ColRects(-1116, -446, 210, 930));
		iCol.add(new ColRects(-906, -441, 1200, 145));
		iCol.add(new ColRects(79, -1081, 215, 740));
		iCol.add(new ColRects(69, -1486, 220, 280));
		iCol.add(new ColRects(-446, -2056, 905, 550));
		iCol.add(new ColRects(479, -1911, 180, 190));
		iCol.add(new ColRects(624, -2056, 540, 425));
	}
	
	public void update() {
		x += vx;
		y += vy;
		
		tx.setToTranslation(x, y);
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}
	
	public int getColSize() {
		return iCol.size();
	}
	
}
