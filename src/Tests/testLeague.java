package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.League;
import model.Team;

import org.junit.Test;

public class testLeague {

	@Test
	public void testConstructor() {
		League l1 = new League(1,"Eredivisie","Holland");
		assertTrue(l1.equals(l1));
	}
	
	@Test
	public void testConstructorFalse(){
		League l1 = new League(1,"Eredivisie","Holland");
		League l2 = new League(5,"Premier League","England");
		assertFalse(l1.equals(l2));
	}
	
	@Test
	public void testGetId(){
		League l1 = new League(1,"Eredivisie","Holland");
		assertEquals(l1.getId(),1);
	}
	
	@Test
	public void testGetIdFalse(){
		League l1 = new League(1,"Eredivisie","Holland");
		assertNotEquals(l1.getId(),5);
	}
	
	@Test
	public void testSetId(){
		League l1 = new League(1,"Eredivisie","Holland");
		l1.setId(6);
		assertEquals(l1.getId(),6);
	}
	
	@Test
	public void testSetIdFalse(){
		League l1 = new League(1,"Eredivisie","Holland");
		l1.setId(5);
		assertNotEquals(l1.getId(),3);
	}
	
	@Test
	public void testGetName(){
		League l1 = new League(1,"Eredivisie","Holland");
		assertEquals(l1.getName(),"Eredivisie");
	}
	
	@Test
	public void testGetNameFalse(){
		League l1 = new League(1,"Eredivisie","Holland");
		assertNotEquals(l1.getName(),"Robben");
	}
	
	@Test
	public void testSetName(){
		League l1 = new League(1,"Eredivisie","Holland");
		l1.setName("Primera Division");
		assertEquals(l1.getName(),"Primera Division");
	}
	
	@Test
	public void testGetCountry(){
		League l1 = new League(1,"Eredivisie","Holland");
		assertEquals(l1.getCountry(),"Holland");
	}
	
	@Test
	public void testGetCountryFalse(){
		League l1 = new League(1,"Eredivisie","Holland");
		assertNotEquals(l1.getCountry(),"Holllland");
	}
	
	@Test
	public void testSetCountry(){
		League l1 = new League(1,"Eredivisie","Holland");
		l1.setCountry("Germany");
		assertEquals(l1.getCountry(),"Germany");
	}
	
	@Test
	public void testSetCountryFalse(){
		League l1 = new League(1,"Eredivisie","Holland");
		l1.setCountry("Rbot");
		assertNotEquals(l1.getCountry(),"Germany");
	}
	
	@Test
	public void testToString(){
		League l1 = new League(1,"Eredivisie","Holland");
		assertEquals(l1.toString(), "League [id=1, name=Eredivisie, country=Holland, teams=[]]");
	}
	
	@Test
	public void testToStringFalse(){
		League l1 = new League(1,"Eredivisie","Holland");
		assertNotEquals(l1.toString(),"'nddnjfnsdknfksd n");
	}

	@Test
	public void testTeams()
	{
		League lr = new League(2, "ere", "hol");

		ArrayList<Team> tl = new ArrayList<Team>();
		Team t = new Team(1,"hi");
		lr.setTeams(tl);
		tl.add(t);
		lr.addTeam(t);
		
		assertEquals(lr.getTeamById(2), t);
		assertEquals(lr.getTeamByName("hi"), t);
		
		Team t2 = new Team(4,"di");
		Team t3 = new Team(3,"fi");
		lr.addTeam(t2);
		lr.addTeam(t3);
		League lf5 = new League(2,"hi","ho", null);
		tl.add(t2);
		tl.add(t3);
		
		assertEquals(lr.getTeamById(4), t2);
		assertEquals(lr.getTeamByName("fi"), t3);
		assertEquals(lr.getTeamByName(""), t);
		assertEquals(lr.getTeams(),tl);
		League lf = new League(2,"hi","ho", tl);
		League lf2 = new League(3,"hi","ho", tl);
		League lf3 = new League(2,"h0","ho", tl);
		League lf4 = new League(2,"hi","h0", tl);

		assertNotEquals(lf, lf2);
		assertNotEquals(lf, lf3);
		assertNotEquals(lf, lf4);
		assertNotEquals(lf, lf5);
		assertNotEquals(lf, t2);
		assertNotEquals(lf, lr);
	}
}
