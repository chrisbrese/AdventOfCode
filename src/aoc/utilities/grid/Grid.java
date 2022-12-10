package aoc.utilities.grid;

import java.util.HashMap;

public class Grid {
	int[][] grid;
	HashMap<String, GridPosition> gridPositions; //used in 2021 day 9
	
	int numRows;
	int numCols;
	
	private int curRow;
	private int curCol;
	
	private int startRow;
	private int startCol;
	private int endRow;
	private int endCol;
	
	private String pointerStr;
	private int pointerNum;
	
	int[] waypointPosition = null;
	
	private int curAim;
	
	public Grid(int numRows, int numCols) {
		this(numRows, numCols, null, false, false);
	}
	
	public Grid(int gridSize, String initialPointer, boolean useWaypoint, boolean startInMiddle) {
		this(gridSize, gridSize, initialPointer, useWaypoint, startInMiddle);
	}
	
	/**
	 * Instantiates a new Grid object.
	 * @param gridSize The Max Rows/Cols of the grid.
	 * @param initialPointer The initial direction to face
	 * @param useWaypoint true if moving towards a waypoint
	 * @param startInMiddle true if starting in the middle, false if starting at [0][0]
	 */
	private Grid(int numRows, int numCols, String initialPointer, boolean useWaypoint, boolean startInMiddle) {
		gridPositions = new HashMap<String, GridPosition>();
		grid = new int[numRows][numCols];
		
		this.numRows = numRows;
		this.numCols = numCols;
		
		if(startInMiddle) {
			curRow = numRows/2;
			curCol = numCols/2;
		}
		else {
			curRow = 0;
			curCol = 0;
		}
		
		startRow = curRow;
		startCol = curCol;
		
		grid[curRow][curCol] = 1;
		
		if(initialPointer != null && !initialPointer.isEmpty()) {
			pointerStr = initialPointer;
			pointerNum = GridUtilities.getPointerNum(pointerStr);
		}
		else {
			pointerStr = "E";
			pointerNum = GridUtilities.getPointerNum(pointerStr);
		}
		
		if(useWaypoint) {
			waypointPosition = new int[2];
			waypointPosition[0] = -1; // starting row in comparison to ship
			waypointPosition[1] = 10; // starting col in comparison to ship
		}
		
		curAim = 0;
	}
	
