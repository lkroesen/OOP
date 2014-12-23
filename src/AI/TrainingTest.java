package AI;

import static org.junit.Assert.*;
import model.Player;
import AI.Training;

import org.junit.Test;

public class TrainingTest {

	@Test
	public void testRest() 
	{
		for (int c = 0; c < 10000; c++){
		// Player( id, firstname, surname, number, type, position, offesnive, defensive, stamina, price )
		Player test = new Player(1, "Mr.", "Test", (byte)1, 1, 1, (byte)50, (byte)50, (byte)50, 500000);
		
		test = Training.rest(test);
		System.out.println(test);
		assertTrue(test.getStaminaScore() > 50);
		}
	}
	
	@Test
	public void testNormalTraining()
	{
		for (int c = 0; c < 10000; c++){
		// Player( id, firstname, surname, number, type, position, offesnive, defensive, stamina, price )
		Player test = new Player(1, "Mr.", "Test", (byte)1, 1, 1, (byte)50, (byte)50, (byte)50, 500000);
		
		test = Training.RegularTraining(test);
		System.out.println(test);
		assertTrue(test.getStaminaScore() < 55);
		}
	}
	
	@Test
	public void testHeavyTraining()
	{
		for (int c = 0; c < 10000; c++){
		// Player( id, firstname, surname, number, type, position, offesnive, defensive, stamina, price )
		Player test = new Player(1, "Mr.", "Test", (byte)1, 1, 1, (byte)50, (byte)50, (byte)50, 500000);
		
		test = Training.HeavyTraining(test);
		System.out.println(test);
		assertTrue(test.getStaminaScore() < 55);
		}
	}
	
	@Test
	public void testHeavyTrainingTwo()
	{
		for (int c = 0; c < 10000; c++){
		// Player( id, firstname, surname, number, type, position, offesnive, defensive, stamina, price )
		Player test = new Player(1, "Mr.", "Test", (byte)1, 3, 3, (byte)50, (byte)50, (byte)1, 500000);
		
		test = Training.HeavyTraining(test);
		System.out.println(test);
		assertTrue(test.getStaminaScore() < 55);
		}
	}
	
	@Test
	public void testNormalTrainingTwo()
	{
		for (int c = 0; c < 10000; c++){
		// Player( id, firstname, surname, number, type, position, offesnive, defensive, stamina, price )
		Player test = new Player(1, "Mr.", "Test", (byte)1, 3, 3, (byte)50, (byte)50, (byte)1, 500000);
		
		test = Training.RegularTraining(test);
		System.out.println(test);
		assertTrue(test.getStaminaScore() < 55);
		}
	}
	
	@Test
	public void testCheck()
	{
		Player test = new Player(1, "Mr.", "Test", (byte)1, 1, 1, (byte)101, (byte)101, (byte)101, 500000);
		test = Training.check(test);
		assertTrue(test.getOffensiveScore() == 100 && test.getDefensiveScore() == 100 && test.getStaminaScore() == 100);
	}

}
