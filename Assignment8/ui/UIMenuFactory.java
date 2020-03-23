package shop.ui;

import java.util.List;

public class UIMenuFactory {
	//private Pair<String, UIMenuAction>[] array;
	private UIMenuBuilder b = new UIMenuBuilder();
	
	public void add(String prompt, UIMenuAction action) {
		b.add(prompt, action);
	}
	
	public Menu newForm(String heading) {
		//if (null == heading)
		    //  throw new IllegalArgumentException();
		   // if (t.size() <= 1)
		    //  throw new IllegalStateException();
		   // for(int i=0; i<t.size(); i++) {
		    //	array[i] = t.get(i);
		    //}
		    return b.toUIMenu(heading);
		   // return null;
	}

}
