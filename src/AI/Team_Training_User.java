package AI;

import java.util.ArrayList;
import model.Team;
import AI.Training;
import model.Player;

// algorithm that decides how the AI uses trainings
public class Team_Training_User 
{
	/**
	 * Core method, input a team, algorithm does the rest
	 * @param AI
	 * Input the AI's team
	 * @return
	 * Returns a trained team.
	 */
	public static Team Core(Team AI)
	{
		// AI is bound to the options a player has
		// AI has to be made a little bit dumber
		// So the AI has a chance of picking an option that is NOT optimal
				
		// Get the first player
		ArrayList<Player> Team = AI.getPlayers();
		
		// Loop for every player in the team
		for (int c = 0; c < Team.size(); c++)
			Team.set(c, Selector(Team.get(c)));
		
		// Returns a team that has been trained
		return AI;
		
	}
	
	/**
	 * Will decide if the AI will use the most optimal route
	 * @return
	 * Returns true or false (false being rarer)
	 */
	public static Boolean OptimalRoute()
	{		
		//   1 in V chance of f'ing up
		if ((int) (Math.random() * 100) % 4 == 0)
			return false;
		
		return true;
	}
	
	/**
	 * Selector finds out what kind of character we're dealing with, an offensive character, or a defensive character
	 * @param p
	 * Input a player to be used
	 * @return
	 * Returns a trained player
	 */
	public static Player Selector(Player p)
	{
		int type = p.getType();
		
		// Defensive character, Stat priority: Defensive > Stamina > Offensive
		if (type == 0 || type == 1)
			// Are we going to do thing optimaly?
			if (OptimalRoute())
				p = Algorithm(p, p.getDefensiveScore(), p.getStaminaScore(), p.getOffensiveScore());
			// Nope
			else
				p = _Algorithm(p, p.getDefensiveScore(), p.getStaminaScore(), p.getOffensiveScore());
		
		// Offensive character, Stat priotiry: Offensive > Stamina > Defensive
		else
			if (OptimalRoute())
				p = Algorithm(p, p.getOffensiveScore(), p.getStaminaScore(), p.getDefensiveScore());
			else
				p = _Algorithm(p, p.getOffensiveScore(), p.getStaminaScore(), p.getDefensiveScore());
		
		return p;
	}

	/**
	 * The main algorithm of the AI, input stat preferences
	 * @param p
	 * Input a player, so we can modify it
	 * @param Primary_Stat
	 * Input the most important stat of the player
	 * @param Secondary_Stat
	 * Input the Secondary_Stat of the player
	 * @param Third_Stat
	 * Input the least important stat of the Player
	 * @return
	 * Returns a Player that has done a training
	 */
	public static Player Algorithm(Player p, byte Primary_Stat, byte Secondary_Stat, byte Third_Stat)
	{		
		// Is the most important stat at its maximum? and is stamina not at its maxium? -> Rest
		if (Primary_Stat == (byte) 100 && Secondary_Stat != (byte) 100)
		{
			p = Training.rest(p);
			return p;
		}
		
		// All stats are max, except Third Stat, let's regular training
		if (Primary_Stat == (byte) 100 && Secondary_Stat == (byte) 100 && Third_Stat != (byte) 100)
		{
			p = Training.RegularTraining(p);
			return p;
		}
		
		// What if my character has all its stats maxxed? We let it rest, (because a rest can take some offensive and defensive scores away randomly)
		if (Primary_Stat == (byte) 100 && Secondary_Stat == (byte) 100 && Third_Stat == (byte) 100)
		{
			p = Training.rest(p);
			return p;
		}
		
		// What if my Primary Stat is not high enough yet? Then we select a training!
		if (Primary_Stat != (byte) 100)
		{
			// What if I have 50 stamina? This is really low, but is my Primary Stat even lower?
			if (Secondary_Stat < 50)
			{
				// I'd still like my Primary Stat to be higher than my stamina so I'm going to pick a regular training.
				if (Primary_Stat < 50)
				{
					p = Training.RegularTraining(p);
					return p;
				}
				// My Primary Stat is higher than my stamina so I can safely let my character rest
				else
				{
					p = Training.rest(p);
					return p;
				}
			}
			
			// What if my stamina (always 2nd stat) is higher than 50 but lower than 75?
			if (Secondary_Stat < 75)
			{
				// I want to use a heavy training if my Primary Stat is lower than 75
				if (Primary_Stat < 75)
				{
					p = Training.HeavyTraining(p);
					return p;
				}
				
				// My Primary Stat is higher than 75 so I can let my players take it a little bit easier, a regular training should be fine
				else
				{
					p = Training.RegularTraining(p);
					return p;
				}
			}
			
			// My stamina is higher than 75, let's go heavy training!
			else
			{
				p = Training.HeavyTraining(p);				
				return p;
			}
		}
		return p;
	}
	
