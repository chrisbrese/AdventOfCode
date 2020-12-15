package aoc.utilities.grid;

public class Grid {
	private int GRID_SIZE;
	
	int[][] grid;
	
	private int curRow;
	private int curCol;
	
	private int startRow;
	private int startCol;
	private int endRow;
	private int endCol;
	
	private String pointerStr;
	private int pointerNum;
	
	/**
	 * Instantiates a new Grid object.
	 * @param gridSize The Max Rows/Cols of the grid.
	 * @param initialPointer The initial direction to face
	 */
	public Grid(int gridSize, String initialPointer) {
		GRID_SIZE = gridSize;
		
		grid = new int[GRID_SIZE][GRID_SIZE];
		
		curRow = GRID_SIZE/2;
		curCol = GRID_SIZE/2;
		
		startRow = curRow;
		startCol = curCol;
		
		grid[curRow][curCol] = 1;
		
		pointerStr = initialPointer;
		pointerNum = GridUtilities.getPointerNum(initialPointer);
	}
	
	/**
	 * Given a single line of input in the format "[A-Z][0-9*]" (e.g. N15), move around a grid.
	 * @param input The current line to parse
	 * @param checkIndividualCells if true, then we care to find the {@link maxNumInCellToEnd} by tracking individual cells
	 * @param maxNumInCellToEnd the number to reach in a cell to consider the search 'done'
	 * @param rLMeansRotate true if R/L means to rotate x degrees, if false, R/L means rotate that direction 90 degrees
	 * @return int[curX,curY] to indicate last position reached in the search. Can be used against getEndX() and getEndY() to determine a final state.
	 */
	public int[] move(String input, boolean checkIndividualCells, int maxNumInCellToEnd, boolean rLMeansRotate) {
		String dir = input.substring(0, 1);
		int num = Integer.parseInt(input.substring(1));
		
		int endRowTmp = curRow;
		int endColTmp = curCol;
		
		boolean done = false;
		
		if(dir.equals("F")) {
			dir = pointerStr;			
		}
		
		if(dir.equals("N")) {
			endRowTmp = curRow - num;
		}
		else if(dir.equals("E")) {
			endColTmp = curCol + num;
		}
		else if(dir.equals("S")) {
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
		
		public int getGridSize() {
			return GRID_SIZE;
		}
}
