package main;

import java.util.ArrayList;
import java.util.Scanner;

import board.Board;
import player.HumanPlayer;
import player.Player;
import helpers.Helpers;
import piece.Piece;

public class Main {
	private static Scanner sc;

	public static void main(String args[]) {
		System.out.println("This program let's you play the yut game");
		System.out.println();

		System.out.println("Setting up the game board...");
		Board b = new Board();

		// scanner for taking user input
		sc = new Scanner(System.in);

		// setting up player information
		ArrayList<Player> players = new ArrayList<Player>();

		System.out.println("Generating player information...");

		if (args.length == 1 && args[0].equals("2")) {
			players.add(new HumanPlayer());
			System.out.println("Player 1 please enter your name");
			String playerName = sc.nextLine();
			players.get(0).setName(playerName);

			players.add(new HumanPlayer());
			System.out.println("Player 2 please enter your name");
			playerName = sc.nextLine();
			players.get(1).setName(playerName);
		}
		else {
			System.out.println("Please enter the correct args");
			return;
		}

		System.out.println("All Set!");
		System.out.println();

		// keep track of turns
		int turn = 1;

		// calculate player turn
		int currentPlayer = 0;

		while (true) {
			// validate the user input
			// only certain commands are allowed
			boolean validInput = false;
			String userCommand;

			System.out.println("This is turn " + Integer.toString(turn));
			System.out.println("It's " + players.get(currentPlayer).getName() + "'s turn");
			System.out.println();
			
			do {
				
				System.out.println("Please input a command. Below are the valid commands");
				System.out.println("[M]ove");
				System.out.println("[Q]uit");
				userCommand = sc.nextLine();
				if (userCommand.equalsIgnoreCase("m") || userCommand.equalsIgnoreCase("move") || userCommand.equalsIgnoreCase("q")
						|| userCommand.equalsIgnoreCase("quit")) {
					validInput = true;
				}
			}
			while (!validInput);
			validInput = false;

			// if the user chooses to move this turn
			if (userCommand.equalsIgnoreCase("m") || userCommand.equalsIgnoreCase("move")) {
				// print the status of the players
				for (int i = 0; i < Integer.parseInt(args[0]); i++) {
					players.get(i).printStatus();
				}

				// print board reference
				System.out.println("Here is the board for reference");
				b.printBoardReference();

				// throw the stick
				// if throw results in a 4 or 5, throw again. maximum 5 throws
				// per turn
				int throwCount = 0;
				while (throwCount < 5) {
					int sticks = b.throwStick();
					if (sticks > 3) {
						System.out.println("You threw a " + Integer.toString(sticks) + " this turn. You get another throw!");
						b.addStickArray(sticks);
						throwCount++;
					}
					else if (sticks == 0) {
						System.out.println("Your sticks broke");
						break;
					}
					else {
						System.out.println("You threw a " + Integer.toString(sticks) + " this turn");
						b.addStickArray(sticks);
						throwCount++;
						break;
					}
				}

				// player needs to select all the stick results thrown this turn
				// and use it all
				movePiece: while (b.getSizeStickArray() != 0) {
					// player chooses a valid piece to move
					String pieceToMove;
					do {
						System.out.println("Which piece would you like to move?");
						pieceToMove = sc.nextLine();
						if (Helpers.isInteger(pieceToMove)) {
							validInput = ((HumanPlayer) players.get(currentPlayer)).isValidPiece(Integer.parseInt(pieceToMove));
						}
					}
					while (!validInput);
					validInput = false;

					// print all the throws for the turn
					System.out.println("Here are your stick values");
					for (int i = 0; i < b.getSizeStickArray(); i++) {
						System.out.print(b.getValueStickArray(i) + " ");
					}
					System.out.println();

					// player chooses a valid throw to use
					String throwToUse;
					do {
						System.out.println("Which throw would you like to use?");
						throwToUse = sc.nextLine();
						
						// allows the player to go back and choose piece again
						if (throwToUse.equals("b")) {
							continue movePiece;
						}
						
						if (Helpers.isInteger(throwToUse)) {
							if (b.getIndexStickArray(Integer.parseInt(throwToUse)) != -1) {
								b.removeStickArray(b.getIndexStickArray(Integer.parseInt(throwToUse)));
								validInput = true;
							}
						}
					}
					while (!validInput);
					validInput = false;

					// if piece is off the board and throw is -1, then do nothing
					if (players.get(currentPlayer).getPiece(Integer.parseInt(pieceToMove) - 1).getLocation() == -1
							&& Integer.parseInt(throwToUse) == -1) {
						continue;
					}

					// calculate possible locations the piece can move to
					int startLocation = players.get(currentPlayer).getPiece(Integer.parseInt(pieceToMove) - 1).getLocation();
					String moveInput;
					int[] possibleDestination = b.possibleLocation(
							players.get(currentPlayer).getPiece(Integer.parseInt(pieceToMove) - 1).getLocation(), Integer.parseInt(throwToUse));
					if (possibleDestination[1] == -1) {
						System.out.println("Piece #" + pieceToMove + " has moved to location " + Integer.toString(possibleDestination[0]));
						System.out.println();
						moveInput = Integer.toString(possibleDestination[0]);
					}
					else {
						System.out.println("The possible destinations to move to are: " + Integer.toString(possibleDestination[0]) + " and "
								+ Integer.toString(possibleDestination[1]));
						do {
							// only valid destinations are allowed
							System.out.println("Please enter the destination to move to");
							moveInput = sc.nextLine();
							if ((moveInput.equals(Integer.toString(possibleDestination[0])))
									|| (moveInput.equals(Integer.toString(possibleDestination[1])))) {
								validInput = true;
							}
						}
						while (!validInput);
						validInput = false;
					}

					// move the piece

					// if piece has stack, update location for all the stack
					// pieces
					int stackSize = players.get(currentPlayer).getPiece(Integer.parseInt(pieceToMove) - 1).getSizeStackArray();
					if (stackSize != 0) {
						for (int i = 0; i < stackSize; i++) {
							players.get(currentPlayer)
									.getPiece(players.get(currentPlayer).getPiece(Integer.parseInt(pieceToMove) - 1).getValueStickArray(i) - 1)
									.setLocation(Integer.parseInt(moveInput));
						}
					}
					// move the chosen piece
					players.get(currentPlayer).getPiece(Integer.parseInt(pieceToMove) - 1).setLocation(Integer.parseInt(moveInput));

					// if piece passes finish line
					if (Integer.parseInt(moveInput) == 30) {
						for (int i = 0; i <= stackSize; i++) {
							players.get(currentPlayer).addFinished();
							System.out.println("You have finished a piece");
						}

						if (players.get(currentPlayer).hasWon()) {
							System.out.println("Congratulations! " + players.get(currentPlayer).getName() + " has won in " + Integer.toString(turn) + " turns!");
							return;
						}
					}
					else {
						// check if piece can stack
						if (b.getCount(Integer.parseInt(moveInput)) != 0) {
							// get the piece number of the pieces that will be
							// stacked
							ArrayList<Integer> piecesToStack = new ArrayList<Integer>();
							for (int i = 1; i < 5; i++) {
								if (players.get(currentPlayer).getPiece(i - 1).getLocation() == Integer.parseInt(moveInput)
										&& !piecesToStack.contains(i)) {
									piecesToStack.add(i);
								}
							}
							// update the pieces stackArray info
							for (int i = 0; i < piecesToStack.size(); i++) {
								for (int j = 0; j < piecesToStack.size(); j++) {
									if (i != j && players.get(currentPlayer).getPiece(piecesToStack.get(i) - 1)
											.getIndexStackArray(piecesToStack.get(j)) == -1) {
										players.get(currentPlayer).getPiece(piecesToStack.get(i) - 1).addStackArray(piecesToStack.get(j));
									}
								}
							}
							
							// check if piece can eat other pieces
							for (int i = 0; i < 4; i++) {
								if (players.get(1-currentPlayer).getPiece(i).getLocation() == Integer.parseInt(moveInput)) {
									players.get(1-currentPlayer).getPiece(i).setLocation(-1);
									for (int j = 0; j < players.get(1-currentPlayer).getPiece(i).getSizeStackArray(); j++) {
										players.get(1-currentPlayer).getPiece(i).removeStackArray(0);
									}
								}
							}
						}					
					}

					// update number of pieces at location on the board
					if (startLocation == -1) {
						b.setCount(Integer.parseInt(moveInput), b.getCount(Integer.parseInt(moveInput)) + 1 + stackSize);
					}
					else {
						b.setCount(startLocation, b.getCount(startLocation) - 1 - stackSize);
						b.setCount(Integer.parseInt(moveInput), b.getCount(Integer.parseInt(moveInput)) + 1 + stackSize);
					}
				}
				// increment turn
				turn++;

				// rotate player
				currentPlayer = (currentPlayer + 1) % Integer.parseInt(args[0]);
			}
			else if (userCommand.equalsIgnoreCase("q")) {
				do {
					System.out.println("Are you sure you want to quit?");
					String answer = sc.nextLine();
					if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
						System.out.println("You have quit the game");
						return;
					}
					else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
						break;
					}
				}
				while (!validInput);
				validInput = false;
			}
		}
	}
}