package main;

import java.util.Scanner;

import board.Board;
import player.Player;
import helpers.Helpers;

public class Main {
	private static Scanner sc;

	public static void main(String args[]) {
		System.out.println("This program let's you play the yut game");
		System.out.println();

		System.out.println("Setting up the game board...");
		Board b = new Board();

		// scanner for taking user input
		sc = new Scanner(System.in);

		System.out.println("Generating player information...");
		Player p1 = new Player();
		System.out.println("Please enter your name");
		String playerName = sc.nextLine();
		p1.setName(playerName);

		System.out.println("All Set!");
		System.out.println();

		while (true) {
			// validate the user input
			// only certain commands are allowed
			boolean validInput = false;
			String userCommand;

			do {
				System.out.println("Please input a command. Below are the valid commands.");
				System.out.println("[M]ove");
				System.out.println("[Q]uit");
				userCommand = sc.nextLine();
				if (userCommand.equalsIgnoreCase("m") || userCommand.equalsIgnoreCase("move")
						|| userCommand.equalsIgnoreCase("q")
						|| userCommand.equalsIgnoreCase("quit")) {
					validInput = true;
				}
			}
			while (!validInput);
			validInput = false;

			// keep track of turns
			int turn = 1;
			
			// if the user chooses to move the piece
			if (userCommand.equalsIgnoreCase("m") || userCommand.equalsIgnoreCase("move")) {
				System.out.println("This is turn " + Integer.toString(turn));
				
				// print the status of the players
				System.out.println("Player1: " + p1.getName());
				System.out.println("Piece 1's position: " + p1.getPiece(0).getLocation());
				System.out.println("Piece 2's position: " + p1.getPiece(1).getLocation());
				System.out.println("Piece 3's position: " + p1.getPiece(2).getLocation());
				System.out.println("Piece 4's position: " + p1.getPiece(3).getLocation());
				System.out.println();

				// print board reference
				System.out.println("Here is the board for reference");
				b.printBoardReference();

				// throw the stick
				// if throw results in a 4 or 5, throw again. maximum 5 throws per turn
				int throwCount = 0;
				while (throwCount < 5) {
					int sticks = b.throwStick();
					if (sticks > 3) {
						System.out.println("You threw a " + Integer.toString(sticks)
								+ " this turn. You get another throw!");
						b.addStickArray(sticks);
						throwCount++;
					}
					else {
						System.out
								.println("You threw a " + Integer.toString(sticks) + " this turn.");
						b.addStickArray(sticks);
						throwCount++;
						break;
					}
				}

				// player needs to select all the stick results thrown this turn
				// and use it all
				while (b.getSizeStickArray() != 0) {
					// player chooses a valid piece to move
					String pieceToMove;
					do {
						System.out.println("Which piece would you like to move?");
						pieceToMove = sc.nextLine();
						if (Helpers.isInteger(pieceToMove)) {
							validInput = p1.isValidPiece(Integer.parseInt(pieceToMove));
						}
					}
					while (!validInput);
					validInput = false;

					// print all the throws for the turn
					System.out.println("Here are your stick values.");
					for (int i = 0; i < b.getSizeStickArray(); i++) {
						System.out.print(b.getValueStickArray(i) + " ");
					}
					System.out.println();
					
					// player chooses a valid throw to use
					String throwToUse;
					do {
						System.out.println("Which throw would you like to use?");
						throwToUse = sc.nextLine();
						if (Helpers.isInteger(throwToUse)) {
							if (b.getIndexStickArray(Integer.parseInt(throwToUse)) != -1) {
								b.removeStickArray(
										b.getIndexStickArray(Integer.parseInt(throwToUse)));
								validInput = true;
							}
						}
					}
					while (!validInput);
					validInput = false;

					// calculate possible locations the piece can move to
					String moveInput;
					int[] possibleDestination = b.possibleLocation(
							p1.getPiece(Integer.parseInt(pieceToMove) - 1).getLocation(),
							Integer.parseInt(throwToUse));
					if (possibleDestination[1] == -1) {
						System.out.println("Piece #" + pieceToMove + " has moved to location "
								+ Integer.toString(possibleDestination[0]));
						moveInput = Integer.toString(possibleDestination[0]);
					}
					else {
						System.out.println("The possible destinations to move to are: "
								+ Integer.toString(possibleDestination[0]) + " and "
								+ Integer.toString(possibleDestination[1]));
						do {
							// only valid destinations are allowed
							System.out.println("Please enter the destination to move to");
							moveInput = sc.nextLine();
							if ((moveInput.equals(Integer.toString(possibleDestination[0])))
									|| (moveInput
											.equals(Integer.toString(possibleDestination[1])))) {
								validInput = true;
							}
						}
						while (!validInput);
						validInput = false;
					}

					// move the piece
					p1.getPiece(Integer.parseInt(pieceToMove) - 1)
							.setLocation(Integer.parseInt(moveInput));

					// if piece passes finish line
					if (Integer.parseInt(moveInput) == 30) {
						p1.addFinished();
						System.out.println("You have finished a piece");

						if (p1.hasWon()) {
							System.out.println("Congratulations! You have won!");
							return;
						}
					}
				}
				// the player's turn ends. increment counter
				turn++;
			}
			else if (userCommand.equalsIgnoreCase("q")) {
				System.out.println("You have quit the game");
				return;
			}
		}
	}
}
