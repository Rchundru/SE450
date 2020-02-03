package homework2;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import junit.framework.TestCase;

//import org.junit.Test;

public class MyLinkedTest extends TestCase{
	
	@Test
	public void testDelete () {
		MyLinked b = new MyLinked ();
		b.add (1);
		assertEquals(1, b.size());
		b.delete (0);
		assertEquals(0, b.size());
		for(double i = 1; i < 13; i++) {
			b.add (i);
		}
		assertEquals(12, b.size());
		b.delete (0);
		assertEquals(11, b.size());
		b.delete (10);
		assertEquals(10, b.size());
		b.delete (4);
		assertEquals(9, b.size());
	}
	
	@Test
	public void testReverse () {
		MyLinked b = new MyLinked ();
		b.reverse ();
		assertEquals(0,b.size());
		b.add (1);
		b.reverse ();
		assertEquals(1, b.size());
		b.add (2);
		b.reverse ();
		assertEquals(2, b.size());
		b.reverse ();
		assertEquals(2, b.size());
		for (double i = 3; i < 7; i++) {
			b.add (i);
			b.add (i);
		}
		b.reverse ();
		assertEquals(10, b.size());
	}
	
	@Test
	public void testRemove () {
		MyLinked b = new MyLinked ();
		b.remove (4);
		assertEquals(0, b.size());
		b.add (1);
		b.remove (4);
		assertEquals(1, b.size());
		b.remove (1);
		assertEquals(0, b.size());
		for (double i = 1; i < 5; i++) {
			b.add (i);
			b.add (i);
		}
		for (double i = 1; i < 5; i++) {
			b.add (i);
			b.add (i);
			b.add (i);
			b.add (i);
			b.add (i);
		}

		b.remove (9);
		assertEquals(28, b.size());
		b.remove (3);
		assertEquals(21, b.size());
		b.remove (1);
		assertEquals(14, b.size());
		b.remove (4);
		assertEquals(7, b.size());
		b.remove (2);
		assertEquals(0, b.size());
	} 

}
