package shop.ui;

public interface Menu {
	int size();
	String getHeading();
	String getPrompt(int i);
	void runAction(int i);

}
