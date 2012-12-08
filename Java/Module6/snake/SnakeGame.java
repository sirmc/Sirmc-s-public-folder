package snake;

import java.io.PrintStream;
import java.util.Scanner;

import ui.Event;
import ui.UserInterfaceFactory;
import ui.UIAuxiliaryMethods;
import ui.SnakeUserInterface;

public class SnakeGame {
	// Name			: Sebastian Osterlund
	// Assignment	: Snake
	// Date			: 23.11.2012
	
	static final int WIDTH=32, HEIGHT=24; // Width and height of game surface
	static final double FPS = 10; // Frames Per Second
	
	PrintStream out;
	SnakeUserInterface ui;
	Snake snake;
	Level level;
	
	SnakeGame() {
		out = System.out;
		UIAuxiliaryMethods.askUserForInput();
		ui = UserInterfaceFactory.getSnakeUI(WIDTH, HEIGHT);
		ui.setFramesPerSecond(FPS);
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
	
	
	
	String readDirection(Scanner in) {
		String direction = in.next();
		if (!direction.equals(Snake.LEFT) && !direction.equals(Snake.RIGHT) 
				&& !direction.equals(Snake.UP) && !direction.equals(Snake.DOWN)) {
			out.printf("Data is malformatted (unknown direction), try with other data file. \n");
			System.exit(1);
		}
		
		return direction;
	}
	
	
	// Initializing functions
	void loadLevel(String levelData) {
		level = new Level(WIDTH,HEIGHT, snake);
		
		Scanner input = new Scanner(levelData);
		while (input.hasNext()) {
			Scanner wallScanner = new Scanner(input.nextLine());
			addWall(wallScanner);
		}
		input.close();
		
		
		
	}
	
	void addWall(Scanner input) {
		level.addObject(SnakeUserInterface.WALL, readIntInRange(input, 0, WIDTH-1), readIntInRange(input, 0, HEIGHT-1));
	}
	
	void initializeSnake(Scanner input, String direction) {
		// Add head
		Scanner snakeHeadScanner = new Scanner(input.nextLine());
		snake = new Snake(snakeHeadScanner.nextInt(), snakeHeadScanner.nextInt(), direction);
		snakeHeadScanner.close();
		addSnakeParts(input);
		
	}
	
	void addSnakeParts(Scanner input) {
		while (input.hasNext()) {
			Scanner partScanner = new Scanner(input.nextLine());
			snake.addPart(partScanner.nextInt(), partScanner.nextInt());
			partScanner.close();
		}
	}
	

	// Game "physics"
	void updateSnake() {
		snake.updateDirection(); // Sets snake direction to queuedDirection
		checkForCollision(); // Collision Check
		if (snake.parts[0].x >= WIDTH-1 && snake.direction.equals(Snake.RIGHT)){
			
			snake.propagateMove();
			snake.parts[0].x = 0;
		} else if (snake.parts[0].x <= 0 && snake.direction.equals(Snake.LEFT)){
			
			snake.propagateMove();
			snake.parts[0].x = WIDTH-1;
			
		} else if (snake.parts[0].y >= HEIGHT-1 && snake.direction.equals(Snake.DOWN)){
			snake.propagateMove();
			snake.parts[0].y = 0;
		} else if (snake.parts[0].y <= 0 && snake.direction.equals(Snake.UP)){
			
			snake.propagateMove();
			snake.parts[0].y = HEIGHT-1;
			
		} else {
			snake.update();
			
		}
		snake.addQueuedPart();
		
		
		
	}
	
	void checkForCollision() {
		
		checkForCollisionWithSelf(); // Check if snake collides with it self
		
		for (int i = 0; i<level.objectsIndex; i++)  {
			if (isCollisionWithWallAtIndex(i)) {
				out.printf("Collision");
				System.exit(1);
			} else if (isCollisionWithAppleAtIndex(i)) {
				// Collision with apple
				snake.grow();
				level.removeObjectAtIndex(i);
				addApple();
			}
		}
	}
	
	void checkForCollisionWithSelf() {
		for (int i = 1; i < snake.parts.length; i++) {
			if (snake.parts[i].x == snake.parts[0].x && snake.parts[i].y == snake.parts[0].y) {
				ui.printf("Collision");
				System.exit(1);
			}
		}
	}
	
	boolean isCollisionWithAppleAtIndex(int i)  {
		
		return level.objects[i].type == SnakeUserInterface.FOOD && level.objects[i].x == snake.parts[0].x 
				&& level.objects[i].y == snake.parts[0].y;
	}
	
	boolean isCollisionWithWallAtIndex(int i) {
		return level.objects[i].type == SnakeUserInterface.WALL && level.objects[i].x == snake.parts[0].x 
				&& level.objects[i].y == snake.parts[0].y;
	}
	
	
	// Event handling
	void processEvent(Event event) {
		
		if (event.name.equals("arrow")) {
			// Handle arrow-key press
			snake.setDirection(event.data);
		} else if (event.name.equals("alarm") && event.data.equals("refresh")) {
			
			clearOldSnake(); // Removes the old snake from ui
			updateSnake(); // Move the snake
			renderSnake(); 
			
		}
	}
	void addApple() {
		int randomX = UIAuxiliaryMethods.getRandom(0, level.width-1);
		int randomY = UIAuxiliaryMethods.getRandom(0, level.height-1);
		
		while (level.objectExistsAt(randomX, randomY) || snake.partExistsAt(randomX, randomY)) {
			randomX = UIAuxiliaryMethods.getRandom(0, level.width-1);
			randomY = UIAuxiliaryMethods.getRandom(0, level.height-1);
		}
		level.addObject(SnakeUserInterface.FOOD, randomX, randomY);
		ui.place(level.objects[level.objectsIndex-1].x, level.objects[level.objectsIndex-1].y, level.objects[level.objectsIndex-1].type);
	}
	
	// Render functions
	void renderLevel() {
		for (int i=0; i<level.objectsIndex; i++) {
						ui.place(level.objects[i].x, level.objects[i].y, level.objects[i].type);
		}
		ui.showChanges();
	}
	
	void renderSnake() {
		
		for (int i =0; i< snake.parts.length; i++) {
			out.printf("%d %d \n", snake.parts[i].x, snake.parts[i].y);

			ui.place(snake.parts[i].x, snake.parts[i].y, SnakeUserInterface.SNAKE);
		}
		ui.showChanges();
	}
	
	void clearOldSnake() {
		for (int i =0; i< snake.parts.length; i++) {
			ui.place(snake.parts[i].x, snake.parts[i].y, SnakeUserInterface.EMPTY);
		}
		ui.showChanges();
	}
	

	void playGame() {
		
		// Initial render
		renderLevel();
		renderSnake();
		
		
		// Main game loop
		while (true) {
			
				
			Event event = ui.getEvent();
			processEvent(event); // Processes events (including refresh, which moves snake)
			
			
		}
	}
	

	
	void start() {
		Scanner in = new Scanner(System.in);
		in.useDelimiter("=");
		Scanner snakeScanner = new Scanner(in.next());
		initializeSnake(snakeScanner, readDirection(in));
		loadLevel(in.next());
		in.close();
		
		addApple();
		
		playGame();
	}
	
	public static void main(String[] args) {
		new SnakeGame().start();

	}

}
