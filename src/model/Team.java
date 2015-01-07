package model;

import java.util.ArrayList;

/**
 * Represents a Team object with players
 * @author hidde
 *
 */
public class Team {
	
	private int id;
	private String name;
	private ArrayList<Player> players;
	private long budget;
	
	public long getBudget(){
		return budget;
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
	public void setBudget(long i){
		this.budget = i;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public void addPlayer(Player p) {
		this.players.add(p);
	}
	public void delPlayer(Player p){
		this.players.remove(p);
	}
	public Team(int id, String name, ArrayList<Player> players) {
		super();
		this.id = id;
		this.name = name;
		this.players = players;
	}
	
	public Team(int id, String name, ArrayList<Player> players, long budget) {
		super();
		this.id = id;
		this.name = name;
		this.players = players;
		this.budget = budget;
	}
	
	public Team(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.players = new ArrayList<Player>();
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", players=" + players
				+ "]";
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		
		if (obj instanceof Team)
		{
			Team that = (Team) obj;
			if (		this.getId() 	== 	that.getId() 
					&&	this.getName() 	== 	that.getName()
					)
				return true;
		}
		
		return false;
		
	}	

}