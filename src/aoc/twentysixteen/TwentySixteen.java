package aoc.twentysixteen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aoc.Year;
import aoc.utilities.ReadInputFile;

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
				day1NoTimeTaxicabHowManyBlocks(false);
				break;
			case "B":
				day1day1NoTimeTaxicabFirstOneTwice();
				break;
			default:
				day1NoTimeTaxicabHowManyBlocks(false);
				day1day1NoTimeTaxicabFirstOneTwice();
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
	public int day1NoTimeTaxicabHowManyBlocks(boolean useGrid) {
		int blocks = 0;
		
		int MAX = 5000;
		int[][] grid = new int[MAX][MAX];
		
		int curX = MAX/2;
		int curY = MAX/2;
		
		int startX = curX;
		int startY = curY;
		int endX = -1 , endY = -1;
		
		grid[curX][curY] = 1;
		
		String[] directions = input.get(0).split(", ");
		
		// pointer can be N, E, S, W
		String pointer = "N";

		int counter = 0;
		boolean done = false;
		for(String d : directions) {
			char dir = d.charAt(0);
			int num = Integer.parseInt(d.substring(1));
			
			int endXTmp = curX;
			int endYTmp = curY;
			
			if(dir == 'L') {
				switch(pointer) {
					case "N":
						endXTmp -= num;
						
						if(useGrid) {
							while(curX > endXTmp && !done) {
								curX--;
								grid[curX][curY]++;
								
								if(grid[curX][curY] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointer = "W";
						break;
					case "E":
						endYTmp -= num;
						
						if(useGrid) {
							while(curY > endYTmp && !done) {
								curY--;
								grid[curX][curY]++;
								
								if(grid[curX][curY] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointer = "N";
						break;
					case "S":
						endXTmp += num;
						
						if(useGrid) {
							while(curX < endXTmp && !done) {
								curX++;
								grid[curX][curY]++;
								
								if(grid[curX][curY] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointer = "E";
						break;
					case "W":
						endYTmp += num;
						
						if(useGrid) {
							while(curY < endYTmp && !done) {
								curY++;
								grid[curX][curY]++;
								
								if(grid[curX][curY] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointer = "S";
						break;
				}
				
				if(done) {
					break;
				}
			}
			else if(dir == 'R') {
				switch(pointer) {
					case "N":
						endXTmp += num;
						
						if(useGrid) {
							while(curX < endXTmp && !done) {
								curX++;
								grid[curX][curY]++;
								
								if(grid[curX][curY] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointer = "E";
						break;
					case "E":
						endYTmp += num;
						
						if(useGrid) {
							while(curY < endYTmp && !done) {
								curY++;
								grid[curX][curY]++;
								
								if(grid[curX][curY] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointer = "S";
						break;
					case "S":
						endXTmp -= num;
						
						if(useGrid) {
							while(curX > endXTmp && !done) {
								curX--;
								grid[curX][curY]++;
								
								if(grid[curX][curY] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointer = "W";
						break;
					case "W":
						endYTmp -= num;
						
						if(useGrid) {
							while(curY > endYTmp && !done) {
								curY--;
								grid[curX][curY]++;
								
								if(grid[curX][curY] == 2) {
									done = true;
									break;
								}
							}
						}
						
						pointer = "N";
						break;
				}
			}			
			
			if(!useGrid) {
				curX = endXTmp;
				curY = endYTmp;
				
				if(counter == (directions.length-1)) {
					endX = curX;
					endY = curY;
					break;
				}				
			}			
			else if(useGrid) {				
				if(done) {
					endX = curX;
					endY = curY;
					break;
				}
			}
			
			counter++;
		}
		
		blocks = Math.abs(endX-startX) + Math.abs(endY-startY);
		
		System.out.println(CUR_YEAR + " Day 1 Part A: " + blocks);
		
		return blocks;
	}
	
	/**
	 * Then, you notice the instructions continue on the back of the Recruiting Document.
	 * Easter Bunny HQ is actually at the first location you visit twice.
	 * How many blocks away is the first location you visit twice?
	 */
	public void day1day1NoTimeTaxicabFirstOneTwice() {
		int blocks = day1NoTimeTaxicabHowManyBlocks(true);
		
		System.out.println(CUR_YEAR + " Day 1 Part B: " + blocks);
	}
	
	/**
	 * Run all Day 2 reports.
	 */
	public void day2(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "2");
		
		switch(part) {
			case "A":
				day2BathroomSecurityNumPad();
				break;
			case "B":
				day2BathroomSecurityDiamondPad();
				break;
			default:
				day2BathroomSecurityNumPad();
				day2BathroomSecurityDiamondPad();
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
	public void day2BathroomSecurityNumPad() {
		String[][] numPad = {{"1","4","7"},{"2","5","8"},{"3","6","9"}};
		
		String key = day2KeypadSearch(numPad, "5");
		
		System.out.println(CUR_YEAR + " Day 2 Part A: " + key);
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
	public void day2BathroomSecurityDiamondPad() {
		String[][] numPad = {{"X","X","5","X","X"},{"X","2","6","A","X"},{"1","3","7","B","D"},{"X","4","8","C","X"},{"X","X","9","X","X"}};
		
		String key = day2KeypadSearch(numPad, "5");
		
		System.out.println(CUR_YEAR + " Day 2 Part B: " + key);
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
				day3Squares3SidesPossibleTriangles(triangleSides);
				break;
			case "B":
				day3TrianglesVertical(triangleSides);
				break;
			default:
				day3Squares3SidesPossibleTriangles(triangleSides);
				day3TrianglesVertical(triangleSides);
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
	public void day3Squares3SidesPossibleTriangles(int[][] triangleSides) {
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
		
		System.out.println(CUR_YEAR + " Day 3 Part A: " + valid);
	}
	
	/**
	 * it occurs to you that triangles are specified in groups of three vertically. Each set of three numbers in a column specifies a triangle. Rows are unrelated.
	 * In your puzzle input, and instead reading by columns, how many of the listed triangles are possible?
	 */
	public void day3TrianglesVertical(int[][] triangleSides) {
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
		
		System.out.println(CUR_YEAR + " Day 3 Part B: " + valid);
	}
	
	/**
	 * Run all Day 4 reports.
	 */
	public void day4(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "4");
		
		switch(part) {
			case "A":
				day4SecurityObscuritySectorIdSums();
				break;
			case "B":
				day4SecurityObscuritySectorShiftCipher();
				break;
			default:
				day4SecurityObscuritySectorIdSums();
				day4SecurityObscuritySectorShiftCipher();
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
	public void day4SecurityObscuritySectorIdSums() {
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
		
		System.out.println(CUR_YEAR + " Day 4 Part A: " + sum);
	}
	
	/**
	 * You come across an information kiosk with a list of rooms.
	 * Of course, the list is encrypted and full of decoy data, but the instructions to decode the list are barely hidden nearby.
	 * Each room consists of an encrypted name (lowercase letters separated by dashes) followed by a dash, a sector ID, and a checksum in square brackets.
	 * A room is real (not a decoy) if the checksum is the five most common letters in the encrypted name, in order, with ties broken by alphabetization.
	 * What is the sum of the sector IDs of the real rooms?
	 */
	public void day4SecurityObscuritySectorShiftCipher() {
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
		
		System.out.println(CUR_YEAR + " Day 4 Part B: " + result);
	}
	
	/**
	 * Run all Day 5 reports.
	 */
	public void day5(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "5");
		
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
	 * Run all Day 6 reports.
	 */
	public void day6(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "6");
		
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
	 * Run all Day 7 reports.
	 */
	public void day7(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "7");
		
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
	 * Run all Day 8 reports.
	 */
	public void day8(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "8");
		
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
	 * Run all Day 9 reports.
	 */
	public void day9(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "9");
		
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