	/**
	 * WARNING THIS ALGORITHM IS NOT OPTIMAL
	 * @param p
	 * Input a player to be trained
	 * @param Primary_Stat
	 * Input primary stat
	 * @param Secondary_Stat
	 * Input secondary stat
	 * @param Third_Stat
	 * Input least impotant stat
	 * @return
	 * Returns a player that has been trained
	 */
	public static Player _Algorithm(Player p, byte Primary_Stat, byte Secondary_Stat, byte Third_Stat)
	{
		// Let's get f'ing crazy
		
		boolean w = false;
		
		if ((int) (Math.random() * 100) < 50)
			w = true;
		
		
		// Is the most important stat at its maximum? and is stamina not at its maxium? -> Rest
		if (Primary_Stat == (byte) 100 && Secondary_Stat != (byte) 100)
		{
			//	p = Training.rest(p); would be optimal, so let's pick one of the other two!
			if (w)
			{
				p = Training.HeavyTraining(p);
				return p;
			}
			else
			{
				p = Training.RegularTraining(p);
				return p;
			}
		}
		
		// All stats are max, except Third Stat, let's regular training
		if (Primary_Stat == (byte) 100 && Secondary_Stat == (byte) 100 && Third_Stat != (byte) 100)
		{
			// 	p = Training.RegularTraining(p);
			if (w)
			{
				p = Training.rest(p);
				return p;
			}
			else
			{
				p = Training.HeavyTraining(p);
				return p;
			}
		}
		
		// What if my character has all its stats maxxed? We let it rest, (because a rest can take some offensive and defensive scores away randomly)
		if (Primary_Stat == (byte) 100 && Secondary_Stat == (byte) 100 && Third_Stat == (byte) 100)
		{
			//p = Training.rest(p);
			if (w)
			{
				p = Training.HeavyTraining(p);
				return p;
			}
			else
			{
				p = Training.RegularTraining(p);
				return p;
			}
		}
		
		// What if my Primary Stat is not high enough yet? Then we select a training!
		if (Primary_Stat != (byte) 100)
		{
			// What if I have 50 stamina? This is really low, but is my Primary Stat even lower?
			if (Secondary_Stat < 50)
			{
				// I'd still like my Primary Stat to be higher than my stamina so I'm going to pick a regular training.
				if (Primary_Stat < 50)
				{
					//p = Training.RegularTraining(p);
					if (w)
					{
						p = Training.rest(p);
						return p;
					}
					else
					{
						p = Training.HeavyTraining(p);
						return p;
					}
				}
				// My Primary Stat is higher than my stamina so I can safely let my character rest
				else
				{
					//p = Training.rest(p);
					if (w)
					{
						p = Training.HeavyTraining(p);
						return p;
					}
					else	
					{
						p = Training.RegularTraining(p);
						return p;
					}
				}
			}
			
			// What if my stamina (always 2nd stat) is higher than 50 but lower than 75?
			if (Secondary_Stat < 75)
			{
				// I want to use a heavy training if my Primary Stat is lower than 75
				if (Primary_Stat < 75)
				{
					//p = Training.HeavyTraining(p);
					if (w)
					{
						p = Training.rest(p);
						return p;
					}
					else
					{
						p = Training.RegularTraining(p);
						return p;
					}
				}
				
				// My Primary Stat is higher than 75 so I can let my players take it a little bit easier, a regular training should be fine
				else
				{
					//p = Training.RegularTraining(p);
					if (w)
					{
						p = Training.rest(p);
						return p;
					}
					else
					{
						p = Training.HeavyTraining(p);
						return p;
					}
				}
			}
			
			// My stamina is higher than 75, let's go heavy training!
			else
			{
				//p = Training.HeavyTraining(p);	
				if (w)
				{
					p = Training.rest(p);
					return p;
				}
				else
				{
					p = Training.RegularTraining(p);
					return p;
				}
			}
		}
		
		return p;
	}
	
}
