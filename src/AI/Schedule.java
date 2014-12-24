package AI;

import java.util.ArrayList;

public class Schedule {

	private ArrayList<PlayRound> S = new ArrayList<PlayRound>();
	
	/**
	 * Getter method for getting an ArrayList<PlayRound>
	 * @return
	 * Returns the schedule's ArrayList<PlayRound>
	 */
	public ArrayList<PlayRound> getS() {
		return S;
	}

	/**
	 * Setter method for setting an ArrayList<PlayRound>
	 * @param s
	 * Input an ArrayList<PlayRound>
	 */
	public void setS(ArrayList<PlayRound> s) {
		S = s;
	}

	/**
	 * Empty constructor
	 */
	public Schedule()
	{}
	
	/**
	 * Add method used for adding a playround to the schedule
	 * @param PR
	 * Input a playround object
	 */
	public void add(PlayRound PR)
	{
		S.add(PR);
	}

	@Override
	public String toString() {
		return "Schedule [S=" + S + ", getS()=" + getS() + "]";
	}

	/**
	 * Equals method, checks to see if the input object is equal
	 * @param obj
	 * Input an object to be checked
	 * @return
	 * Returns true or false wether the objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (S == null) {
			if (other.S != null)
				return false;
		} else if (!S.equals(other.S))
			return false;
		return true;
	}
	
	/**
	 * Creates a list of weeks that has been played, true = played
	 * @return
	 * Returns an array of booleans
	 */
	public boolean[] WeeksPlayed()
	{
		boolean[] played = new boolean[S.size()];
		
		int a = 0;
		
		// Label the outer loop so we can break out of it easily
		loop:for (int c = 0; c < S.size(); c++)
		{
			// if it's null then a match has not been played yet
			if (S.get(c).getFriday().getMatches().get(0).getResult() == null)
				break loop;
			
			for (int d = 0; d < S.get(c).getSaturday().getMatches().size(); d++)
				if (S.get(c).getSaturday().getMatches().get(d).getResult() == null)
					break loop;

			for (int e = 0; e < S.get(c).getSunday().getMatches().size(); e++)
				if (S.get(c).getSunday().getMatches().get(e).getResult() == null)
					break loop;
			
			played[c] = true;
			
		}
		
		
		
		return played;
				
	}
}
