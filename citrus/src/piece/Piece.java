package piece;

public class Piece {

	// location is from 0 to 29.
	// 30 means reached the finish line
	// -1 means off the board
	private int location = -1;

	public int getLocation() {
		return location;
	}

	public void setLocation(int p) {
		location = p;
	}

}
