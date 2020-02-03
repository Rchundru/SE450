// TODO:  complete the methods
/**
 * Immutable Data Class for video objects.
 * Comprises a triple: title, year, director.
 *
 * @objecttype Immutable Data Class
 * @objectinvariant
 *   Title is non-null, no leading or final spaces, not empty string.
 * @objectinvariant
 *   Year is greater than 1800, less than 5000.
 * @objectinvariant
 *   Director is non-null, no leading or final spaces, not empty string.
 */
final class VideoObj implements Comparable {
  /** @invariant non-null, no leading or final spaces, not empty string */
  private final String _title;
  /** @invariant greater than 1800, less than 5000 */
  private final int    _year;
  /** @invariant non-null, no leading or final spaces, not empty string */
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if any object invariant is violated.
   */
  VideoObj(String title, int year, String director) {
    // TODO  
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
    _year = year;
    _director = director.trim();
     
  }

  /**
   * Return the value of the attribute.
   */
  public String director() {
    // TODO  
    return _director;
  }

  /**
   * Return the value of the attribute.
   */
  public String title() {
    // TODO  
    return _title;
  }

  /**
   * Return the value of the attribute.
   */
  public int year() {
    // TODO  
    return _year;
  }

  /**
   * Compare the attributes of this object with those of thatObject.
   * @param thatObject the Object to be compared.
   * @return deep equality test between this and thatObject.
   */
  public boolean equals(Object thatObject) {
    // TODO  
	  if(thatObject == this) {
		  return true;
	  }
	  if(!(thatObject instanceof VideoObj)) {
		  return false;
	  }
	  VideoObj vo = (VideoObj) thatObject;
	  return vo.director()==_director && vo.title().toString() == _title && vo.year() == _year;
  }

  /**
   * Return a hash code value for this object using the algorithm from Bloch:
   * fields are added in the following order: title, year, director.
   */
  public int hashCode() {
    // TODO  
	  int result = 17;
	    result = 37*result + _title.hashCode();
	    result = 37*result + _year; 
	    result = 37*result + _director.hashCode();
	    return result;
  }

  /**
   * Compares the attributes of this object with those of thatObject, in
   * the following order: title, year, director.
   * @param thatObject the Object to be compared.
   * @return a negative integer, zero, or a positive integer as this
   *  object is less than, equal to, or greater thatObject.
   * @throws ClassCastException if thatObject has an incompatible type.
   */
  public int compareTo(Object thatObject) {
    // TODO  
	  VideoObj vo = (VideoObj) thatObject;
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

  /**
   * Return a string representation of the object in the following format:
   * <code>"title (year) : director"</code>.
   */
  public String toString() {
    // TODO  
	  return title()+ " ("+year()+") : " +director();
  }
}
