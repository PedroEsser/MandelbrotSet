package Graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import utils.Complex;

public class RGBImage extends BufferedImage{
	
	
	private static final int widthDiv2 = GUI.WIDTH/2;
	private static final int heightDiv2 = GUI.HEIGHT/2;
	protected final ImageValues iv;
	protected final double[] widthValues, heightValues;
	protected final int iterations;
	
	public RGBImage(ImageValues iv, int width, int height) {
		super(width,height,BufferedImage.TYPE_3BYTE_BGR);
		double zoom = iv.zoom;
		this.iv=iv;
		widthValues = new double[width];
		heightValues = new double[height];
		for(int i=-widthDiv2 ; i < widthDiv2 ; i++)
			widthValues[i+widthDiv2]=i*zoom;
		for(int i=-heightDiv2 ; i < heightDiv2 ; i++)
			heightValues[i+heightDiv2]=i*zoom;
		this.iterations = GUI.getIterations(zoom);
	}
	
	public RGBImage(ImageValues iv) {
		this(iv, GUI.WIDTH,GUI.HEIGHT);
	}
	
	public void generateImage() {
		for(int i=0 ; i<GUI.WIDTH ; i++) 
			for(int j=0 ; j<GUI.HEIGHT ; j++) {
				float aux = (float)iv.center.added(new Complex(widthValues[i], heightValues[j])).getPercent(iterations);
				setRGB(i, j, Color.HSBtoRGB(aux + iv.hueOffset, iv.saturation, aux));
			}
	}
	
}
