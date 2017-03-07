package player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import board.Board;
import helpers.Helpers;
import helpers.Triplet;
import main.Main;
import piece.Piece;

public class ComputerPlayer extends Player {

	public ComputerPlayer() {
		for (int i = 0; i < 4; i++) {
			pieces[i] = new Piece();
		}
	}

	private static String[] fruits = new String[] { "Blood orange", "Grapefruit", "Lemon", "Lime", "Mandarin orange", "Orange", "Pomelo",
			"Tangerine" };
	private static Random rand = new Random();

	// keep track of all possible moves to make this turn
	private ArrayList<Triplet> allMoves = new ArrayList<Triplet>();

	// keep track of locations
	private ArrayList<Integer> myLocations = new ArrayList<Integer>();
	private ArrayList<Integer> oppLocations = new ArrayList<Integer>();

	private String pieceToMove;
	private String throwToUse;
	private String moveInput;

	public static String getRandomFruit() {
		return fruits[rand.nextInt(fruits.length)];
	}

	public void thinkMove() {
		// calculate all the possible moves for this turn
		for (int i = 0; i < 4; i++) {
			if (pieces[i].getLocation() == 30) {
				continue;
			}

			for (int j = 0; j < this.getSizeStickArray(); j++) {
				int[] possibleDestination = Board.possibleLocation(pieces[i].getLocation(), this.getValueStickArray(j));
				allMoves.add(new Triplet(i, this.getValueStickArray(j), possibleDestination[0]));
				if (possibleDestination[1] != -1) {
					allMoves.add(new Triplet(i, this.getValueStickArray(j), possibleDestination[1]));
				}
			}
		}

		// use first set of move as default
		this.pieceToMove = Integer.toString(allMoves.get(0).getPiece());
		this.throwToUse = Integer.toString(allMoves.get(0).getStick());
		this.moveInput = Integer.toString(allMoves.get(0).getDestination());

		// find my locations
		for (int i = 0; i < 4; i++) {
			if (this.getPiece(i).getLocation() != -1 && this.getPiece(i).getLocation() != 30) {
				myLocations.add(this.getPiece(i).getLocation());
			}
		}

		// find opponent's locations
		for (int i = 0; i < 4; i++) {
			if (Main.players.get(0).getPiece(i).getLocation() != -1 && Main.players.get(0).getPiece(i).getLocation() != 30) {
				oppLocations.add(Main.players.get(0).getPiece(i).getLocation());
			}
		}

		Collections.sort(myLocations);
		Collections.sort(oppLocations);

		// print for testing
		/*
		 * for (int i = 0; i < allMoves.size(); i++) {
		 * allMoves.get(i).printTriplet(); }
		 * 
		 * for (int i = 0; i < myLocations.size(); i++) {
		 * System.out.println(Integer.toString(myLocations.get(i))); }
		 * 
		 * for (int i = 0; i < oppLocations.size(); i++) {
		 * System.out.println(Integer.toString(oppLocations.get(i))); }
		 */

		return;
	}

	@Override
	public String commandInput() {
		return "m";
	}

	@Override
	public String choosePiece() {
		this.thinkMove();

		boolean updated = false;
		int n = -1;
		String tempPiece = this.pieceToMove;
		String tempThrow = this.throwToUse;
		String tempMove = this.moveInput;

		// eat opponent's piece if possible
		for (int i = 0; i < allMoves.size(); i++) {
			if (oppLocations.contains(allMoves.get(i).getDestination())) {
				if (allMoves.get(i).getDestination() > Integer.parseInt(tempMove)) {
					tempPiece = Integer.toString(allMoves.get(i).getPiece());
					tempThrow = Integer.toString(allMoves.get(i).getStick());
					tempMove = Integer.toString(allMoves.get(i).getDestination());
					updated = true;
				}
				else if (!updated) {
					n = i;
				}
			}
		}
		if (updated) {
			this.pieceToMove = tempPiece;
			this.throwToUse = tempThrow;
			this.moveInput = tempMove;

			return this.pieceToMove;
		}
		else if (n != -1 && !updated) {
			this.pieceToMove = Integer.toString(allMoves.get(n).getPiece());
			this.throwToUse = Integer.toString(allMoves.get(n).getStick());
			this.moveInput = Integer.toString(allMoves.get(n).getDestination());

			return this.pieceToMove;
		}

		n = -1;

		// stack own pieces if possible
		for (int i = 0; i < allMoves.size(); i++) {
			if (myLocations.contains(allMoves.get(i).getDestination())) {
				if (allMoves.get(i).getDestination() > Integer.parseInt(tempMove)) {
					tempPiece = Integer.toString(allMoves.get(i).getPiece());
					tempThrow = Integer.toString(allMoves.get(i).getStick());
					tempMove = Integer.toString(allMoves.get(i).getDestination());
					updated = true;
				}
				else if (!updated) {
					n = i;
				}
			}
		}
		if (updated) {
			this.pieceToMove = tempPiece;
			this.throwToUse = tempThrow;
			this.moveInput = tempMove;

			return this.pieceToMove;
		}
		else if (n != -1 && !updated) {
			this.pieceToMove = Integer.toString(allMoves.get(n).getPiece());
			this.throwToUse = Integer.toString(allMoves.get(n).getStick());
			this.moveInput = Integer.toString(allMoves.get(n).getDestination());

			return this.pieceToMove;
		}

		// move the farthest piece
		for (int i = 0; i < allMoves.size(); i++) {
			if (allMoves.get(i).getDestination() > Integer.parseInt(tempMove)) {
				tempPiece = Integer.toString(allMoves.get(i).getPiece());
				tempThrow = Integer.toString(allMoves.get(i).getStick());
				tempMove = Integer.toString(allMoves.get(i).getDestination());
			}
		}

		this.pieceToMove = tempPiece;
		this.throwToUse = tempThrow;
		this.moveInput = tempMove;

		return this.pieceToMove;
	}

	@Override
	public String chooseThrow() {
		this.removeStickArray(this.getIndexStickArray(Integer.parseInt(this.throwToUse)));
		return this.throwToUse;
	}

	@Override
	public String movePiece(String piece, String value) {
		allMoves.clear();
		myLocations.clear();
		oppLocations.clear();

		// if piece is off the board and throw is -1, then do
		// nothing
		int startLocation = this.getPiece(Integer.parseInt(piece)).getLocation();
		if (startLocation == -1 && Integer.parseInt(value) == -1) {
			System.out.println("No movements this turn");

			return "";
		}

		// move the piece
		if (startLocation == -1) {
			this.getPiece(Integer.parseInt(piece)).setLocation(Integer.parseInt(this.moveInput));
			System.out.println("Piece #" + Integer.toString(Integer.parseInt(piece) + 1) + " has moved to location " + this.moveInput);
		}
		else {
			for (int i = 0; i < 4; i++) {
				if (this.getPiece(i).getLocation() == startLocation) {
					this.getPiece(i).setLocation(Integer.parseInt(this.moveInput));
					System.out.println("Piece #" + Integer.toString(i + 1) + " has moved to location " + this.moveInput);

					// if piece passes finish line
					if (Integer.parseInt(this.moveInput) == 30) {
						this.addFinished();
						System.out.println("You have finished a piece");
					}
				}
			}
		}

		return this.moveInput;
	}
}
