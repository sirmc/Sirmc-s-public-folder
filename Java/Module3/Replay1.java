import java.io.PrintStream;
import java.util.Scanner;

import ui.UserInterfaceFactory;
import ui.UIAuxiliaryMethods;
import ui.OthelloReplayUserInterface;

public class Replay1 {

	/**
	 * @param args
	 */
	static final int CHAR_OFFSET = (int) 'a';
	PrintStream out = System.out;
	OthelloReplayUserInterface ui;
	Replay1() {
		UIAuxiliaryMethods.askUserForInput();
		ui = UserInterfaceFactory.getOthelloReplayUI();
	}
	
	void initializeBoard() {
		// Place white at d4 and e5
		ui.place(3, 3, ui.WHITE);
		ui.place(4, 4, ui.WHITE);
		
		// Place black at d5 and e4
		ui.place(3, 4, ui.BLACK);
		ui.place(4, 3, ui.BLACK);
		ui.showChanges();
	}
	
	int parsePlayer(Scanner line) {
		String player = line.next();
		if (player.equals("wit") || player.equals("white")) {
			return ui.WHITE;
		} else if (player.equals("zwart") || player.equals("black")) {
			return ui.BLACK;
		} else {
			System.exit(0);
			return 0; // Needed, java will will not compile without return int
		}
		
	}
	
	int getThinkingTime(Scanner line) {
		return line.nextInt();
	}
	
	boolean getMove(Scanner line) {
		// Return True if move, False if pass
		String action = line.next();
		if (action.equals("zet") || action.equals("move")) {
			return true;
		} else {
			return false;
		}
	}
	
	int getRowCoordinate(Scanner line) {
		char coordinate = line.next().charAt(0);
		int row = (int) coordinate - CHAR_OFFSET;
		return row;
	}
	
	int getColumnCoordinate(Scanner line) {
		return line.nextInt() - 1;
	}
	
	void processTurn(String turnOperations) {
		// Main function to process a turn
		Scanner line = new Scanner(turnOperations);
		
		int player = parsePlayer(line);
		int thinkingTime = getThinkingTime(line);
		boolean move = getMove(line);
		
		ui.wait(thinkingTime);
		if (move) {
			int row = getRowCoordinate(line);
			int column = getColumnCoordinate(line);
			ui.place(row, column, player);
		}
		ui.showChanges();
	}
	
	void start() {
		Scanner in = new Scanner(System.in);
		initializeBoard();
		
		while (in.hasNext()) {
			String turnOperations = in.nextLine();
			out.printf("%s \n", turnOperations); // Print move
			processTurn(turnOperations);
		}
		out.printf("Game Finished \n");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Replay1().start();
	}

}
