package player;

import piece.Piece;

public class Player {

	// number of pieces finished
	private int finished = 0;
	
	// number of pieces currently on the board
	private int piecesOnBoard = 0;
	
	// each player starts with 4 available pieces to be played
	private Piece[] pieces = new Piece[4];
	
	public Player() {
		for (int i = 0; i < 4; i++) {
			pieces[i] = new Piece();
		}
	}
	
	// find the piece that's in play
	// if no pieces in play then return the first piece
	public int nextPiece() {
		for (int i = 0; i < 4; i++) {
			if (pieces[i].getLocation() != -1 || pieces[i].getLocation() != 30) {
				return i;
			}
		}
		return 0;
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
