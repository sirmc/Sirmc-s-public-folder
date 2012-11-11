import java.io.PrintStream;

public class Alphabet {

	// Name			: Sebastian …sterlund
	// Assignment	: Alphabet
	// Date			: 3.11.2012
	
	PrintStream out;
	static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	Alphabet() {
		out = new PrintStream(System.out);
	}
	
	void start() {
		
		for (int i=0; i < alphabet.length(); i++){
			out.printf("%c", alphabet.charAt(i));
		}
		
	}
	public static void main(String[] args) {
		// Lazy Way:
		// System.out.println("abcdefghijklmnopqrstuvwxyz");
		new Alphabet().start();
	}

}
