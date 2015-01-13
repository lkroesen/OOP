package AI;

import java.util.ArrayList;
import java.util.Scanner;

import model.Match;
import model.Team;

public class Betting 
{
	private double rate1;
	private double rate2;
	
	// Team IDs
	private int team1;
	private int team2;
	
	private String result;
	
	
	public Betting(double rate1, double rate2, int team1, int team2, String result) 
	{
		this.rate1 = rate1;
		this.rate2 = rate2;
		this.team1 = team1;
		this.team2 = team2;
		this.result = result;
	}	

	public String toString()
	{
		return "[" + team1 + " Rate: " + rate1 + " " + team2 + " Rate: " + rate2 + " Predicted Result: " + result + "]";
	}
	
	
	public double getRate1() {
		return rate1;
	}

	public void setRate1(double rate1) {
		this.rate1 = rate1;
	}

	public double getRate2() {
		return rate2;
	}

	public void setRate2(double rate2) {
		this.rate2 = rate2;
	}

	public int getTeam1() {
		return team1;
	}

	public void setTeam1(int team1) {
		this.team1 = team1;
	}

	public int getTeam2() {
		return team2;
	}

	public void setTeam2(int team2) {
		this.team2 = team2;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Betting other = (Betting) obj;
		if (Double.doubleToLongBits(rate1) != Double
				.doubleToLongBits(other.rate1))
			return false;
		if (Double.doubleToLongBits(rate2) != Double
				.doubleToLongBits(other.rate2))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (!(this.team1 == other.team1))
			return false;
		if (!(this.team2 == other.team2))
			return false;
		return true;
	}
	
	/************************************************************************
	 * 																		*
	 *                             STATIC METHODS							*
	 * 																		*
	 * **********************************************************************/

	// make a bet, and return it
	/**
	 * Betting before matches take place, generates ONE bet, call multiple times for multiple bets
	 * @param Budget
	 * Input the budget of the team betting ( Budget.getBudgetByTeam(Team) )
	 * @param s
	 * Input the Schedule
	 * @param c_week
	 * Input the week for which the bet is to be generated
	 * @return
	 */
	public static ArrayList<Betting> Before_Match (Schedule s, int c_week)
	{
		// Get a list of upcoming matches this week
		// check which matches have been played
		
		// check which all still will take place
		
		// Creates an object, that has matches that can be bet on.
		ArrayList<Match> ListOfBets = GenerateBetsPerWeek(c_week, s.getS());
		ArrayList<Betting> BetList = new ArrayList<Betting>();
		
		for (int c = 0; c < ListOfBets.size(); c++)
		{
			// Count winners
			int home = 0;
			int out = 0;
			int c_home = 0;
			int c_out = 0;
			// Simulate upcoming matches a couple of times
			
			for (int d = 0; d < 1000; d++)
			{
				String a[] = PlayMatch.play(ListOfBets.get(c)).split(",");
				home = Integer.parseInt(a[0]);
				out = Integer.parseInt(a[1]);
				
				// home won
				if (home > out)
					c_home++;
				
				// out won
				if (out > home)
					c_out++;
				
			}
			System.out.println(out);
			System.out.println(home);
			
			double rateone = 0;
			double ratetwo = 0;
			
			// amount of ties
			int ties = 1000 - (c_home + c_out);
			
			// This means that this match is likely to tie
			if (ties > c_home && ties > c_out)
			{
				rateone = (double) 1.5;
				ratetwo = (double) 1.5;
				Betting bet = new Betting(rateone, ratetwo, ListOfBets.get(c).getTeam_home().getId(), ListOfBets.get(c).getTeam_away().getId(), "tie");
				BetList.add(bet);
			}
			
			else if (c_home > c_out)
			{
				double e = ties / (double) 1000;
				rateone = (e) + (c_home / (double) 1000);
				ratetwo = ((double) 1.5 + e) + (c_out / (double) 1000);
				System.out.println("Wining home-rate: " + rateone);
				System.out.println("Winning out-rate: " + ratetwo);
				
				
				Betting bet = new Betting(rateone, ratetwo, ListOfBets.get(c).getTeam_home().getId(), ListOfBets.get(c).getTeam_away().getId(), "home");
				BetList.add(bet);
			}
			
			else if (c_out > c_home)
			{
				double e = ties / (double) 1000;
				rateone = (e) + (c_out / (double) 1000);
				ratetwo = ((double) 1.5 + e) + (c_home / (double) 1000);
				System.out.println("Wining out-rate: " + rateone);
				System.out.println("Winning home-rate: " + ratetwo);
				
				Betting bet = new Betting(rateone, ratetwo, ListOfBets.get(c).getTeam_away().getId(), ListOfBets.get(c).getTeam_home().getId(), "away");
				BetList.add(bet);
			}
		
		}
		
		return BetList;
	}
	
