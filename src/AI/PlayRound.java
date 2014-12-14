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
	
	public PlayRound(PlayDay friday, PlayDay saturday, PlayDay sunday, int RN)
	{
		this(RN);
		Friday = friday;
		Saturday = saturday;
		Sunday = sunday;
	}
	
	public PlayDay getFriday() {
		return Friday;
	}

	public void setFriday(PlayDay friday) {
		Friday = friday;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayRound other = (PlayRound) obj;
		if (Friday == null) {
			if (other.Friday != null)
				return false;
		} else if (!Friday.equals(other.Friday))
			return false;
		if (RoundNumber != other.RoundNumber)
			return false;
		if (Saturday == null) {
			if (other.Saturday != null)
				return false;
		} else if (!Saturday.equals(other.Saturday))
			return false;
		if (Sunday == null) {
			if (other.Sunday != null)
				return false;
		} else if (!Sunday.equals(other.Sunday))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlayRound [Friday=" + Friday + ", Saturday=" + Saturday
				+ ", Sunday=" + Sunday + ", RoundNumber=" + RoundNumber
				+ ", getFriday()=" + getFriday() + ", getSaturday()="
				+ getSaturday() + ", getSunday()=" + getSunday()
				+ ", getRoundNumber()=" + getRoundNumber() + "]";
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
