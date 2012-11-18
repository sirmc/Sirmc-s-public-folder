package Pirate;

import java.io.PrintStream;
import java.util.Scanner;

public class CoordinateRow {
	PrintStream out;
	public Coordinate[] coordinates;
	
	void printRow() {
		for (int i = 0; i < this.coordinates.length; i++) {
			out.printf("(%d,%d) ", coordinates[i].x, coordinates[i].y);
		}
	}
	
	CoordinateRow(String input) {
		out = System.out;
		Scanner rowScanner = new Scanner(input);
		coordinates = new Coordinate[1];
		
		coordinates[0] = new Coordinate(rowScanner.next());
		while (rowScanner.hasNext()) {
			
			Coordinate coordinate = new Coordinate(rowScanner.next());
			this.append(coordinate);
		}
		rowScanner.close();
	}
	
	void prepend(Coordinate coordinate) {
		int length = coordinates.length + 1;
		Coordinate[] newCoordinates = new Coordinate[length];
		newCoordinates[0] = coordinate;
		
		for (int i = 1; i<length; i++) {
			// Add every coordinate in coordinates to newCoordinates
			// Where coordinates[0] becomes newCoordinates[1]
			newCoordinates[i] = coordinates[i-1];
		}
		this.coordinates = newCoordinates;
	}
	
	void prepend(CoordinateRow row) {
		for (int i = row.coordinates.length; i > 0 ; i--) {
			this.prepend(row.coordinates[i-1]);
		}
	}
	
	void append(Coordinate coordinate) {
		int length = coordinates.length + 1;
		Coordinate[] newCoordinates = new Coordinate[length];
		newCoordinates[length-1] = coordinate;
		
		for (int i = 0; i<(length-1); i++) {
			newCoordinates[i] = coordinates[i];
		}
		this.coordinates = newCoordinates;
	}
	
	void append(CoordinateRow row) {
		for (int i = 0; i < row.coordinates.length ; i++) {
			this.append(row.coordinates[i]);
		}
	}

}
