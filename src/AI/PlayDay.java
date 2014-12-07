package AI;

import java.util.ArrayList;
import model.Match;

public class PlayDay 
{
	
	private ArrayList<Match> Matches;
	private int Day;
	private int Month;
	private int Year;
	
	public PlayDay()
	{}
	
	public PlayDay(ArrayList<Match> matches)
	{
		Matches = matches;
	}
	
	public PlayDay(ArrayList<Match> matches, int day, int month, int year)
	{
		this(matches);
		Day = day;
		Month = month;
		Year = year;
	}
	
	public ArrayList<Match> getMatches() {
		return Matches;
	}
	public void setMatches(ArrayList<Match> matches) {
		this.Matches = matches;
	}
	public int getDay() {
		return Day;
	}
	public void setDay(int day) {
		Day = day;
	}
	public int getMonth() {
		return Month;
	}
	public void setMonth(int month) {
		Month = month;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
}
