package model;

import AI.Schedule;

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
	private static int currentDay;
	private static int currentTeam;
	private static int currentLeague;
	private static int curentPlayRound;
	private ArrayList<League> leagues;
	private ArrayList<Transfer> transfers;
	private ArrayList<Match> matches;
	private Schedule schedule;

	public Game(int id, String name, int currentDay, int currentTeam, int currentLeague, int currentPlayRound, Schedule schedule) {
		super();
		this.id = id;
		this.name = name;
		Game.currentDay = currentDay;
		Game.currentTeam = currentTeam;
		Game.currentLeague = currentLeague;
		Game.curentPlayRound = currentPlayRound;

		this.schedule = schedule;

		leagues = new ArrayList<League>();
		transfers = new ArrayList<Transfer>();
		matches = new ArrayList<Match>();
	}

	public static int getCurentPlayRound() {
		return curentPlayRound;
	}

	public static void setCurentPlayRound(int curentPlayRound) {
		Game.curentPlayRound = curentPlayRound;
	}

	public static int getCurrentLeague() {
		return currentLeague;
	}

	public static void setCurrentLeague(int currentLeague) {
		Game.currentLeague = currentLeague;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
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

	public static void setCurrentDay(int currentDay) {
		Game.currentDay = currentDay;
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

	public void setId(int id) {
		this.id = id;
	}

	public static void setCurrentTeam(int currentTeam) {
		Game.currentTeam = currentTeam;
	}

	public ArrayList<Transfer> getTransfers() {
		return transfers;
	}

	public void setTransfers(ArrayList<Transfer> transfers) {
		this.transfers = transfers;
	}

	public ArrayList<Match> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
	}

	@Override
	public String toString() {
		return "Game{" +
				"id=" + id +
				", name='" + name + '\'' +
				", leagues=" + leagues +
				", transfers=" + transfers +
				", matches=" + matches +
				", schedule=" + schedule +
				'}';
	}
}