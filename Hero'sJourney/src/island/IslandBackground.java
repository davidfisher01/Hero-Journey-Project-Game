package island;

public class IslandBackground extends Sprite{
	private int vx, vy;

	public IslandBackground(String fileName, int width, int height) {
		super(fileName, width, height);
		vx = -3100;
		vy = -2925;
		this.width = width;
		this.height = height;
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
	
}
