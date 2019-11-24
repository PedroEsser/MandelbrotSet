package Graphics;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.KeyHandler;
import utils.Complex;

public class GUI {
	
	public static int WIDTH = 600;
	public static int HEIGHT = 600;
	public static ImageValues guiImageValues;
	public static JPanel panel;
	static int BASEITERATIONS = -100;
	public static RGBImage img;
	
	public GUI() {
		guiImageValues = new ImageValues();
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(3);
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				if(img!=null) 
					g.drawImage(img, 0, 0, null);
			}
		};
		panel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				guiImageValues.center.add(new Complex((double)(e.getX()-WIDTH/2)*guiImageValues.zoom, (double)(e.getY()-HEIGHT/2)*guiImageValues.zoom));
				updateImage();
				System.out.println(guiImageValues.center);
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		panel.addMouseWheelListener(e -> {
			int sign = e.getWheelRotation() < 0 ? 1 : -1;
			guiImageValues.zoom*=Math.pow(.9, sign * e.getScrollAmount());
			updateImage();
		});
		frame.addKeyListener(new KeyHandler());
		updateImage();
		frame.add(panel);
		frame.setVisible(true);
	}
	
	public static void updateImage() {
		img = new RGBImage(guiImageValues);
		img.generateImage();
		panel.updateUI();
	}
	
	public static int getIterations(double zoom) {
		return BASEITERATIONS - (int)(Math.log10(zoom)*300);
	}
	
	public static void increaseBaseIterations(int value) {
		BASEITERATIONS+=value;
		updateImage();
	}
	
	public static void zoom(double percentage) {
		guiImageValues.zoom *= percentage;
		updateImage();
	}
	
	//temporary
	public static void saveImgPNG() {
		try {
			ImageIO.write(img, "png", new File("images/test.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
