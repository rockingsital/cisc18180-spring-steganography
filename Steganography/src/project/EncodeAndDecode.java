package project;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.lang.Integer;
import java.awt.Color;

public class EncodeAndDecode{

public static BufferedImage encodeText(){
		
		/*
		 * Hides a text message in an image.
		 */
		
		BufferedImage encodedImage = null;
		try{
			/* Needs to deal with file input??? */
			encodedImage = ImageIO.read(new File("mothersDay.png"));
		}
		catch(Exception e){
			System.out.println(e);
			return encodedImage;
		}
		/* Needs to deal with message input??? */
		String message = "Dear Mom, I thought about sending you a regular card but instead I decided to show you what I've been working on in computer science. My group project deals with steganography. We're hiding text messages and images in other images. In this case, this message is encoded into the picture itself. Hope you like it. Have a wonderful mother's day. Thanks for all that you do.";
		int scaleFactor = 1;
		while((message.length()) > (scaleFactor * scaleFactor * encodedImage.getHeight() * encodedImage.getWidth())){
			scaleFactor += 1;
		}
		/* Determines the scaleFactor needed to fit all the
		   information from the message into the
		   image without changing its appearance.
		   i.e. the image must have as many pixels as the
		   message has characters. */
		encodedImage = scaleUp(encodedImage,scaleFactor);
		int minX = encodedImage.getMinX();
		int minY = encodedImage.getMinY();
		int width = encodedImage.getWidth();
		int height = encodedImage.getHeight();
		for (int j = 0; j < width; j+= 1){
			/* Controls movement through the image 
			   horizontally. */
			/* Too Long Lines ??? */
			for (int i = 0; ((((j * height) + i) <= 
					message.length()+1) && (i < height)); 
					i += 1){
				/* Controls movement through the image 
				   vertically. */
				if (i == 0 && j == 0){
					encodedImage.setRGB(minX + j,minY + i,
							changeColor(new Color(encodedImage.
							getRGB((minX + j),(minY + i))
							),342).getRGB());
					/* Places 342 in the first pixel of the
					   image to indicate a text message is
					   within it. + 1 accounts for presence of
				   	   start up and end codes. */
					/* Text Code??? */
				}
				else if (((j * height) + i) == (message.length()+1)){
					encodedImage.setRGB(minX + j,minY + i,
							changeColor(new Color(encodedImage.
							getRGB((minX + j),(minY + i))
							),423).getRGB());
					/* Places 423 in the current pixel of the
					   image to indicate the end of the message
					   has been reached. + 1 accounts for start 
					   up code. */
					/* End Code??? */
					return encodedImage;
				}
				else{
					encodedImage.setRGB(minX + j,minY + i,
							changeColor(new Color(encodedImage.
							getRGB((minX + j),(minY + i))
							),message.charAt((j * height) + i - 1))
							.getRGB());
					/* Changes the Color of the current pixel
				   	so that a character of the message is
				   	held within it. */
				}
			}
		}
		return encodedImage;
		
	}
	
