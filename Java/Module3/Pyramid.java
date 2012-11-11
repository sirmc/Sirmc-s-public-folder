import java.io.PrintStream;


public class Pyramid {

	/**
	 * @param args
	 */
	static final int OUTPUT_BUFFER_SIZE = 80;
	
	PrintStream out = System.out;
	int characterIntOffset; // Used to convert 'a' to 0, 'b' to 1 etc.
	
	
	void printPadding(int numberOfWhiteSpaces) {
		for (int i = 0; i < numberOfWhiteSpaces; i++) {
		out.printf(" ");
		}
		
	}
	void printNumberOfCharacter(char character, int numberOfCharacters) {
		for (int i = 0; i<numberOfCharacters;i++) {
			out.printf("%c", character);
		}
		out.printf("\n");
	}
	
	void printPyramidLineWithChar(char characterToPrint) {
		int lengthOfLine = 1 + 2 * ((int) characterToPrint - characterIntOffset);
		int rowPadding = (OUTPUT_BUFFER_SIZE - lengthOfLine)/2;
		printPadding(rowPadding);
		printNumberOfCharacter(characterToPrint, lengthOfLine);
	}
	void printPyramid() {
		characterIntOffset = (int) 'a'; 
		for (char c = 'a'; c<='o'; c++) {
			printPyramidLineWithChar(c);
		}
	}
	
	void start() {
		printPyramid();
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Pyramid().start();
	}

}
