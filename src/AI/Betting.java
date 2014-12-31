package AI;

import java.util.ArrayList;
import java.util.Scanner;

import model.Budget;
import model.Match;
import model.Team;

public class Betting 
{
	private double rate1;
	private double rate2;
	
	private Team team1;
	private Team team2;
	
	private String result;
	
	
	public Betting(double rate1, double rate2, Team team1, Team team2, String result) 
	{
		this.rate1 = rate1;
		this.rate2 = rate2;
		this.team1 = team1;
		this.team2 = team2;
		this.result = result;
	}	

	public String toString()
	{
		return "[" + team1.getName() + " Rate: " + rate1 + " " + team2.getName() + " Rate: " + rate2 + " Predicted Result: " + result + "]";
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

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
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
		if (team1 == null) {
			if (other.team1 != null)
				return false;
		} else if (!team1.equals(other.team1))
			return false;
		if (team2 == null) {
			if (other.team2 != null)
				return false;
		} else if (!team2.equals(other.team2))
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
				Betting bet = new Betting(rateone, ratetwo, ListOfBets.get(c).getTeam_home(), ListOfBets.get(c).getTeam_away(), "tie");
				BetList.add(bet);
			}
			
			else if (c_home > c_out)
			{
				double e = ties / (double) 1000;
				rateone = (e) + (c_home / (double) 1000);
				ratetwo = ((double) 1.5 + e) + (c_out / (double) 1000);
				System.out.println("Wining home-rate: " + rateone);
				System.out.println("Winning out-rate: " + ratetwo);
				
				
				Betting bet = new Betting(rateone, ratetwo, ListOfBets.get(c).getTeam_home(), ListOfBets.get(c).getTeam_away(), "home");
				BetList.add(bet);
			}
			
