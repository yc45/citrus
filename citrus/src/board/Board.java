package board;

import java.util.ArrayList;

public class Board {
	/*represent board

	  10 9  8  7  6  5

	  11 25       20 4

	  12    26  21   3

	          22

	  13    23  27   2

	  14 24       28 1

	  15 16 17 18 19 0 30(finish)
	  				
	  				-1

	*/

	// position 5, 10, and 22 are special as player can change direction
	// -1 is off the board
	// 30 is finished

	// keeps track of number of pieces at the board location
	private int[] boardArray = new int[31];

	// keep track of sticks thrown for the turn
	private ArrayList<Integer> stickArray = new ArrayList<Integer>();

	// creates the board
	// initially no player have pieces on the board
	public Board() {
		for (int i = 0; i < 31; i++) {
			boardArray[i] = 0;
		}
	}

	// return how many pieces are currently at this location
	public int getCount(int index) {
		return boardArray[index];
	}

	// update number of pieces at the location
	public void setCount(int index, int value) {
		boardArray[index] = value;
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

	// determine how many spaces the piece will move on the board
	public int throwStick() {
		// add special throw, 0 and -1
		int random = (int) (Math.random() * 100 + 1);
		if (random < 8) {
			return 0;
		}
		else if (random > 93) {
			return -1;
		}

		// when thrown, the sticks are either "over" or "under"
		// the stick have 2 sides
		int over = 0;
		int up = 0;

		int result;

		// throw 4 sticks and see which side they land on
		for (int i = 0; i < 4; i++) {
			result = (int) (Math.random() * 2);
			if (result == 0) {
				over++;
			}
			else {
				up++;
			}
		}

		switch (over) {
		case 0:
			return 5;
		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 3;
		case 4:
			return 4;
		default: 
			return -1;
		}
		

		// code shouldn't reach here

	}

	// > 29 means reaches the finish line
	public int[] possibleLocation(int begin, int stick) {
		int[] result = new int[2];
		result[0] = -1;
		result[1] = -1;

		if (stick == -1) {
			if (begin == -1) {
				result[0] = -1;
			}
			else if (begin == 20) {
				result[0] = 5;
			}
			else if (begin == 25) {
				result[0] = 10;
			}
			else if (begin == 22) {
				result[0] = 21;
				result[1] = 26;
			}
			else if (begin == 28) {
				result[0] = 22;
			}
			else if (begin == 15) {
				result[0] = 24;
			}
			else if (begin == 0) {
				result[0] = 19;
				result[1] = 29;
			}
			else {
				result[0] = begin - 1;
			}
		}
		else {
			// check if it's on space that have multiple directions
			if (begin == -1) {
				result[0] = 0 + stick;
			}
			else if (begin == 5) {
				result[0] = 5 + stick;
				result[1] = 19 + stick;
			}
			else if (begin == 10) {
				if (stick == 3) {
					result[0] = 13;
					result[1] = 22;
				}
				else {
					result[0] = 10 + stick;
					result[1] = 24 + stick;
				}
			}
			else if (begin == 22) {
				if (stick == 1) {
					result[0] = 23;
					result[1] = 28;
				}
				else if (stick == 2) {
					result[0] = 24;
					result[1] = 29;
				}
				else if (stick == 3) {
					result[0] = 15;
					result[1] = 0;
				}
				else if (stick == 4) {
					result[0] = 16;
					result[1] = 30;
				}
				else if (stick == 5) {
					result[0] = 17;
					result[1] = 30;
				}
			}
			else if (begin == 20) {
				if (stick == 5) {
					result[0] = 15;
				}
				else {
					result[0] = 20 + stick;
				}
			}
			else if (begin == 21) {
				if (stick == 4) {
					result[0] = 15;
				}
				else if (stick == 5) {
					result[0] = 16;
				}
				else {
					result[0] = 21 + stick;
				}
			}
			else if (begin == 23) {
				if (stick == 1) {
					result[0] = 24;
				}
				else if (stick == 2) {
					result[0] = 15;
				}
				else if (stick == 3) {
					result[0] = 16;
				}
				else if (stick == 4) {
					result[0] = 17;
				}
				else if (stick == 5) {
					result[0] = 18;
				}
			}
			else if (begin == 24) {
				if (stick == 1) {
					result[0] = 15;
				}
				else if (stick == 2) {
					result[0] = 16;
				}
				else if (stick == 3) {
					result[0] = 17;
				}
				else if (stick == 4) {
					result[0] = 18;
				}
				else if (stick == 5) {
					result[0] = 19;
				}
			}
			else if (begin == 25) {
				if (stick == 2) {
					result[0] = 22;
				}
				else if (stick == 5) {
					result[0] = 0;
				}
				else {
					result[0] = 25 + stick;
				}
			}
			else if (begin == 26) {
				if (stick == 1) {
					result[0] = 22;
				}
				else if (stick == 4) {
					result[0] = 0;
				}
				else if (stick == 5) {
					result[0] = 30;
				}
				else {
					result[0] = 26 + stick;
				}
			}
			else if (begin == 28) {
				if (stick == 1) {
					result[0] = 29;
				}
				else if (stick == 2) {
					result[0] = 0;
				}
				else {
					result[0] = 30;
				}
			}
			else if (begin == 29) {
				if (stick == 1) {
					result[0] = 0;
				}
				else {
					result[0] = 30;
				}
			}
			else if (begin == 15) {
				if (stick == 5) {
					result[0] = 0;
				}
				else {
					result[0] = 15 + stick;
				}
			}
			else if (begin == 16) {
				if (stick == 4) {
					result[0] = 0;
				}
				else if (stick == 5) {
					result[0] = 30;
				}
				else {
					result[0] = 16 + stick;
				}
			}
			else if (begin == 17) {
				if (stick == 3) {
					result[0] = 0;
				}
				else if (stick > 3) {
					result[0] = 30;
				}
				else {
					result[0] = 17 + stick;
				}
			}
			else if (begin == 18) {
				if (stick == 1) {
					result[0] = 19;
				}
				else if (stick == 2) {
					result[0] = 0;
				}
				else {
					result[0] = 30;
				}

			}
			else if (begin == 19) {
				if (stick == 1) {
					result[0] = 0;
				}
				else {
					result[0] = 30;
				}
			}
			else if (begin == 0) {
				result[0] = 30;
			}
			else {
				result[0] = begin + stick;
			}
		}
		return result;
	}

	public void printBoardReference() {
		System.out.print(Integer.toString(10) + " ");
		System.out.print(Integer.toString(9) + "  ");
		System.out.print(Integer.toString(8) + "  ");
		System.out.print(Integer.toString(7) + "  ");
		System.out.print(Integer.toString(6) + "  ");
		System.out.println(Integer.toString(5));

		System.out.println();

		System.out.print(Integer.toString(11) + " ");
		System.out.print(Integer.toString(25) + "       ");
		System.out.print(Integer.toString(20) + " ");
		System.out.println(Integer.toString(4));

		System.out.println();

		System.out.print(Integer.toString(12) + "   ");
		System.out.print(Integer.toString(26) + "   ");
		System.out.print(Integer.toString(21) + "   ");
		System.out.println(Integer.toString(3));

		System.out.println();

		System.out.println("        " + Integer.toString(22));

		System.out.println();

		System.out.print(Integer.toString(13) + "   ");
		System.out.print(Integer.toString(23) + "   ");
		System.out.print(Integer.toString(28) + "   ");
		System.out.println(Integer.toString(2));

		System.out.println();

		System.out.print(Integer.toString(14) + " ");
		System.out.print(Integer.toString(24) + "       ");
		System.out.print(Integer.toString(29) + " ");
		System.out.println(Integer.toString(1));

		System.out.println();

		System.out.print(Integer.toString(15) + " ");
		System.out.print(Integer.toString(16) + " ");
		System.out.print(Integer.toString(17) + " ");
		System.out.print(Integer.toString(18) + " ");
		System.out.print(Integer.toString(19) + " ");
		System.out.print(Integer.toString(0));
		System.out.println(" 30(Finish)");

		System.out.println();
	}
}
