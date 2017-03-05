package player;

import java.util.ArrayList;
import java.util.Scanner;

import board.Board;
import piece.Piece;

public abstract class Player {

	String name;

	// number of pieces finished
	int finished = 0;

	// each player starts with 4 available pieces to be played
	Piece[] pieces = new Piece[4];

	// keep track of sticks thrown for the turn
	private ArrayList<Integer> stickArray = new ArrayList<Integer>();
	
	Player() {
		for (int i = 0; i < 4; i++) {
			pieces[i] = new Piece();
		}
	}

	// get the piece from pieces array
	public Piece getPiece(int index) {
		return pieces[index];
	}

	// add throw result to the stick array
	public void addStickArray(int value) {
		stickArray.add(value);
	}

	public void removeStickArray(int index) {
		stickArray.remove(index);
	}

	public int getSizeStickArray() {
		return stickArray.size();
	}

	public int getValueStickArray(int index) {
		return stickArray.get(index);
	}

	public int getIndexStickArray(int value) {
		return stickArray.indexOf(value);
	}

	public void addFinished() {
		finished += 1;
	}

	public boolean hasWon() {
		return (finished == 4);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void printStatus() {
		System.out.println("Player: " + name);
		System.out.println("Piece 1's position: " + this.getPiece(0).getLocation());
		System.out.println("Piece 2's position: " + this.getPiece(1).getLocation());
		System.out.println("Piece 3's position: " + this.getPiece(2).getLocation());
		System.out.println("Piece 4's position: " + this.getPiece(3).getLocation());
		System.out.println();
	}
	
	public abstract String commandInput(Scanner sc);
	public abstract void throwSticks();
	public abstract String choosePiece(Scanner sc);
	public abstract String chooseThrow(Scanner sc);
	public abstract String movePiece(Scanner sc, Board b, String piece, String value);
}
