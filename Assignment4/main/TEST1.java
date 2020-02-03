package shop.main;


import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;
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
  
  @Test
  private void expect(Video v, String s) {
    assertEquals(s,_inventory.get(v).toString());
  }
  
  @Test
  private void expect(Record r, String s) {
    assertEquals(s,r.toString());
  }
  
  @Test
  public void test1() {
    Command clearCmd = Data.newClearCmd(_inventory);
    clearCmd.run();
    
    Video v1 = Data.newVideo("Title1", 2000, "Director1");
    assertEquals(0, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    // System.out.println(_inventory.get(v1));
    expect(v1,"Title1 (2000) : Director1 [10,0,0]");
    Data.newAddCmd(_inventory, v1, 5).run();
    assertTrue(Data.newOutCmd(_inventory, v1).run());
    assertEquals(1, _inventory.get(v1).numOut());
    assertTrue(Data.newOutCmd(_inventory, v1).run());
    assertEquals(2, _inventory.get(v1).numOut());
    assertTrue(Data.newOutCmd(_inventory, v1).run());
    assertEquals(3, _inventory.get(v1).numOut());
    assertTrue(Data.newOutCmd(_inventory, v1).run());
    assertEquals(4, _inventory.get(v1).numOut());
    assertTrue(Data.newOutCmd(_inventory, v1).run());
    assertEquals(5, _inventory.get(v1).numOut());
    assertTrue(Data.newInCmd(_inventory, v1).run());
    assertEquals(4, _inventory.get(v1).numOut());
    assertTrue(Data.newInCmd(_inventory, v1).run());
    assertEquals(3, _inventory.get(v1).numOut());
    assertTrue(Data.newInCmd(_inventory, v1).run());
    assertEquals(2, _inventory.get(v1).numOut());
    assertTrue(Data.newInCmd(_inventory, v1).run());
    assertEquals(1, _inventory.get(v1).numOut());
    assertTrue(Data.newInCmd(_inventory, v1).run());
    assertEquals(0, _inventory.get(v1).numOut());
    Data.newAddCmd(_inventory, v1, -15).run();
    assertEquals(0, _inventory.size());
    // TODO
  }
}
