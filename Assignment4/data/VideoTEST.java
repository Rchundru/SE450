package shop.data;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

public class VideoTEST extends TestCase {
  public VideoTEST(String name) {
    super(name);
  }
  
  @Test
  public void testConstructorAndAttributes() {
	    String title1 = "XX";
	    String director1 = "XY";
	    String title2 = " XX ";
	    String director2 = " XY ";
	    int year = 2002;

	    VideoObj v1 = new VideoObj(title1, year, director1);
	    assertSame(title1, v1.title());
	    assertEquals(year, v1.year());
	    assertSame(director1, v1.director());

	    VideoObj v2 = new VideoObj(title2, year, director2);
	    assertEquals(title1, v2.title());
	    assertEquals(director1, v2.director());
	  }
  
  @Test
  public void testConstructorExceptionYear() {
	    try {
	      new VideoObj("X", 1800, "Y");
	      fail();
	    } catch (IllegalArgumentException e) { }
	    try {
	      new VideoObj("X", 5000, "Y");
	      fail();
	    } catch (IllegalArgumentException e) { }
	    try {
	      new VideoObj("X", 1801, "Y");
	      new VideoObj("X", 4999, "Y");
	    } catch (IllegalArgumentException e) {
	      fail();
	    }
	  }
  
  @Test
  public void testConstructorExceptionTitle() {
	    try {
	      new VideoObj(null, 2002, "Y");
	      fail();
	    } catch (IllegalArgumentException e) { }
	    try {
	      new VideoObj("", 2002, "Y");
	      fail();
	    } catch (IllegalArgumentException e) { }
	    try {
	      new VideoObj(" ", 2002, "Y");
	      fail();
	    } catch (IllegalArgumentException e) { }
	  }

  @Test
  public void testConstructorExceptionDirector() {
	    // TODO
		   try {
			      new VideoObj("test", 2002, null);
			      fail();
			    } catch (IllegalArgumentException e) { }
			    try {
			      new VideoObj("test", 2002, "");
			      fail();
			    } catch (IllegalArgumentException e) { }
			    try {
			      new VideoObj("test", 2002, " ");
			      fail();
			    } catch (IllegalArgumentException e) { }
	  }

  @Test
  public void testHashCode() {
	    assertEquals
	      (-875826552,
	       new VideoObj("None", 2009, "Zebra").hashCode());
	    assertEquals
	      (-1391078111,
	       new VideoObj("Blah", 1954, "Cante").hashCode());
	  }

  @Test
  public void testEquals() { 
	    VideoObj v1 = new VideoObj("test", 2000, "test");
	    VideoObj v2 = new VideoObj("test", 2000, "test");
	    VideoObj v3 = new VideoObj("t", 2000, "test");
	    assertFalse(v1.equals(v3));
	    assertTrue(v1.equals(v1));
	    assertTrue(v1.equals(v2));
	  }

  @Test
  public void testCompareTo() { 
		VideoObj v1 = new VideoObj("test", 2000, "test");
	    VideoObj v2 = new VideoObj("test", 2000, "test");
	    VideoObj v3 = new VideoObj("t", 1998, "t");
	    assertEquals(0, v1.compareTo(v2));
	    assertEquals(1, v1.compareTo(v3));
	    assertEquals(-1, v3.compareTo(v1));
	  }

  @Test
  public void testToString() { 
		  VideoObj v1 = new VideoObj("test", 2000, "test");
		  assertEquals("test (2000) : test", v1.toString());
	  }
}