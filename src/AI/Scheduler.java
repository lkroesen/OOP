package AI;

import java.util.ArrayList;
import java.util.Random;

import model.League;
import model.Match;
import model.Team;

// Algorithm to schedule all teams in matches at the start of the game
public class Scheduler 
{
	/**
	 * The core of the Scheduler, WARNING: !!! WILL ONLY WORK FOR 18 TEAMS !!!
	 * @param league
	 * Input a league for which the schedule is to be generated
	 * @return
	 * Returns a Schedule
	 */
	public static Schedule scheduler(League league)
	{
		//This makes it so I don't have to keep typing league.getTeams().size() 
		int TeamSize = league.getTeams().size();

		// Collect all the Team IDs and Team Names
		int[] TeamIDs;
		TeamIDs = new int[TeamSize];
		String[] TeamNames = new String[TeamSize];

		for (int c = 0; c < TeamSize; c++)
		{
			TeamIDs[c] = league.getTeams().get(c).getId();
			TeamNames[c] = league.getTeams().get(c).getName();
		}

		int[] Order = new int[TeamSize];

		// Pre-initialize the array so my method can be shorter
		for (int c = 0; c < TeamSize; c++)
			Order[c] = -1;
		Order = OrderRandomizer(TeamSize, Order);

		// Week 0/17
		int week = 0;
		Schedule s = new Schedule();
		// on week 17 (=16) we change the complete order again
		
		
		
		while (week < TeamSize-1)
		{
			// initial order
			if (week == 0)
				s.add(Gen(TeamSize, league, Order, week));
			else
			{
				Order = ClockShufflePlusPlus(Order, TeamSize);
				s.add(Gen(TeamSize, league, Order, week));
			}
			
			System.out.println("*/*/*/ WEEK: " + week + " \\*\\*\\*" );
			print(TeamSize, league, Order);
			
			week++;
			
			//failsafe
			if (week == (TeamSize-1))
				break;

			
		}
		
		// regenerate Order
		for (int g = 0; g < TeamSize; g++)
			Order[g] = -1;
		
		Order = OrderRandomizer(TeamSize, Order);
		
		// Weeks 18/34
		while (week < ((2*TeamSize)-2))
		{
			// inital order
			if (week == (TeamSize-1))
				s.add(Gen(TeamSize, league, Order, week));
			else
			{
				Order = ClockShufflePlusPlus(Order, TeamSize);
				s.add(Gen(TeamSize, league, Order, week));
			}
			
			System.out.println("*/*/*/ WEEK: " + week + " \\*\\*\\*" );
			print(TeamSize, league, Order);
			
			week++;
			
			//failsafe
			if (week == ((2*TeamSize)-2))
				break;
			

		}
		return s;
	}
	
	/**
	 * Check if the inputed int Array contains the inputed int 
	 * NOTE: do NOT use an array that has not been initialized!
	 * @param i
	 * Input an int[] Array
	 * @param r
	 * Input an int to be checked
	 * @return
	 * Returns true or false if it is contained within the array
	 */
	public static boolean contains(int[] i, int r)
	{
		for (int c = 0; c < i.length; c++)
		{
			if (i[c] == r)
				return true;
		}
		return false;
	}
	
	/**
	 * Creates a randomized order
	 * @param TeamSize
	 * Input the size of the team
	 * @param Order
	 * Input an int[] to be used
	 * @return
	 * Returns an int[] that has been randomized
	 */
	public static int[] OrderRandomizer(int TeamSize, int[] Order)
	{
		Random rng = new Random();
		int a = 0;
		while (a < TeamSize)
		{
			int r = rng.nextInt(TeamSize);
			
			if (contains(Order,r) == false)
			{
				Order[a] = r;
				a++;
			}
			
		}
		return Order;
	}
	
	public static int[] ClockShufflePlusPlus (int[] Old_Order, int TeamSize)
	{
		int[] New_Order;
		
		// If teams are even
		if (TeamSize % 2 == 0)
		{
			New_Order = new int[TeamSize];
			
			//Exceptions are declared
			New_Order[0] = Old_Order[0];
			
			New_Order[(TeamSize-1)] = Old_Order[(TeamSize/2) - 1];
			
			New_Order[1] = Old_Order[(TeamSize/2)];
			
			
			//Linear parts
			for (int c = 2; c < (TeamSize/2); c++)
				New_Order[c] = Old_Order[c-1];
			
			// Linear parts
			for (int c = (TeamSize/2); c < TeamSize-1; c++)
				New_Order[c] = Old_Order[c+1];
		}
		
		// If teams are uneven we need to add a bye
		else
		{
			int[] Temp_Order = new int[TeamSize+1];
			for (int c = 0; c < TeamSize; c++)
				Temp_Order[c] = Old_Order[c];
			Temp_Order[TeamSize] = -1;
			New_Order = ClockShufflePlusPlus(Temp_Order, TeamSize+1);
		}
		
		
		
		return New_Order;
	}
	
	
	/**
	 * Deprecated Clock Shuffle USE: ClockShufflePlusPlus
	 * @param Old_Order
	 * The order to be shuffled
	 * @param TeamSize
	 * The teamsize
	 * @return
	 * Returns a clockshuffled array
	 */
	@Deprecated
	public static int[] ClockShuffle(int[] Old_Order, int TeamSize)
	{
		int[] New_Order = new int[18];
		
		// Exception
		New_Order[0] = Old_Order[0];
		New_Order[17] = Old_Order[8];
		
		// Linear
		New_Order[8] = Old_Order[7];
		New_Order[7] = Old_Order[6];
		New_Order[6] = Old_Order[5];
		New_Order[5] = Old_Order[4];
		New_Order[4] = Old_Order[3];
		New_Order[3] = Old_Order[2];
		New_Order[2] = Old_Order[1];
		
		// Exception
		New_Order[1] = Old_Order[9];
		
		// Linear
		New_Order[9] = Old_Order[10];
		New_Order[10] = Old_Order[11];
		New_Order[11] = Old_Order[12];
		New_Order[12] = Old_Order[13];
		New_Order[13] = Old_Order[14];
		New_Order[14] = Old_Order[15];
		New_Order[15] = Old_Order[16];
		New_Order[16] = Old_Order[17];
		
		return New_Order;	
	}

