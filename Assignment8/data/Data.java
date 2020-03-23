package shop.data;

import java.util.HashMap;
import java.util.Map;

import shop.command.RerunnableCommand;
import shop.command.UndoableCommand;
/**
 * A static class for accessing data objects.
 */
public class Data {
  private Data() {}
  private static HashMap videoCache = new HashMap();
  /**
   * Returns a new Inventory.
   */
  static public final Inventory newInventory() {
    return new InventorySet();
  }
  

  /**
   * Factory method for Video objects.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if Video invariant violated.
   */
   static public Video newVideo(String title, int year, String director) {
    // TODO
	  String  _title = title;
	  int _year = year;
	  String _director = director;
	  if(_title == null || _title.trim().isEmpty()) {
		  throw new IllegalArgumentException("invalid title");
	  }
	  if(_year <= 1800 || _year >= 5000) {
		  throw new IllegalArgumentException("invalid year");
	  }
	  if(_director == null || _director.trim().isEmpty()) {
		  throw new IllegalArgumentException("invalid director");
	  }
    _title = title.trim();
    _director = director.trim();
    VideoObj vid = new VideoObj(_title, _year, _director);


    return vid;
  }
   

  /**
   * Returns a command to add or remove copies of a video from the inventory.
   * <p>The returned command has the following behavior:</p>
   * <ul>
   * <li>If a video record is not already present (and change is
   * positive), a record is created.</li>
   * <li>If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.</li>
   * <li>If <code>change</code> brings the number of copies to be less
   * than one, the record is removed from the inventory.</li>
   * </ul>
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if <code>inventory<code> not created by a call to <code>newInventory</code>.
   */
   static public UndoableCommand newAddCmd(Inventory inventory, Video video, int change) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException("1");
    return new CmdAdd((InventorySet) inventory, video, change);
  }
   

  /**
   * Returns a command to check out a video.
   * @param video the video to be checked out.
   */
   static public UndoableCommand newOutCmd(Inventory inventory, Video video) {
    // TODO
	  if(!(inventory instanceof InventorySet)) {
		  throw new IllegalArgumentException("2");
	  }
	  return new CmdOut((InventorySet) inventory, video);
  }
   
  /**
   * Returns a command to check in a video.
   * @param video the video to be checked in.
   */
   static public UndoableCommand newInCmd(Inventory inventory, Video video) {
    // TODO
	  if(!(inventory instanceof InventorySet)) {
		  throw new IllegalArgumentException("3");
	  }
	  return new CmdIn((InventorySet) inventory, video);
  }
   
  
  /**
   * Returns a command to remove all records from the inventory.
   */
   static public UndoableCommand newClearCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException("4");
    return new CmdClear((InventorySet) inventory);
  }
   

  /**
   * Returns a command to undo that will undo the last successful UndoableCommand. 
   */
   static public RerunnableCommand newUndoCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException("5");
    return ((InventorySet)inventory).getHistory().getUndo();
  }
   

  /**
   * Returns a command to redo that last successfully undone command. 
   */
   static public RerunnableCommand newRedoCmd(Inventory inventory) {
    // TODO
	  if (!(inventory instanceof InventorySet))
	      throw new IllegalArgumentException("6");
	    return ((InventorySet)inventory).getHistory().getRedo();
  }
}  