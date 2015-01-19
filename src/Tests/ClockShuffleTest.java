package Tests;

import static org.junit.Assert.*;
import AI.Scheduler;
import org.junit.Test;

public class ClockShuffleTest {

	@Test
	public void test() {
		int[] Old_Order = {0,1,2,3,4};
		
		int[] New_Order = Scheduler.ClockShufflePlusPlus(Old_Order, 5);
		
		for (int d = 0; d < 4; d++)
		{
			for (int c = 0; c < 3; c++)
				System.out.println(New_Order[c] + " vs " + New_Order[c+3]);
			System.out.println("Week " + d + "\n");
			New_Order = Scheduler.ClockShufflePlusPlus(New_Order, 6);
		}	
		
	}

}
