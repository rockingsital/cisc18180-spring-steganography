package project;

import java.math.BigInteger;
import java.lang.Math;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AES {

	byte[][] state;
	byte[] key;
	byte[][] w;
	byte[][] rCon;
	byte[][] sBox;
	byte[][] iSBox;

	int round; //Used to represent what round the encryption algorithms is on (will be 12 in the end)
	static final int blockSize = 4; //In Words (128 bits, 16 bytes)
	static final int keySize = 6; //In Words (192 bits, 24 bytes)
	static final int numberOfRounds = 12;

	public AES(byte[] input){
		key = generateKey();
		//Put input array of length 16 into 4x4 state array
		state = new byte[4][4];
		state = TwoDimensionalArray.fromSingleArray(input);
		rCon = buildRCon();
		sBox = SBoxes.buildSBox();
		iSBox = SBoxes.buildISBox();
		w = keyExpansion(key);
	}

	public static void main(String[] args){
		byte[] testText = new byte[16];
		for (int i = 0; i < testText.length; i++)
			testText[i] = (byte) ((i+3) * 5);
		long startTime = System.currentTimeMillis();
		AES test = new AES(testText);
		System.out.println(System.currentTimeMillis() - startTime);
		System.out.println("192 bit Key Example: " + arrayToString(test.key));
		//TwoDimensionalArray.print(test.state);
		System.out.println("Input State: " + arrayToString(TwoDimensionalArray.toSingleArray(test.state)));
		test.encrypt();
		System.out.println("rCon");
		TwoDimensionalArray.print(test.rCon);
		System.out.println("sBox");
		TwoDimensionalArray.print(test.sBox);
		System.out.println("iSBox");
		TwoDimensionalArray.print(test.iSBox);
		System.out.println("Key Expansion");
		TwoDimensionalArray.print(test.w);
		System.out.println("Encrypted State: " + arrayToString(TwoDimensionalArray.toSingleArray(test.state)));
		test.decrypt();
		System.out.println("Decrypted State: " + arrayToString(TwoDimensionalArray.toSingleArray(test.state)));
	}

	public byte[] generateKey(){
		/**
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
		byte [] encoded = aeskey.getEncoded();
		return encoded;
		**/
		String key = "6ea013aa065fd44347c2b9371bdb2df31c66770409c7ac40";
		byte[] output = new byte[24];
		for (int i = 0; i < 48; i = i + 2)
			output[i/2] = (byte) ((Character.digit(key.charAt(i), 16) << 4) + Character.digit(key.charAt(i+1), 16));
		return output;
	}

	public static String arrayToString(byte[] input){
		String output = "";
		for(byte b: input)
			output = output + Hexidecimal.byte2Hex(b);
		return output;
	}

	//Takes your initial 6 row key and expands it into
	//a 52 row key that is used for the encryption
	
	public byte[][] keyExpansion(byte[] key){
		byte[][] output = new byte[52][4];
		//Put key into the top of the output
		for (int row = 0; row < 6; row++){
			for (int col = 0; col < 4; col++){
				output[row][col] = key[(row * 4) + col];
			}
		}
		//Generate the rest using the existing key
		byte[] temp = new byte[4];
		//Loop set to run for 46 times (52-6)
		for (int row = keySize; row < blockSize * (numberOfRounds+1); ++row){
			//Set temp equal to the previously generate row
			for(int i = 0; i < 4; i++){
				temp[i] = output[row-1][i];
			}
			//Every 6th row, rotWord, subWord then XOR with a value from the rCon
			if (row % keySize == 0){
				temp = subWord(rotWord(temp));
				for(int i = 0; i < 4; i++){
					temp[i] = (byte)( (int)temp[i] ^ (int)rCon[row/keySize][i] );
				}
			}
			//Then take the row 6 rows up and XOR it with the row currently being generated
			for(int i = 0; i < 4; i++){
				output[row][i] = (byte) ((int)output[row-keySize][i] ^ (int)temp[i] );
			}
		}
		return output;
	}

	
	public void encrypt(){
		addRoundKey(0);
		for (int round = 1; round <= (numberOfRounds - 1); ++round){
			subBytes(); 
			shiftRows();  
			mixColumns(); 
			addRoundKey(round);
		}  
		subBytes();
		shiftRows();
		addRoundKey(numberOfRounds);
	}
	 

	public static byte[][] buildRCon(){
		byte[][] output = new byte[11][4];
		output[0][0] = (byte) 0x00;
		output[1][0] = (byte) 0x01;
		output[2][0] = (byte) 0x02;
		output[3][0] = (byte) 0x04;
		output[4][0] = (byte) 0x08;
		output[5][0] = (byte) 0x10;
		output[6][0] = (byte) 0x20;
		output[7][0] = (byte) 0x40;
		output[8][0] = (byte) 0x80;
		output[9][0] = (byte) 0x1b;
		output[10][0] = (byte) 0x36;
		for (int row = 0; row < 11; row = row + 1){
			for (int col = 1; col < 4; col = col + 1){
				output[row][col] = (byte) 0x00;
			}
		}
		return output;
	}

	public byte[] rotWord(byte[] input){
		byte tmp = input[0];
		input[0] = input[1];
		input[1] = input[2];
		input[2] = input[3];
		input[3] = tmp;
		return input;
	}

	public byte[] subWord(byte[] input){
		byte[] output = new byte[input.length];
		Integer hexInt;
		String hexString;
		char sRow;
		char sCol;
		for (int col = 0; col < input.length; col = col + 1){
			hexInt = (input[col] & 0xff);
			hexString = Integer.toHexString(hexInt);
			if (hexString.length() == 1){
				sRow = '0';
				sCol = hexString.charAt(0);
			}
			else{
				sRow = hexString.charAt(0);
				sCol = hexString.charAt(1);
			}
			try {
				output[col] = sBox[Hexidecimal.char2Hex(sRow)][Hexidecimal.char2Hex(sCol)];
			} catch (SteveCodedThisException e) {
				e.printStackTrace();
			}
		}
		return output;
	}
	
	
	
	//Take a value from the state table and XOR it with the inverse
	//value from the w table.  If you get state[4][1] you would XOR
	//that with w[1][4]. You flip the row and column for the w table.
	public void addRoundKey(int round){
		for(int row = 0; row < 4; row++){
			for(int column = 0; column < 4; column++){
				state[row][column] = (byte) ((int)state[row][column] ^ (int)w[column + (round*4)][row]);
			}
		}
	}
	
	public void subBytes(){
		Integer hexInt;
		String hexString;
		char sRow;
		char sCol;
		for (int row = 0; row < 4; row++){
			for (int col = 0; col < 4; col++){
				hexInt = (state[row][col] & 0xff);
				hexString = Integer.toHexString(hexInt);
				if (hexString.length() == 1){
					sRow = '0';
					sCol = hexString.charAt(0);
				}
				else{
					sRow = hexString.charAt(0);
					sCol = hexString.charAt(1);
				}
				try {
					state[row][col] = sBox[Hexidecimal.char2Hex(sRow)][Hexidecimal.char2Hex(sCol)];
				} catch (SteveCodedThisException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void shiftRows(){
		byte[] temp = new byte[4];
		for(int row = 1; row < 4; row++){
			temp = state[row];
			for (int col = 0; col < 4; col++){
				state[row][(col + 4 - row) % 4] = temp[col];
			}
		}
	}
	
	private void mixColumns()
	{
	  byte[][] temp = new byte[4][4];
	  for (int row = 0; row < 4; row++)  
	  {
	    for (int col = 0; col < 4; col++)
	    {
	      temp[row][col] = this.state[row][col];
	    }
	  }
	      
	  for (int col = 0; col < 4; col++)
	  {
	    this.state[0][col] = (byte) (fieldMultiply(2,(int)temp[0][col]) ^
	                               fieldMultiply(3,(int)temp[1][col]) ^
	                               fieldMultiply(1,(int)temp[2][col]) ^
	                               fieldMultiply(1,(int)temp[3][col]) );

	    this.state[1][col] = (byte) (fieldMultiply(1,(int)temp[0][col]) ^
	                               fieldMultiply(2,(int)temp[1][col]) ^
	                               fieldMultiply(3,(int)temp[2][col]) ^
	                               fieldMultiply(1,(int)temp[3][col]) );

	    this.state[2][col] = (byte) (fieldMultiply(1,(int)temp[0][col]) ^
	                               fieldMultiply(1,(int)temp[1][col]) ^
	                               fieldMultiply(2,(int)temp[2][col]) ^
	                               fieldMultiply(3,(int)temp[3][col]) );

	    this.state[3][col] = (byte) (fieldMultiply(3,(int)temp[0][col]) ^
	                               fieldMultiply(1,(int)temp[1][col]) ^
	                               fieldMultiply(1,(int)temp[2][col]) ^
	                               fieldMultiply(2,(int)temp[3][col]) );
	    }
	  }
	
	public static int fieldMultiply(int a, int b) {
		   int p = 0;
		   for (int n=0; n<8; n++) {
		      p = ((b & 0x01) > 0) ? p^a : p;
		      boolean kyle = ((a & 0x80) > 0); //Is the first binary digit one?
		      a = ((a<<1) & 0xFE);
		      if (kyle)
		         a = a ^ 0x1b;
		      b = ((b>>1) & 0x7F);
		   }
		   return p;
		}
	
	public void decrypt(){
		
		addRoundKey(numberOfRounds);
		for (int round = numberOfRounds - 1; round > 0; --round){
			inverseShiftRows();  
			inverseSubBytes(); 
			addRoundKey(round);
			inverseMixCols(); 
		}  
		inverseShiftRows();
		inverseSubBytes();
		addRoundKey(0);
	}
	
	public void inverseShiftRows(){
		
		byte[] temp = new byte[4];
		for(int row = 1; row < 4; row++){
			temp = state[row];
			for (int col = 0; col < 4; col++){
				state[row][(col + 4 + row) % 4] = temp[col];
			}
		}
	}
	
	public void inverseSubBytes(){
		
		Integer hexInt;
		String hexString;
		char sRow;
		char sCol;
		for (int row = 0; row < 4; row++){
			for (int col = 0; col < 4; col++){
				hexInt = (state[row][col] & 0xff);
				hexString = Integer.toHexString(hexInt);
				if (hexString.length() == 1){
					sRow = '0';
					sCol = hexString.charAt(0);
				}
				else{
					sRow = hexString.charAt(0);
					sCol = hexString.charAt(1);
				}
				try {
					state[row][col] = iSBox[Hexidecimal.char2Hex(sRow)][Hexidecimal.char2Hex(sCol)];
				} catch (SteveCodedThisException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void inverseMixCols(){
		
		byte[][] temp = new byte[4][4];
		  for (int row = 0; row < 4; row++)  
		  {
		    for (int col = 0; col < 4; col++)
		    {
		      temp[row][col] = this.state[row][col];
		    }
		  }
		  
		  for (int col = 0; col < 4; col++)
		  {
		    this.state[0][col] = (byte) (fieldMultiply(9,(int)temp[0][col]) ^
		                               fieldMultiply(11,(int)temp[1][col]) ^
		                               fieldMultiply(13,(int)temp[2][col]) ^
		                               fieldMultiply(14,(int)temp[3][col]) );

		    this.state[1][col] = (byte) (fieldMultiply(14,(int)temp[0][col]) ^
		                               fieldMultiply(9,(int)temp[1][col]) ^
		                               fieldMultiply(11,(int)temp[2][col]) ^
		                               fieldMultiply(13,(int)temp[3][col]) );

		    this.state[2][col] = (byte) (fieldMultiply(13,(int)temp[0][col]) ^
		                               fieldMultiply(14,(int)temp[1][col]) ^
		                               fieldMultiply(9,(int)temp[2][col]) ^
		                               fieldMultiply(11,(int)temp[3][col]) );

		    this.state[3][col] = (byte) (fieldMultiply(11,(int)temp[0][col]) ^
		                               fieldMultiply(13,(int)temp[1][col]) ^
		                               fieldMultiply(14,(int)temp[2][col]) ^
		                               fieldMultiply(9,(int)temp[3][col]) );
		    }
	}
}
