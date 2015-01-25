package Tests;

import static org.junit.Assert.*;
import model.Player;
import AI.Training;

import org.junit.Test;

public class TrainingTest {

	@Test
	public void testRest() 
	{
		// Player( id, firstname, surname, number, type, position, offesnive, defensive, stamina, price )
		Player test = new Player(1, "Mr.", "Test", (byte)1, 1, 1, (byte)50, (byte)50, (byte)50, 500000);
		for (int c = 0; c < 10000; c++){
		test = Training.rest(test);
		assertNotEquals(test.getStaminaScore(), 50);
		}
	}
	
	@Test
	public void testNormalTraining()
	{
		// Player( id, firstname, surname, number, type, position, offesnive, defensive, stamina, price )
		Player test = new Player(1, "Mr.", "Test", (byte)1, 1, 1, (byte)50, (byte)50, (byte)50, 500000);
		for (int c = 0; c < 10000; c++){
		test = Training.RegularTraining(test);
		assertNotEquals(test.getStaminaScore(), 50);
		}
	}
	
	@Test
	public void testHeavyTraining()
	{
		// Player( id, firstname, surname, number, type, position, offesnive, defensive, stamina, price )
		Player test = new Player(1, "Mr.", "Test", (byte)1, 1, 1, (byte)50, (byte)50, (byte)50, 500000);
		for (int c = 0; c < 10000; c++){
		test = Training.HeavyTraining(test);
		assertNotEquals(test.getStaminaScore(), 50);
		}
	}
	
	@Test
	public void testHeavyTrainingTwo()
	{
		// Player( id, firstname, surname, number, type, position, offesnive, defensive, stamina, price )
		Player test = new Player(1, "Mr.", "Test", (byte)1, 3, 3, (byte)50, (byte)50, (byte)1, 500000);
		for (int c = 0; c < 10000; c++){
		test = Training.HeavyTraining(test);
		assertNotEquals(test.getStaminaScore(), 50);
		}
	}
	
	@Test
	public void testNormalTrainingTwo()
	{
		// Player( id, firstname, surname, number, type, position, offesnive, defensive, stamina, price )
		Player test = new Player(1, "Mr.", "Test", (byte)1, 3, 3, (byte)50, (byte)50, (byte)1, 500000);
		
		for (int c = 0; c < 10000; c++){
		test = Training.RegularTraining(test);
		assertNotEquals(test.getStaminaScore(), 50);
		}
	}
	
	@Test
	public void testCheck()
	{
		Player test = new Player(1, "Mr.", "Test", (byte)1, 1, 1, (byte)101, (byte)101, (byte)101, 500000);
		Training.check(test);
		assertEquals(test.getOffensiveScore(), 100);
	}
	
	@Test
	public void testCheck2()
	{
		Player test = new Player(1, "Mr.", "Test", (byte)1, 1, 1, (byte)99, (byte)101, (byte)101, 500000);
		Training.check(test);
		assertEquals(test.getDefensiveScore(), 100);
	}
	
	@Test
	public void testCheck3()
	{
		Player test = new Player(1, "Mr.", "Test", (byte)1, 1, 1, (byte)99, (byte)99, (byte)101, 500000);
		Training.check(test);
		assertEquals(test.getStaminaScore(), 100);
	}
}
