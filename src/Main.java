import java.io.IOException;
import java.util.ArrayList;

import model.Game;
import model.League;
import model.Match;
import model.Team;

import org.xml.sax.SAXException;

import AI.PlayMatch;
import AI.Schedule;
import AI.Scheduler;
import xml.XML;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	//test
	public XML xml;
	public static void main(String[] args) throws SAXException, IOException, Exception {
		// Do not touch this please <START>
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League leaguee = new League(0, "Eredivisie", "Nederland", teams);
		Schedule s = Scheduler.scheduler(leaguee);
		// Do not touch this please </START>

		
		XML xml = new XML("teams.xml");
		Game game = xml.parseGame();
		
		int at = 0;
		int def = 0;
		League league = game.getLeagues().get(0);
		Team team1 = league.getTeams().get(0);
		Team team2 = league.getTeams().get(1);
		int x = 0;
		int aantalkeer = 1000;
		Match match = new Match(1,1,team1,team2);
		while(x<aantalkeer){
			String tp[] = PlayMatch.play(match).split(",");
			at += Integer.parseInt(tp[0]);
			def += Integer.parseInt(tp[1]);
			x++;
		}
		System.out.println("ajax - feyenoord (gemmideld)");
		System.out.println((double)at/aantalkeer + "-" + (double)def/aantalkeer);
		System.out.println("ajax - feyenoord (totaal)");
		System.out.println(at + "-" + def);
		System.out.println(xml.parseGame());
		
	}

}
