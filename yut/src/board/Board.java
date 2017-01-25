package board;

public class Board {

	/*represent board
	  
	  10 9  8  7  6  5
	  11 25       20 4
	  12    26  21   3
	          22
	  13    23  27   2
	  14 24       28 1
	  15 16 17 18 19(0 or 29)
	  
	  */
	
	// position 5, 10, and 22 are special as player can change direction
	
	
	// keeps track of number of pieces at the board location
	private int[] boardArray = new int[30];
	
	// creates the board
	// initially no player have pieces on the board, set position to -1
	public Board() {
		for (int i = 0; i < 30; i++) {
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
	
	// determine how many spaces the piece will move on the board
	public int throwStick() {
		
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
		
		switch(over) {
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
		}
		
		// code shouldn't reach here
		return -1;
	}
	
	// > 29 means reaches the finish line
	public int[] possibleLocation(int begin, int stick) {
		int[] result = new int[2];
		result[0] = -1;
		result[1] = -1;
		
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
		
		return result;
	}
	
	
	public void printBoard() {
		System.out.print(Integer.toString(boardArray[10]) + "  ");
		System.out.print(Integer.toString(boardArray[9]) + "  ");
		System.out.print(Integer.toString(boardArray[8]) + "  ");
		System.out.print(Integer.toString(boardArray[7]) + "  ");
		System.out.print(Integer.toString(boardArray[6]) + "  ");
		System.out.print(Integer.toString(boardArray[5]));
		
		System.out.println();
		System.out.println();
		
		System.out.print(Integer.toString(boardArray[11]) + "  ");
		System.out.print(Integer.toString(boardArray[25]) + "        ");
		System.out.print(Integer.toString(boardArray[20]) + "  ");
		System.out.print(Integer.toString(boardArray[4]));
		
		System.out.println();
		System.out.println();
		
		System.out.print(Integer.toString(boardArray[12]) + "    ");
		System.out.print(Integer.toString(boardArray[26]) + "    ");
		System.out.print(Integer.toString(boardArray[21]) + "    ");
		System.out.print(Integer.toString(boardArray[3]));
		
		System.out.println();
		System.out.println();
		
		System.out.print("        " + Integer.toString(boardArray[22]));
		
		System.out.println();
		System.out.println();
		
		System.out.print(Integer.toString(boardArray[13]) + "    ");
		System.out.print(Integer.toString(boardArray[23]) + "    ");
		System.out.print(Integer.toString(boardArray[28]) + "    ");
		System.out.print(Integer.toString(boardArray[2]));
		
		System.out.println();
		System.out.println();
		
		System.out.print(Integer.toString(boardArray[14]) + "  ");
		System.out.print(Integer.toString(boardArray[24]) + "        ");
		System.out.print(Integer.toString(boardArray[29]) + "  ");
		System.out.print(Integer.toString(boardArray[1]));
		
		System.out.println();
		System.out.println();
		
		System.out.print(Integer.toString(boardArray[15]) + "  ");
		System.out.print(Integer.toString(boardArray[16]) + "  ");
		System.out.print(Integer.toString(boardArray[17]) + "  ");
		System.out.print(Integer.toString(boardArray[18]) + "  ");
		System.out.print(Integer.toString(boardArray[19]) + "  ");
		System.out.print(Integer.toString(boardArray[0]));
		
		System.out.println();
		System.out.println();
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