	public String printGridPositions() {
		String ret = "";
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				GridPosition gp = getGridPosition(row, col);
				ret += gp.getVal();
			}
			ret += "\n";
		}
		
		return ret;
	}
	
	/**
	 * Get the GridPosition object for the current row/col
	 * @param row the row
	 * @param col the col
	 * @return the GridPosition
	 */
	public GridPosition getGridPosition(int row, int col) {
		String rowStr = (row < 10 ? "0" + String.valueOf(row) : String.valueOf(row));
		String colStr = (col < 10 ? "0" + String.valueOf(col) : String.valueOf(col));
		return gridPositions.get(rowStr + colStr);
	}
	
	/**
	 * Add a GridPosition to the grid
	 * @param pos is a string of row + col, example row 12 col 22 would be "1222"
	 * @param gp the grid position to add
	 */
	public void addGridPosition(String pos, GridPosition gp) {
		gridPositions.put(pos, gp);
	}
	
	public HashMap<String, GridPosition> getGridPositions(){
		return gridPositions;
	}
	
	/**
	 * Given a single line of input in the format "[A-Z][0-9*]" (e.g. N15), move around a grid.
	 * @param input The current line to parse
	 * @param checkIndividualCells if true, then we care to find the {@link maxNumInCellToEnd} by tracking individual cells
	 * @param maxNumInCellToEnd the number to reach in a cell to consider the search 'done'
	 * @param rLMeansRotate true if R/L means to rotate x degrees, if false, R/L means rotate that direction 90 degrees
	 * @param adjustAim true if u/d means to adjust aim, and modifies how f is handled (submarine puzzles)
	 * @return int[curX,curY] to indicate last position reached in the search. Can be used against getEndX() and getEndY() to determine a final state.
	 */
	public int[] move(String input, boolean checkIndividualCells, int maxNumInCellToEnd, boolean rLMeansRotate, boolean adjustAim) {
		String dir = input.substring(0, 1);
		int num = Integer.parseInt(input.substring(1));
		
		int endRowTmp = curRow;
		int endColTmp = curCol;
		
		boolean done = false;
		
		if(waypointPosition != null) {
			switch(dir) {
				case "N":
					waypointPosition[0] = waypointPosition[0] - num;
					break;
				case "E":
					waypointPosition[1] = waypointPosition[1] + num;
					break;
				case "S":
					waypointPosition[0] = waypointPosition[0] + num;
					break;
				case "W":
					waypointPosition[1] = waypointPosition[1] - num;
					break;
				case "F":
					for(int i = 0; i < num; i++) {
						endRowTmp += waypointPosition[0];
						endColTmp += waypointPosition[1];
					}
					break;
				default:
					for(int i = 0; i < (num/90); i++) {
						int tmpRow = waypointPosition[0];
						int tmpCol = waypointPosition[1];
						
						// case if north
						if(tmpRow < 0) {
							if(tmpCol >= 0) { // case if east
								if(dir.equals("R")) {
									waypointPosition[1] = -1 * tmpRow;
									waypointPosition[0] = tmpCol;
								}
								else {
									waypointPosition[1] = tmpRow;
									waypointPosition[0] = -1 * tmpCol;
								}
							}
							else { // case if west
								if(dir.equals("R")) {
									waypointPosition[1] = -1 * tmpRow;
									waypointPosition[0] = tmpCol;
								}
								else {
									waypointPosition[1] = tmpRow;
									waypointPosition[0] = -1 * tmpCol;
								}
							}
						}
						else if(tmpRow >= 0) { // case if south
							if(tmpCol >= 0) { // case if east
								if(dir.equals("R")) {
									waypointPosition[1] = -1 * tmpRow;
									waypointPosition[0] = tmpCol;
								}
								else {
									waypointPosition[1] = tmpRow;
									waypointPosition[0] = -1 * tmpCol;
								}
							}
							else { // case if west
								if(dir.equals("R")) {
									waypointPosition[1] = -1 * tmpRow;
									waypointPosition[0] = tmpCol;
								}
								else {
									waypointPosition[1] = tmpRow;
									waypointPosition[0] = -1 * tmpCol;
								}
							}
						}
						
					}
					break;
			}
		}
		else if(adjustAim) {
			if(dir.equals("u")) {
				curAim -= num;
			}
			else if(dir.equals("f")) {
				endColTmp = curCol + num;
				endRowTmp = curRow + (curAim * num);
			}
			else if(dir.equals("d")) {
				curAim += num;
			}
		}
		else {
			if(dir.equals("F")) {
				dir = pointerStr;			
			}
			if(dir.equals("U")) {
				endColTmp = curCol - num;
			}
			if(dir.equals("N") || dir.equals("u")) {
				endRowTmp = curRow - num;
			}
			else if(dir.equals("E") || dir.equals("f")) {
				endColTmp = curCol + num;
			}
			else if(dir.equals("S") || dir.equals("d")) {
				endRowTmp = curRow + num;
			}
			else if(dir.equals("W")) {
				endColTmp = curCol - num;
			}
			else if((dir.equals("L") || dir.equals("R")) && rLMeansRotate) {
				if(dir.equals("L")) {
					pointerNum = Math.floorMod((pointerNum - num), 360);
				}
				else if(dir.equals("R")) {
					pointerNum = Math.floorMod((pointerNum + num), 360);
				}
	
				pointerStr = GridUtilities.getPointerString(pointerNum);
			}
			else if(dir.equals("L")) {			
				switch(pointerStr) {
					case "N":
						endColTmp -= num;
						
						if(checkIndividualCells) {
							while(curCol > endColTmp && !done) {
								curCol--;
								grid[curRow][curCol]++;
								
								if(grid[curRow][curCol] == maxNumInCellToEnd) {
									done = true;
									break;
								}
							}
						}
						
						pointerStr = "W";
						break;
					case "E":
						endRowTmp -= num;
						
						if(checkIndividualCells) {
							while(curRow > endRowTmp && !done) {
								curRow--;
								grid[curRow][curCol]++;
								
								if(grid[curRow][curCol] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointerStr = "N";
						break;
					case "S":
						endColTmp += num;
						
						if(checkIndividualCells) {
							while(curCol < endColTmp && !done) {
								curCol++;
								grid[curRow][curCol]++;
								
								if(grid[curRow][curCol] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointerStr = "E";
						break;
					case "W":
						endRowTmp += num;
						
						if(checkIndividualCells) {
							while(curRow < endRowTmp && !done) {
								curRow++;
								grid[curRow][curCol]++;
								
								if(grid[curRow][curCol] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointerStr = "S";
						break;
				}
			}
			else if(dir.equals("R")) {
				switch(pointerStr) {
					case "N":
						endColTmp += num;
						
						if(checkIndividualCells) {
							while(curCol < endColTmp && !done) {
								curCol++;
								grid[curRow][curCol]++;
								
								if(grid[curRow][curCol] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointerStr = "E";
						break;
					case "E":
						endRowTmp += num;
						
						if(checkIndividualCells) {
							while(curRow < endRowTmp && !done) {
								curRow++;
								grid[curRow][curCol]++;
								
								if(grid[curRow][curCol] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointerStr = "S";
						break;
					case "S":
						endColTmp -= num;
						
						if(checkIndividualCells) {
							while(curCol > endColTmp && !done) {
								curCol--;
								grid[curRow][curCol]++;
								
								if(grid[curRow][curCol] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointerStr = "W";
						break;
					case "W":
						endRowTmp -= num;
						
						if(checkIndividualCells) {
							while(curRow > endRowTmp && !done) {
								curRow--;
								grid[curRow][curCol]++;
								
								if(grid[curRow][curCol] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointerStr = "N";
							break;
				}
			}
		}
		if(checkIndividualCells) {				
			if(done) {
				endRow = curRow;
				endCol = curCol;
			}
		}
		else {
			curRow = endRowTmp;
			curCol = endColTmp;
		}
		
		return new int[]{curRow, curCol};
	}
	
	public int getCurRow() {
		return curRow;
	}
	
	public int getCurCol() {
		return curCol;
	}

	public int[][] getGrid() {
		return grid;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getStartCol() {
		return startCol;
	}

	public int getEndRow() {
		return endRow;
	}

	public int getEndCol() {
		return endCol;
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumCols() {
		return numCols;
	}
}
