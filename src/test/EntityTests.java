package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.SnakePlacementException;
import main.Model.HumanPiece;
import main.Model.Ladder;
import main.Model.Snake;
import main.Model.SnakeGuard;

public class EntityTests {

	@Test
	// Testing HumanPiece.activate()
	public void test1() {
		
		// What does activate do?

	}

	@Test
	// Testing HumanPiece.move()
	public void test2() {

		HumanPiece h = new HumanPiece("1",1);
		h.move(3);
		assertEquals(h.getLocation(), 3);
	}

	@Test
	// Testing Snake.activate()
	public void test3() {
		
		Snake s = new Snake(20,10);
		HumanPiece h2 = new HumanPiece("1",20);
		s.activate(h2);
		assertEquals(h2.getLocation(), s.getTail());
	}

	@Test
	// Testing Snake.move()
	public void test4() {
		
		Snake s = new Snake(20,10); 
		s.move(21);
		assertEquals(s.getTail(), 11);

	}
	
	@Test (expected = SnakePlacementException.class) 
	//Testing snake.move() when tail would go below zero
	public void test5() {
		
		Snake s = new Snake(5,1); 
		s.move(3);

	}
	
	
	// Testing Ladder.activate()
	public void test6() {
		
		Ladder l = new Ladder(10,20);
		HumanPiece h3 = new HumanPiece("1",10);
		l.activate(h3);
		assertEquals(h3.getLocation(), l.getTop());

	}


	@Test
	// Testing Trap.activate()
	public void test7() {
		
	//What does activate do?

	}

	// Testing Trap.move()
	public void test9() {
		
		SnakeGuard g = new SnakeGuard(50);
		g.move(60);
		assertEquals(g.getLocation(), 60);
	}

}
