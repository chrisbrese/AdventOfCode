package aoc.utilities.grid;

import java.util.HashMap;
import java.util.List;

public class GridUtilities {
	@SuppressWarnings("serial")
	private static final HashMap<String, Integer> pointerLookup = 
			new HashMap<String, Integer>() {{
		put("N", 0);
		put("E", 90);
		put("S", 180);
		put("W", 270);
	}};
	
	/**
	 * Helper method to work with the Grid object
	 * @param input The list of input for the day
	 * @param gridSize The size of the grid to initialize
	 * @param initialPointer The way the object is facing
	 * @param checkIndividualCells true if counting all touched spaces on the grid, false if only care about final destination
	 * @param maxNumInCellToEnd the number to reach in a cell to consider the search 'done'
	 * @param rLMeansRotate true if R/L means to rotate x degrees, if false, R/L means rotate that direction 90 degrees
	 */
	public static int howManyBlocks(List<String> input, int gridSize, String initialPointer, boolean checkIndividualCells, int maxNumInCellToEnd, boolean rlMeansRotate) {
		Grid grid = new Grid(gridSize, initialPointer);
		int blocks = 0;

		int[] lastPosition = null;
		for(String d : input) {
			lastPosition = grid.move(d, checkIndividualCells, maxNumInCellToEnd, rlMeansRotate);
			
			if(checkIndividualCells) {
				int endX = grid.getEndRow();
				int endY = grid.getEndCol();
				
				if(endX == lastPosition[0] && endY == lastPosition[1]) {
					break;
				}
			}
		}
		
		blocks = Math.abs(lastPosition[0]-grid.getStartRow()) + Math.abs(lastPosition[1]-grid.getStartCol());
				
		return blocks;
	}
	
	public static Integer getPointerNum(String pointerStr) {
		return pointerLookup.get(pointerStr);
	}
	
	public static String getPointerString(int pointerNum) {
		int tmp = pointerNum;
		
		for(String s : pointerLookup.keySet()) {
			if(getPointerNum(s) == tmp) {
				return s;
			}
		}
		
		return null;
	}
}
