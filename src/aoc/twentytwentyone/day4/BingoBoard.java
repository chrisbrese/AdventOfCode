package aoc.twentytwentyone.day4;

import java.util.List;

public class BingoBoard {
	private Cell[][] cells;
	private boolean winner;
	private Integer winningCall = null;
	
	public BingoBoard(List<List<String>> boardInput) {
		cells = new Cell[boardInput.size()][boardInput.size()];	
		winner = false;

		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(Integer.parseInt(boardInput.get(i).get(j)));
			}
		}
	}
	
	/**
	 * Mark the cell found as true. Return the position of the newly marked cell.
	 * If that cell turns out to be the one that makes the board a winner, this 
	 * will make it quicker to do the scan.
	 * @param value the value of the cell to mark
	 * @return the position of the winning value in the board
	 */
	public int[] markCell(int value) {
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				if(cells[i][j].getValue() == value) {
					cells[i][j].markCell();
					return (new int[]{i, j});
				}
			}
		}
		
		return null;
	}
	
	public boolean checkIfWinner(int col, int row) {
		boolean winner = true;
		for(int i = 0; i < cells[col].length; i++) {
			if(!cells[col][i].isMarked()) {
				winner = false;
			}
		}
		
		if(!winner) {
			for(int i = 0; i < cells[row].length; i++) {
				if(!cells[i][row].isMarked()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public int getUnmarkedCellSum() {
		int sum = 0;
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				if(!cells[i][j].isMarked()) {
					sum += cells[i][j].getValue();					
				}
			}
		}
		
		return sum;
	}
	
	public boolean isWinner() {
		return winner;
	}
	
	public void markAsWinner(int winningCall) {
		winner = true;
		this.winningCall = winningCall;
	}
	
	public Integer getWinningCall() {
		return winningCall;
	}
}
