package aoc.twentytwentyone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import aoc.Year;
import aoc.twentytwentyone.dayeleven.TwentyTwentyOneDayEleven;
import aoc.twentytwentyone.dayfour.BingoBoard;
import aoc.utilities.ReadInputFile;
import aoc.utilities.grid.Grid;
import aoc.utilities.grid.GridPosition;

public class TwentyTwentyOne extends Year {
	
	public TwentyTwentyOne() {
		CUR_YEAR = 2021;
	}
	
	/**
	 * Common method for year 2021 to build the grid and fill it with values
	 * @return the Grid object
	 */
	public Grid year2021SetupGrid() {
		Grid grid = new Grid(input.size(), input.get(0).length());
		
		int rowCount = 0;
		for(String line : input) {
			int colCount = 0;
			while(colCount < line.length()) {
				int cur = Integer.valueOf(line.substring(colCount, colCount+1));
				String row = (rowCount < 10 ? "0" + String.valueOf(rowCount) : String.valueOf(rowCount));
				String col = (colCount < 10 ? "0" + String.valueOf(colCount) : String.valueOf(colCount));
				grid.addGridPosition(row + col, new GridPosition(rowCount, colCount, cur));
				grid.getGrid()[rowCount][colCount++] = cur;
			}
			rowCount++;
		}
		
		return grid;
	}
    
	/**
	 * {@link https://adventofcode.com/2021/day/1}
	 * Run all Day 1 reports.
	 */
	public void day1(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "1");
		
