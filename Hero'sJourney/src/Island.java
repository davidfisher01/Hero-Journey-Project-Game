import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Island extends JPanel implements ActionListener, MouseListener {

	public Island() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Island");
		frame.setSize(800, 800);
		frame.add(this);
		frame.addMouseListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Timer t = new Timer(16, this);
		t.start();
		frame.setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//  -- Image Mapping --
		BufferedImage grass_image = null;
		BufferedImage sand_image = null;
		BufferedImage water_image = null;
		try {
			water_image = ImageIO.read(new URL("https://re-mm-assets.s3.amazonaws.com/product_photo/46476/large_large_PolyTurquoise-2_7467U_1471509906.jpg"));
			sand_image = ImageIO.read(new URL("https://www.familybedding.com/img/p/l/3/2/6/9/130614/Sand_Solid_Color_16_Square_Throw_Pillow.jpg"));
			grass_image = ImageIO.read(new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Green_square.svg/1200px-Green_square.svg.png"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int x = 0; x < 400; x +=50 ) {
			for(int y = 0; y < 400; y+=50) {
		Image scaledImage = grass_image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		g.drawImage(scaledImage, x, y, null);
		g.drawImage(scaledImage, x + 50, y + 100, null);
		}
		}
		for(int x = 50; x < 800; x +=50 ) {
			for(int y = 500; y < 800; y+=50) {
		Image scaledImage = grass_image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		g.drawImage(scaledImage, x, y, null);
		}
		}
		Image scaledImage = sand_image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		g.drawImage(scaledImage, 400, 0, null);
		g.drawImage(scaledImage, 0, 350, null);
		for(int i =350; i < 800; i+=50) {
			g.drawImage(scaledImage, 50, i, null);
			
		}
		g.drawImage(scaledImage, 400, 50, null);
		for(int i =50; i < 450; i+=50) {
			g.drawImage(scaledImage, 450, i, null);
		}
		for(int i =450; i < 800; i+=50) {
			g.drawImage(scaledImage, i, 450, null);
		}
		Image w_scaledImage = water_image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		g.drawImage(w_scaledImage, 450, 0, null);
		for(int x = 500; x < 800; x+=50) {
			for(int y = 0; y < 450; y+=50) {
				g.drawImage(w_scaledImage, x, y, null);
			}
		}
		for(int y = 400; y < 800; y +=50) {
			g.drawImage(w_scaledImage, 0, y, null);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
