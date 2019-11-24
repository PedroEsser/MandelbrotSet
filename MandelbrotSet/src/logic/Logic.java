package logic;

import java.awt.Color;
import java.util.Arrays;

import Graphics.DiffImage;
import Graphics.GUI;
import Graphics.Menu;
import Video.Video;
import utils.Complex;

public class Logic {

	static GUI gui;
	public static Video video;
	
	public static void main(String[] args) {
		Menu.constructMenu();
		gui = new GUI();
	}
	
	public static void setVideo(Video v) {
		video = v;
		v.generateVideo();
	}
	
	public static void displayVideo() {
		if(video==null || !video.hasRendered())
			System.out.println("Video hasn't rendered yet");
		else
			video.displayVideo();
	}
	
}
