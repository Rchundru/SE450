package shop.main;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import shop.command.Command;
import shop.data.Data;
import shop.data.Record;
import shop.data.Video;
import shop.data.Inventory;
import java.util.Iterator;

// TODO:
// write an integration test that tests the data classes.
// add in some videos, check out, check in, delete videos, etc.
// check that errors are reported when necessary.
// check that things are going as expected.
public class TEST1 extends TestCase {
  private Inventory _inventory = Data.newInventory();
  public TEST1(String name) {
    super(name);
  }
  
  private void check(Video v, int numOwned, int numOut, int numRentals) {
    Record r = _inventory.get(v);
    assertEquals(numOwned, r.numOwned());
    assertEquals(numOut, r.numOut());
    assertEquals(numRentals, r.numRentals());
  }
    
  @Test
  public void test1() {
    Command clearCmd = Data.newClearCmd(_inventory);
    clearCmd.run();
    
    Video v1 = Data.newVideo("Title1", 2000, "Director1");
    Video v2 = Data.newVideo("Title2", 2005, "Director2");
    boolean cmd;
    assertEquals(0, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    check(v1,10,0,0);
    cmd = Data.newAddCmd(_inventory, v2, 5).run();
    assertTrue(cmd);
    assertEquals(2, _inventory.size());
    check(v2,5,0,0);
    cmd = Data.newAddCmd(_inventory, null, 5).run();
    assertFalse(cmd);
    assertEquals(2, _inventory.size());
    // TODO
  }
}
