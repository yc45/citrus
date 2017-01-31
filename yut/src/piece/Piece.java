package piece;

import java.util.ArrayList;

public class Piece {

	// location is from 0 to 29.
	// 30 means reached the finish line
	// -1 means off the board
	private int location = -1;

	private ArrayList<Integer> stackArray = new ArrayList<Integer>();

	public int getLocation() {
		return location;
	}

	public void setLocation(int p) {
		location = p;
	}

	public void addStackArray(int value) {
		stackArray.add(value);
	}

	public void removeStackArray(int index) {
		stackArray.remove(index);
	}

	public int getSizeStackArray() {
		return stackArray.size();
	}

	public int getValueStickArray(int index) {
		return stackArray.get(index);
	}

	public int getIndexStackArray(int value) {
		return stackArray.indexOf(value);
	}

}