		switch(part) {
			case "1":
				day1CountNumberOfTimesNumberIncreased();
				break;
			case "2":
				day1CountIncreaseOf3BlockWindow();
				break;
			default:
				day1CountNumberOfTimesNumberIncreased();
				day1CountIncreaseOf3BlockWindow();
				break;
		}		
	}
	
	public void day1CountNumberOfTimesNumberIncreased() {
		int count = 0;
		for(int i = 0; i < input.size(); i++){
			if(i != input.size()-1) {
				if(Integer.parseInt(input.get(i+1)) > Integer.parseInt(input.get(i))) {
					count++;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 1 Part 1: " + count);
	}
	
	public void day1CountIncreaseOf3BlockWindow() {
		int count = 0;
		for(int i = 0; i < input.size(); i++){
			if(i <= input.size()-4) {
				int sum1 = Integer.parseInt(input.get(i)) + Integer.parseInt(input.get(i+1)) + Integer.parseInt(input.get(i+2));
				int sum2 = Integer.parseInt(input.get(i+1)) + Integer.parseInt(input.get(i+2)) + Integer.parseInt(input.get(i+3));
				
				if(sum2 > sum1) {
					count++;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 1 Part 2: " + count);
	}
	
	/**
	 * {@link https://adventofcode.com/2021/day/2}
	 * Run all Day 2 reports.
	 */
	public void day2(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "2");
		
		switch(part) {
			case "1":
				day2MultiplyFinalGridPositionIndexes();
				break;
			case "2":
				day2MulitplyFinalGridPositionIndexesWithAim();
				break;
			default:
				day2MultiplyFinalGridPositionIndexes();
				day2MulitplyFinalGridPositionIndexesWithAim();
				break;
		}
	}
	
	public void day2MultiplyFinalGridPositionIndexes() {
		Grid grid = new Grid(2000, "E", false, false);
		int[] curPos = new int[2];
		
		for(String line : input) {
			String[] split = line.split(" ");
			
			String dir = split[0].substring(0,1);
			int num = Integer.parseInt(split[1]);
			curPos = grid.move(dir + num, false, 0, false, false);
		}
		
		System.out.println(CUR_YEAR + " Day 2 Part 1: " + (curPos[0] * curPos[1]));
	}
	
	public void day2MulitplyFinalGridPositionIndexesWithAim() {
		Grid grid = new Grid(10000, "E", false, false);
		int[] curPos = new int[2];
		
		for(String line : input) {
			String[] split = line.split(" ");
			
			String dir = split[0].substring(0,1);
			int num = Integer.parseInt(split[1]);
			curPos = grid.move(dir + num, false, 0, false, true);
		}
		
		System.out.println(CUR_YEAR + " Day 2 Part 2: " + (curPos[0] * curPos[1]));
	}
	
	/**
	 * {@link https://adventofcode.com/2021/day/3}
	 * Run all Day 3 reports.
	 */
	public void day3(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "3");
		
		switch(part) {
			case "1":
				day3CalculatePowerConsumption();
				break;
			case "2":
				day3CalculateLifeSupportRating();
				break;
			default:
				day3CalculatePowerConsumption();
				day3CalculateLifeSupportRating();
				break;
		}
	}
	
	public void day3CalculatePowerConsumption() {
		HashMap<Integer, int[]> posToBitCount = new HashMap<Integer, int[]>();
		String binaryStrGamma = "";
		String binaryStrEpsilon = "";
		
		for(String line : input) {
			char[] bits = line.toCharArray();
			for(int i = 0; i < bits.length; i++) {
				int[] updated = posToBitCount.get(i);
				if(updated == null) {
					updated = new int[2];
				}
				
				if(bits[i] == '0') {
					updated[0]++;
					posToBitCount.put(i, updated);
				}
				else if(bits[i] == '1') {
					updated[1]++;
					posToBitCount.put(i, updated);
				}
			}
		}
		
		for(int[] cur : posToBitCount.values()) {
			if(cur[0] > cur[1]) {
				binaryStrGamma += "0";
				binaryStrEpsilon += "1";
			}
			else {
				binaryStrGamma += "1";
				binaryStrEpsilon += "0";
			}
		}
		
		System.out.println(CUR_YEAR + " Day 3 Part 1: " + (Integer.parseInt(binaryStrGamma,2) * Integer.parseInt(binaryStrEpsilon,2)));
	}
	
	public void day3CalculateLifeSupportRating() {
		List<String> oxyRatingOptions = new ArrayList<String>();
		List<String> co2ScrubbingOptions = new ArrayList<String>();
		oxyRatingOptions.addAll(input);
		co2ScrubbingOptions.addAll(input);
		String binaryStringOxy = "";
		String binaryStringCo2 = "";
		
		int curBit = 0;
		while(curBit < input.get(0).length()) { // do all pieces 1 bit at a time
			int[] oxyRatingTracking = new int[2];
			int[] co2ScrubbingTracking = new int[2];
			
			// Do oxyRating first, don't continue if we've narrowed down to 1
			if(oxyRatingOptions.size() > 1) {
				for(String line : oxyRatingOptions) {
					if(line.substring(curBit, curBit+1).equals("0")) {
						oxyRatingTracking[0]++;
					}
					else {
						oxyRatingTracking[1]++;					
					}				
				}
				
				// ok, see which has a higher count
				String val = "";
				if(oxyRatingTracking[0] > oxyRatingTracking[1]) {
					val = "0";
				}
				else {
					val = "1";
				}
				
				// trim the options
				for(String line : input) {
					if(!line.substring(curBit,curBit+1).equals(val)) {
						oxyRatingOptions.remove(line);
					}
				}
			}
			else {
				// found it!
				binaryStringOxy = oxyRatingOptions.get(0);
			}
			
			// Do co2Scrubbing next, don't continue if we've narrowed down to 1
			if(co2ScrubbingOptions.size() > 1) {
				for(String line : co2ScrubbingOptions) {
					if(line.substring(curBit, curBit+1).equals("0")) {
						co2ScrubbingTracking[0]++;
					}
					else {
						co2ScrubbingTracking[1]++;					
					}				
				}

				// ok, see which has a higher count
				String val = "";
				if(co2ScrubbingTracking[0] <= co2ScrubbingTracking[1]) {
					val = "0";
				}
				else {
					val = "1";
				}
				
				// trim the options
				for(String line : input) {
					if(!line.substring(curBit,curBit+1).equals(val)) {
						co2ScrubbingOptions.remove(line);
					}
				}
			}
			else {
				// found it!
				binaryStringCo2 = co2ScrubbingOptions.get(0);
			}
			
			// stop the loop if we are done
			if(!binaryStringOxy.isEmpty() && ! binaryStringCo2.isEmpty()) {
				break;
			}
			
			curBit++;
		}
		
		System.out.println(CUR_YEAR + " Day 3 Part 2: " + (Integer.parseInt(binaryStringOxy,2) * Integer.parseInt(binaryStringCo2,2)));
	}
	
	/**
	 * {@link https://adventofcode.com/2021/day/4}
	 * Run all Day 4 reports.
	 */
	public void day4(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "4");
		
		switch(part) {
			case "1":
				day4FindWinningBingoCard(true);
				break;
			case "2":
				day4FindWinningBingoCard(false);
				break;
			default:
				day4FindWinningBingoCard(true);
				day4FindWinningBingoCard(false);
				break;
		}
	}
	
	public void day4FindWinningBingoCard(boolean getFirstWinner) {
		String[] numberCalls = input.get(0).split(",");
		List<BingoBoard> boards = new ArrayList<BingoBoard>();
		
		for(int i = 2; i < input.size(); i++) {
			List<List<String>> boardCells = new ArrayList<List<String>>();
			for(int j = i; j < (i+5); j++) {
				boardCells.add(Arrays.asList(input.get(j).split("\\s+")));				
			}
			
			boards.add(new BingoBoard(boardCells));
			i += 5;
		}
		
		int count = 1;
		int lastBoardToWin = 0;
		for(String call : numberCalls) {
			int curCall = Integer.parseInt(call);
			
			boolean winner = false;
			for(BingoBoard board : boards) {
				if(!board.isWinner()) {
					winner = false;
					int[] lastMark = board.markCell(curCall);				
					
					if(count >= 5 && lastMark != null) {
						winner = board.checkIfWinner(lastMark[0], lastMark[1]);
					}
					
					if(winner) {
						board.markAsWinner(curCall);
						lastBoardToWin = boards.indexOf(board);
						if(getFirstWinner) {
							System.out.println(CUR_YEAR + " Day 4 Part 1: " + (board.getUnmarkedCellSum() * board.getWinningCall()));
							break;
						}
					}
				}
			}
			
			if(winner && getFirstWinner) {
				break;
			}
			count++;
		}
		
		if(!getFirstWinner) {
			BingoBoard lastBoard = boards.get(lastBoardToWin);
			System.out.println(CUR_YEAR + " Day 4 Part 2: " + (lastBoard.getUnmarkedCellSum() * lastBoard.getWinningCall()));
		}
	}
	
	/**
	 * {@link https://adventofcode.com/2021/day/5}
	 * Run all Day 5 reports.
	 */
	public void day5(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "5");
		
		switch(part) {
			case "1":
				day5CalculateVentLineOverlap(false);
				break;
			case "2":
				day5CalculateVentLineOverlap(true);
				break;
			default:
				day5CalculateVentLineOverlap(false);
				day5CalculateVentLineOverlap(true);
				break;
		}
	}
	
	public void day5CalculateVentLineOverlap(boolean includeDiagonals) {
		int[][] grid = new int[1000][1000];
		int total = 0;
		for(String line : input) {
			String[] lineParts = line.split("\\s+");
			int x1 = Integer.valueOf(lineParts[0].substring(0, lineParts[0].indexOf(",")));
			int y1 = Integer.valueOf(lineParts[0].substring(lineParts[0].indexOf(",")+1));
			int x2 = Integer.valueOf(lineParts[2].substring(0, lineParts[2].indexOf(",")));
			int y2 = Integer.valueOf(lineParts[2].substring(lineParts[2].indexOf(",")+1));
			
			if(x1 == x2 || y1 == y2) {
				if(x1 == x2) {
					if(y1 < y2) {
						for(int y = y1; y <= y2; y++) {
							grid[y][x1]++;
							if(grid[y][x1] == 2) {
								total++;
							}
						}
					}
					else {
						for(int y = y1; y >= y2; y--) {
							grid[y][x1]++;
							if(grid[y][x1] == 2) {
								total++;
							}
						}
					}
				}
				else {
					if(x1 < x2) {
						for(int x = x1; x <= x2; x++) {
							grid[y1][x]++;
							if(grid[y1][x] == 2) {
								total++;
							}
						}
					}
					else {
						for(int x = x1; x >= x2; x--) {
							grid[y1][x]++;
							if(grid[y1][x] == 2) {
								total++;
							}
						}
					}
				}
			}
			else if(includeDiagonals) {
				// down and to the right
				if(x1 < x2 && y1 < y2) {
					int x = x1;
					for(int y = y1; y <= y2; y++) {
						grid[y][x]++;
						if(grid[y][x] == 2) {
							total++;
						}
						x++;
					}
				}
				// up and to the right
				else if(x1 < x2 && y1 > y2) {
					int x = x1;
					for(int y = y1; y >= y2; y--) {
						grid[y][x]++;
						if(grid[y][x] == 2) {
							total++;
						}
						x++;
					}
				}
				// down and to the left
				else if(x1 > x2 && y1 < y2) {
					int x = x1;
					for(int y = y1; y <= y2; y++) {
						grid[y][x]++;
						if(grid[y][x] == 2) {
							total++;
						}
						x--;
					}
				}
				// up and to the left
				else if(x1 > x2 && y1 > y2) {
					int x = x1;
					for(int y = y1; y >= y2; y--) {
						grid[y][x]++;
						if(grid[y][x] == 2) {
							total++;
						}
						x--;
					}
				}
			}
		}
		// print the grid
//		for(int y = 0; y < grid.length; y++) {
//			for(int x = 0; x < grid[y].length; x++) {
//				System.out.print(grid[y][x]);
//			}
//			System.out.println();
//		}
		
		if(!includeDiagonals) {
			System.out.println(CUR_YEAR + " Day 5 Part 1: " + total);
		}
		else {
			System.out.println(CUR_YEAR + " Day 5 Part 2: " + total);
		}
	}
	
	/**
	 * {@link https://adventofcode.com/2021/day/6}
	 * Run all Day 6 reports.
	 */
	public void day6(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "6");
		
		switch(part) {
			case "1":
				System.out.println(CUR_YEAR + " Day 6 Part 1: " + day6CalculateSpawningFish(80));
				break;
			case "2":
				System.out.println(CUR_YEAR + " Day 6 Part 2: " + day6CalculateSpawningFish(256));
				break;
			default:
				System.out.println(CUR_YEAR + " Day 6 Part 1: " + day6CalculateSpawningFish(80));
				System.out.println(CUR_YEAR + " Day 6 Part 2: " + day6CalculateSpawningFish(256));
				break;
		}
	}
	
	public Long day6CalculateSpawningFish(int numDays) {
		HashMap<Integer, Long> school = new HashMap<Integer, Long>(); //current time left to number of fish with that amount of time left
		
		// init
		for (String s : input.get(0).split(",")) {
			Integer cur = Integer.parseInt(s);
			if(school.containsKey(cur)) {
				school.put(cur, school.get(cur)+1L);
			}
			else {
				school.put(cur, 1L);
			}
		}
		
		int day = 1;
		while(day <= numDays) {
			HashMap<Integer, Long> newDay = new HashMap<Integer, Long>();
			
			for(Integer cur : school.keySet()) {
				if(cur == 0) {
					newDay.put(6, school.get(cur));
					newDay.put(8, school.get(cur));
				}
				else {
					// if there were any 0's above, there will be new 6's to update
					if(newDay.containsKey(cur-1)) {
						newDay.put(cur-1, school.get(cur) + newDay.get(cur-1));
					}
					else {
						newDay.put(cur-1, school.get(cur));
					}
				}
			}
			
			school = newDay;
			day++;
		}
		
		Long sum = 0L;
		for(Long num : school.values()) {
			sum += num;
		}
		return sum;
	}
	
	/**
	 * {@link https://adventofcode.com/2021/day/7}
	 * Run all Day 7 reports.
	 */
	public void day7(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "7");
		
		switch(part) {
			case "1":
				day7MinimumHorizontalMovement();
				break;
			case "2":
				day7MinimumHorizontalMovementWithFuelDegradation();
				break;
			default:
				day7MinimumHorizontalMovement();
				day7MinimumHorizontalMovementWithFuelDegradation();
				break;
		}
	}
	
	public void day7MinimumHorizontalMovement() {
		String[] numStr = input.get(0).split(",");
		int[] nums = new int[numStr.length];
		
		for(int i = 0; i < nums.length; i++) {
			nums[i] = Integer.valueOf(numStr[i]);
		}
		
		Arrays.sort(nums);
		
		// the median of the array should be the point that the crabs meet at - guarantees shortest distance
		int med = nums[Math.round(nums.length/2)];
		
		int count = 0;
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] > med) {
				count += (nums[i] - med);
			}
			else {
				count += (med - nums[i]);
			}
		}
		
		System.out.println(CUR_YEAR + " Day 7 Part 1: " + count);
	}
	
	public void day7MinimumHorizontalMovementWithFuelDegradation() {
		String[] numStr = input.get(0).split(",");
		int[] nums = new int[numStr.length];
		
		for(int i = 0; i < nums.length; i++) {
			nums[i] = Integer.valueOf(numStr[i]);
		}
		
		// This time we use average in order to minimize the distance of the furthest away crabs
		double sum = Arrays.stream(nums).sum();
		// why does floor work with my input, but round(ceil) is required for the sample input?
		double avg = Math.floor(sum/nums.length);
		
		int count = 0;
		for(int i = 0; i < nums.length; i++) {
			int tmp = 1;
			if(nums[i] > avg) {				
				for(int j = nums[i]; j > avg; j--) {
					count += tmp;
					tmp++;
				}
			}
			else {
				for(int j = nums[i]; j < avg; j++) {
					count += tmp;
					tmp++;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 7 Part 2: " + count);
	}
	
	/**
	 * {@link https://adventofcode.com/2021/day/8}
	 * Run all Day 8 reports.
	 */
	public void day8(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "8");
		
		switch(part) {
			case "1":
				day8SevenSegmentSearchUniqueNumberCount();
				break;
			case "2":
				day8SevenSegmentSearchSumOfOutputs();
				break;
			default:
				day8SevenSegmentSearchUniqueNumberCount();
				day8SevenSegmentSearchSumOfOutputs();
				break;
		}
	}
	
	public void day8SevenSegmentSearchUniqueNumberCount() {
		int count = 0;
		for(String line : input) {
			String[] parts = line.split(" \\| ");
			String[] outputParts = parts[1].split(" ");
			
			for(String out : outputParts) {
				if(out.length() == 2 || out.length() == 4 || out.length() == 3 || out.length() == 7) {
					count++;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 8 Part 1: " + count);
	}
	
	public int getNumFromString(HashMap<Integer, String> map, String in) {
		if(in.length() == 2) {        // 1
			return 1;
		}
		else if (in.length() == 4) {  // 4
			return 4;
		}
		else if(in.length() == 3) {   // 7
			return 7;
		}
		else if(in.length() == 7) {  // 8
			return 8;
		}
		else if(in.length() == 6) {  // 0, 6, 9
			boolean has1 = true;
			boolean has4 = true;
			
			if(map.containsKey(1)) { // if 1 overlaps, it is a 0
				for(char c : map.get(1).toCharArray()) {
					if(in.indexOf(c) == -1) {
						has1 = false;
						break;
					}
				}
			}
			else {
				has1 = false;
			}
			
			if(map.containsKey(4)) { // if 4 overlaps, it is a 9
				for(char c : map.get(4).toCharArray()) {
					if(in.indexOf(c) == -1) {
						has4 = false;
						break;
					}
				}
			}
			else {
				has4 = false;
			}
			
			if((!has1 && map.containsKey(1)) && (!has4 && map.containsKey(4))) {
				return 6;
			}
			else {
				if(has4) { // its a 9
					return 9;
				}
				else { // its a 0
					return 0;
				}
			}
		}
		else if(in.length() == 5) {   // 2, 3, 5
			boolean has1 = true;
			StringBuilder tmp = new StringBuilder(in);
			char deletedChar = '1';
			if(map.containsKey(1)) { // if 1 overlaps, it is a 3
				for(char c : map.get(1).toCharArray()) {
					if(in.indexOf(c) == -1) {
						has1 = false;
						deletedChar = c;
						break;
					}
				}
			}
			else {
				has1 = false;
			}
			
			if(has1 && map.containsKey(1)) { // its a 3
				return 3;
			}
			else if(deletedChar != '1'){
				boolean has4 = true;
				if(map.containsKey(4)) {
					for(char c : map.get(4).toCharArray()) {
						if(c != deletedChar && tmp.toString().indexOf(c) == -1) {
							has4 = false;
							break;
						}
					}
				}
				else {
					has4 = false;
				}
				
				if(has4 && map.containsKey(4)) { // its a 5
					return 5;
				}
				else if(!has4 && map.containsKey(4)) { // its a 2
					return 2;
				}
			}
		}
		
		return -1;
	}
	
	public void day8SevenSegmentSearchSumOfOutputs() {
		int sum = 0;
		
		for(String line : input) {
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			String[] inOutParts = line.split(" \\| ");
			
			StringBuilder outStr = new StringBuilder("aaaa");
			while(outStr.indexOf("a") > -1) {
				int cur;
				for(String in : inOutParts[0].split(" ")) {
					if((cur = getNumFromString(map, in)) != -1) {
						map.put(cur, in);
					}
				}
				
				int i = 0;
				for(String out : inOutParts[1].split(" ")) {
					if((cur = getNumFromString(map, out)) != -1) {
						map.put(cur, out);
						outStr.replace(i, i+1, String.valueOf(cur));
					}
					i++;
				}
			}
			sum += Integer.valueOf(outStr.toString());
		}
		
		System.out.println(CUR_YEAR + " Day 8 Part 2: " + sum);
	}
	
	/**
	 * {@link https://adventofcode.com/2021/day/9}
	 * Run all Day 9 reports.
	 */
	public void day9(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "9");
		
		switch(part) {
			case "1":
				day9SmokeBasin(year2021SetupGrid());
				break;
			case "2":
				day9SmokeBasinFlow();
				break;
			default:
				day9SmokeBasin(year2021SetupGrid());
				day9SmokeBasinFlow();
				break;
		}
	}
	
	public LinkedBlockingQueue<GridPosition> day9SmokeBasin(Grid grid) {
		LinkedBlockingQueue<GridPosition> basinQueue = new LinkedBlockingQueue<GridPosition>(); // holds the positions of the basins in row,col format
		int sum = 0;
		
		int[][] g = grid.getGrid();
		
		for(int row = 0; row < grid.getNumRows(); row++) {
			for(int col = 0; col < grid.getNumCols(); col++) {
				boolean foundBasin = false;
				int cur = g[row][col];
				if(cur != 9) {
					if(row == 0) {
						if(col == 0) {
							if(cur < g[row+1][col] && cur < g[row][col+1]) {
								foundBasin = true;
							}
						}
						else if(col == grid.getNumCols()-1) {
							if(cur < g[row+1][col] && cur < g[row][col-1]) {
								foundBasin = true;
							}
						}
						else {
							if(cur < g[row+1][col] && cur < g[row][col-1] && cur < g[row][col+1]) {
								foundBasin = true;
							}
						}
					}
					else if(row == grid.getNumRows()-1) {
						if(col == 0) {
							if(cur < g[row-1][col] && cur < g[row][col+1]) {
								foundBasin = true;
							}
						}
						else if(col == grid.getNumCols()-1) {
							if(cur < g[row-1][col] && cur < g[row][col-1]) {
								foundBasin = true;
							}
						}
						else {
							if(cur < g[row-1][col] && cur < g[row][col-1] && cur < g[row][col+1]) {
								foundBasin = true;
							}
						}
					}
					else {
						if(col == 0) {
							if(cur < g[row-1][col] && cur < g[row][col+1] && cur < g[row+1][col]) {
								foundBasin = true;
							}
						}
						else if(col == grid.getNumCols()-1) {
							if(cur < g[row-1][col] && cur < g[row][col-1] && cur < g[row+1][col]) {
								foundBasin = true;
							}
						}
						else {
							if(cur < g[row-1][col] && cur < g[row][col-1] && cur < g[row][col+1] && cur < g[row+1][col]) {
								foundBasin = true;
							}
						}
					}
					if(foundBasin) {
						sum += (cur+1);
						basinQueue.add(grid.getGridPosition(row, col));
					}
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 9 Part 1: " + sum);
		return basinQueue;
	}
	
	public int nextPositionHelper(Grid grid, GridPosition nextPos, GridPosition prevPos) {
		int count = 0;
		if(nextPos != null && !nextPos.equals(prevPos) && nextPos.getVal() != 9 && !nextPos.isChecked()) {
			count++;
			count += getFlowToBasinCount(grid, nextPos);
		}
		
		return count;
	}
	
	public int getFlowToBasinCount(Grid grid, GridPosition curPos) {
		int count = 0;
		
		int curRow = curPos.getRow();
		int curCol = curPos.getCol();
		
		curPos.markAsChecked();
		
		GridPosition nextPos;
		
		nextPos = grid.getGridPosition(curRow+1, curCol);
		count += nextPositionHelper(grid, nextPos, curPos);
		
		nextPos = grid.getGridPosition(curRow, curCol+1);
		count += nextPositionHelper(grid, nextPos, curPos);
		
		nextPos = grid.getGridPosition(curRow, curCol-1);
		count += nextPositionHelper(grid, nextPos, curPos);
		
		nextPos = grid.getGridPosition(curRow-1, curCol);
		count += nextPositionHelper(grid, nextPos, curPos);
		
		return count;
	}
	
	public void day9SmokeBasinFlow() {
		Grid grid = year2021SetupGrid();
		HashMap<GridPosition, Integer> flowToBasinCountMap = new HashMap<GridPosition, Integer>();
		LinkedBlockingQueue<GridPosition> basinQueue = day9SmokeBasin(grid);
		
		while(!basinQueue.isEmpty()) {
			GridPosition curBasin = basinQueue.poll();
			flowToBasinCountMap.put(curBasin, getFlowToBasinCount(grid, curBasin) + 1); // the +1 is to count the basin itself
		}

		// find the 3 largest basins
		int largestBasinSize = 0;
		int secondLargestBasinSize = 0;
		int thirdLargestBasinSize = 0;
		
		for(Integer val : flowToBasinCountMap.values()) {
			if(val > largestBasinSize) {
				thirdLargestBasinSize = secondLargestBasinSize;
				secondLargestBasinSize = largestBasinSize;
				largestBasinSize = val;
			}
			else if(val > secondLargestBasinSize) {
				thirdLargestBasinSize = secondLargestBasinSize;
				secondLargestBasinSize = val;
			}
			else if(val > thirdLargestBasinSize) {
				thirdLargestBasinSize = val;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 9 Part 2: " + 
				largestBasinSize + " * " + secondLargestBasinSize + " * " + thirdLargestBasinSize + " = " + 
				largestBasinSize * secondLargestBasinSize * thirdLargestBasinSize);
	}
	
	/**
	 * {@link https://adventofcode.com/2021/day/10}
	 * Run all Day 10 reports.
	 */
	public void day10(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "10");
		
		switch(part) {
			case "1":
				day10SyntaxScoring(false);
				break;
			case "2":
				day10SyntaxScoring(true);
				break;
			default:
				day10SyntaxScoring(false);
				day10SyntaxScoring(true);
				break;
		}
	}
	
	/**
	 * @param partTwo true if running part2 only, false if part1 only
	 */
	public void day10SyntaxScoring(boolean partTwo) {
		int badCharSum = 0;
		List<Long> incompleteSums = new ArrayList<Long>();
		for(String line : input) {
			Stack<String> lastOpenChars = new Stack<>();
			for(int i = 0; i < line.length(); i++) {
				String nextChar = line.substring(i, i+1);
				boolean found = false;
				
				if(nextChar.equals(")") || nextChar.equals("]") || nextChar.equals("}") || nextChar.equals(">")) {
					String curChar = lastOpenChars.pop();
					if(nextChar.equals(")") && curChar.equals("(")) {
						found = true;
					}
					else if(nextChar.equals("]") && curChar.equals("[")) {
						found = true;
					}
					else if(nextChar.equals("}") && curChar.equals("{")) {
						found = true;
					}
					else if(nextChar.equals(">") && curChar.equals("<")) {
						found = true;
					}
					
					if(!found) {
						if(nextChar.equals(")")) {
							badCharSum += 3;
						}
						else if(nextChar.equals("]")) {
							badCharSum += 57;
						}
						else if(nextChar.equals("}")) {
							badCharSum += 1197;
						}
						else if(nextChar.equals(">")) {
							badCharSum += 25137;
						}
						break;
					}
				}
				else {
					lastOpenChars.push(nextChar);
				}
				
				if(i == line.length()-1) {
					long incompleteSum = 0;
					while(lastOpenChars.size() > 0) {
						String cur = lastOpenChars.pop();
						incompleteSum *= 5;
						if(cur.equals("(")) {
							incompleteSum += 1;
						}
						else if(cur.equals("[")) {
							incompleteSum += 2;
						}
						else if(cur.equals("{")) {
							incompleteSum += 3;
						}
						else if(cur.equals("<")) {
							incompleteSum += 4;
						}
					}
					incompleteSums.add(incompleteSum);
				}
			}
		}
		
		long[] sums = new long[incompleteSums.size()];
		for(int i = 0; i < incompleteSums.size(); i++) {
			sums[i] = incompleteSums.get(i);
		}
		
		Arrays.sort(sums);
		long median = sums[Math.round(sums.length/2)];
		
		if(!partTwo) {
			System.out.println(CUR_YEAR + " Day 10 Part 1: " + badCharSum);
		}
		else {
			System.out.println(CUR_YEAR + " Day 10 Part 2: " + median);
		}
	}
	
	/**
	 * {@link https://adventofcode.com/2021/day/10}
	 * Run all Day 11 reports.
	 */
	public void day11(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "11");
		
		switch(part) {
			case "1":
				day11DumboOctopus(100);
				break;
			case "2":
				day11DumboOctopus(500);
				break;
			default:
				day11DumboOctopus(100);
				day11DumboOctopus(500);
				break;
		}
	}
	
	public void day11DumboOctopus(int numSteps) {
		TwentyTwentyOneDayEleven day11 = new TwentyTwentyOneDayEleven(year2021SetupGrid());
		day11.day11DumboOctopus(numSteps);
	}
	
	/**
	 * Run all Day 12 reports.
	 */
	public void day12(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "12");
		
		switch(part) {
			case "1":
				break;
			case "2":
				break;
			default:
				break;
		}
	}
	
	/**
	 * Run all Day 13 reports.
	 */
	public void day13(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "13");
		
		switch(part) {
			case "1":
				break;
			case "2":
				break;
			default:
				break;
		}
	}
	
	/**
	 * Run all Day 14 reports.
	 */
	public void day14(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "14");
		
		switch(part) {
			case "1":
				break;
			case "2":
				break;
			default:
				break;
		}
	}
	
	/**
	 * Run all Day 15 reports.
	 */
	public void day15(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "15");
		
		switch(part) {
			case "1":
				break;
			case "2":
				break;
			default:
				break;
		}
	}
	
	/**
	 * Run all Day 16 reports.
	 */
	public void day16(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "16");
		
		switch(part) {
			case "1":
				break;
			case "2":
				break;
			default:
				break;
		}
	}
	
	/**
	 * Run all Day 17 reports.
	 */
	public void day17(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "17");
		
		switch(part) {
			case "1":
				break;
			case "2":
				break;
			default:
				break;
		}
	}
	
	/**
	 * Run all Day 18 reports.
	 */
	public void day18(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "18");
		
		switch(part) {
			case "1":
				break;
			case "2":
				break;
			default:
				break;
		}
	}
	
	/**
	 * Run all Day 19 reports.
	 */
	public void day19(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "19");
		
		switch(part) {
			case "1":
				break;
			case "2":
				break;
			default:
				break;
		}
	}
	
	/**
	 * Run all Day 20 reports.
	 */
	public void day20(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "20");
		
		switch(part) {
			case "1":
				break;
			case "2":
				break;
			default:
				break;
		}
	}
	
	/**
	 * Run all Day 21 reports.
	 */
	public void day21(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "21");
		
		switch(part) {
			case "1":
				break;
			case "2":
				break;
			default:
				break;
		}
	}
	
	/**
	 * Run all Day 22 reports.
	 */
	public void day22(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "22");
		
		switch(part) {
			case "1":
				break;
			case "2":
				break;
			default:
				break;
		}
	}
	
	/**
	 * Run all Day 23 reports.
	 */
	public void day23(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "23");
		
		switch(part) {
			case "1":
				break;
			case "2":
				break;
			default:
				break;
		}
	}
	
	/**
	 * Run all Day 24 reports.
	 */
	public void day24(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "24");
		
		switch(part) {
			case "1":
				break;
			case "2":
				break;
			default:
				break;
		}
	}
	
	/**
	 * Run all Day 25 reports.
	 */
	public void day25(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "25");
		
		switch(part) {
			case "1":
				break;
			case "2":
				break;
			default:
				break;
		}
	}
}
