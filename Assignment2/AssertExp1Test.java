package homework2;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import junit.framework.TestCase;



//import org.junit.Test;

public class AssertExp1Test extends TestCase{
	
	AssertExp1 o = new AssertExp1();

	@Test
	public void testMinValue() {
		double[] list = new double[] {9,4,6,3,10,1,4,6};
		assertEquals(1.0, o.minValue(list));
		double[] list1 = new double[] {1.2, 1.3, 1.1, 1.6, 6.5, 4.3};
		assertEquals(1.1, o.minValue(list1));
		double[] list2 = new double[] {1,1,1,1,1,1,1};
		assertEquals(1.0, o.minValue(list2));
	}
	
	@Test
	public void testMinPosition() {
		double[] list = new double[] {9,4,6,3,10,1,4,6};
		assertEquals(5, o.minPosition(list));
		double[] list1 = new double[] {1.2, 1.3, 1.1, 1.6, 6.5, 4.3};
		assertEquals(2, o.minPosition(list1));
		double[] list2 = new double[] {1,1,1,1,1,1,1};
		assertEquals(0, o.minPosition(list2));
	}
	
	@Test
	public void testNumUnique() {
		double[] list = new double[] {1,1,1,1,2,3,3,4,5,5,5,5,6,7};
		assertEquals(7, o.numUnique(list));
		double[] list1 = new double[] {1.1, 1.1, 1.2, 1.3, 3.1, 3.1};
		assertEquals(4, o.numUnique(list1));
		double[] list2 = new double[] {1,1,1,1,1};
		assertEquals(1, o.numUnique(list2));
		double[] list3 = new double[] {};
		assertEquals(0, o.numUnique(list3));
		double[] list4 = new double[] {1,2,3,4,5};
		assertEquals(5, o.numUnique(list4));
	}
	
	@Test
	public void testRemoveDuplicates() {
		double[] list = new double[] {1,1,1,1,2,3,3,4,5,5,5,5,6,7};
		assertEquals(7, o.removeDuplicates(list).length);
		double[] list1 = new double[] {1.1, 1.1, 1.2, 1.3, 3.1, 3.1};
		assertEquals(4, o.removeDuplicates(list1).length);
		double[] list2 = new double[] {1,1,1,1,1};
		assertEquals(1, o.removeDuplicates(list2).length);
		double[] list3 = new double[] {};
		assertEquals(0, o.removeDuplicates(list3).length);
	}

}
