import java.io.IOException;
import java.util.ArrayList;

import model.League;
import model.Team;

import org.xml.sax.SAXException;

import AI.Scheduler;
import xml.XML;


public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	//test

	public static void main(String[] args) throws SAXException, IOException, Exception {
		// Do not touch this please <START>
		ArrayList<Team> teams = new ArrayList<Team>();
		for (int c = 0; c < 18; c++){String a = "team " + c;Team team = new Team(c, a);teams.add(c, team);}
		League league = new League(0, "Eredivisie", "Nederland", teams);
		Scheduler.Scheduler(league);
		// Do not touch this please </START>
		
		XML xml = new XML("default.xml");
		
		System.out.println(xml.parseGame());
		
	}

}
