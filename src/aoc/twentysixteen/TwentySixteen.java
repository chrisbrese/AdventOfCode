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
	 * Run all Day 1 reports.
	 */
	public void day1(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "1");
		
		switch(part) {
			case "A":
				day1NoTimeTaxicabPart1();
				break;
			case "B":
				day1day1NoTimeTaxicabPart2();
				break;
			default:
				day1NoTimeTaxicabPart1();
				day1day1NoTimeTaxicabPart2();
				break;
		}		
	}
	
	/**
	 * The Document indicates that you should start at the given coordinates and face North.
	 * Then, follow the provided sequence: either turn left (L) or right (R) 90 degrees, then walk forward the given number of blocks, 
	 *    ending at a new intersection.
	 * Given that you can only walk on the street grid of the city, how far is the shortest path to the destination?
	 * How many blocks away is Easter Bunny HQ?
	 */
	public void day1NoTimeTaxicabPart1() {
		List<String> directions = Arrays.asList(input.get(0).split(", "));
		
		int blocks = GridUtilities.howManyBlocks(directions, 5000, "N", false, 0, false, false);
		
		System.out.println(CUR_YEAR + " Day 1 Part 1: " + blocks);
	}
	
	/**
	 * Then, you notice the instructions continue on the back of the Recruiting Document.
	 * Easter Bunny HQ is actually at the first location you visit twice.
	 * How many blocks away is the first location you visit twice?
	 */
	public void day1day1NoTimeTaxicabPart2() {
		List<String> directions = Arrays.asList(input.get(0).split(", "));
		
		int blocks = GridUtilities.howManyBlocks(directions, 5000, "N", true, 2, false, false);
		
		System.out.println(CUR_YEAR + " Day 1 Part 2: " + blocks);
	}
	
	/**
	 * Run all Day 2 reports.
	 */
	public void day2(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "2");
		
		switch(part) {
			case "A":
				day2BathroomSecurityPart1();
				break;
			case "B":
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
	
	/**
	 * You left in such a rush that you forgot to use the bathroom!
	 * The document explains that each button to be pressed can be found by starting on the previous button and moving to adjacent buttons on the keypad: 
	 *    U moves up, D moves down, L moves left, and R moves right. 
	 * Each line of instructions corresponds to one button, starting at the previous button (or, for the first line, the "5" button).
	 * Press whatever button you're on at the end of each line. If a move doesn't lead to a button, ignore it.
	 */
	public void day2BathroomSecurityPart1() {
		String[][] numPad = {{"1","4","7"},{"2","5","8"},{"3","6","9"}};
		
		String key = day2KeypadSearch(numPad, "5");
		
		System.out.println(CUR_YEAR + " Day 2 Part 1: " + key);
	}
	
	/**
	 * You finally arrive at the bathroom, the keypad is not at all like you imagined it.
	 * Instead, you are confronted with the result of hundreds of man-hours of bathroom-keypad-design meetings:
			    1
			  2 3 4
			5 6 7 8 9
			  A B C
			    D
     * You still start at "5" and stop when you're at an edge
	 */
	public void day2BathroomSecurityPart2() {
		String[][] numPad = {{"X","X","5","X","X"},{"X","2","6","A","X"},{"1","3","7","B","D"},{"X","4","8","C","X"},{"X","X","9","X","X"}};
		
		String key = day2KeypadSearch(numPad, "5");
		
		System.out.println(CUR_YEAR + " Day 2 Part 2: " + key);
	}
	
	/**
	 * Run all Day 3 reports.
	 */
	public void day3(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "3");
		
		int[][] triangleSides;
		
		triangleSides = parseTriangleInput();
		
		switch(part) {
			case "A":
				day3Squares3SidesPart1(triangleSides);
				break;
			case "B":
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
	
	/**
	 * In a valid triangle, the sum of any two sides must be larger than the remaining side.
	 * In your puzzle input, how many of the listed triangles are possible?
	 */
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
	
	/**
	 * it occurs to you that triangles are specified in groups of three vertically. Each set of three numbers in a column specifies a triangle. Rows are unrelated.
	 * In your puzzle input, and instead reading by columns, how many of the listed triangles are possible?
	 */
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
	 * Run all Day 4 reports.
	 */
	public void day4(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "4");
		
		switch(part) {
			case "A":
				day4SecurityObscurityPart1();
				break;
			case "B":
				day4SecurityObscurityPart2();
				break;
			default:
				day4SecurityObscurityPart1();
				day4SecurityObscurityPart2();
				break;
		}
	}
	
	/**
	 * You come across an information kiosk with a list of rooms.
	 * Of course, the list is encrypted and full of decoy data, but the instructions to decode the list are barely hidden nearby.
	 * Each room consists of an encrypted name (lowercase letters separated by dashes) followed by a dash, a sector ID, and a checksum in square brackets.
	 * A room is real (not a decoy) if the checksum is the five most common letters in the encrypted name, in order, with ties broken by alphabetization.
	 * What is the sum of the sector IDs of the real rooms?
	 */
	public void day4SecurityObscurityPart1() {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
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
						if(alphabet.indexOf(s) < alphabet.indexOf(chk)) {
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
	
	/**
	 * You come across an information kiosk with a list of rooms.
	 * Of course, the list is encrypted and full of decoy data, but the instructions to decode the list are barely hidden nearby.
	 * Each room consists of an encrypted name (lowercase letters separated by dashes) followed by a dash, a sector ID, and a checksum in square brackets.
	 * A room is real (not a decoy) if the checksum is the five most common letters in the encrypted name, in order, with ties broken by alphabetization.
	 * What is the sum of the sector IDs of the real rooms?
	 */
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
	 * Run all Day 5 reports.
	 */
	public void day5(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "5");
		
		switch(part) {
			case "A":
				day5NiceGameOfChessMD5Part1();
				break;
			case "B":
				day5NiceGameOfChessMD5Part2();
				break;
			default:
				day5NiceGameOfChessMD5Part1();
				day5NiceGameOfChessMD5Part2();
				break;
		}
	}
	
	/**
	 * The eight-character password for the door is generated one character at a time by finding the MD5 hash of some Door ID and an increasing integer index (starting with 0).
	 * A hash indicates the next character in the password if its hexadecimal representation starts with five zeroes.
	 * If it does, the sixth character in the hash is the next character of the password.
	 * Given the actual Door ID, what is the password?
	 */
	public void day5NiceGameOfChessMD5Part1() {
		String line = input.get(0);
		
		List<String> hashList = MD5.findMD5HashesOfIncreasingIntegersWithNumZeros(line, 5, 8, true, false);
		
		String pass = "";
		for(String s : hashList) {
			pass += s.substring(5,6);
		}
		
		System.out.println(CUR_YEAR + " Day 5 Part 1: " + pass);
	}
	
	/**
	 * Instead of simply filling in the password from left to right, the hash now also indicates the position within the password to fill.
	 * You still look for hashes that begin with five zeroes; however, now, the sixth character represents the position (0-7), and the seventh character is the character to put in that position.
	 * Given the actual Door ID and this new method, what is the password?
	 */
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
	 * Run all Day 6 reports.
	 */
	public void day6(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "6");
		
		switch(part) {
			case "A":
				day6day6SignalsNoisePart1();
				break;
			case "B":
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
	
	/**
	 * In this model, the same message is sent repeatedly. You've recorded the repeating message signal (your puzzle input), but the data seems quite corrupted - almost too badly to recover. Almost.
	 * All you need to do is figure out which character is most frequent for each position (vertically).
	 * Given the recording in your puzzle input, what is the error-corrected version of the message being sent?
	 */
	public void day6day6SignalsNoisePart1() {
		String pw = day6SignalsNoiseVerticalPassword(true);
		
		System.out.println(CUR_YEAR + " Day 6 Part 1: " + pw);
	}
	
	/**
	 * You can look at the letter distributions in each column and choose the least common letter to reconstruct the original message.
	 * Given the recording in your puzzle input and this new decoding methodology, what is the original message that Santa is trying to send?
	 */
	public void day6day6SignalsNoisePart2() {
		String pw = day6SignalsNoiseVerticalPassword(false);
		
		System.out.println(CUR_YEAR + " Day 6 Part 2: " + pw);
	}
	
	/**
	 * Run all Day 7 reports.
	 */
	public void day7(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "7");
		
		switch(part) {
			case "A":
				day7IPv7Part1();
				break;
			case "B":
				day7IPv7Part2();
				break;
			default:
				day7IPv7Part1();
				day7IPv7Part2();
				break;
		}
	}
	
	/**
	 * An IP supports TLS if it has an Autonomous Bridge Bypass Annotation, or ABBA. 
	 * An ABBA is any four-character sequence which consists of a pair of two different characters followed by the reverse of that pair, such as xyyx or abba.
	 * However, the IP also must not have an ABBA within any hypernet sequences, which are contained by square brackets.
	 * How many IPs in your puzzle input support TLS?
	 */
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
	
	/**
	 * An IP supports SSL if it has an ABA, anywhere in the supernet sequences (outside any square bracketed sections), 
	 * and a corresponding BAB, anywhere in the hypernet sequences.
	 * An ABA is any three-character sequence which consists of the same character twice with a different character between them.
	 * A corresponding BAB is the same characters but in reversed positions.
	 * The interior character must be different.
	 * How many IPs in your puzzle input support SSL?
	 */
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
	
	/**
	 * The screen is 50 pixels wide and 6 pixels tall, all of which start off, and is capable of three somewhat peculiar operations:
	 *    - rect AxB turns on all of the pixels in a rectangle at the top-left of the screen which is A wide and B tall.
	 *    - rotate row y=A by B shifts all of the pixels in row A (0 is the top row) right by B pixels. Pixels that would fall off the right end appear at the left end of the row.
	 *    - rotate column x=A by B shifts all of the pixels in column A (0 is the left column) down by B pixels. Pixels that would fall off the bottom appear at the top of the column.
	 * How many pixels should be lit?
	 */
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
	 * Run all Day 9 reports.
	 */
	public void day9(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "9");
		
		switch(part) {
			case "A":
				day9ExplosivesCyperspace();
				break;
			case "B":
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
	
	/**
	 * The format compresses a sequence of characters.
	 * Whitespace is ignored. To indicate that some sequence should be repeated, a marker is added to the file, like (10x2).
	 * To decompress this marker, take the subsequent 10 characters and repeat them 2 times.
	 * Then, continue reading the file after the repeated data. The marker itself is not included in the decompressed output.
	 * If parentheses or other characters appear within the data referenced by a marker, that's okay - 
	 *    treat it like normal data, not a marker, and then resume looking for markers after the decompressed section.
	 * What is the decompressed length of the file (your puzzle input)? Don't count whitespace.
	 */
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
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
			case "A":
				break;
			case "B":
				break;
			default:
				break;
		}
	}
}
