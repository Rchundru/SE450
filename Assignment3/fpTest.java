package homework3;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import junit.framework.TestCase;


public class fpTest extends TestCase{

	fp m = new fp();
	
	@Test
	public void testMap() {
		List<String> list = new ArrayList();
		List<Integer> hashList = new ArrayList();
		List<Integer> result = new ArrayList();
		list.add("Mary");
		list.add("Isla");
		list.add("Sam");
		hashList.add(list.get(0).hashCode());
		hashList.add(list.get(1).hashCode());
		hashList.add(list.get(2).hashCode());
		Function <String, Integer> func = (x) -> x.hashCode();
		result = m.map(list, func);
		assertEquals(hashList,result);
	}
	
	@Test
	public void testSum() {
		List<Integer> nums = new ArrayList();
		nums.add(5);
		nums.add(5);
		nums.add(5);
		int sum = m.sum(nums);
		assertEquals(15, sum);
		
	}
	
	@Test
	public void testS() {
		List<String> strings = new ArrayList();
		strings.add("this");
		strings.add("is");
		strings.add("a");
		strings.add("test");
		String s = "thisisatest";
		String result = m.s(strings);
		assertEquals(result, s);
	}
	
	@Test
	public void testFilter() {
		Person p1 = new Person(110000, "Mary");
		Person p2 = new Person(60000, "Isla");
		Person p3 = new Person(105000, "Sam");
		
		List<Person> filterList = new ArrayList();
		filterList.add(p1);
		filterList.add(p2);
		filterList.add(p3);
		
		List<Person> names = new ArrayList();
		names.add(p1);
		names.add(p3);
		
		Predicate<Person> greater = i ->(i.salary>100000);
		
		List<Person> results = new ArrayList();
		results = (List<Person>) m.filter(filterList, greater);
		assertEquals(names, results);
	}
	
	@Test
	public void testMinVal() {
		List<Integer> list = new ArrayList();
		class compareInts implements Comparator<Integer> 
		{ 
		    public int compare(Integer a, Integer b) 
		    { 
		        return a.compareTo(b); 
		    } 
		} 
		
		class compareStrings implements Comparator<String> 
		{ 
		    public int compare(String a, String b) 
		    { 
		        return a.compareTo(b); 
		    } 
		} 
		Comparator<Integer> c = new compareInts();
		Comparator<String> c2 = new compareStrings();
		list.add(5);
		list.add(1);
		list.add(7);
		list.add(3);
		int result = m.minVal(list, c);
		assertEquals(result, 1);
		
		List<String> list1 = new ArrayList();
		list1.add("This");
		list1.add("String");
		list1.add("is");
		list1.add("a");
		list1.add("test");
		assertEquals("String", m.minVal(list1, c2));
	}
	
	@Test
	public void testMinValMax() {
		List<Integer> list = new ArrayList();
		class compareInts implements Comparator<Integer> 
		{ 
		    public int compare(Integer a, Integer b) 
		    { 
		        return b.compareTo(a); 
		    } 
		} 
		Comparator<Integer> c = new compareInts();
		list.add(5);
		list.add(1);
		list.add(7);
		list.add(3);
		int result = m.minVal(list, c);
		assertEquals(result, 7);
		
	}
	
	@Test
	public void testMinPos() {
		List<Integer> list = new ArrayList();
		list.add(5);
		list.add(8);
		list.add(1);
		list.add(3);
		list.add(11);
		int minPos = m.minPos(list);
		assertEquals(2,minPos);
	}
	
	@Test
	public void testMinPosStrings() {
		List<String> list = new ArrayList();
		list.add("This");
		list.add("is");
		list.add("a");
		list.add("String");
		list.add("test");
		int minPos = m.minPos(list);
		assertEquals(3,minPos);
	}
	
	
}
