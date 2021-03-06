package project;

public class Hexidecimal {
	
	public static String byte2Hex(byte input){
		Integer hexInt = (input & 0xff);
		String hexString = Integer.toHexString(hexInt);
		if (hexString.length() == 1){
			return "0" + hexString;
		}
		else
			return hexString;
	}
	
	
	public static int char2Hex(char input) throws Exception{
		switch (input){
			case '0': return 0; 
			case '1': return 1; 
			case '2': return 2;
			case '3': return 3;
			case '4': return 4;
			case '5': return 5;
			case '6': return 6; 
			case '7': return 7; 
			case '8': return 8;
			case '9': return 9;
			case 'a': return 10; 
			case 'b': return 11; 
			case 'c': return 12;
			case 'd': return 13;
			case 'e': return 14;
			case 'f': return 15;
			default: throw new Exception();
		}
	}
}
