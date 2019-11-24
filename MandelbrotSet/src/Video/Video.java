package Video;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Graphics.GUI;
import Graphics.ImageValues;
import Graphics.RGBImage;
import utils.Complex;

public class Video{

	private final RGBImage[] video;
	private static final int MAXWORKERS = 200;
	private static final double PERCENTAGEPERSECOND = 1;
	private final int sleepTime;
	private final double percentagePerFrame;
	private ImageValues videoImageValues;
	private boolean hasRendered;
	public boolean isDisplaying;
	private int current;
	private int percentCounter;
	private static final int KILO = 1 << 10;
	private static final int MEGA = KILO << 10;
	private static final int GIGA = MEGA << 10;
	
	public Video(double speed, int fps, double depth, float hueOffset, float saturation, Complex center) {
		videoImageValues = new ImageValues(1/150d, hueOffset, saturation, center);
		percentagePerFrame = 1-speed*PERCENTAGEPERSECOND/fps;
		int totalFrames = (int)(Math.log(depth/videoImageValues.zoom)/Math.log(percentagePerFrame));
		video = new RGBImage[totalFrames];
		this.sleepTime = 1000/fps;
	}
	
	public void displayVideo() {
		new Thread(() -> {
			long timeStamp = System.currentTimeMillis();
			isDisplaying=true;
			for(int i=0 ; i<video.length ; i++) {
				GUI.img=video[i];
				GUI.panel.updateUI();
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
				}
			}
			GUI.guiImageValues.zoom = videoImageValues.zoom;
			GUI.img=null;
			isDisplaying=false;
			double secondsPassed = (System.currentTimeMillis()-timeStamp)/1000.0;
			System.out.println("Video duration: " + secondsPassed + " seconds");
		}).start();
	}
	
	public void readVideo() {
		
	}
	
	public void generateVideo() {
		new Thread(() ->  {
			hasRendered = false;
			System.out.println("Generating video...");
			long timeStamp=System.currentTimeMillis();
			percentCounter = 0;
			LinkedList<Worker> workers = new LinkedList<>();
			for(int i=0 ; i<MAXWORKERS ; i++) {
				Worker w = new Worker();
				workers.add(w);
				w.start();
			}
			for(Worker w : workers)
				try {
					w.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			double secondsPassed = (System.currentTimeMillis()-timeStamp)/1000.0;
			System.out.println("Video took " + secondsPassed + " seconds to render.");
			System.out.println("Currently using " + Runtime.getRuntime().totalMemory()/MEGA + " Megabytes");
			hasRendered = true;
		}).start();
	}
	
	private synchronized RGBImage nextImage() {
		if(current == video.length)
			return null;
		RGBImage img = new RGBImage(videoImageValues);
		video[current++]=img;
		videoImageValues.zoom*=percentagePerFrame;
		return img;
	}
	
	private void updatePercent() {
		if(percentCounter*10/video.length != ++percentCounter*10/video.length) 
			System.out.println(percentCounter*100/video.length + " %");
	}
	
	public boolean hasRendered() {
		return hasRendered;
	}
	
	public boolean isDisplaying() {
		return isDisplaying;
	}
	
	private class Worker extends Thread{
		
		@Override
		public void run() {
			RGBImage img = nextImage();
			while(img != null){
				img.generateImage();
				updatePercent();
				img=nextImage();
			}
		}
		
	}
	
}
