package snake;

public class Snake {
	
	static final String UP = "U", DOWN = "D", LEFT="L", RIGHT="R";
	LevelObject[] parts;
	LevelObject partToAdd;
	String direction;
	String queuedDirection;
	
	Snake(int x, int y, String startingDirection) {
		direction = startingDirection;
		queuedDirection = startingDirection;
		parts = new LevelObject[1];
		parts[0] = new LevelObject(ui.SnakeUserInterface.SNAKE, x, y);
		partToAdd = null;
		
	}
	

	
	void grow() {
		// Increase length of snake
		
		addPart(parts[parts.length-1].x, parts[parts.length-1].y);
	}
	
	void addPart(int x, int y) {
		partToAdd = new LevelObject(ui.SnakeUserInterface.SNAKE, x, y);
		
	}
	
	void appendPart(LevelObject part) {
		LevelObject[] newParts = new LevelObject[parts.length+1];
		for (int i = 0; i<parts.length; i++) {
			newParts[i] = parts[i];
		}
		
		newParts[newParts.length-1] = part;
		parts = newParts;
	}
	
	void addQueuedPart() {
		if (partToAdd != null) {
			appendPart(partToAdd);
			partToAdd = null;
		}
	}
	
	void update() {
		// First move all parts except head of snake
		propagateMove(); 
		// Then move head
		switch (direction) {
			case LEFT:
				moveLeft();
				break;
			case RIGHT:
				moveRight();
				break;
			case UP:
				moveUp();
				break;
			case DOWN:
				moveDown();
				break;
		
		}
		
		
		
		
	}
	
	void propagateMove() {
		// Update position of every piece, except head
		for (int i = parts.length-1; i>0; i--) {
			parts[i].x = parts[i-1].x;
			parts[i].y = parts[i-1].y;
		}
	}
	
	void moveLeft() {
		parts[0].x -= 1;
	}
	
	void moveRight() {
		parts[0].x += 1;
	}
	
	void moveUp() {
		parts[0].y -= 1;
	}
	
	void moveDown() {
		parts[0].y += 1;
	}
	
	String getOppositeDirection(String direction) {
		String returnDirection = direction; // Will return error if not initialized
		switch (direction) {
			case LEFT:
				returnDirection = RIGHT;
				break;
			case RIGHT:
				returnDirection = LEFT;
				break;
			case UP:
				returnDirection = DOWN;
				break;
			case DOWN:
				returnDirection = UP;
				break;
			
		
		}
		return returnDirection;
	}
	
	void updateDirection() {
		if (!this.queuedDirection.equals(getOppositeDirection(direction))) {
			direction = queuedDirection;
		}
		
	}
	void setDirection(String direction) {
		if (!this.queuedDirection.equals(getOppositeDirection(direction))) {
			this.queuedDirection= direction;
		}
	}
	
	boolean partExistsAt(int x, int y) {
		for (int i = 0; i<parts.length; i++)  {
			if (parts[i].x == x && parts[i].y == y) {
				return true;
			}
		}
		
		return false;
		
	}

}
