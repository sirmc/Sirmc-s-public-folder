import java.io.PrintStream;
import java.util.Scanner;


public class RepeatCharacter2 {

	/**
	 * @param args
	 */
	PrintStream out = System.out;

	void printNumberOfCharacter(char character, int numberOfCharacters) {
		for (int i = 0; i<numberOfCharacters;i++) {
			out.printf("%c", character);
		}
		out.printf("\n");
	}
	int inputWantedNumberOfOutputs() {
		out.printf("Input int for wanted length of output: ");
		Scanner in = new Scanner(System.in);
		return in.nextInt();
		
	}
	void start() {
		int numberOfExclamationmarks = inputWantedNumberOfOutputs();
		printNumberOfCharacter('!', numberOfExclamationmarks);
		int numberOfCommas = inputWantedNumberOfOutputs();
		printNumberOfCharacter(',', numberOfCommas);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RepeatCharacter2().start();
	}

}
