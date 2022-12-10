package aoc.twentysixteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import aoc.Year;
import aoc.utilities.MD5;
import aoc.utilities.ReadInputFile;
import aoc.utilities.grid.GridUtilities;

public class TwentySixteen extends Year {
	
	public TwentySixteen() {
		CUR_YEAR = 2016;
	}
    
	/**
	 * {@link https://adventofcode.com/2016/day/1}
	 * Run all Day 1 reports.
	 */
	public void day1(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "1");
		
		switch(part) {
			case "1":
				day1NoTimeTaxicabPart1();
				break;
			case "2":
				day1day1NoTimeTaxicabPart2();
				break;
			default:
				day1NoTimeTaxicabPart1();
				day1day1NoTimeTaxicabPart2();
				break;
		}		
	}
	
	public void day1NoTimeTaxicabPart1() {
		List<String> directions = Arrays.asList(input.get(0).split(", "));
		
		int blocks = GridUtilities.howManyBlocks(directions, 5000, "N", false, 0, false, false);
		
		System.out.println(CUR_YEAR + " Day 1 Part 1: " + blocks);
	}
	
	public void day1day1NoTimeTaxicabPart2() {
		List<String> directions = Arrays.asList(input.get(0).split(", "));
		
		int blocks = GridUtilities.howManyBlocks(directions, 5000, "N", true, 2, false, false);
		
		System.out.println(CUR_YEAR + " Day 1 Part 2: " + blocks);
	}
	
	/**
	 * {@link https://adventofcode.com/2016/day/2}
	 * Run all Day 2 reports.
	 */
	public void day2(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "2");
		
		switch(part) {
			case "1":
				day2BathroomSecurityPart1();
				break;
			case "2":
				day2BathroomSecurityPart2();
				break;
			default:
				day2BathroomSecurityPart1();
				day2BathroomSecurityPart2();
				break;
		}
	}
	
	public String day2KeypadSearch(String[][] keyPad, String startChar) {
		String key = "";
		
		int curX = 0;
		int curY = 0;
		
		for(int i = 0; i < keyPad.length; i++) {
			for(int j = 0; j < keyPad[i].length; j++) {
				if(keyPad[i][j].equalsIgnoreCase(startChar)) {
					curX = i;
					curY = j;
				}
			}
		}
		
		for(String line : input) {
			for(int i = 0; i < line.length(); i++) {
				switch(line.substring(i, i+1)) {
					case "U":
						if(((curY-1) >= 0) && !keyPad[curX][curY-1].equalsIgnoreCase("X")) {
							curY--;
						}
						break;
					case "D":
						if(((curY+1) <= keyPad[0].length-1) && !keyPad[curX][curY+1].equalsIgnoreCase("X")) {
							curY++;
						}
						break;
					case "L":
						if(((curX-1) >= 0) && !keyPad[curX-1][curY].equalsIgnoreCase("X")) {
							curX--;
						}
						break;
					case "R":
						if(((curX+1) <= keyPad.length-1) && !keyPad[curX+1][curY].equalsIgnoreCase("X")) {
							curX++;
						}
						break;
				}
			}
			
			key += keyPad[curX][curY];
		}
		
		return key;
	}
	
	public void day2BathroomSecurityPart1() {
		String[][] numPad = {{"1","4","7"},{"2","5","8"},{"3","6","9"}};
		
		String key = day2KeypadSearch(numPad, "5");
		
		System.out.println(CUR_YEAR + " Day 2 Part 1: " + key);
	}
	
	public void day2BathroomSecurityPart2() {
		String[][] numPad = {{"X","X","5","X","X"},{"X","2","6","A","X"},{"1","3","7","B","D"},{"X","4","8","C","X"},{"X","X","9","X","X"}};
		
		String key = day2KeypadSearch(numPad, "5");
		
		System.out.println(CUR_YEAR + " Day 2 Part 2: " + key);
	}
	
	/**
	 * {@link https://adventofcode.com/2016/day/3}
	 * Run all Day 3 reports.
	 */
	public void day3(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "3");
		
		int[][] triangleSides;
		
		triangleSides = parseTriangleInput();
		
		switch(part) {
			case "1":
				day3Squares3SidesPart1(triangleSides);
				break;
			case "2":
				day3Squares3SidesPart2(triangleSides);
				break;
			default:
				day3Squares3SidesPart1(triangleSides);
				day3Squares3SidesPart2(triangleSides);
				break;
		}
	}
	
	public int[][] parseTriangleInput(){
		int[][] triangleInput = new int[3][input.size()];
		
		int curY = 0;
		
		for(String line : input) {
			int count = 0;
			String part = "";
			
			for(int i = 0; i < line.length(); i++) {
				String tmp = line.substring(i,i+1);
				
				if(!tmp.equals(" ") || (i+1 == line.length())) {
					part += tmp;
					
					if(i+1 == line.length()) {
						triangleInput[count][curY] = Integer.valueOf(part);
					}
				}
				else if(part != ""){
					triangleInput[count][curY] = Integer.valueOf(part);
					part = "";
					count++;
				}
			}
			
			curY++;
		}
		
		return triangleInput;
	}
	
	public void day3Squares3SidesPart1(int[][] triangleSides) {
		int valid = 0;
		
		int curY = 0;
		
		for(int i = 0; i < triangleSides[0].length; i++) {
			if(triangleSides[0][curY] + triangleSides[1][curY] > triangleSides[2][curY] &&
			   triangleSides[0][curY] + triangleSides[2][curY] > triangleSides[1][curY] &&
			   triangleSides[1][curY] + triangleSides[2][curY] > triangleSides[0][curY]) {
				valid++;
			}
			
			curY++;
		}
		
		System.out.println(CUR_YEAR + " Day 3 Part 1: " + valid);
	}
	
	public void day3Squares3SidesPart2(int[][] triangleSides) {
		int valid = 0;
		
		int curY = 0;
		
		while (curY < triangleSides[0].length) {
			for(int i = 0; i < 3; i++) {
				if(triangleSides[i][curY] + triangleSides[i][curY+1] > triangleSides[i][curY+2] &&
				   triangleSides[i][curY] + triangleSides[i][curY+2] > triangleSides[i][curY+1] &&
				   triangleSides[i][curY+1] + triangleSides[i][curY+2] > triangleSides[i][curY]) {
					valid++;
				}
			}
			
			curY+=3;
		}
		
		System.out.println(CUR_YEAR + " Day 3 Part 2: " + valid);
	}
	
	/**
	 * {@link https://adventofcode.com/2016/day/4}
	 * Run all Day 4 reports.
	 */
	public void day4(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "4");
		
		switch(part) {
			case "1":
				day4SecurityObscurityPart1();
				break;
			case "2":
				day4SecurityObscurityPart2();
				break;
			default:
				day4SecurityObscurityPart1();
				day4SecurityObscurityPart2();
				break;
		}
	}
	
	public void day4SecurityObscurityPart1() {
		int sum = 0;
		
		for(String line : input) {
			String[] parts = line.split("-");
			String[] checksumParts = parts[parts.length-1].split("\\[");
			
			parts[parts.length-1] = checksumParts[0];
			String checksum = checksumParts[1].substring(0,checksumParts[1].length()-1);
			String sector = parts[parts.length-1];
			
			String id = "";
			for(int i = 0; i < parts.length-1; i++) {
				id += parts[i];
			}
			
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for(int i = 0; i < id.length(); i++) {
				String cur = id.substring(i,i+1);
				if(map.containsKey(cur)) {
					Integer curVal = map.get(cur);
					map.put(cur, curVal+1);
				}
				else {
					map.put(cur, 1);
				}
			}
			
			List<String> checksumTmp = new ArrayList<String>();
			for(String s : map.keySet()) {
				for(String chk : checksumTmp){
					if(map.get(s) > map.get(chk)) {
						checksumTmp.add(checksumTmp.indexOf(chk), s);
						break;
					}
					else if(map.get(s) == map.get(chk)) {
						if(alphabetStr.indexOf(s) < alphabetStr.indexOf(chk)) {
							checksumTmp.add(checksumTmp.indexOf(chk), s);
							break;
						}
					}
				}
				
				if(!checksumTmp.contains(s)) {
					checksumTmp.add(s);
				}
			}
			
			String checksumChk = "";
			for(String s : checksumTmp) {
				checksumChk += s;
			}
			
			if(checksumChk.substring(0, 5).equals(checksum)) {
				sum += Integer.parseInt(sector);
			}
		}
		
		System.out.println(CUR_YEAR + " Day 4 Part 1: " + sum);
	}
	
	public void day4SecurityObscurityPart2() {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String result = "";
		
		for(String line : input) {
			String[] parts = line.split("-");
			String[] checksumParts = parts[parts.length-1].split("\\[");
			
			parts[parts.length-1] = checksumParts[0];
			String sector = parts[parts.length-1];
			
			String phrase = "";
			for(int i = 0; i < parts.length-1; i++) {
				String part = parts[i];
				
				for(int j = 0; j < part.length(); j++) {
					String p = part.substring(j, j+1);
					
					int cur = alphabet.indexOf(p);
					cur += Integer.parseInt(sector);
					
					cur = cur % 26;
					
					phrase += alphabet.substring(cur, cur+1);
				}
				
				phrase += " ";
			}
			
			if(phrase.contains("northpole")) {
				result = sector;
				break;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 4 Part 2: " + result);
	}
	
	/**
	 * {@link https://adventofcode.com/2016/day/5}
	 * Run all Day 5 reports.
	 */
	public void day5(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "5");
		
		switch(part) {
			case "1":
				day5NiceGameOfChessMD5Part1();
				break;
			case "2":
				day5NiceGameOfChessMD5Part2();
				break;
			default:
				day5NiceGameOfChessMD5Part1();
				day5NiceGameOfChessMD5Part2();
				break;
		}
	}
	
	public void day5NiceGameOfChessMD5Part1() {
		String line = input.get(0);
		
		List<String> hashList = MD5.findMD5HashesOfIncreasingIntegersWithNumZeros(line, 5, 8, true, false);
		
		String pass = "";
		for(String s : hashList) {
			pass += s.substring(5,6);
		}
		
		System.out.println(CUR_YEAR + " Day 5 Part 1: " + pass);
	}
	
	public void day5NiceGameOfChessMD5Part2() {
		String line = input.get(0);
		
		List<String> hashList = MD5.findMD5HashesOfIncreasingIntegersWithNumZeros(line, 5, 8, false, true);
		
		String pass = "";
		for(String s : hashList) {
			pass += s;
		}
		
		System.out.println(CUR_YEAR + " Day 5 Part 2: " + pass);
	}
	
	/**
	 * {@link https://adventofcode.com/2016/day/6}
	 * Run all Day 6 reports.
	 */
	public void day6(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "6");
		
		switch(part) {
			case "1":
				day6day6SignalsNoisePart1();
				break;
			case "2":
				day6day6SignalsNoisePart2();
				break;
			default:
				day6day6SignalsNoisePart1();
				day6day6SignalsNoisePart2();
				break;
		}
	}
	
	public String day6SignalsNoiseVerticalPassword(boolean useMostFoundChar) {
		int xSize = input.get(0).length();
		int ySize = input.size();
		String[][] grid = new String[xSize][ySize];
		
		// fill in the grid
		int curX = 0;
		int curY = 0;
		for(String line : input) {
			for (int i = 0; i < xSize; i++) {
				grid[i][curY] = line.substring(i,i+1);
			}
			curY++;
		}
		
		curY = 0;
		curX = 0;
		String pw = "";
		while(curX < xSize) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for(int y = 0; y < ySize; y++) {
				String cur = grid[curX][y];
				
				if(map.containsKey(cur)) {
					Integer curVal = map.get(cur);
					map.put(cur, curVal+1);
				}
				else {
					map.put(cur, 1);
				}
				
				curY++;
			}
			
			if(useMostFoundChar) {
				String highest = "";
				for(String s : map.keySet()) {
					if(highest.equals("")) {
						highest = s;
					}
					else if(map.get(s) > map.get(highest)){
						highest = s;
					}
				}
				pw += highest;
				curX++;
			}
			else {
				String lowest = "";
				for(String s : map.keySet()) {
					if(lowest.equals("")) {
						lowest = s;
					}
					else if(map.get(s) < map.get(lowest)){
						lowest = s;
					}
				}
				pw += lowest;
				curX++;
			}
		}
		
		return pw;
	}
	
	public void day6day6SignalsNoisePart1() {
		String pw = day6SignalsNoiseVerticalPassword(true);
		
		System.out.println(CUR_YEAR + " Day 6 Part 1: " + pw);
	}
	
	public void day6day6SignalsNoisePart2() {
		String pw = day6SignalsNoiseVerticalPassword(false);
		
		System.out.println(CUR_YEAR + " Day 6 Part 2: " + pw);
	}
	
	/**
	 * {@link https://adventofcode.com/2016/day/7}
	 * Run all Day 7 reports.
	 */
	public void day7(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "7");
		
		switch(part) {
			case "1":
				day7IPv7Part1();
				break;
			case "2":
				day7IPv7Part2();
				break;
			default:
				day7IPv7Part1();
				day7IPv7Part2();
				break;
		}
	}
	
	public void day7IPv7Part1() {
		int abbaCount = 0;
		for(String line : input) {
			boolean insideBrackets = false;
			boolean abbaFound = false;
			
			for(int i = 0; i < line.length(); i++) {
				String cur1 = line.substring(i,i+1);				
				
				if(cur1.equals("[")) {
					insideBrackets = true;
				}
				else if(cur1.equals("]")) {
					insideBrackets = false;
				}
				else {
					try {
						String cur2 = line.substring(i+1,i+2);
						
						if(cur2.matches("^[a-z]+$") && !cur1.equals(cur2)) {
							String curCombo = cur1 + cur2;
							
							String cur3 = line.substring(i+2,i+3);
							String cur4 = line.substring(i+3,i+4);
							String compareCombo = cur4 + cur3;
							
							if(curCombo.equals(compareCombo)) {
								if(!insideBrackets) {
									abbaFound = true;
								}
								else {
									abbaFound = false;
									break;
								}
							}
						}
					}
					catch(IndexOutOfBoundsException ioobe) {
						break;
					}
				}
			}
			
			if(abbaFound) {
				abbaCount++;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 7 Part 1: " + abbaCount);
	}
	
	public void day7IPv7Part2() {
		int abaBabCount = 0;
		for(String line : input) {
			boolean insideBrackets = false;
			boolean babFound = false;
			
			List<String> possibleAbas = new ArrayList<String>();			
			for(int i = 0; i < line.length(); i++) {
				String cur1 = line.substring(i,i+1);				
				
				if(cur1.equals("[")) {
					insideBrackets = true;
				}
				else if(cur1.equals("]")) {
					insideBrackets = false;
				}
				else {
					try {
						String cur2 = line.substring(i+1,i+2);
						String cur3 = line.substring(i+2,i+3);
						
						if(cur2.matches("^[a-z]+$") && 
						   cur3.matches("^[a-z]+$") &&
						   !cur1.equals(cur2) &&
						   cur1.equals(cur3)) {							
							if(!insideBrackets) {
								possibleAbas.add(cur1 + cur2 + cur3);
							}
						}
					}
					catch(IndexOutOfBoundsException ioobe) {
						break;
					}
				}
			}
			
			for(String aba : possibleAbas) {
				String bab = aba.substring(1,2) + aba.substring(0,1) + aba.substring(1,2);
				
				for(int i = 0; i < line.length(); i++) {
					String cur1 = line.substring(i,i+1);				
					
					if(cur1.equals("[")) {
						insideBrackets = true;
					}
					else if(cur1.equals("]")) {
						insideBrackets = false;
					}
					else {
						try {
							String cur2 = line.substring(i+1,i+2);
							String cur3 = line.substring(i+2,i+3);
							
							if((cur1+cur2+cur3).equals(bab)) {
								if(insideBrackets) {
									babFound = true;
								}
							}
						}
						catch(IndexOutOfBoundsException ioobe) {
							break;
						}
					}
				}
			}
			
			if(babFound) {
				abaBabCount++;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 7 Part 2: " + abaBabCount);
	}
	
	/**
	 * {@link https://adventofcode.com/2016/day/8}
	 * Run all Day 8 reports.
	 */
	public void day8(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "8");
		
		switch(part) {
			default:
				day82FARotatingGrid();
				break;
		}
	}
	
	public void day8PrintGrid(boolean[][] grid) {
		int NUM_COLUMNS = 50;
		int NUM_ROWS = 6;
		for(int y = 0; y < NUM_ROWS; y++) {
			for(int x = 0; x < NUM_COLUMNS; x++) {			
				if(grid[y][x]) {
					System.out.print("##");
				}
				else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	public void day82FARotatingGrid() {
		int NUM_COLUMNS = 50;
		int NUM_ROWS = 6;
		boolean[][] grid = new boolean[NUM_ROWS][NUM_COLUMNS];
		int lightCount = 0;
		
		for(String line : input) {
			String parts[] = line.split(" ");
			
			if(parts.length == 2) {
				String[] size = parts[1].split("x");
				int numColumns = Integer.parseInt(size[0]);
				int numRows = Integer.parseInt(size[1]);
				
				for(int x = 0; x < numColumns; x++) {
					for(int y = 0; y < numRows; y++) {
						grid[y][x] = true;
					}
				}
			}
			else {
				String[] loc = parts[2].split("=");
				int pos = Integer.parseInt(loc[1]);
				int amount = Integer.parseInt(parts[4]);
				
				switch(parts[1]) {
					case "row":
						boolean[] row = new boolean[NUM_COLUMNS];
						for(int x = 0; x < NUM_COLUMNS; x++) {
							row[x] = grid[pos][x];
						}
						
						for(int x = 0; x < NUM_COLUMNS; x++) {
							if(x + amount < NUM_COLUMNS){
								grid[pos][x+amount] = row[x];
							}
							else {
								grid[pos][x+amount-NUM_COLUMNS] = row[x];
							}
						}
						break;
					case "column":
						boolean[] column = new boolean[NUM_ROWS];
						for(int y = 0; y < NUM_ROWS; y++) {
							column[y] = grid[y][pos];
						}
						
						for(int y = 0; y < NUM_ROWS; y++) {
							if(y + amount < NUM_ROWS){
								grid[y+amount][pos] = column[y];
							}
							else {
								grid[y+amount-NUM_ROWS][pos] = column[y];
							}
						}
						break;
				}
			}
		}
		
		for(int y = 0; y < NUM_ROWS; y++) {
			for(int x = 0; x < NUM_COLUMNS; x++) {			
				if(grid[y][x]) {
					lightCount++;
				}
			}
		}
		
		day8PrintGrid(grid);
		
		System.out.println(CUR_YEAR + " Day 8: " + lightCount);
	}
	
	/**
	 * {@link https://adventofcode.com/2016/day/9}
	 * Run all Day 9 reports.
	 */
	public void day9(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "9");
		
		switch(part) {
			case "1":
				day9ExplosivesCyperspace();
				break;
			case "2":
				// TODO:
				break;
			default:
				day9ExplosivesCyperspace();
				break;
		}
	}
	
	public long day9RecursiveDecompression(long curLength, String text, int curPos) {		
		String tmpLine = "";
		
		for(int i = curPos; i < text.length(); i++) {
			String cur = text.substring(i,i+1);
			
			while(!cur.equals("(") && i < text.length()) {
				tmpLine += cur;
				i++;
				if(i < text.length()) {
					cur = text.substring(i,i+1);
				}
			}
			
			if(cur.equals("(")) {
				String marker = text.substring(i+1,text.indexOf(")",i+1));
				String[] parts = marker.split("x");
				int numChars = Integer.parseInt(parts[0]);
				int numRepeats = Integer.parseInt(parts[1]);
				
				int tmpI = i+(marker.length()+2);

				for(int x = 0; x < numRepeats; x++) {
					try {
						tmpLine += text.substring(tmpI, tmpI + numChars);
					}
					catch(StringIndexOutOfBoundsException sioobe) {
						tmpLine += text.substring(tmpI);
					}
				}
				
				i = (i+(marker.length()+2) + (numChars-1));
			}
		}			
		
		curLength += tmpLine.length();
		
		return -1;
	}
	
	public void day9ExplosivesCyperspace() {
		int totalLength = 0;
		
		for(String line : input) {
			String tmpLine = "";
			
			for(int i = 0; i < line.length(); i++) {
				String cur = line.substring(i,i+1);
				
				while(!cur.equals("(") && i < line.length()) {
					tmpLine += cur;
					i++;
					if(i < line.length()) {
						cur = line.substring(i,i+1);
					}
				}
				
				if(cur.equals("(")) {
					String marker = line.substring(i+1,line.indexOf(")",i+1));
					String[] parts = marker.split("x");
					int numChars = Integer.parseInt(parts[0]);
					int numRepeats = Integer.parseInt(parts[1]);
					
					int tmpI = i+(marker.length()+2);

					for(int x = 0; x < numRepeats; x++) {
						try {
							tmpLine += line.substring(tmpI, tmpI + numChars);
						}
						catch(StringIndexOutOfBoundsException sioobe) {
							tmpLine += line.substring(tmpI);
						}
					}
					
					i = (i+(marker.length()+2) + (numChars-1));
				}
			}			
			totalLength += tmpLine.length();
		}
		
		System.out.println(CUR_YEAR + " Day 9 Part 1: " + totalLength);
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
