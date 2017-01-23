package player;

import piece.Piece;

public class Player {

	int finished = 0;
	int piecesOnBoard = 0;
	
	public Piece[] pieces = new Piece[4];
	
	public Player() {
		for (int i = 0; i < 4; i++) {
			pieces[i] = new Piece();
		}
	}
	
	// find the piece that's in play
	// if no pieces in play then return the first piece
	public int nextPiece() {
		for (int i = 0; i < 4; i++) {
			if (pieces[i].getLocation() != 0) {
				return i;
			}
		}
		return 0;
	}
	
	// return number of pieces that are finished
	public int getFinished() {
		return finished;
	}
	
	public void addFinished() {
		finished += 1;
	}
	
	// return number of pieces currently on the board
	public int getPieces() {
		return piecesOnBoard;
	}
	
	void addPieces() {
		piecesOnBoard += 1;
	}
	
	public boolean hasWon() {
		return (finished == 4);
	}
}
