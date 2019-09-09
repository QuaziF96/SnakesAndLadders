package main.View;

import main.Controller.GameController;
import main.Model.Snake;

public class GameInitiator {

   
   // The very first method to be called
   // This method constructs a GameController object and calls its control method 
   public static void main(String args[])
   {
       GameController game = new GameController();

       game.game();
	   
   }


}