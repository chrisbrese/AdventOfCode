package aoc.utilities.grid;

public class GridPosition {
	private int row;
	private int col;	
	private int val;
	private boolean checked;
	
	public GridPosition(int row, int col, int val) {
		this.row = row;
		this.col = col;		
		this.val = val;
		checked = false;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getVal() {
		return val;
	}
	
	public void setVal(int val) {
		this.val = val;
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	public void markAsChecked() {
		checked = true;
	}
	
	public void uncheck() {
		checked = false;
	}
	
	public boolean equals(GridPosition gp) {
		if(this.row == gp.row && this.col == gp.col) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return String.valueOf(val);
	}
}