	public static String getTeamNameById(int id, ArrayList<Team> tl)
	{
		for (int c = 0; c < tl.size(); c++)
			if (tl.get(c).getId() == id)
				return tl.get(c).getName();
		return "";
	}
	
	/**
	 * Method for making a bet
	 * @param BL
	 * Input the betting list
	 * @param Money
	 * Input the amount of money the player has
	 * @param TeamList
	 * Input the current list of all teams
	 * @return
	 * Returns the made bet
	 */
	public static Bet Bet_Maker(ArrayList<Betting> BL, long Money, ArrayList<Team> TeamList)
	{
		// Show users bets he can make
		for (int c = 0; c < BL.size(); c++)
			System.out.println(c+1 + ") " + BL.get(c).toString()); 
		
		// Select a match to bet on
		Scanner sc = new Scanner(System.in);
		String n = sc.next();
		
		String winner = "none";
		long bet_amount = (long) 0;
		Double rate = 0.00;
		int win = 0;
		
		int a = (int) n.charAt(0) - 49;
		System.out.println("Selected: " + a);
		
		// This whole system is just for testing
		
		if (n.charAt(0) == '1')
		{
			System.out.println("Select a team:\n" + "1) " + getTeamNameById(BL.get(a).getTeam1(), TeamList) + "\n2) " + getTeamNameById(BL.get(a).getTeam2(), TeamList));
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = getTeamNameById(BL.get(a).getTeam1(), TeamList);
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = getTeamNameById(BL.get(a).getTeam2(), TeamList);
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Money)
				bet_amount = Money;
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;	
		}
		
		else if (n.charAt(0) == '2')
		{
			System.out.println("Select a team:\n" + "1) " + getTeamNameById(BL.get(a).getTeam1(), TeamList) + "\n2) " + getTeamNameById(BL.get(a).getTeam2(), TeamList));
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = getTeamNameById(BL.get(a).getTeam1(), TeamList);
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = getTeamNameById(BL.get(a).getTeam2(), TeamList);
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Money)
				bet_amount = Money;
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
		
		else if (n.charAt(0) == '3')
		{
			System.out.println("Select a team:\n" + "1) " + getTeamNameById(BL.get(a).getTeam1(), TeamList) + "\n2) " + getTeamNameById(BL.get(a).getTeam2(), TeamList));
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = getTeamNameById(BL.get(a).getTeam1(), TeamList);
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = getTeamNameById(BL.get(a).getTeam2(), TeamList);
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Money)
				bet_amount = Money;
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
		
		else if (n.charAt(0) == '4')
		{
			System.out.println("Select a team:\n" + "1) " + getTeamNameById(BL.get(a).getTeam1(), TeamList) + "\n2) " + getTeamNameById(BL.get(a).getTeam2(), TeamList));
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = getTeamNameById(BL.get(a).getTeam1(), TeamList);
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = getTeamNameById(BL.get(a).getTeam2(), TeamList);
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Money)
				bet_amount = Money;
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
		
