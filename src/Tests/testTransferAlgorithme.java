package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import AI.TransferAlgorithm;
import AI.Ranking;
import AI.Schedule;
import AI.Scheduler;
import model.League;
import model.Player;
import model.Team;
import model.Game;

import org.junit.Test;

import xml.XML;

public class testTransferAlgorithme {

	@Test
	public void test0() {
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		//  ID       NAME        SURNAME        JERSEY_NR  	TYPE   POS  OFFENSIVE   DEFENSIVE  STAMINA       PRICE
		Player one    = new Player	(1, 	"Neo", 		"Keeper", 		(byte)  1, 	 0, 	0, 	(byte)  20, (byte) 69, (byte) 56, 	 1000);

		Schedule s = Scheduler.scheduler(leaguee);
		Game g = new Game(1, "game", 0, 1, 0, 0, s);
		TransferAlgorithm t = new TransferAlgorithm(g);
		t.CalculateWorth(one);
		
		assertEquals(one.getPrice(), 772800);
	}
	
	@Test
	public void test1() {
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		Game g = new Game(1, "game", 0, 1, 0, 0, s);
		TransferAlgorithm t = new TransferAlgorithm(g);
		
		assertNotNull(t);
	}
	
	@Test
	public void test2() {
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		Game g = new Game(1, "game", 0, 1, 0, 0, s);
		TransferAlgorithm t = new TransferAlgorithm(g);
		t.DailyRoutine(g);
		
		assertNotNull(t);
	}
	
	
	@Test
	public void test3() {
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		//  ID       NAME        SURNAME        JERSEY_NR  	TYPE   POS  OFFENSIVE   DEFENSIVE  STAMINA       PRICE
		Player one    = new Player	(1, 	"Neo", 		"Keeper", 		(byte)  1, 	 0, 	0, 	(byte)  20, (byte) 69, (byte) 56, 	 1000);

		Schedule s = Scheduler.scheduler(leaguee);
		Game g = new Game(1, "game", 0, 1, 0, 0, s);
		TransferAlgorithm t = new TransferAlgorithm(g);
		t.AddPlayer(one);
		
		assertNotNull(t);
	}
	
	@Test
	public void test4() {
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		//  ID       NAME        SURNAME        JERSEY_NR  	TYPE   POS  OFFENSIVE   DEFENSIVE  STAMINA       PRICE
		Player one    = new Player	(1, 	"Neo", 		"Keeper", 		(byte)  1, 	 0, 	0, 	(byte)  20, (byte) 69, (byte) 56, 	 1000);

		Schedule s = Scheduler.scheduler(leaguee);
		Game g = new Game(1, "game", 0, 1, 0, 0, s);
		TransferAlgorithm t = new TransferAlgorithm(g);
		t.AddPlayer(one);
		t.DelPlayer(one);
		
		assertNotNull(t);
	}
	
	@Test
	public void test5() {
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		//  ID       NAME        SURNAME        JERSEY_NR  	TYPE   POS  OFFENSIVE   DEFENSIVE  STAMINA       PRICE
		Player one    = new Player	(1, 	"Neo", 		"Keeper", 		(byte)  1, 	 0, 	0, 	(byte)  20, (byte) 69, (byte) 56, 	 1000);

		Schedule s = Scheduler.scheduler(leaguee);
		Game g = new Game(1, "game", 0, 1, 0, 0, s);
		TransferAlgorithm t = new TransferAlgorithm(g);
		t.AddPlayer(one);
		t.Sell(one);
		
		assertNotNull(t);
	}
	
	@Test
	public void test6() {
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		//  ID       NAME        SURNAME        JERSEY_NR  	TYPE   POS  OFFENSIVE   DEFENSIVE  STAMINA       PRICE
		Player one    = new Player	(1, 	"Neo", 		"Keeper", 		(byte)  1, 	 0, 	0, 	(byte)  20, (byte) 69, (byte) 56, 	 1000);

		Schedule s = Scheduler.scheduler(leaguee);
		Game g = new Game(1, "game", 0, 1, 0, 0, s);
		TransferAlgorithm t = new TransferAlgorithm(g);
		t.AddPlayer(one);
		t.Buy(one);
		
		assertNotNull(t);
	}
	
	@Test
	public void test7() {
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		//  ID       NAME        SURNAME        JERSEY_NR  	TYPE   POS  OFFENSIVE   DEFENSIVE  STAMINA       PRICE
		Player one    = new Player	(1, 	"Neo", 		"Keeper", 		(byte)  1, 	 0, 	0, 	(byte)  20, (byte) 69, (byte) 56, 	 1000);
		Team team = new Team(17, "team 17");
		Schedule s = Scheduler.scheduler(leaguee);
		Game g = new Game(1, "game", 0, 1, 0, 0, s);
		TransferAlgorithm t = new TransferAlgorithm(g);
		t.AddPlayer(one);
		t.TransferPlayer(team, one);
		
		assertNotNull(t);
	}
	
	
	@Test
	public void testCalculateWorth(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		//  ID       NAME        SURNAME        JERSEY_NR  	TYPE   POS  OFFENSIVE   DEFENSIVE  STAMINA       PRICE
		Player one    = new Player	(1, 	"Neo", 		"Keeper", 		(byte)  1, 	 0, 	0, 	(byte)  20, (byte) 69, (byte) 56, 	 1000);
		Schedule s = Scheduler.scheduler(leaguee);
		Game g = new Game(1, "game", 0, 1, 0, 0, s);
		TransferAlgorithm t = new TransferAlgorithm(g);
		
		t.CalculateWorth(one);
		int worth = 10*(one.getDefensiveScore()*one.getOffensiveScore()*one.getStaminaScore());
		
		assertEquals(one.getPrice(), worth);
	}
	
	@Test
	public void testTransfer(){
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		//  ID       NAME        SURNAME        JERSEY_NR  	TYPE   POS  OFFENSIVE   DEFENSIVE  STAMINA       PRICE
		Player one    = new Player	(1, 	"Neo", 		"Keeper", 		(byte)  1, 	 0, 	0, 	(byte)  20, (byte) 69, (byte) 56, 	 1000);
		Schedule s = Scheduler.scheduler(leaguee);
		Game g = new Game(1, "game", 0, 1, 0, 0, s);
		TransferAlgorithm t = new TransferAlgorithm(g);
		t.AddPlayer(one);
		ArrayList<Player> ar = new ArrayList();
		ar.add(one);
		assertEquals(t.getTransferringplayers(), ar);
		
		
	}
}