	public static String decodeText(){
		
		/* Deal with start code??? */
		BufferedImage encodedImage = null;
		try{
			/* Needs to deal with file input??? */
			encodedImage = ImageIO.read(new File("interestingCorn.png"));
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
		String message = "";
		int minX = encodedImage.getMinX();
		int minY = encodedImage.getMinY();
		int width = encodedImage.getWidth();
		int height = encodedImage.getHeight();
		/* Properties of the image used to move through
		   pixels of the image using a loop. */
		for (int j = 0; j < width; j+= 1){
			/* Controls movement through the image 
			   horizontally. */
			/* Too Long Lines ??? */
			for (int i = 0; i < height; 
					i += 1){
				/* Controls movement through the image 
				   vertically. */
				if ((i == 0) && (j == 0)){
					/* Accounts for presence of code 
					   indicating a text message is
					   hidden within the image. */
				}
				else{
					/* Change ??? */
					String nextCharacter = getText(new Color(encodedImage.
							getRGB((minX + j),(minY + i))));
					if (nextCharacter.equals("done")){
						return message;
					}
					else{
						message = message.concat(nextCharacter);
					}
				}
			}
		}
		return message;
		
	}
	
	public static BufferedImage scaleUp(BufferedImage original,int scaleFactor){
		
		/* Increases the number of pixels in the given image
		 * by the given factor.
		 */
		
		BufferedImage scaledUp = new BufferedImage(original.getWidth() * scaleFactor,original.getHeight() * scaleFactor, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < original.getHeight(); i += 1){
			/* Controls vertical movement through the image. */
			for (int j = 0; j < original.getWidth(); j += 1){
				/* Controls horizontal movement through the image. */
				for (int k = 0; k < scaleFactor; k += 1){
					for (int l = 0; l < scaleFactor; l += 1){
						scaledUp.setRGB((j*scaleFactor)+k,(i*scaleFactor)+l,original.getRGB(j,i));
					}
				}
				/* Converts a scaleFactor x scaleFactor 
				   area of pixels in the new image to match 
				   a single pixel in the original image. */
			}
		}
		return scaledUp;
		
	}
	
	public static String getText(Color pixelColor){
		
		/*
		 * Gets the text hidden in the given Color.
		 */
		
		/* Exception if not ASCII??? */
		
		int[] characterNumbers = {(pixelColor.getRed() % 
				10),(pixelColor.getGreen() % 
						10),(pixelColor.getBlue() % 
								10)};
		/* Change ??? */
		if ((characterNumbers[0] == 4) && (characterNumbers[1] == 2) && (characterNumbers[2] == 3)){
			return "done";
		}
		char[] character = {(char) digitsToInt(characterNumbers)};
		return new String(character);
		
	}
	
	public static Color changeColor(Color currentColor,char inputCharacter){
		
		/*
		 * Returns a Color representing the given character
		 * being placed in the given Color.
		 */
		
		int[] red = intToDigits(currentColor.getRed());
		int[] green = intToDigits(currentColor.getGreen());
		int[] blue = intToDigits(currentColor.getBlue());
		/* Breaks the integers representing the red, green, 
		   and blue components of the pixel color into its
		   digits. */
		int[] character = intToDigits((int) inputCharacter);
		/* Breaks the integer representing the input 
		   character under the ASCII system into its 
		   digits. */
		
		if ((blue[0] == 2) && (blue[1] == 5) && (character[2] > 5)){
			blue[1] = 4;
		}
		/* Accounts for the fact that blue can not exceed
		   255. */
		blue[2] = character[2];
		/* Places the third digit representing the input
		   character as the last digit of the blue component
		   of the pixel. */
		
		if ((green[0] == 2) && (green[1] == 5) && (character[1] > 5)){
			green[1] = 4;
		}
		/* Accounts for the fact that green can not exceed
		   255. */
		green[2] = character[1];
		/* Places the second digit representing the input
		   character as the last digit of the green component
		   of the pixel. */
		
		red[2] = character[0];
		/* Places the first digit representing the input
		   character as the last digit of the red component
		   of the pixel. No check is needed for exceeding
		   255 as ASCII character codes do not surpass
		   127. */

		return new Color(digitsToInt(red),digitsToInt(green),
				digitsToInt(blue));
		
	}
	
	public static Color changeColor(Color currentColor,int inputNumber){
		
		/*
		 * Returns a Color representing the given number
		 * being placed in the given Color.
		 */
		
		int[] red = intToDigits(currentColor.getRed());
		int[] green = intToDigits(currentColor.getGreen());
		int[] blue = intToDigits(currentColor.getBlue());
		/* Breaks the integers representing the red, green, 
		   and blue components of the pixel color into its
		   digits. */
		int[] number = intToDigits(inputNumber);
		/* Breaks the input number into its digits. */
		
		blue[2] = number[2];
		/* Places the third digit of the input number as the 
		   last digit of the blue component of the pixel. */
		green[2] = number[1];
		/* Places the second digit of the input number as the
		   last digit of the green component of the pixel. */
		red[2] = number[0];
		/* Places the first digit of the input number as the
		   last digit of the red component of the pixel. */
		 
		/* No check is needed for exceeding 255 as the codes
		    being placed in the pixels do not contain digits
		    larger than 5. */

		return new Color(digitsToInt(red),digitsToInt(green),
				digitsToInt(blue));
		
	}
	
	/* Too many digits Exception??? */
	public static int[] intToDigits(int aNumber){
		
		/*
		 * Returns an array of 3 integers representing the
		 * three digits of the given integer. This method
		 * should not be called on an integer with more than
		 * three digits.
		 * Example:
		 * intToDigits(137)
		 * return == [1,3,7]
		 * intToDigits(53)
		 * return == [0,5,3]
		 */
		
		int[] digits = new int[3];
		digits[0] = aNumber / 100;
		/* The integer component of a three-digit number divided 
		   by 100 reflects the first digit of that number. */
		digits[1] = (aNumber % 100) / 10;
		/* A number mod 100 reflects the last two digits
		   of that number.  The integer component of a two-digit
		   number divided by 10 reflects the first digit of
		   that number. */
		digits[2] = aNumber % 10;
		/* A number mod 10 reflects the last digit of a 
		   number. */
		return digits;
	
	}
	
	/* Incorrect Array Size Exception??? */
	public static int digitsToInt(int[] digits){
		
		/*
		 * Returns the integer represented by an array of
		 * integers.  Should not be called on anything
		 * but an array of size 3.
		 * Example:
		 * digitsToInt([1,3,7])
		 * return == 137
		 * digitsToInt([0,5,3])
		 * return == 53
		 */
		
		/* The first integer in the array represents the digit
		   in the hundreds place of the final integer.
		   The second integer in the array represents the digit
		   in the tens place of the final integer.
		   The third integer in the array represents the digit
		   in the ones place of the final integer. */
		return (digits[0] * 100) + (digits[1] * 10) + digits[2];
		
	}
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame("Drawing Frame");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    MyCanvas canvas = new MyCanvas();
	    frame.getContentPane().add(canvas);
	    frame.setSize(1000,1000);
	    frame.setVisible(true);
	    System.out.println(EncodeAndDecode.decodeText());
		
	}

}

class MyCanvas extends JComponent{
	
	public void paint(Graphics g){
	
		Graphics2D g2d = (Graphics2D) g;
		try{
			BufferedImage test = EncodeAndDecode.encodeText();
			g2d.drawImage(test,null,1,1);
			ImageIO.write(test,"png",new File("interestingCorn.png"));
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	
}
