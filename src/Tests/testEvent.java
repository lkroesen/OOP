package Tests;

import static org.junit.Assert.*;
import model.Event;
import model.Team;

import org.junit.Test;

public class testEvent
{
	//Test constructor
	@Test
	public void testConstructor() 
	{
		Event e1 = new Event(1,2,3,4);
		assertEquals(e1,e1);
	}
	
	@Test
	public void testConstructorFalse()
	{
		Event e1 = new Event(1,2,3,4);
		Event e2 = new Event(1,5,3,4);
		assertNotEquals(e1,e2);
	}
	
	//Test equals
	@Test
	public void testEquals()
	{
		Event e1 = new Event(1,42,430,102);
		Event e3 = new Event(1,42,430,102);
		assertTrue(e1.equals(e3));
	}
	
	@Test
	public void testEqualsFalse()
	{
		Event e1 = new Event(1,42,430,102);
		Event e2 = new Event(42,4,420,142);
		assertFalse(e1.equals(e2));
	}
	
	//Test getters
	@Test
	public void testGetPlayer()
	{
		Event e1 = new Event(1,2,3,4);
		assertEquals(e1.getPlayer(),1);
	}
	
	@Test
	public void testGetPlayerFalse()
	{
		Event e1 = new Event(1,2,3,4);
		assertNotEquals(e1.getPlayer(),46847);
	}
	
	@Test
	public void testGetType()
	{
		Event e1 = new Event(2,3,4,5);
		assertEquals(e1.getType(),3);
	}
	
	@Test
	public void testGetTypeFalse()
	{
		Event e1 = new Event(2,3,4,5);
		assertNotEquals(e1.getType(),5);
	}
	
	@Test
	public void testGetMinute()
	{
		Event e1 = new Event(5,43,62,23);
		assertEquals(e1.getMinute(),62);
	}
	
	@Test
	public void testGetMinuteFalse()
	{
		Event e1 = new Event(5,43,62,23);
		assertNotEquals(e1.getMinute(),62469);
	}
	
	@Test
	public void testGetOutfor()
	{
		Event e1 = new Event(7,93,242,123);
		assertEquals(e1.getOutfor(),123);
	}
	
	@Test
	public void testGetOutforFalse()
	{
		Event e1 = new Event(7,93,242,123);
		assertNotEquals(e1.getOutfor(),129);
	}
	
	//Test toString
	@Test
	public void testToString()
	{
		Event e1 = new Event(5,6,7,9);
		assertEquals(e1.toString(),"Event [player=5, type=6, minute=7, outfor=9]");
	}
	
	@Test
	public void testToStringFalse()
	{
		Event e1 = new Event(564,546,138,945);
		assertNotEquals(e1.toString(),"");
	}
	
	@Test
	public void testEquals2()
	{
		Event e = new Event(1,2,3,4);
		Event e1 = new Event(0,2,3,4);
		Event e2 = new Event(1,3,3,4);
		Event e3 = new Event(1,2,4,4);
		Event e4 = new Event(1,2,3,5);
		
		assertNotEquals(e,e1);
		assertNotEquals(e,e2);
		assertNotEquals(e,e3);
		assertNotEquals(e,e4);
		
	}
	
	@Test
	public void testEquals3()
	{
		Event e = new Event(1,2,3,4);
		Team t = new Team(5,"hi");
		
		assertNotEquals(e,t);
	}
}
