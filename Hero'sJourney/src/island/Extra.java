package island;

public class Extra extends Sprite {
	private int vx, vy;

	public Extra(String fileName, int x, int y, int width, int height) {
		super(fileName, width, height);
		vx = 0;
		vy = 0;
		this.x = x;
		this.y = y;
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
