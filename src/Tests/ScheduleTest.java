package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.League;
import model.Team;

import org.junit.Test;
import AI.Schedule;
import AI.Scheduler;


public class ScheduleTest {

	@Test
	public void testWeeksPlayed() 
	{
		// Do not touch this please <START>
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		// Do not touch this please </START>
		
		// Setting some matchs scores for testing
		s.getS().get(0).getSaturday().getMatches().get(0).setResult("1-0");
		s.getS().get(0).getSaturday().getMatches().get(1).setResult("0-2");
		s.getS().get(0).getSaturday().getMatches().get(2).setResult("3-0");
		s.getS().get(1).getSaturday().getMatches().get(0).setResult("5-0");
		s.getS().get(1).getSaturday().getMatches().get(1).setResult("1-9");
		s.getS().get(1).getSaturday().getMatches().get(2).setResult("1-8");
		
		s.getS().get(0).getSunday().getMatches().get(0).setResult("1-0");
		s.getS().get(0).getSunday().getMatches().get(1).setResult("0-2");
		s.getS().get(0).getSunday().getMatches().get(2).setResult("3-0");

		s.getS().get(0).getSaturday().getMatches().get(3).setResult("1-0");
		s.getS().get(0).getSunday().getMatches().get(3).setResult("0-2");
		s.getS().get(0).getFriday().getMatches().get(0).setResult("3-0");
		
		boolean[] a = s.WeeksPlayed();
		assertTrue(a[0]);
	}

	@Test
	public void testWeeksPlayedf1() 
	{
		// Do not touch this please <START>
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		// Do not touch this please </START>
		
		// Setting some matchs scores for testing
		s.getS().get(0).getSaturday().getMatches().get(0).setResult("1-0");
		s.getS().get(0).getSaturday().getMatches().get(1).setResult("0-2");
		s.getS().get(0).getSaturday().getMatches().get(2).setResult("3-0");
		s.getS().get(1).getSaturday().getMatches().get(0).setResult("5-0");
		s.getS().get(1).getSaturday().getMatches().get(1).setResult("1-9");
		s.getS().get(1).getSaturday().getMatches().get(2).setResult("1-8");
		
		s.getS().get(0).getSunday().getMatches().get(0).setResult("1-0");
		s.getS().get(0).getSunday().getMatches().get(1).setResult("0-2");
		s.getS().get(0).getSunday().getMatches().get(2).setResult("3-0");

		//s.getS().get(0).getSaturday().getMatches().get(3).setResult("1-0");
		s.getS().get(0).getSunday().getMatches().get(3).setResult("0-2");
		s.getS().get(0).getFriday().getMatches().get(0).setResult("3-0");
		
		boolean[] a = s.WeeksPlayed();
		assertFalse(a[0]);
	}

	@Test
	public void testWeeksPlayedf2() 
	{
		// Do not touch this please <START>
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		// Do not touch this please </START>
		
		// Setting some matchs scores for testing
		s.getS().get(0).getSaturday().getMatches().get(0).setResult("1-0");
		s.getS().get(0).getSaturday().getMatches().get(1).setResult("0-2");
		s.getS().get(0).getSaturday().getMatches().get(2).setResult("3-0");
		s.getS().get(1).getSaturday().getMatches().get(0).setResult("5-0");
		s.getS().get(1).getSaturday().getMatches().get(1).setResult("1-9");
		s.getS().get(1).getSaturday().getMatches().get(2).setResult("1-8");
		
		s.getS().get(0).getSunday().getMatches().get(0).setResult("1-0");
		s.getS().get(0).getSunday().getMatches().get(1).setResult("0-2");
		s.getS().get(0).getSunday().getMatches().get(2).setResult("3-0");

		s.getS().get(0).getSaturday().getMatches().get(3).setResult("1-0");
		//s.getS().get(0).getSunday().getMatches().get(3).setResult("0-2");
		s.getS().get(0).getFriday().getMatches().get(0).setResult("3-0");
		
		boolean[] a = s.WeeksPlayed();
		assertFalse(a[0]);
	}	
	
	@Test
	public void testWeeksPlayedf3() 
	{
		// Do not touch this please <START>
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		// Do not touch this please </START>
		
		// Setting some matchs scores for testing
		s.getS().get(0).getSaturday().getMatches().get(0).setResult("1-0");
		s.getS().get(0).getSaturday().getMatches().get(1).setResult("0-2");
		s.getS().get(0).getSaturday().getMatches().get(2).setResult("3-0");
		s.getS().get(1).getSaturday().getMatches().get(0).setResult("5-0");
		s.getS().get(1).getSaturday().getMatches().get(1).setResult("1-9");
		s.getS().get(1).getSaturday().getMatches().get(2).setResult("1-8");
		
		s.getS().get(0).getSunday().getMatches().get(0).setResult("1-0");
		s.getS().get(0).getSunday().getMatches().get(1).setResult("0-2");
		s.getS().get(0).getSunday().getMatches().get(2).setResult("3-0");

		s.getS().get(0).getSaturday().getMatches().get(3).setResult("1-0");
		s.getS().get(0).getSunday().getMatches().get(3).setResult("0-2");
		//s.getS().get(0).getFriday().getMatches().get(0).setResult("3-0");
		
		boolean[] a = s.WeeksPlayed();
		assertFalse(a[0]);
	}
	
	@Test
	public void testEquals(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		assertTrue(s.equals(s));
	}
	
	@Test
	public void testSetS() 
	{
		// Do not touch this please <START>
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		
		s.setS(null);
		assertNotNull(s);
	}
	
	@Test
	public void testEquals1() 
	{
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
	
		ArrayList<Team> teams1 = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leagueee = new League(0, "Eredivisie", "Nederland", teams1);
		Schedule d = Scheduler.scheduler(leagueee);

		assertFalse(s.equals(d));
	}
	
	@Test
	public void testtoString() 
	{
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);

		assertNotNull(s.toString());
	}
}
