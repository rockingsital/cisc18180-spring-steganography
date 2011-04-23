package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GUI extends JPanel implements ActionListener{
	
	JTextField pictureLocation, password, desiredLocation;
	
	public GUI(){
		
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
		Desktop desktop = Desktop.getDesktop();
		try{
			desktop.open(fromFile);
		} catch(IOException e){
			System.out.println("File does not exist");
		}
	}
	
	public static void createAndShowGUI(){
		
		JFrame frame = new JFrame("Steganography");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 200);
        frame.getContentPane().add(new GUI());
        frame.setVisible(true);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		createAndShowGUI();
	}

}
