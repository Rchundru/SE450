package shop.ui;

public interface Form {
	int size();
    String getHeading();
	String getPrompt(int i);
	boolean checkInput(int i, String input);

}