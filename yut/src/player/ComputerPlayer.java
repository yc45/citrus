package player;

import piece.Piece;

public class ComputerPlayer extends Player {

	public ComputerPlayer() {
		for (int i = 0; i < 4; i++) {
			pieces[i] = new Piece();
		}
	}

}
