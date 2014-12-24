package Tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import model.Budget;
import model.Team;
import org.junit.Test;

public class BudgetTest {

	@Test
	public void testConstructor() 
	{
		Team test = new Team(1, "Test");
		ArrayList<Team> t = new ArrayList<Team>();
		t.add(test);
		Budget money = new Budget(t);
		
		assertEquals(money.getTeam(), t);
	}

	@Test
	public void testBudget()
	{
		Team test = new Team(1, "Test");
		ArrayList<Team> t = new ArrayList<Team>();
		t.add(test);
		Budget money = new Budget(t);
		
		money.setBudgetByTeam(test, (long) 100000);
		assertEquals(money.getBudgetByTeam(test), (long) 100000);
	}
	
	@Test
	public void testBudget2()
	{
		Team test = new Team(1, "Test");
		ArrayList<Team> t = new ArrayList<Team>();
		Budget money = new Budget(t);
		
		money.setBudgetByTeam(test, (long) 100000);
		assertEquals(money.getBudgetByTeam(test), -1);
	}
	
	@Test
	public void testToSTring()
	{
		Team test = new Team(1, "Test");
		ArrayList<Team> t = new ArrayList<Team>();
		t.add(test);
		Budget money = new Budget(t);
		
		money.setBudgetByTeam(test, (long) 100000);
		System.out.println(money.toString());
		assertEquals(money.toString(), "Budget [Team: Team [id=1, name=Test, players=[]] Budget: 100000]");
	}
	
	@Test
	public void testEquals()
	{
		Team test1 = new Team(1, "test");
		Team test2 = null;
		
		ArrayList<Team> t = new ArrayList<Team>();
		ArrayList<Team> t2 = new ArrayList<Team>();
		
		t.add(test1);
		t.add(test2);
		t2.add(test2);
		
		
		Budget test3 = new Budget(t);
		Budget test4 = null;
		Budget test5 = new Budget(t);
		Budget test6 = new Budget(t2);
		
		assertTrue(test3.equals(test5));
		assertFalse(test3.equals(test4));
		assertFalse(test3.equals(test2));
		assertFalse(test3.equals(test1));
		assertTrue(test3.equals(test3));
		assertFalse(test3.equals(test6));
	}
}
