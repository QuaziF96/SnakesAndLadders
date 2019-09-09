package main.Controller;

import exceptions.LadderPlacementException;
import exceptions.SnakeGuardPlacementException;
import exceptions.SnakePlacementException;
import main.Model.Board;
import main.Model.Ladder;
import main.Model.Snake;
import main.Model.SnakeGuard;

public class BoardController {

	private Board board;
	private int snakesCount;
	private int laddersCount;
	private int snakeGuardsCount;
	private boolean topTwentySnake;

// - Should be allowed to lay 5 snakes and 5 ladders. DONE 
// - Difference between snake head/tail or ladder top/base cannot be more than 30 DONE
// - Ladder top/base cannot be placed on a snake head DONE
// - Ladder base cannot be placed on another ladder's head DONE
// - No ladder base at location 1 and no ladder top at location 100 DONE
// - Snake head cannot be placed on top of an existing ladder head/base DONE
// - Snake head cannot be placed +-1 either side of another snake head DONE
// - Only one snake in locations 81 to 100 at any one time DONE
// - Snake head cannot be on or behind tail, and vice versa for ladder DONE
// - Snakes and ladders cannot be horizontal DONE
	

	// Constructor
	public BoardController(Board board) {
		this.board = board;
	}

	// Add Snake
	public void add(Snake thisSnake) throws SnakePlacementException {

		this.snakesCount = board.getSnakesCount();

		// Check max 5 Snakes
		if (this.snakesCount >= 5) {
			System.out.println("z");
			throw new SnakePlacementException("Snake limit reached");
		}

		// Check top/bottom difference
		if (thisSnake.getHead() - thisSnake.getTail() > 30) {
			System.out.println("x");
			throw new SnakePlacementException("Snake length invalid");
		}
		
		//Check top/bottom not in same place and top is above bottom
		if (thisSnake.getTail() >= thisSnake.getHead()) {
			throw new SnakePlacementException("Snake position invalid (tail on or above head");
		}

		// No snake head on existing ladder head/base
		for (Ladder i : this.board.getLS()) {
			System.out.println("c");
			if (thisSnake.getHead() == i.getTop() || thisSnake.getHead() == i.getBottom()) {
				throw new SnakePlacementException("Snake position invalid (Ladder Clash)");
			}
		}

		// No snake head on another snake head / either side of another snake head
		for (Snake i : this.board.getSS()) {
			System.out.println("v");
			if (thisSnake.getHead() == i.getHead() || thisSnake.getHead() == (i.getHead() - 1)
					|| thisSnake.getHead() == (i.getHead() + 1)) {
				throw new SnakePlacementException("Snake position invalid (Snake Clash)");
			}
		}
		
		// Only one snake head allowed in positions 81 to 100
		if (thisSnake.getHead() >= 81 && thisSnake.getHead() <= 100) {
			System.out.println("b");
			if (topTwentySnake == false) {
				this.topTwentySnake = true;
			}
			else {
				System.out.println("n");
				throw new SnakePlacementException("Snake position invalid (Top Twenty Snake Clash)");
			}
		} 
		
		//Horizontal check
		if (this.horizontalCheck(thisSnake.getHead(), thisSnake.getTail())) {
			throw new SnakePlacementException("Snake position invalid (horizontal entity)");
		}

		// Adding snake to Board
		board.setSS(snakesCount, thisSnake);
		board.setSnakesCount(++snakesCount);
	
	}

	// Add Ladder
	public void add(Ladder thisLadder) throws LadderPlacementException {

		this.laddersCount = board.getLaddersCount();

		// Check max 5 Ladders
		if (this.laddersCount >= 5) {
			throw new LadderPlacementException("Ladder limit reached");
		}

		// Check top/bottom difference
		if (thisLadder.getTop() - thisLadder.getBottom() > 30) {
			throw new LadderPlacementException("Ladder length invalid");
		}
		
		//Check top/bottom not in same place and top is above bottom
		if (thisLadder.getBottom() >= thisLadder.getTop()) {
			throw new LadderPlacementException("Ladder position invalid (bottom on or above top");
		}

		// Check ladder doesn't begin or end on a snake head
		for (Snake i : this.board.getSS()) {
			if (i.getHead() == thisLadder.getTop() || i.getHead() == thisLadder.getBottom()) {
				throw new LadderPlacementException("Ladder position invalid (Snake Clash)");
			}
		}

		// Check ladder top/bottom isn't placed on another ladder top
		for (Ladder i : this.board.getLS()) {
			if (i.getTop() == thisLadder.getBottom() || i.getTop() == thisLadder.getTop()) {
				throw new LadderPlacementException("Ladder position invalid (Ladder Clash)");
			}
		}

		// Check ladder top and bottom (no position 1 or 100)
		if (thisLadder.getBottom() == 1 || thisLadder.getTop() == 100) {
			throw new LadderPlacementException("Ladder position invalid (1 or 100)");
		}
		
		//Horizontal check
		if (this.horizontalCheck(thisLadder.getTop(), thisLadder.getBottom())) {
			throw new LadderPlacementException("Ladder position invalid (horizontal entity)");
		}

		// Adding ladder to Board
		board.setLS(laddersCount, thisLadder);
		board.setLaddersCount(++laddersCount);
	}
	
	public void add(SnakeGuard thisSnakeGuard) throws SnakeGuardPlacementException{
		this.snakeGuardsCount = this.board.getSnakeGuardCount();
		
		for (Snake s : this.board.getSS()) {
			if (thisSnakeGuard.getLocation() == s.getHead()) {
				throw new SnakeGuardPlacementException("Cannot place trap there!");
			}
		}
		
		board.setSnakeGuards(snakeGuardsCount, thisSnakeGuard);
		board.setSnakeGuardCount(++snakeGuardsCount);
	}
	
	//Check whether Entity is horizontal
	public boolean horizontalCheck(int top, int bottom) {
		
		int board[][] = new int[10][10];
		int square = 1;
		int targetRow = 0;
		boolean result = false;
		
		//2D Array representing the board
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				
				board[i][j] = square;
				square++;
			}
		}
		
		//Finding the index of the row which contains the Entity's top
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				
				if (top == board[i][j]) {
					targetRow = i;	
				}
			}
		}
		
		//Checking whether the bottom is also in that row.
		//If so, it's a horizontal entity and method returns true.
		for (int i = 0; i < 10; i++) {
			if (bottom == board[targetRow][i]) {
				result = true;
			} 
		}
		return result;
	}
}