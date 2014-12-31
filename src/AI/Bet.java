package AI;

import model.Team;

public class Bet 
{
	// Amount of money the user bet
	private long money_bet;
	
	// The rate with which the money bet will be multiplied
	private double rate;
	
	// Team user selected as winner
	private Team s_won;
	
	// Score the user predicted
	private String result;

	public Bet(long money_bet, double rate, Team s_won, String result) 
	{
		this.money_bet = money_bet;
		this.rate = rate;
		this.s_won = s_won;
		this.result = result;
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

	public Team getS_won() {
		return s_won;
	}

	public void setS_won(Team s_won) {
		this.s_won = s_won;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Bet [money_bet=" + money_bet + ", rate=" + rate + ", s_won="
				+ s_won + ", result=" + result + "]";
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
		if (money_bet != other.money_bet)
			return false;
		if (Double.doubleToLongBits(rate) != Double
				.doubleToLongBits(other.rate))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (s_won == null) {
			if (other.s_won != null)
				return false;
		} else if (!s_won.equals(other.s_won))
			return false;
		return true;
	}
	
	
}
