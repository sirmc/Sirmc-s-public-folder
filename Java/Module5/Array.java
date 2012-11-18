import java.io.PrintStream;
import java.util.Scanner;


public class Array {

	static final int ARRAY_LENGTH = 20;
	PrintStream out;
	
	Array() {
		out = System.out;
	}
	
	int[] readInNumbers() {
		out.printf("Input %d numbers: \n", ARRAY_LENGTH);
		Scanner in = new Scanner(System.in);
		int[] row = new int[ARRAY_LENGTH];
		
		for (int i = 0; i < row.length; i++) {
			row[i] = in.nextInt();
		}
		in.close();
		return row;
	}
	
	void printReversed(int[] row) {
		for (int i = row.length-1; i >= 0; i--) {
			out.printf("%d ", row[i]);
		}
	}
	
	void start() {
		int row[];
		row = readInNumbers();
		printReversed(row);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Array().start();
	}

}
