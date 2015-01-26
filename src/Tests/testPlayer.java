package Tests;

import static org.junit.Assert.*;
import model.Event;
import model.Player;

import org.junit.Test;

public class testPlayer {

	@Test
	public void testConstructor() {
		Player p1 = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		assertTrue(p1.equals(p1));
	}

	@Test
	public void testSettersnGetters() {
		Player p1 = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		Player p2 = new Player(2,"A","R",(byte)5,3,2,(byte)8,(byte)6,(byte)2,40);
		p1.setId(2);
		p1.setFirstname("A");
		p1.setSurname("R");
		p1.setJerseyNumber((byte)5);
		p1.setType(3);
		p1.setPosition(2);
		p1.setOffensiveScore((byte)8);
		p1.setDefensiveScore((byte)6);
		p1.setStaminaScore((byte)2);
		p1.setPrice(40);

		assertEquals(p1,p2);
	}

	@Test
	public void Branch1()
	{
		Event e = new Event(0,0,0,0);
		Player p1 = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		assertNotEquals(p1,e);
	}
	
	@Test
	public void Branches1()
	{
		Player p = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		Player p1 = new Player(2,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		
		assertNotEquals(p,p1);
		
	}
	
	@Test
	public void Branches2()
	{
		Player p = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		Player p2 = new Player(1,"rjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		
		assertNotEquals(p,p2);
		
	}
	
	@Test
	public void Branches3()
	{
		Player p = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		Player p3 = new Player(1,"Arjen","obben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		
		assertNotEquals(p,p3);
		
		
	}
	
	@Test
	public void Branches4()
	{
		Player p = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		Player p4 = new Player(1,"Arjen","Robben",(byte)5,4,6,(byte)88,(byte)76,(byte)82,40000000);
		
		assertNotEquals(p,p4);
		
	}
	
	@Test
	public void Branches5()
	{
		Player p = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		Player p5 = new Player(1,"Arjen","Robben",(byte)15,3,6,(byte)88,(byte)76,(byte)82,40000000);
			
		assertNotEquals(p,p5);
	
	}
	
	@Test
	public void Branches6()
	{
		Player p = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		Player p6 = new Player(1,"Arjen","Robben",(byte)15,4,2,(byte)88,(byte)76,(byte)82,40000000);
		
		assertNotEquals(p,p6);
		
	}
	
	@Test
	public void Branches7()
	{
		Player p = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		Player p7 = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)98,(byte)76,(byte)82,40000000);
		
		assertNotEquals(p,p7);
		
	}
	
	@Test
	public void Branches8()
	{
		Player p = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		Player p8 = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)86,(byte)82,40000000);
		
		assertNotEquals(p,p8);
		
	}
	
	@Test
	public void Branches9()
	{
		Player p = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		Player p9 = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)72,40000000);
		
		assertNotEquals(p,p9);
		
	}
	
	@Test
	public void Branches10()
	{
		Player p = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		Player p10 = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,4000);
		
		assertNotEquals(p,p10);
		
	}
	
	@Test
	public void testString()
	{
		Player p1 = new Player(1,"Arjen","Robben",(byte)15,4,6,(byte)88,(byte)76,(byte)82,40000000);
		assertEquals(p1.toString(),"Player [id=1, firstname=Arjen, surname=Robben, jerseyNumber=15, type=4, offensiveRating=88, defensiveRating=76, staminaRating=82, price=40000000]");
	}
}
