package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Game;
import model.League;
import model.Match;
import model.Transfer;

import org.junit.Test;

public class testGame {

	@Test
	public void testConstructor() {
		Game g1 = new Game(20,"Leon",5,1);
		assertEquals(g1,g1);
	}
	
	@Test
	public void testConstructorFalse() {
		Game g1 = new Game(10,"ABC",20,6);
		Game g2 = new Game(10,"ABC",21,6);
		assertNotEquals(g1,g2);
	}
	
	
	@Test
	public void TestSetters1(){
		Game g = new Game(1,"1",1,1);
		League l = new League(0, null, null);
		ArrayList<League> ll = new ArrayList<League>();
		ll.add(l);
		g.setLeagues(ll);
		Transfer t = new Transfer(0,1,1,1,1,1);
		g.addTransfer(t);
		Match m = new Match(1,1);
		g.addMatch(m);
		g.setId(0);
		Game.setCurrentTeam(1);
		ArrayList<Match> ml = new ArrayList<Match>();
		ml.add(m);
		g.setMatches(ml);
		ArrayList<Transfer> tl = new ArrayList<Transfer>();
		tl.add(t);
		g.setTransfers(tl);
		
		assertEquals(g.getTransfers(),tl);
	}
	
	@Test
	public void TestSetters2(){
		Game g = new Game(1,"1",1,1);
		League l = new League(0, null, null);
		ArrayList<League> ll = new ArrayList<League>();
		ll.add(l);
		g.setLeagues(ll);
		Transfer t = new Transfer(0,1,1,1,1,1);
		g.addTransfer(t);
		Match m = new Match(1,1);
		g.addMatch(m);
		g.setId(0);
		Game.setCurrentTeam(1);
		ArrayList<Match> ml = new ArrayList<Match>();
		ml.add(m);
		g.setMatches(ml);
		ArrayList<Transfer> tl = new ArrayList<Transfer>();
		tl.add(t);
		g.setTransfers(tl);
		
		assertEquals(g.getMatches(),ml);
	}
	
	@Test
	public void TestSetters3(){
		Game g = new Game(1,"1",1,1);
		League l = new League(0, null, null);
		ArrayList<League> ll = new ArrayList<League>();
		ll.add(l);
		g.setLeagues(ll);
		Transfer t = new Transfer(0,1,1,1,1,1);
		g.addTransfer(t);
		Match m = new Match(1,1);
		g.addMatch(m);
		g.setId(0);
		Game.setCurrentTeam(1);
		ArrayList<Match> ml = new ArrayList<Match>();
		ml.add(m);
		g.setMatches(ml);
		ArrayList<Transfer> tl = new ArrayList<Transfer>();
		tl.add(t);
		g.setTransfers(tl);
		
		assertEquals(g.getLeagues(),ll);
	}

	@Test
	public void testGetName(){
		Game g1 = new Game(20,"Karel",25,4);
		assertEquals(g1.getName(),"Karel");
	}
	
	@Test
	public void testGetNameFalse(){
		Game g1 = new Game(150,"Abc",15,3);
		assertNotEquals(g1.getName(),"Leon");
	}
	
	@Test
	public void testsetName(){
		Game g1 = new Game(30,"Karel",36,10);
		g1.setName("Leon");
		assertEquals(g1.getName(),"Leon");
	}
	
	@Test
	public void testsetNameFalse(){
		Game g1 = new Game(20,"Leon",2,300);
		g1.setName("Hans");
		assertNotEquals(g1.getName(),"Leon");
	}
	
	@Test
	public void testCurrentDay(){
		Game g2 = new Game(30,"Robert",1,2);
		assertEquals(g2.getCurrentDay(),1);
	}
	
	@Test
	public void testCurrentDayFalse(){
		Game g1 = new Game(10,"Leon",20,3);
		assertNotEquals(g1.getCurrentDay(),4);
	}
	
	@Test
	public void testCurrentTeam(){
		Game g2 = new Game(25,"Leon",1,2);
		assertEquals(2,g2.getCurrentTeam());
	}
	
	@Test
	public void testCurrentTeamFalse(){
		Game g3 = new Game(20,"Karel",6,10);
		assertNotEquals(g3.getCurrentTeam(),20);
	}
	
	@Test
	public void testSetCurrentDay(){
		Game g1 = new Game(15,"Sjaak",23,1);
		g1.setCurrentDay(5);
		assertEquals(g1.getCurrentDay(),5);
	}
	
	@Test
	public void testSetCurrentDayFalse(){
		Game g1 = new Game(15,"Robert",1,13);
		g1.setCurrentDay(6);
		assertNotEquals(g1.getCurrentDay(),1);
	}
	
	@Test
	public void testGetId(){
		Game g1 = new Game(36,"Leon",26,260);
		assertEquals(g1.getId(),36);
	}
	
	@Test
	public void testGetIdFalse(){
		Game g1 = new Game(10,"Jan",1,3);
		assertNotEquals(g1.getId(),15);
	}
	
	@Test
	public void testToString(){
		Game g1 = new Game(20,"Robert",23,6);
		assertEquals(g1.toString(), "Game [id=20, name=Robert, currentDay=23, currentTeam=6, leagues=[], transfers=[], matches=[]]");
	}
	
	@Test
	public void testToStringFalse(){
		Game g1 = new Game(15,"Jan",23,1);
		assertNotEquals(g1.toString(),"hello");
	}
	
	@Test
	public void testaddLeague(){
		Game g1 = new Game(22,"Jan",2,5);
		Game g2 = new Game(1,"Robert",36,10);
		League l1 = new League(1,"Eredivisie","Holland");
		g1.addLeague(l1);
		g2.addLeague(l1);
		assertTrue(g1.getLeagues().equals(g2.getLeagues()));
	}
	
	@Test
	public void testaddLeagueFalse(){
		Game g1 = new Game(10,"Robert",2,5);
		Game g2 = new Game(20,"Jan",22,10);
		League l1 = new League(22,"BundesLiga","Germany");
		g1.addLeague(l1);
		assertFalse(g1.getLeagues().equals(g2.getLeagues()));
	}

}
