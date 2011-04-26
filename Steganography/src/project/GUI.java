package project;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JPanel implements ActionListener{
	
	JTextField pictureLocation, password, desiredLocation;
	JComponent pictureDisplay;
	
	public GUI(){

		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		JPanel panel = new JPanel();
		panel.add(createInformationField());
		panel.add(createButton());
		add(panel);
		
	}
	
	public JComponent createInformationField(){
		
		JPanel panel = new JPanel(new SpringLayout());
		String[] labelStrings = {
				"Picture location: ",
				"Password: ",
				"New picture's location: "
		};
		JLabel[] labels = new JLabel[labelStrings.length];
		JTextField[] fields = new JTextField[labelStrings.length];
		
		pictureLocation = new JTextField();
		pictureLocation.setColumns(20);
		fields[0] = pictureLocation;
		
		password = new JTextField();
		password.setColumns(20);
		fields[1] = password;
		
		desiredLocation = new JTextField();
		desiredLocation.setColumns(20);
		fields[2] = desiredLocation;
		
		for(int count = 0; count < labelStrings.length; count = count + 1){
			labels[count] = new JLabel(labelStrings[count], JLabel.TRAILING);
			labels[count].setLabelFor(fields[count]);
			panel.add(labels[count]);
			panel.add(fields[count]);
			fields[count].addActionListener(this);
		}
		SpringUtilities.makeCompactGrid(panel, labelStrings.length, 2, 10, 10, 10, 5);
		
		return panel;
	}
	
	public JComponent createButton(){
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		
		JButton button = new JButton("Encode/Decode!");
		button.addActionListener(this);
		panel.add(button);
		
		return panel;
	}
	
	public void actionPerformed(ActionEvent action){
		
		File fromFile = new File(pictureLocation.getText());
		String pass = password.getText();
		File toFile = new File(desiredLocation.getText());
		ImagePanel imgPanel = new ImagePanel(desiredLocation);
		add(imgPanel);
		this.resize(imgPanel);
		}
	
	public void resize(ImagePanel imgPanel){
		
		System.out.println(this.getWidth());
		this.setSize(this.getWidth()+imgPanel.image.getWidth(),
						this.getHeight()+imgPanel.image.getHeight());
		System.out.println(this.getWidth());
		this.updateUI();
		this.setPreferredSize(new Dimension(200,150));
	}
	
	public static void createAndShowGUI(){
		
		JFrame frame = new JFrame("Steganography");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUI());
        frame.pack();
        frame.setVisible(true);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		createAndShowGUI();
	}

}