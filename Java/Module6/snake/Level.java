package snake;



public class Level {
	static final int WALL = ui.SnakeUserInterface.WALL;
	
	
	LevelObject[] objects;
	int objectsIndex;
	int width, height;
	Snake snake;
	

	
	Level(int width, int height, Snake snake) {
		
		this.width = width;
		this.height = height;
		objects = new LevelObject[width*height];
		objectsIndex = 0;
		
		this.snake = snake;

	}
	
	void addObject(int type, int x, int y) {
		
		
		objects[objectsIndex] = new LevelObject(type, x, y);
		objectsIndex++;
		
	}
	
	
	boolean objectExistsAt(int x, int y) {
		for (int i = 0; i<objectsIndex; i++)  {
			if (objects[i].x == x && objects[i].y == y) {
				return true;
			}
		}
		
		return false;
		
	}
	
	void removeObjectAtIndex(int i) {
		objects[i] = objects[objectsIndex-1];
		objectsIndex--;
	}
	


}
