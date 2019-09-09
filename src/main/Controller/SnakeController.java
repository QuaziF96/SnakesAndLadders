package main.Controller;

import java.util.Arrays;
import java.util.stream.IntStream;

import exceptions.SnakePlacementException;
import main.Model.Board;
import main.Model.Entity;
import main.Model.Player;
import main.Model.Snake;

public class SnakeController {
	
	private static final int[] outerLeft = {1,20,21,40,41,60,61,80,81,100};
	private static final int[] outerRight = {10,11,30,31,50,51,70,71,90,91};
	
	private Board board;
	
	//Constructor (so that we can see the state of the board to validate snake movement)
	public SnakeController(Board board) {
		this.board = board;
	}
	
	//Tells the selected Snake to move to the target location. Throws error if any issues
	public void move(Snake thisSnake, int headTarget) throws SnakePlacementException {

		int headMove = headTarget - thisSnake.getHead();
		int tailTarget = thisSnake.getTail() + headMove;
		
		//Checking for SnakeGuards at headTarget
		for (int i = 0; i < this.board.getSG().size(); i++) {
			if (this.board.getSG().get(i).getLocation() == headTarget) {
				throw new SnakePlacementException("Move invalid - snake guard there");
			}
		}
		
		//Checking for other snake heads at headTarget
		for (int i = 0; i < this.board.getSS().size(); i++) {
			if (this.board.getSS().get(i).getHead() == headTarget) {
				throw new SnakePlacementException("Move invalid - already a snake head there");
			}
		}
		
		//Checking for ladderTops at headTarget
				for (int i = 0; i < this.board.getLS().size(); i++) {
					if (this.board.getLS().get(i).getTop() == headTarget) {
						throw new SnakePlacementException("Move invalid - already a ladder top there");
					}
				}
		
		//Checking for ladderBottoms at headTarget
		for (int i = 0; i < this.board.getLS().size(); i++) {
			if (this.board.getLS().get(i).getBottom() == headTarget) {
				throw new SnakePlacementException("Move invalid - already a ladder base there");
			}
		}
		
		//Checking tail doesn't go off the board
		if (tailTarget < 1) {
			throw new SnakePlacementException("Move invalid - tail out of bounds");
		}
		
		//Setting new head and tail values
		thisSnake.setHead(headTarget);
		thisSnake.setTail(tailTarget);
	}	
	
	//1 is up, 2 is down, 3 is left, 4 is right
	//only checks for snake head out of bounds error, rest should be 
	//checked in snake move function
	public int getTarget(Snake snake,int direction) throws SnakePlacementException {
		int location = snake.getHead(), index = location, i = 0;
		
		if (direction == 1 && location > 90) {
			throw new SnakePlacementException("Can't move up, already at the top of board");
		}
		else if (direction == 2 && (location >= 11 && location <= 20)) {
			throw new SnakePlacementException("Can't move down, already at the bottom of board");
		}
		else if (direction == 3 && (Arrays.stream(outerLeft).anyMatch(x -> x == location))) {
			throw new SnakePlacementException("Can't move left, already at the left side of board");
		}
		else if (direction == 4 && (Arrays.stream(outerRight).anyMatch(x -> x == location))) {
			throw new SnakePlacementException("Can't move right, already at the right side of board");
		}
		if (direction == 1) {
			while (index % 10 != 0) {
				index++;
				i++;
			}
			return location + ((i * 2) + 1);
		}
		else if (direction == 2) {
			while (index % 10 != 1) {
				index--;
				i++;
			}
			return location - ((i * 2) + 1);
		}
		else if (direction == 3) {
			index = Integer.parseInt(Integer.toString(location).substring(0, 1));
			if (index % 2 == 0) {
				return location - 1;
			}
			return location + 1;
		}
		else {
			index = Integer.parseInt(Integer.toString(location).substring(0, 1));
			if (index % 2 == 0) {
				return location + 1;
			}
			return location - 1;
		}
	}
	
	
}


