package project;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class EncodeAndDecode{

public static void encodeText(File original,File writeTo,String message){
		
		/*
		 * Hides a text message in an image.
		 */
		
		BufferedImage encodedImage = null;
		try{
			encodedImage = ImageIO.read(original);
		}
		catch(Exception e){
			System.out.println(e);
			return;
		}
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
					try{
						ImageIO.write(encodedImage,"png",writeTo);
					}
					catch (Exception e){
						System.out.println(e);
					}
					return;
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
		return;
		
	}

	public static void encodePicture(File hideThis,File hideIn,String writeTo){
	
		/*
		 * Hides an image in another image.
		 */
	
		BufferedImage imageToHide = null;
		BufferedImage placeToHide = null;
		try{
			imageToHide = ImageIO.read(hideThis);
			placeToHide = ImageIO.read(hideIn);
		}
		catch(Exception e){
			System.out.println(e);
			return;
		}
		int scaleFactor = 1;
		while(((3 * imageToHide.getWidth() * imageToHide.getHeight()) + 3 > ((scaleFactor * scaleFactor * placeToHide.getHeight() * placeToHide.getWidth())))){
			scaleFactor += 1;
		}
		/* Determines the scaleFactor needed to fit all the
	   	information from the original image into the
	   	new image without changing its appearance.
	   	i.e. the new image must have 3 times as many
	   	pixels as the original + 3 after scaling to fit the image, 
	   	dimensions, and end code.*/
		placeToHide = scaleUp(placeToHide,scaleFactor);
		int imageMinX = imageToHide.getMinX();
		int imageMinY = imageToHide.getMinY();
		int imageWidth = imageToHide.getWidth();
		int imageHeight = imageToHide.getHeight();
		/* Properties of the original image used to move through
	   	pixels of the image using a loop. */
		int[] imagePixels = new int[3 * imageWidth * imageHeight];
		int[] currentPixelData = new int[3];
		for (int j = 0; j < imageWidth; j+= 1){
			/* Controls movement through the image 
		   	horizontally. */
			for (int i = 0; i < imageHeight; i += 1){
				/* Controls movement through the image 
			   	vertically. */
				currentPixelData = getPixelData(new Color(imageToHide.getRGB(imageMinX + j, imageMinY + i)));
				/* Gets the integers representing the pixels in the original image. */
				imagePixels[3 * ((j * imageHeight) + i)] = currentPixelData[0];
				imagePixels[3 * ((j * imageHeight) + i) + 1] = currentPixelData[1];
				imagePixels[3 * ((j * imageHeight) + i) + 2] = currentPixelData[2];
			}
		}
		int placeMinX = placeToHide.getMinX();
		int placeMinY = placeToHide.getMinY();
		int placeWidth = placeToHide.getWidth();
		int placeHeight = placeToHide.getHeight();
		/* Properties of the new image used to move through
	   	pixels of the image using a loop. */
		for(int j = 0; j < placeWidth; j += 1){
			/* Controls movement through the image 
		   	horizontally. */
			for (int i = 0; i < placeHeight; i += 1){
				/* Controls movement through the image 
			   	vertically. */
				if((i == 0) && (j == 0)){
					placeToHide.setRGB(placeMinX,placeMinY,changeColor(new Color(placeToHide.getRGB(placeMinX,placeMinY)),imageWidth).getRGB());
				}
				else if((i == 1) && (j == 0)){
					placeToHide.setRGB(placeMinX,placeMinY + 1,changeColor(new Color(placeToHide.getRGB(placeMinX,placeMinY + 1)),imageHeight).getRGB());
				}
				else if((j * placeHeight) + i == imagePixels.length + 2){
					placeToHide.setRGB(placeMinX + j,placeMinY + i,changeColor(new Color(placeToHide.getRGB(placeMinX + j,placeMinY + i)),423).getRGB());
					try{
						ImageIO.write(placeToHide,"png",new File(writeTo));
					}
					catch(Exception e){
						System.out.println(e);
					}
					return;
				}
				else{
					placeToHide.setRGB(placeMinX + j, placeMinY + i,changeColor(new Color(placeToHide.getRGB(placeMinX + j,placeMinY + i)),imagePixels[(j * placeHeight) + i - 2]).getRGB());
				}
			}
		}
		return;
		
	}

	public static int[] getPixelData(Color pixelColor){
		
		/*
		 * Returns an int[] representing the given Color.
		 */
		
		int[] pixelData = new int[3];
		pixelData[0] = pixelColor.getRed();
		pixelData[1] = pixelColor.getGreen();	
		pixelData[2] = pixelColor.getBlue();
		return pixelData;
		
	}
	
/*	public static int[] encryption(byte[] bytes,AES anAES){
		/*
		/*
		 * Prepares information to be encoded for encryption.
		 */
		/*
		int[] encrypted = new int[bytes.length];
		byte[] encryptedBytes = new byte[16];
		for (int i = 0; i <= (bytes.length/16); i += 1){
			if(i == (bytes.length/16)){
				encryptedBytes = getPortion(bytes,(16 * i),(bytes.length % 16));
				for (int j = (bytes.length % 16); j < (16 * (i+1)); j += 1){
					encryptedBytes[j] = (char) ' ';
				}
				encryptedBytes = anAES.encrypt(encryptedBytes);
			}
			else{
				encryptedBytes = anAES.encrypt(getPortion(bytes,(16 * i),(16 * (i + 1))));
			}
			for(int j = 0; j < 16; j += 1){
				encrypted[(i * 16) + j] = (int) encryptedBytes[j];
			}
		}
		return encrypted;
		
		
	}*/

	public static int[] getPortion(int[] original,int startIndex, int endIndex){
		
		/*
		 * Returns an int[] that contains the elements of the given array from
		 * the start index to (but not including) the end index.
		 */
		
		int[] portion = new int[endIndex - startIndex];
		for (int i = startIndex; i < endIndex; i += 1){
			portion[i - startIndex] = original[i];
		}
		return portion;
		
	}
	
	public static byte[] getPortion(byte[] original,int startIndex, int endIndex){
		
		/*
		 * Returns an byte[] that contains the elements of the given array from
		 * the start index to (but not including) the end index.
		 */
		
		byte[] portion = new byte[endIndex - startIndex];
		for (int i = startIndex; i < endIndex; i += 1){
			portion[i - startIndex] = original[i];
		}
		return portion;
		
	}
	
	public static void decode(File encoded, File writeTo, String password){
		
		BufferedImage encodedImage = null;
		try{
			encodedImage = ImageIO.read(encoded);
		}
		catch(Exception e){
			System.out.println(e);
			return;
		}
		if (checkText(encodedImage)){
			decodeText(encodedImage,writeTo,password);
		}
		else{
			decodeImage(encodedImage,writeTo,password);
		}
		return;
		
	}
	
	public static boolean checkText(BufferedImage encodedImage){
		
		/*
		 * Returns a boolean representing if a text file is encoded in the image.
		 */
		
		Color pixelColor = new Color(encodedImage.getRGB(encodedImage.getMinX(),encodedImage.getMinY()));
		if (((pixelColor.getRed() % 10) == 3) && ((pixelColor.getGreen() % 10) == 4) && ((pixelColor.getBlue() % 10) == 2)){
			return true;
		}
		else{
			return false;
		}
		
	}

	public static void decodeText(BufferedImage encodedImage,File writeTo,String password){
		
		/* Recovers the message from the given image and writes it to the desired
		   file location. */
		
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
						try{
							BufferedWriter output = new BufferedWriter(new FileWriter(writeTo));
							output.write(message);
							output.close();
						}
						catch (Exception e){
							System.out.println(e);
						}
						return;
					}
					else{
						message = message.concat(nextCharacter);
					}
				}
			}
		}
		return;
		
	}
	
	public static void decodeImage(BufferedImage encodedImage,File writeTo,String password){
		
		/* Recovers the image from the given image and writes it to the desired
		   file location. */
		
		int encodedMinX = encodedImage.getMinX();
		int encodedMinY = encodedImage.getMinY();
		int encodedWidth = encodedImage.getWidth();
		int encodedHeight = encodedImage.getHeight();
		/* Properties of the encoded image used to move through
		   pixels of the image using a loop. */
		int hiddenWidth = 0;
		int hiddenHeight = 0;
		/* Properties of the hidden image used to move through 
		   pixels of the image using a loop. */
		int[] hiddenPixels = null;
		/* Variable used to move hold the pixel data for the hidden
		   image. */
		int pixelInt = 0;
		/* Holds information about number hidden in the current pixel
		   of the encoded image. */
		for (int j = 0; j < encodedWidth; j+= 1){
			/* Controls movement through the image 
			   horizontally. */
			/* Too Long Lines ??? */
			for (int i = 0; i < encodedHeight; 
					i += 1){
				/* Controls movement through the image 
				   vertically. */
				if ((i == 0) && (j == 0)){
					/* Gets width of hidden image. */
					hiddenWidth = getInt(new Color(encodedImage.getRGB(encodedMinX,encodedMinY)));
				}
				else if ((j == 0) && (i == 1)){
					/* Gets height of hidden image. */
					hiddenHeight = getInt(new Color(encodedImage.getRGB(encodedMinX,encodedMinY + 1)));
					hiddenPixels = new int[3 * hiddenWidth * hiddenHeight];
				}
				else{
					pixelInt = getInt(new Color(encodedImage.
							getRGB((encodedMinX + j),(encodedMinY + i))));
					if (pixelInt == 423){
						rebuildImage(hiddenPixels,hiddenWidth,hiddenHeight,writeTo);
						return;
					}
					else{
						hiddenPixels[(j * encodedHeight) + i - 2] = pixelInt;
					}
				}
			}
		}
		return;
		
	}
	
	public static void rebuildImage(int[] pixels,int width,int height,File writeTo){
		
		/*
		 * Creates an image from data about its pixels, width, and height.
		 */
		
		BufferedImage hiddenImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		for (int j = 0; j < width; j += 1){
			/* Moves through image horizontally. */
			for (int i = 0; i < height; i += 1){
				/* Moves through image vertically. */
				hiddenImage.setRGB(j,i,new Color(pixels[3 *((j * height) + i)],pixels[3 * ((j * height) + i) + 1],pixels[3 * ((j * height) + i) + 2]).getRGB());
			}
		}
		try{
			ImageIO.write(hiddenImage,"png",writeTo);
		}
		catch(Exception e){
			System.out.println(e);
		}
		return;
		
	}
	
	public static int getInt(Color pixelColor){
		
		/*
		 * Returns the int hidden in the Color of a pixel.
		 */
		
		int[] digits = new int[3];
		digits[0] = intToDigits(pixelColor.getRed())[2];
		digits[1] = intToDigits(pixelColor.getGreen())[2];
		digits[2] = intToDigits(pixelColor.getBlue())[2];
		return digitsToInt(digits);
		
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
		
		if ((red[0] == 2) && (red[1] == 5) && (character[0] > 5)){
			red[1] = 4;
		}
		/* Accounts for the fact that red can not exceed
		   255. */
		red[2] = character[0];
		/* Places the first digit representing the input
		   character as the last digit of the red component
		   of the pixel. */

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
		
		
		if ((blue[0] == 2) && (blue[1] == 5) && (number[2] > 5)){
			blue[1] = 4;
		}
		/* Accounts for the fact that blue can not exceed
		   255. */
		blue[2] = number[2];
		/* Places the third digit representing the input
		   number as the last digit of the blue component
		   of the pixel. */
		
		if ((green[0] == 2) && (green[1] == 5) && (number[1] > 5)){
			green[1] = 4;
		}
		/* Accounts for the fact that green can not exceed
		   255. */
		green[2] = number[1];
		/* Places the second digit representing the input
		   number as the last digit of the green component
		   of the pixel. */
		
		if ((red[0] == 2) && (red[1] == 5) && (number[0] > 5)){
			red[1] = 4;
		}
		/* Accounts for the fact that red can not exceed
		   255. */
		red[2] = number[0];
		/* Places the first digit representing the input
		   number as the last digit of the red component
		   of the pixel. */

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
		
		encodePicture(new File("hideImage.png"),new File("corn.png"),"encoded.png"); 
		/*
		BufferedImage image = null;
		try{
			image = ImageIO.read(new File("encoded.png"));
		}
		catch(Exception e){
			System.out.println(e);
		}
		decodeImage(image,new File("decoded.png"),"");
		*/
	}
	
}