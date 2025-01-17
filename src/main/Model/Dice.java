package main.Model;
import java.awt.Color;
import java.awt.Graphics;

import main.Controller.GraphicsController;
public class Dice
{
   int n = -1;
   GraphicsController sl;
   
   public void draw(Graphics g)
   {
       g.setColor(Color.BLACK);
       g.fill3DRect(500,400,45,45,false);
       g.setColor(Color.WHITE);
       if ( n == 1)
       {
          g.fillOval(520,420,5,5);
       }          
       else if ( n == 2 )
       {
          g.fillOval(510,420,5,5);
          g.fillOval(530,420,5,5);
       }          
       else if ( n == 3 )
       {
          g.fillOval(520,410,5,5);
          g.fillOval(510,430,5,5);
          g.fillOval(530,430,5,5);
       }          
       else if ( n == 4 )
       {
          g.fillOval(510,410,5,5);
          g.fillOval(530,410,5,5);
          g.fillOval(510,430,5,5);
          g.fillOval(530,430,5,5);
       }          
       else if ( n == 5 )
       {
          g.fillOval(510,410,5,5);
          g.fillOval(530,410,5,5);
          g.fillOval(520,420,5,5);
          g.fillOval(510,430,5,5);
          g.fillOval(530,430,5,5);
       }          
       else if ( n == 6 )
       {
          g.fillOval(510,410,5,5);
          g.fillOval(530,410,5,5);
          g.fillOval(510,420,5,5);
          g.fillOval(530,420,5,5);
          g.fillOval(510,430,5,5);
          g.fillOval(530,430,5,5);
       }          
   }

   public Dice(GraphicsController sl)
   {
      this.sl = sl;
   }

   public int roll()
   {
      int n = 0;
      for (int i=1; i<=20; i++) {
         n = (int) (Math.random()*6) + 1;
         set(n);
         try {
           Thread.sleep(100);
         }
         catch(Exception e) {}
      }
      return n;
   }

   public int set(int val)
   {
      if ( val >= 1 && val <= 6)
         n = val;  
      else if (val < 1)
    	 n = 1;
      else n = 6;
      sl.repaint();
      return n;
   }
   static int getThrow() { return (int) (Math.random()*6) + 1;    }         
}