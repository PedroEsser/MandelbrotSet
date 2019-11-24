package logic;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import Graphics.GUI;
import Graphics.Menu;
import utils.Complex;

public class KeyHandler implements KeyListener{
	
	HashMap<Integer,Runnable> keyEvents;

	public KeyHandler() {
		keyEvents = new HashMap<>();
		keyEvents.put(KeyEvent.VK_X, () -> System.exit(0));
		keyEvents.put(KeyEvent.VK_PLUS, () -> {
			Logic.gui.guiImageValues.hueOffset+=.05;
			Logic.gui.updateImage();
		});
		keyEvents.put(KeyEvent.VK_MINUS, () -> {
			Logic.gui.guiImageValues.hueOffset-=.05;
			Logic.gui.updateImage();
		});
		keyEvents.put(KeyEvent.VK_UP, () -> {
			Logic.gui.guiImageValues.saturation+=1;
			Logic.gui.updateImage();
		});
		keyEvents.put(KeyEvent.VK_DOWN, () -> {
			Logic.gui.guiImageValues.saturation-=1;
			Logic.gui.updateImage();
		});
		keyEvents.put(KeyEvent.VK_RIGHT, () -> {
			Logic.gui.increaseBaseIterations(10);;
			Logic.gui.updateImage();
		});
		keyEvents.put(KeyEvent.VK_LEFT, () -> {
			Logic.gui.increaseBaseIterations(-10);
			Logic.gui.updateImage();
		});
		keyEvents.put(KeyEvent.VK_S, () -> GUI.saveImgPNG());
		keyEvents.put(KeyEvent.VK_T, () -> System.out.println(GUI.getIterations(GUI.guiImageValues.zoom)));
		keyEvents.put(KeyEvent.VK_M, () -> Menu.toggle());
		keyEvents.put(KeyEvent.VK_SPACE, () -> {
			if(!Logic.video.isDisplaying())
				Logic.displayVideo();
		});
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(keyEvents.containsKey(e.getKeyCode()))
			keyEvents.get(e.getKeyCode()).run();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}
