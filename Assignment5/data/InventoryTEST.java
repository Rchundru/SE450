package shop.data;

import junit.framework.Assert;
import junit.framework.TestCase;
import shop.command.UndoableCommand;
import shop.command.CommandHistory;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

// TODO:  complete the tests
public class InventoryTEST extends TestCase {
  public InventoryTEST(String name) {
    super(name);
  }
  // TODO  
  InventorySet s = new InventorySet();
  final VideoObj v1 = new VideoObj( "A", 2000, "B" );
  final VideoObj v2 = new VideoObj( "B", 2000, "B" );
  
  public void testSize() {
    // TODO 
	  assertEquals( 0, s.size() );
      s.addNumOwned(v1,  1); 
      assertEquals( 1, s.size() );
      s.addNumOwned(v1,  2); 
      assertEquals( 1, s.size() );
      s.addNumOwned(v2,  1); 
      assertEquals( 2, s.size() );
      s.addNumOwned(v2, -1); 
      assertEquals( 1, s.size() );
      s.addNumOwned(v1, -3); 
      assertEquals( 0, s.size() );
      try { 
    	  s.addNumOwned(v1, -3); 
    	  } catch ( IllegalArgumentException e ) {}
      assertEquals( 0, s.size() );
  }

  public void testAddNumOwned() {
    // TODO  
	  assertEquals( 0, s.size() );
      s.addNumOwned(v1, 1);     
      assertEquals( v1, s.get(v1).video());
      assertEquals( 1, s.get(v1).numOwned());
      s.addNumOwned(v1, 2);     
      assertEquals(3, s.get(v1).numOwned());
      s.addNumOwned(v1, -1);    
      assertEquals(2, s.get(v1).numOwned());
      s.addNumOwned(v1, -2);    
      assertEquals(0, s.size());
  }

  public void testCheckOutCheckIn() {
    // TODO 
	  s.addNumOwned(v1, 5);
	  s.checkOut(v1);     
	  assertEquals(1, s.get(v1).numOut());
	  s.checkOut(v1);     
	  assertEquals(2, s.get(v1).numOut());
	  s.checkOut(v1);     
	  assertEquals(3, s.get(v1).numOut());
	  s.checkOut(v1);     
	  assertEquals(4, s.get(v1).numOut());
	  s.checkOut(v1);     
	  assertEquals(5, s.get(v1).numOut());
	  try{
		  s.checkOut(v1);
		  }   catch(IllegalArgumentException e){}  
	  assertEquals(5, s.get(v1).numOut());
	  
	  s.checkIn(v1);     
	  assertEquals(4, s.get(v1).numOut());
	  s.checkIn(v1);     
	  assertEquals(3, s.get(v1).numOut());
	  s.checkIn(v1);     
	  assertEquals(2, s.get(v1).numOut());
	  s.checkIn(v1);     
	  assertEquals(1, s.get(v1).numOut());
	  s.checkIn(v1);     
	  assertEquals(0, s.get(v1).numOut());
	  s.addNumOwned(v1, -5);    
	  assertEquals(0, s.size());
  }

  public void testClear() {
    // TODO 
	  s.addNumOwned(v1, 4);
	  s.addNumOwned(v2, 4);
	  s.clear();
      assertEquals(0, s.size());
  }

  public void testGet() {
    // TODO  
	  s.addNumOwned(v1, 5);
	  assertEquals(v1, s.get(v1).video());
	  s.addNumOwned(v1, -5);
	  assertEquals(0, s.size());
  }

  public void testIterator1() {
    // TODO  
	  Set<Video> expected = new HashSet<Video>();
	    s.addNumOwned(v1,1);
	    s.addNumOwned(v2,2);
	    expected.add(v1);
	    expected.add(v2);
	    Iterator<Record> i = s.iterator();
	    while(i.hasNext()) {
	      Record rec = i.next(); 
	      assertTrue(expected.contains(rec.video()));
	      expected.remove(rec.video());
	    }
	    assertTrue(expected.isEmpty());
  }
  public void testIterator2() {
    // TODO  
	  List<Video> list = new ArrayList<Video>();
	  list.add(v1);
	  list.add(v2);
	  s.addNumOwned(v1, 5);
	  s.addNumOwned(v2, 5);
	  Comparator<Record> c = new Comparator<Record>() {
		  public int compare(Record rec1, Record rec2) {
			  return rec1.video().year() - rec2.video().year();
		  }
	  };
	  Iterator<Record> it = s.iterator(c);
	  Iterator it2 = list.iterator();
	  while(it2.hasNext()) {
		  assertEquals(it2.next(), it.next().video());
		  it2.remove();
	  }
	  assertTrue(list.isEmpty());
  }
}
