package model;

import java.util.ArrayList;

/**
 * Represents a league: A collection of teams in an area
 * @author hidde
 *
 */
public class League {

	private int id;
	private String name;
	private String country;
	private ArrayList<Team> teams;
	
	public League(int id, String name, String country, ArrayList<Team> teams) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.teams = teams;
	}
	public League(int id, String name, String country) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.teams = new ArrayList<Team>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public ArrayList<Team> getTeams() {
		return teams;
	}
	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}
	public void addTeam(Team t) {
		this.teams.add(t);
	}
	@Override
	public String toString() {
		return "League [id=" + id + ", name=" + name + ", country=" + country
				+ ", teams=" + teams + "]";
	}
	
	public Team getTeamById(int id)
	{
		for(int c = 0; c < this.teams.size(); c++)
		{
			if (id == this.teams.get(c).getId())
				return this.teams.get(c);
		}
		return teams.get(0);
	}
	
	public Team getTeamByName(String name)
	{
		for(int c = 0; c < this.teams.size(); c++)
		{
			if (name.equals(this.teams.get(c).getName()))
				return this.teams.get(c);
		}
		return teams.get(0);
	}
	
	public boolean equals(Object obj)
	{
		if (obj instanceof League)
		{
			League that = (League) obj;
			if (		this.getName() 	== 	that.getName() 
					&&	this.getId() 	== 	that.getId()
					&&	this.getCountry() ==	that.getCountry()
					&&	this.getTeams()	==	that.getTeams())
				return true;
		}
		
		return false;
		
	}
}