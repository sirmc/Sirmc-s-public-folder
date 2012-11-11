import java.io.PrintStream;
import java.util.Scanner;


public class RepeatCharacter1 {

	/**
	 * @param args
	 */
	PrintStream out = System.out;

	void printNumberOfCharacter(char character, int numberOfCharacters) {
		for (int i = 0; i<numberOfCharacters;i++) {
			out.printf("%c", character);
		}
	}
	int inputWantedNumberOfOutputs() {
		out.printf("Input int for wanted length of output: ");
		Scanner in = new Scanner(System.in);
		return in.nextInt();
		
	}
	void start() {
		int numberOfExclamationmarks = inputWantedNumberOfOutputs();
		printNumberOfCharacter('!', numberOfExclamationmarks);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RepeatCharacter1().start();
	}

}
