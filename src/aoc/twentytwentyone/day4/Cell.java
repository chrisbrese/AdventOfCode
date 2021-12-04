package aoc.twentytwentyone.day4;

public class Cell {
	private int value;
	private boolean marked;
	
	public Cell(int value) {
		this.value = value;
		marked = false;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public void markCell() {
		this.marked = true;
	}
}
