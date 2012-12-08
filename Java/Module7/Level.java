import ui.LabyrinthUserInterface;


public class Level {
	
	
	LevelObject[][] level;
	LevelObject[] walls;
	int wallsIndex;
	
	LevelObject start;
	LevelObject end;
	
	Level(int width, int height, LevelObject start, LevelObject end) {
		walls = new LevelObject[width*height];
		wallsIndex = 0;
		level = new LevelObject[height][width];
		this.start = start;
		this.end = end;
	}
	
	void addWall(int x, int y) {
		walls[wallsIndex] = new LevelObject(LabyrinthUserInterface.WALL, x, y);
		level[y][x] = walls[wallsIndex];
		wallsIndex++;
		
	}
	
	boolean wallExistsAtCoordinate(int x, int y) {
		if (level[y][x] != null && level[y][x].type == LabyrinthUserInterface.WALL) {
			return true;
		}
		return false;
	}
}
