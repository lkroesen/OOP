package AI;

import java.util.ArrayList;
import java.util.Random;

import model.Event;
import model.Match;
import model.Player;
import model.Team;
//eerst kijken hoevel wissels,
//dan wie er gewisseld wordt random
//dan wie er in gaat roandom van zelfde 
//dan event
//opnieuwe scoren bereken van team.

public class PlayMatch {

	private static double hstamina;
	private static double hattack;
	private static double hdefend;
	private static double astamina;
	private static double aattack;
	private static double adefend;
	private static int attackn;
	private static Match amatch;
	private static ArrayList<Player> hplayers;
	private static ArrayList<Player> aplayers;
	private static int hgoal;
	private static int agoal;
	
	public static String play(Match match){
		attackn = 0;
		amatch = match;
		Team home = match.getTeam_home();
		hplayers = home.getPlayers();
		//System.out.println(home.getPlayers());
		Team away = match.getTeam_away();
		aplayers = away.getPlayers();
		generatestats(hplayers,aplayers);
		
		
		
		boolean last = false;
		if(random(hstamina, astamina)){
			attack(true);
			last = true;
		}
		while(attackn <= 11){
			attack(!last);
			last = !last;
		}
		
		match.setResult(hgoal + "-" + agoal);
		System.out.println(home.getName() + " - " + away.getName());
		System.out.println(hgoal + "-" + agoal);
		return hgoal+","+agoal;
	}
	
	private static void addevent(int id, boolean home, int pos){
		int time = (int) Math.round((new Random().nextDouble() * 8.18) + (attackn * 8.18)); 
		if(home){
			ArrayList<Player> posplayers = new ArrayList<Player>();
			//System.out.println(hplayers);
			for(Player player:hplayers){
				//System.out.println(player.getPosition() + ":" + pos);
				if(player.getPosition() == pos){
					posplayers.add(player);
				}
			}
			//int pid = posplayers.get(new Random().nextInt(posplayers.size())).getPosition();
			//amatch.addEventHome(new Event(pid, id, time, 0));
		}else{
			ArrayList<Player> posplayers = new ArrayList<Player>();
			for(Player player:aplayers){
				//System.out.println(player.getPosition() + ":" + pos);
				if(player.getPosition() == pos){
					posplayers.add(player);
				}
			}
			//int pid = posplayers.get(new Random().nextInt(posplayers.size())).getPosition();
			//amatch.addEventAway(new Event(pid, id, time, 0));
		}
		
	}
	
	private static void attack(boolean i) {
		attackn ++;
		if(attackn > 11){
			return;
		}
		if(i){
			double t = (hattack/adefend) * 18.18;
			
			if(t >= 100){
				System.out.println("values need tweeking teamcombo will always score");
				t = 100;
			}
			if(random(t,100)){
				addevent(3,true,-1);
				hgoal += 1;
			}
		}else{
			double t = (aattack/hdefend) * 18.18;
			
			if(t >= 100){
				System.out.println("values need tweeking teamcombo will always score");
				t = 100;
			}
			if(random(t,100)){
				addevent(3,false,-1);
				agoal += 1;
			}
		}
		
		//yellow card
		if(random(36.36,100)){
			int pos = randompos(2, 33, 35, 11);
			if(pos == 3){
				addevent(1, i, -1);
			}else{
				addevent(1, !i, -1);
			}
		}
		
		
	}
	
//	private void yellowcard(boolean i){
//		
//	}

	//returns true if the first double wins
	private static boolean random(double x, double y){
		double i = x + y;
		double r = new Random().nextDouble() * i;
		if(r > x){
			return false;
		}else{
			return true;
		}
	}
	
	//return a weigthed random player posisition
	private static int randompos(double goalkeeper, double defender, double midfielder, double attacker){
		double i = goalkeeper + defender + midfielder + attacker;
		double r = new Random().nextDouble() * i;
		if(r>(i-attacker)){
			return 3;
		}else if(r>(i- (midfielder + attacker))){
			return 2;
		}else if(r>(i-(midfielder + attacker + defender))){
			return 1;
		}else{
			return 0;
		}
	}
	
	private static void generatestats(ArrayList<Player> hplayers, ArrayList<Player> aplayers){
		hstamina = getStamina(hplayers);
		hattack = getOffenciveScore(hplayers);
		hdefend = getDefenciveScore(hplayers);
		astamina = getStamina(aplayers);
		aattack = getOffenciveScore(aplayers);
		adefend = getDefenciveScore(aplayers);
		agoal = 0;
		hgoal = 0;
	}
	
	private static double getStamina(ArrayList<Player> players){
		double i = 0;
		for(Player player:players){
			i += player.getStaminaScore();
		}
		return i;
	}
	
	private static double getOffenciveScore(ArrayList<Player> players){
		double i = 0;
		for(Player player:players){
			if(player.getType() == 1){
				i += player.getOffensiveScore() * 1.20;
			}else if(player.getType() == 2){
				i += player.getOffensiveScore() * 1.10;
			}else{
				i += player.getOffensiveScore();
			}
		}
		return i;
	}
	
	private static double getDefenciveScore(ArrayList<Player> players){
		double i = 0;
		for(Player player:players){
			if(player.getType() == 2){
				i += player.getDefensiveScore() * 1.10; 
			}else if(player.getType() == 3){
				i+= player.getDefensiveScore() * 1.20;
			}else if(player.getType() == 4){
				i += player.getDefensiveScore() * 1.50;
			}else{
				i += player.getDefensiveScore();
			}
		}
		return i;
	}
	
}
