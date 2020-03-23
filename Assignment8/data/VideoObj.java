package shop.data;

import java.util.Map;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
  private final String _title;
  private final int    _year;
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if object invariant violated.
   */
  VideoObj(String title, int year, String director) {
	  if(title == null || title.trim().isEmpty()) {
		  throw new IllegalArgumentException();
	  }
	  if(year <= 1800 || year >= 5000) {
		  throw new IllegalArgumentException();
	  }
	  if(director == null || director.trim().isEmpty()) {
		  throw new IllegalArgumentException();
	  }
    _title = title.trim();
    _director = director.trim();
    _year = year;
  }

  public String director() {
    // TODO
    return _director;
  }

  public String title() {
    // TODO
    return _title;
  }

  public int year() {
    // TODO
    return _year;
  }

  public boolean equals(Object thatObject) {
    // TODO
	  if(thatObject == this) {
		  return true;
	  }
	  if(!(thatObject instanceof VideoObj)) {
		  return false;
	  }
	  VideoObj vo = (VideoObj) thatObject;
	  return vo.director()==_director && vo.title() == _title && vo.year() == _year;
  }

  public int hashCode() {
    // TODO
	  int result = 17;
	    result = 37*result + _title.hashCode();
	    result = 37*result + _year; 
	    result = 37*result + _director.hashCode();
	    return result;
  }

  public int compareTo(Video that) {
    // TODO
	  Video vo = that;
	  if(_title.compareTo(vo.title())<0) {
		  return -1;
	  }
	  if(_title.compareTo(vo.title())>0) {
		  return 1;
	  }
	  if(_year < vo.year()) {
		  return -1;
	  }
	  if(_year > vo.year()) {
		  return 1;
	  }
	  if(_director.compareTo(vo.director())<0) {
		  return -1;
	  }
	  if(_director.compareTo(vo.director())>0) {
		  return 1;
	  }
	  
	  
    return 0;
  }

  public String toString() {
    // TODO
	  return title()+ " ("+year()+") : " +director();
  }
}
