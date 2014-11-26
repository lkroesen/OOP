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
	private ArrayList<Match> matches;
	private ArrayList<Transfer> transfers;

	public int calcPoints(Team team) {
		int points = 0;
		for (int i = 0; i < matches.size(); i++) {
			if (team.equals(matches.get(i).getWinningTeam())) {
				points = points + 3;
			}
			if (team.equals(matches.get(i).getTeamAway())
					|| team.equals(matches.get(i).getTeamHome())
					&& matches.get(i).getResult() == MatchResult.TIE) {
				points++;
			}
		}
		return points;
	}

	public Game(int id, String name, int currentDay, int currentTeam) {
		super();
		this.id = id;
		this.name = name;
		this.currentDay = currentDay;
		this.currentTeam = currentTeam;
		
		matches = new ArrayList<Match>();
		leagues = new ArrayList<League>();
		transfers = new ArrayList<Transfer>();
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

	public ArrayList<Match> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", currentDay="
				+ currentDay + ", currentTeam=" + currentTeam + ", leagues="
				+ leagues + ", matches=" + matches + ", transfers=" + transfers
				+ "]";
	}
	
	
}