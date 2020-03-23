package shop.main;

import shop.ui.Menu;
import shop.ui.Form;
import shop.ui.UI;
import shop.ui.UIMenuAction;
import shop.ui.UIMenuFactory;
import shop.ui.UIFormTest;
import shop.ui.UIFormFactory;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Video;
import shop.data.Record;
import shop.command.Command;
import java.util.Iterator;

enum State{
	EXITED(0), EXIT(1), START(2);
	private final int state;
    State(int state){
		this.state=state;
	}
    public int getState() {
		return state;
	}
}

enum Add{
	ADD("Add/remove copies of a video"){
		public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory, State _state) {
			String[] result1 = _ui.processForm(_getVideoForm);
	          video = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

	          UIFormFactory f = new UIFormFactory();
	          f.add("Number of copies to add/remove", _numberTest);
	          String[] result2 = _ui.processForm(f.newForm(""));
	                                             
	          Command c = Data.newAddCmd(_inventory, video, Integer.parseInt(result2[0]));
	          if (! c.run()) {
	            _ui.displayError("Command failed");
	          
		}
	}


},
	CHECKIN("Check in a video"){
	public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory, State _state) {
		checkInOut(_ui,_getVideoForm, video, _inventory, "in");
	}

},
	CHECKOUT("Check out a video"){
	public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory, State _state) {
		checkInOut(_ui,_getVideoForm, video, _inventory, "out");
	}

},
	PRINT("Print the inventory"){
	public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory, State _state) {
		_ui.displayMessage(_inventory.toString());
	}

},
	CLEAR("Clear the inventory"){
	public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory, State _state) {
		 if (!Data.newClearCmd(_inventory).run()) {
	            _ui.displayError("Command failed");
	          }
	}

},
	UNDO("Undo"){
	public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory, State _state) {
		 if (!Data.newUndoCmd(_inventory).run()) {
	            _ui.displayError("Command failed");
	          }
	}

},
	REDO("Redo"){
	public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory, State _state) {
		 if (!Data.newRedoCmd(_inventory).run()) {
	            _ui.displayError("Command failed");
	          }
	}

},
	PRINTTOP("Print top ten all time rentals in order"){
	public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory, State _state) {
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

},
	EXIT("Exit"){
	public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory, State _state) {
	}

},
	INIT("Initialize with bogus contents"){
	public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory, State _state) {
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

	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
	;
	private String command;
	private Add(String command) {
		this.command=command;
	}
	public String getCommand() {
		return command;
	}
	public State getExitState() {
		return State.EXIT;
	}
	public void checkInOut(UI _ui, Form _getVideoForm, Video video, Inventory _inventory, String status) {
		String[] forms = _ui.processForm(_getVideoForm);
        video = Data.newVideo(forms[0], Integer.parseInt(forms[1]), forms[2]);
    	Record rec = _inventory.get(video);
    	Command c;
    	try {
    	if(status == "in") {
    		c = Data.newInCmd(_inventory, rec.video());
    	}
    	else {
    		c = Data.newOutCmd(_inventory, rec.video());
    	}
    	if(!c.run()) {
    		_ui.displayError("Command failed");
    	}
    	}catch(NullPointerException e) {
    		_ui.displayError("Command failed, video not found");
    	}
	}
	public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory,
			State _state) {
		// TODO Auto-generated method stub
		
	}
}

enum Exit implements Runnable{
	DEFAULT("Default"){
		public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory, State _state) {
		}

		public void run() {
			// TODO Auto-generated method stub
			
		}
	},
	YES("Yes"){
		public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory, State _state) {
			 _state = State.EXITED;
		}

		public void run() {
			// TODO Auto-generated method stub
			
		}
	},
	NO("No"){
		public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory, State _state) {
			_state = State.START;
		}

		public void run() {
			// TODO Auto-generated method stub
			
		}
	}
	;
	private String command;
	private Exit(String command) {
		this.command=command;
	}
	public String getCommand() {
		return command;
	}
	public State getExitedState() {
		return State.EXITED;
	}
	public State getStartState() {
		return State.START;
	}
	public void run(UI _ui, Form _getVideoForm, Video video, UIFormTest _numberTest, Inventory _inventory,
			State _state) {
		// TODO Auto-generated method stub
		
	}
}


class Control {

  private Menu[] _menus;
  private State _state;

