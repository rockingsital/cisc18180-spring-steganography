package project;
 import java.security.*;

   import javax.crypto.*;
   import javax.crypto.spec.*;
import java.io.*;
import java.math.BigInteger;


public class KeyGenerate {
	public static void main(String[] args){
		System.out.println(generate());
	}
	
	public static String generate(){
		KeyGenerator kgen = null;
		try {
			//Use AES encryption
			kgen = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//Use 192 bit AES encryption
		kgen.init(192);
		//Generate the Key then store it in aeskey
		SecretKey aeskey = kgen.generateKey();
		//Put that key in a byte[]
		byte[] encoded = aeskey.getEncoded();
		//create a BigInteger (from the byte[]) then turn it into a string with a radix of 16 (base 16)
	    //this means that 0-9 are used to represent 0-9 and a,b,c,d,e,f are used to represent 10-15
	    String output = new BigInteger(1, encoded).toString(16);
	    return output;
	}
}
