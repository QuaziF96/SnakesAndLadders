package main.Controller;

import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import main.Model.Board;
import main.Model.Entity;
import main.Model.HumanPiece;
import main.Model.Player;

public class HumanController {
	
	private static final int[] outerUp = IntStream.range(91, 101).toArray();
	private static final int[] innerUp = IntStream.range(81, 91).toArray();
	private static final int[] outerDown = IntStream.range(1, 11).toArray();
	private static final int[] innerDown = IntStream.range(11, 21).toArray();
	private static final int[] outerLeft = {1,20,21,40,41,60,61,80,81,100};
	private static final int[] innerLeft = {2,19,22,39,42,59,62,79,82,99};
	private static final int[] outerRight = {10,11,30,31,50,51,70,71,90,91};
	private static final int[] innerRight = {9,12,29,32,49,52,69,72,89,92}; 
	private HashMap<String,Boolean> moves = new HashMap<String,Boolean>();
	private static final String[] movesString = {"U1L1","U1L2","U2L1","U1R1","U1R2","U2R1",
					"D1L1","D1L2","D2L1","D1R1","D1R2","D2R1"};
	private ArrayList<Integer> moveOptions = new ArrayList<Integer>();;
	
	private Board board;
	
	//Constructor (so that we can see the state of the board to validate snake movement)
	public HumanController(Board board) {
		this.board = board;
		setMovesToTrue();
	}

	public void setMovesToTrue() {
		for (String string: movesString) {
			moves.put(string, true);
		}
	}
	
	public void finalStageLocationCheck(HumanPiece piece) {
		int location = piece.getLocation();
		if (Arrays.stream(outerLeft).anyMatch(x -> x == location)) {
			for (String movesString: moves.keySet()) {
				if (movesString.contains("L")) {
					moves.put(movesString,false);
				}
			}
		}
		else if (Arrays.stream(innerLeft).anyMatch(x -> x == location)) {
			for (String movesString: moves.keySet()) {
				if (movesString.contains("L2")) {
					moves.put(movesString,false);
				}
			}
		}
		else if (Arrays.stream(outerRight).anyMatch(x -> x == location)) {
			for (String movesString: moves.keySet()) {
				if (movesString.contains("R")) {
					moves.put(movesString,false);
				}
			}
		}
		else if (Arrays.stream(innerRight).anyMatch(x -> x == location)) {
			for (String movesString: moves.keySet()) {
				if (movesString.contains("R2")) {
					moves.put(movesString,false);
				}
			}
		}
		if (Arrays.stream(outerUp).anyMatch(x -> x == location)) {
			for (String movesString: moves.keySet()) {
				if (movesString.contains("U")) {
					moves.put(movesString,false);
				}
			}
		}
		else if (Arrays.stream(innerUp).anyMatch(x -> x == location)) {
			for (String movesString: moves.keySet()) {
				if (movesString.contains("U2")) {
					moves.put(movesString,false);
				}
			}
		}
		else if (Arrays.stream(outerDown).anyMatch(x -> x == location)) {
			for (String movesString: moves.keySet()) {
				if (movesString.contains("D")) {
					moves.put(movesString,false);
				}
			}
		}
		else if (Arrays.stream(innerDown).anyMatch(x -> x == location)) {
			for (String movesString: moves.keySet()) {
				if (movesString.contains("D2")) {
					moves.put(movesString,false);
				}
			}
		}	
	}
	
	public boolean isOddRow(int location) {
		int firstDigit = Integer.parseInt(Integer.toString(location).substring(0, 1));
		if (Arrays.stream(outerDown).anyMatch(x -> x == location) || firstDigit % 2 == 0) {
			return true;
		}
		return false;
	}
	
	public int getLocationAbove(int location) {
		int index = location, i = 0;
		while (index % 10 != 0) {
			index++;
			i++;
		}
		return location+((i*2)+1);
	}
	
	public int getLocationBelow(int location) {
		int index = location, i = 0;
		while (index % 10 != 1) {
			index--;
			i++;
		}
		return location-((i*2)+1);
	}
	
	
	
	public void finalStageDiagonalMoves(HumanPiece piece) {
		int location = piece.getLocation(), move;
		String[] diagonalMovesString = {"U1L1","U1R1","D1L1","D1R1"};
		for (String diagonalMove: diagonalMovesString) {
			if (moves.get(diagonalMove)) {
				if (diagonalMove.contains("U")) {
					move = getLocationAbove(location);
				}	
				//contains "D"
				else {
					move = getLocationBelow(location);
				}
				if (diagonalMove.contains("L")) {
					if (isOddRow(location)) {
						this.moveOptions.add(move+1);
					}
					else {
						this.moveOptions.add(move-1);
					}
				}
				//contains "R"
				else {
					if (isOddRow(location)) {
						this.moveOptions.add(move-1);
					}
					else {
						this.moveOptions.add(move+1);
					}
				}	
			}
		}
	}
	public void finalStageKnightMoves(HumanPiece piece) {
		int location = piece.getLocation(),move,move2;
		String[] knightMovesString = {"U1L2","U2L1","U1R2","U2R1","D1L2","D2L1"
									,"D1R2","D2R1"};
		for (String knightMove: knightMovesString) {
			if (moves.get(knightMove)) {
				if (knightMove.contains("U")) {
					move = getLocationAbove(location);
					move2 = getLocationAbove(move);
				}
				//contains "D"
				else {
					move = getLocationBelow(location);
					move2 = getLocationBelow(move);
				}
				if (knightMove.contains("L")) {
					if (knightMove.contains("L1")) {
						if (isOddRow(location)) {
							this.moveOptions.add(move2-1);
						}
						else {
							this.moveOptions.add(move2+1);
						}	
					}
					//contains "L2"
					else {
						if (isOddRow(location)) {
							this.moveOptions.add(move+2);
						}
						else {
							this.moveOptions.add(move-2);
						}
					}
				}
				//contains "R"
				else {
					if (knightMove.contains("R1")) {
						if (isOddRow(location)) {
							this.moveOptions.add(move2+1);
						}
						else {
							this.moveOptions.add(move2-1);
						}
					}
					//contains "R2"
					else {
						if (isOddRow(location)) {
							this.moveOptions.add(move-2);
						}
						else {
							this.moveOptions.add(move+2);
						}
					}
				}
			}	
		}
	}
	
	public ArrayList<Integer> finalStageMoveOptions(HumanPiece piece) {
		setMovesToTrue();
		this.moveOptions.clear();
		finalStageLocationCheck(piece);
		finalStageDiagonalMoves(piece);
		finalStageKnightMoves(piece);
		return this.moveOptions;
	}
	
}
