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
	public void setDay(String day) {
		Day = day;
	}

}
