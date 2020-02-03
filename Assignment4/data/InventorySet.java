package shop.data;

import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import shop.command.Command;

/**
 * Implementation of Inventory interface.
 * @see Data
 */
final class InventorySet implements Inventory {
  // Chose to use Map of Record, rather than RecordObj, because of
  // Java's broken generic types.  The story is too sad to retell, but
  // involves the fact that Iterable<? extends Record> is not a valid
  // type, and that Iterator<RecordObj> is not a subtype of
  // Iterator<Record>.
  //
  // Seems like the best approach for Java generics is to use the
  // external representation internally and downcast when necessary.
  private final Map<Video,Record> _data;

  InventorySet() {
    _data = new HashMap<Video,Record>();
  }

  public int size() {
    // TODO  
	  return _data.size();
  }

  public Record get(Video v) {
    // TODO  
	  for(Video key : _data.keySet()) {
		  if(key.equals(v)) {
			  return _data.get(key);
		  }
	  }
	  return null;
  }

  public Iterator<Record> iterator() {
    return Collections.unmodifiableCollection(_data.values()).iterator();
  }

  public Iterator<Record> iterator(Comparator<Record> comparator) {
    // TODO  
	  List<Record> list = new ArrayList<>(_data.values());
	    Collections.sort(list, comparator);
	    return Collections.unmodifiableCollection(list).iterator();
  }

  /**
   * Add or remove copies of a video from the inventory.
   * If a video record is not already present (and change is
   * positive), a record is created. 
   * If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.
   * If <code>change</code> brings the number of copies to be less
   * than one, the record is removed from the inventory.
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if video null or change is zero
   */
  void addNumOwned(Video video, int change) {
    // TODO 
	  if(video == null || change == 0) {
	    	throw new IllegalArgumentException();
	    }
	  if(_data.containsKey(video) == false && change > 0) {
		  if(change > 0) {
			  Record record = new RecordObj(video, change, 0, 0);
			  _data.put(video, record);
		  }
	  } 
	  else if(_data.containsKey(video) == false && change < 0) {
		  throw new IllegalArgumentException("Attempting to change records that don't exist.");
	  }
	  else {
		  if(_data.containsKey(video) && _data.get(video).numOwned() + change < _data.get(video).numOut()) {
		    	throw new IllegalArgumentException("Attempting to remove copies that are checked out");
		    }
		  else if(_data.get(video).numOwned() + change < 1) {
		    	_data.remove(video);
		    }
		  else if(_data.containsKey(video) && _data.get(video).numOwned() + change >= 1) {
		    	Record newRecord = new RecordObj(video, _data.get(video).numOwned()+change, _data.get(video).numOut(), _data.get(video).numRentals());
		    	_data.put(video, newRecord);
		    }

	  }
  }

  /**
   * Check out a video.
   * @param video the video to be checked out.
   * @throws IllegalArgumentException if video has no record or numOut
   * equals numOwned.
   */
  void checkOut(Video video) {
    // TODO  
	  if(_data.containsKey(video) == false || _data.get(video).numOwned() - _data.get(video).numOut() == 0) {
		  throw new IllegalArgumentException();
	  }
	  else {
		  RecordObj rec = new RecordObj(video, _data.get(video).numOwned(), _data.get(video).numOut()+1, _data.get(video).numRentals()+1);
		  _data.put(video, rec);
	  }
  }
  
  /**
   * Check in a video.
   * @param video the video to be checked in.
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   */
  void checkIn(Video video) {
    // TODO  
	  if(_data.containsKey(video) == false || _data.get(video).numOut() < 0 || _data.get(video).numOut() == 0) {
		  throw new IllegalArgumentException();
	  } else {
		  RecordObj rec = new RecordObj(video, _data.get(video).numOwned(), _data.get(video).numOut()-1, _data.get(video).numRentals());
		  _data.put(video, rec);
	  }
  }
  
  /**
   * Remove all records from the inventory.
   */
  void clear() {
    // TODO  
	  _data.clear();
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("Database:\n");
    for (Record r : _data.values()) {
      buffer.append("  ");
      buffer.append(r);
      buffer.append("\n");
    }
    return buffer.toString();
  }


  /**
   * Implementation of Record interface.
   *
   * <p>This is a utility class for Inventory.  Fields are mutable and
   * package-private.</p>
   *
   * <p><b>Class Invariant:</b> No two instances may reference the same Video.</p>
   *
   * @see Record
   */
  private static final class RecordObj implements Record {
    Video video; // the video
    int numOwned;   // copies owned
    int numOut;     // copies currently rented
    int numRentals; // total times video has been rented
    
    RecordObj(Video video, int numOwned, int numOut, int numRentals) {
      this.video = video;
      this.numOwned = numOwned;
      this.numOut = numOut;
      this.numRentals = numRentals;
    }
    public Video video() {
      return video;
    }
    public int numOwned() {
      return numOwned;
    }
    public int numOut() {
      return numOut;
    }
    public int numRentals() {
      return numRentals;
    }
    public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(video);
      buffer.append(" [");
      buffer.append(numOwned);
      buffer.append(",");
      buffer.append(numOut);
      buffer.append(",");
      buffer.append(numRentals);
      buffer.append("]");
      return buffer.toString();
    }
  }
}
