package AI;

import java.util.ArrayList;

import model.Game;
import model.League;
import model.Player;
import model.Team;
import model.Transfer;
/** Class that calculates the worth of players and regulates the transfers
 * 
 * @author Dennis de Jong
 *	@version 19-01-15
 */
public class TransferAlgorithm {

	private Game game;
	private ArrayList<Player> player;
	private ArrayList<Team> teams;
	private double sellChance = 0.05;
	private double buyChance = 0.04;

	public TransferAlgorithm(Game game){
		this.game = game;
	}
	/**Calculates the Price of a give Player p
	 * 
	 * @param p
	 */
	public void CalculateWorth(Player p){
		int worth = 33333*(p.getDefensiveScore()+p.getOffensiveScore()+p.getStaminaScore());
		p.setPrice(worth);
	}
	/**Transfers a Player p from the old team(to) to a new team(tn)
	 * 
	 * @param to
	 * @param tn
	 * @param p
	 */
	public void TransferPlayer(Team tn, Player p){
		Team to = null;
		for(Team t : this.teams){
			for(Player p1 : t.getPlayers()){
				if(p1.equals(p)){
					to = t;
				}
			}
		}
		//needs to be changed
		int id = 0;
		int cost = p.getPrice();
		tn.setBudget((tn.getBudget()-cost));
		to.setBudget((to.getBudget()+cost));
		to.delPlayer(p);
		tn.addPlayer(p);
		DelPlayer(p);
		Transfer t = new Transfer(id, to.getId(), tn.getId(), p.getId(), cost, game.getCurrentDay());
		game.addTransfer(t);
	}
	/**Adds Player to the ArrayList with Players for sale.
	 * 
	 * @param p
	 */
	public void AddPlayer(Player p, Team t){
		player.add(p);
		teams.add(t);
	}
	/**Removes Player from the ArrayList with Players for sale.
	 * 
	 * @param p
	 */
	public void DelPlayer(Player p){
		player.remove(p);
	}
	/**Generates for each team a random chance to put a Player on the for sale list
	 * 
	 */
	public void AIsel(){
		double d = 0, e = 0;
		for(League l : game.getLeagues()){
			for(Team t : l.getTeams()){
				if(t.getId() != game.getCurrentTeam()){
					d = Math.random();
					if(d < sellChance){
						int i = 1;
						while(i == 1){
							for(Player p : t.getPlayers()){
								e = Math.random();
								if(e < 0.1){
									AddPlayer(p, t);
									return;
								}	
							}
						}
					}
				}
			}
		}
	}
	/**Generates for each team a random chance to buy a Player from the for sale list
	 * 
	 */
	public void AIbuy(){
		double d = 0, e = 0;
		for(League l : game.getLeagues()){
			for(Team t : l.getTeams()){
				if(t.getId() != game.getCurrentTeam()){
					d = Math.random();
					if(d < buyChance){
						int i = 1;
						while(i == 1){
							for(Player p : this.player){
								e = Math.random();
								if(e < 0.1){
									CalculateWorth(p);
									if(p.getPrice() < t.getBudget()){
										TransferPlayer(t, p);
									}
									return;
								}	
							}
						}	
					}
				}
			}
		}
	}
	/**Puts a player for sale for the current team
	 * 
	 * @param p
	 */
	public void Sell(Player p){
		for(League l : game.getLeagues()){
			for(Team t : l.getTeams()){
				if(t.getId() == game.getCurrentTeam()){
					AddPlayer(p, t);
				}
			}
		}
	}
	/**Buys a player for the current team
	 * 
	 * @param p
	 */
	public void Buy(Player p){
		for(League l : game.getLeagues()){
			for(Team t : l.getTeams()){
				if(t.getId() == game.getCurrentTeam()){
					CalculateWorth(p);
					if(p.getPrice() < t.getBudget()){
						TransferPlayer(t, p);
					} else {
						System.out.println("Low Budget");
					}
				}
			}
		}
	}
}