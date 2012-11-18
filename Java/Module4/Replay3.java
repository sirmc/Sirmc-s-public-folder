
import java.io.PrintStream;
import java.util.Scanner;

import ui.UserInterfaceFactory;
import ui.UIAuxiliaryMethods;
import ui.OthelloReplayUserInterface;

public class Replay3 {

	// Name			: Sebastian Osterlund
	// Assignment	: Replay1
	// Date			: 11.11.2012
	static final int CHAR_OFFSET = (int) 'a'; // Used to map 'a' to 0, 'b' to 1 etc.
	
	PrintStream out = System.out;
	OthelloReplayUserInterface ui;
	int[][] gameBoard = new int[8][8];
	
	Replay3() {
		UIAuxiliaryMethods.askUserForInput();
		ui = UserInterfaceFactory.getOthelloReplayUI();
	}
	
	
	// General functions
	int readIntInRange(Scanner in, int start, int end){
		int input = in.nextInt(); 
		if (!(start <= input && end >= input)) {
			out.printf("Data is malformatted, try with other data file. \n");
			System.exit(1);
		} 
		return input;
	}
	
	char readCharInRange(Scanner in, char start, char end) {
		char input = in.next().charAt(0);
		
		if (!(input >= start && input <= end)) {
			out.printf("Data is malformatted, try with other data file. \n");
			System.exit(1);
		} 
		return input;
	}
	// END General functions
	
	void initializeBoard() {
		// Place white at d4 and e5
		ui.place(3, 3, ui.WHITE);
		ui.place(4, 4, ui.WHITE);
		
		// Place black at d5 and e4
		ui.place(3, 4, ui.BLACK);
		ui.place(4, 3, ui.BLACK);
		ui.showChanges();
	}
	
	
	int getPlayer(Scanner line) {
		String player = line.next();
		if (player.equals("wit") || player.equals("white")) {
			return ui.WHITE;
		} else if (player.equals("zwart") || player.equals("black")) {
			return ui.BLACK;
		} else {
			out.printf("Data is malformatted, try with other data file. \n");
			System.exit(1);
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
		//char coordinate = line.next().charAt(0);
		char coordinate = readCharInRange(line, 'a', 'h');
		int row = (int) coordinate - CHAR_OFFSET;
		return row;
	}
	
	int getColumnCoordinate(Scanner line) {
		//return line.nextInt() - 1;
		return readIntInRange(line, 1, ui.NUMBER_OF_COLUMNS) - 1;
	}
	
	void executeMove(Scanner line, int player){
		int placedThisTurn = 0;
		
		while (line.hasNext()) {
			int row = getRowCoordinate(line);
			int column = getColumnCoordinate(line);
			ui.place(row, column, player);
			placedThisTurn++;
			
		}
		
		int conquered = placedThisTurn - 1; // 1 piece is placed by the player
		ui.clearStatusBar();
		ui.printf("%s conquered %d pieces", (player == 1 ? "Black" : "White"), conquered);
	}
	
	void processTurn(String turnOperations) {
		// Main function to process a turn
		Scanner line = new Scanner(turnOperations);
		
		int player = getPlayer(line);
		int thinkingTime = getThinkingTime(line);
		boolean move = getMove(line);
		
		ui.wait(thinkingTime);
		if (move) {
			executeMove(line, player);
		}
		ui.showChanges();
	}
	
	void playGame(Scanner in) {
		initializeBoard();
		
		while (in.hasNext()) {
			String turnOperations = in.nextLine();
			out.printf("%s \n", turnOperations); // Print move
			processTurn(turnOperations);
		}
		in.close();
		
		ui.clear();
		ui.clearStatusBar();
		ui.wait(5000);
		out.printf("Game Finished \n");
	}
	
	void start() {
		Scanner in = new Scanner(System.in);
		in.useDelimiter("=");
		while (in.hasNext()) {
			Scanner game = new Scanner(in.next());
			playGame(game);
		}
		
	}
	
	public static void main(String[] args) {
		
		new Replay3().start();
	}

}
