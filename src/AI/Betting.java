package AI;

import java.util.ArrayList;
import model.Budget;
import model.Match;

public class Betting 
{
	// make a bet, and return it
	/**
	 * Betting before matches take place, generates ONE bet, call multiple times for multiple bets
	 * @param Budget
	 * Input the budget of the team betting ( Budget.getBudgetByTeam(Team) )
	 * @param s
	 * Input the Schedule
	 * @return
	 */
	public static Bet Before_Match (Long Budget, Schedule s)
	{
		// Get a list of upcoming matches this week
		// check which matches have been played
		
		// check which all still will take place
		
		// Generate a list of weeks that have been played
		boolean[] played = s.WeeksPlayed();
		
		int c_week = 0;
		
		for (int c = 0; c < played.length; c++)
			if (played[c] == false)
				c_week = c;
		
		// Creates an object, that has matches that can be bet on. ? ArrayList<Match> ?
		GenerateBetsPerWeek(c_week, s.getS(), Budget);
		
		Bet placeholder = new Bet(0,0.1,null,null);
		return placeholder;
	}
	
	public static Budget After_Match (Budget Budget, Bet bet)
	{
		return Budget;
	}
	
	public static ArrayList<Match> GenerateBetsPerWeek(int c_week, ArrayList<PlayRound> S, Long Budget)
	{
		ArrayList<Match> ListOfBets = new ArrayList<Match>();
		
		// Check which days have been played
		// Can only bet if none of the matches of that day have taken place
		
		// Check if Friday has not been played
		if (!DayPlayed(S.get(c_week).getFriday()))
			//Get matches that can be bet on
			
			
			
		// Check if Saturday has not been played
		if (!DayPlayed(S.get(c_week).getSaturday()))
			// Get matches that can be bet on
			
			
			
		// Check if Sunday has not been played
		if (!DayPlayed(S.get(c_week).getSunday()))
			return ListOfBets;
		
		return ListOfBets;
	}
	
	public static Boolean DayPlayed(PlayDay Day)
	{
		if (Day.getMatches().get(0).getResult() == null)
			return false;
		
		return true;
	}
}
