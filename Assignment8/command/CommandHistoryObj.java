package shop.command;
import java.util.Stack;

final class CommandHistoryObj implements CommandHistory {
  Stack<UndoableCommand> _undoStack = new Stack<UndoableCommand>();
  Stack<UndoableCommand> _redoStack = new Stack<UndoableCommand>();
  RerunnableCommand _undoCmd = new RerunnableCommand () {
      public boolean run () {
        boolean result = !_undoStack.empty();
        if (result) {
          // TODO
        	UndoableCommand cmd = _undoStack.pop();
        	_redoStack.push(cmd);
        	cmd.undo();
        }
        return result;
      }
    };
  RerunnableCommand _redoCmd = new RerunnableCommand () {
      public boolean run () {
        boolean result = !_redoStack.empty();
        if (result) {
          // TODO
        	UndoableCommand cmd = _redoStack.pop();
        	_undoStack.push(cmd);
        	cmd.redo();
        }
        return result;
      }
    };

  public void add(UndoableCommand cmd) {
    // TODO
	  _undoStack.push(cmd);
	  _redoStack.clear();
  }
  
  public RerunnableCommand getUndo() {
    return _undoCmd;
  }
  
  public RerunnableCommand getRedo() {
    return _redoCmd;
  }
  
  // For testing
  UndoableCommand topUndoCommand() {
    if (_undoStack.empty())
      return null;
    else
      return _undoStack.peek();
  }
  // For testing
  UndoableCommand topRedoCommand() {
    if (_redoStack.empty())
      return null;
    else
      return _redoStack.peek();
  }
}
