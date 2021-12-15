package aoc.twentyseventeen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import aoc.Year;
import aoc.utilities.ReadInputFile;
import aoc.utilities.circuits.Circuit;
import aoc.utilities.circuits.Gate;
import aoc.utilities.circuits.Wire;
import aoc.utilities.circuits.gates.MultiOutputGate;

public class TwentySeventeen extends Year {
	
	public TwentySeventeen() {
		CUR_YEAR = 2017;
	}
    
	/**
	 * Run all Day 1 reports.
	 */
	public void day1(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "1");
		
		switch(part) {
			case "1":
				day1InverseCaptchaPart1();
				break;
			case "2":
				day1InverseCaptchaPart2();
				break;
			default:
				day1InverseCaptchaPart1();
				day1InverseCaptchaPart2();
				break;
		}		
	}
	
	/**
	 * You're standing in a room with "digitization quarantine" written in LEDs along one wall.
	 * The only door is locked, but it includes a small interface. "Restricted Area - Strictly No Digitized Users Allowed."
	 * It goes on to explain that you may only leave by solving a captcha to prove you're not a human.
	 * The captcha requires you to review a sequence of digits (your puzzle input) and find the sum of all digits that match the next digit in the list.
	 * The list is circular, so the digit after the last digit is the first digit in the list.
	 * What is the solution to your captcha?
	 */
	public void day1InverseCaptchaPart1() {
		String line = input.get(0);
		int sum = 0;
		
		for(int i = 0; i < line.length(); i++) {
			if(i == line.length()-1) {
				if(Integer.parseInt(line.substring(i)) == Integer.parseInt(line.substring(0,1))) {
					sum = sum + Integer.parseInt(line.substring(i));
				}
			}
			else {
				if(Integer.parseInt(line.substring(i,i+1)) == Integer.parseInt(line.substring(i+1,i+2))) {
					sum = sum + Integer.parseInt(line.substring(i,i+1));
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 1 Part 1: " + sum);
	}
	
	/**
	 * Apparently, the door isn't yet satisfied.
	 * Now, instead of considering the next digit, it wants you to consider the digit halfway around the circular list.
	 * That is, if your list contains 10 items, only include a digit in your sum if the digit 10/2 = 5 steps forward matches it.
	 * Fortunately, your list has an even number of elements.
	 * What is the solution to your new captcha?
	 */
	public void day1InverseCaptchaPart2() {
		String line = input.get(0);
		int sum = 0;
		int lineHalf = line.length() / 2;
		
		for(int i = 0; i < line.length(); i++) {
			int tmpI = (i+lineHalf) % line.length();
			if(Integer.parseInt(line.substring(i,i+1)) == Integer.parseInt(line.substring(tmpI,tmpI+1))) {
				sum = sum + Integer.parseInt(line.substring(i,i+1));
			}
		}
		
		System.out.println(CUR_YEAR + " Day 1 Part 2: " + sum);
	}
	
	/**
	 * Run all Day 2 reports.
	 */
	public void day2(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "2");
		
		switch(part) {
			case "1":
				day2CorruptionChecksumPart1();
				break;
			case "2":
				day2CorruptionChecksumPart2();
				break;
			default:
				day2CorruptionChecksumPart1();
				day2CorruptionChecksumPart2();
				break;
		}
	}
	
	/**
	 * The spreadsheet consists of rows of apparently-random numbers.
	 * To make sure the recovery process is on the right track, they need you to calculate the spreadsheet's checksum.
	 * For each row, determine the difference between the largest value and the smallest value; the checksum is the sum of all of these differences.
	 * What is the checksum for the spreadsheet in your puzzle input?
	 */
	public void day2CorruptionChecksumPart1() {
		int sum = 0;
		for(String line : input) {
			String[] parts = line.split("\t");
			
			int smallest = 0;
			int largest = 0;
			for(int i = 0; i < parts.length; i++) {
				int num = Integer.parseInt(parts[i]);
				
				if(smallest == 0 || num < smallest) {
					smallest = num;
				}
				if(largest == 0 || num > largest) {
					largest = num;
				}
			}
			
			sum += (largest - smallest);
		}
		
		System.out.println(CUR_YEAR + " Day 2 Part 1: " + sum);
	}
	
	/**
	 * It looks like all the User wanted is some information about the evenly divisible values in the spreadsheet.
	 * Find the only two numbers in each row where one evenly divides the other - 
	 *    that is, where the result of the division operation is a whole number.
	 * Find those numbers on each line, divide them, and add up each line's result.
	 * What is the sum of each row's result in your puzzle input?
	 */
	public void day2CorruptionChecksumPart2() {
		int sum = 0;
		for(String line : input) {
			String[] parts = line.split("\t");

			evensearch:
			for(int i = 0; i < parts.length; i++) {
				for(int j = 0; j < parts.length; j++) {
					if(i != j) {
						Double num1 = Double.parseDouble(parts[i]);
						Double num2 = Double.parseDouble(parts[j]);
						
						Double quotient = (num1 / num2);
						
						if(quotient % 1 == 0) {						
							sum += quotient;
							break evensearch;
						}
					}
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 2 Part 2: " + sum);
	}
	
	/**
	 * Run all Day 3 reports.
	 */
	public void day3(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "3");
		
		switch(part) {
			case "1":
				day3SpiralMemoryPart1();
				break;
			case "2":
				day3SpiralMemoryPart2();
				break;
			default:
				day3SpiralMemoryPart1();
				day3SpiralMemoryPart2();
				break;
		}
	}
	
	/**
	 * You come across an experimental new kind of memory stored on an infinite two-dimensional grid.
	 * Each square on the grid is allocated in a spiral pattern starting at a location marked 1 and then counting up while spiraling outward.
	 * While this is very space-efficient (no squares are skipped), requested data must be carried back to square 1 
	 *    (the location of the only access port for this memory system) by programs that can only move up, down, left, or right.
	 * They always take the shortest path: the Manhattan Distance between the location of the data and square 1.
	 * How many steps are required to carry the data from the square identified in your puzzle input all the way to the access port?
	 */
	public void day3SpiralMemoryPart1() {
		int[][] grid;
		
		int curRow;
		int curCol;
		
		int startRow;
		int startCol;
		int endRow;
		int endCol;
		
		grid = new int[1000][1000];
		
		curRow = 1000/2;
		curCol = 1000/2;
		
		startRow = curRow;
		startCol = curCol;
		
		grid[curRow][curCol] = 1;
		int myInput = Integer.parseInt(input.get(0));
		
		int count = 2;
		curCol++;
		while(count < myInput) {
			grid[curRow][curCol] = count;
			if(grid[curRow][curCol-1] > 0 && grid[curRow-1][curCol] <= 0) {
				curRow--;
			}
			else if(grid[curRow+1][curCol] > 0 && grid[curRow][curCol-1] <= 0) {
				curCol--;
			}
			else if(grid[curRow][curCol+1] > 0 && grid[curRow+1][curCol] <= 0) {
				curRow++;
			}
			else if(grid[curRow-1][curCol] > 0 && grid[curRow][curCol+1] <= 0) {
				curCol++;
			}
			count++;
		}
		
		endRow = curRow;
		endCol = curCol;
		
		int blocks = Math.abs(endRow-startRow) + Math.abs(endCol-startCol);
		System.out.println(CUR_YEAR + " Day 3 Part 1: " + blocks);
	}
	
	/**
	 * As a stress test on the system, the programs here clear the grid and then store the value 1 in square 1.
	 * Then, in the same allocation order as shown above, they store the sum of the values in all adjacent squares, including diagonals.
	 * Once a square is written, its value does not change.
	 * What is the first value written that is larger than your puzzle input?
	 */
	public void day3SpiralMemoryPart2() {
		int[][] grid;
		
		int curRow;
		int curCol;
		
		grid = new int[1000][1000];
		
		curRow = 1000/2;
		curCol = 1000/2;
		
		grid[curRow][curCol] = 1;
		int myInput = Integer.parseInt(input.get(0));
		
		int curSum = 0;
		curCol++;
		while(curSum < myInput) {
			curSum = grid[curRow-1][curCol-1] +
					 grid[curRow-1][curCol] +
					 grid[curRow-1][curCol+1] +					 
					 grid[curRow][curCol+1] +
					 grid[curRow][curCol-1] +
					 grid[curRow+1][curCol-1] +
					 grid[curRow+1][curCol] +
					 grid[curRow+1][curCol+1];
			
			grid[curRow][curCol] = curSum;
			if(grid[curRow][curCol-1] > 0 && grid[curRow-1][curCol] <= 0) {
				curRow--;
			}
			else if(grid[curRow+1][curCol] > 0 && grid[curRow][curCol-1] <= 0) {
				curCol--;
			}
			else if(grid[curRow][curCol+1] > 0 && grid[curRow+1][curCol] <= 0) {
				curRow++;
			}
			else if(grid[curRow-1][curCol] > 0 && grid[curRow][curCol+1] <= 0) {
				curCol++;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 3 Part 2: " + curSum);
	}
	
	/**
	 * Run all Day 4 reports.
	 */
	public void day4(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "4");
		
		switch(part) {
			case "1":
				day4HighEntropyPassphrasesPart1();
				break;
			case "2":
				day4HighEntropyPassphrasesPart2();
				break;
			default:
				day4HighEntropyPassphrasesPart1();
				day4HighEntropyPassphrasesPart2();
				break;
		}
	}
	
	/**
	 * A new system policy has been put in place that requires all accounts to use a passphrase instead of simply a password.
	 * A passphrase consists of a series of words (lowercase letters) separated by spaces.
	 * To ensure security, a valid passphrase must contain no duplicate words.
	 * The system's full passphrase list is available as your puzzle input. How many passphrases are valid?
	 */
	public void day4HighEntropyPassphrasesPart1() {
		int numCorrect = 0;
		for(String line : input) {
			String[] parts = line.split(" ");
			
			boolean invalid = false;
			pwcheck:
			for(int i = 0; i < parts.length; i++) {
				for(int j = i+1; j < parts.length; j++) {
					if(parts[i].equals(parts[j])) {
						invalid = true;
						break pwcheck;
					}
				}
			}
			
			if(!invalid) {
				numCorrect ++;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 4 Part 1: " + numCorrect);
	}
	
	/**
	 * For added security, yet another system policy has been put in place.
	 * Now, a valid passphrase must contain no two words that are anagrams of each other - 
	 *    that is, a passphrase is invalid if any word's letters can be rearranged to form any other word in the passphrase.
	 * Under this new system policy, how many passphrases are valid?
	 */
	public void day4HighEntropyPassphrasesPart2() {
		int numCorrect = 0;
		for(String line : input) {
			String[] parts = line.split(" ");
			
			boolean invalid = false;
			pwcheck:
			for(int i = 0; i < parts.length; i++) {
				List<String> piecesI = Arrays.asList(parts[i].split(""));
				for(int j = i+1; j < parts.length; j++) {
					List<String> piecesJ = Arrays.asList(parts[j].split(""));
					if(piecesI.containsAll(piecesJ) && piecesJ.containsAll(piecesI)) {
						invalid = true;
						break pwcheck;
					}
				}
			}
			
			if(!invalid) {
				numCorrect ++;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 4 Part 2: " + numCorrect);
	}
	
	/**
	 * Run all Day 5 reports.
	 */
	public void day5(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "5");
		
		switch(part) {
			case "1":
				day5MazeTwistyTrampolinesPart1();
				break;
			case "2":
				day5MazeTwistyTrampolinesPart2();
				break;
			default:
				day5MazeTwistyTrampolinesPart1();
				day5MazeTwistyTrampolinesPart2();
				break;
		}
	}
	
	/**
	 * An urgent interrupt arrives from the CPU: it's trapped in a maze of jump instructions, and it would like assistance from any programs with spare cycles to help find the exit.
	 * The message includes a list of the offsets for each jump.
	 * Jumps are relative:
	 *    -1 moves to the previous instruction
	 *    2 skips the next one.
	 * Start at the first instruction in the list. The goal is to follow the jumps until one leads outside the list.
	 * In addition, these instructions are a little strange; after each jump, the offset of that instruction increases by 1.
	 * So, if you come across an offset of 3, you would move three instructions forward, but change it to a 4 for the next time it is encountered.
	 * Positive jumps ("forward") move downward; negative jumps move upward.
	 * How many steps does it take to reach the exit?
	 */
	public void day5MazeTwistyTrampolinesPart1() {
		List<Integer> instructions = new ArrayList<Integer>(input.size());
		
		for(String line : input) {
			instructions.add(Integer.parseInt(line));
		}
		
		Integer curInstructionIndex = 0;
		Integer nextInstructionMove;
		Integer stepCount = 0;
		
		while(true) {
			try {
				nextInstructionMove = instructions.get(curInstructionIndex);
				instructions.set(curInstructionIndex, nextInstructionMove+1);				
				curInstructionIndex += nextInstructionMove;
			}
			catch(IndexOutOfBoundsException ioobe) {
				break;
			}
			stepCount++;
		}
		
		System.out.println(CUR_YEAR + " Day 5 Part 1: " + stepCount);
	}
	
	/**
	 * Now, the jumps are even stranger: after each jump, if the offset was three or more, instead decrease it by 1. Otherwise, increase it by 1 as before.
	 * How many steps does it now take to reach the exit?
	 */
	public void day5MazeTwistyTrampolinesPart2() {
		List<Integer> instructions = new ArrayList<Integer>(input.size());
		
		for(String line : input) {
			instructions.add(Integer.parseInt(line));
		}
		
		Integer curInstructionIndex = 0;
		Integer nextInstructionMove;
		Integer stepCount = 0;
		
		while(true) {
			try {
				nextInstructionMove = instructions.get(curInstructionIndex);
				if(nextInstructionMove >= 3) {
					instructions.set(curInstructionIndex, nextInstructionMove-1);
				}
				else {
					instructions.set(curInstructionIndex, nextInstructionMove+1);
				}
				curInstructionIndex += nextInstructionMove;
			}
			catch(IndexOutOfBoundsException ioobe) {
				break;
			}
			stepCount++;
		}
		
		System.out.println(CUR_YEAR + " Day 5 Part 2: " + stepCount);
	}
	
	/**
	 * Run all Day 6 reports.
	 */
	public void day6(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "6");
		
		switch(part) {
			case "1":
				day6MemoryReallocationPart1();
				break;
			case "2":
				day6MemoryReallocationPart2();
				break;
			default:
				day6MemoryReallocationPart1();
				day6MemoryReallocationPart2();
				break;
		}
	}
	
	/**
	 * Helper method for day 6 Memory Reallocation problems
	 * @param findLoopSize true if we care about the difference between the duplicate allocation and the original time it was seen
	 * @return int with the number of cycles to find the duplicate, or if findLoopSize is true, the the difference between final count and the first time the allocation was seen
	 */
	public int day6MemoryReallocationHelper(boolean findLoopSize) {
		HashMap<Integer, Integer> memoryHistory = new HashMap<Integer, Integer>();
		List<String> memoryStr = Arrays.asList(input.get(0).split("\t"));
		int count = 0;
		
		List<Integer> lastMemory = new ArrayList<Integer>(memoryStr.size());
		for(int i = 0; i < memoryStr.size(); i++) {
			lastMemory.add(Integer.parseInt(memoryStr.get(i)));
		}
		
		memoryHistory.put(count, lastMemory.hashCode());
		
		while(true) {
			count++;
			int blocks = 0;
			int blocksIndex = 0;
			
			// get the highest memory for this cycle
			for(int i = 0; i < lastMemory.size(); i++) {
				if(blocks == 0 || lastMemory.get(i) > blocks) {
					blocks = lastMemory.get(i);
					blocksIndex = i;
				}
			}
			
			List<Integer> newMemory = lastMemory;
			newMemory.set(blocksIndex, 0);
			
			// redistribute the blocks
			for(int i = (blocksIndex+1 == newMemory.size() ? 0 : blocksIndex+1); i < newMemory.size() && blocks > 0; i++) {
				newMemory.set(i, newMemory.get(i) + 1);
				
				if(i == newMemory.size()-1) {
					i = -1;
				}
				blocks--;
			}
			
			if(memoryHistory.containsValue(newMemory.hashCode())) {
				if(findLoopSize) {
					for(int i = 0; i < memoryHistory.size(); i++) {
						if(memoryHistory.get(i) == newMemory.hashCode()) {
							count -= i;
						}
					}
				}
				break;
			}
			
			memoryHistory.put(count, newMemory.hashCode());
		}
		
		return count;
	}
	
	/**
	 * A debugger program here is having an issue: it is trying to repair a memory reallocation routine, but it keeps getting stuck in an infinite loop.
	 * In this area, there are sixteen memory banks; each memory bank can hold any number of blocks.
	 * The goal of the reallocation routine is to balance the blocks between the memory banks.
	 * The reallocation routine operates in cycles.
	 * In each cycle, it finds the memory bank with the most blocks (ties won by the lowest-numbered memory bank) and redistributes those blocks among the banks.
	 * To do this, it removes all of the blocks from the selected bank, then moves to the next (by index) memory bank and inserts one of the blocks.
	 * It continues doing this until it runs out of blocks; if it reaches the last memory bank, it wraps around to the first one.
	 * The debugger would like to know how many redistributions can be done before a blocks-in-banks configuration is produced that has been seen before.
	 * Given the initial block counts in your puzzle input, how many redistribution cycles must be completed before a configuration is produced that has been seen before?
	 */
	public void day6MemoryReallocationPart1() {
		int count = day6MemoryReallocationHelper(false);
		
		System.out.println(CUR_YEAR + " Day 6 Part 1: " + count);
	}
	
	/**
	 * Out of curiosity, the debugger would also like to know the size of the loop:
	 *    starting from a state that has already been seen, how many block redistribution cycles must be performed before that same state is seen again?
	 * How many cycles are in the infinite loop that arises from the configuration in your puzzle input?
	 */
	public void day6MemoryReallocationPart2() {
		int count = day6MemoryReallocationHelper(true);
		
		System.out.println(CUR_YEAR + " Day 6 Part 2: " + count);
	}
	
	/**
	 * Run all Day 7 reports.
	 */
	public void day7(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "7");
		
		switch(part) {
			case "1":
				day7RecursiveCircusPart1();
				break;
			case "2":
				break;
			default:
				day7RecursiveCircusPart1();
				break;
		}
	}
	
	public Circuit day7BuildCircuit() {
		Circuit circuit = new Circuit();
		
		for(String line : input) {
			String[] lineParts = line.split(" ");
			
			Wire w;
			if((w = circuit.getWire(lineParts[0])) == null) {
				w = new Wire(lineParts[0]);				
			}
			
			// Strip the parens
			String weight = lineParts[1].substring(1, lineParts[1].length()-1);
			w.setValue(weight);
			circuit.addOrUpdateWire(w);
			
			if(lineParts.length > 2) {
				MultiOutputGate mog = new MultiOutputGate();
				mog.setInputWireNameOrValue(w.getName());
				
				String[] outputs = line.substring(line.indexOf("-> ")+3).split(",");
				
				for(String out : outputs) {
					Wire o;
					if((o = circuit.getWire(out.trim())) == null) {
						o = new Wire(out.trim());
						circuit.addOrUpdateWire(o);
					}
					mog.addOutputWireNameOrValue(o.getName());
				}
				
				circuit.addGate(mog);
			}
		}
		
		return circuit;
	}
	
	/**
	 * You come upon a tower of programs that have gotten themselves into a bit of trouble.
	 * A recursive algorithm has gotten out of hand, and now they're balanced precariously in a large tower.
	 * One program at the bottom supports the entire tower. It's holding a large disc, and on the disc are balanced several more sub-towers.
	 * At the bottom of these sub-towers, standing on the bottom disc, are other programs, each holding their own disc, and so on.
	 * At the very tops of these sub-sub-sub-...-towers, many programs stand simply keeping the disc below them balanced but with no disc of their own.
	 * You offer to help, but first you need to understand the structure of these towers.
	 * You ask each program to yell out their name, their weight, and (if they're holding a disc) the names of the programs immediately above them balancing on that disc.
	 * You write this information down (your puzzle input).
	 * Unfortunately, in their panic, they don't do this in an orderly fashion; by the time you're done, you're not sure which program gave which information.
	 * Before you're ready to help them, you need to make sure your information is correct. What is the name of the bottom program?
	 */
	public void day7RecursiveCircusPart1() {
		Circuit circuit = day7BuildCircuit();
		
		String firstBlock = "";
		for(Wire w : circuit.getWires()) {
			if(circuit.findGateWithOutput(w.getName()) == null) {
				firstBlock = w.getName();
			}
		}
		
		System.out.println(CUR_YEAR + " Day 7 Part 1: " + firstBlock);
	}
	
	/**
	 * The programs explain the situation: they can't get down. Rather, they could get down, if they weren't expending all of their energy trying to keep the tower balanced.
	 * Apparently, one program has the wrong weight, and until it's fixed, they're stuck here.
	 * For any program holding a disc, each program standing on that disc forms a sub-tower.
	 * Each of those sub-towers are supposed to be the same weight, or the disc itself isn't balanced. The weight of a tower is the sum of the weights of the programs in that tower.
	 * For a tower to be balanced, each of the programs standing on its disc and all programs above it must each match. This means that the sums must all be the same.
	 * Given that exactly one program is the wrong weight, what would its weight need to be to balance the entire tower?
	 */
	public void day7RecursiveCircusPart2() {
		Circuit circuit = day7BuildCircuit();
		
		List<MultiOutputGate> curGates = new ArrayList<MultiOutputGate>();
		for(Wire w : circuit.getWires()) {
			// start at the bottom, then we'll do it differently next time
			Gate mog;
			if((mog = circuit.findGateWithInput(w.getName())) == null) {
				curGates.add((MultiOutputGate) mog);
			}
		}
		
		// check the sum of what we have, then using the the gate, get the inputs and find the gates of where they are outputs, and get sum...
		// continue until we've found the bad one
		int newWeight = 0;
		if((newWeight = circuit.day7RecursiveCircus(curGates)) > 0) {
			System.out.println(CUR_YEAR + " Day 7 Part 2: " + newWeight);
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
