package Tests;

import static org.junit.Assert.*;
import model.Team;

import org.junit.Test;

public class testTeam {

	@Test
	public void testConstructor(){
		Team t1 = new Team(1,"Ajax");
		assertTrue(t1.equals(t1));
	}
	
	@Test
	public void testConstructorFalse(){
		Team t2 = new Team(1,"Ajax");
		Team t1 = new Team(2,"Excelsior");
		assertFalse(t1.equals(t2));
	}
	
	@Test
	public void testGetId(){
		Team t1 = new Team(1,"Ajax");
		assertEquals(t1.getId(),1);
	}
	
	@Test
	public void testGetIdFalse(){
		Team t1 = new Team(1,"Ajax");
		assertNotEquals(t1.getId(),3);
	}
	
	@Test
	public void testSetId(){
		Team t1 = new Team(1,"Ajax");
		t1.setId(3);
		assertEquals(t1.getId(),3);
	}
	
	@Test
	public void testSetIdFalse(){
		Team t1 = new Team(1,"Ajax");
		t1.setId(6);
		assertNotEquals(t1.getId(),1);
	}
	
	@Test
	public void testGetName(){
		Team t1 = new Team(1,"Ajax");
		assertEquals(t1.getName(),"Ajax");
	}
	
	@Test
	public void testGetNameFalse(){
		Team t1 = new Team(1,"Ajax");
		assertNotEquals(t1.getName(),"NAC Breda");
	}
	
	@Test
	public void testSetName(){
		Team t1 = new Team(1,"Ajax");
		t1.setName("PSV");
		assertEquals(t1.getName(),"PSV");
	}
	
	@Test
	public void testSetNameFalse(){
		Team t1 = new Team(1,"Ajax");
		t1.setName("Radio");
		assertNotEquals(t1.getName(),"FC Utrecht");
	}
	
	@Test
	public void testToString(){
		Team t1 = new Team(2,"Feyenoord");
		assertEquals(t1.toString(),"Team [id=2, name=Feyenoord, players=[]]");
	}
	
	@Test
	public void testToStringFalse(){
		Team t2 = new Team(3,"PSV");
		assertNotEquals(t2.toString(),"Excelsior");
	}
	
	@Test
	public void testEquals(){
		Team t1 = new Team(1,"Ajax");
		assertTrue(t1.equals(t1));
	}
	
	@Test
	public void testEqualsFalse(){
		Team t1 = new Team(1,"Ajax");
		Team t2 = new Team(1,"NEC Nijmegen");
		assertFalse(t1.equals(t2));
	}
}
