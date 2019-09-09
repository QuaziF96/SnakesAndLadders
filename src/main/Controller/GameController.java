package main.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import exceptions.LadderPlacementException;
import exceptions.SnakeGuardPlacementException;
import exceptions.SnakePlacementException;
import main.Model.Board;
import main.Model.Dice;
import main.Model.HumanPiece;
import main.Model.Ladder;
import main.Model.Player;
import main.Model.Snake;
import main.Model.SnakeGuard;

public class GameController {

	private static final int maxSnakeGuard = 3;
	
	ArrayList<Snake> snakes = new ArrayList<Snake>();    	
	ArrayList<Ladder> ladders = new ArrayList<Ladder>();  	
	ArrayList<SnakeGuard> traps = new ArrayList<SnakeGuard>();
	ArrayList<Integer> moves = new ArrayList<Integer>();
	
	int snakesCount = 0;				 
	int laddersCount = 0;	
	int snakeGuardCount = 0;
	private String admin;
	private String humanPlayer;
	private String snakePlayer;
	private Player[] players = new Player[2];
	private HumanPiece[] pieces = new HumanPiece[4];
	private HumanPiece piece;
	private Snake snake;
	private int snakeNumber;
	private int pieceNumber;
	static boolean stage1 = false;
	static boolean reached100 = false;
	static boolean finalStageHumanPlayerTurn = false;
	   
	   
	// Creating a Board, dice and a Scanner objects
	Board bd = new Board();
	BoardController boardController;
	GraphicsController graphicsController = new GraphicsController(bd);
	SnakeController snakeController;
	HumanController humanController;
	Dice dice = bd.getDice();
	Scanner scan = new Scanner(System.in);
	
	public GameController() {
		
	}
/*	      
	public void setup(Board bd) throws Exception {
		
		
		boardController = new BoardController(bd);
		//int choice = 0;
		//boardController.add(new Trap(25,3));
		//boardController.add(new Trap(95,3));
		trapsCount = 1;
	
		boardController.add(new Snake(92,34));
		boardController.add(new Snake(62,12));	  
		boardController.add(new Snake(41,3));  
		boardController.add(new Snake(99,10));  
	  
		snakesCount = 3;

		boardController.add(new Ladder(7,49));
		boardController.add(new Ladder(55,90));
		boardController.add(new Ladder(38,86));
		laddersCount = 3;
			  
	}	   
*/	     
	   // A method to print a message and to read an int value in the range specified
	   int getInt(String message, int from, int to)
	   {
		   String s;
		   int n = 0;
		   boolean invalid;
		   do {
			 invalid = false;
		     s = (String)JOptionPane.showInputDialog(
		      null,  message,  "Customized Dialog",
		          JOptionPane.PLAIN_MESSAGE);	
		      try {
		         n = Integer.parseInt(s);
		         if (n < from || n > to )
		    	     plainMessage("Re-enter: Input not in range " + from + " to " + to);
		      }
		      catch (NumberFormatException nfe)
		      {
		    	  plainMessage("Re-enter: Invalid number");
		    	  invalid = true;
		      }
		   } while ( invalid || n < from || n > to);
		   return n;
	   } 
	   
	   // A method to print a message and to read a String
	   String getString(String message)
	   {
		   
		   String s;
		   
		   do {
			   
			   s = (String)JOptionPane.showInputDialog(
					      null,  message,  "Enter Player Name",
					          JOptionPane.PLAIN_MESSAGE);
		   } while(s.trim().equals(""));
		   
		   /*
		   JOptionPane optionPane = new JOptionPane("Its me"
                   , JOptionPane.PLAIN_MESSAGE
                   , JOptionPane.DEFAULT_OPTION
                   , null, null, "Please ENTER your NAME here");
			optionPane.setWantsInput(true);             
			JDialog dialog = optionPane.createDialog(null, "Enter Player Name");
			dialog.setLocation(150, 475);
			dialog.setVisible(true);
			s = (String) optionPane.getInputValue();
			*/ 
		   return s;
	   }   

	   // A method to print a message
	   void plainMessage(String message)
	   {
	        JOptionPane.showMessageDialog(null,
			    message, "A prompt message",
			    JOptionPane.PLAIN_MESSAGE);
	   }
	   
