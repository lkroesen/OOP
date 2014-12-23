package AI;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Team;
import model.Player;
import AI.Team_Training_User;

public class Team_Training_UserTest {

	@Test
	public void testOne() 
	{
		ArrayList<Player> Team = new ArrayList<Player>();
		//                          ID       NAME        SURNAME        JERSEY_NR  	TYPE   POS  OFFENSIVE   DEFENSIVE  STAMINA       PRICE
		Player one    = new Player	(1, 	"Neo", 		"Keeper", 		(byte)  1, 	 0, 	0, 	(byte)  20, (byte) 69, (byte) 56, 	 1000);
		Player two    = new Player	(2, 	"Owt", 		"Defender", 	(byte)  2, 	 1, 	1, 	(byte)  15, (byte) 16, (byte) 78, 	 2000);
		Player three  = new Player	(3, 	"Heert", 	"Defender", 	(byte)  3, 	 1, 	2, 	(byte)  33, (byte) 58, (byte) 33, 	 3000);
		Player four   = new Player	(4, 	"Rofu", 	"Defender", 	(byte)  4, 	 1, 	3, 	(byte)  42, (byte) 92, (byte) 100, 	 4000);
		Player five   = new Player	(5, 	"Evif", 	"Defender", 	(byte)  5, 	 1, 	4, 	(byte)  52, (byte) 74, (byte) 1, 	 5000);
		Player six    = new Player	(6, 	"Xis",		"MidFielder",	(byte)  6, 	 2, 	5, 	(byte)  69, (byte) 21, (byte) 67,	 6000);
		Player seven  = new Player	(7, 	"Svene",	"Attacker",		(byte)  7, 	 3, 	6, 	(byte)  74, (byte) 1,  (byte) 80,	 7000);
		Player eight  = new Player	(8, 	"Infinity", "Attacker",		(byte)  8, 	 3, 	7, 	(byte)  22, (byte) 100,(byte) 2,	 8000);
		Player nine   = new Player	(9, 	"Cirno",	"Attacker",		(byte)  9, 	 3, 	9, 	(byte)  59, (byte) 99, (byte) 99,	 9000);
		Player ten    = new Player	(10,	"Ned",		"Attacker",		(byte) 10, 	 3, 	8, 	(byte)  19, (byte) 13, (byte) 58,	10000);
		Player eleven = new Player	(11,	"Veleen",	"Attacker",		(byte) 11, 	 3, 	10, (byte) 100, (byte) 45, (byte) 1,	11000);
		Player twelve = new Player	(12,	"Weltve",	"Keeper",		(byte) 12, 	 0,		11, (byte)   1, (byte) 1,  (byte) 1,	12000);
		Player thrthn = new Player	(13,	"Lucky",	"Defender",		(byte) 13, 	 1,		12, (byte) 100, (byte) 100,(byte) 100,	13000);
		Player frtthn = new Player	(14,	"Andy",		"Zaidman",		(byte) 14,	 2,		13,	(byte)   5,	(byte) 5,  (byte) 5,	14000);
		Player fvthn  = new Player  (15,	"WHAT",		"EVER",			(byte) 15,	 3,		14,	(byte)  50, (byte) 50, (byte) 50,	 1337);
		
		Team.add(one);			Team.add(two);		Team.add(three);		Team.add(four);		Team.add(five);
		Team.add(six);			Team.add(seven);	Team.add(eight);		Team.add(nine);		Team.add(ten);
		Team.add(eleven);		Team.add(twelve);	Team.add(thrthn);		Team.add(frtthn);	Team.add(fvthn);
				
		Team AI = new Team(1, "BEST FOOTBALL TEAM IN THE WORLD", Team);
		
		
		for (int c = 0; c < 100000; c++){
		Team_Training_User.Core(AI);
		assertNotNull(AI);
		System.out.println(AI.getPlayers().get(14).toString());
		}

	}

}
