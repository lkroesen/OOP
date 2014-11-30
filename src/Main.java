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
		ArrayList<Team> teams = new ArrayList<Team>();
		
		for (int c = 0; c < 18; c++)
		{
			String a = "team " + c;
			Team team = new Team(c, a);
			teams.add(c, team);
		}
		
		League league = new League(0, "Eredivisie", "Nederland", teams);
		
		
		XML xml = new XML("default.xml");
		
		System.out.println(xml.parseGame());
		
		Scheduler.Scheduler(league);
	}

}
