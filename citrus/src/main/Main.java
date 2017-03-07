package main;

import java.util.ArrayList;
import java.util.Scanner;

import board.Board;
import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;

public class Main {
	public static Scanner sc;
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static Board b = new Board();

	public static void main(String args[]) {
		System.out.println("This program let's you play the yut game");
		System.out.println();

		System.out.println("Setting up the game board...");

		System.out.println("Generating player information...");

		boolean validInput = false;
		sc = new Scanner(System.in);
		String decision;
		
		do {
			// only valid options are allowed
			System.out.println("Do you want to play [1]P or [2]P?");
			decision = sc.nextLine();
			if (decision.equals("1") || (decision.equals("2"))) {
				validInput = true;
			}
		}
		while (!validInput);
		validInput = false;

		// setting up player information
		if (decision.equals("1")) {
			players.add(new HumanPlayer());
			System.out.println("Player 1 please enter your name");
			String playerName = sc.nextLine();
			players.get(0).setName(playerName);

			players.add(new ComputerPlayer());
			playerName = ComputerPlayer.getRandomFruit();
			players.get(1).setName(playerName);
		}
		else {
			players.add(new HumanPlayer());
			System.out.println("Player 1 please enter your name");
			String playerName = sc.nextLine();
			players.get(0).setName(playerName);

			players.add(new HumanPlayer());
			System.out.println("Player 2 please enter your name");
			playerName = sc.nextLine();
			players.get(1).setName(playerName);
		}

		System.out.println("All Set!");
		System.out.println();

		// keep track of turns
		int turn = 1;

		// calculate player turn
		int currentPlayer = 0;

		while (true) {
			System.out.println("This is turn " + Integer.toString(turn));
			System.out.println("It's your move " + players.get(currentPlayer).getName());

			// take player command
			String playerCommand = players.get(currentPlayer).commandInput();

			// if the user chooses to move this turn
			if (playerCommand.equalsIgnoreCase("m") || playerCommand.equalsIgnoreCase("move")) {
				// print the status of the players
				for (int i = 0; i < 2; i++) {
					players.get(i).printStatus();
				}

				// print board reference
				System.out.println("Here is the board for reference");
				b.printBoardReference();

				// throw sticks for the turn
				players.get(currentPlayer).throwSticks();

				// player needs to select all the stick results thrown this turn
				// and use it all
				while (players.get(currentPlayer).getSizeStickArray() != 0) {
					// player chooses a valid piece to move and choose a throw
					// to go along with the move
					String pieceToMove = players.get(currentPlayer).choosePiece();
					String throwToUse = players.get(currentPlayer).chooseThrow();
					// the position the player chose to move to
					String moveInput = players.get(currentPlayer).movePiece(pieceToMove, throwToUse);

					if (moveInput.equals("")) {
						continue;
					}

					if (players.get(currentPlayer).hasWon()) {
						System.out.println(
								"Congratulations! " + players.get(currentPlayer).getName() + " has won in " + Integer.toString(turn) + " turns!");
						return;
					}
					// check if piece can eat other pieces
					for (int i = 0; i < 4; i++) {
						if (players.get(1 - currentPlayer).getPiece(i).getLocation() == Integer.parseInt(moveInput)) {
							players.get(1 - currentPlayer).getPiece(i).setLocation(-1);
							System.out.println(
									players.get(1 - currentPlayer).getName() + "'s piece " + Integer.toString(i + 1) + " has been sent back.");
						}
					}
				}

				System.out.println();
				
				// increment turn
				turn++;

				// rotate player
				currentPlayer = (currentPlayer + 1) % 2;
			}
		}
	}
}