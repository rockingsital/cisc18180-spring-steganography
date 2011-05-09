/**
 * Authors: Ron Lewis, Kyle Tucker, Steve Herbein
 * CISC181 Spr 11 Project, Dr. Harvey, TA: Sunny Singh
 * This file contains the class for the GUI for this project. The GUI contains four text fields and
 * a button that allow for encoding and decoding (with encryption and decryption, respectively). The
 * GUI is smart in the sense that it can detect whether or not the user wants to encode or decode, and
 * then it performs the action. Once completed, it shows the final product.
 */

package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

@SuppressWarnings("serial")
public class GUI extends JPanel implements ActionListener{

	JTextField pictureLocation, password, secret,desiredLocation;
	static JFrame frame;
	SpringLayout layout;
	static int textFieldHeight, textFieldWidth;

	public GUI(){

		layout = new SpringLayout();
		setLayout(layout);
		JPanel panel = new JPanel();
		JComponent infoField = createInformationField();
		JComponent buttonField = createButton();
		panel.add(infoField);
		panel.add(buttonField);
		layout.putConstraint(SpringLayout.WEST, infoField, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, infoField, 5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, buttonField, 5, SpringLayout.EAST, infoField);
		layout.putConstraint(SpringLayout.NORTH, buttonField, 30, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, this, 5, SpringLayout.EAST, buttonField);
		layout.putConstraint(SpringLayout.SOUTH, this, 5, SpringLayout.SOUTH, infoField);
		add(panel);
		return;

	}

	public JComponent createInformationField(){

		JPanel panel = new JPanel(new SpringLayout());
		String[] labelStrings = {
				"Picture location and name: ",
				"Password: ",
				"Secret Information",
				"Output's location and name: "
		};
		JLabel[] labels = new JLabel[labelStrings.length];
		JTextField[] fields = new JTextField[labelStrings.length];

		pictureLocation = new JTextField();
		pictureLocation.setColumns(20);
		fields[0] = pictureLocation;

		password = new JTextField();
		password.setColumns(20);
		fields[1] = password;

		secret = new JTextField();
		secret.setColumns(20);
		fields[2] = secret;

		desiredLocation = new JTextField();
		desiredLocation.setColumns(20);
		fields[3] = desiredLocation;

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
		String secretInfo = secret.getText();
		File toFile = new File(desiredLocation.getText());
		if (fromFile.toString().contentEquals("") || 
				toFile.toString().contentEquals("")){
			System.out.println("Please fill in the appropriate fields.");
		}
		else{
			if (!pass.contentEquals("") && !secretInfo.contentEquals("")){
				System.out.println("You can't enter both a password and secret information.");
			}
			else if (pass.contentEquals("")){
				if (isPicture(secretInfo)){
					String writeTo = desiredLocation.getText();
					File hideThis = new File(secretInfo);
					File hideIn = fromFile;
					EncodeAndDecode.encodePicture(hideThis, hideIn, writeTo);
				}
				else{
					EncodeAndDecode.encodeText(fromFile, toFile, secretInfo);
				}
				ImagePanel imgPanel = new ImagePanel(desiredLocation);
				add(imgPanel);
				this.resize(imgPanel);
			}
			else if (secretInfo.contentEquals("")){
				EncodeAndDecode.decode(fromFile, toFile, pass);
			}
			else{
				System.out.println("Please enter EITHER a password OR your secret information.");
			}
		}
		return;
	}

	public boolean isPicture(String secretInfo){
		if(secretInfo.length() > 0){
			return (secretInfo.charAt(0) == 'C' && secretInfo.charAt(1) == ':' && secretInfo.charAt(2) == '\\') ||
			(secretInfo.charAt(0) == '/');
		}
		else
			return false;
	}

	public void resize(ImagePanel imgPanel){

		layout.putConstraint(SpringLayout.NORTH, imgPanel, textFieldHeight, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, imgPanel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, this, 5, SpringLayout.EAST, imgPanel);
		layout.putConstraint(SpringLayout.SOUTH, this, 5, SpringLayout.SOUTH, imgPanel);
		if (imgPanel.image.getWidth() > textFieldWidth){
			frame.setSize(imgPanel.image.getWidth() + 5,
					textFieldHeight + imgPanel.image.getHeight() + 5);
		}
		else{
			frame.setSize(textFieldWidth,
					textFieldHeight + imgPanel.image.getHeight() + 5);
		}
		this.updateUI();
		return;
	}

	public static void createAndShowGUI(){

		frame = new JFrame("Steganography");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GUI());
		frame.pack();
		frame.setVisible(true);
		textFieldWidth = frame.getWidth();
		textFieldHeight = frame.getHeight();
		return;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		createAndShowGUI();
		return;
	}

}