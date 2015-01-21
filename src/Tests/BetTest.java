package Tests;

import static org.junit.Assert.*;
import model.Team;
import org.junit.Test;
import AI.Bet;

public class BetTest {
	
	@Test
	public void testConstructor(){
		Bet bet1 = new Bet(125,3,36,35);
		Bet bet2 = new Bet(125,3,36,35);
		assertTrue(bet1.equals(bet2));
	}
	
	@Test
	public void testConstructorFalse(){
		Bet bet1 = new Bet(151,616,16161,6161);
		Bet bet2 = new Bet(1515616,616,161,61);
		assertFalse(bet1.equals(bet2));
	}

	@Test
	public void testConstructorNsetters() 
	{
		Team one = new Team(1, "one");
		Team two = new Team(2, "two");
		Bet b = new Bet(1337, 1.3, two.getId(), 1);
		
		b.setMoney_bet(100000);
		b.setRate(1.5);
		b.setS_won(one.getId());
		b.setMatchid(2);
		
		Bet c = new Bet(100000, 1.5, one.getId(), 2);
		
		assertEquals(b,c);		
	}

	@Test
	public void testGetMoney_bet(){
		Team two = new Team(2, "two");
		Bet b = new Bet(1337, 1.3, two.getId(), 1);
		assertEquals(b.getMoney_bet(), 1337);
	}
	
	@Test
	public void testGetMoney_betFalse(){
		Team two = new Team(62, "two");
		Bet b = new Bet(1337, 1.3, two.getId(), 1);
		assertNotEquals(b.getMoney_bet(), 1335167);
	}
	
	@Test
	public void testGetRate(){
		Team two = new Team(2, "two");
		Bet b = new Bet(1337, 1.3, two.getId(), 1);
		assertEquals(b.getRate(),1.3,0.0);
	}
	
	@Test
	public void testGetRateFalse(){
		Team two = new Team(32, "twhhho");
		Bet b = new Bet(1337, 1.536, two.getId(), 1);
		assertNotEquals(b.getRate(),1.6,0.0);
	}
	
	@Test
	public void testGetS_Won(){
		Team two = new Team(2, "two");
		Bet b = new Bet(1337, 1.3, two.getId(), 1);
		assertEquals(b.getS_won(), two.getId());
	}
	
	@Test
	public void testGetS_WonFalse(){
		Team two = new Team(2, "two");
		Team twoo = new Team(23, "two");
		Bet b = new Bet(1337, 1.3, two.getId(), 1);
		assertNotEquals(b.getS_won(), twoo.getId());
	}
	
	@Test
	public void testGetMatchid()
	{
		Team two = new Team(2, "two");
		Bet b = new Bet(1337, 1.3, two.getId(), 1);
		assertEquals(b.getMatchid(), 1);
	}
	
	@Test
	public void testGetMatchidFalse()
	{
		Team two = new Team(15, "twfffo");
		Bet b = new Bet(133237, 1.63, two.getId(), 6);
		assertNotEquals(b.getMatchid(), 2);
	}
	
	@Test
	public void testString()
	{
		Team two = new Team(2, "two");
		Bet b = new Bet(1337, 1.3, two.getId(), 1);
		System.out.println(b.toString());
		assertEquals(b.toString(), "Bet [money_bet=1337, rate=1.3, id_won=2, matchid=1]");
	}
	
	
	@Test
	public void testEquals1()
	{
		Team test1 = new Team(1, "test");
		Bet test3 = new Bet(420, 1.337, test1.getId(), 1);
		Bet test5 = new Bet(420, 1.337, test1.getId(), 1);
		assertTrue(test3.equals(test5));
	}
	
	@Test
	public void testEquals2()
	{
		Team test1 = new Team(1, "test");
		Bet test3 = new Bet(420, 1.337, test1.getId(), 1);
		Bet test4 = null;
		assertFalse(test3.equals(test4));	
	}
	
	@Test
	public void testEquals3()
	{
		Team test1 = new Team(1, "test");
		Bet test3 = new Bet(420, 1.337, test1.getId(), 1);
		assertFalse(test3.equals(test1));
	}
	
	@Test
	public void testEquals4()
	{
		Team test1 = new Team(1, "test");
		Bet test3 = new Bet(420, 1.337, test1.getId(), 1);
		assertTrue(test3.equals(test3));
	}
	
	@Test
	public void testEquals5()
	{
		Team test1 = new Team(1, "test");
		Bet test3 = new Bet(420, 1.337, test1.getId(), 1);
		Bet test7 = new Bet(420, 1.338, test1.getId(), 1);
		assertFalse(test3.equals(test7));	
	}
	
	@Test
	public void testEquals6()
	{
		Team test1 = new Team(1, "test");
		Bet test3 = new Bet(420, 1.337, test1.getId(), 1);
		Bet test8 = new Bet(421, 1.337, test1.getId(), 1);
		assertFalse(test3.equals(test8));	
	}
	
	@Test
	public void testEquals7()
	{
		Team test1 = new Team(1, "test");
		Bet test3 = new Bet(420, 1.337, test1.getId(), 1);
		Bet test10 = new Bet(420, 1.337, test1.getId(), 2);
		assertFalse(test3.equals(test10));	
	}
	
	@Test
	public void testEquals8()
	{
		Team test1 = new Team(1, "test");
		Bet test3 = new Bet(420, 1.337, test1.getId(), 1);
		Bet test11 = new Bet(420, 1.337, test1.getId(), 0);
		assertFalse(test3.equals(test11));
	}
	
	@Test
	public void testEquals9()
	{
		Team test1 = new Team(1, "test");
		Bet test11 = new Bet(420, 1.337, test1.getId(), 0);
		Bet test12 = new Bet(0, 0.1, 1, 0);
		assertFalse(test11.equals(test12));		
	}
}
