/**
 * @author Ron Lewis, Stephen Herbein, and Kyle Tucker
 * 
 * This class allows for the creation of a JPanel that contains a BufferedImage. 
 * The image is also Scrollable (able to be placed inside a JScrollPane).
 */

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

	/**
	 * This is the constructor for this class. It takes a JTextField and reads an image
	 * from its text.
	 * 
	 * @param desiredLocation A JTextField containing the location of the image.
	 */
	
	public ImagePanel(JTextField desiredLocation){

		try{
			File fileName  = new File(desiredLocation.getText());
			image = ImageIO.read(fileName);
		} catch(IOException e){
			System.out.println("Unable to read file.");
		}

	}

	/**
	 * This method paints the image, and overrides the paint method in JPanel.
	 * 
	 * @param g
	 */
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

	/**
	 * This method is implemented from the Scrollable interface.
	 * 
	 * @return The preferred size of the panel
	 */
	
	@Override
	public Dimension getPreferredScrollableViewportSize() {
		
		return getPreferredSize();
	}

	/**
	 * This method is implemented from the Scrollable interface. It defines a 
	 * scrollable block increment.
	 * 
	 * @param visibleRect The current visible Rectangle
	 * @param orientation The orientation of the Rectangle
	 * @param direction The direction of the Rectangle
	 * @return The int that determines the scrollable block increment
	 */
	
	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {

        if (orientation == SwingConstants.HORIZONTAL) {
            return visibleRect.width - maxUnitIncrement;
        } else {
            return visibleRect.height - maxUnitIncrement;
        }
	}

	/**
	 * This method is implemented from the Scrollable interface.
	 * 
	 * @return the boolean value that determined the track's viewport height
	 */
	
	@Override
	public boolean getScrollableTracksViewportHeight() {
		
		return false;
	}

	/**
	 * This method is implemented from the Scrollable interface.
	 * 
	 * @return the boolean value that determined the track's viewport width
	 */
	
	@Override
	public boolean getScrollableTracksViewportWidth() {
		
		return false;
	}

	/**
	 * This method is implemented from the Scrollable interface. It defines
	 * a scrollable unit increment.
	 * 
	 * @param visibleRect The current visible Rectangle
	 * @param orientation The orientation of the Rectangle
	 * @param direction The direction of the Rectangle
	 * @return The int that determines the scrollable unit increment
	 */
	
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