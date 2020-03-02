package shop.ui;

import java.util.ArrayList;
import java.util.List;

    public final class UIFormFactory {

    	private Pair<String, UIFormTest>[] array;
	private UIFormBuilder b = new UIFormBuilder();
    	
    	public void add(String prompt, UIFormTest test) {
    		b.add(prompt, test);
    	}
	public UIForm newForm(String heading) {
	    /*if (null == heading)
	      throw new IllegalArgumentException();
	    if (t.size() < 1)
	      throw new IllegalStateException();
	    for (int i = 0; i < t.size(); i++)
	      array[i] = t.get(i);
	    return new UIForm(heading, array);*/
		return b.toUIForm(heading);
	  }

}