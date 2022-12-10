package aoc.twentyseventeen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import aoc.Year;
import aoc.circuits.Circuit;
import aoc.circuits.Gate;
import aoc.circuits.Wire;
import aoc.circuits.gates.MultiOutputGate;
import aoc.utilities.ReadInputFile;

public class TwentySeventeen extends Year {
	
	public TwentySeventeen() {
		CUR_YEAR = 2017;
	}
    
	/**
	 * 
	 * {@link https://adventofcode.com/2017/day/1}
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
	 * {@link https://adventofcode.com/2017/day/2}
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
	 * {@link https://adventofcode.com/2017/day/3}
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
	 * {@link https://adventofcode.com/2017/day/4}
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
	 * {@link https://adventofcode.com/2017/day/5}
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
	 * {@link https://adventofcode.com/2017/day/6}
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
	 * {@link https://adventofcode.com/2017/day/7}
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
