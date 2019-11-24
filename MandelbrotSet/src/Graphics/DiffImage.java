package Graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import utils.Complex;

public class DiffImage extends Thread{

	/*private final LinkedList<PointDiffTuple> diffs;
	private final Video video;
	private final BufferedImage previousImage;
	private static final int MAXWORKERS = 20;
	//helper constants
	private static final int help = 0xff;
	//private static final int sizeDiv2 = GUI.SIZE/2;
	//private static final int sizeSquared = GUI.SIZE*GUI.SIZE;
	private final double rangeDivSize;
	private final int iterations;
	
	private int currentPoint = 0;
	
	public DiffImage(Video video, BufferedImage previous, double range) {
		diffs = new LinkedList<>();
		this.video = video;
		this.previousImage = previous;
		rangeDivSize = range/GUI.SIZE;
		iterations = GUI.getIterations(range);
	}
	
	private void checkColorAt(int point) {
		int x = getX(point);
		int y = getY(point);
		
		float aux = (float)video.center.added(new Complex((x-sizeDiv2)*rangeDivSize, (y-sizeDiv2)*rangeDivSize)).getPercent(iterations);
		int rgb = Color.HSBtoRGB(aux + video.hueOffset, video.saturation, aux);
		if(differentEnough(rgb, previousImage.getRGB(x, y))) {
			previousImage.setRGB(x, y, rgb);
			addPointDiffTuple(new PointDiffTuple(point, rgb));
		}
	}
	
	public static boolean differentEnough(int a, int b) {
		int diff = abs((a&help) - (b&help));
		if(diff > 10)
			return true;
		a>>=8;
		b>>=8;
		diff += abs((a&help) - (b&help));
		if(diff > 10)
			return true;
		a>>=8;
		b>>=8;
		diff += abs((a&help) - (b&help));
		if(diff > 10)
			return true;
		return false;
	}
	
	private static int abs(int a) {
		if(a<0)
			return -a;
		return a;
	}
	
	private void addPointDiffTuple(PointDiffTuple p) {
		synchronized (diffs) {
			diffs.add(p);
		}
	}
	
	public void applyDiffs(BufferedImage img) {
		//System.out.println(diffs.getFirst());
		//System.out.println(diffs);
		//diffs.add(new PointDiffTuple(0, 0));
		diffs.forEach(p -> img.setRGB(getX(p.point), getY(p.point), p.diff));
	}
	
	public int getNumberOfDiffs() {
		return diffs.size();
	}
	
	private static int getX(int point) {
		return point/GUI.SIZE;
	}
	
	private static int getY(int point) {
		return point%GUI.SIZE;
	}
	
	private synchronized int getNextPoint() {
		return currentPoint++;
	}
	
	@Override
	public void run() {
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
	}
	
	private class Worker extends Thread{
		
		@Override
		public void run() {
			int nextPoint = getNextPoint();
			while(currentPoint < sizeSquared) {
				checkColorAt(nextPoint);
				nextPoint = getNextPoint();
			}
		}
		
	}
	
	private class PointDiffTuple{
		
		private int point;
		private int diff;
		
		public PointDiffTuple(int point, int diff) {
			this.point = point;
			this.diff = diff;
		}
		
	}*/
	
}
