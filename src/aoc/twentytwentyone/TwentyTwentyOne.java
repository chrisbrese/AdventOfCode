package aoc.twentytwentyone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import aoc.Year;
import aoc.twentytwentyone.day4.BingoBoard;
import aoc.utilities.ReadInputFile;
import aoc.utilities.grid.Grid;

public class TwentyTwentyOne extends Year {
	
	public TwentyTwentyOne() {
		CUR_YEAR = 2021;
	}
    
	/**
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
	
	/**
	 * Figure out how quickly the depth increases.
	 * To do this, count the number of times a depth measurement increases from the previous measurement. (There is no measurement before the first measurement.)
	 * How many measurements are larger than the previous measurement?
	 */
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
	
	/**
	 * Consider sums of a three-measurement sliding window.
	 * Start by comparing the first and second three-measurement windows.
	 * Your goal now is to count the number of times the sum of measurements in this sliding window increases from the previous sum.
	 * Stop when there aren't enough measurements left to create a new three-measurement sum.
	 * How many sums are larger than the previous sum?
	 */
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
	
	/**
	 * It seems like the submarine can take a series of commands like forward 1, down 2, or up 3.
	 * Note that since you're on a submarine, down and up affect your depth, and so they have the opposite result of what you might expect.
	 * The submarine seems to already have a planned course (your puzzle input).
	 * Your horizontal position and depth both start at 0.
	 * Calculate the horizontal position and depth you would have after following the planned course. What do you get if you multiply your final horizontal position by your final depth?
	 */
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
	
	/**
	 * Based on your calculations, the planned course doesn't seem to make any sense.
	 * You find the submarine manual and discover that the process is actually slightly more complicated.
	 * In addition to horizontal position and depth, you'll also need to track a third value, aim, which also starts at 0.
	 * The commands also mean something entirely different than you first thought:
	 *     - down X increases your aim by X units.
	 *     - up X decreases your aim by X units.
	 *     - forward X does two things:
	 *         a. It increases your horizontal position by X units.
	 *         b. It increases your depth by your aim multiplied by X.
	 * Using this new interpretation of the commands, calculate the horizontal position and depth you would have after following the planned course.
	 * What do you get if you multiply your final horizontal position by your final depth?
	 */
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
	
	/**
	 * The diagnostic report (your puzzle input) consists of a list of binary numbers which, when decoded properly, can tell you many useful things about the conditions of the submarine.
	 * The first parameter to check is the power consumption.
	 * You need to use the binary numbers in the diagnostic report to generate two new binary numbers (called the gamma rate and the epsilon rate).
	 * The power consumption can then be found by multiplying the gamma rate by the epsilon rate.
	 * Each bit in the gamma rate can be determined by finding the most common bit in the corresponding position of all numbers in the diagnostic report.
	 * The epsilon rate is calculated in a similar way; rather than use the most common bit, the least common bit from each position is used.
	 * Use the binary numbers in your diagnostic report to calculate the gamma rate and epsilon rate, then multiply them together.
	 * What is the power consumption of the submarine? (Be sure to represent your answer in decimal, not binary.)
	 */
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
	
	/**
	 * Next, you should verify the life support rating, which can be determined by multiplying the oxygen generator rating by the CO2 scrubber rating.
	 * Before searching for either rating value, start with the full list of binary numbers from your diagnostic report.
	 * Then:
	 *    - Keep only numbers selected by the bit criteria for the type of rating value for which you are searching. Discard numbers which do not match the bit criteria.
	 *    - If you only have one number left, stop; this is the rating value for which you are searching.
	 *    - Otherwise, repeat the process, considering the next bit to the right.
	 * 
	 * The bit criteria depends on which type of rating value you want to find:
	 *    - To find oxygen generator rating, determine the most common value (0 or 1) in the current bit position, and keep only numbers with that bit in that position.
	 *      If 0 and 1 are equally common, keep values with a 1 in the position being considered.
	 *    - To find CO2 scrubber rating, determine the least common value (0 or 1) in the current bit position, and keep only numbers with that bit in that position.
	 *      If 0 and 1 are equally common, keep values with a 0 in the position being considered.
	 * Use the binary numbers in your diagnostic report to calculate the oxygen generator rating and CO2 scrubber rating, then multiply them together.
	 * What is the life support rating of the submarine? (Be sure to represent your answer in decimal, not binary.)
	 */
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
	
	/**
	 * What you can see, is a giant squid that has attached itself to the outside of your submarine.
	 * Maybe it wants to play bingo?
	 * Bingo is played on a set of boards each consisting of a 5x5 grid of numbers.
	 * Numbers are chosen at random, and the chosen number is marked on all boards on which it appears.
	 * (Numbers may not appear on all boards.) If all numbers in any row or any column of a board are marked, that board wins.
	 * (Diagonals don't count.)
	 * The score of the winning board is the sum of all unmarked numbers on that board, multiplied by the number that was just called when the board won.
	 * @param getFirstWinner true if looking for first board to win; false if looking for last board to win
	 */
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
	 * Run all Day 5 reports.
	 */
	public void day5(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "5");
		
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
	 * Run all Day 6 reports.
	 */
	public void day6(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "6");
		
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
	 * Run all Day 7 reports.
	 */
	public void day7(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "7");
		
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
	 * Run all Day 8 reports.
	 */
	public void day8(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "8");
		
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
	 * Run all Day 9 reports.
	 */
	public void day9(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "9");
		
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
	 * Run all Day 10 reports.
	 */
	public void day10(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "10");
		
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
	 * Run all Day 11 reports.
	 */
	public void day11(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "11");
		
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
