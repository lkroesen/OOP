package AI;

import java.util.Arrays;

import model.Match;
import model.Team;

public class Ranking 
{

	// Team array with the Team orded by ranking 0 - highest, 17 - lowest
	private Team[] ranking;
	// current score of team on place
	private int[] ScoreOfTeam;
	
	/**
	 * Constructor
	 */
	public Ranking(Team[] ranking, int[] scoreOfTeam) {
		super();
		this.ranking = ranking;
		ScoreOfTeam = scoreOfTeam;
	}

	@Override
	public String toString() {
		String fi = "";
		
		for (int k = 0; k < ranking.length; k++)
			fi += ranking[k].getName() + ": \t" + ScoreOfTeam[k] + "\n"; 
		
		return fi;
	}

	public Team[] getRanking() {
		return ranking;
	}

	public void setRanking(Team[] ranking) {
		this.ranking = ranking;
	}

	public int[] getScoreOfTeam() {
		return ScoreOfTeam;
	}

	public void setScoreOfTeam(int[] scoreOfTeam) {
		ScoreOfTeam = scoreOfTeam;
	}

	
	/*****************************************************************************
	 *						STATIC METHODS START HERE                            *
	 * ***************************************************************************/
	
	/**
	 * Generate a Ranking object with the current rankings
	 * @param S
	 * Input a Schedule to base the ranking on
	 * @return
	 * Returns a Ranking object with the current ranking
	 */
	public static Ranking generate(Schedule S)
	{
		// Parse schedule to get all teams
		Team[] aTeam = ParseSchedule(S);
		int[] TeamScores = new int[aTeam.length];
		// initalize everyone at 0 points
		
		for (int c = 0; c < S.getS().size(); c++)
		{
			// First do Friday
			PlayDay PD = S.getS().get(c).getFriday();
			TeamScores = PointAssigner(PD, aTeam, TeamScores);
			
			// Secondly do Saturday
			PD = S.getS().get(c).getSaturday();
			TeamScores = PointAssigner(PD, aTeam, TeamScores);
			
			// Finally do Sunday
			PD = S.getS().get(c).getSunday();
			TeamScores = PointAssigner(PD, aTeam, TeamScores);
		}
		
		int[] FinalScores = new int[TeamScores.length];
		Team[] FinalTeamList = new Team[TeamScores.length];
		
		// Sort the teams and corrosponding scores
		for (int i = 0; i < TeamScores.length; i++)
		{
			int j = HighestScore(TeamScores);
			FinalScores[i] = TeamScores[j];
			FinalTeamList[i] = aTeam[j];
			
			TeamScores[j] = -1;
		}
		
		// Check for ties
		Dispute(FinalScores);
		
		Ranking Rank = new Ranking(FinalTeamList, FinalScores);
		return Rank;
	}
	
	/**
	 * Looks at a week in the Schedule to generate a list of all teams
	 * @param S
	 * Input a Schedule
	 * @return
	 * Returns a list with all the teams
	 */
	public static Team[] ParseSchedule(Schedule S)
	{
		// look at all the matches in one week to determine all teams.
		
		//Size of all teams is the amount of matches played x2
		int TeamSize = S.getS().get(0).getFriday().getMatches().size();
		TeamSize += S.getS().get(0).getSaturday().getMatches().size();
		TeamSize += S.getS().get(0).getSunday().getMatches().size();
		TeamSize *= 2;
		
		Team[] TeamList = new Team[TeamSize];
		int gCounter = 0;
		
		// look at all the teams on friday
		for(int e = 0; e < S.getS().get(0).getFriday().getMatches().size(); e++){
			TeamList[gCounter] = S.getS().get(0).getFriday().getMatches().get(e).getTeam_home();
			gCounter++;
			TeamList[gCounter] = S.getS().get(0).getFriday().getMatches().get(e).getTeam_away();
			gCounter++;
			
		}
		
		// look at all the teams on saturday
		for(int e = 0; e < S.getS().get(0).getSaturday().getMatches().size(); e++){
			TeamList[gCounter] = S.getS().get(0).getSaturday().getMatches().get(e).getTeam_home();
			gCounter++;
			TeamList[gCounter] = S.getS().get(0).getSaturday().getMatches().get(e).getTeam_away();
			gCounter++;
			
		}
		// look at all the teams on sunday
		for(int e = 0; e < S.getS().get(0).getSunday().getMatches().size(); e++){
			TeamList[gCounter] = S.getS().get(0).getSunday().getMatches().get(e).getTeam_home();
			gCounter++;
			TeamList[gCounter] = S.getS().get(0).getSunday().getMatches().get(e).getTeam_away();
			gCounter++;
			
		}
		
		return TeamList;
	}
	
