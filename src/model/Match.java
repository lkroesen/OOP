package model;

import java.util.ArrayList;

public class Match {

private int id;
private int day;

ArrayList<Event> events_home;
ArrayList<Event> events_away;

private Team team_home;
private Team team_away;

private String result;
	
	public Match(int id, int day, Team team_home, Team team_away) {
		this.id = id;
		this.day = day;
		
		this.team_home = team_home;
		this.team_away = team_away;

		events_home = new ArrayList<Event>();
		events_away = new ArrayList<Event>();
	}
	
	public Match(int id, int day){
		this.id = id;
		this.day = day;

		events_home = new ArrayList<Event>();
		events_away = new ArrayList<Event>();
	}
	
	public void addEventHome(Event e){
		events_home.add(e);
	}
	
	public void addEventAway(Event e){
		events_away.add(e);
	}

	public int getId() {
		return id;
	}

	public int getDay() {
		return day;
	}

	public ArrayList<Event> getEvents_home() {
		return events_home;
	}

	public ArrayList<Event> getEvents_away() {
		return events_away;
	}

	public Team getTeam_home() {
		return team_home;
	}

	public Team getTeam_away() {
		return team_away;
	}

	public void setTeam_home(Team team_home) {
		this.team_home = team_home;
	}

	public void setTeam_away(Team team_away) {
		this.team_away = team_away;
	}

	public void setResult(String re){
		this.result = re;
	}
	
	public String getResult()
	{
		return result;
	}
	
	@Override
	public String toString() {
		return "Match [id=" + id + ", day=" + day + ", events_home="
				+ events_home + ", events_away=" + events_away + ", team_home="
				+ team_home + ", team_away=" + team_away + "]";
	}
	
	

}

