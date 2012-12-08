import ui.LabyrinthUserInterface;


class Path {

	LevelObject[] steps;
	int length;
	
	Path() {
		steps = new LevelObject[32*24];
		length = 0;
	}
	
	Path copy() {
		Path pathCopy = new Path();
		pathCopy.steps = this.steps.clone();
		pathCopy.length = this.length;
		
		return pathCopy;
	}
	
	void addStep(int x, int y) {
		steps[length] = new LevelObject(LabyrinthUserInterface.PATH, x, y);
		length++;
	}
	
	void prependStep(int x, int y) {
		for (int i=length; i>0; i--) {
			steps[i] = steps[i-1];
		}
		steps[0] = new LevelObject(LabyrinthUserInterface.PATH, x, y);
		length++;
	}
	
	boolean stepExistsAtCoordinate(int x, int y) {
		for (int i =0; i<length;i++) {
			if (steps[i].x == x && steps[i].y == y) {
				return true;
			}
		}
		return false;
	}

}
