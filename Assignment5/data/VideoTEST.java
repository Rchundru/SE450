package shop.data;

import junit.framework.Assert;
import junit.framework.TestCase;

// TODO:  complete the tests
public class VideoTEST extends TestCase {
  public VideoTEST(String name) {
    super(name);
  }
  public void testEquals() { 
    // TODO  
	  VideoObj v1 = new VideoObj("test", 2000, "test");
	    VideoObj v2 = new VideoObj("test", 2000, "test");
	    VideoObj v3 = new VideoObj("t", 2000, "test");
	    assertFalse(v1.equals(v3));
	    assertTrue(v1.equals(v1));
	    assertTrue(v1.equals(v2));
  }

  public void testCompareTo() { 
    // TODO  
	  VideoObj v1 = new VideoObj("test", 2000, "test");
	    VideoObj v2 = new VideoObj("test", 2000, "test");
	    VideoObj v3 = new VideoObj("t", 1998, "t");
	    assertEquals(0, v1.compareTo(v2));
	    assertEquals(1, v1.compareTo(v3));
	    assertEquals(-1, v3.compareTo(v1));
  }

  public void testToString() { 
    // TODO  
	  VideoObj v1 = new VideoObj("test", 2000, "test");
	  assertEquals("test (2000) : test", v1.toString());
  }

  public void testHashCode() {
    Assert.assertEquals
      (-875826552,
       new VideoObj("None", 2009, "Zebra").hashCode());
    Assert.assertEquals
      (-1391078111,
       new VideoObj("Blah", 1954, "Cante").hashCode());
  }
}