  private Form _getVideoForm;
  private UIFormTest _numberTest;
  private UIFormTest _stringTest;
    
  private Inventory _inventory;
  private UI _ui;
  private Video video;

  
  Control(Inventory inventory, UI ui) {
    _inventory = inventory;
    _ui = ui;

    _menus = new Menu[10];
    _state = State.START;
    addSTART(State.START);
    addEXIT(State.EXIT);
    
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
      while (_state != State.EXITED) {
        _ui.processMenu(_menus[_state.getState()]);
      }
    } catch (Error e) {
      _ui.displayError("UI closed");
    }
  }
  
  private void addSTART(State stateNum) {
    UIMenuFactory m = new UIMenuFactory();
    
    m.add("Default",
      new UIMenuAction() {
        public void run() {
          _ui.displayError("doh!");
        }
      });
    Add add = Add.valueOf("ADD");
    m.add(add.getCommand(),
      new UIMenuAction() {
        public void run() {
          add.run(_ui, _getVideoForm, video, _numberTest, _inventory, _state); 
        }
        });
    Add checkin = Add.valueOf("CHECKIN");
    m.add(checkin.getCommand(),
      new UIMenuAction() {
        public void run() {
          // TODO
        	checkin.run(_ui, _getVideoForm, video, _numberTest, _inventory, _state);
        }
      });
    Add checkout = Add.valueOf("CHECKOUT");
    m.add(checkout.getCommand(),
      new UIMenuAction() {
        public void run() {
          // TODO
        	checkout.run(_ui, _getVideoForm, video, _numberTest, _inventory, _state);
        }
      });
    Add print = Add.valueOf("PRINT");
    m.add(print.getCommand(),
      new UIMenuAction() {
        public void run() {
          print.run(_ui, _getVideoForm, video, _numberTest, _inventory, _state);
        }
      });
    Add clear = Add.valueOf("CLEAR"); 
    m.add(clear.getCommand(),
      new UIMenuAction() {
        public void run() {
          clear.run(_ui, _getVideoForm, video, _numberTest, _inventory, _state);
        }
      });
    Add undo = Add.valueOf("UNDO");
    m.add(undo.getCommand(),
      new UIMenuAction() {
        public void run() {
          undo.run(_ui, _getVideoForm, video, _numberTest, _inventory, _state);
        }
      });
    Add redo = Add.valueOf("REDO");
    m.add(redo.getCommand(),
      new UIMenuAction() {
        public void run() {
          redo.run(_ui, _getVideoForm, video, _numberTest, _inventory, _state);
        }
      });
    Add printtop = Add.valueOf("PRINTTOP");
    m.add(printtop.getCommand(),
      new UIMenuAction() {
        public void run() {
          // TODO
        	printtop.run(_ui, _getVideoForm, video, _numberTest, _inventory, _state);
        }
      });
    Add exit = Add.valueOf("EXIT");      
    m.add(exit.getCommand(),
      new UIMenuAction() {
        public void run() {
          exit.run(_ui, _getVideoForm, video, _numberTest, _inventory, _state);
          _state = exit.getExitState();
        }
      });
    Add init = Add.valueOf("INIT");
    m.add(init.getCommand(),
      new UIMenuAction() {
        public void run() {
          init.run(_ui, _getVideoForm, video, _numberTest, _inventory, _state);
        }
      });
    
    _menus[2] = m.newForm("Bob's Video");
  }
  private void addEXIT(State stateNum) {
    UIMenuFactory m1 = new UIMenuFactory();
    
    Exit Default = Exit.valueOf("DEFAULT");
    m1.add(Default.getCommand(), new UIMenuAction() { public void run() {
    	Default.run(_ui, _getVideoForm, video, _numberTest, _inventory, _state);
    } });
    Exit yes = Exit.valueOf("YES");
    m1.add(yes.getCommand(),
      new UIMenuAction() {
        public void run() {
        	yes.run(_ui, _getVideoForm, video, _numberTest, _inventory, _state);
          _state = yes.getExitedState();
        }
      });
    
    Exit no = Exit.valueOf("NO");
    m1.add(no.getCommand(),
      new UIMenuAction() {
        public void run() {
        	no.run(_ui, _getVideoForm, video, _numberTest, _inventory, _state);
          _state = no.getStartState();
        }
      });
    
    _menus[1] = m1.newForm("Are you sure you want to exit?");
  }
}