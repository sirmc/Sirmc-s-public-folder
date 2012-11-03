import java.io.PrintStream;
import java.util.Scanner;

public class Collatz {

	// Name			: Sebastian …sterlund
	// Assignment	: Collatz
	// Date			: 3.11.2012
	
	private int startNumber;
	private int currentNumber;
	
	PrintStream out;
	Collatz() {
		out = new PrintStream(System.out);
		
	}
	int inputNumber() {
		Scanner in = new Scanner(System.in);
		out.printf("Input starting number for sequence: ");
		int inputNumber = in.nextInt();
		if (inputNumber < 0) {
			
			inputNumber = inputNumber();
		}
		
		return inputNumber;
		
	}
	
	int nextNumber() {
		if (currentNumber % 2 == 0) {
			// Number is even
			return currentNumber/2;
		} else {
			// Number is uneven
			return 3*currentNumber+1;
		}
	}
	
	
	void start() {
		startNumber = inputNumber();
		currentNumber = startNumber;
		
		// Loop until sequence reaches 1
		while (currentNumber != 1) {
			out.printf("%d ", currentNumber);
			currentNumber = nextNumber();
			
		}
		
		out.printf("1");
	}
	
	public static void main(String[] args) {
		new Collatz().start();
	}

}
