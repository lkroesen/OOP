package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Team;
import model.Player;
import model.Match;
import AI.Bet;
import AI.Betting;
import AI.Schedule;
import AI.PlayRound;
import AI.PlayDay;

import org.junit.Test;

public class BettingTest {

	@Test
	public void testBetting() 
	{
		for (int c = 0; c < 200; c++){
		ArrayList<Player> Team = new ArrayList<Player>();
		//Likely winners            ID       NAME        SURNAME        JERSEY_NR  	TYPE   POS  OFFENSIVE   DEFENSIVE  STAMINA       PRICE
		Player one    = new Player	(1, 	"Neo", 		"Keeper", 		(byte)  1, 	 0, 	1, 	(byte)  99, (byte) 99,  (byte) 99, 	 1000);
		Player two    = new Player	(2, 	"Owt", 		"Defender", 	(byte)  2, 	 1, 	2, 	(byte)  99, (byte) 99,  (byte) 99, 	 2000);
		Player three  = new Player	(3, 	"Heert", 	"Defender", 	(byte)  3, 	 1, 	3, 	(byte)  99, (byte) 99,  (byte) 99, 	 3000);
		Player four   = new Player	(4, 	"Rofu", 	"Defender", 	(byte)  4, 	 1, 	4, 	(byte)  99, (byte) 99,  (byte) 100,  4000);
		Player five   = new Player	(5, 	"Evif", 	"Defender", 	(byte)  5, 	 1, 	5, 	(byte)  99, (byte) 99,  (byte) 99, 	 5000);
		Player six    = new Player	(6, 	"Xis",		"MidFielder",	(byte)  6, 	 2, 	6, 	(byte)  99, (byte) 99,  (byte) 99,	 6000);
		Player seven  = new Player	(7, 	"Svene",	"Attacker",		(byte)  7, 	 3, 	7, 	(byte)  99, (byte) 99,  (byte) 99,	 7000);
		Player eight  = new Player	(8, 	"Infinity", "Attacker",		(byte)  8, 	 3, 	8, 	(byte)  99, (byte) 100, (byte) 99,	 8000);
		Player nine   = new Player	(9, 	"Cirno",	"Attacker",		(byte)  9, 	 3, 	9, 	(byte)  99, (byte) 99,  (byte) 99,	 9000);
		Player ten    = new Player	(10,	"Ned",		"Attacker",		(byte) 10, 	 3, 	10, 	(byte)  99, (byte) 99,  (byte) 99,	10000);
		Player eleven = new Player	(11,	"Veleen",	"Attacker",		(byte) 11, 	 3, 	11, (byte) 100, (byte) 99,  (byte) 99,	11000);

		Team.add(one);			Team.add(two);		Team.add(three);		Team.add(four);		Team.add(five);
		Team.add(six);			Team.add(seven);	Team.add(eight);		Team.add(nine);		Team.add(ten);
		Team.add(eleven);		
		
		ArrayList<Player> Teame = new ArrayList<Player>();

		Player onee    = new Player	(1, 	"Neo", 		"Keeper", 		(byte)  1, 	 0, 	1, 	(byte)  59, (byte) 59,  (byte) 59, 	 1000);
		Player twoe    = new Player	(2, 	"Owt", 		"Defender", 	(byte)  2, 	 1, 	1, 	(byte)  59, (byte) 59,  (byte) 59, 	 2000);
		Player threee  = new Player	(3, 	"Heert", 	"Defender", 	(byte)  3, 	 1, 	2, 	(byte)  59, (byte) 59,  (byte) 59, 	 3000);
		Player foure   = new Player	(4, 	"Rofu", 	"Defender", 	(byte)  4, 	 1, 	3, 	(byte)  59, (byte) 59,  (byte) 50,  4000);
		Player fivee   = new Player	(5, 	"Evif", 	"Defender", 	(byte)  5, 	 1, 	4, 	(byte)  59, (byte) 59,  (byte) 59, 	 5000);
		Player sixe    = new Player	(6, 	"Xis",		"MidFielder",	(byte)  6, 	 2, 	5, 	(byte)  59, (byte) 59,  (byte) 59,	 6000);
		Player sevene  = new Player	(7, 	"Svene",	"Attacker",		(byte)  7, 	 3, 	6, 	(byte)  59, (byte) 59,  (byte) 59,	 7000);
		Player eighte  = new Player	(8, 	"Infinity", "Attacker",		(byte)  8, 	 3, 	7, 	(byte)  59, (byte) 50, (byte) 59,	 8000);
		Player ninee   = new Player	(9, 	"Cirno",	"Attacker",		(byte)  9, 	 3, 	9, 	(byte)  59, (byte) 59,  (byte) 59,	 9000);
		Player tene    = new Player	(10,	"Ned",		"Attacker",		(byte) 10, 	 3, 	8, 	(byte)  59, (byte) 59,  (byte) 59,	10000);
		Player elevene = new Player	(11,	"Veleen",	"Attacker",		(byte) 11, 	 3, 	10, (byte)  50, (byte) 59,  (byte) 59,	11000);

		
		Teame.add(onee);			Teame.add(twoe);		Teame.add(threee);		Teame.add(foure);		Teame.add(fivee);
		Teame.add(sixe);			Teame.add(sevene);		Teame.add(eighte);		Teame.add(ninee);		Teame.add(tene);
		Teame.add(elevene);			

		Team winners = new Team(1, "Winners", Team);
		Team losers = new Team(2, "Losers", Teame);
		
		ArrayList<Team> tl = new ArrayList<Team>();
		tl.add(winners);
		tl.add(losers);
		
		Match m = new Match(0, 5, winners, losers);
		
		ArrayList<Match> mat = new ArrayList<Match>();
		mat.add(m);
				
		PlayDay f = new PlayDay(mat,"friday");
		PlayDay sa = new PlayDay(mat,"saturday");
		PlayDay su = new PlayDay(mat, "sunday");
		
		PlayRound w = new PlayRound(f,sa,su,0);
		
		ArrayList<PlayRound> sch = new ArrayList<PlayRound>();
		sch.add(w);
		
		Schedule s = new Schedule();
		s.setS(sch);
		
		ArrayList<Betting> b = Betting.Before_Match(s, 0);
		
		ArrayList<Team> TeamList = new ArrayList<Team>();
		Team P_Team = new Team(4, "Player");
		TeamList.add(P_Team);
		
		long Budget = Betting.After_Match((long)123456, Betting.Bet_Maker(b, (long)123456, tl,  1, true), winners.getId());
		System.out.println("The player now has: " + Budget + " imaginary money left");
		
		assertNotNull(b);
		}
	}