	   public void initialStage() {
		   int count = 1,snakeHead,snakeTail,ladderTop,ladderBottom;
		   
		   while (count < 6) {
			   snakeHead = getInt(admin + ": Enter position for Snake " + count + "'s head",0,100);
			   snakeTail = getInt(admin + ": Enter position for Snake " + count + "'s tail",0,100);
			   try {
				   boardController.add(new Snake(snakeHead,snakeTail));
			   }
			   catch(SnakePlacementException e) {
				   plainMessage(e.getMessage());
				   continue;
			   }
			   count++;
		   }
		   count = 1;
		   while (count < 6) {
			   ladderTop = getInt(admin + ": Enter position for Ladder " + count + "'s top",0,100);
			   ladderBottom = getInt(admin + ": Enter position for Ladder " + count + "'s bottom",0,100);
			   try {
				   boardController.add(new Ladder(ladderBottom,ladderTop));
			   }
			   catch(LadderPlacementException e) {
				   plainMessage(e.getMessage());
				   continue;
			   }
			   count++;
		   }
		   stage1 = true;
	   }
	   
	   public void updateParalysedPieces() {
		   int turns;
		   boolean isParalysed;
		   for (HumanPiece piece: pieces) {
			   isParalysed = piece.getParalyse();
			   turns = piece.getParalysedTurns();
			   if (isParalysed) {
				   if (turns + 1 == 3) {
					   piece.setParalyse(false);
					   piece.setParalysedTurns(0);
				   }
				   else {
					   piece.setParalysedTurns(turns + 1);
				   }
			   }
		   }
	   }
	   
	   public boolean checkAllPieceParalysed() {
		   for (HumanPiece piece: pieces) {
			   if (!piece.getParalyse()) {
				   return false;
			   }
		   }
		   return true;
	   }
	   
	   public void snakeInfo() {
		   if (!stage1) {
			   return;
		   }
		   int count = 1;
		   for (Snake snake: snakes) {
			   graphicsController.addMessage("Snake " + count + "'s Head: "
					   					+ snake.getHead());
			   count++;
		   }
	   }
	   
	   public void updateGame() {
		   this.pieces = bd.getPieces();
		   this.snakes = bd.getSS();
		   this.ladders = bd.getLS();
		   this.snakesCount = bd.getSnakesCount();
		   this.laddersCount = bd.getLaddersCount();
		   this.snakeGuardCount = bd.getSnakeGuardCount();
	   }
	   
	   public void secondStageHumanPlayerTurn() {
		   int val, location, snakeGuard;
		   
		   if (checkAllPieceParalysed() && snakeGuardCount == maxSnakeGuard) {
			   updateParalysedPieces();
			   plainMessage("All pieces paralysed and no snake gaurds left. Humans' turn skipped.");
			   return;
		   }
		   while (true) {
			   pieceNumber = getInt(humanPlayer + ": Enter piece number to move or "
			   							+ "5 to place snake guard ",1,5);
			   if (pieceNumber == 5 && snakeGuardCount == maxSnakeGuard) {
				   plainMessage("Maximum number of snake guards already on the board");
				   continue;
			   }
			   else if(pieceNumber == 5) {
				   while (true) {
					   snakeGuard = getInt(humanPlayer + ": Enter location for Snake "
					   						+ "Guard",1,100);
					   try {
						   boardController.add(new SnakeGuard(snakeGuard));
						   plainMessage("Snake Guard placed! No moves this turn.");
						   return;
					   } 
					   catch (SnakeGuardPlacementException e) {
						   plainMessage(e.getMessage());
						   continue;
					   }
				   }
			   }
			   piece = pieces[pieceNumber - 1];
			   if (!piece.getParalyse()) {
				   break;
			   }
			   plainMessage("Piece is paralysed!");
		   }
		   location = piece.getLocation();
		   val = getInt(humanPlayer + ": Enter 0 to throw dice. Enter 1 - 6 for Testing.", 0, 6);
		   if ( val == 0)
		          val = dice.roll();
		      else
		    	  dice.set(val);
		   location += val;
		   if (location > 100) {
			   updateParalysedPieces();
			   plainMessage("Cannot move there!");
			   return;
		   }
		   if (location == 100) {
			   if (piece.getLaddersClimbed().size() >= 3) {
				   reached100 = true;
				   piece.activate();
				   graphicsController.setPiece(piece,location);
				   updateParalysedPieces();
				   return;
			   }
			   updateParalysedPieces();
			   plainMessage("This piece can't land on 100 as it did not climb 3 distinct ladders");
			   return;
		   }
		   graphicsController.setPiece(piece,location);
		   loop:
		   for (Snake snake: snakes) {
			   if (snake.getHead() == location) {
				   location = snake.getTail();
				   graphicsController.setPiece(piece,location);
				   piece.setParalyse(true);
				   piece.setParalysedTurns(0);
				   break loop;
			   }
		   }
		   newloop:
		   for (Ladder ladder: ladders) {
			   System.out.println(ladder.getBottom());
			   if (ladder.getBottom() == location) {
				   if (!piece.getLaddersClimbed().contains(ladder)) {
					   piece.setLaddersClimbed(ladder);
					   location = ladder.getTop();
					   graphicsController.setPiece(piece,location);
					   return;
				   }
				   break newloop;
			   }
		   }
		   updateParalysedPieces();
	   }
	   
