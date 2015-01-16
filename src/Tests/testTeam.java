package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Player;
import model.Team;

import org.junit.Test;

public class testTeam {

	@Test
	public void testConstructor(){
		Team t1 = new Team(1,"Ajax");
		assertTrue(t1.equals(t1));
	}
	
	@Test
	public void testAddPlayer(){
		Team t1 = new Team(1,"Ajax");
		ArrayList<Player> pl = new ArrayList<Player>();
		pl.add(new Player(1, "Ha", "Ha", (byte) 1, 0, 1, (byte) 50, (byte) 50, (byte) 50, 5));
		t1.addPlayer(new Player(1, "Ha", "Ha", (byte) 1, 0, 1, (byte) 50, (byte) 50, (byte) 50, 5));
		assertTrue(t1.getPlayers().equals(pl));
	}
	
	@Test
	public void testSetPlayer()
	{
		Team t1 = new Team(1,"Ajax");
		ArrayList<Player> pl = new ArrayList<Player>();
		ArrayList<Player> pls = new ArrayList<Player>();
		pl.add(new Player(1, "Ha", "Ha", (byte) 1, 0, 1, (byte) 50, (byte) 50, (byte) 50, 5));
		t1.setPlayers(pl);
		t1.delPlayer(new Player(1, "Ha", "Ha", (byte) 1, 0, 1, (byte) 50, (byte) 50, (byte) 50, 5));
		assertTrue(t1.getPlayers().equals(pls));
	}
	
	@Test
	public void testConstructor2(){
		ArrayList<Player> pl = new ArrayList<Player>();
		pl.add(new Player(1, "Ha", "Ha", (byte) 1, 0, 1, (byte) 50, (byte) 50, (byte) 50, 5));
		Team t1 = new Team(1,"Ajax", pl);
		assertEquals(t1.getPlayers(), pl);
	}
	
	@Test
	public void testConstructor3(){

		ArrayList<Player> pl = new ArrayList<Player>();
		pl.add(new Player(1, "Ha", "Ha", (byte) 1, 0, 1, (byte) 50, (byte) 50, (byte) 50, 5));
		Team t1 = new Team(1,"Ajax", pl, (long)1000);
		assertEquals(t1.getBudget(), (long)1000);
	}
	
	@Test
	public void testConstructor4(){
		Team t1 = new Team(1,"Ajax", (long)1337, "herp");
		t1.setStadium("derp");
		assertEquals(t1.getStadium(), "derp");
	}
	
	
	@Test
	public void testConstructorFalse(){
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		assertFalse(t1.equals(t2));
	}
	
	@Test
	public void testGetId(){
		Team t1 = new Team(1,"Ajax");
		assertEquals(t1.getId(),1);
	}
	
	@Test
	public void testGetIdFalse(){
		Team t1 = new Team(1,"Ajax");
		assertNotEquals(t1.getId(),3);
	}
	
	@Test
	public void testSetId(){
		Team t1 = new Team(1,"Ajax");
		t1.setId(3);
		assertEquals(t1.getId(),3);
	}
	
	@Test
	public void testSetIdFalse(){
		Team t1 = new Team(1,"Ajax");
		t1.setId(6);
		assertNotEquals(t1.getId(),1);
	}
	
	@Test
	public void testGetName(){
		Team t1 = new Team(1,"Ajax");
		assertEquals(t1.getName(),"Ajax");
	}
	
	@Test
	public void testGetNameFalse(){
		Team t1 = new Team(1,"Ajax");
		assertNotEquals(t1.getName(),"NAC Breda");
	}
	
	@Test
	public void testSetName(){
		Team t1 = new Team(1,"Ajax");
		t1.setName("PSV");
		assertEquals(t1.getName(),"PSV");
	}
	
	@Test
	public void testSetNameFalse(){
		Team t1 = new Team(1,"Ajax");
		t1.setName("Radio");
		assertNotEquals(t1.getName(),"FC Utrecht");
	}
	
	@Test
	public void testToString(){
		Team t1 = new Team(2,"Feyenoord");
		assertEquals(t1.toString(),"Team [id=2, name=Feyenoord, players=[]]");
	}
	
	@Test
	public void testToStringFalse(){
		Team t2 = new Team(3,"PSV");
		assertNotEquals(t2.toString(),"Excelsior");
	}
	
	@Test
	public void testEquals(){
		Team t1 = new Team(1,"Ajax");
		assertTrue(t1.equals(t1));
	}
	
	@Test
	public void testEqualsTwo()
	{
		Team t = new Team(1, "Foo");
		Team t3 = new Team(1, "Foo");
		assertEquals(t,t3);
	}
	
	@Test
	public void testBudget()
	{
		Team t = new Team(1, "Namae");
		t.setBudget((long) 1337);
		
		assertEquals((long)1337, t.getBudget());
	}
	
	@Test
	public void testEqualsFalse(){
		Team t1 = new Team(1,"Ajax");
		Team t2 = new Team(1,"NEC Nijmegen");
		assertFalse(t1.equals(t2));
	}
	
	@Test
	public void testEqualsFalseTwo(){
		Team t1 = new Team(1,"Ajax");
		Player p = new Player(1, "Ha", "Ha", (byte) 1, 0, 1, (byte) 50, (byte) 50, (byte) 50, 5);
		assertFalse(t1.equals(p));
	}
}
