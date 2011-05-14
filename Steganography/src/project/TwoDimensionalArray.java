package project;

public class TwoDimensionalArray {

	//Takes a 2d Byte Array and prints it out with
	//numbered rows and columns separated by lines
	public static void print(byte[][] input){
		System.out.print("   | ");
		for(int i = 0; i < input[0].length; i++)
			System.out.print(i + spacer2(i) + "| ");
		System.out.println();
		System.out.print("---");
		for(int i = 0; i < input[0].length; i++)
			System.out.print("-------");
		System.out.println();
		for(int i = 0; i < input.length; i++){
			System.out.print(i + spacer(i) + "| ");
			for(int j = 0; j < input[i].length; j++){
				System.out.print(input[i][j] + spacer2(input[i][j]) + "| ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	//Used in the print 2d array to add spaces so all the lines, line up
	//Used to add spaces after the first column / adds a max of two spaces
	public static String spacer(int input){
		if (Math.abs(input) >= 0 && Math.abs(input) <= 9)
			return "  ";
		else if (Math.abs(input) >= 10 && Math.abs(input) <= 99)
			return " ";
		else
			return "";
	}
	
	//Used in the print 2d array to add spaces so all the lines, line up
	//Used to add spaces after all the data in the table and in the top row
	//Adds a max a four spaces.
	public static String spacer2(int input){
		if (input >= 0 && input <= 9)
			return "    ";
		else if(input >= 10 && input <= 99)
			return "   ";
		else if(input >= 100)
			return "  ";
		else if(input <= -1 && input >= -9)
			return "   ";
		else if(input <= -10 && input >= -99)
			return "  ";
		else
			return " ";
	}
	
	public static byte[] toSingleArray(byte[][] input){
		byte[] output = new byte[16];
		for(int row = 0; row < 4; row++){
			for(int col = 0; col < 4; col++){
				output[(row * 4) + col] = input[row][col];
			}
		}
		return output;
	}
	
	public static byte[][] fromSingleArray(byte[] input){
		byte[][] output = new byte[(input.length)/4][4];
		for (int row = 0; row < ((input.length)/4); row = row + 1){
			for (int col = 0; col < 4; col = col + 1){
				output[row][col] = input[((row * 4) + col)];
			}
		}
		return output;
	}
	
}
