import java.io.PrintStream;
import java.util.Scanner;

import ui.UserInterfaceFactory;
import ui.UIAuxiliaryMethods;
import ui.LabyrinthUserInterface;

public class LongestPath {
	
	static final int WIDTH = 32, HEIGHT=24;
	static final int WAITING_TIME = 50; // Waiting time between placement of path pieces 
	static final int WALL = LabyrinthUserInterface.WALL, START = 3, END=4;
	PrintStream out;
	LabyrinthUserInterface ui;
	Level level;

	LongestPath() {
		out = System.out;
		UIAuxiliaryMethods.askUserForInput();
		ui = UserInterfaceFactory.getLabyrinthUI(WIDTH, HEIGHT);
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
	
	// Initializing functions
	void loadLevel() {
		
		Scanner levelScanner = new Scanner(System.in);
		levelScanner.useDelimiter("=");
		
		int startCoordinates[] = getCoordinateFromScanner(new Scanner(levelScanner.next()));
		int endCoordinates[] = getCoordinateFromScanner(new Scanner(levelScanner.next()));
		
		LevelObject start = new LevelObject(START, startCoordinates[0], startCoordinates[1]);
		LevelObject end = new LevelObject(END, endCoordinates[0], endCoordinates[1]);
		
		level = new Level(WIDTH,HEIGHT, start, end);
		
		Scanner input = new Scanner(levelScanner.next());
		populateLevel(input); // Add walls to level
		input.close();
		levelScanner.close();		
		
		
	}
	
	void populateLevel(Scanner input) {
		while (input.hasNext()) {
			Scanner wallScanner = new Scanner(input.nextLine());
			addWall(wallScanner);
		}
	}
	
	int[] getCoordinateFromScanner(Scanner input) {
		// Returns array in format [x, y]
		int result[] = new int[2];
		result[0] = readIntInRange(input, 0, WIDTH-1); // x-value
		result[1] = readIntInRange(input, 0, HEIGHT-1); // y-value
		return result;
	}
	
	void addWall(Scanner input) {
		level.addWall(readIntInRange(input, 0, WIDTH-1), readIntInRange(input, 0, HEIGHT-1));
	}
	
	// Render functions
	void renderLevel() {
		for (int i=0; i<level.wallsIndex; i++) {
			ui.place(level.walls[i].x, level.walls[i].y, level.walls[i].type);
		}
		ui.showChanges();
	}
	
	void renderSolution(Path path) {
		for (int i = 0; i<path.length; i++) {
			ui.place(path.steps[i].x, path.steps[i].y, ui.PATH);
			ui.showChanges();
			ui.wait(WAITING_TIME);
		}
	} 
	
	boolean collision(int x, int y, Path path) {
		
		if (level.wallExistsAtCoordinate(x, y)) {
			return true;
		} else if (path.stepExistsAtCoordinate(x, y)) {
			
			return true;
		}
		
		return false;
	}
	
	Path maxPath(Path[] routes) {
		Path result = routes[0];
		for (int i = 1; i<routes.length; i++) {
			if (routes[i].length > result.length) {
				result = routes[i];
			}
		}
		
		return result;
		
	}
	
	Path longestRouteDirection(Path path) {
		
		
		Path leftPath = new Path();
		Path rightPath = new Path();
		Path upPath = new Path();
		Path downPath = new Path();
		int x = path.steps[path.length-1].x;
		int y = path.steps[path.length-1].y;
		
			if (x>0 && !collision(x-1, y, path)) {
				leftPath = longestRoute(x-1, y, path);
			}
			if (x < WIDTH && !collision(x+1, y, path)) {
				rightPath = longestRoute(x+1, y, path);
			}
			if (y > 0 && !collision(x, y-1, path)) {
				upPath = longestRoute(x, y-1, path);
			}
			if (y < HEIGHT && !collision(x, y+1, path)) {
				downPath = longestRoute(x, y+1, path);
			}
		
		Path[] routes = new Path[4];
		routes[0] = leftPath;
		routes[1] = rightPath;
		routes[2] = upPath;
		routes[3] = downPath;
		
		return maxPath(routes);
	}
	
	Path longestRoute(int x, int y, Path path) {
		
		ui.place(x, y, ui.PATH);
		ui.showChanges();
		ui.wait(WAITING_TIME);
		
		// Stopping condition
		if (x == level.end.x && y == level.end.y) {
			path.addStep(x, y); // Add end to path
			ui.place(x, y, ui.EMPTY); // Remove piece from UI
			ui.showChanges();
			
			return path;
		}
		
		// Make copy of path object and give copy to recursive function
		Path path2 = path.copy();
		path2.addStep(x, y);
		
		path = longestRouteDirection(path2);
		
		ui.place(x, y, ui.EMPTY);
		ui.wait(WAITING_TIME);
		ui.showChanges();
		return path;
	}
	
	void start() {
		loadLevel();
		renderLevel();
		ui.encircle(level.start.x, level.start.y);
		ui.encircle(level.end.x, level.end.y);
		
		
		Path solution = longestRoute(level.start.x, level.start.y, new Path());
		ui.printf("Start: %d %d \n", level.start.x, level.start.y);
		ui.printf("End: %d %d \n", level.end.x, level.end.y);
		ui.printf("Length of solution: %d \n", solution.length);
		renderSolution(solution);
	}
	public static void main(String[] args) {
		new LongestPath().start();

	}

}
