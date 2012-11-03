import java.io.PrintStream;
import java.util.Scanner;

public class SecondSmallest {

	// Name			: Sebastian …sterlund
	// Assignment	: SecondSmallest
	// Date			: 3.11.2012
	
	PrintStream out;
	private int smallest;
	private int secondSmallest;
	private int inputBuffer;
	
	
	SecondSmallest() {
		
		out = new PrintStream(System.out);
		
	}
	
	void processInput(int inputInt) {
		if (inputInt > 0){
			// Number has to be positive
		}
		else if (inputInt < smallest) {
			// If input is the smallest int yet, 
			// set smallest to input.
			secondSmallest = smallest;
			smallest = inputInt;
		} else if (inputInt < secondSmallest) {
			// If input is second smallest number yet, 
			// set seconSmallest to input. 
			secondSmallest = inputInt;
		}
	}
	
	void start() {
		Scanner in = new Scanner(System.in);
		out.println("Input row of integers, in which to check for second smallest.");
		out.println("The program can be terminated by entering 0 as input: ");
		smallest = in.nextInt();
		inputBuffer = in.nextInt();
		// Initialize smallest and secondSmallest
		if (inputBuffer < smallest) {
			secondSmallest = smallest;
			smallest = inputBuffer;
		}
		else {
			secondSmallest = inputBuffer;
		}
		
		while (in.hasNext()) {
			processInput(in.nextInt());
		}
		out.printf("\nThe second smallest number is: %d", secondSmallest);
		
		
	}
	
	public static void main(String[] args) {
		new SecondSmallest().start();
	}

}
