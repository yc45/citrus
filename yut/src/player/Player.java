package player;

import piece.Piece;

public class Player {

	String name;

	// number of pieces finished
	int finished = 0;

	// each player starts with 4 available pieces to be played
	Piece[] pieces = new Piece[4];

	Player() {
		for (int i = 0; i < 4; i++) {
			pieces[i] = new Piece();
		}
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
