package AI;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.NEW;

import model.Match;

public class PlayDay 
{
	
	private ArrayList<Match> Matches = new ArrayList<Match>();
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

	public void addMatch(Match match){
		Matches.add(match);
	}

	public String getDay() {
		return Day;
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