	/**
	 * Find at what location the team is
	 * @param TeamList
	 * Input a TeamList
	 * @param Team
	 * Find the location of the inputed Team in the TeamList
	 * @return
	 * Returns the location where the team is, if nothing is found defaults to 0
	 */
	public static int FindTeamAtLocation(Team[] TeamList, Team Team)
	{
		for (int f = 0; f < TeamList.length; f++)			
			if (TeamList[f].getId() == Team.getId())
				return f;
		//defaults to 0
		return 0;
	}
	
	/**
	 * Assign points to a team based on match results in a PlayDay
	 * @param PD
	 * Input a PlayDay to generate scores off
	 * @param aTeam
	 * Input A list of Teams
	 * @param TeamScores
	 * Input the previous TeamScores so the totals can be counted
	 * @return
	 * Returns a new Int Array with updated TeamScores
	 */
	public static int[] PointAssigner(PlayDay PD, Team[] aTeam, int[] TeamScores)
	{
		for (int d = 0; d < PD.getMatches().size(); d++)
		{
			int[] res = new int[2];
			Match m = PD.getMatches().get(d);
			
			// if the match has no result, then we do not assign anything.
			if (m.getResult() == null)
			{
				return TeamScores;
			}
			// Parse the result, assign score to teams <-- create a list of all teams with their scores
			res = m.ParseResult();
			
			// The home team won
			if (res[0] > res[1])
			{
				// Then we assign 3 points to the home team
				TeamScores[FindTeamAtLocation(aTeam, m.getTeam_home())] += 3;
			}
			
			// The away team won
			else if (res[1] > res[0])
			{
				TeamScores[FindTeamAtLocation(aTeam, m.getTeam_away())] += 3;
			}
			
			// it's a tie
			else
			{
				TeamScores[FindTeamAtLocation(aTeam, m.getTeam_home())] += 1;
				TeamScores[FindTeamAtLocation(aTeam, m.getTeam_away())] += 1;
			}
		}
		
		return TeamScores;
	}
	
	/**
	 * Find the highest score
	 * @param TeamScores
	 * Input the list of scores
	 * @return
	 * Returns the current highest number in the array.
	 */
	public static int HighestScore(int[] TeamScores)
	{
		int high = 0;
		
		for(int h = 0; h < TeamScores.length; h++)
		{
			if (TeamScores[high] < TeamScores[h])
			{
				high = h;
			}
		}
		
		return high;
	}
	
	public static void Dispute(int[] Scores)
	{
		int AoD = 0;		
		
		for (int l = 1; l < Scores.length; l++)
			if (Scores[0] == Scores[l])
				AoD++;
		
		if (AoD > 0)
			System.out.println(AoD + "-way tie, extra matches required!");
	}
	
	/**
	 * Gets the current rank of the team
	 * @param ID
	 * Input the team ID
	 * @param r
	 * Input the current ranking
	 * @return
	 * Returns the number at which the rank is located
	 */
	public static int CurrentRank(int ID, Ranking r)
	{
		int cu = 0;
		Team[] a = r.getRanking();
		for (int c = 0; c < r.getRanking().length; c++)
		{
			if (ID == a[c].getId())
			{cu = c;}
		}
		
		return cu+1;
	}
}
