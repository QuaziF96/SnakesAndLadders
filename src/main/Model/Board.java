package main.Model;
import java.util.ArrayList;



public class Board {
	
	private HumanPiece[] pieces = new HumanPiece[4];
	private Dice dice;
	private ArrayList<Snake> ss = new ArrayList<Snake>();
	private ArrayList<Ladder> ls = new ArrayList<Ladder>();
	private ArrayList<SnakeGuard> snakeGuards = new ArrayList<SnakeGuard>();
	int snakesCount = 0;
	int laddersCount = 0;
	int snakeGuardCount = 0;

	public Board() {
	   this(4);
   }

	public Board(int n) {
      
		//  r.start();   
		for (int i=0; i<n; i++) {
			pieces[i] = new HumanPiece("Piece" + (i+1),1);
		}
	}

	public Dice getDice() {
		return dice;
	}
   
	public void setDice(Dice dice) {
		this.dice = dice;
	}
	
	public ArrayList<Snake> getSS() {
		return this.ss;
	}
	
	public ArrayList<SnakeGuard> getSG() {
		return this.snakeGuards;
	}
	
	public void setSS(int index, Snake snake) {
		this.ss.add(index,snake);
	}
   
	public ArrayList<Ladder> getLS() {
		return this.ls;
	}
	
	public void setLS(int index, Ladder ladder) {
		this.ls.add(index, ladder);
	}
   
	public ArrayList<SnakeGuard> getSnakeGuards() {
		return this.snakeGuards;
	}
   
	public void setSnakeGuards(int index, SnakeGuard snakeGuard) {
		this.snakeGuards.add(index,snakeGuard);
	}
	
	public int getSnakesCount() {
		return this.snakesCount;
	}
	
	public void setSnakesCount(int snakesCount) {
		this.snakesCount = snakesCount;
	}
   
	public int getLaddersCount() {
		return this.laddersCount;
	}
	
	public void setLaddersCount(int laddersCount) {
		this.laddersCount = laddersCount;
	}
   
	public int getSnakeGuardCount() {
		return this.snakeGuardCount;
	}
	
	public void setSnakeGuardCount(int snakeGuardCount) {
		this.snakeGuardCount = snakeGuardCount;
	}
   
	public HumanPiece[] getPieces() {
		return this.pieces;
	}
   
	public void setPiece(HumanPiece piece, int pos) {
		piece.setLocation(pos);
	}
	
	public void clearLadders() {
		this.ls.clear();
		this.laddersCount = 0;
	}
	
	public void clearSnakeGuards() {
		this.snakeGuards.clear();
		this.snakeGuardCount = 0;
	}
	
	public void removeSnake(Snake snake) {
		this.ss.remove(snake);
		this.snakesCount--;
	}
	
	public void removePiece(int index) {
		this.pieces[index] = null;
	}
}