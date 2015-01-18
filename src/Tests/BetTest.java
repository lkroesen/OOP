package Tests;

import static org.junit.Assert.*;
import model.Team;
import org.junit.Test;
import AI.Bet;

public class BetTest {

	@Test
	public void testConstructorNsetters() 
	{
		Team one = new Team(1, "one");
		Team two = new Team(2, "two");
		Bet b = new Bet(1337, 1.3, two.getId(), "2-1");
		
		b.setMoney_bet(100000);
		b.setRate(1.5);
		b.setS_won(one.getId());
		b.setResult("1-2");
		
		Bet c = new Bet(100000, 1.5, one.getId(), "1-2");
		
		assertEquals(b,c);		
	}

	
	@Test
	public void testGetters()
	{
		Team two = new Team(2, "two");
		Bet b = new Bet(1337, 1.3, two.getId(), "2-1");
		
		assertEquals(b.getMoney_bet(), 1337);
		assertEquals(b.getRate(),1.3,0.0);
		assertEquals(b.getS_won(), two.getId());
		assertEquals(b.getResult(), "2-1");
	}
	
	@Test
	public void testString()
	{

		Team two = new Team(2, "two");
		Bet b = new Bet(1337, 1.3, two.getId(), "2-1");
		System.out.println(b.toString());
		assertEquals(b.toString(), "Bet [money_bet=1337, rate=1.3, id_won=2, result=2-1]");
	}
	
	@Test
	public void testEquals()
	{
		Team test1 = new Team(1, "test");

		Bet test3 = new Bet(420, 1.337, test1.getId(), "a");
		Bet test4 = null;
		Bet test5 = new Bet(420, 1.337, test1.getId(), "a");
		Bet test7 = new Bet(420, 1.338, test1.getId(), "a");
		Bet test8 = new Bet(421, 1.337, test1.getId(), "a");
		Bet test10 = new Bet(420, 1.337, test1.getId(), "c");
		Bet test11 = new Bet(420, 1.337, test1.getId(), null);
		Bet test12 = new Bet(0, 0.1, 1, null);
		
		
		assertTrue(test3.equals(test5));
		assertFalse(test3.equals(test4));
		assertFalse(test3.equals(test1));
		assertTrue(test3.equals(test3));
		assertFalse(test3.equals(test7));
		assertFalse(test3.equals(test8));
		assertFalse(test3.equals(test10));
		assertFalse(test3.equals(test11));
		assertFalse(test11.equals(test12));		
	}
}
