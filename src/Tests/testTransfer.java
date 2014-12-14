package Tests;

import static org.junit.Assert.*;
import model.Transfer;

import org.junit.Test;

public class testTransfer {

	@Test
	public void testConstructor() {
		Transfer t1 = new Transfer(20,6,30,5,300000,5);
		assertEquals(t1,t1);
	}
	
	@Test
	public void testConstructorFalse(){
		Transfer t1 = new Transfer(10,5,6,320,1500000,3);
		Transfer t3 = new Transfer(10,5,6,300,1500000,3);
		assertNotEquals(t1,t3);
	}
	
	@Test
	public void testEquals(){
		Transfer t1 = new Transfer(15,20,3,15,10000,3);
		Transfer t2 = new Transfer(15,20,3,15,10000,3);
		assertTrue(t1.equals(t2));
		
	}
	
	@Test
	public void testEqualsFalse(){
		Transfer t1 = new Transfer(10,6,3,200,300000,31);
		Transfer t2 = new Transfer(156,31,31,10,250,15);
		assertFalse(t1.equals(t2));
	}
	
	@Test
	public void testGetId(){
		Transfer t1 = new Transfer(36,1,2,36,3000000,1);
		assertEquals(t1.getId(),36);
	}
	
	@Test
	public void testGetIdFalse(){
		Transfer t2 = new Transfer(10,2,3,31,3000000,3);
		assertNotEquals(t2.getId(), 12);
	}
	
	@Test
	public void testGetFrom(){
		Transfer t3 = new Transfer(20,3,6,12,200000,3);
		assertEquals(t3.getFrom(),3);
	}
	
	@Test
	public void testGetFromFalse(){
		Transfer t3 = new Transfer(30,3,6,12,200000,3);
		assertNotEquals(t3.getFrom(),50);
	}
	
	@Test
	public void testGetTo(){
		Transfer t3 = new Transfer(20,3,6,12,200000,3);
		assertEquals(t3.getTo(),6);
	}
	
	@Test
	public void testGetToFalse(){
		Transfer t1 = new Transfer(20,32,1,22,200000,3);
		assertNotEquals(t1.getTo(),3);
	}
	
	@Test
	public void testGetPlayer(){
		Transfer t1 = new Transfer(200,62,6,122,200000,50);
		assertEquals(t1.getPlayer(),122);
	}
	
	@Test
	public void testGetPlayerFalse(){
		Transfer t3 = new Transfer(26,320,610,122,2000200,351);
		assertNotEquals(t3.getPlayer(),3);
	}
	
	@Test
	public void testGetPrice(){
		Transfer t1 = new Transfer(20,3,6,12,3500000,3);
		assertEquals(t1.getPrice(),3500000);
	}
	
	@Test
	public void testGetPriceFalse(){
		Transfer t1 = new Transfer(1,2,3,15,123456789,10);
		assertNotEquals(t1.getPrice(),1000000);
	}
	
	@Test
	public void testToString(){
		Transfer t2 = new Transfer(30,1,2,36,3000000,1);
		assertEquals(t2.toString(),"Transfer [id=30, from=1, to=2, player=36, price=3000000, day=1]");
	}
	
	@Test
	public void testToStringFalse(){
		Transfer t3 = new Transfer(20,6,1,30,2500000,3);
		assertNotEquals(t3.toString(),"abc");
	}

}
