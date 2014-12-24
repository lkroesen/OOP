package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Budget 
{
	// Array Location corrosponds to team at location
	private long[] Budget;
	private ArrayList<Team> Team;
	
	public Budget(ArrayList<Team> Team) 
	{
		this.Team = Team;
		Budget = new long[Team.size()];
	}
	
	public void setBudgetByTeam(Team Team, Long Budget)
	{
		for (int c = 0; c < this.Team.size(); c++)
			if (this.Team.get(c).equals(Team))
				this.Budget[c] = Budget;
	}

	public long getBudgetByTeam(Team Team)
	{
		for (int c = 0; c < this.Team.size(); c++)
			if (this.Team.get(c).equals(Team))
				return this.Budget[c];
		
		return -1;
	}

	public ArrayList<Team> getTeam() {
		return Team;
	}

	@Override
	public String toString() 
	{
		String a = "Budget [";
		
		for (int c = 0; c < this.Team.size(); c++)
			a += "Team: " + this.Team.get(c).toString() + " Budget: " + this.Budget[c];
		
		a += "]";
		return a;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Budget other = (Budget) obj;
		if (!Arrays.equals(Budget, other.Budget))
			return false;

		return true;
	}
	
	
	
}