			else if (c_out > c_home)
			{
				double e = ties / (double) 1000;
				rateone = (e) + (c_out / (double) 1000);
				ratetwo = ((double) 1.5 + e) + (c_home / (double) 1000);
				System.out.println("Wining out-rate: " + rateone);
				System.out.println("Winning home-rate: " + ratetwo);
				
				Betting bet = new Betting(rateone, ratetwo, ListOfBets.get(c).getTeam_away(), ListOfBets.get(c).getTeam_home(), "away");
				BetList.add(bet);
			}
		
		}
		
		return BetList;
	}
	
	public static Bet Bet_Maker(ArrayList<Betting> BL, Budget Bu, Team P_Team)
	{
		// Show users bets he can make
		for (int c = 0; c < BL.size(); c++)
			System.out.println(c+1 + ") " + BL.get(c).toString()); 
		
		// Select a match to bet on
		Scanner sc = new Scanner(System.in);
		String n = sc.next();
		
		String winner = "none";
		Long bet_amount = (long) 0;
		Double rate = 0.00;
		Team win = null;
		
		int a = (int) n.charAt(0) - 49;
		System.out.println("Selected: " + a);
		
		// This whole system is just for testing
		
		if (n.charAt(0) == '1')
		{
			System.out.println("Select a team:\n" + "1) " + BL.get(a).getTeam1().getName() + "\n2) " + BL.get(a).getTeam2().getName());
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = BL.get(a).getTeam1().getName();
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = BL.get(a).getTeam2().getName();
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Bu.getBudgetByTeam(P_Team))
				bet_amount = Bu.getBudgetByTeam(P_Team);
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;	
		}
		
		else if (n.charAt(0) == '2')
		{
			System.out.println("Select a team:\n" + "1) " + BL.get(a).getTeam1().getName() + "\n2) " + BL.get(a).getTeam2().getName());
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = BL.get(a).getTeam1().getName();
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = BL.get(a).getTeam2().getName();
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Bu.getBudgetByTeam(P_Team))
				bet_amount = Bu.getBudgetByTeam(P_Team);
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
		
		else if (n.charAt(0) == '3')
		{
			System.out.println("Select a team:\n" + "1) " + BL.get(a).getTeam1().getName() + "\n2) " + BL.get(a).getTeam2().getName());
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = BL.get(a).getTeam1().getName();
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = BL.get(a).getTeam2().getName();
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Bu.getBudgetByTeam(P_Team))
				bet_amount = Bu.getBudgetByTeam(P_Team);
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
		
		else if (n.charAt(0) == '4')
		{
			System.out.println("Select a team:\n" + "1) " + BL.get(a).getTeam1().getName() + "\n2) " + BL.get(a).getTeam2().getName());
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = BL.get(a).getTeam1().getName();
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = BL.get(a).getTeam2().getName();
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Bu.getBudgetByTeam(P_Team))
				bet_amount = Bu.getBudgetByTeam(P_Team);
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
		
		else if (n.charAt(0) == '5')
		{
			System.out.println("Select a team:\n" + "1) " + BL.get(a).getTeam1().getName() + "\n2) " + BL.get(a).getTeam2().getName());
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = BL.get(a).getTeam1().getName();
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = BL.get(a).getTeam2().getName();
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Bu.getBudgetByTeam(P_Team))
				bet_amount = Bu.getBudgetByTeam(P_Team);
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
		
		else if (n.charAt(0) == '6')
		{
			System.out.println("Select a team:\n" + "1) " + BL.get(a).getTeam1().getName() + "\n2) " + BL.get(a).getTeam2().getName());
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = BL.get(a).getTeam1().getName();
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = BL.get(a).getTeam2().getName();
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Bu.getBudgetByTeam(P_Team))
				bet_amount = Bu.getBudgetByTeam(P_Team);
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
		
		else if (n.charAt(0) == '7')
		{
			System.out.println("Select a team:\n" + "1) " + BL.get(a).getTeam1().getName() + "\n2) " + BL.get(a).getTeam2().getName());
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = BL.get(a).getTeam1().getName();
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = BL.get(a).getTeam2().getName();
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Bu.getBudgetByTeam(P_Team))
				bet_amount = Bu.getBudgetByTeam(P_Team);
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
		
		else if (n.charAt(0) == '8')
		{
			System.out.println("Select a team:\n" + "1) " + BL.get(a).getTeam1().getName() + "\n2) " + BL.get(a).getTeam2().getName());
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = BL.get(a).getTeam1().getName();
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = BL.get(a).getTeam2().getName();
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Bu.getBudgetByTeam(P_Team))
				bet_amount = Bu.getBudgetByTeam(P_Team);
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
		
		else if (n.charAt(0) == '9')
		{
			System.out.println("Select a team:\n" + "1) " + BL.get(a).getTeam1().getName() + "\n2) " + BL.get(a).getTeam2().getName());
			n = sc.next();
			if (n.charAt(0) == '1')
			{
				win = BL.get(a).getTeam1();
				rate = BL.get(a).getRate1();
				winner = BL.get(a).getTeam1().getName();
			}
			
			else
			{
				win = BL.get(a).getTeam2();
				rate = BL.get(a).getRate2();
				winner = BL.get(a).getTeam2().getName();
			}

			System.out.println("How much money do you want to bet?" + "\n>");
			bet_amount = sc.nextLong();
			if (bet_amount > Bu.getBudgetByTeam(P_Team))
				bet_amount = Bu.getBudgetByTeam(P_Team);
			else if(bet_amount < (long) 0)
				bet_amount = (long) 0;
		}
			
		Bet b = new Bet(bet_amount, rate, win, winner);
		
		// Don't forget to change the amount of money in budget!
		return b;
	}
	
	public static Budget After_Match (Budget Budget, Bet bet, Team Won, Team P_Team)
	{
		// Check if the team that won is the team that was bet on
		if (bet.getS_won().equals(Won))
		{
			long money = Budget.getBudgetByTeam(P_Team);
			money += bet.getMoney_bet() * bet.getRate();		
			Budget.setBudgetByTeam(P_Team, money); 
		}
				
		return Budget;
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
	
	public static Boolean DayPlayed(PlayDay Day)
	{
		if (Day.getMatches().get(0).getResult() == null)
			return false;
		
		return true;
	}
}
