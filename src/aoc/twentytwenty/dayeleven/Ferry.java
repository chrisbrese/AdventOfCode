package aoc.twentytwenty.dayeleven;

import java.util.HashMap;
import java.util.List;

public class Ferry {
	private HashMap<Integer, String[][]> seatingHistory;
	private int historyCount;
	
	/**
	 * Initialize the ferry
	 * @param input the original file input to create the first seating grid
	 */
	public Ferry(List<String> input) {
		seatingHistory = new HashMap<Integer, String[][]>();
		historyCount = 0;
		String[][] seatingGrid = new String[input.size()][];
		
		int curRow = 0;
		for(String line : input) {
			seatingGrid[curRow] = new String[line.length()];
			for(int curColumn = 0; curColumn < line.length(); curColumn++) {
				seatingGrid[curRow][curColumn] = line.substring(curColumn, curColumn+1);
			}
			curRow++;
		}
		
		seatingHistory.put(historyCount, seatingGrid);
	}
	
	/**
	 * seatPeople will simultaneously seat (or re-seat) people based on the following rules:
	 *    If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
	 *    If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
	 *    Otherwise, the seat's state does not change.
	 *    Floor (.) never changes; seats don't move, and nobody sits on the floor.
	 * @param numAdjacentSeats the number of adjacent seats to be tolerant of
	 * @param extendedView true if this method should look further than immediate surroundings
	 * @return an int representing the number in the historyGrid that this seating placement occurred
	 */
	public int seatPeople(int numAdjacentSeats, boolean extendedView) {
		String[][] lastSeating = seatingHistory.get(historyCount);
		String[][] seatingGrid = new String[lastSeating.length][];
		boolean changes = false;
		
		for(int curRow = 0; curRow < lastSeating.length; curRow++) {
			seatingGrid[curRow] = new String[lastSeating[curRow].length];
			
			for(int curCol = 0; curCol < lastSeating[curRow].length; curCol++) {
				if(!lastSeating[curRow][curCol].equals(".")) {
					int adjacentSeats = 0;
					if(!extendedView) {
						for(int newRow = curRow - 1; newRow <= curRow + 1; newRow ++) {
							for(int newCol = curCol - 1; newCol <= curCol + 1; newCol ++) {
								if(newRow == curRow && newCol == curCol) {
									// nothing, this is the current cell we are checking against so skip
								}
								else {
									try {
										if(lastSeating[newRow][newCol].equals("#")) {
											adjacentSeats++;
										}
									}
									catch (IndexOutOfBoundsException ioobe) {
										// ignored - just means we're at an edge
									}
								}
							}
						}
					}
					else {
						String[] row = lastSeating[curRow];
						
						tld: // top left diagonal
						for(int newRow = curRow - 1; newRow >= 0; newRow --) {
							for(int newCol = curCol - 1; newCol >= 0; newCol --) {
								if(lastSeating[newRow][newCol].equals("#") || lastSeating[newRow][newCol].equals("L")) {
									if(lastSeating[newRow][newCol].equals("#")) {
										adjacentSeats++;
									}
									break tld;
								}
							}
						}
						
						u: // up
						for(int newRow = curRow-1; newRow >= 0; newRow--) {
							if(lastSeating[newRow][curCol].equals("#") || lastSeating[newRow][curCol].equals("L")) {
								if(lastSeating[newRow][curCol].equals("#")) {
									adjacentSeats++;
								}
								break u;
							}
						}
					
						trd: // top right diagonal
						for(int newRow = curRow - 1; newRow >= 0; newRow --) {
							for(int newCol = curCol + 1; newCol < row.length; newCol ++) {
								if(lastSeating[newRow][newCol].equals("#") || lastSeating[newRow][newCol].equals("L")) {
									if(lastSeating[newRow][newCol].equals("#")) {
										adjacentSeats++;
									}
									break trd;
								}
							}
						}
						
						l: // left
						for(int newCol = curCol-1; newCol >= 0; newCol--) {
							if(lastSeating[curRow][newCol].equals("#") || lastSeating[curRow][newCol].equals("L")) {
								if(lastSeating[curRow][newCol].equals("#")) {
									adjacentSeats++;
								}
								break l;
							}
						}
						
						r: // right
						for(int newCol = curCol+1; newCol < row.length; newCol++) {
							if(lastSeating[curRow][newCol].equals("#") || lastSeating[curRow][newCol].equals("L")) {
								if(lastSeating[curRow][newCol].equals("#")) {
									adjacentSeats++;
								}
								break r;
							}
						}
						
						bld: // bottom left diagonal
						for(int newRow = curRow + 1; newRow < lastSeating.length; newRow ++) {
							for(int newCol = curCol - 1; newCol >= 0; newCol --) {
								if(lastSeating[newRow][newCol].equals("#") || lastSeating[newRow][newCol].equals("L")) {
									if(lastSeating[newRow][newCol].equals("#")) {
										adjacentSeats++;
									}
									break bld;
								}
							}
						}
						
						d: // down
						for(int newRow = curRow+1; newRow < lastSeating.length; newRow++) {
							if(lastSeating[newRow][curCol].equals("#") || lastSeating[newRow][curCol].equals("L")) {
								if(lastSeating[newRow][curCol].equals("#")) {
									adjacentSeats++;
								}
								break d;
							}
						}
					
						brd: // bottom right diagonal
						for(int newRow = curRow + 1; newRow < lastSeating.length; newRow ++) {
							for(int newCol = curCol + 1; newCol < row.length; newCol ++) {
								if(lastSeating[newRow][newCol].equals("#") || lastSeating[newRow][newCol].equals("L")) {
									if(lastSeating[newRow][newCol].equals("#")) {
										adjacentSeats++;
									}
									break brd;
								}
							}
						}
					}
					
					if(lastSeating[curRow][curCol].equals("#") && adjacentSeats >= numAdjacentSeats) {
						seatingGrid[curRow][curCol] = "L";
						changes = true;
					}
					else if(lastSeating[curRow][curCol].equals("L") && adjacentSeats == 0) {
						seatingGrid[curRow][curCol] = "#";
						changes = true;
					}
					else {
						seatingGrid[curRow][curCol] = lastSeating[curRow][curCol];
					}
				}
				else {
					seatingGrid[curRow][curCol] = ".";
				}
			}
		}
		
		if(changes) {
			historyCount++;
			seatingHistory.put(historyCount, seatingGrid);
		}
		
		return historyCount;
	}
	
	public String[][] getHistory(int historyNumber){
		if(historyNumber < seatingHistory.size()) {
			return seatingHistory.get(historyNumber);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Print out a grid from the list of histories.
	 * @param historyNumber The history number to print (0 is the original input)
	 * @param mostRecent If true, print the last history - historyNumber would be ignored
	 * @return String representation of the requested grid to print
	 */
	public String printSeatingGrid(int historyNumber, boolean mostRecent) {
		String printOut = "";
		
		String[][] gridToPrint = null;
		if(mostRecent) {
			gridToPrint = seatingHistory.get(seatingHistory.size()-1);
		}
		else {
			if(historyNumber < seatingHistory.size()) {
				gridToPrint = seatingHistory.get(historyNumber);
			}
			else {
				printOut = "Invalid history number.";
			}
		}
		
		if(gridToPrint != null) {
			for(int curRow = 0; curRow < gridToPrint.length; curRow++) {
				String[] row = gridToPrint[curRow];
				for(int curColumn = 0; curColumn < row.length; curColumn++) {
					printOut += row[curColumn];
				}
				printOut += "\n";
			}
		}
		
		return printOut;
	}
}
