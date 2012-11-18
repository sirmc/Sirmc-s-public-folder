package Pirate;

import java.io.PrintStream;
import java.util.Scanner;
import ui.UIAuxiliaryMethods;

public class Pirate {

	PrintStream out;
	Pirate() {
		out = System.out;
		UIAuxiliaryMethods.askUserForInput();
	}
	
	CoordinateRow offsetRow(CoordinateRow row, int x, int y) {
		for (int i = 0; i < row.coordinates.length; i++) {
			row.coordinates[i].offset(x, y);
		}
		return row;
	}
	
	void solvePuzzle(Scanner in) {
		CoordinateRow solutionRow = new CoordinateRow(in.next());
		solutionRow.append(new CoordinateRow(in.next()));
		solutionRow.prepend(new CoordinateRow(in.next()));
		solutionRow.append(new CoordinateRow(in.next()));
		solutionRow.prepend(new CoordinateRow(in.next()));
		
		// Offset solution by (1,0)
		solutionRow = this.offsetRow(solutionRow, 1, 0);
		
		out.printf("Solution: ");
		solutionRow.printRow();
	}
	
	void start() {
		
		Scanner in = new Scanner(System.in);
		in.useDelimiter("=");
		solvePuzzle(in);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Pirate().start();
	}

}
