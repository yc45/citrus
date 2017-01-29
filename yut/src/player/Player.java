package player;

import piece.Piece;

public class Player {

	private String name;

	// number of pieces finished
	private int finished = 0;

	// each player starts with 4 available pieces to be played
	private Piece[] pieces = new Piece[4];

	public Player() {
		for (int i = 0; i < 4; i++) {
			pieces[i] = new Piece();
		}
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

	// return number of pieces that are finished
	public int getFinished() {
		return finished;
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
}