		else if (n.charAt(0) == '5')
		{
			System.out.println("Select a team:\n" + "1) " + getTeamNameById(BL.get(a).getTeam1(), TeamList) + "\n2) " + getTeamNameById(BL.get(a).getTeam2(), TeamList));
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = getTeamNameById(BL.get(a).getTeam1(), TeamList);
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = getTeamNameById(BL.get(a).getTeam2(), TeamList);
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Money)
				bet_amount = Money;
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
		
		else if (n.charAt(0) == '6')
		{
			System.out.println("Select a team:\n" + "1) " + getTeamNameById(BL.get(a).getTeam1(), TeamList) + "\n2) " + getTeamNameById(BL.get(a).getTeam2(), TeamList));
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = getTeamNameById(BL.get(a).getTeam1(), TeamList);
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = getTeamNameById(BL.get(a).getTeam2(), TeamList);
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Money)
				bet_amount = Money;
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
		
		else if (n.charAt(0) == '7')
		{
			System.out.println("Select a team:\n" + "1) " + getTeamNameById(BL.get(a).getTeam1(), TeamList) + "\n2) " + getTeamNameById(BL.get(a).getTeam2(), TeamList));
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = getTeamNameById(BL.get(a).getTeam1(), TeamList);
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = getTeamNameById(BL.get(a).getTeam2(), TeamList);
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Money)
				bet_amount = Money;
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
		
		else if (n.charAt(0) == '8')
		{
			System.out.println("Select a team:\n" + "1) " + getTeamNameById(BL.get(a).getTeam1(), TeamList) + "\n2) " + getTeamNameById(BL.get(a).getTeam2(), TeamList));
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = getTeamNameById(BL.get(a).getTeam1(), TeamList);
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = getTeamNameById(BL.get(a).getTeam2(), TeamList);
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Money)
				bet_amount = Money;
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
		
		else if (n.charAt(0) == '9')
		{
			System.out.println("Select a team:\n" + "1) " + getTeamNameById(BL.get(a).getTeam1(), TeamList) + "\n2) " + getTeamNameById(BL.get(a).getTeam2(), TeamList));
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = getTeamNameById(BL.get(a).getTeam1(), TeamList);
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = getTeamNameById(BL.get(a).getTeam2(), TeamList);
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Money)
				bet_amount = Money;
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
			
		Bet b = new Bet(bet_amount, rate, win, winner);
		
		// Don't forget to change the amount of money in budget!
		return b;
	}
	
	public static long After_Match (long Money, Bet bet, int TeamIDWon)
	{
		// Check if the team that won is the team that was bet on
		if (bet.getS_won() == TeamIDWon)
			Money = (long) (bet.getMoney_bet() * bet.getRate());
				
		return Money;
	}
	
	public static ArrayList<Match> GenerateBetsPerWeek(int c_week, ArrayList<PlayRound> S)
	{
		ArrayList<Match> ListOfBets = new ArrayList<Match>();
		
		// Check which days have been played
		// Can only bet if none of the matches of that day have taken place
		
		// Check if Friday has not been played
		if (!DayPlayed(S.get(c_week).getFriday())) 
			ListOfBets.add(S.get(c_week).getFriday().getMatches().get(0));
			
			
			
		// Check if Saturday has not been played
		if (!DayPlayed(S.get(c_week).getSaturday()))
			for (int c = 0; c < S.get(c_week).getSaturday().getMatches().size(); c++)
				ListOfBets.add(S.get(c_week).getSaturday().getMatches().get(c));
			
			
		// Check if Sunday has not been played
		if (!DayPlayed(S.get(c_week).getSunday()))
			for (int c = 0; c < S.get(c_week).getSunday().getMatches().size(); c++)
				ListOfBets.add(S.get(c_week).getSaturday().getMatches().get(c));
		
		return ListOfBets;
	}
	
	public static boolean DayPlayed(PlayDay Day)
	{
		if (Day.getMatches().get(0).getResult() == null)
			return false;
		
		return true;
	}
}
