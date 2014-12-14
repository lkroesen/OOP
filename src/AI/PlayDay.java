package AI;

import java.util.ArrayList;

import model.Match;

public class PlayDay 
{
	
	private ArrayList<Match> Matches;
	private String Day;
	
	public PlayDay()
	{}
	
	public PlayDay(ArrayList<Match> matches)
	{
		Matches = matches;
	}
	
	public PlayDay(ArrayList<Match> matches, String day)
	{
		this(matches);
		Day = day;
	}
	
	public ArrayList<Match> getMatches() {
		return Matches;
	}
	public void setMatches(ArrayList<Match> matches) {
		this.Matches = matches;
	}
	public String getDay() {
		return Day;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayDay other = (PlayDay) obj;
		if (Day == null) {
			if (other.Day != null)
				return false;
		} else if (!Day.equals(other.Day))
			return false;
		if (Matches == null) {
			if (other.Matches != null)
				return false;
		} else if (!Matches.equals(other.Matches))
			return false;
		return true;
	}

	public void setDay(String day) {
		Day = day;
	}

	@Override
	public String toString() {
		return "PlayDay [Matches=" + Matches + ", Day=" + Day
				+ ", getMatches()=" + getMatches() + ", getDay()=" + getDay()
				+ "]";
	}
	
	
}
