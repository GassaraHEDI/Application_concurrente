package Prog_TP1;

public class CountLines {
	private int lineNumbers  ;
	static private CountLines instance = new CountLines();
	
	private CountLines() {
		lineNumbers=0;
	}
	static CountLines getInstance() {
		return instance;
	}
	public synchronized void addlinesNumber(int l) {
		lineNumbers+=l;
	}
	public int getLineNumbers() {
		return lineNumbers;
	}
}
