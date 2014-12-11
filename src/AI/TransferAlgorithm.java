package AI;

import model.Player;

public class TransferAlgorithm {
	
	public static void CalculateWorth(Player p){
		int worth = 33333*(p.getDefensiveScore()+p.getOffensiveScore()+p.getStaminaScore());
		p.setPrice(worth);
	}
}
