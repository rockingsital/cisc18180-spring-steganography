package project;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class EncodeAndDecodeTest {

	int[] getPixelDataTest1;
	int[] getPixelDataTest2;
	int[] getPixelDataTest3;
	
	byte[] convertToBytesTest1;
	byte[] convertToBytesTest2;
	byte[] convertToBytesTest3;

	int[] getPortionIntTest1;
	int[] getPortionIntTest2;
	int[] getPortionIntTest3;
	
	byte[] getPortionByteTest1;
	byte[] getPortionByteTest2;
	byte[] getPortionByteTest3;
	
	BufferedImage checkTextTest1;
	BufferedImage checkTextTest2;
	BufferedImage checkTextTest3;
	
	int[] getPixelDigitsTest1;
	int[] getPixelDigitsTest2;
	int[] getPixelDigitsTest3;
	
	String convertToTextTest1;
	String convertToTextTest2;
	String convertToTextTest3;
	
	Color getHiddenIntTest1;
	Color getHiddenIntTest2;
	Color getHiddenIntTest3;
	
	BufferedImage scaleUpTest1;
	BufferedImage scaleUpTest2;
	BufferedImage scaleUpTest3;
	
	Color changeColorTest1;
	Color changeColorTest2;
	Color changeColorTest3;
	
	int[] digitsIntConversionTest1;
	int[] digitsIntConversionTest2;
	int[] digitsIntConversionTest3;
	
	@Before
	public void prepare(){
		
		/*
		 * Creates all the necessary objects for testing purposes.
		 */
		
		/* Objects for the getPixelData method tests. */
		getPixelDataTest1 = EncodeAndDecode.getPixelData(new Color(65,137,243));
		getPixelDataTest2 = EncodeAndDecode.getPixelData(new Color(137,243,65));
		getPixelDataTest3 = EncodeAndDecode.getPixelData(new Color(243,65,137));
		
		/* Objects for the convertToBytes method tests. */
		
		int[] convertBytes = new int[3];
		/* Array used to create necessary objects for convertToBytes method tests. */
		
		for (int i = 0; i < 3; i += 1){
			convertBytes[i] = i;
		}
		convertToBytesTest1 = EncodeAndDecode.convertToBytes(convertBytes);
		
		for (int i = 0; i < 3; i += 1){
			convertBytes[i] = 2 - i;
		}
		convertToBytesTest2 = EncodeAndDecode.convertToBytes(convertBytes);
		
		for (int i = 0; i < 3; i += 1){
			convertBytes[i] = (2 * i);
		}
		convertToBytesTest3 = EncodeAndDecode.convertToBytes(convertBytes);
		
		/* Objects for the getPortion method tests. */
		
		int[] intPortion = {0,1,2,3,4,5,6,7,8,9};
		/* Array used to create necessary objects for convertToBytes method tests. */
		getPortionIntTest1 = EncodeAndDecode.getPortion(intPortion,0,3);
		getPortionIntTest2 = EncodeAndDecode.getPortion(intPortion,3,7);
		getPortionIntTest3 = EncodeAndDecode.getPortion(intPortion,7,10);
		
		byte[] bytePortion = {0,1,2,3,4,5,6,7,8,9};
		/* Array used to create necessary objects for convertToBytes method tests. */
		getPortionByteTest1 = EncodeAndDecode.getPortion(bytePortion,0,3);
		getPortionByteTest2 = EncodeAndDecode.getPortion(bytePortion,3,7);
		getPortionByteTest3 = EncodeAndDecode.getPortion(bytePortion,7,10);
		
		/* Objects for the checkText method tests. */
		
		checkTextTest1 = new BufferedImage(10,10,BufferedImage.TYPE_INT_RGB);
		checkTextTest1.setRGB(0,0,new Color(143,244,12).getRGB());
		
		checkTextTest2 = new BufferedImage(10,10,BufferedImage.TYPE_INT_RGB);
		checkTextTest2.setRGB(0,0,new Color(233,124,152).getRGB());
		
		checkTextTest3 = new BufferedImage(10,10,BufferedImage.TYPE_INT_RGB);
		checkTextTest3.setRGB(0,0,new Color(142,242,13).getRGB());
		
		/* Objects for the getPixelDigits method tests. */
		
		getPixelDigitsTest1 = EncodeAndDecode.getPixelDigits(new Color(31,132,243));
		getPixelDigitsTest2 = EncodeAndDecode.getPixelDigits(new Color(34,135,246));
		getPixelDigitsTest3 = EncodeAndDecode.getPixelDigits(new Color(37,138,249));
		
		/* Objects for the convertToText method tests. */
		
		int[] convertTextArray1 = {0,6,5,0,6,6,0,6,7};
		int[] convertTextArray2 = {0,6,8,0,6,9,0,7,0};
		int[] convertTextArray3 = {0,7,1,0,7,2,0,7,3};
		/* Array used to create necessary objects for convertToText method tests. */
		
		convertToTextTest1 = EncodeAndDecode.convertToText(convertTextArray1);
		convertToTextTest2 = EncodeAndDecode.convertToText(convertTextArray2);
		convertToTextTest3 = EncodeAndDecode.convertToText(convertTextArray3);
		
		/* Objects for the getHiddenInt method tests. */
		
		getHiddenIntTest1 = new Color(131,242,63);
		getHiddenIntTest2 = new Color(134,245,66);
		getHiddenIntTest3 = new Color(137,248,69);
		
		/* Objects for the scaleUp method tests. */
		
		scaleUpTest1 = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
		scaleUpTest2 = new BufferedImage(2,1,BufferedImage.TYPE_INT_RGB);
		scaleUpTest3 = new BufferedImage(1,2,BufferedImage.TYPE_INT_RGB);
		
		scaleUpTest1.setRGB(0,0,-16711165);
		
		scaleUpTest2.setRGB(0,0,-16711165);
		scaleUpTest2.setRGB(1,0,-16050655);
		
		scaleUpTest3.setRGB(0,0,-16050655);
		scaleUpTest3.setRGB(0,1,-16116706);
		
		scaleUpTest1 = EncodeAndDecode.scaleUp(scaleUpTest1,3);
		scaleUpTest2 = EncodeAndDecode.scaleUp(scaleUpTest2,2);
		scaleUpTest3 = EncodeAndDecode.scaleUp(scaleUpTest3,2);
		
		/* Objects for the changeColor method tests. */
		
		changeColorTest1 = EncodeAndDecode.changeColor(new Color(117,228,119),123);
		changeColorTest2 = EncodeAndDecode.changeColor(new Color(117,228,119),456);
		changeColorTest3 = EncodeAndDecode.changeColor(new Color(111,222,113),789);
		
		/* Objects for the intToDigits and digitsToInt method tests. */
		
		digitsIntConversionTest1 = EncodeAndDecode.intToDigits(123);
		digitsIntConversionTest2 = EncodeAndDecode.intToDigits(456);
		digitsIntConversionTest3 = EncodeAndDecode.intToDigits(789);
		
	}
	
	@Test
	public void testGetPixelData() {
		
		assertEquals(65,getPixelDataTest1[0]);
		assertEquals(137,getPixelDataTest1[1]);
		assertEquals(243,getPixelDataTest1[2]);
		
		assertEquals(137,getPixelDataTest2[0]);
		assertEquals(243,getPixelDataTest2[1]);
		assertEquals(65,getPixelDataTest2[2]);
		
		assertEquals(243,getPixelDataTest3[0]);
		assertEquals(65,getPixelDataTest3[1]);
		assertEquals(137,getPixelDataTest3[2]);
		
	}

	@Test
	public void testConvertToBytes() {
		
		assertEquals(0,convertToBytesTest1[0]);
		assertEquals(1,convertToBytesTest1[1]);
		assertEquals(2,convertToBytesTest1[2]);
		
		assertEquals(2,convertToBytesTest2[0]);
		assertEquals(1,convertToBytesTest2[1]);
		assertEquals(0,convertToBytesTest2[2]);
		
		assertEquals(0,convertToBytesTest3[0]);
		assertEquals(2,convertToBytesTest3[1]);
		assertEquals(4,convertToBytesTest3[2]);
		
	}

	@Test
	public void testGetPortionIntArrayIntInt() {
		
		assertEquals(0,getPortionIntTest1[0]);
		assertEquals(1,getPortionIntTest1[1]);
		assertEquals(2,getPortionIntTest1[2]);
		
		assertEquals(3,getPortionIntTest2[0]);
		assertEquals(4,getPortionIntTest2[1]);
		assertEquals(5,getPortionIntTest2[2]);
		assertEquals(6,getPortionIntTest2[3]);
		
		assertEquals(7,getPortionIntTest3[0]);
		assertEquals(8,getPortionIntTest3[1]);
		assertEquals(9,getPortionIntTest3[2]);
		
	}

	@Test
	public void testGetPortionByteArrayIntInt() {
		
		assertEquals(0,getPortionByteTest1[0]);
		assertEquals(1,getPortionByteTest1[1]);
		assertEquals(2,getPortionByteTest1[2]);
		
		assertEquals(3,getPortionByteTest2[0]);
		assertEquals(4,getPortionByteTest2[1]);
		assertEquals(5,getPortionByteTest2[2]);
		assertEquals(6,getPortionByteTest2[3]);
		
		assertEquals(7,getPortionByteTest3[0]);
		assertEquals(8,getPortionByteTest3[1]);
		assertEquals(9,getPortionByteTest3[2]);
		
	}

	@Test
	public void testCheckText() {
		
		assertEquals(true,EncodeAndDecode.checkText(checkTextTest1));
		assertEquals(true,EncodeAndDecode.checkText(checkTextTest2));
		assertEquals(false,EncodeAndDecode.checkText(checkTextTest3));
		
	}

	@Test
	public void testGetPixelDigits() {
		
		assertEquals(1,getPixelDigitsTest1[0]);
		assertEquals(2,getPixelDigitsTest1[1]);
		assertEquals(3,getPixelDigitsTest1[2]);
		
		assertEquals(4,getPixelDigitsTest2[0]);
		assertEquals(5,getPixelDigitsTest2[1]);
		assertEquals(6,getPixelDigitsTest2[2]);
		
		assertEquals(7,getPixelDigitsTest3[0]);
		assertEquals(8,getPixelDigitsTest3[1]);
		assertEquals(9,getPixelDigitsTest3[2]);
		
	}

	@Test
	public void testConvertToText() {
		
		assertEquals(true,convertToTextTest1.contentEquals("ABC"));
		assertEquals(true,convertToTextTest2.contentEquals("DEF"));
		assertEquals(true,convertToTextTest3.contentEquals("GHI"));
		
	}

	@Test
	public void testGetHiddenInt() {
		
		assertEquals(123,EncodeAndDecode.getHiddenInt(getHiddenIntTest1));
		assertEquals(456,EncodeAndDecode.getHiddenInt(getHiddenIntTest2));
		assertEquals(789,EncodeAndDecode.getHiddenInt(getHiddenIntTest3));
		
	}

	@Test
	public void testScaleUp() {
		
		assertEquals(-16711165,scaleUpTest1.getRGB(0,0));
		assertEquals(-16711165,scaleUpTest1.getRGB(1,0));
		assertEquals(-16711165,scaleUpTest1.getRGB(2,0));
		assertEquals(-16711165,scaleUpTest1.getRGB(0,1));
		assertEquals(-16711165,scaleUpTest1.getRGB(1,1));
		assertEquals(-16711165,scaleUpTest1.getRGB(2,1));
		assertEquals(-16711165,scaleUpTest1.getRGB(0,2));
		assertEquals(-16711165,scaleUpTest1.getRGB(1,2));
		assertEquals(-16711165,scaleUpTest1.getRGB(2,2));
		
		assertEquals(-16711165,scaleUpTest2.getRGB(0,0));
		assertEquals(-16711165,scaleUpTest2.getRGB(1,0));
		assertEquals(-16711165,scaleUpTest2.getRGB(0,1));
		assertEquals(-16711165,scaleUpTest2.getRGB(1,1));
		assertEquals(-16050655,scaleUpTest2.getRGB(2,0));
		assertEquals(-16050655,scaleUpTest2.getRGB(3,0));
		assertEquals(-16050655,scaleUpTest2.getRGB(2,1));
		assertEquals(-16050655,scaleUpTest2.getRGB(3,1));
		
		assertEquals(-16050655,scaleUpTest3.getRGB(0,0));
		assertEquals(-16050655,scaleUpTest3.getRGB(0,1));
		assertEquals(-16050655,scaleUpTest3.getRGB(1,0));
		assertEquals(-16050655,scaleUpTest3.getRGB(1,1));
		assertEquals(-16116706,scaleUpTest3.getRGB(0,2));
		assertEquals(-16116706,scaleUpTest3.getRGB(0,3));
		assertEquals(-16116706,scaleUpTest3.getRGB(1,2));
		assertEquals(-16116706,scaleUpTest3.getRGB(1,3));
		
	}

	@Test
	public void testChangeColor() {
		
		assertEquals(121,changeColorTest1.getRed());
		assertEquals(232,changeColorTest1.getGreen());
		assertEquals(123,changeColorTest1.getBlue());
		
		assertEquals(114,changeColorTest2.getRed());
		assertEquals(225,changeColorTest2.getGreen());
		assertEquals(116,changeColorTest2.getBlue());

		assertEquals(107,changeColorTest3.getRed());
		assertEquals(218,changeColorTest3.getGreen());
		assertEquals(109,changeColorTest3.getBlue());
		
	}

	@Test
	public void testIntToDigits() {
		
		assertEquals(1,digitsIntConversionTest1[0]);
		assertEquals(2,digitsIntConversionTest1[1]);
		assertEquals(3,digitsIntConversionTest1[2]);
		
		assertEquals(4,digitsIntConversionTest2[0]);
		assertEquals(5,digitsIntConversionTest2[1]);
		assertEquals(6,digitsIntConversionTest2[2]);
		
		assertEquals(7,digitsIntConversionTest3[0]);
		assertEquals(8,digitsIntConversionTest3[1]);
		assertEquals(9,digitsIntConversionTest3[2]);
		
	}

	@Test
	public void testDigitsToInt() {
		
		assertEquals(123,EncodeAndDecode.digitsToInt(digitsIntConversionTest1));
		assertEquals(456,EncodeAndDecode.digitsToInt(digitsIntConversionTest2));
		assertEquals(789,EncodeAndDecode.digitsToInt(digitsIntConversionTest3));
		
	}

}