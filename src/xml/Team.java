package xml;

import java.util.ArrayList;

public class Team {
	
	ArrayList<Player> players = new ArrayList<Player>();

	public Team() {
		
		
		
	}
	
	public void addPlayer(Player player){
		players.add(player);
	}
	
	public Player getPlayerById(int id){
		for(Player player : players){
			if(player.getId() == id)
				return player;
		}
		return null;
	}
	
	public Player getPlayerByName(String name){
		for(Player player : players){
			if(player.getName().equals(name))
				return player;
		}
		return null;
	}

}
