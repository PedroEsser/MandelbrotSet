package Graphics;

import java.awt.Color;

import utils.Complex;

public class LowRGBImage extends RGBImage{

	private final int pixelsize;
	private final int pixelsizeSquared;
	
	public LowRGBImage(ImageValues iv, int pixelsize) {
		super(iv);//WRONG
		this.pixelsize=pixelsize;
		this.pixelsizeSquared=pixelsize*pixelsize;
	}

	@Override
	public void generateImage() {
		for(int i=0 ; i<GUI.WIDTH ; i+=pixelsize) 
			for(int j=0 ; j<GUI.HEIGHT ; j+=pixelsize){
				int rgbAvg=0;
				float auxAvg=0;
				for(int k=0 ; k<pixelsize ; k++)
					for(int l=0 ; l<pixelsize; l++) {
						//float aux = (float)iv.center.added(new Complex(widthValues[i+k], heightValues[j+l])).getPercent(iterations);
						auxAvg+=(float)iv.center.added(new Complex(widthValues[i+k], heightValues[j+l])).getPercent(iterations);
						//rgbAvg+=Color.HSBtoRGB(aux + iv.hueOffset, iv.saturation, aux);
					}
				//rgbAvg/=pixelsizeSquared;
				auxAvg/=pixelsizeSquared;
				for(int k=0 ; k<pixelsize ; k++)
					for(int l=0 ; l<pixelsize; l++) {
						//setRGB(i+k, j+l, rgbAvg);
						setRGB(i+k, j+l, Color.HSBtoRGB(auxAvg + iv.hueOffset, iv.saturation, auxAvg));
					}
			}
	}
	
}
