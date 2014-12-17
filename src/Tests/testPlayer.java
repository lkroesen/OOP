package Tests;

import static org.junit.Assert.*;
import model.Player;

import org.junit.Test;

public class testPlayer {

	@Test
	public void testConstructor() {
		Player p1 = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		assertTrue(p1.equals(p1));
	}

}
