import java.io.PrintStream;
import java.util.Scanner;


public class Othello1 {

	// Name			: Sebastian …sterlund
	// Assignment	: Othello1
	// Date			: 3.11.2012
	static final int BOARD_SIZE = 8*8;
	
	private int blackPieces;
	private int whitePieces;
	
	private double percentageOfBoardCoveredByBlackPieces;
	private double percentageOfPiecesBlack;
	
	
	PrintStream out;
	
	Othello1() {
		out = new PrintStream(System.out);
		
	}
	
	void start(){
		Scanner in = new Scanner(System.in);
		
		out.printf("Inpute number of white pieces: ");
		whitePieces = in.nextInt();
		out.printf("Inpute number of black pieces: ");
		blackPieces = in.nextInt();
		
		
		percentageOfBoardCoveredByBlackPieces = (double) 100 *  // * 100 for conversion to percent
				blackPieces/BOARD_SIZE;
		percentageOfPiecesBlack = (double) 100 * 
				blackPieces/(whitePieces+blackPieces);
		
		out.printf("The percentage of black pieces on the board is: %.2f%% \n", 
				percentageOfBoardCoveredByBlackPieces);
		out.printf("The percentage of black pieces of all the pieces on the board is: %.2f%%",
				percentageOfPiecesBlack);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Othello1().start();

	}

}
