package shop.command;

public class CommandHistoryFactory {
  private CommandHistoryFactory() {}
  static public CommandHistory newCommandHistory() {
    // TODO
	  CommandHistory cmd = new CommandHistoryObj();
	  return cmd;
  }
}
