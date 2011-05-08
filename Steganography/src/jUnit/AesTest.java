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
			byteArray[count] = (byte)(count * (10 + count)); //0, 11, 24, 39
		}
		AES test = new AES(new byte[16]);
		byte[] newArray = test.subWord(byteArray);
		assertTrue("SubWord 0", newArray[0] == 99); //0x
		assertTrue("SubWord 1", newArray[1] == -126); //0x
		assertTrue("SubWord 2", newArray[2] == 54); //0x
		assertTrue("SubWord 3", newArray[3] == 18); //0x
	}
}
