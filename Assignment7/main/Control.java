package shop.main;

import shop.ui.Form;
import shop.ui.Menu;
import shop.ui.UI;
//import shop.ui.UIMenu;
import shop.ui.UIMenuAction;
import shop.ui.UIMenuFactory;
//import shop.ui.UIMenuBuilder;
import shop.ui.UIError;
//import shop.ui.UIForm;
import shop.ui.UIFormTest;
//import shop.ui.UIFormBuilder;
import shop.ui.UIFormFactory;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Video;
import shop.data.Record;
import shop.command.Command;
import java.util.Iterator;
import java.util.Comparator;

class Control {
  private static final int EXITED = 0;
  private static final int EXIT = 1;
  private static final int START = 2;
  private static final int NUMSTATES = 10;
  private Menu[] _menus;
  private int _state;

  private Form _getVideoForm;
  private UIFormTest _numberTest;
  private UIFormTest _stringTest;
    
  private Inventory _inventory;
  private UI _ui;
  private Video video;
  
  Control(Inventory inventory, UI ui) {
    _inventory = inventory;
    _ui = ui;

    _menus = new Menu[NUMSTATES];
    _state = START;
    addSTART(START);
    addEXIT(EXIT);
    
    UIFormTest yearTest = new UIFormTest() {
        public boolean run(String input) {
          try {
            int i = Integer.parseInt(input);
            return i > 1800 && i < 5000;
          } catch (NumberFormatException e) {
            return false;
          }
        }
      };
    _numberTest = new UIFormTest() {
        public boolean run(String input) {
          try {
            Integer.parseInt(input);
            return true;
          } catch (NumberFormatException e) {
            return false;
          }
        }
      };
    _stringTest = new UIFormTest() {
        public boolean run(String input) {
          return ! "".equals(input.trim());
        }
      };

    UIFormFactory f = new UIFormFactory();
    f.add("Title", _stringTest);
    f.add("Year", yearTest);
    f.add("Director", _stringTest);
    _getVideoForm = f.newForm("Enter Video");
  }
  
  void run() {
    try {
      while (_state != EXITED) {
        _ui.processMenu(_menus[_state]);
      }
    } catch (UIError e) {
      _ui.displayError("UI closed");
    }
  }
  
