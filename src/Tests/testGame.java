package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Game;
import model.League;
import model.Match;
import model.Team;
import model.Transfer;

import org.junit.Test;

import AI.Schedule;
import AI.Scheduler;

public class testGame {
	
	
	

	@Test
	public void testConstructor() {
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g1 = new Game(20,"Leon",5,1, 4, 5, s);
		assertEquals(g1,g1);
	}
	
	@Test
	public void testConstructorFalse() {
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g1 = new Game(10,"ABC",20,6, 4, 5, s);
		Game g2 = new Game(10,"ABC",21,6, 4, 5, s);
		assertNotEquals(g1,g2);
	}
	
	
	@Test
	public void TestSetters1(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g = new Game(1,"1",1,1, 4, 5, s);
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
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g = new Game(1,"1",1,1, 4, 5, s);
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
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g = new Game(1,"1",1,1, 4, 5, s);
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
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g1 = new Game(20,"Karel",25,4, 4, 5, s);
		assertEquals(g1.getName(),"Karel");
	}
	
	@Test
	public void testGetNameFalse(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g1 = new Game(150,"Abc",15,3, 4, 5, s);
		assertNotEquals(g1.getName(),"Leon");
	}
	
	@Test
	public void testsetName(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g1 = new Game(30,"Karel",36,10, 4, 5, s);
		g1.setName("Leon");
		assertEquals(g1.getName(),"Leon");
	}
	
	@Test
	public void testsetNameFalse(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g1 = new Game(20,"Leon",2,300, 4, 5, s);
		g1.setName("Hans");
		assertNotEquals(g1.getName(),"Leon");
	}
	
	@Test
	public void testCurrentDay(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g2 = new Game(30,"Robert",1,2, 4, 5, s);
		assertEquals(g2.getCurrentDay(),1);
	}
	
	@Test
	public void testCurrentDayFalse(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g1 = new Game(10,"Leon",20,3, 4, 5, s);
		assertNotEquals(g1.getCurrentDay(),4);
	}
	
	@Test
	public void testCurrentTeam(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g2 = new Game(25,"Leon",1,2, 4, 5, s);
		assertEquals(2,g2.getCurrentTeam());
	}
	
	@Test
	public void testCurrentTeamFalse(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g3 = new Game(20,"Karel",6,10, 4, 5, s);
		assertNotEquals(g3.getCurrentTeam(),20);
	}
	
	@Test
	public void testSetCurrentDay(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g1 = new Game(15,"Sjaak",23,1, 4, 5, s);
		g1.setCurrentDay(5);
		assertEquals(g1.getCurrentDay(),5);
	}
	
	@Test
	public void testSetCurrentDayFalse(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g1 = new Game(15,"Robert",1,13, 4, 5, s);
		g1.setCurrentDay(6);
		assertNotEquals(g1.getCurrentDay(),1);
	}
	
	@Test
	public void testGetId(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g1 = new Game(36,"Leon",26,260, 4, 5, s);
		assertEquals(g1.getId(),36);
	}
	
	@Test
	public void testGetIdFalse(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g1 = new Game(10,"Jan",1,3, 4, 5, s);
		assertNotEquals(g1.getId(),15);
	}
	
	@Test
	public void testToStringFalse(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g1 = new Game(15,"Jan",23,1, 4, 5, s);
		assertNotEquals(g1.toString(),"hello");
	}
	
	@Test
	public void testaddLeague(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g1 = new Game(22,"Jan",2,5, 4, 5, s);
		Game g2 = new Game(1,"Robert",36,10, 4, 5, s);
		League l1 = new League(1,"Eredivisie","Holland");
		g1.addLeague(l1);
		g2.addLeague(l1);
		assertTrue(g1.getLeagues().equals(g2.getLeagues()));
	}
	
	@Test
	public void testaddLeagueFalse(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		Game g1 = new Game(10,"Robert",2,5, 4, 5, s);
		Game g2 = new Game(20,"Jan",22,10, 4, 5, s);
		League l1 = new League(22,"BundesLiga","Germany");
		g1.addLeague(l1);
		assertFalse(g1.getLeagues().equals(g2.getLeagues()));
	}

}
