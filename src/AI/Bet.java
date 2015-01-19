package AI;

import model.Team;

public class Bet 
{
	// Amount of money the user bet
	private long money_bet;
	
	// The rate with which the money bet will be multiplied
	private double rate;
	
	// Team user selected as winner
	private int id_won;
	
	// Match id
	private int matchid;

	public Bet(long money_bet, double rate, int id_won,int matchid) 
	{
		this.money_bet = money_bet;
		this.rate = rate;
		this.id_won = id_won;
		this.matchid = matchid;
	}
	
	public long getMoney_bet() {
		return money_bet;
	}

	public void setMoney_bet(long money_bet) {
		this.money_bet = money_bet;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getS_won() {
		return id_won;
	}

	public void setS_won(int id_won) {
		this.id_won = id_won;
	}

	public int getMatchid() {
		return matchid;
	}

	public void setMatchid(int matchid) {
		this.matchid = matchid;
	}

	@Override
	public String toString() {
		return "Bet [money_bet=" + money_bet + ", rate=" + rate + ", id_won="
				+ id_won + ", matchid=" + matchid + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bet other = (Bet) obj;
		if (id_won != other.id_won)
			return false;
		if (money_bet != other.money_bet)
			return false;
		if (Double.doubleToLongBits(rate) != Double
				.doubleToLongBits(other.rate))
			return false;
		if (matchid != other.matchid)
			return false;
		return true;
	}

	
	
}
