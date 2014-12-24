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
		Bet b = new Bet(1337, 1.3, two, "2-1");
		
		b.setMoney_bet(100000);
		b.setRate(1.5);
		b.setS_won(one);
		b.setResult("1-2");
		
		Bet c = new Bet(100000, 1.5, one, "1-2");
		
		assertEquals(b,c);		
	}

	
	@Test
	public void testGetters()
	{
		Team two = new Team(2, "two");
		Bet b = new Bet(1337, 1.3, two, "2-1");
		
		assertEquals(b.getMoney_bet(), 1337);
		assertTrue(b.getRate() == 1.3);
		assertFalse(b.getRate() == 1.5);
		assertEquals(b.getS_won(), two);
		assertEquals(b.getResult(), "2-1");
	}
	
	@Test
	public void testString()
	{
		Team two = new Team(2, "two");
		Bet b = new Bet(1337, 1.3, two, "2-1");
		assertEquals(b.toString(), "Bet [money_bet=1337, rate=1.3, s_won=Team [id=2, name=two, players=[]], result=2-1]");
	}
	
	@Test
	public void testEquals()
	{
		Team test1 = new Team(1, "test");
		Team test2 = null;

		Bet test3 = new Bet(420, 1.337, test1, "a");
		Bet test4 = null;
		Bet test5 = new Bet(420, 1.337, test1, "a");
		Bet test6 = new Bet(1337, 4.20, test2, "b");
		Bet test7 = new Bet(420, 1.338, test1, "a");
		Bet test8 = new Bet(421, 1.337, test1, "a");
		Bet test9 = new Bet(420, 1.337, test2, "a");
		Bet test10 = new Bet(420, 1.337, test1, "c");
		Bet test11 = new Bet(420, 1.337, test1, null);
		Bet test12 = new Bet(0, 0.1, null, null);
		
		
		assertTrue(test3.equals(test5));
		assertFalse(test3.equals(test4));
		assertFalse(test3.equals(test2));
		assertFalse(test3.equals(test1));
		assertTrue(test3.equals(test3));
		assertFalse(test3.equals(test6));
		assertFalse(test3.equals(test7));
		assertFalse(test3.equals(test8));
		assertFalse(test3.equals(test9));
		assertFalse(test3.equals(test10));
		assertFalse(test3.equals(test11));
		assertFalse(test11.equals(test12));		
	}
}
