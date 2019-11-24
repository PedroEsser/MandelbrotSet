package Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Video.Video;
import logic.Logic;
import utils.Complex;

public class Menu {

	public static JTextField hueTextField, saturationTextField, zoomTextField,
	centerATextField, centerBTextField, speedTextField, fpsTextField, depthTextField;
	private static JFrame frame;
	public static void constructMenu() {
		frame = new JFrame("Menu") {
			@Override
			public void dispose() {
				toggle();
			}
		};
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JPanel colorPanel = new JPanel();
		colorPanel.setLayout(new GridLayout(3, 2));
		JLabel hueLabel = new JLabel("Hue: ");
		hueTextField = new JTextField("0");
		JLabel saturationLabel = new JLabel("Saturation: ");
		saturationTextField = new JTextField("1");
		JLabel zoomLabel = new JLabel("Current zoom: ");
		zoomTextField = new JTextField("1");
		colorPanel.add(hueLabel);
		colorPanel.add(hueTextField);
		colorPanel.add(saturationLabel);
		colorPanel.add(saturationTextField);
		colorPanel.add(zoomLabel);
		colorPanel.add(zoomTextField);
		frame.add(colorPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(3,2));
		JLabel centerALabel = new JLabel("Center a:");
		centerATextField = new JTextField("0", 20);
		JLabel centerBLabel = new JLabel("Center b:");
		centerBTextField = new JTextField("0", 20);
		JButton leftButton = new JButton("<<<<");
		leftButton.addActionListener(e -> {
			CoolCenters.go(-1);
		});
		JButton rightButton = new JButton(">>>>");
		rightButton.addActionListener(e -> {
			CoolCenters.go(1);
		});
		centerPanel.add(centerALabel);
		centerPanel.add(centerATextField);
		centerPanel.add(centerBLabel);
		centerPanel.add(centerBTextField);
		centerPanel.add(leftButton);
		centerPanel.add(rightButton);
		frame.add(centerPanel, BorderLayout.CENTER);
		
		JPanel videoPanel = new JPanel();
		videoPanel.setLayout(new GridLayout(4, 2));
		JLabel speedLabel = new JLabel("Speed: ");
		speedTextField = new JTextField("5");
		JLabel fpsLabel = new JLabel("FPS: ");
		fpsTextField = new JTextField("30");
		JLabel depthLabel = new JLabel("Final zoom: ");
		depthTextField = new JTextField("100");
		JButton apply = new JButton("Apply!");
		apply.addActionListener(e -> {
			applyGUI();
			GUI.updateImage();
		});
		JButton videoButton = new JButton("Generate Video!");
		videoButton.addActionListener(e -> {
			applyGUI();
			generateVideo();
		});
		videoPanel.add(speedLabel);
		videoPanel.add(speedTextField);
		videoPanel.add(fpsLabel);
		videoPanel.add(fpsTextField);
		videoPanel.add(depthLabel);
		videoPanel.add(depthTextField);
		videoPanel.add(apply);
		videoPanel.add(videoButton);
		frame.add(videoPanel, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	private static void generateVideo() {
		if(Logic.video==null || Logic.video.hasRendered()) {
			float hueOffset = Float.parseFloat(hueTextField.getText());
			float saturation = Float.parseFloat(saturationTextField.getText());
			double a = Double.parseDouble(centerATextField.getText());
			double b = Double.parseDouble(centerBTextField.getText());
			Complex center = new Complex(a, b);
			double speed = Double.parseDouble(speedTextField.getText());
			int fps = Integer.parseInt(fpsTextField.getText());
			double depth = convertZoom(Double.parseDouble(depthTextField.getText()));
			Video v = new Video(speed, fps, depth, hueOffset, saturation, center);
			Logic.setVideo(v);
		}
		
	}
	
	public static void setCenter(Complex center) {
		centerATextField.setText(center.a+"");
		centerBTextField.setText(center.b+"");
	}
	
	private static double convertZoom(double zoom) {
		return 1/(zoom*150);
	}
	
	private static void applyGUI() {
		GUI.guiImageValues.hueOffset = Float.parseFloat(hueTextField.getText());
		GUI.guiImageValues.saturation = Float.parseFloat(saturationTextField.getText());
		GUI.guiImageValues.center.a = Double.parseDouble(centerATextField.getText());
		GUI.guiImageValues.center.b = Double.parseDouble(centerBTextField.getText());
		GUI.guiImageValues.zoom = convertZoom(Double.parseDouble(zoomTextField.getText()));
		toggle();
	}
	
	private static void updateTextFields() {
		hueTextField.setText(GUI.guiImageValues.hueOffset + "");
		saturationTextField.setText(GUI.guiImageValues.saturation + "");
		zoomTextField.setText(convertZoom(GUI.guiImageValues.zoom) + "");
		centerATextField.setText(GUI.guiImageValues.center.a + "");
		centerBTextField.setText(GUI.guiImageValues.center.b + "");
	}
	
	public static void toggle() {
		if(!frame.isVisible())
			updateTextFields();
		frame.setVisible(!frame.isVisible());
	}
	
}
