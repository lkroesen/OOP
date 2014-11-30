package AI;

public class PlayRound {

	private PlayDay Friday;
	private PlayDay Saturday;
	private PlayDay Sunday;
	private int RoundNumber;
	
	public PlayRound(int roundnumber)
	{
		RoundNumber = roundnumber;
	}
	
	public PlayDay getFriday() {
		return Friday;
	}

	public void setFriday(PlayDay friday) {
		Friday = friday;
	}

	public PlayDay getSaturday() {
		return Saturday;
	}

	public void setSaturday(PlayDay saturday) {
		Saturday = saturday;
	}

	public PlayDay getSunday() {
		return Sunday;
	}

	public void setSunday(PlayDay sunday) {
		Sunday = sunday;
	}

	public int getRoundNumber() {
		return RoundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		RoundNumber = roundNumber;
	}
}
