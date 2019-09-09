package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("BeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("AfterClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Before");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("After");
	}

	@Test
	public void test1() {
		// assertEquals(b.snakes[s1.getHead()], s1);
		System.out.println("Test1");
	}

	@Test
	public void test2() {
		System.out.println("Test2");
		
	}

	@Test // (expected = SnakePlacementException.class)
	public void test3() {
		System.out.println("Test3");
		
	}

}
