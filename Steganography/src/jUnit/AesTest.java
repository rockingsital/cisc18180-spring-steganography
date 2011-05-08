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
		AES test = new AES(new byte[16]);
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
		AES test = new AES(new byte[16]);
		byte[] newArray = test.subWord(byteArray);
		assertTrue("SubWord 0", newArray[0] == 99);
		assertTrue("SubWord 1", newArray[1] == 43);
		assertTrue("SubWord 2", newArray[2] == -83);
		assertTrue("SubWord 3", newArray[3] == -52);
	}

	@Test
	public void testMixColumns(){
		byte[][] test = new byte[4][4];
		test[0][0]= (byte) 0xdb;
		test[0][1]= (byte) 0x13;
		test[0][2]= (byte) 0x53;
		test[0][3]= (byte) 0x45;
		test[1][0]= (byte) 0xf2;
		test[1][1]= (byte) 0x0a;
		test[1][2]= (byte) 0x22;
		test[1][3]= (byte) 0x5c;
		test[2][0]= (byte) 0x01;
		test[2][1]= (byte) 0x01;
		test[2][2]= (byte) 0x01;
		test[2][3]= (byte) 0x01;
		test[3][0]= (byte) 0xc6;
		test[3][1]= (byte) 0xc6;
		test[3][2]= (byte) 0xc6;
		test[3][3]= (byte) 0xc6;
		AES testAES = new AES(TwoDimensionalArray.toSingleArray(test));
		testAES.inverseMixCols();
		TwoDimensionalArray.print(testAES.state);
	}
}
