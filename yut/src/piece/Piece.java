package piece;

import board.Board;

public class Piece {

	// location is from 0 to 29. 
	// > 29 means reached the finish line
	int location = 0;
	
	public int getLocation() {
		return location;
	}
	
	public void setLocation(int p) {
		location = p;
	}
}
