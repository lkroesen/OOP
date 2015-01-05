package AI;

import model.Player;
import model.Team;
/** Class that calculates the worth of players and regulates the transfers
 * 
 * @author Dennis de Jong
 *	@version 05-01-15
 */
public class TransferAlgorithm {
	
	public static void CalculateWorth(Player p){
		int worth = 33333*(p.getDefensiveScore()+p.getOffensiveScore()+p.getStaminaScore());
		p.setPrice(worth);
	}
	public static void TransferPlayer(Team to, Team tn, Player p){
		int cost = p.getPrice();
		tn.setBudget((tn.getBudget()-cost));
		to.setBudget((to.getBudget()+cost));
		to.delPlayer(p);
		tn.addPlayer(p);
	}
}