	   public void secondStageSnakePlayerTurn() {
		   int direction, target;
		   snakeNumber = getInt(snakePlayer + ": Enter Snake number to move ",1,5);
		   snake = snakes.get(snakeNumber - 1);
		   while (true) {
			   direction = getInt(snakePlayer + ": Enter number for direction - 1 for up, "
							+ "2 for down, 3 for left, 4 for right ",1,4);
			   try {
				   target = snakeController.getTarget(snake, direction);
			   }
			   catch (Exception e) {
				   plainMessage(e.getMessage());
				   continue;
			   }
			   try {
				   snakeController.move(snake,target);
			   } 
			   catch (SnakePlacementException e) {
				   plainMessage(e.getMessage());
				   continue;
			   }
			   break;
			   
		   }
		   for (HumanPiece piece: pieces) {
			   if (snake.getHead() == piece.getLocation()) {
				   piece.setLocation(snake.getTail());
				   graphicsController.setPiece(piece,piece.getLocation());
				   piece.setParalyse(true);
				   piece.setParalysedTurns(0);
			   }
		   }
	   }
	   
	   public boolean secondStage() {
		   
		   int humansTurn = 0;
		   plainMessage("Board has been set! Second stage has commenced.");
		   while (humansTurn != 50 && reached100 == false) {
			   for (Player player: players) {
				   updateGame();
				   if (player.getType().equals("HUMANCONTROLLER")) {
					   secondStageHumanPlayerTurn();
				   }
				   else {
					   if (reached100 != false) {
						   break;
					   }
					   snakeInfo();
					   secondStageSnakePlayerTurn();
					   graphicsController.clearMessages(5);
				   }
			   }
			   humansTurn++;
		   }
		   if (humansTurn == 50 && reached100 == false) {
			   plainMessage("50 turns finished without reaching 100, Snakes win!");
			   return false;
		   }
		   graphicsController.addMessage("100 reached! Final stage has commenced.");
		   return true;
	   }
	   
	   public void finalStageHumanPlayerTurn() {
		   int move;
		   while (true) {
			   pieceNumber = getInt(humanPlayer + ": Enter piece number to move",1,4);
			   piece = pieces[pieceNumber - 1];
			   if (piece.getisActivated()) {
				   plainMessage("This piece has reached 100 and cannot move!");
				   continue;
			   }
			   moves = humanController.finalStageMoveOptions(piece);
			   System.out.println(moves.toString());
			   graphicsController.setMoves(moves);
			   graphicsController.addMessage("Moves: " + moves.toString());
			   move = getInt(humanPlayer + ": Enter legible location to move to",1,100);
			   if (!moves.contains(move)) {
				   graphicsController.clearMoves();
				   if (("Moves: "+moves.toString()).length() > 20) {
					   graphicsController.clearMessages(2);
				   }
				   else {
					   graphicsController.clearMessages(1);
				   }
				   plainMessage("Can't move there, pick from greyed boxes");
				   continue;
			   }
			   graphicsController.setPiece(piece, move);
			   break;
		   }
	   	   loop:
		   for (Snake snake: snakes) {
			   if (snake.getTail() == move) {
				   bd.removeSnake(snake);
				   if (bd.getSS().size() != 0) {
					   plainMessage("Snake destroyed! Only " + bd.getSS().size() 
							   			+ " more to go!");
				   }
				   break loop;
			   }
			   else if (snake.getHead() == move) {
				   bd.removePiece(pieceNumber-1);
				   return;
			   }
		   }
		   
		   graphicsController.clearMoves();
		   
	   }
	   
