package island;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Task {
	protected boolean completed;
	
	public Task() {
		completed = false;
	}

	public void setCompleted() {
		completed = true;
	}
	
	public boolean isCompleted() {
		return completed;
	}

	protected Image getImage(String path) {
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
