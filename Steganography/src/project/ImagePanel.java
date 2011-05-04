package project;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImagePanel extends JPanel {

	BufferedImage image;

	public ImagePanel(JTextField desiredLocation){

		try{
			File fileName  = new File(desiredLocation.getText());
			image = ImageIO.read(fileName);
		} catch(IOException e){
			System.out.println("Unable to read file.");
		}

	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

}