package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Match;

import org.junit.Test;

import AI.PlayDay;

public class testPlayDay {

	@Test
	public void test() {
		PlayDay pd = new PlayDay();
		Match m = new Match(6,5);
		ArrayList<Match> ml = new ArrayList<Match>();
		ml.add(m);
		pd.setMatches(ml);
		
		assertEquals(ml.get(0).getId(), 6);
	}

	@Test
	public void test2()
	{
		PlayDay pd = new PlayDay();
		Match m = new Match(6,5);
		ArrayList<Match> ml = new ArrayList<Match>();
		pd.setMatches(ml);
		pd.addMatch(m);
		
		assertEquals(ml.get(0).getId(), 6);
	}
	
	@Test
	public void test3()
	{
		PlayDay pd = new PlayDay();
		pd.setDay("a");
		
		assertEquals(pd.getDay(), "a");
	}
}
