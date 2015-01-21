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
	private ArrayList<Player> player = new ArrayList<Player>();
	private double sellChance = 0.01;
	private double buyChance = 0.0099;

	public TransferAlgorithm(Game game){
		this.game = game;
	}
	public void DailyRoutine(Game game){
		Updater(game);
		AIsell();
		//AIbuy(); Want transferplayer werkt nog niet
	}
	public void Updater(Game game){
		this.game = game;
		if(this.player.size()>0){
			for(Player p : this.player){
				for(League l : game.getLeagues()){
					for(Team t : l.getTeams()){
						for(Player p1 : t.getPlayers()){
							if(p.getId() == p1.getId()){
								p = p1;
							}
						}
					}
				}
			}
		}
	}
	public ArrayList<Player> getTransferringplayers(){
		return this.player;
	}
	/**Calculates the Price of a give Player p
	 * 
	 * @param p
	 */
	public void CalculateWorth(Player p){
		int worth = 33333*(p.getDefensiveScore()+p.getOffensiveScore()+p.getStaminaScore());
		p.setPrice(worth);
	}
	/**Transfers a Player p from the old team to a new team(tn)
	 * 
	 * @param to
	 * @param tn
	 * @param p
	 */
	public void TransferPlayer(Team tn, Player p){
		for(League l : game.getLeagues()){
			for(Team t : l.getTeams()){
				for(Player p1 : t.getPlayers()){
					if(p1.equals(p)){
						CalculateWorth(p);

						int cost = p.getPrice();
						tn.setBudget((tn.getBudget()-cost));
						t.setBudget((t.getBudget()+cost));
						t.delPlayer(p);
						tn.addPlayer(p);						
						int id = 0;
						Transfer tr = new Transfer(id, t.getId(), tn.getId(), p.getId(), cost, game.getCurrentDay());
						game.addTransfer(tr);
					}
				}
			}
		}
		DelPlayer(p);
	}
	/**Adds Player to the ArrayList with Players for sale.
	 * 
	 * @param p
	 */
	public void AddPlayer(Player p){
		this.player.add(p);
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
	public void AIsell(){
		double d = 0, e = 0;
		for(League l : game.getLeagues()){
			for(Team t : l.getTeams()){
				if(t.getId() != game.getCurrentTeam()){
					d = Math.random();
					if(d < sellChance){
						int i = 1;
						while(i == 1){
							for(Player p : t.getPlayers()){
								int o = 1;
								for(Player p1 : this.player){
									if(p1.getId() == p.getId()){
										o = 0;
									}
								}
								e = Math.random();
								if(e < 0.1 && o == 1){
									AddPlayer(p);
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
							for(Player p : player){
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
	public void Sell(Player p){
		int i = 1;
		for(Player p1 : this.player){
			if(p1.getId() == p.getId()){
				i = 0;
			}
		}
		if(i == 1){
			AddPlayer(p);
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