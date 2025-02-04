package shop.command;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

public class CommandHistoryTEST extends TestCase {
  public CommandHistoryTEST(String name) {
    super(name);
  }

  @Test
  public void testEmptyExceptions() {
    CommandHistoryObj h = new CommandHistoryObj();
    assertSame(null, h.topUndoCommand());
    assertSame(null, h.topRedoCommand());
    assertFalse(h.getUndo().run());
    assertFalse(h.getRedo().run());
  }

  @Test
  private void checkStacks(CommandHistoryObj h, UndoableCommand topUndo, UndoableCommand topRedo) {
    assertSame(topUndo, h.topUndoCommand());
    assertSame(topRedo, h.topRedoCommand());
  }

  @Test
  public void testThatTopIsSetByAddUndoAndRedo() {
    CommandHistoryObj h = new CommandHistoryObj();

    class CmdSuccess implements UndoableCommand {
      public boolean run() { return true; }
      public void undo() { }
      public void redo() { }
    }

    UndoableCommand x1 = new CmdSuccess();
    UndoableCommand x2 = new CmdSuccess();
    UndoableCommand x3 = new CmdSuccess();

    h.add(x1);          checkStacks(h, x1,   null);
    h.getUndo().run();  checkStacks(h, null, x1);
    h.getRedo().run();  checkStacks(h, x1,   null);

    h.add(x2);          checkStacks(h, x2,   null);
    h.getUndo().run();  checkStacks(h, x1,   x2);
    h.getUndo().run();  checkStacks(h, null, x1);
    h.getRedo().run();  checkStacks(h, x1,   x2);
    h.getRedo().run();  checkStacks(h, x2,   null);

    h.getUndo().run();  checkStacks(h, x1,   x2);
    h.add(x3);          checkStacks(h, x3,   null);
    h.getUndo().run();  checkStacks(h, x1,   x3);
    h.getUndo().run();  checkStacks(h, null, x1);
    h.getRedo().run();  checkStacks(h, x1,   x3);
    h.getRedo().run();  checkStacks(h, x3,   null);

    h = new CommandHistoryObj();
    h.add(x1);          checkStacks(h, x1,   null);  
    h.add(x2);          checkStacks(h, x2,   null);
    h.getUndo().run();  checkStacks(h, x1,   x2);  
    h.getRedo().run();  checkStacks(h, x2,   null);  
    h.add(x3);          checkStacks(h, x3,   null);  
    h.getUndo().run();  checkStacks(h, x2,   x3);
    h.getUndo().run();  checkStacks(h, x1,   x2);
  }

  // these must be fields so that they can be changed by the instances
  // of the inner class TestThatMethodsArePerformed.
  private boolean _didRun;
  private boolean _didUndo;
  private boolean _didRedo;

  @Test
  public void testThatMethodsArePerformed() {
    CommandHistoryObj h = new CommandHistoryObj();

    class MockCommand implements UndoableCommand {
      // Using "CommandHistoryTEST.this" to make references to
      // outer class instance explicit
      public boolean run() {
        CommandHistoryTEST.this._didRun = true;
        return true;
      }
      public void undo() {
        CommandHistoryTEST.this._didUndo = true;
      }
      public void redo() {
        CommandHistoryTEST.this._didRedo = true;
      }
    }

    UndoableCommand x = new MockCommand();

    _didRun = _didUndo = _didRedo = false;
    h.add(x);
    assertTrue(!_didRun && !_didUndo && !_didRedo);

    _didRun = _didUndo = _didRedo = false;
    h.getUndo().run();
    assertTrue(!_didRun && _didUndo && !_didRedo);

    _didRun = _didUndo = _didRedo = false;
    h.getRedo().run();
    assertTrue(!_didRun && !_didUndo && _didRedo);
  }
}
