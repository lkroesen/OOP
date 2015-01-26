package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.League;
import model.Match;
import model.Player;
import model.Team;

import org.junit.Test;

import AI.PlayMatch;

public class testPlayMatch {

	@Test
	public void testMatch() {
		League l = new League(1, "test", "erens");
		Team t1 = new Team(1,"Ajax");
		ArrayList<Player> pl = new ArrayList<Player>();
		for (int c = 1; c < 15; c++){
		pl.add(new Player(c, "Ha", "Ha", (byte) 1, c, c, (byte) 50, (byte) 50, (byte) 50, 5));
		t1.addPlayer(new Player(c, "Ha", "Ha", (byte) 1, c, c, (byte) 50, (byte) 50, (byte) 50, 5));}
		
		Team t2 = t1;
		l.addTeam(t2);
		l.addTeam(t1);
		
		
		Match match = new Match(1,1,t1.getId(),t2.getId());
		match.setResult(PlayMatch.play(match, l));
		assertNotNull(match.getResult());
	}

}
