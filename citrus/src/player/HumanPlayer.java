package player;

import java.util.Scanner;

import board.Board;
import helpers.Helpers;
import main.Main;
import piece.Piece;

public class HumanPlayer extends Player {

	public HumanPlayer() {
		for (int i = 0; i < 4; i++) {
			pieces[i] = new Piece();
		}
	}

	@Override
	public String commandInput() {
		// take user command
		// only certain commands are allowed
		boolean validInput = false;
		String userCommand;

		do {
			System.out.println("Please input a command. Below are the valid commands");
			System.out.println("[M]ove");
			System.out.println("[Q]uit");
			userCommand = Main.sc.nextLine();
			if (userCommand.equalsIgnoreCase("m") || userCommand.equalsIgnoreCase("move")) {
				validInput = true;
			}
		}
		while (!validInput);

		return userCommand;
	}

	@Override
	public String choosePiece() {
		boolean validInput = false;
		String pieceToMove;

		do {
			System.out.println("Which piece would you like to move?");
			pieceToMove = Main.sc.nextLine();
			if (Helpers.isInteger(pieceToMove)) {
				validInput = this.isValidPiece(Integer.parseInt(pieceToMove));
			}
		}
		while (!validInput);

		return pieceToMove;
	}

	@Override
	public String chooseThrow() {
		boolean validInput = false;
		String throwToUse;

		// print all the throws for the turn
		System.out.println("Here are your stick values");
		for (int i = 0; i < this.getSizeStickArray(); i++) {
			System.out.print(this.getValueStickArray(i) + " ");
		}
		System.out.println();

		// player chooses a valid throw to use
		do {
			System.out.println("Which throw would you like to use?");
			throwToUse = Main.sc.nextLine();

			if (Helpers.isInteger(throwToUse)) {
				if (this.getIndexStickArray(Integer.parseInt(throwToUse)) != -1) {
					this.removeStickArray(this.getIndexStickArray(Integer.parseInt(throwToUse)));
					validInput = true;
				}
			}
		}
		while (!validInput);

		return throwToUse;
	}

	@Override
	public String movePiece(String piece, String value) {
		// if piece is off the board and throw is -1, then do
		// nothing
		int startLocation = this.getPiece(Integer.parseInt(piece) - 1).getLocation();
		if (startLocation == -1 && Integer.parseInt(value) == -1) {
			System.out.println("No movements this turn");

			return "";
		}

		// calculate possible locations the piece can move to
		String moveInput;
		int[] possibleDestination = Board.possibleLocation(startLocation, Integer.parseInt(value));
		int location1 = possibleDestination[0];
		int location2 = possibleDestination[1];

		boolean validInput = false;
		if (location2 == -1) {
			moveInput = Integer.toString(location1);
		}
		else {
			System.out.println("The possible destinations to move to are: " + Integer.toString(location1) + " and " + Integer.toString(location2));
			do {
				// only valid destinations are allowed
				System.out.println("Please enter the destination to move to");
				moveInput = Main.sc.nextLine();
				if ((moveInput.equals(Integer.toString(location1))) || (moveInput.equals(Integer.toString(location2)))) {
					validInput = true;
				}
			}
			while (!validInput);
			validInput = false;
		}

		// move the piece
		if (startLocation == -1) {
			this.getPiece(Integer.parseInt(piece) - 1).setLocation(Integer.parseInt(moveInput));
			System.out.println("Piece #" + piece + " has moved to location " + moveInput);
		}
		else {
			for (int i = 0; i < 4; i++) {
				if (this.getPiece(i).getLocation() == startLocation) {
					this.getPiece(i).setLocation(Integer.parseInt(moveInput));
					System.out.println("Piece #" + Integer.toString(i + 1) + " has moved to location " + moveInput);

					// if piece passes finish line
					if (Integer.parseInt(moveInput) == 30) {
						this.addFinished();
						System.out.println("You have finished a piece");
					}
				}
			}
		}

		return moveInput;
	}
}
