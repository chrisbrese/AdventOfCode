package aoc.twentytwentyone.day11;

import java.util.concurrent.LinkedBlockingQueue;

import aoc.utilities.grid.Grid;
import aoc.utilities.grid.GridPosition;

/**
 * Class to break out helper methods for 2021 day 11
 */
public class TwentyTwentyOneDayEleven {
	private Grid grid;
	private LinkedBlockingQueue<GridPosition> flashQueue;
	
	/**
	 * Constructor for this class
	 * @param grid the grid to use
	 */
	public TwentyTwentyOneDayEleven(Grid grid) {
		this.grid = grid;
		flashQueue = new LinkedBlockingQueue<GridPosition>();
	}
	
	/**
	 * Helper method for checking if next position value is valid for checking inside of getFlashingOctopusCount
	 * @param grid The grid to search
	 * @param nextPos the current position to check the next position from
	 * @param prevPos the previous position to check the curPos against
	 * @return a sum of the number of flashes for the step
	 */
	public int nextPositionHelper(Grid grid, GridPosition nextPos, GridPosition prevPos) {
		int count = 0;
		if(nextPos != null && !nextPos.equals(prevPos) && !nextPos.isChecked()) {
			count += getFlashingOctopusCount(grid, nextPos);
		}
		
		return count;
	}
	
	/**
	 * Recursive method to find the grid points that are affected by an octopus flash.
	 * @param grid The grid to search
	 * @param curPos the current position to check the next position from
	 * @return a sum of the number of flashes
	 */
	public int getFlashingOctopusCount(Grid grid, GridPosition curPos) {
		int count = 0;
		
		int curRow = curPos.getRow();
		int curCol = curPos.getCol();
		
		if(curPos.getVal() == 10) {
			curPos.markAsChecked(); // can only flash once per step
			curPos.setVal(0);
			count++;
			
			GridPosition nextPos;
			
			// down
			nextPos = grid.getGridPosition(curRow+1, curCol);
			count += nextPositionHelper(grid, nextPos, curPos);
			
			// down/right
			nextPos = grid.getGridPosition(curRow+1, curCol+1);
			count += nextPositionHelper(grid, nextPos, curPos);
			
			// right
			nextPos = grid.getGridPosition(curRow, curCol+1);
			count += nextPositionHelper(grid, nextPos, curPos);
			
			// down/left
			nextPos = grid.getGridPosition(curRow+1, curCol-1);
			count += nextPositionHelper(grid, nextPos, curPos);
			
			// left
			nextPos = grid.getGridPosition(curRow, curCol-1);
			count += nextPositionHelper(grid, nextPos, curPos);
			
			// up/left
			nextPos = grid.getGridPosition(curRow-1, curCol-1);
			count += nextPositionHelper(grid, nextPos, curPos);
			
			// up
			nextPos = grid.getGridPosition(curRow-1, curCol);
			count += nextPositionHelper(grid, nextPos, curPos);
			
			// up/right
			nextPos = grid.getGridPosition(curRow-1, curCol+1);
			count += nextPositionHelper(grid, nextPos, curPos);
		}
		else {
			if(!curPos.isChecked()){
				curPos.setVal(curPos.getVal() + 1);
			}
			
			if(curPos.getVal() == 10) {
				flashQueue.add(curPos);
			}
		}
		
		return count;
	}
	
	/**
	 * The main logic method for finding octopus flashes.
	 * @param stepCount The number of steps to iterate through (max)
	 */
	public void day11DumboOctopus(int stepCount) {
		int numFlashes = 0;
		for(int step = 0; step < stepCount; step++) {
			// for part 2, check to see if all will flash simultaneously
			int flashCount = 0;
			for(int row = 0; row < grid.getNumRows(); row++) {
				for(int col = 0; col < grid.getNumCols(); col++) {
					GridPosition gp = grid.getGridPosition(row, col);
					if(gp.getVal() == 0) {
						flashCount++;
					}
				}
			}
			if(flashCount == (grid.getNumCols() * grid.getNumRows())) {
				System.out.println("2021 Day 11 Part 2: " + step);
				break;
			}			
			
			// update all cells +1
			for(int row = 0; row < grid.getNumRows(); row++) {
				for(int col = 0; col < grid.getNumCols(); col++) {
					GridPosition gp = grid.getGridPosition(row, col);
					gp.setVal(gp.getVal()+1);
					if(gp.getVal() == 10) {
						flashQueue.add(gp);
					}
				}
			}
			
			// keep looping until no more flashes
			while(flashQueue.size() > 0) {
				numFlashes += getFlashingOctopusCount(grid, flashQueue.poll());
			}
			
			// step is complete, reset the checked cells
			for(int row = 0; row < grid.getNumRows(); row++) {
				for(int col = 0; col < grid.getNumCols(); col++) {
					GridPosition gp = grid.getGridPosition(row, col);
					gp.uncheck();
				}
			}
		}
		
		System.out.println("2021 Day 11 after " + stepCount + " steps: " + numFlashes);
	}
}
