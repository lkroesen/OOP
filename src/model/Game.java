package model;

import java.util.ArrayList;

/**
 * Represents a Game object with leagues and played matches. This holds all the
 * game data necessary for one instance of the game to function.
 * 
 * @author hidde
 *
 */
public class Game {

	private int id;
	private String name;
	private int currentDay;
	private int currentTeam;
	private ArrayList<League> leagues;
	private ArrayList<Transfer> transfers;
	private ArrayList<Match> matches;

	public Game(int id, String name, int currentDay, int currentTeam) {
		super();
		this.id = id;
		this.name = name;
		this.currentDay = currentDay;
		this.currentTeam = currentTeam;
		
		leagues = new ArrayList<League>();
		transfers = new ArrayList<Transfer>();
		matches = new ArrayList<Match>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentDay() {
		return currentDay;
	}
	
	public int getCurrentTeam(){
		return currentTeam;
	}

	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}

	public ArrayList<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(ArrayList<League> leagues) {
		this.leagues = leagues;
	}
	
	public void addLeague(League l) {
		this.leagues.add(l);
	}
	
	public void addTransfer(Transfer t) {
		this.transfers.add(t);
	}
	
	public void addMatch(Match m) {
		this.matches.add(m);
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", currentDay="
				+ currentDay + ", currentTeam=" + currentTeam + ", leagues="
				+ leagues + ", transfers=" + transfers + ", matches=" + matches
				+ "]";
	}
	
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		
		if (obj instanceof Game)
		{
			Game that = (Game) obj;
			if (		this.getName() 	== 	that.getName() 
					&&	this.getCurrentDay() 	== 	that.getCurrentDay()
					&&	this.getId() 		==	that.getId()
					&&	this.getCurrentTeam()	==	that.getCurrentTeam())
				return true;
		}
		
		return false;
		
	}
	
	
	
}