package shop.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

final class TextUI implements UI {
  final BufferedReader _in;
  final PrintStream _out;

  TextUI() {
    _in = new BufferedReader(new InputStreamReader(System.in));
    _out = System.out;
  }

  public void displayMessage(String message) {
    _out.println(message);
  }

  public void displayError(String message) {
    _out.println(message);
  }

  private String getResponse() {
    String result;
    try {
      result = _in.readLine();
    } catch (IOException e) {
      throw new UIError(); // re-throw UIError
    }
    if (result == null) {
      throw new UIError(); // input closed
    }
    return result;
  }

  public void processMenu(UIMenu menu) {
    _out.println(menu.getHeading());
    _out.println("Enter choice by number:");

    for (int i = 1; i < menu.size(); i++) {
      _out.println("  " + i + ". " + menu.getPrompt(i));
    }

    String response = getResponse();
    int selection;
    try {
      selection = Integer.parseInt(response, 10);
      if ((selection < 0) || (selection >= menu.size()))
        selection = 0;
    } catch (NumberFormatException e) {
      selection = 0;
    }

    menu.runAction(selection);
  }

  public String[] processForm(UIForm form) {
    // TODO  
	  String[] forms = new String [form.size()];
	  int count =0;
	  while(count<forms.length) {
		 // String response = JOptionPane.showInputDialog(form.getPrompt(count));
		  _out.print(form.getPrompt(count)+ " ");
		  String response = getResponse();
		  if(form.checkInput(count, response) == false) {
			  displayError("Invalid input");
		  }
		  else {
			  forms[count] = response;
			  count++;
		  }
	  }
    return forms;
  }
}
