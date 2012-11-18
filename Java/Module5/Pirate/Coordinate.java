package Pirate;

import java.util.Scanner;

public class Coordinate {
	int x;
	int y;
	
	Coordinate(String input) {
		Scanner in = new Scanner(input);
		in.useDelimiter(",");
		this.x = in.nextInt();
		this.y = in.nextInt();
		in.close();
	}
	
	void offset(int x, int y) {
		this.x += x;
		this.y += y;
	}

}