	@Test
	public void testTeamByID1()
	{
		Team winners = new Team(1, "Winners");
		Team losers = new Team(2, "Losers");
		ArrayList<Team> tl = new ArrayList<Team>();
		tl.add(winners);
		tl.add(losers);
		assertEquals(Betting.getTeamNameById(1, tl), "Winners");
	}
	
	@Test
	public void testTeamByID2()
	{
		Team winners = new Team(1, "Winners");
		Team losers = new Team(2, "Losers");
		ArrayList<Team> tl = new ArrayList<Team>();
		tl.add(winners);
		tl.add(losers);
		assertEquals(Betting.getTeamNameById(2, tl), "Losers");
	}
	
	@Test
	public void testTeamByID3()
	{
		Team winners = new Team(1, "Winners");
		Team losers = new Team(2, "Losers");
		ArrayList<Team> tl = new ArrayList<Team>();
		tl.add(winners);
		tl.add(losers);
		assertNotEquals(Betting.getTeamNameById(1,tl),"Losers");
	}
	
	@Test
	public void testTeamByID4()
	{
		Team winners = new Team(1, "Winners");
		Team losers = new Team(2, "Losers");
		ArrayList<Team> tl = new ArrayList<Team>();
		tl.add(winners);
		tl.add(losers);
		assertEquals(Betting.getTeamNameById(0,tl),"");
	}
	
