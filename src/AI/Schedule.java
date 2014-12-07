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
	
}
