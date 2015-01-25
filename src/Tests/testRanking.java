package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import AI.Ranking;
import AI.Schedule;
import AI.Scheduler;
import model.League;
import model.Team;

import org.junit.Test;

public class testRanking {

	@Test
	public void test() {
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
		s.getS().get(1).getSaturday().getMatches().get(1).setResult("2-2");
		s.getS().get(1).getSaturday().getMatches().get(2).setResult("1-8");
		
		s.getS().get(0).getSunday().getMatches().get(0).setResult("1-0");
		s.getS().get(0).getSunday().getMatches().get(1).setResult("0-2");
		s.getS().get(0).getSunday().getMatches().get(2).setResult("3-0");

		s.getS().get(0).getSaturday().getMatches().get(3).setResult("1-0");
		s.getS().get(0).getSunday().getMatches().get(3).setResult("0-2");
		s.getS().get(0).getFriday().getMatches().get(0).setResult("3-3");
		
		// Ranking will work for any match that has a result
		// if the match has no result it will not be used
		// So, don't set a result EVER, unless a match has been played!
		Ranking r = Ranking.generate(s);
		
		boolean[] a = s.WeeksPlayed();
		
		r.setRanking(r.getRanking());
		r.setScoreOfTeam(r.getScoreOfTeam());
		
		assertEquals(a[0], true);

	}
	
	@Test
	public void testerino() 
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
		s.getS().get(1).getSaturday().getMatches().get(1).setResult("2-2");
		s.getS().get(1).getSaturday().getMatches().get(2).setResult("1-8");
		
		s.getS().get(0).getSunday().getMatches().get(0).setResult("1-0");
		s.getS().get(0).getSunday().getMatches().get(1).setResult("0-2");
		s.getS().get(0).getSunday().getMatches().get(2).setResult("3-0");

		s.getS().get(0).getSaturday().getMatches().get(3).setResult("1-0");
		s.getS().get(0).getSunday().getMatches().get(3).setResult("0-2");
		s.getS().get(0).getFriday().getMatches().get(0).setResult("3-3");
		
		// Ranking will work for any match that has a result
		// if the match has no result it will not be used
		// So, don't set a result EVER, unless a match has been played!
		Ranking r = Ranking.generate(s);
		
		assertNotEquals(Ranking.CurrentRank(1, r),0);
	}

	@Test
	public void testCurrentRankpp() 
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
		s.getS().get(1).getSaturday().getMatches().get(1).setResult("2-2");
		s.getS().get(1).getSaturday().getMatches().get(2).setResult("1-8");
		
		s.getS().get(0).getSunday().getMatches().get(0).setResult("1-0");
		s.getS().get(0).getSunday().getMatches().get(1).setResult("0-2");
		s.getS().get(0).getSunday().getMatches().get(2).setResult("3-0");
	
		s.getS().get(0).getSaturday().getMatches().get(3).setResult("1-0");
		s.getS().get(0).getSunday().getMatches().get(3).setResult("0-2");
		s.getS().get(0).getFriday().getMatches().get(0).setResult("3-3");
		
		// Ranking will work for any match that has a result
		// if the match has no result it will not be used
		// So, don't set a result EVER, unless a match has been played!
		Ranking r = Ranking.generate(s);
		
		assertEquals(Ranking.CurrentRank(-1, r),1);
	}
}
