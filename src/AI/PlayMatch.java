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
	private static ArrayList<Player> hreserve;
	private static ArrayList<Player> areserve;
	private static int hgoal;
	private static int agoal;
	private static int hswitch;
	private static int aswitch;
	
	public static String play(Match match){
		attackn = 0;
		amatch = match;
		Team home = match.getTeam_home();
		//hplayers = home.getPlayers();
		//System.out.println(home.getPlayers());
		Team away = match.getTeam_away();
		//aplayers = away.getPlayers();
		generatestats(amatch);
		
		
		
		boolean last = false;
		if(random(hstamina, astamina)){
			attack(true);
			last = true;
		}
		while(attackn <= 11){
			attack(!last);
			last = !last;
		}
		if(hgoal > agoal){
			home.setBudget(home.getBudget() + 1000000);
		}
		else if(hgoal < agoal){
			away.setBudget(away.getBudget() + 1000000);
		}
		else {
			home.setBudget(home.getBudget() + 500000);
			away.setBudget(away.getBudget() + 500000);
		}
		return hgoal+"-"+agoal;
	}
	
	private static void addevent(int id, boolean home, int pos){
		int time = (int) Math.round((new Random().nextDouble() * 8.18) + (attackn * 8.18)); 
		if(home){
			ArrayList<Player> posplayers = new ArrayList<Player>();
			//System.out.println(hplayers);
			for(Player player:hplayers){
				//System.out.println(player.getPosition() + ":" + pos);
				if(player.getType() == pos){
					posplayers.add(player);
				}
			}
			int pid = posplayers.get(new Random().nextInt(posplayers.size())).getId();
			amatch.addEventHome(new Event(pid, id, time, 0));
		}else{
			ArrayList<Player> posplayers = new ArrayList<Player>();
			for(Player player:aplayers){
				//System.out.println(player.getPosition() + ":" + pos);
				if(player.getType() == pos){
					posplayers.add(player);
				}
			}
			int pid = posplayers.get(new Random().nextInt(posplayers.size())).getId();
			amatch.addEventAway(new Event(pid, id, time, 0));
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
				addevent(3,true,3);
				hgoal += 1;
			}
		}else{
			double t = (aattack/hdefend) * 18.18;
			
			if(t >= 100){
				System.out.println("values need tweeking teamcombo will always score");
				t = 100;
			}
			if(random(t,100)){
				addevent(3,false,3);
				agoal += 1;
			}
		}
		
		//yellow card
		if(random(36.36,63.64)){
			int pos = randompos(2, 33, 35, 11);
			if(pos == 3){
				addevent(1, i, pos);
			}else{
				addevent(1, !i, pos);
			}
		}
		wissel();
	}
	
//	private void yellowcard(boolean i){
//		
//	}

	private static void wissel(){
		if(random(45,55)){
			int pos = randompos(0.2, 0.4, 1.27, 1);
			int time = (int) Math.round((new Random().nextDouble() * 8.18) + (attackn * 8.18)); 
			if(random(1, 1)){
				if(hswitch >= 3){
					return;
				}
				ArrayList<Player> hr =  new ArrayList<Player>();
				ArrayList<Player> hb =  new ArrayList<Player>();
				for(Player p:hplayers){
					if(p.getType() == pos)
						hb.add(p);
				}
				for(Player p:hr){
					if(p.getType() == pos)
						hr.add(p);
				}
				if(hr.size() == 0 || hb.size() == 0){
					return;
				}
				Player pr = hr.get(new Random().nextInt(hr.size()));
				Player pb = hb.get(new Random().nextInt(hb.size()));
				int tp = pr.getPosition();
				pr.setPosition(pb.getPosition());
				pb.setPosition(tp);
				amatch.addEventHome(new Event(pr.getId(), 5, time, 0));
				amatch.addEventHome(new Event(pb.getId(), 4, time, 0));
				generatestats(amatch);
				hswitch++;
			}else{
				if(aswitch >= 3){
					return;
				}
				ArrayList<Player> ar =  new ArrayList<Player>();
				ArrayList<Player> ab =  new ArrayList<Player>();
				for(Player p:aplayers){
					if(p.getType() == pos)
						ab.add(p);
				}
				for(Player p:ar){
					if(p.getType() == pos)
						ar.add(p);
				}
				if(ar.size() == 0 || ab.size() == 0){
					return;
				}
				Player pr = ar.get(new Random().nextInt(ar.size()));
				Player pb = ab.get(new Random().nextInt(ab.size()));
				int tp = pr.getPosition();
				pr.setPosition(pb.getPosition());
				pb.setPosition(tp);
				amatch.addEventAway(new Event(pr.getId(), 5, time, 0));
				amatch.addEventAway(new Event(pb.getId(), 4, time, 0));
				generatestats(amatch);
				aswitch++;
			}
		}
	}
	
	
	//returns true if the first double win
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
	
	private static void generatestats(Match match){
		hplayers = new ArrayList<Player>();
		aplayers = new ArrayList<Player>();
		hreserve = new ArrayList<Player>();
		areserve = new ArrayList<Player>();
		
		for(Player player:match.getTeam_away().getPlayers()){
			if(player.getPosition()<=10){
				aplayers.add(player);
			}else if(player.getPosition() <= 17){
				areserve.add(player);
			}
		}
		
		for(Player player:match.getTeam_home().getPlayers()){
			if(player.getPosition()<=10){
				hplayers.add(player);
			}else if(player.getPosition() <= 17){
				hreserve.add(player);
			}
		}
		
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
			if(player.getPosition() <= 10){
				i += player.getStaminaScore();
			}
		}
		return i;
	}
	
	private static double getOffenciveScore(ArrayList<Player> players){
		double i = 0;
		for(Player player:players){
			if(player.getPosition() <= 10){
				if(player.getType() == 1){
					i += player.getOffensiveScore() * 1.20;
				}else if(player.getType() == 2){
					i += player.getOffensiveScore() * 1.10;
				}else{
					i += player.getOffensiveScore();
				}
			}
		}
		return i;
	}
	
	private static double getDefenciveScore(ArrayList<Player> players){
		double i = 0;
		for(Player player:players){
			if(player.getPosition() <= 10){
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
		}
		return i;
	}
	
}
