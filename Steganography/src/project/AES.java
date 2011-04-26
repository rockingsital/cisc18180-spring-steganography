package project;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AES {
	
	byte[][] state;
	byte[] key;
	byte[][] w;
	byte[] rCon;
	byte[][] sBox;
	byte[][] iSBox;
	
	int round; //Used to represent what round the encryption algorithms is on (will be 12 in the end)
	static final int blockSize = 4; //In Words (128 bits, 16 bytes)
	static final int keySize = 6; //In Words (192 bits, 24 bytes)
	
	public AES(byte[] input){
		key = generateKey();
		//Put input array of length 16 into 4x4 state array
		for (int row = 0; row < 4; row++){
			for (int col = 0; col < 4; col++){
				state[row][col] = input[(row * 4) + col];
			}
		}
		w = keyExpansion(key);
	}
	
	public static void main(String[] args){
		AES test = new AES();
		System.out.println("192 bit Key Example: " + test.keyToString());
	}
	
	public byte[] generateKey(){
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
		return encoded;
	}
	
	public String keyToString(){
		//create a BigInteger (from the byte[]) then turn it into a string with a radix of 16 (base 16)
	    //this means that 0-9 are used to represent 0-9 and a,b,c,d,e,f are used to represent 10-15
	    String output = new BigInteger(1, key).toString(16);
	    return output;
	}
	
	public byte[][] keyExpansion(byte[] key){
		byte[][] output = new byte[][4];
		//Put key into the top of the output
		for (int row = 0; row < 6; row++){
			for (int col = 0; col < 4; col++){
				output[row][col] = key[(row * 4) + col];
			}
		}
		
	}
	/**
	public byte[] encrypt(){
		
	}
	*/
	public void addRoundKey(int round){
		
	}
}
