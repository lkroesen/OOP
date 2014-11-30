package AI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import model.League;

// Algorithm to schedule all teams in matches at the start of the game
public class Scheduler 
{
	public static Schedule Scheduler(League league)
	{
		int TeamSize = league.getTeams().size();

		
		Schedule schedule = new Schedule();
		int[] TeamIDs;
		TeamIDs = new int[TeamSize];
		String[] TeamNames = new String[TeamSize];

		for (int c = 0; c < TeamSize; c++)
		{
			TeamIDs[c] = league.getTeams().get(c).getId();
			TeamNames[c] = league.getTeams().get(c).getName();
			//System.out.println(TeamIDs[c] + " " + TeamNames[c]);
		}

		PlayRound[] Week;
		Week = new PlayRound[TeamSize];
		for (int c = 0; c < TeamSize; c++)
			Week[c] = new PlayRound(c);
		
		//Sort
		// pick a random team
		Random rng = new Random();
		int[] Order = new int[TeamSize];

		// (initialize the array with every as 0, meaning the player will always play the very first game
		for (int c = 0; c < TeamSize; c++)
			Order[c] = 0;
		
		//Create a randomized order
		
		int a = 1;
		while (a < TeamSize)
		{
			int r = rng.nextInt(TeamSize);
			
			if (contains(Order,r) == false)
			{
				Order[a] = r;
				a++;
			}
			
		}
				
		// first order are first matches

		String[] HomeTeam = new String[(TeamSize/2)];
		String[] OutTeam = new String[(TeamSize/2)];
		
		System.out.println("Test");
		

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
		
		// keep track of which team played vs which
		// Return a Schedule which contains all the playing data.
		
		return schedule;
	}
	
	public static boolean contains(int[] i, int r)
	{
		for (int c = 0; c < i.length; c++)
		{
			if (i[c] == r)
				return true;
		}
		return false;
	}
}
