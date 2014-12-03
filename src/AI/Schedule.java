package AI;

import java.util.ArrayList;

public class Schedule {

	private ArrayList<PlayRound> S = new ArrayList<PlayRound>();
	
	public Schedule()
	{}
		
	public void add(PlayRound PR)
	{
		S.add(PR);
	}
	
	public void print()
	{
		for (int c = 0; c < S.size(); c++)
		{
			System.out.println(" * * * \tWEEK: " + c + " * * * ");
			System.out.println("Friday: " + S.get(c).getFriday().getMatches().get(0).getTeam_home().getName() + " vs " + S.get(c).getFriday().getMatches().get(0).getTeam_away().getName());
			
			// print saturday
			for (int i = 0; i < 4; i++)
			{
				System.out.println("Saturday: " + S.get(c).getSaturday().getMatches().get(i).getTeam_home().getName() + " vs " + S.get(c).getSaturday().getMatches().get(i).getTeam_away().getName());
			}
			
			// print sunday
			for (int k = 0; k < 4; k++)
			{
				System.out.println("Sunday: " + S.get(c).getSaturday().getMatches().get(k).getTeam_home().getName() + " vs " + S.get(c).getSaturday().getMatches().get(k).getTeam_away().getName());
			}
		}
	}
	
}
