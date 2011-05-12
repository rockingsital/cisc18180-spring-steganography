package project;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel implements Scrollable{

	BufferedImage image;
	private int maxUnitIncrement = 1;

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

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		
		return getPreferredSize();
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {

        if (orientation == SwingConstants.HORIZONTAL) {
            return visibleRect.width - maxUnitIncrement;
        } else {
            return visibleRect.height - maxUnitIncrement;
        }
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		
		return false;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		int currentPosition = 0;
        if (orientation == SwingConstants.HORIZONTAL) {
            currentPosition = visibleRect.x;
        } else {
            currentPosition = visibleRect.y;
        }
        if (direction < 0) {
            int newPosition = currentPosition -
                             (currentPosition / maxUnitIncrement)
                              * maxUnitIncrement;
            return (newPosition == 0) ? maxUnitIncrement : newPosition;
        } else {
            return ((currentPosition / maxUnitIncrement) + 1)
                   * maxUnitIncrement
                   - currentPosition;
        }
	}

}