  private void addSTART(int stateNum) {
    UIMenuFactory m = new UIMenuFactory();
    
    m.add("Default",
      new UIMenuAction() {
        public void run() {
          _ui.displayError("doh!");
        }
      });
    m.add("Add/Remove copies of a video",
      new UIMenuAction() {
        public void run() {
          String[] result1 = _ui.processForm(_getVideoForm);
          Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

          UIFormFactory f = new UIFormFactory();
          f.add("Number of copies to add/remove", _numberTest);
          String[] result2 = _ui.processForm(f.newForm(""));
                                             
          Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
          if (! c.run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Check in a video",
      new UIMenuAction() {
        public void run() {
          // TODO  
        	String[] forms = _ui.processForm(_getVideoForm);
            video = Data.newVideo(forms[0], Integer.parseInt(forms[1]), forms[2]);
        	Record rec = _inventory.get(video);
        	try {
        	Command c = Data.newInCmd(_inventory, rec.video());
        	if(!c.run()) {
        		_ui.displayError("Command failed");
        	}
        	}catch(NullPointerException e) {
        		_ui.displayError("Command failed, video not found");
        	}
        }
      });
    m.add("Check out a video",
      new UIMenuAction() {
        public void run() {
          // TODO  
        	String[] forms = _ui.processForm(_getVideoForm);
       	 video = Data.newVideo(forms[0], Integer.parseInt(forms[1]), forms[2]);
       	Record rec = _inventory.get(video);
       	//Video v2 = rec.video();
       	try {
       	Command c = Data.newOutCmd(_inventory, rec.video());
       	if(! c.run()) {
       		_ui.displayError("Command failed");
       	}
       	} catch(NullPointerException e) {
       		_ui.displayError("Command failed, video not found");
       	}
        }
      });
    m.add("Print the inventory",
      new UIMenuAction() {
        public void run() {
          _ui.displayMessage(_inventory.toString());
        }
      });
    m.add("Clear the inventory",
      new UIMenuAction() {
        public void run() {
          if (!Data.newClearCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Undo",
      new UIMenuAction() {
        public void run() {
          if (!Data.newUndoCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Redo",
      new UIMenuAction() {
        public void run() {
          if (!Data.newRedoCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Print top ten all time rentals in order",
      new UIMenuAction() {
        public void run() {
          // TODO  
        	int count = 10;
        	Iterator<Record> i = _inventory.iterator((r1, r2) -> r2.numRentals() - r1.numRentals());
        	String result = "";
        	while (i.hasNext() && count >0) {
        		Record rec=i.next();
        		result = result + rec.toString() + "\n";
        		count--;
        	
        	}
        	_ui.displayMessage(result);
        }
      });
          
    m.add("Exit",
      new UIMenuAction() {
        public void run() {
          _state = EXIT;
        }
      });
    
    m.add("Initialize with bogus contents",
      new UIMenuAction() {
        public void run() {
          Data.newAddCmd(_inventory, Data.newVideo("a", 2000, "m"), 1).run();
          Data.newAddCmd(_inventory, Data.newVideo("b", 2000, "m"), 2).run();
          Data.newAddCmd(_inventory, Data.newVideo("c", 2000, "m"), 3).run();
          Data.newAddCmd(_inventory, Data.newVideo("d", 2000, "m"), 4).run();
          Data.newAddCmd(_inventory, Data.newVideo("e", 2000, "m"), 5).run();
          Data.newAddCmd(_inventory, Data.newVideo("f", 2000, "m"), 6).run();
          Data.newAddCmd(_inventory, Data.newVideo("g", 2000, "m"), 7).run();
          Data.newAddCmd(_inventory, Data.newVideo("h", 2000, "m"), 8).run();
          Data.newAddCmd(_inventory, Data.newVideo("i", 2000, "m"), 9).run();
          Data.newAddCmd(_inventory, Data.newVideo("j", 2000, "m"), 10).run();
          Data.newAddCmd(_inventory, Data.newVideo("k", 2000, "m"), 11).run();
          Data.newAddCmd(_inventory, Data.newVideo("l", 2000, "m"), 12).run();
          Data.newAddCmd(_inventory, Data.newVideo("m", 2000, "m"), 13).run();
          Data.newAddCmd(_inventory, Data.newVideo("n", 2000, "m"), 14).run();
          Data.newAddCmd(_inventory, Data.newVideo("o", 2000, "m"), 15).run();
          Data.newAddCmd(_inventory, Data.newVideo("p", 2000, "m"), 16).run();
          Data.newAddCmd(_inventory, Data.newVideo("q", 2000, "m"), 17).run();
          Data.newAddCmd(_inventory, Data.newVideo("r", 2000, "m"), 18).run();
          Data.newAddCmd(_inventory, Data.newVideo("s", 2000, "m"), 19).run();
          Data.newAddCmd(_inventory, Data.newVideo("t", 2000, "m"), 20).run();
          Data.newAddCmd(_inventory, Data.newVideo("u", 2000, "m"), 21).run();
          Data.newAddCmd(_inventory, Data.newVideo("v", 2000, "m"), 22).run();
          Data.newAddCmd(_inventory, Data.newVideo("w", 2000, "m"), 23).run();
          Data.newAddCmd(_inventory, Data.newVideo("x", 2000, "m"), 24).run();
          Data.newAddCmd(_inventory, Data.newVideo("y", 2000, "m"), 25).run();
          Data.newAddCmd(_inventory, Data.newVideo("z", 2000, "m"), 26).run();
        }
      });
    
    _menus[stateNum] = m.newForm("Bob's Video");
  }
  private void addEXIT(int stateNum) {
    UIMenuFactory m = new UIMenuFactory();
    
    m.add("Default", new UIMenuAction() { public void run() {} });
    m.add("Yes",
      new UIMenuAction() {
        public void run() {
          _state = EXITED;
        }
      });
    m.add("No",
      new UIMenuAction() {
        public void run() {
          _state = START;
        }
      });
    
    _menus[stateNum] = m.newForm("Are you sure you want to exit?");
  }
}
