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

}
