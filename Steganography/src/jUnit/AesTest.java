package jUnit;

import project.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class AesTest {
	
	@Test
	public void testRotWord(){
		byte[] byteArray = new byte[4];
		AES tester = new AES(byteArray);
		for (int count = 0; count < 4; count++){
			byteArray[count] = (byte)(count + 1);
		}
		byte[] newArray = tester.rotWord(byteArray);
		assertTrue("Testing", newArray[0] == 2);
		assertTrue("Testing", newArray[1] == 3);
		assertTrue("Testing", newArray[2] == 4);
		assertTrue("Testing", newArray[3] == 1);
		
	}
}
