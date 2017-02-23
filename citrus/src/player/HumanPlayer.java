package player;

import piece.Piece;

public class HumanPlayer extends Player {

	public HumanPlayer() {
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
}
