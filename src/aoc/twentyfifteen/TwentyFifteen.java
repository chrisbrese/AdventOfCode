package aoc.twentyfifteen;

import java.util.List;

import aoc.Year;
import aoc.utilities.MD5;
import aoc.utilities.ReadInputFile;
import circuits.Circuit;
import circuits.Wire;
import circuits.gates.AndGate;
import circuits.gates.LShiftGate;
import circuits.gates.NotGate;
import circuits.gates.OrGate;
import circuits.gates.RShiftGate;

public class TwentyFifteen extends Year {
	
	public TwentyFifteen() {
		CUR_YEAR = 2015;
	}
    
	/**
	 * Run all Day 1 reports.
	 */
	public void day1(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "1");
		
		switch(part) {
			case "A":
				day1NotQuiteLispFloorNavigation();
				break;
			case "B":
				day1NotQuiteLispFindBasement();
				break;
			default:
				day1NotQuiteLispFloorNavigation();
				day1NotQuiteLispFindBasement();
				break;
		}		
	}
	
	/**
	 * Start on the ground floor (floor 0) and then follows the instructions one character at a time.
	 * A '(' means go up one floor, and ')', means go down one floor.
	 * To what floor do the instructions take Santa?
	*/
	public void day1NotQuiteLispFloorNavigation() {
		int count = 0;
		String line = input.get(0);
		
		for(int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == '(') {
				count++;
			}
			else if(line.charAt(i) == ')') {
				count--;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 1 Part A: " + count);
	}
	
	/**
	 * Given the same instructions, find the position of the first character that causes him to enter the basement (floor -1).
	 * The first character in the instructions has position 1, the second character has position 2, and so on.
	 */
	public void day1NotQuiteLispFindBasement() {
		int count = 0;
		String line = input.get(0);
		
		int floorPos = 0;
		for(int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == '(') {
				count++;
			}
			else if(line.charAt(i) == ')') {
				count--;
			}
			
			if(count == -1) {
				floorPos = i+1;
				break;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 1 Part B: " + floorPos);
	}
	
	/**
	 * Run all Day 2 reports.
	 */
	public void day2(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "2");
		
		switch(part) {
			case "A":
				day2NoMathWrappingPaper();
				break;
			case "B":
				day2NoMathRibbon();
				break;
			default:
				day2NoMathWrappingPaper();
				day2NoMathRibbon();
				break;
		}
	}
	
	/**
	 * Find the surface area of the box, which is 2*l*w + 2*w*h + 2*h*l.
	 * The elves also need a little extra paper for each present: the area of the smallest side.
	 */
	public void day2NoMathWrappingPaper() {
		int total = 0;
		for(String s : input) {
			String[] measurementsStr = s.split("x");
			int[] measurements = new int[3];
			measurements[0] = Integer.parseInt(measurementsStr[0]);
			measurements[1] = Integer.parseInt(measurementsStr[1]);
			measurements[2] = Integer.parseInt(measurementsStr[2]);
			
			int curArea = 0;
			
			int side1 = measurements[0] * measurements[1];
			int side2 = measurements[0] * measurements[2];
			int side3 = measurements[1] * measurements[2];
			
			int smallest = 0;
			if(side1 < side2) {
				if(side1 < side3) {
					smallest = side1;
				}
				else {
					smallest = side3;
				}
			}
			else if(side2 < side3) {
				smallest = side2;
			}
			else {
				smallest = side3;
			}
			
			curArea = (2 * side1) + (2 * side2) + (2 * side3) + smallest;
			total += curArea;
		}
		
		System.out.println(CUR_YEAR + " Day 2 Part A: " + total);
	}
	
	/**
	 * The ribbon required to wrap a present is the shortest distance around its sides, or the smallest perimeter of any one face.
	 * Each present also requires a bow made out of ribbon as well; the feet of ribbon required for the perfect bow is equal to the cubic feet of volume of the present.
	 */
	public void day2NoMathRibbon() {
		int total = 0;
		for(String s : input) {
			String[] measurementsStr = s.split("x");
			int[] measurements = new int[3];
			measurements[0] = Integer.parseInt(measurementsStr[0]);
			measurements[1] = Integer.parseInt(measurementsStr[1]);
			measurements[2] = Integer.parseInt(measurementsStr[2]);
			
			int curLength = 0;
			
			int side1 = 2 * measurements[0] + 2 * measurements[1];
			int side2 = 2 * measurements[0] + 2 * measurements[2];
			int side3 = 2 * measurements[1] + 2 * measurements[2];
			
			int smallest = 0;
			if(side1 < side2) {
				if(side1 < side3) {
					smallest = side1;
				}
				else {
					smallest = side3;
				}
			}
			else if(side2 < side3) {
				smallest = side2;
			}
			else {
				smallest = side3;
			}
			
			curLength = smallest + (measurements[0] * measurements[1] * measurements[2]);
			total += curLength;
		}
		
		System.out.println(CUR_YEAR + " Day 2 Part B: " + total);
	}
	
	/**
	 * Run all Day 3 reports.
	 */
	public void day3(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "3");
		
		switch(part) {
			case "A":
				day3HousesInAVacuumHowManyHomesGotAPresent();
				break;
			case "B":
				day3HousesInAVacuumHowManyHomesGotAPresentTwoSantas();
				break;
			default:
				day3HousesInAVacuumHowManyHomesGotAPresent();
				day3HousesInAVacuumHowManyHomesGotAPresentTwoSantas();
				break;
		}
	}
	
	/**
	 * Santa is delivering presents to an infinite two-dimensional grid of houses.
	 * He begins by delivering a present to the house at his starting location.
	 * Moves are always exactly one house to the north (^), south (v), east (>), or west (<).
	 * After each move, he delivers another present to the house at his new location.
	 * How many houses receive at least one present?
	 */
	public void day3HousesInAVacuumHowManyHomesGotAPresent() {
		int total = 1;
		String line = input.get(0);
		int MAX = 5000;
		
		int[][] houseGrid = new int[MAX][MAX];
		
		int curX = MAX/2;
		int curY = MAX/2;
		houseGrid[curX][curY] = 1;
		
		for(int i = 0; i < line.length(); i++) {
			char cur = line.charAt(i);
			
			if(cur == '^') {
				curY--;
			}
			else if(cur == 'v') {
				curY++;
			}
			else if(cur == '<') {
				curX--;
			}
			else if(cur == '>') {
				curX++;
			}
			
			if(houseGrid[curX][curY] == 0) {
				total++;				
			}
			
			houseGrid[curX][curY] ++;
		}
		
		System.out.println(CUR_YEAR + " Day 3 Part A: " + total);
	}
	
	/**
	 * Santa creates a robot version of himself, Robo-Santa, to deliver presents with him.
	 * Santa and Robo-Santa start at the same location (delivering two presents to the same starting house), then take turns moving based on instructions.
	 * This year, how many houses receive at least one present?
	 */
	public void day3HousesInAVacuumHowManyHomesGotAPresentTwoSantas() {
		int total = 1;
		String line = input.get(0);
		int MAX = 5000;
		
		int[][] houseGrid = new int[MAX][MAX];
		
		int curX1 = MAX/2;
		int curX2 = MAX/2;
		int curY1 = MAX/2;
		int curY2 = MAX/2;
		houseGrid[curX1][curY1] ++;
		houseGrid[curX2][curY2] ++;
		
		for(int i = 0; i < line.length(); i++) {
			char cur = line.charAt(i);
			
			if((i % 2) == 0) {
				if(cur == '^') {
					curY1--;
				}
				else if(cur == 'v') {
					curY1++;
				}
				else if(cur == '<') {
					curX1--;
				}
				else if(cur == '>') {
					curX1++;
				}
				
				if(houseGrid[curX1][curY1] == 0) {
					total++;				
				}
				
				houseGrid[curX1][curY1] ++;
			}
			else if((i % 2) == 1) {
				if(cur == '^') {
					curY2--;
				}
				else if(cur == 'v') {
					curY2++;
				}
				else if(cur == '<') {
					curX2--;
				}
				else if(cur == '>') {
					curX2++;
				}
				
				if(houseGrid[curX2][curY2] == 0) {
					total++;				
				}
				
				houseGrid[curX2][curY2] ++;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 3 Part B: " + total);
	}
	
	/**
	 * Run all Day 4 reports.
	 */
	public void day4(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "4");
		
		switch(part) {
			case "A":
				day4IdealStockingStufferAdventCoins(5);
				break;
			case "B":
				day4IdealStockingStufferAdventCoins(6);
				break;
			default:
				day4IdealStockingStufferAdventCoins(5);
				day4IdealStockingStufferAdventCoins(6);
				break;
		}
	}
	
	/**
	 * Find the MD5 hash which, in hexadecimal, start with at [numZeros].
	 * The input to the MD5 hash is your secret key followed by a number in decimal.
	 * To mine AdventCoins, you must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.
	 */
	public void day4IdealStockingStufferAdventCoins(int numZeros) {
		String line = input.get(0);
		
		List<String> hashList = MD5.findMD5HashesOfIncreasingIntegersWithNumZeros(line, numZeros, 1, false, false);
		
		System.out.println(CUR_YEAR + " Day 4 " + numZeros + " zeros: " + hashList.get(0));
	}
	
	/**
	 * Run all Day 5 reports.
	 */
	public void day5(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "5");
		
		switch(part) {
			case "A":
				day5InternElvesNaughtyNiceStringsEasy();
				break;
			case "B":
				day5InternElvesNaughtyNiceStringsHard();
				break;
			default:
				day5InternElvesNaughtyNiceStringsEasy();
				day5InternElvesNaughtyNiceStringsHard();
				break;
		}
	}
	
	/**
	 * Santa needs help figuring out which strings in his text file are naughty or nice.
	 * A nice string is one with all of the following properties:
	 *     It contains at least three vowels.
	 *     It contains at least one letter that appears twice in a row.
	 *     It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
	 * How many strings are nice?
	 */
	public void day5InternElvesNaughtyNiceStringsEasy() {
		int count = 0;
		
		for(String line : input) {
			if(!line.contains("ab") &&
			   !line.contains("cd") &&
			   !line.contains("pq") &&
			   !line.contains("xy")) {
				int vowelCount = 0;
				boolean doubleChar = false;
				char lastChar = 1;
				for(int i = 0; i < line.length(); i++) {
					char curChar = line.charAt(i);
					if(curChar == 'a' ||
					   curChar == 'e' ||
					   curChar == 'i' ||
					   curChar == 'o' ||
					   curChar == 'u') {
						vowelCount++;
					}
					
					if(lastChar != 1) {
						if(lastChar == curChar) {
							doubleChar = true;
						}
					}
					
					lastChar = curChar;
				}
				
				if(vowelCount >= 3 && doubleChar) {
					count++;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 5 Part A: " + count);
	}
	
	/**
	 * Santa needs help figuring out which strings in his text file are naughty or nice.
	 * A nice string is one with all of the following properties:
	 *    It contains a pair of any two letters that appears at least twice in the string without overlapping, like xyxy or aabcdefgaa (aa), but not like aaa.
	 *    It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa.
	 * How many strings are nice?
	 */
	public void day5InternElvesNaughtyNiceStringsHard() {
		int count = 0;
		
		for(String line : input) {
			int doubleCount = 0;
			boolean camelChar = false;

			for(int i = 0; i < line.length(); i++) {
				char curChar = line.charAt(i);
				
				if(i+1 < line.length()) {
					String curPair = line.substring(i, i+2);
					
					if(i+2 < line.length()) {
						char camelCheck = line.charAt(i+2);
						if(curChar == camelCheck) {
							camelChar = true;
						}
						
						String cut = line.substring(i+2);
						
						if(cut.indexOf(curPair) >=0) {
							// once the first one is found, we now have 2 pairs
							if(doubleCount == 0) {
								doubleCount = 2;
							}
							else {
								doubleCount++;
							}
						}
					}
				}
			}
			
			if(doubleCount >= 2 && camelChar) {
				count++;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 5 Part B: " + count);
	}
	
	/**
	 * Run all Day 6 reports.
	 */
	public void day6(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "6");
		
		switch(part) {
			case "A":
				day6FireHazardLightToggle();
				break;
			case "B":
				day6FireHazardLightBrightness();
				break;
			default:
				day6FireHazardLightToggle();
				day6FireHazardLightBrightness();
				break;
		}
	}
	
	/**
	 * Lights in your grid are numbered from 0 to 999 in each direction.
	 * The instructions include whether to turn on, turn off, or toggle various inclusive ranges given as coordinate pairs.
	 * Each coordinate pair represents opposite corners of a rectangle, inclusive. The lights all start turned off.
	 * To defeat your neighbors this year, all you have to do is set up your lights by doing the instructions Santa sent you in order.
	 * For example:
	 *    turn on 0,0 through 999,999 would turn on (or leave on) every light.
	 *    toggle 0,0 through 999,0 would toggle the first line of 1000 lights, turning off the ones that were on, and turning on the ones that were off.
	 *    turn off 499,499 through 500,500 would turn off (or leave off) the middle four lights.
	 * How many lights are lit?
	 */
	public void day6FireHazardLightToggle() {
		int GRID_SIZE = 1000;
		boolean[][] lights = new boolean[GRID_SIZE][GRID_SIZE];
		
		for(String line : input) {
			String[] lineParts = line.split(" ");
			
			String[] start;
			String[] end;
			
			String action;
			if(lineParts.length == 4) {
				action = "toggle";
				start = lineParts[1].split(",");
				end = lineParts[3].split(",");
			}
			else {
				action = lineParts[1];
				start = lineParts[2].split(",");
				end = lineParts[4].split(",");
			}
			
			int startX = Integer.parseInt(start[0]);
			int startY = Integer.parseInt(start[1]);
			int endX = Integer.parseInt(end[0]);
			int endY = Integer.parseInt(end[1]);
			
			for(int i = startX; i <= endX; i++) {
				for(int j = startY; j <= endY; j++) {
					switch(action) {
						case "toggle":
							lights[i][j] = !lights[i][j];
							break;
						case "on":
							lights[i][j] = true;
							break;
						case "off":
							lights[i][j] = false;
							break;
					}
				}
			}
		}
		
		int count = 0;
		for(int i = 0; i < GRID_SIZE; i++) {
			for(int j = 0; j < GRID_SIZE; j++) {
				boolean light = lights[i][j];
				
				if(light) {
					count ++;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 6 Part A: " + count);
	}
	
	/**
	 * The light grid you bought actually has individual brightness controls; each light can have a brightness of zero or more. The lights all start at zero.
	 * The phrase turn on actually means that you should increase the brightness of those lights by 1.
	 * The phrase turn off actually means that you should decrease the brightness of those lights by 1, to a minimum of zero.
	 * The phrase toggle actually means that you should increase the brightness of those lights by 2.
	 * What is the total brightness of all lights combined?
	 */
	public void day6FireHazardLightBrightness() {
		int GRID_SIZE = 1000;
		int[][] lights = new int[GRID_SIZE][GRID_SIZE];
		
		/*
		 *  turn off 674,321 through 793,388
			toggle 749,672 through 973,965
			turn on 943,30 through 990,907
		 */
		
		for(String line : input) {
			String[] lineParts = line.split(" ");
			
			String[] start;
			String[] end;
			
			String action;
			if(lineParts.length == 4) {
				action = "toggle";
				start = lineParts[1].split(",");
				end = lineParts[3].split(",");
			}
			else {
				action = lineParts[1];
				start = lineParts[2].split(",");
				end = lineParts[4].split(",");
			}
			
			int startX = Integer.parseInt(start[0]);
			int startY = Integer.parseInt(start[1]);
			int endX = Integer.parseInt(end[0]);
			int endY = Integer.parseInt(end[1]);
			
			for(int i = startX; i <= endX; i++) {
				for(int j = startY; j <= endY; j++) {
					switch(action) {
						case "toggle":
							lights[i][j] += 2;
							break;
						case "on":
							lights[i][j] += 1;
							break;
						case "off":
							lights[i][j] = ((lights[i][j] - 1) < 0 ? 0 : (lights[i][j] - 1));
							break;
					}
				}
			}
		}
		
		int count = 0;
		for(int i = 0; i < GRID_SIZE; i++) {
			for(int j = 0; j < GRID_SIZE; j++) {
				count += lights[i][j];
			}
		}
		
		System.out.println(CUR_YEAR + " Day 6 Part B: " + count);
	}
	
	/**
	 * Run all Day 7 reports.
	 */
	public void day7(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "7");
		
		switch(part) {
			case "A":
				day7AssemblyRequiredWireInputs();
				break;
			case "B":
				day7AssemblyRequiredWireOverride();
				break;
			default:
				day7AssemblyRequiredWireInputs();
				day7AssemblyRequiredWireOverride();
				break;
		}
	}
	
	public Circuit day7BuildCircuit() {
		Circuit circuit = new Circuit();
		
		for(String line : input) {
			String[] lineParts = line.split(" ");
			
			// send signal value to wire
			if(lineParts.length == 3) {
				Wire output;
				if((output = circuit.getWire(lineParts[2])) == null) {
					output = new Wire(lineParts[2]);
				}
				
				Wire input;
				if(lineParts[0].matches("^[a-z]+$")) {
					if((input = circuit.getWire(lineParts[0])) == null) {
						input = new Wire(lineParts[0]);
						circuit.addOrUpdateWire(input);
					}
				}
				output.setValue(lineParts[0]);			
				
				circuit.addOrUpdateWire(output);
			}
			// NOT gates only
			else if(lineParts.length == 4) {
				Wire input;
				if((input = circuit.getWire(lineParts[1])) == null) {
					input = new Wire(lineParts[1]);
					circuit.addOrUpdateWire(input);
				}
				
				Wire output;
				if((output = circuit.getWire(lineParts[3])) == null) {
					output = new Wire(lineParts[3]);
					circuit.addOrUpdateWire(output);
				}
				
				NotGate ng = new NotGate(input.getName(), output.getName());
				circuit.addGate(ng);
			}
			// all other actions
			else{
				Wire leftInputWire = null;
				Integer leftInputValue = null;

				if(lineParts[0].matches("^[a-z]+$")) {
					if((leftInputWire = circuit.getWire(lineParts[0])) == null) {
						leftInputWire = new Wire(lineParts[0]);
						circuit.addOrUpdateWire(leftInputWire);
					}
				}
				else if(lineParts[0].matches("^[0-9]+$")) {
					leftInputValue = Integer.parseInt(lineParts[0]);
				}
				
				Wire rightInputWire = null;
				Integer rightInputValue = null;
				if(lineParts[2].matches("^[a-z]+$")) {
					if((rightInputWire = circuit.getWire(lineParts[2])) == null) {
						rightInputWire = new Wire(lineParts[2]);
						circuit.addOrUpdateWire(rightInputWire);
					}
				}
				else if(lineParts[2].matches("^[0-9]+$")) {
					rightInputValue = Integer.parseInt(lineParts[2]);
				}
				
				Wire output;
				if((output = circuit.getWire(lineParts[4])) == null) {
					output = new Wire(lineParts[4]);
					circuit.addOrUpdateWire(output);
				}
				
				switch(lineParts[1]) {
					case "AND":
						AndGate ag = new AndGate(output.getName());
						
						if(leftInputWire != null) {
							ag.setLeftInputWireNameOrValue(leftInputWire.getName());
						}
						else {
							ag.setLeftInputWireNameOrValue(leftInputValue.toString());
						}
						
						if(rightInputWire != null) {
							ag.setRightInputWireNameOrValue(rightInputWire.getName());
						}
						else {
							ag.setRightInputWireNameOrValue(rightInputValue.toString());
						}

						circuit.addGate(ag);						
						break;
					case "OR":
						OrGate og = new OrGate(output.getName());
						if(leftInputWire != null) {
							og.setLeftInputWireNameOrValue(leftInputWire.getName());
						}
						else {
							og.setLeftInputWireNameOrValue(leftInputValue.toString());
						}
						
						if(rightInputWire != null) {
							og.setRightInputWireNameOrValue(rightInputWire.getName());
						}
						else {
							og.setRightInputWireNameOrValue(rightInputValue.toString());
						}
						
						circuit.addGate(og);
						break;
					case "RSHIFT":
						RShiftGate rg = new RShiftGate(output.getName());
						if(leftInputWire != null) {
							rg.setLeftInputWireNameOrValue(leftInputWire.getName());
						}
						else {
							rg.setLeftInputWireNameOrValue(leftInputValue.toString());
						}
						
						if(rightInputWire != null) {
							rg.setRightInputWireNameOrValue(rightInputWire.getName());
						}
						else {
							rg.setRightInputWireNameOrValue(rightInputValue.toString());
						}
						
						circuit.addGate(rg);						
						break;
					case "LSHIFT":
						LShiftGate lg = new LShiftGate(output.getName());
						if(leftInputWire != null) {
							lg.setLeftInputWireNameOrValue(leftInputWire.getName());
						}
						else {
							lg.setLeftInputWireNameOrValue(leftInputValue.toString());
						}
						
						if(rightInputWire != null) {
							lg.setRightInputWireNameOrValue(rightInputWire.getName());
						}
						else {
							lg.setRightInputWireNameOrValue(rightInputValue.toString());
						}
						
						circuit.addGate(lg);
						break;
				}
			}
		}
		
		return circuit;
	}
	
	/**
	 * Each wire has an identifier (lowercase letters) and can carry a 16-bit signal.
	 * A signal is provided to each wire by a gate, another wire, or some specific value.
	 * Each wire can only get a signal from one source, but can provide its signal to multiple destinations. A gate provides no signal until all of its inputs have a signal.
	 * The included instructions booklet describes how to connect the parts together: 
	 *    x AND y -> z means to connect wires x and y to an AND gate, and then connect its output to wire z.
	 * For example:
	 *    123 -> x means that the signal 123 is provided to wire x.
	 *    x AND y -> z means that the bitwise AND of wire x and wire y is provided to wire z.
	 *    p LSHIFT 2 -> q means that the value from wire p is left-shifted by 2 and then provided to wire q.
	 *    NOT e -> f means that the bitwise complement of the value from wire e is provided to wire f.
	 *    Other possible gates include OR (bitwise OR) and RSHIFT (right-shift).
	 * What signal is ultimately provided to wire a?
	 */
	public void day7AssemblyRequiredWireInputs() {
		Circuit circuit = day7BuildCircuit();
		
		circuit.runCircuit();
		
		System.out.println(CUR_YEAR + " Day 7 Part A: " + circuit.getWireValue("a"));
	}
	
	/**
	 * Now, take the signal you got on wire a, override wire b to that signal, and reset the other wires (including wire a). 
	 * What new signal is ultimately provided to wire a?
	 */
	public void day7AssemblyRequiredWireOverride() {
		Circuit circuit = day7BuildCircuit();
		
		Wire bWire = circuit.getWire("b");
		bWire.setValue((new Integer(16076)).toString());
		circuit.addOrUpdateWire(bWire);
		
		circuit.runCircuit();
		
		System.out.println(CUR_YEAR + " Day 7 Part B: " + circuit.getWireValue("a"));
	}
	
	/**
	 * Run all Day 8 reports.
	 */
	public void day8(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "8");
		
		switch(part) {
			case "A":
				day8MatchsticksDecodeStringLiterals();
				break;
			case "B":
				day8MatchsticksEncodeStringLiterals();
				break;
			default:
				day8MatchsticksDecodeStringLiterals();
				day8MatchsticksEncodeStringLiterals();
				break;
		}
	}
	
	/**
	 * It is common in many programming languages to provide a way to escape special characters in strings.
	 * However, it is important to realize the difference between the number of characters in the 
	 * code representation of the string literal and the number of characters in the in-memory string itself.
	 * Santa's list is a file that contains many double-quoted string literals, one on each line.
	 * The only escape sequences used are:
	 *    \\ (which represents a single backslash),
	 *    \" (which represents a lone double-quote character), and 
	 *    \x plus two hexadecimal characters (which represents a single character with that ASCII code).
	 * Disregarding the whitespace in the file, what is the number of characters of code for string literals, 
	 * minus the number of characters in memory for the values of the strings in total for the entire file?
	 */
	public void day8MatchsticksDecodeStringLiterals() {
		int actualChars = 0;
		int inMemoryChars = 0;
		
		for(String l : input) {
			actualChars += l.length();
			String line = l.substring(1, l.length()-1);
			
			for(int i = 0; i < line.length(); i++) {
				String cur = line.substring(i,i+1);
				
				if(cur.equals("\\")){
					String cur1 = line.substring(i+1,i+2);
					
					if(cur1.equals("\\") || cur1.equals("\"")) {
						i ++;
					}
					else {
						i+=3;
					}
				}
				
				inMemoryChars++;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 8 Part A: " + (actualChars - inMemoryChars));
	}
	
	public void day8MatchsticksEncodeStringLiterals() {
		int actualChars = 0;
		int afterEncodedChars = 0;
		
		for(String line : input) {
			actualChars += line.length();

			for(int i = 0; i < line.length(); i++) {
				String cur = line.substring(i,i+1);
				
				if(cur.equals("\"") || cur.equals("\\")) {
					line = line.substring(0,i) + "\\" + line.substring(i);
					i ++;
				}
			}

			line = "\"" + line + "\"";
			afterEncodedChars += line.length();
		}
		
		System.out.println(CUR_YEAR + " Day 8 Part B: " + (afterEncodedChars - actualChars));
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
