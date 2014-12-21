package AI;

import model.Player;

public class Training {
	
	 /* Regular Training: costs 1 stamina per player
	 * Gains: 0 (50%) or 1 (50%) point bonus on their position
	 * Randomly Gains: 0-2 in Stamina, Attack or Defence
	 */
	 public static Player RegularTraining(Player p)
	 {
		 // Deduct one from stamina
		 byte s = (byte) ((byte) Math.abs(p.getStaminaScore() - 1));
		 p.setStaminaScore(s);
		 
		 // Decide how much points we will get to add
		 int points = (int) (Math.random() * 100);
		 int spendable = 0;
		 
		 System.out.println(points);
		 
		 if (points >=70 && points < 95)
			 spendable = 1;
		 
		 if (points >= 95 && points <= 100)
			 spendable = 2;
		 
		 if (spendable > 0)
		 {
			 int offensivebonus = 0;
			 
			 // offensive bonus / penalty
			 if (p.getType() == 1 || p.getType() == 2)
				 offensivebonus = 5;
			 
			 if (p.getType() == 3 || p.getType() == 4)
				 offensivebonus = -5;
			 
			 for(int c = 0; c < spendable; c++)
			 {
				 int percentage = (int) (Math.random() * 100);

				 // see if we add to offensive
				 if (percentage <= (45 + offensivebonus))
					 p.setOffensiveScore((byte) (p.getOffensiveScore() + 1));
				 
				 // Stamina bonus
				 if (percentage >= 90)
					 p.setStaminaScore((byte) (p.getStaminaScore() + 1));
				 
				 // if not any of the above, then it's defensive bonus
				 else
					 p.setDefensiveScore((byte) (p.getDefensiveScore() + 1)); 
			 }
		 }
			
		 // Check to see if any stats are exceeded and return.
		 return check(p); 
	 }
	
	 /* Heavy Training: costs 3 stamina per player
	  * Gains: 1 point in Attack or Defence based on their position
	  * Randomly Gains: 0-3 point(s) in Stamina, Attack or Defence
	  */
	 public static Player HeavyTraining(Player p)
	 {
		 byte s = (byte) ((byte) Math.abs(p.getStaminaScore() - 3));
		 p.setStaminaScore(s);
		 
		 int points = (int) (Math.random() * 100);
		 int spendable = 0;
		 
		 if (points > 40 && points <= 80)
			 spendable = 1;
		 
		 if (points > 80 && points <= 95)
			 spendable = 2;
		 
		 if (points > 95 && points <= 100)
			 spendable = 3;
		 
		 if (spendable > 0)
		 {
			 int offensivebonus = 0;
			 
			 // Offensive positions
			 if (p.getType() == 1 || p.getType() == 2)
				 offensivebonus = 10;
			 
			 // Defensive positions
			 if (p.getType() == 3 || p.getType() == 4)
				 offensivebonus = -10;
			 
			 for(int c = 0; c < spendable; c++)
			 {
				 int percentage = (int) (Math.random() * 100);

				 // see if we add to offensive
				 if (percentage <= (40 + offensivebonus))
					 p.setOffensiveScore((byte) (p.getOffensiveScore() + 1));
				 
				 // Stamina bonus
				 if (percentage >= 90)
					 p.setStaminaScore((byte) (p.getStaminaScore() + 1));
				 
				 // if not any of the above, then it's defensive bonus
				 else
					 p.setDefensiveScore((byte) (p.getDefensiveScore() + 1)); 
			 }
		 } 
		 return check(p);
	 }
	 
	 /* Rest
	 * Gains: 2 Stamina
	 * Randomly Gain: Nothing / Stamina / Attack / Defense
	 * Randomly Lose: Nothing / Attack / Defense
	 */
	 public static Player rest(Player p)
	 {
		 byte s = (byte) ((byte) p.getStaminaScore() + 2);
		 p.setStaminaScore(s);
		 
		 int rng = (int) (Math.random()*100);
		 
		 // Randomly Gain
		 
		 // Stamina boost
		 if (rng >= 48 && rng <= 98)
		 {
			 rng = (int) (Math.random()*100);
			 
			 if (rng >= 49 && rng <= 75)
				p.setStaminaScore((byte) ((byte)p.getStaminaScore()+1));
			 
			 if (rng >= 76 && rng <= 87)
				p.setStaminaScore((byte) ((byte)p.getStaminaScore()+2));
			 
			 if (rng >= 88 && rng <= 100)
				 p.setStaminaScore((byte) ((byte)p.getStaminaScore()+3)); 
		 }
		 
		 // Offense boost
		 if (rng == 99)
		 {
			 rng = (int) (Math.random()*100);
			 
			 if (rng >= 50)
				 p.setOffensiveScore((byte) ((byte)p.getOffensiveScore()+1));
		 }
		 
		 // Defense boost
		 if (rng == 100)
		 {
			 rng = (int) (Math.random()*100);
			 
			 if (rng >= 50)
				 p.setDefensiveScore((byte) ((byte)p.getDefensiveScore()+1));
		 }
		 
		 
		 // Randomly Lose
		 rng = (int) (Math.random()*100);
		 
		 if (rng >= 90 && rng <= 95)
			 p.setOffensiveScore((byte) (Math.abs((byte)p.getOffensiveScore()-1)));
			 
		 if (rng >= 95 && rng <= 100)
			 p.setDefensiveScore((byte) (Math.abs((byte)p.getDefensiveScore()-1)));
		 
		 return check(p);
	 }
	 
	/**
	 * Check to see if a player's score has exceeded 100 (the maximum)
	 * @param p
	 * Input a player to be checked
	 * @return
	 * Returns a player object with stats of 100 if the maximum was exceeded
	 */
	public static Player check(Player p)
	{
		// Check offense
		if (p.getOffensiveScore() > 100)
			p.setOffensiveScore((byte) 100);
		
		// Check defense
		if (p.getDefensiveScore() > 100)
			p.setDefensiveScore((byte) 100);
		
		// Check stamina
		if (p.getStaminaScore() > 100)
			p.setStaminaScore((byte) 100);
		
		return p;
	}
}
