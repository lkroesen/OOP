package Tests;

import static org.junit.Assert.*;
import model.Event;
import model.Match;
import model.Team;

import org.junit.Test;

public class testMatch {

	@Test
	public void test1() {
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		
		Event e = new Event(1,1,1,1);
		Event e2 = new Event(2,2,2,2);
		
		Match m = new Match(1, 2);
		m.setResult("1-1");
		m.setTeam_home(t1);
		m.setTeam_away(t2);
		m.addEventAway(e);
		m.addEventHome(e2);
		
		
		assertEquals(m.getId(),1);
	}
	
	@Test
	public void test2() {
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		
		Event e = new Event(1,1,1,1);
		Event e2 = new Event(2,2,2,2);
		
		Match m = new Match(1, 2);
		m.setResult("1-1");
		m.setTeam_home(t1);
		m.setTeam_away(t2);
		m.addEventAway(e);
		m.addEventHome(e2);
		
		assertEquals(m.getDay(),2);
	}
	
	@Test
	public void test3() {
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		
		Event e = new Event(1,1,1,1);
		Event e2 = new Event(2,2,2,2);
		
		Match m = new Match(1, 2);
		m.setResult("1-1");
		m.setTeam_home(t1);
		m.setTeam_away(t2);
		m.addEventAway(e);
		m.addEventHome(e2);
		
		assertTrue(m.getEvents_away().get(0).equals(e));
	}
	
	@Test
	public void test4() {
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		
		Event e = new Event(1,1,1,1);
		Event e2 = new Event(2,2,2,2);
		
		Match m = new Match(1, 2);
		m.setResult("1-1");
		m.setTeam_home(t1);
		m.setTeam_away(t2);
		m.addEventAway(e);
		m.addEventHome(e2);
		
		assertTrue(m.getEvents_home().get(0).equals(e2));
	}

	@Test
	public void test5() {
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		
		Event e = new Event(1,1,1,1);
		Event e2 = new Event(2,2,2,2);

		Match m2 = new Match(1, 2, t1, t2);
		Match m = new Match(1, 2);
		m.setResult("1-1");
		m.setTeam_home(t1);
		m.setTeam_away(t2);
		m.addEventAway(e);
		m.addEventHome(e2);
		
		assertEquals(m,m2);
	}

	@Test
	public void test6() {
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		
		Event e = new Event(1,1,1,1);
		Event e2 = new Event(2,2,2,2);
		
		Match m = new Match(1, 2);
		m.setResult("1-1");
		m.setTeam_home(t1);
		m.setTeam_away(t2);
		m.addEventAway(e);
		m.addEventHome(e2);
		
		assertEquals(m.getResult(),"1-1");
	}

	
	@Test
	public void test7() {
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		
		Event e = new Event(1,1,1,1);
		Event e2 = new Event(2,2,2,2);
		
		Match m = new Match(1, 2);
		m.setResult("1-1");
		m.setTeam_home(t1);
		m.setTeam_away(t2);
		m.addEventAway(e);
		m.addEventHome(e2);
		
		assertEquals(m.toString(),"Match [id=1, day=2, events_home=[Event [player=2, type=2, minute=2, outfor=2]], events_away=[Event [player=1, type=1, minute=1, outfor=1]], team_home=Team [id=2, name=Excelsior, players=[]], team_away=Team [id=1, name=Ajax, players=[]]]");

	}

	
	@Test
	public void test8() {
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		
		Event e = new Event(1,1,1,1);
		Event e2 = new Event(2,2,2,2);
		
		Match m3 = new Match(1,3,t1,t2);
		Match m = new Match(1, 2);
		m.setResult("1-1");
		m.setTeam_home(t1);
		m.setTeam_away(t2);
		m.addEventAway(e);
		m.addEventHome(e2);
		
		
		assertNotEquals(m,m3);
	}
	
	@Test
	public void test9() {
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		
		Event e = new Event(1,1,1,1);
		Event e2 = new Event(2,2,2,2);
		
		Match m4 = new Match(0,2,t1,t2);
		Match m = new Match(1, 2);
		m.setResult("1-1");
		m.setTeam_home(t1);
		m.setTeam_away(t2);
		m.addEventAway(e);
		m.addEventHome(e2);
		
		assertNotEquals(m,m4);
	}

	@Test
	public void test10() {
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		
		Event e = new Event(1,1,1,1);
		Event e2 = new Event(2,2,2,2);
		
		Match m6 = new Match(1,2,t1,t1);
		Match m = new Match(1, 2);
		m.setResult("1-1");
		m.setTeam_home(t1);
		m.setTeam_away(t2);
		m.addEventAway(e);
		m.addEventHome(e2);

		assertNotEquals(m,m6);
	}

	@Test
	public void test11() {
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		
		Event e = new Event(1,1,1,1);
		Event e2 = new Event(2,2,2,2);
		
		Match m5 = new Match(1,2,t2,t1);
		Match m = new Match(1, 2);
		m.setResult("1-1");
		m.setTeam_home(t1);
		m.setTeam_away(t2);
		m.addEventAway(e);
		m.addEventHome(e2);

		assertNotEquals(m,m5);
		
	}

	@Test
	public void test12() {
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		
		Event e = new Event(1,1,1,1);
		Event e2 = new Event(2,2,2,2);
		
		Match m = new Match(1, 2);
		m.setResult("1-1");
		m.setTeam_home(t1);
		m.setTeam_away(t2);
		m.addEventAway(e);
		m.addEventHome(e2);
		
		assertNotEquals(m,e);
	}

	@Test
	public void test13() {
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		
		Event e = new Event(1,1,1,1);
		Event e2 = new Event(2,2,2,2);
		
		Match m = new Match(1, 2);
		m.setResult("1-1");
		m.setTeam_home(t1);
		m.setTeam_away(t2);
		m.addEventAway(e);
		m.addEventHome(e2);
		
		
		int[] a = m.ParseResult();
		assertEquals(a[0],1);
	}

	@Test
	public void test14() {
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		
		Event e = new Event(1,1,1,1);
		Event e2 = new Event(2,2,2,2);
		
		Match m = new Match(1, 2);
		m.setResult("1-1");
		m.setTeam_home(t1);
		m.setTeam_away(t2);
		m.addEventAway(e);
		m.addEventHome(e2);
		
		int[] a = m.ParseResult();
		assertEquals(a[1],1);
	}
	
}