	public static int AmountOfMatches(League le)
	{
		int number = 0;
		for (int c = 1; c < le.getTeams().size(); c++)
			number += c;
		number *= 2;

		return number;
		
	}
	
	/**
	 * Prints teams based on inputed data (works similar to Gen and was used for testing)
	 * @param TeamSize
	 * Input a teamsize
	 * @param league
	 * Input a league
	 * @param Order
	 * Input an order
	 */
	public static void print(int TeamSize, League league, int[] Order)
	{
		// first order our first matches
		String[] HomeTeam = new String[(TeamSize/2)];
		String[] OutTeam = new String[(TeamSize/2)];

		// Assign teams to Home and Out positions
		int c = 0;
		int d = (TeamSize/2);
		
		while (d < TeamSize)
		{
			HomeTeam[c] = league.getTeams().get(Order[c]).getName();
			OutTeam[c] = league.getTeams().get(Order[d]).getName();
			c++;
			d++;
		}
		
		for (int b = 0; b < ((TeamSize)/2); b++)
		{
			if (b == 0)
				System.out.println("Friday: " + HomeTeam[b] + " vs " + OutTeam[b]);
			if (b > 0 && b < 5)
				System.out.println("Saturday: " + HomeTeam[b] + " vs " + OutTeam[b]);
			if (b > 4)
				System.out.println("Sunday: " + HomeTeam[b] + " vs " + OutTeam[b]);
			
		}
		
		
	}
	
	/**
	 * Generates playrounds based on inputed data
	 * @param TeamSize
	 * Input the size of the team
	 * @param league
	 * Input a league object
	 * @param Order
	 * Input an order to be sorted with
	 * @param w
	 * Input week number
	 * @return
	 * Returns a generated Playround for inserted week
	 */
	public static PlayRound Gen(int TeamSize, League league, int[] Order, int w)
	{
		// first order our first matches
		// change 1
		PlayRound PR = new PlayRound(w);
		Team[] HomeTeam = new Team[(TeamSize/2)];
		Team[] OutTeam = new Team[(TeamSize/2)];

		// Assign teams to Home and Out positions
		int c = 0;
		int d = (TeamSize/2);
		
		while (d < TeamSize)
		{
			HomeTeam[c] = league.getTeams().get(Order[c]);
			OutTeam[c] = league.getTeams().get(Order[d]);
			c++;
			d++;
		}
		
		
		ArrayList<Match> friday = new ArrayList<Match>();
		ArrayList<Match> saturday = new ArrayList<Match>();
		ArrayList<Match> sunday = new ArrayList<Match>();
		
		while (true)
		{

		int id = 0;


			for (int b = 0; b < ((TeamSize)/2); b++)
			{
				id = (w*10) + b;
				
				if (b == 0)
				{
					Match match = new Match(id, 4, HomeTeam[b], OutTeam[b]);
					friday.add(match);
					System.out.println("Friday: " + HomeTeam[b] + " vs " + OutTeam[b]);
				}
				if (b > 0 && b < 5)
				{
					Match match = new Match(id, 5, HomeTeam[b], OutTeam[b]);
					saturday.add(match);
					System.out.println("Saturday: " + HomeTeam[b] + " vs " + OutTeam[b]);
				}
				if (b > 4)
				{
					Match match = new Match(id, 6, HomeTeam[b], OutTeam[b]);
					sunday.add(match);
					System.out.println("Sunday: " + HomeTeam[b] + " vs " + OutTeam[b]);
				}
			}
		break;
		}
		
		PlayDay f = new PlayDay(friday, "Friday");
		PlayDay s = new PlayDay(saturday, "Saturday");
		PlayDay S = new PlayDay(sunday, "Sunday");
		
		PR.setFriday(f);
		PR.setSaturday(s);
		PR.setSunday(S);
		
		return PR;
		
	}
}
