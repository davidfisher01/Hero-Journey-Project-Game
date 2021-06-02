package island;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;

public class Checklist {
	
	protected ArrayList<Task> tasklist;
	private Image img;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public Checklist() {
		tasklist = new ArrayList<>();
		Task catchFish = new Task();
		Task buildBridge = new Task();
		Task save = new Task();
		Task collectFlowers = new Task();
		//WalkToTown getToTown = new WalkToTown(g);
		tasklist.add(catchFish);
		tasklist.add(buildBridge);
		tasklist.add(save);
		tasklist.add(collectFlowers);
		//tasklist.add(getToTown);
		
		img = getImage("clipboard.png");
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
