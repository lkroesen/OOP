package AI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import model.League;
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
	public static Schedule Scheduler(League league)
	{
		//This makes it so I don't have to keep typing league.getTeams().size() 
		int TeamSize = league.getTeams().size();

		// Create a schedule and collect all the Team IDs and Team Names
		Schedule schedule = new Schedule();
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

		//Create a randomized order
		Order = OrderRandomizer(TeamSize, Order);

		
		// first order are first matches
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
			System.out.println(HomeTeam[b] + " vs " + OutTeam[b]);
		
		ArrayList<Team> Team = new ArrayList<Team>();
		
		Team.add(league.getTeamByName(HomeTeam[0]));

		/*	TODO:
		 * 	Rotate matches around using a "clock" method which means,
		 * 	we keep 1 team at it's original position and rotate all teams around
		 * 	until the 1 team plays vs the out team of the 2nd match, then we regenerate
		 * 	the entire schedule, and repeat
		 */
		
		// Return a Schedule which contains all the playing data.
		
		return schedule;
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
	
	// Not finished
	public static int[] ClockShuffle(int[] Old_Order, int TeamSize)
	{
		int[] New_Order = new int[TeamSize];
		
		return New_Order;
	}
}
