package player;

import java.util.ArrayList;
import piece.Piece;

public abstract class Player {

	String name;

	// number of pieces finished
	int finished = 0;

	// each player starts with 4 available pieces to be played
	Piece[] pieces = new Piece[4];

	// keep track of sticks thrown for the turn
	private ArrayList<Integer> stickArray = new ArrayList<Integer>();

	Player() {
		for (int i = 0; i < 4; i++) {
			pieces[i] = new Piece();
		}
	}

	// throw the stick
	// if throw results in a 4 or 5, throw again. maximum 5 throws
	// per turn
	public void throwSticks() {
		int throwCount = 0;
		while (throwCount < 5) {
			int random = (int) (Math.random() * 100 + 1);
			int value;

			if (random <= 25) {
				value = 2;
			}
			else if (random <= 50) {
				value = 3;
			}
			else if (random <= 65) {
				value = 1;
			}
			else if (random <= 80) {
				value = 4;
			}
			else if (random <= 90) {
				value = 5;
			}
			else if (random <= 95) {
				value = -1;
			}
			else {
				value = 0;
			}

			if (value > 3) {
				System.out.println("You threw a " + Integer.toString(value) + " this turn. You get another throw!");
				this.addStickArray(value);
				throwCount++;
			}
			else if (value == 0) {
				System.out.println("Your sticks broke");
				break;
			}
			else {
				System.out.println("You threw a " + Integer.toString(value) + " this turn");
				this.addStickArray(value);
				throwCount++;
				break;
			}
		}
		System.out.println();
	}

	// check if this piece is valid to be moved for the turn
	// valid if it's not on the board or is currently on the board
	// invalid if piece is already finished
	public boolean isValidPiece(int index) {
		return index >= 1 && index <= 4 && pieces[index - 1].getLocation() != 30;
	}

	// get the piece from pieces array
	public Piece getPiece(int index) {
		return pieces[index];
	}

	// add throw result to the stick array
	public void addStickArray(int value) {
		stickArray.add(value);
	}

	public void removeStickArray(int index) {
		stickArray.remove(index);
	}

	public int getSizeStickArray() {
		return stickArray.size();
	}

	public int getValueStickArray(int index) {
		return stickArray.get(index);
	}

	public int getIndexStickArray(int value) {
		return stickArray.indexOf(value);
	}

	public void addFinished() {
		finished += 1;
	}

	public boolean hasWon() {
		return (finished == 4);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void printStatus() {
		System.out.println("Player: " + name);
		for (int i = 1; i < 5; i++) {
			if (this.getPiece(i-1).getLocation() == -1) {
				System.out.println("Piece " + i + "'s position: off");
			}
			else if (this.getPiece(i-1).getLocation() == 30) {
				System.out.println("Piece " + i + "'s position: finished");
			}
			else {
				System.out.println("Piece " + i + "'s position: " + this.getPiece(i-1).getLocation());
			}
		}
		System.out.println();
	}

	public abstract String commandInput();

	public abstract String choosePiece();

	public abstract String chooseThrow();

	public abstract String movePiece(String piece, String value);
}
