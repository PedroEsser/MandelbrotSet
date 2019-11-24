package Graphics;

import utils.Complex;

public class ImageValues {

	private static final double DEFAULTZOOM=1/150d;
	public double zoom;
	public float hueOffset;
	public float saturation;
	public Complex center;
	
	public ImageValues(double zoom, float hueOffset, float saturation, Complex center) {
		this.zoom = zoom;
		this.hueOffset = hueOffset;
		this.saturation = saturation;
		this.center = center;
	}
	
	public ImageValues() {
		this(DEFAULTZOOM, .65f, -35, new Complex(0,0));
	}
	
}
