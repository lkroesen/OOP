package Tests;

import static org.junit.Assert.*;
import AI.PlayRound;
import AI.PlayDay;
import org.junit.Test;

public class testPlayRound {

	@Test
	public void testToString() 
	{
		PlayRound pr = new PlayRound(1);
		assertEquals(pr.toString(), "PlayRound [Friday=null, Saturday=null, Sunday=null, RoundNumber=1, getFriday()=null, getSaturday()=null, getSunday()=null, getRoundNumber()=1]");
	}

}
