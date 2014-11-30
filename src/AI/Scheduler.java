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
	 * The core of the Scheduler, this will eventually return a Schedule object with the schedule for the entire game, this is randomized
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

		// Not sure what I was gonna use this for oops, leaving it in for now
		PlayRound[] Week;
		Week = new PlayRound[TeamSize];
		for (int c = 0; c < TeamSize; c++)
			Week[c] = new PlayRound(c);

		int[] Order = new int[TeamSize];

		// Pre-initialize the array so my method can be shorter
		for (int c = 0; c < TeamSize; c++)
			Order[c] = -1;
		Order = OrderRandomizer(TeamSize, Order);

		ArrayList<Team> Team = new ArrayList<Team>();
		
		// ArrayList<Team> formatted in the following: First half is Home teams, second half is out teams
		for (int a = 0; a < Order.length; a++)
			Team.add(league.getTeamByName(league.getTeams().get(Order[a]).getName()));
		
		// Week 0/17
		int week = 0;
		Schedule s = new Schedule();
		// on week 17 (=16) we change the complete order again
		
		
		
		while (week < TeamSize-1)
		{
			// initial order
			if (week == 0)
				s.add(PlayRoundGenerator(Team, Order, TeamSize, week));
			else
			{
				Order = ClockShuffle(Order, TeamSize);
				s.add(PlayRoundGenerator(Team, Order, TeamSize, week));
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
				s.add(PlayRoundGenerator(Team, Order, TeamSize, week));
			else
			{
				Order = ClockShuffle(Order, TeamSize);
				s.add(PlayRoundGenerator(Team, Order, TeamSize, week));
			}
			
			System.out.println("*/*/*/ WEEK: " + week + " \\*\\*\\*" );
			print(TeamSize, league, Order);
			
			week++;
			
			//failsafe
			if (week == ((2*TeamSize)-2))
				break;
			

		}

		
		/*	TODO:
		 * 	Rotate matches around using a "clock" method which means,
		 * 	we keep 1 team at it's original position and rotate all teams around
		 * 	until the 1 team plays vs the out team of the 2nd match, then we regenerate
		 * 	the entire schedule, and repeat
		 */
		
		// Return a Schedule which contains all the playing data.
		
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
	
	// Hardcoded for now
	public static int[] ClockShuffle(int[] Old_Order, int TeamSize)
	{
		int[] New_Order = new int[18];
		
		New_Order[0] = Old_Order[0];
		New_Order[17] = Old_Order[8];
		New_Order[8] = Old_Order[7];
		New_Order[7] = Old_Order[6];
		New_Order[6] = Old_Order[5];
		New_Order[5] = Old_Order[4];
		New_Order[4] = Old_Order[3];
		New_Order[3] = Old_Order[2];
		New_Order[2] = Old_Order[1];
		New_Order[1] = Old_Order[9];
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
	
	// Not finished
	/*
	public static int[] ClockShuffle(int[] Old_Order, int TeamSize)
	{
		int[] New_Order = new int[TeamSize];
		int done = 0;
		
		int c = ((TeamSize/2) + 1);
		
		while (done != 1)
		{
			// old 10 -> new 9 etc until old 17 -> new 16 
			if (c > (TeamSize/2) && c < (TeamSize-1))
			New_Order[c] = Old_Order[c-1];
			
			c++;
			
			if(c == (TeamSize))
			{
				New_Order[c-1] = Old_Order[(TeamSize/2)-1];	
				New_Order[1] = Old_Order[(TeamSize/2)];
				
				int d = 7;
				while (d != 0)
				{
					New_Order[d+1] = Old_Order[d];
					d--;
				}
				
				done = 1;
				break;
			}

		}
		
		return New_Order;
	}
	*/
	public static int AmountOfMatches(League le)
	{
		int number = 0;
		for (int c = 1; c < le.getTeams().size(); c++)
			number += c;
		number *= 2;

		return number;
		
	}
	
	public static PlayRound PlayRoundGenerator(ArrayList<Team> TeamList, int[] Order, int TeamSize, int Week)
	{
		int c = 0;
		int d = (TeamSize/2);
		
		ArrayList<Match> Friday = new ArrayList<Match>();
		ArrayList<Match> Saturday = new ArrayList<Match>();
		ArrayList<Match> Sunday = new ArrayList<Match>();
		
		while (d < TeamSize)
		{
			int id = (Week*10)+c;
			
			if (c == 1)
			{
				Match f = new Match(id,5,TeamList.get(Order[c]),TeamList.get(Order[d]));
				Friday.add(f);
			}
			
			if (c >= 2 && c < 6)
			{
				Match f = new Match(id,5,TeamList.get(Order[c]),TeamList.get(Order[d]));
				Saturday.add(f);
			}
			
			if (c >= 6)
			{
				Match f = new Match(id,5,TeamList.get(Order[c]),TeamList.get(Order[d]));
				Sunday.add(f);
			}
			
			c++;
			d++;
		}
		
		// 1 on friday
		PlayDay friday = new PlayDay(Friday);
		// 4 on saturday
		PlayDay saturday = new PlayDay(Saturday);
		// 4 on sunday
		PlayDay sunday = new PlayDay(Sunday);
		
		PlayRound PR = new PlayRound(friday,saturday,sunday,Week);
		return PR;
	}
	
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
}