	   public void finalStageSnakePlayerTurn() {
		   int direction, target;
		   
		   while (true) {
			   snakeNumber = getInt(snakePlayer + ": Enter Snake number to move ",1,snakes.size());
			   snake = snakes.get(snakeNumber - 1);
			   direction = getInt(snakePlayer + ": Enter number for direction - 1 for up, "
							+ "2 for down, 3 for left, 4 for right ",1,4);
			   try {
				   target = snakeController.getTarget(snake, direction);
			   }
			   catch (Exception e) {
				   plainMessage(e.getMessage());
				   continue;
			   }
			   try {
				   snakeController.move(snake,target);
			   } 
			   catch (SnakePlacementException e) {
				   plainMessage(e.getMessage());
				   continue;
			   }
			   break;
		   }
		   for (HumanPiece piece: pieces) {
			   if (snake.getTail() == piece.getLocation()) {
				   bd.removeSnake(snake);
				   plainMessage("Snake destroyed! Poor move!");
			   }	
			   else if (snake.getHead() == piece.getLocation()) {
				   bd.removePiece(pieceNumber-1);
				   plainMessage("Human piece destroyed!");
				   return;
			   }
		   }
	   }
	   
	   public void finalStage() {
		   bd.clearLadders();
		   bd.clearSnakeGuards();
		   
		   int humansTurn = 0;
		   while (humansTurn != 20 && snakes.size() != 0 && 
				   !Arrays.stream(pieces).anyMatch(x -> x == null)) {
			   for (Player player: players) {
				   updateGame();
				   if (player.getType().equals("HUMANCONTROLLER")) {
					   finalStageHumanPlayerTurn();
				   }
				   else {
					   updateGame();
					   snakeInfo();
					   finalStageSnakePlayerTurn();
					   graphicsController.clearMessages(5);
				   }
				   updateGame();
				   if (Arrays.stream(pieces).anyMatch(x -> x == null)) {
					   break;
				   }
			   }
			   humansTurn++;
		   }
		   if (humansTurn == 20 && snakes.size() != 0) {
			   plainMessage("20 turns finished without killing all snakes, Snakes win!");
			   return;
		   }
		   else if (Arrays.stream(pieces).anyMatch(x -> x == null)) {
			   plainMessage("Humans lost a piece, Snakes win!");
			   return;
		   }
		   plainMessage("The snakes have been defeated! Humans win!");
	   }
	   
	   // The main method implementing the game logic for Part A
	   // For Part A you may use the hard coded values in the setup() method 
	   // You will need to change the code for Part B
	   // For user interaction use the methods getString, getInt and plainMessage
	   // For display/erase on the board use bd.addMessage(), bd.clearMessages()

	public void game() { 
		
		boardController = new BoardController(bd);
		snakeController = new SnakeController(bd);
		humanController = new HumanController(bd);
		graphicsController.clearMessages();  // clears the display board  
		
		admin = getString("Admin name : ");
		humanPlayer = getString("Human player name : ");
		snakePlayer = getString("Snake Player name : ");
		players[0] = new Player(humanPlayer,"Human");
		players[1] = new Player(admin,"Snake");
		
		graphicsController.addMessage("Current Players Are - ");
		graphicsController.addMessage("Admin : "); 
		graphicsController.addMessage(admin);
		graphicsController.addMessage("Human Player : ");
		graphicsController.addMessage(humanPlayer);
		graphicsController.addMessage("Snake Player : ");
		graphicsController.addMessage(snakePlayer);
		graphicsController.addMessage("________________________");
		      
		initialStage();
		/*
		if (secondStage()) {
			finalStage();
		}
		*/
		updateGame();
		graphicsController.setPiece(pieces[0],100);
		finalStage();
		
	   }
	
	public static boolean getStage1() {
		return stage1;
	}
	public static boolean getfinalStageHumanPlayerTurn() {
		return finalStageHumanPlayerTurn;
	}
}
