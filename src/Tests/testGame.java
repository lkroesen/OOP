package Tests;

import static org.junit.Assert.*;
import model.Game;

import org.junit.Test;

public class testGame {

	@Test
	public void testConstructor() {
		Game g1 = new Game(20,"Leon",5,1);
		assertEquals(g1,g1);
	}
	
	@Test
	public void testConstructorFalse() {
		Game g1 = new Game(10,"ABC",20,6);
		Game g2 = new Game(10,"ABC",21,6);
		assertNotEquals(g1,g2);
	}
	
	@Test
	public void testEquals() {
		Game g1 = new Game(30,"Karel",61,3);
		Game g4 = new Game(30,"Karel",61,3);
		assertTrue(g1.equals(g4));
	}
	
	@Test
	public void testEqualsFalse(){
		Game g1 = new Game(20,"Peter",2,15);
		Game g2 = new Game(10,"Sven",3,12);
		assertFalse(g1.equals(g2));
	}

}
