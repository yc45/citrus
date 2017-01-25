package main;

import java.util.Scanner;

import board.Board;
import player.Player;
import piece.Piece;


public class Main {
	private static Scanner sc;
	
	public static void main(String args[]) {
		System.out.println("This program let's you play the yut game");
		System.out.println();
		
		System.out.println("Setting up the game board...");
		Board b = new Board();
		
		System.out.println("Generating player information...");
		Player p1 = new Player();
		
		System.out.println("All Set!");
		System.out.println();
		
		// take user input
		sc = new Scanner(System.in);
		String userInput;
		String moveInput;
		
		
		while (true) {
			boolean validInput = false;
			// validate the user input
			// only certain commands are allowed
			do {
				System.out.println("Please input a command. Below are the valid commands.");
				System.out.println("[M]ove");
				System.out.println("[V]iew Map");
				System.out.println("[S]tatus");
				System.out.println("[Q]uit");
				userInput =  sc.nextLine();
				if (userInput.equalsIgnoreCase("m") || userInput.equalsIgnoreCase("v") || userInput.equalsIgnoreCase("q") || userInput.equalsIgnoreCase("s")) {
					validInput = true;
				}
			} while (!validInput);
			validInput = false;
			
			// if the user chooses to move the piece
			if (userInput.equalsIgnoreCase("m")) {
				// throw the stick
				int sticks = b.throwStick();
				System.out.println("You threw a " + Integer.toString(sticks) + " this turn");
				System.out.println();
				
				// find the index of the piece that's already on the board
				// if none on the board, then get the first piece available
				int nextPiece = p1.nextPiece();

				// get the location of the piece that's about to move
				int startLocation = p1.getPiece(nextPiece).getLocation();
				if (startLocation == -1) {
					p1.addPieces();
					System.out.println("Your piece is currently off the board");
					System.out.println();
				}
				else {
					System.out.println("Your piece is currently at location " + Integer.toString(startLocation));
					System.out.println();
				}
				
				// calculate the possible destinations to move to
				int[] possibleDestination = b.possibleLocation(startLocation, sticks);
				if (possibleDestination[1] == -1) {
					System.out.println("The possible destinations to move to are: " + Integer.toString(possibleDestination[0]));
				}
				else {
					System.out.println("The possible destinations to move to are: " + Integer.toString(possibleDestination[0]) + " and " + Integer.toString(possibleDestination[1]));
				}
				System.out.println();
				System.out.println("Here is the current map");
				b.printBoard();
				System.out.println("Here is a map for location reference");
				b.printBoardReference();

				do {
					// only valid destinations are allowed
					System.out.println("Please enter a destination to move to");
					moveInput = sc.nextLine();
					if ((moveInput.equals(Integer.toString(possibleDestination[0]))) || (moveInput.equals(Integer.toString(possibleDestination[1])))) {
						validInput = true;
					}
				} while (!validInput);
				validInput = false;
				
				// move the piece
				p1.getPiece(nextPiece).setLocation(Integer.parseInt(moveInput));
				
				// if piece passes finish line
				if (Integer.parseInt(moveInput) == 30) {
					p1.addFinished();
					p1.removePieces();
					System.out.println("You have finished a piece");
					
					if (p1.hasWon()) {
						System.out.println("Congratulations! You have won!");
						return;
					}
					
					b.setCount(startLocation, b.getCount(startLocation) - 1);
					continue;
				}

				// update piece locations
				if (startLocation == -1) {
					b.setCount(Integer.parseInt(moveInput), b.getCount(Integer.parseInt(moveInput)) + 1);
				}
				else {
					b.setCount(startLocation, b.getCount(startLocation) - 1);
					b.setCount(Integer.parseInt(moveInput), b.getCount(Integer.parseInt(moveInput)) + 1);
				}
			}
			else if (userInput.equalsIgnoreCase("v")) {
				b.printBoard();
			}
			else if (userInput.equalsIgnoreCase("s")) {
				System.out.println("You have " + Integer.toString(p1.getPieces()) + " piece(s) currently on the board");
				System.out.println("You have finished " + Integer.toString(p1.getFinished()) + " piece(s)");
				System.out.println();
			}
			else if (userInput.equalsIgnoreCase("q")) {
				System.out.println("You have quit the game");
				return;
			}
		}
	}
}
