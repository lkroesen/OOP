package Tests;

import static org.junit.Assert.*;
import model.Transfer;

import org.junit.Test;

public class testTransfer {

	@Test
	public void testConstructor() {
		Transfer t1 = new Transfer(0,20,6,30,300000,5);
		assertEquals(t1,t1);
	}
	
	@Test
	public void testConstructorFalse(){
		Transfer t1 = new Transfer(0,10,5,6,1500000,3);
		Transfer t3 = new Transfer(0,10,5,6,15000000,3);
		assertNotEquals(t1,t3);
	}
	
	@Test
	public void testEquals1(){
		Transfer t1 = new Transfer(0,15,20,3,10000,3);
		Transfer t2 = new Transfer(0,15,21,3,10000,3);
		assertFalse(t1.equals(t2));
	}
	
	@Test
	public void testEquals2(){
		Transfer t1 = new Transfer(0,15,20,3,10000,3);
		assertEquals(t1.getDay(),3);
	}
	
	@Test
	public void testEquals3(){
		Transfer t1 = new Transfer(0,15,20,3,10000,3);
		assertTrue(t1.equals(t1));
	}
	
	@Test
	public void testEquals4(){
		Transfer t1 = new Transfer(0,15,20,3,10000,3);
		assertTrue(t1.equals(t1));
	}
	
	@Test
	public void testEquals5(){
		Transfer t1 = new Transfer(0,15,20,3,10000,3);
		Transfer t2 = new Transfer(0,15,21,3,10000,3);
		assertNotEquals(t1,t2);
	}
	
	@Test
	public void testEquals6(){
		Transfer t1 = new Transfer(0,15,20,3,10000,3);
		assertEquals(t1,t1);
	}

	@Test
	public void testEquals7(){
		Transfer t2 = new Transfer(0,15,21,3,10000,3);
		assertTrue(t2.equals(t2));
	}
	
	@Test
	public void testEqualsFalse1(){
		Transfer t1 = new Transfer(0,10,6,3,300000,31);
		Transfer t2 = new Transfer(0,156,31,31,250,15);
		assertFalse(t1.equals(t2));
	}
	
	@Test
	public void testEqualsFalse2(){
		Transfer t1 = new Transfer(0,10,6,3,300000,31);
		Transfer t3 = new Transfer(0,10,6,3,300000,31);
		assertFalse(t1.equals(t3));
	}
	
	@Test
	public void testEqualsFalse3(){
		Transfer t1 = new Transfer(0,10,6,3,300000,31);
		assertFalse(t1.equals(null));
	}
	
	@Test
	public void testGetIdFalse(){
		Transfer t2 = new Transfer(0,10,2,3,3000000,3);
		assertNotEquals(t2.getId(), 12);
	}
	
	@Test
	public void testGetFrom(){
		Transfer t3 = new Transfer(0,20,3,6,200000,3);
		assertEquals(t3.getFrom(),20);
	}
	
	@Test
	public void testGetFromFalse(){
		Transfer t3 = new Transfer(0,30,3,6,200000,3);
		assertNotEquals(t3.getFrom(),50);
	}
	
	@Test
	public void testGetTo(){
		Transfer t3 = new Transfer(0,20,3,6,200000,3);
		assertEquals(t3.getTo(),3);
	}
	
	@Test
	public void testGetToFalse(){
		Transfer t1 = new Transfer(0,20,32,1,200000,3);
		assertNotEquals(t1.getTo(),15);
	}
	
	@Test
	public void testGetPrice(){
		Transfer t1 = new Transfer(0,20,3,6,3500000,3);
		assertEquals(t1.getPrice(),3500000);
	}
	
	@Test
	public void testGetPriceFalse(){
		Transfer t1 = new Transfer(0,1,2,3,123456789,10);
		assertNotEquals(t1.getPrice(),1000000);
	}
	
	public void testGetDay(){
		Transfer t1 = new Transfer(0,36,1,2,3000000,1);
		assertEquals(t1.getDay(),1);
	}
	
	@Test
	public void testGetDayFalse(){
		Transfer t2 = new Transfer(0,10,2,3,3000000,3);
		assertNotEquals(t2.getDay(), 12);
	}
	
	@Test
	public void testGetPlayer(){
		Transfer t2 = new Transfer(0,10,2,3,3000000,3);
		assertEquals(t2.getPlayer(), 3);
	}
	
	@Test
	public void testGetPlayerFalse(){
		Transfer t2 = new Transfer(0,10,2,3,3000000,3);
		assertNotEquals(t2.getPlayer(), 12);
	}
	
	@Test
	public void testToString(){
		Transfer t2 = new Transfer(0,30,1,2,3000000,1);
		assertEquals(t2.toString(),"Transfer [id="+ t2.getId() +", from=30, to=1, player=2, price=3000000, day=1]");
	}
	
	
	@Test
	public void testToStringFalse(){
		Transfer t3 = new Transfer(0,20,6,1,2500000,3);
		assertNotEquals(t3.toString(),"abc");
	}

}
