package piece;

public class Piece {

	// location is from 0 to 29. 
	// > 29 means reached the finish line
	private int location = 0;
	
	public int getLocation() {
		return location;
	}
	
	public void setLocation(int p) {
		location = p;
	}
}
