package jUnit;

import project.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class AesTest {
	
	@Test
	public void testRotWord(){
		byte[] byteArray = new byte[4];
		for (int count = 0; count < 4; count++)
			byteArray[count] = (byte)(count);
		AES test = new AES();
		byte[] newArray = test.rotWord(byteArray);
		assertTrue("RotWord", newArray[0] == 1);
		assertTrue("RotWord", newArray[1] == 2);
		assertTrue("RotWord", newArray[2] == 3);
		assertTrue("RotWord", newArray[3] == 0);	
	}
	
	@Test
	public void testSubWord(){
		byte[] byteArray = new byte[4];
		for(int count = 0; count < 4; count++){
			byteArray[count] = (byte)(count * (10 + count)); //0, b, 18, 27 (0, 11, 24, 39)
		}
		AES test = new AES();
		byte[] newArray = test.subWord(byteArray);
		assertTrue("SubWord 0", newArray[0] == 99);
		assertTrue("SubWord 1", newArray[1] == 43);
		assertTrue("SubWord 2", newArray[2] == -83);
		assertTrue("SubWord 3", newArray[3] == -52);
	}
	
	@Test
	public void testSubBytes(){
		byte[] byteArray = new byte[16];
		for(int count = 0; count < 16; count++){
			byteArray[count] = (byte)((count * 10));
		}
		AES test = new AES();
		test.state = TwoDimensionalArray.fromSingleArray(byteArray);
		TwoDimensionalArray.print(test.state);
		test.subBytes();
		TwoDimensionalArray.print(test.sBox);
		TwoDimensionalArray.print(test.state);
		assertTrue("Sub Bytes 1", test.state[0][0] == 99);
		assertTrue("Sub Bytes 2", test.state[0][1] == 103);
		assertTrue("Sub Bytes 3", test.state[0][2] == -6);
		assertTrue("Sub Bytes 4", test.state[0][3] == 114);
		assertTrue("Sub Bytes 5", test.state[1][0] == 52);
		assertTrue("Sub Bytes 6", test.state[2][1] == -66);
		assertTrue("Sub Bytes 7", test.state[3][2] == 100);
	}
	
	
	@Test
	public void testMixColumns(){
		byte[][] test = new byte[4][4];
		test[0][0]= (byte) 0xdb;
		test[0][1]= (byte) 0xf2;
		test[0][2]= (byte) 0x01;
		test[0][3]= (byte) 0xc6;
		test[1][0]= (byte) 0x13;
		test[1][1]= (byte) 0x0a;
		test[1][2]= (byte) 0x01;
		test[1][3]= (byte) 0xc6;
		test[2][0]= (byte) 0x53;
		test[2][1]= (byte) 0x22;
		test[2][2]= (byte) 0x01;
		test[2][3]= (byte) 0xc6;
		test[3][0]= (byte) 0x45;
		test[3][1]= (byte) 0x5c;
		test[3][2]= (byte) 0x01;
		test[3][3]= (byte) 0xc6;
		AES testAES = new AES();
		testAES.state = test;
		testAES.mixColumns();
		assertTrue("Mix Col 1", testAES.state[0][0] == -114);
		assertTrue("Mix Col 2", testAES.state[1][1] == -36);
		assertTrue("Mix Col 3", testAES.state[2][2] == 1);
		assertTrue("Mix Col 4", testAES.state[3][3] == -58);
		testAES.inverseMixCols();
		assertTrue("Inv Mix Col 1", testAES.state[0][0] == -37);
		assertTrue("Inv Mix Col 2", testAES.state[1][1] == 10);
		assertTrue("Inv Mix Col 3", testAES.state[2][2] == 1);
		assertTrue("Inv Mix Col 4", testAES.state[3][3] == -58);
	}
	
	@Test
	public void testAddRoundKey(){
		byte[] byteArray = new byte[16];
		for (int count = 0; count < 16; count++)
			byteArray[count] = (byte)(count);
		String key = "6ea013aa065fd44347c2b9371bdb2df31c66770409c7ac40";
		byte[] output = new byte[24];
		for (int i = 0; i < 48; i = i + 2)
			output[i/2] = (byte) ((Character.digit(key.charAt(i), 16) << 4) + Character.digit(key.charAt(i+1), 16));
		AES testAES = new AES(output);
		testAES.state = TwoDimensionalArray.fromSingleArray(byteArray);
		testAES.addRoundKey(0);
		assertTrue("Add Round Key 1", testAES.state[0][0] == 110);
		assertTrue("Add Round Key 2", testAES.state[0][1] == 7);
		assertTrue("Add Round Key 3", testAES.state[0][2] == 69);
		assertTrue("Add Round Key 4", testAES.state[0][3] == 24);
		assertTrue("Add Round Key 5", testAES.state[1][0] == -92);
		assertTrue("Add Round Key 6", testAES.state[1][1] == 90);
		assertTrue("Add Round Key 7", testAES.state[1][2] == -60);
		assertTrue("Add Round Key 8", testAES.state[1][3] == -36);
	}
	
	@Test
	public void testShiftRows(){
		byte[] byteArray = new byte[16];
		for (int count = 0; count < 16; count++)
			byteArray[count] = (byte)(count);
		AES testAES = new AES();
		testAES.state = TwoDimensionalArray.fromSingleArray(byteArray);
		testAES.shiftRows();
		assertTrue("Shift Rows 1", testAES.state[0][0] == 0);
		assertTrue("Shift Rows 2", testAES.state[0][1] == 1);
		assertTrue("Shift Rows 3", testAES.state[1][0] == 5);
		assertTrue("Shift Rows 4", testAES.state[1][1] == 6);
		assertTrue("Shift Rows 5", testAES.state[2][0] == 10);
		assertTrue("Shift Rows 6", testAES.state[2][1] == 11);
		assertTrue("Shift Rows 7", testAES.state[3][0] == 15);
		assertTrue("Shift Rows 8", testAES.state[3][1] == 12);
	}
}
