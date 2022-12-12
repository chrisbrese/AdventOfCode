package aoc.twentytwentytwo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import aoc.Year;
import aoc.twentytwentytwo.dayeleven.Jungle;
import aoc.twentytwentytwo.dayeleven.Monkey;
import aoc.twentytwentytwo.dayseven.Directory;
import aoc.twentytwentytwo.dayseven.File;
import aoc.twentytwentytwo.dayseven.FileSystem;
import aoc.utilities.ReadInputFile;
import aoc.utilities.grid.Grid;
import aoc.utilities.grid.GridPosition;

public class TwentyTwentyTwo extends Year {
	
	public TwentyTwentyTwo() {
		CUR_YEAR = 2022;
	}
    
	/**
	 * {@link https://adventofcode.com/2022/day/1}
	 * Run all Day 1 reports.
	 */
	public void day1(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "1");
		
		switch(part) {
			case "1":
				day1CalorieCounting();
				break;
			case "2":
				break;
			default:
				day1CalorieCounting();
				break;
		}		
	}
	
	private void day1CalorieCounting() {
		int max = 0;
		int combinedMax;
		int count = 0;
		int lineCount = 0;
		List<Integer> elfCalorieCount = new ArrayList<Integer>();
		
		while(lineCount < input.size()) {
			String line = input.get(lineCount);
			
			if(!line.isEmpty()) {
				count += Integer.parseInt(line);
			}
			else if(lineCount == input.size() - 1) {
				if(count > max) {
					max = count;
				}
				
				elfCalorieCount.add(count);
			}
			else {
				if(count > max) {
					max = count;
				}
				elfCalorieCount.add(count);
				count = 0;
			}
			
			lineCount++;
		}
		
		Collections.sort(elfCalorieCount, Collections.reverseOrder());
		combinedMax = elfCalorieCount.get(0) + elfCalorieCount.get(1) + elfCalorieCount.get(2);
		
		System.out.println(CUR_YEAR + " Day 1 Part 1: " + max);
		System.out.println(CUR_YEAR + " Day 1 Part 2: " + combinedMax);
	}
	
	/**
	 * {@link https://adventofcode.com/2022/day/2}
	 * Run all Day 2 reports.
	 */
	public void day2(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "2");
		
		switch(part) {
			case "1":
				day2RockPaperScissorsDirect();
				break;
			case "2":
				day2RockPaperScissorsEndResult();
				break;
			default:
				day2RockPaperScissorsDirect();
				day2RockPaperScissorsEndResult();
				break;
		}
	}
	
	private void day2RockPaperScissorsDirect() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("X", 1);
		map.put("Y", 2);
		map.put("Z", 3);
		int totalScore = 0;
		
		for(String s : input) {
			String[] battle = s.split(" ");
			String opp = battle[0];
			String me = battle[1];
			
			if(opp.equals("A") && me.equals("X") || 
			   opp.equals("B") && me.equals("Y") ||
			   opp.equals("C") && me.equals("Z")) {
				totalScore += (3 + map.get(me));
			}
			else if (opp.equals("A") && me.equals("Z") ||
					 opp.equals("B") && me.equals("X") ||
					 opp.equals("C") && me.equals("Y")) {
				totalScore += map.get(me);
			}
			else {
				totalScore += (6 + map.get(me));
			}
		}
		
		System.out.println(CUR_YEAR + " Day 2 Part 1: " + totalScore);
	}
	
	private void day2RockPaperScissorsEndResult() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		int totalScore = 0;
		
		for(String s : input) {
			String[] battle = s.split(" ");
			String opp = battle[0];
			String me = battle[1];
			
			if(me.equals("Y")) {
				totalScore += (3 + map.get(opp));
			}
			else if (me.equals("X")) {
				if(opp.equals("A")) {
					totalScore += map.get("C");
				}
				else if(opp.equals("B")) {
					totalScore += map.get("A");
				}
				else if(opp.equals("C")) {
					totalScore += map.get("B");
				}
			}
			else {
				if(opp.equals("A")) {
					totalScore += (6 + map.get("B"));
				}
				else if(opp.equals("B")) {
					totalScore += (6 + map.get("C"));
				}
				else if(opp.equals("C")) {
					totalScore += (6 + map.get("A"));
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 2 Part 2: " + totalScore);
	}
	
	/**
	 * {@link https://adventofcode.com/2022/day/3}
	 * Run all Day 3 reports.
	 */
	public void day3(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "3");
		
		switch(part) {
			case "1":
				day3RucksackReorganization();
				break;
			case "2":
				day3RucksackReorganizationBadges();
				break;
			default:
				day3RucksackReorganization();
				day3RucksackReorganizationBadges();
				break;
		}
	}
	
	private void day3RucksackReorganization() {
		int priorityTotal = 0;
		for(String s : input) {
			String first = s.substring(0, s.length()/2);
			String sec = s.substring(s.length()/2);
			String item = "";
			
			for(int i = 0; i < first.length(); i++) {
				if(sec.contains(first.substring(i, i+1))) {
					item = first.substring(i, i+1);
					priorityTotal += alphabetStr.indexOf(item) + 1;
					break;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 3 Part 1: " + priorityTotal);
	}
	
	private void day3RucksackReorganizationBadges() {
		int priorityTotal = 0;
		for(int i = 0; i < input.size(); i+=3) {
			String first = input.get(i);
			String sec = input.get(i+1);
			String third = input.get(i+2);
			String item = "";
			
			for(int j = 0; j < first.length(); j++) {
				if(sec.contains(first.substring(j, j+1)) && third.contains(first.substring(j, j+1))) {
					item = first.substring(j, j+1);
					priorityTotal += alphabetStr.indexOf(item) + 1;
					break;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 3 Part 2: " + priorityTotal);
	}
	
	/**
	 * {@link https://adventofcode.com/2022/day/4}
	 * Run all Day 4 reports.
	 */
	public void day4(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "4");
		
		switch(part) {
			case "1":
				day4CampCleanupWhole();
				break;
			case "2":
				day4CampCleanupAny();
				break;
			default:
				day4CampCleanupWhole();
				day4CampCleanupAny();
				break;
		}
	}
	
	private void day4CampCleanupWhole() {
		int count = 0;
		for(String s : input) {
			String[] parts = s.split(",");
			String[] part1 = parts[0].split("-");
			String[] part2 = parts[1].split("-");
			
			int part1Low = Integer.parseInt(part1[0]);
			int part1High = Integer.parseInt(part1[1]);
			int part2Low = Integer.parseInt(part2[0]);
			int part2High = Integer.parseInt(part2[1]);
			
			// part2 is included in part1
			if(part1Low <= part2Low && 
			   part1High >= part2High) {
				count++;
			}
			// part1 is included in part2
			else if(part2Low <= part1Low && 
			   part2High >= part1High) {
				count++;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 4 Part 1: " + count);
	}
	
	private void day4CampCleanupAny() {
		int count = 0;
		for(String s : input) {
			String[] parts = s.split(",");
			String[] part1 = parts[0].split("-");
			String[] part2 = parts[1].split("-");
			
			int part1Low = Integer.parseInt(part1[0]);
			int part1High = Integer.parseInt(part1[1]);
			int part2Low = Integer.parseInt(part2[0]);
			int part2High = Integer.parseInt(part2[1]);
			
			// part2 overlaps
			if(part2Low >= part1Low && 
			   part2Low <= part1High) {
				count++;
			}
			else if(part2High >= part1Low && 
			   part2High <= part1High) {
				count++;
			}
			// part1 is included in part2
			else if(part1Low >= part2Low && 
			   part1Low <= part2High) {
				count++;
			}
			else if(part1High >= part2Low && 
			   part1High <= part2High) {
				count++;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 4 Part 2: " + count);
	}
	
	/**
	 * {@link https://adventofcode.com/2022/day/5}
	 * Run all Day 5 reports.
	 */
	public void day5(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "5");
		
		switch(part) {
			case "1":
				day5SupplyStacks(false);
				break;
			case "2":
				day5SupplyStacks(true);
				break;
			default:
				day5SupplyStacks(false);
				day5SupplyStacks(true);
				break;
		}
	}
	
	/**
	 * @param part2 true if running part 2
	 */
	private void day5SupplyStacks(boolean part2) {
		HashMap<Integer, Stack<String>> backwardsStackMap = new HashMap<Integer, Stack<String>>();
		HashMap<Integer, Stack<String>> stackMap = new HashMap<Integer, Stack<String>>();

		boolean end = false;
		for(String line : input) {
			int curStack = 1;

			if(!end) {
				try {
					// if its a number, we have the backwards stacks - end loop
					Integer.parseInt(line.substring(1, 2));
					end = true;

					// reverse the stacks
					for (int i = 1; i <= backwardsStackMap.size(); i++){
						Stack<String> stack = new Stack<String>();
						while(!backwardsStackMap.get(i).isEmpty()){
							stack.push(backwardsStackMap.get(i).pop());
						}
						stackMap.put(i, stack);
					}
				}
				catch(NumberFormatException nfe){
					for(int i = 0; i < line.length(); i+=4){
						if(line.substring(i, i+1).equals("[")) {
							if(backwardsStackMap.get(curStack) == null) {
								backwardsStackMap.put(curStack, new Stack<String>());
							}
							
							// add to stack
							backwardsStackMap.get(curStack).push(line.substring(i+1, i+2));
						}
						curStack++;
					}
				}
			}
			else {
				// start reading the instructions
				if(!line.isEmpty()){
					String[] parts = line.split(" ");
					int num = Integer.parseInt(parts[1]);
					int from = Integer.parseInt(parts[3]);
					int to = Integer.parseInt(parts[5]);
					
					Stack<String> temp = new Stack<String>();
					
					for(int i = 0; i < num; i++){
						if(part2) {
							temp.push(stackMap.get(from).pop());
						}
						else {
							stackMap.get(to).push(stackMap.get(from).pop());
						}
					}
					
					if(part2) {
						for(int i = 0; i < num; i++){
							stackMap.get(to).push(temp.pop());
						}
					}
				}
			}
		}

		String result = "";
		for(int i = 1; i <= stackMap.size(); i++){
			result += stackMap.get(i).peek();
		}

		System.out.println(CUR_YEAR + " Day 5 Part " + (part2 ? "2" : "1") + ": " + result);
	}
	
	/**
	 * {@link https://adventofcode.com/2022/day/6}
	 * Run all Day 6 reports.
	 */
	public void day6(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "6");
		
		switch(part) {
			case "1":
				day6TuningTrouble(4);
				break;
			case "2":
				day6TuningTrouble(14);
				break;
			default:
				day6TuningTrouble(4);
				day6TuningTrouble(14);
				break;
		}
	}
	
	private void day6TuningTrouble(int prefix) {
		for(String line : input) {
			for(int i = 0; i < line.length(); i++) {
				String temp = line.substring(i, i+prefix);
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				for (int j = 0; j < temp.length(); j++) {
					String c = temp.substring(j, j+1);
	
					if(map.get(c) == null) {
						map.put(c, 0);
					}
	
					map.put(c, map.get(c)+1);
				}
	
				if(map.size() == prefix){
					System.out.println(CUR_YEAR + " Day 6 Prefix " + prefix + ": " + (i+prefix));
					break;
				}
			}
		}
	}
	
	/**
	 * {@link https://adventofcode.com/2022/day/7}
	 * Run all Day 7 reports.
	 */
	public void day7(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "7");
		
		switch(part) {
			case "1":
				day7NoSpaceLeftOnDevice();
				break;
			case "2":
				day7NoSpaceLeftOnDevice();
				break;
			default:
				day7NoSpaceLeftOnDevice();
				break;
		}
	}
	
	private void day7NoSpaceLeftOnDevice() {
		FileSystem fs = new FileSystem();
		fs.setBase(new Directory("/", null));
		
		Directory curDir = fs.getBase();
		for(String line : input) {
			String[] parts = line.split(" ");
			
			if(line.startsWith("$")) {
				if(parts[1].equals("cd")) {
					if(parts[2].equals("/")) {
						//skip
					}
					
					else if(parts[2].equals("..")) {
						curDir = fs.setCurDir(curDir.getParentDir());
					}
					else {
						Directory d;
						if((d = curDir.findDirectory(parts[2])) == null) {
							d = curDir.addDirectory(new Directory(parts[2], curDir));
							fs.addKnownDir(d);
						}
						
						curDir = fs.setCurDir(d);
					}
				}
			}
			else if(line.startsWith("dir")) {
				if(curDir.findDirectory(parts[1]) == null) {
					Directory d = curDir.addDirectory(new Directory(parts[1], curDir));
					fs.addKnownDir(d);
				}
			}
			else {
				curDir.addFile(new File(parts[1], Integer.parseInt(parts[0])));
			}
		}
		
		int totalSize = 0;
		for(Directory d : fs.getKnownDirs()) {
			int size = d.getSize();
			
			if(size <= 100000) {
				totalSize += size;
			}
		}
		
		int fsSize = fs.getBase().getSize();
		// total avail space - current space - space required. absolute value because result is negative
		int spaceToDelete = Math.abs(70000000 - fsSize - 30000000);
		int dirSizeToDelete = 0;
		for(Directory d : fs.getKnownDirs()) {
			int curSize = 0;
			if((curSize = d.getSize()) >= spaceToDelete) {
				if(dirSizeToDelete == 0 || curSize < dirSizeToDelete) {
					dirSizeToDelete = curSize;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 7 Part 1: " + totalSize);
		System.out.println(CUR_YEAR + " Day 7 Part 2: " + dirSizeToDelete);
	}
	
	/**
	 * {@link https://adventofcode.com/2022/day/8}
	 * Run all Day 8 reports.
	 */
	public void day8(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "8");
		
		switch(part) {
			case "1":
				day8TreeTopTreeHouse();
				break;
			case "2":
				day8TreeTopTreeHouseScenicScore();
				break;
			default:
				day8TreeTopTreeHouse();
				day8TreeTopTreeHouseScenicScore();
				break;
		}
	}
	
	private void day8TreeTopTreeHouse() {
		Grid g = new Grid(input.size(), input.get(0).length());
		
		// setup the grid
		int rowCount = 0;
		for(String line : input) {
			int colCount = 0;
			while(colCount < line.length()) {
				int cur = Integer.valueOf(line.substring(colCount, colCount+1));
				g.getGrid()[rowCount][colCount] = cur;
				colCount++;
			}
			rowCount++;
		}
		
		int visCount = 0;
		for(int row = 0; row < g.getNumRows(); row++) {
			for(int col = 0; col < g.getNumCols(); col++) {
				if(row == 0 || col == 0) {
					visCount++;
				}
				else {
					int curVal = g.getGrid()[row][col];
					
					boolean isVisible = true;
					for(int tRow = 0; tRow < row; tRow++) {
						if(g.getGrid()[tRow][col] >= curVal) {
							isVisible = false;
							break;
						}
					}
					
					if(!isVisible) {
						isVisible = true;
						for(int tRow = row+1; tRow < g.getNumRows(); tRow++) {
							if(g.getGrid()[tRow][col] >= curVal) {
								isVisible = false;
								break;
							}
						}
						
						if(!isVisible) {
							isVisible = true;
							for(int tCol = 0; tCol < col; tCol++) {
								if(g.getGrid()[row][tCol] >= curVal) {
									isVisible = false;
									break;
								}
							}
							
							if(!isVisible) {
								isVisible = true;
								for(int tCol = col+1; tCol < g.getNumCols(); tCol++) {
									if(g.getGrid()[row][tCol] >= curVal) {
										isVisible = false;
										break;
									}
								}
							}
						}
					}
					
					if(isVisible) {
						visCount++;
					}
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 8 Part 1: " + visCount);
	}
	
	private void day8TreeTopTreeHouseScenicScore() {
		Grid g = new Grid(input.size(), input.get(0).length());
		
		// setup the grid
		int rowCount = 0;
		for(String line : input) {
			int colCount = 0;
			while(colCount < line.length()) {
				int cur = Integer.valueOf(line.substring(colCount, colCount+1));
				g.getGrid()[rowCount][colCount] = cur;
				colCount++;
			}
			rowCount++;
		}
		
		int scenicScore = 1;
		for(int row = 0; row < g.getNumRows(); row++) {
			for(int col = 0; col < g.getNumCols(); col++) {
				int curVal = g.getGrid()[row][col];
				int tempScenicScore = 1;
				for(int tRow = row-1; tRow >= 0; tRow--) {
					if(tRow == 0 || g.getGrid()[tRow][col] >= curVal) {
						tempScenicScore *= (row-tRow);
						break;
					}
				}
				
				for(int tRow = row+1; tRow < g.getNumRows(); tRow++) {
					if(tRow == (g.getNumRows()-1) || g.getGrid()[tRow][col] >= curVal) {
						tempScenicScore *= (tRow-row);
						break;
					}
				}
				
				for(int tCol = col-1; tCol >= 0; tCol--) {
					if(tCol == 0 || g.getGrid()[row][tCol] >= curVal) {
						tempScenicScore *= (col-tCol);
						break;
					}
				}
				
				for(int tCol = col+1; tCol < g.getNumCols(); tCol++) {
					if(tCol == (g.getNumCols()-1) || g.getGrid()[row][tCol] >= curVal) {
						tempScenicScore *= (tCol-col);
						break;
					}
				}
				
				if(tempScenicScore > scenicScore) {
					scenicScore = tempScenicScore;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 8 Part 2: " + scenicScore);
	}
	
	/**
	 * {@link https://adventofcode.com/2022/day/9}
	 * Run all Day 9 reports.
	 */
	public void day9(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "9");
		
		switch(part) {
			case "1":
				day9RopeBridge(1);
				break;
			case "2":
				day9RopeBridge(9);
				break;
			default:
				day9RopeBridge(1);
				day9RopeBridge(9);
				break;
		}
	}
	
	/**
	 * @param numTails The number of tails to track
	 */
	private void day9RopeBridge(int numTails) {
		Grid g = new Grid(1000, "", false, true);
		
		List<GridPosition> tails = new ArrayList<GridPosition>();
		
		int curHRow = g.getCurRow();
		int curHCol = g.getCurCol();
		
		GridPosition gp = new GridPosition(curHRow, curHCol, 1);
		g.addGridPosition(Integer.toString(curHRow) + Integer.toString(curHCol), gp);
		
		for(int i = 0; i < numTails+1; i++) {
			tails.add(gp);
		}
		
		for(String line : input) {
			String[] parts = line.split(" ");
			String dir = parts[0];
			int num = Integer.parseInt(parts[1]);

			for(int i = 0; i < num; i++) {
				// first, move the head
				switch(dir) {
					case "U" :
						curHRow--;
						break;
					case "D" :
						curHRow++;
						break;
					case "R" :
						curHCol++;
						break;
					case "L" :
						curHCol--;
						break;
					default :
						break;
				}
				
				tails.remove(0);
				tails.add(0, new GridPosition(curHRow, curHCol, 1));
				
				for(int t = 1; t < tails.size(); t++) {
					int curTRow = tails.get(t).getRow();
					int curTCol = tails.get(t).getCol();
					
					int lastRow = tails.get(t-1).getRow();
					int lastCol = tails.get(t-1).getCol();
					// then, move the tails
					if(curTRow == lastRow && (Math.abs(lastRow - curTRow) > 1)) {
						if(lastCol > curTCol) {
							curTCol++;
						}
						else if(lastCol < curTCol) {
							curTCol--;
						}
					}
					
					else if(curTCol == lastCol && (Math.abs(lastCol - curTCol) > 1)) {
						if(lastRow > curTRow) {
							curTRow++;
						}
						else if(lastRow < curTRow) {
							curTRow--;
						}
					}
					else {
						// if these == 1, then they would be directly diagonal already
						if(Math.abs(lastRow - curTRow) > 1 || Math.abs(lastCol - curTCol) > 1) {
							if(lastCol > curTCol) {
								curTCol++;
							}
							else if(lastCol < curTCol) {
								curTCol--;
							}
							
							if(lastRow > curTRow) {
								curTRow++;
							}
							else if(lastRow < curTRow) {
								curTRow--;
							}
						}
					}
					
					tails.remove(t);
					tails.add(t, new GridPosition(curTRow, curTCol, 1));
					
					if(t == tails.size() - 1) {
						GridPosition p;
						if((p = g.getGridPosition(curTRow, curTCol)) == null) {
							p = new GridPosition(curTRow, curTCol, 1);
							g.addGridPosition(Integer.toString(curTRow) + Integer.toString(curTCol), p);
						}
						else {
							p.setVal(p.getVal() + 1);
						}
					}
				}
			}
		}
		
		// Check the GPs
		int count = 0;
		for(GridPosition pos : g.getGridPositions().values()) {
			if(pos != null && pos.getVal() > 0) {
				count++;
			}
		}

		System.out.println(CUR_YEAR + " Day 9 Unique Spots of Last Tail ( " + numTails + "): " + count);
	}
	
	/**
	 * {@link https://adventofcode.com/2022/day/10}
	 * Run all Day 10 reports.
	 */
	public void day10(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "10");
		
		switch(part) {
			case "1":
				day10CathodeRayTube();
				break;
			case "2":
				break;
			default:
				day10CathodeRayTube();
				break;
		}
	}
	
	private void day10CathodeRayTube() {
		int cycle = 1;
		int register = 1;
		int spritePos = 1;
		int cycleRegisterSum = 0;
		HashMap<Integer, Integer> cycleToRegisterChange = new HashMap<Integer, Integer>();
		
		while(cycle < 241) {
			for(String line : input) {
				if(cycle == 20 || cycle == 60 || cycle == 100 || 
				   cycle == 140 || cycle == 180 || cycle == 220) {
					cycleRegisterSum += (cycle*register);
				}
				
				// start of a cycle - Draw!
				if(cycle == 41 || cycle == 81 || cycle == 121 || cycle == 161 || cycle == 201) {
					spritePos += 40;
					System.out.println();
				}
				
				if(cycle == spritePos || cycle == (spritePos + 1) || cycle == (spritePos + 2)) {
					System.out.print("#");
				}
				else {
					System.out.print(".");
				}
				
				if(!line.equals("noop")) {
					String[] parts = line.split(" ");
					int num = Integer.parseInt(parts[1]);
					
					cycleToRegisterChange.put(cycle+1, num);
					cycle++;
					
					// start of another cycle - draw!
					if(cycle == 20 || cycle == 60 || cycle == 100 || 
					   cycle == 140 || cycle == 180 || cycle == 220) {
						cycleRegisterSum += (cycle*register);
					}
					
					if(cycle == 41 || cycle == 81 || cycle == 121 || cycle == 161 || cycle == 201) {
						spritePos += 40;
						System.out.println();
					}
					
					if(cycle == spritePos || cycle == (spritePos + 1) || cycle == (spritePos + 2)) {
						System.out.print("#");
					}
					else {
						System.out.print(".");
					}
				}
				
				if(cycleToRegisterChange.containsKey(cycle)) {
					register += cycleToRegisterChange.get(cycle);
					spritePos += cycleToRegisterChange.get(cycle);
				}
				
				cycle++;
			}
		}
		
		System.out.println();
		System.out.println(CUR_YEAR + " Day 10 Part 1: " + cycleRegisterSum);
	}
	
	/**
	 * {@link https://adventofcode.com/2022/day/11}
	 * Run all Day 11 reports.
	 */
	public void day11(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "11");
		
		switch(part) {
			case "1":
				day11MonkeyInTheMiddle(20, false);
				break;
			case "2":
				day11MonkeyInTheMiddle(10000, true);
				break;
			default:
				day11MonkeyInTheMiddle(20, false);
				day11MonkeyInTheMiddle(10000, true);
				break;
		}
	}

	/**
	 * 
	 * @param numCycles The num cycles to process
	 * @param part2 true if running part 2
	 */
	private void day11MonkeyInTheMiddle(int numCycles, boolean part2) {
		Jungle jungle = new Jungle();
		
		Monkey m;
		int curMonkey = 0;
		for(String line : input) {
			line = line.trim(); // get rid of those pesky spaces
			String parts[] = line.split(" ");
			if(line.startsWith("Monkey")) {
				curMonkey = Integer.parseInt(parts[1].substring(0, 1));
				m = jungle.addMonkey(new Monkey(curMonkey));
			}
			else {
				m = jungle.getMonkey(curMonkey);
			}
			
			if(line.startsWith("Starting items:")){
				for(int i = 2; i < parts.length; i++) {
					int item;
					
					if(parts[i].contains(",")) {
						item = Integer.parseInt(parts[i].substring(0, parts[i].indexOf(",")));
					}
					else {
						item = Integer.parseInt(parts[i]);
					}
					
					m.addItem(item);
				}
			}
			else if(line.startsWith("Operation:")){
				m.setOperation(parts[4] + " " + parts[5]);
			}
			else if(line.startsWith("Test:")) {
				m.setDivisibleBy(Integer.parseInt(parts[3]));
			}
			else if(line.startsWith("If")) {
				m.addMonkey(parts[1].substring(0, parts[1].indexOf(":")), Integer.parseInt(parts[5]));
			}
		}
		
		for(int i = 0; i < numCycles; i++) {
			for(int j = 0; j < jungle.getMonkeys().size(); j++) {
				Monkey monkey = jungle.getMonkey(j);
				for(int k = 0; k < monkey.getItems().size(); k++) {
					Integer newVal = monkey.operate(monkey.getItems().get(k));
					
					if(!part2) {
						newVal = Math.floorDiv(newVal, 3);
					}
					
					int newMonkey = monkey.testItem(newVal);
					jungle.getMonkey(newMonkey).addItem(newVal);
				}
				monkey.getItems().clear();
			}
		}
		
		int highest = 0;
		int second = 0;
		for(Monkey monkey : jungle.getMonkeys()) {
			int num = monkey.getItemsInspected();
System.out.println(num);
			if(num > highest) {
				second = highest;
				highest = num;
			}
			else if(num > second) {
				second = num;
			}
		}
		
		//752449677 is too low
		//26522253453 is too high
		System.out.println(CUR_YEAR + " Day 11 Part " + (part2 ? "2" : "1") + ": " + (highest * second));
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