	@Test
	public void testGettersNSetters1()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);
		
		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
		
		assertEquals(b.equals(b),true);
	}
	
	@Test
	public void testGettersNSetters2()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);
		Betting c = new Betting((double)1.6, (double)1.3, 2,3, "2-2", 0);
		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
		
		assertEquals(b.equals(c), false);
	}
	
	@Test
	public void testGettersNSetters3()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);
		Betting d = new Betting((double)1.2, (double)1.6, 1,0, "2-2", 0);
		
		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
		
		assertEquals(b.equals(d), false);
	}
	
	@Test
	public void testGettersNSetters4()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);
		Betting e = new Betting((double)1.2, (double)1.3, 6,3, "2-2", 0);
		
		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
		
		assertEquals(b.equals(e), false);
	}
	
	@Test
	public void testGettersNSetters5()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);
		Betting f = new Betting((double)1.2, (double)1.3, 2,1, "2-2", 0);
		
		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
		
		assertEquals(b.equals(f), false);
	}
	
	@Test
	public void testGettersNSetters6()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);
		Betting g = new Betting((double)1.2, (double)1.3, 2,3, "2-1", 0);
		
		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
		
		assertEquals(b.equals(g), false);
	}
	
	@Test
	public void testGettersNSetters7()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);
		Player one    = new Player	(1, 	"Neo", 		"Keeper", 		(byte)  1, 	 0, 	1, 	(byte)  99, (byte) 99,  (byte) 99, 	 1000);
		
		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
		
		assertEquals(b.equals(one), false);
	}
	
	@Test
	public void testGettersNSetters8()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);

		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
	
		assertEquals(b.equals(null), false);
	}
	
	@Test
	public void testGettersNSetters9()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);
		Betting h = new Betting((double)1.2, (double)1.3, 2,3, null, 0);
		
		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
		
		assertEquals(b.equals(h), false);
	}
	
	@Test
	public void testGettersNSetters10()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);
		Betting h = new Betting((double)1.2, (double)1.3, 2,3, null, 0);
	
		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
	
		assertEquals(h.equals(h), true);
	}
	
	@Test
	public void testGettersNSetters11()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);
		Betting h = new Betting((double)1.2, (double)1.3, 2,3, null, 0);
		
		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
		
		assertEquals(h.equals(b), false);
		
	}
	
	@Test
	public void testGettersNSetters12()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);
		
		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
		
		assertEquals(b.getRate1(),1.2,0.0);
	}
	
	@Test
	public void testGettersNSetters13()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);

		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
		
		assertEquals(b.getRate2(),1.3,0.0);
	}
	
	@Test
	public void testGettersNSetters14()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);
	
		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
		
		assertEquals(b.getResult(),"2-2");
	}
	
	@Test
	public void testGettersNSetters15()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);
	
		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
		
		assertEquals(b.getTeam1(),2);
	}
	
	@Test
	public void testGettersNSetters16()
	{
		Betting b = new Betting((double)1.5, (double)1.5, 1,0, "1-1", 0);

		b.setRate1(1.2);
		b.setRate2(1.3);
		b.setResult("2-2");
		b.setTeam1(2);
		b.setTeam2(3);
		
		assertEquals(b.getTeam2(),3);
	}
	
	@Test
	public void testSetMatchid(){
		Betting d = new Betting((double)1.2, (double)1.6, 1,0, "2-2", 0);
		d.setMatchid(62);
		Betting e = new Betting((double)1.2, (double)1.6, 1,0, "2-2", 62);
		assertTrue(d.equals(e));
	}
}
