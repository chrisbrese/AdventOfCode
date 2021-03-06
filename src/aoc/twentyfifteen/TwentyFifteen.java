package aoc.twentyfifteen;

import java.util.ArrayList;
import java.util.List;

import aoc.Year;
import aoc.circuits.Circuit;
import aoc.circuits.Wire;
import aoc.circuits.gates.AndGate;
import aoc.circuits.gates.LShiftGate;
import aoc.circuits.gates.NotGate;
import aoc.circuits.gates.OrGate;
import aoc.circuits.gates.RShiftGate;
import aoc.twentyfifteen.daynine.City;
import aoc.twentyfifteen.daynine.Country;
import aoc.utilities.MD5;
import aoc.utilities.ReadInputFile;

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
			case "1":
				day1NotQuiteLispPart1();
				break;
			case "2":
				day1NotQuiteLispPart2();
				break;
			default:
				day1NotQuiteLispPart1();
				day1NotQuiteLispPart2();
				break;
		}		
	}
	
	/**
	 * Start on the ground floor (floor 0) and then follows the instructions one character at a time.
	 * A '(' means go up one floor, and ')', means go down one floor.
	 * To what floor do the instructions take Santa?
	*/
	public void day1NotQuiteLispPart1() {
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
		
		System.out.println(CUR_YEAR + " Day 1 Part 1: " + count);
	}
	
	/**
	 * Given the same instructions, find the position of the first character that causes him to enter the basement (floor -1).
	 * The first character in the instructions has position 1, the second character has position 2, and so on.
	 */
	public void day1NotQuiteLispPart2() {
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
		
		System.out.println(CUR_YEAR + " Day 1 Part 2: " + floorPos);
	}
	
	/**
	 * Run all Day 2 reports.
	 */
	public void day2(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "2");
		
		switch(part) {
			case "1":
				day2NoMathPart1();
				break;
			case "2":
				day2NoMathPart2();
				break;
			default:
				day2NoMathPart1();
				day2NoMathPart2();
				break;
		}
	}
	
	/**
	 * Find the surface area of the box, which is 2*l*w + 2*w*h + 2*h*l.
	 * The elves also need a little extra paper for each present: the area of the smallest side.
	 */
	public void day2NoMathPart1() {
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
		
		System.out.println(CUR_YEAR + " Day 2 Part 1: " + total);
	}
	
	/**
	 * The ribbon required to wrap a present is the shortest distance around its sides, or the smallest perimeter of any one face.
	 * Each present also requires a bow made out of ribbon as well; the feet of ribbon required for the perfect bow is equal to the cubic feet of volume of the present.
	 */
	public void day2NoMathPart2() {
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
		
		System.out.println(CUR_YEAR + " Day 2 Part 2: " + total);
	}
	
	/**
	 * Run all Day 3 reports.
	 */
	public void day3(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "3");
		
		switch(part) {
			case "1":
				day3HousesInAVacuumPart1();
				break;
			case "2":
				day3HousesInAVacuumPart2();
				break;
			default:
				day3HousesInAVacuumPart1();
				day3HousesInAVacuumPart2();
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
	public void day3HousesInAVacuumPart1() {
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
		
		System.out.println(CUR_YEAR + " Day 3 Part 1: " + total);
	}
	
	/**
	 * Santa creates a robot version of himself, Robo-Santa, to deliver presents with him.
	 * Santa and Robo-Santa start at the same location (delivering two presents to the same starting house), then take turns moving based on instructions.
	 * This year, how many houses receive at least one present?
	 */
	public void day3HousesInAVacuumPart2() {
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
		
		System.out.println(CUR_YEAR + " Day 3 Part 2: " + total);
	}
	
	/**
	 * Run all Day 4 reports.
	 */
	public void day4(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "4");
		
		switch(part) {
			case "1":
				day4IdealStockingStufferAdventCoins(5);
				break;
			case "2":
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
			case "1":
				day5InternElvesNaughtyNicePart1();
				break;
			case "2":
				day5InternElvesNaughtyNicePart2();
				break;
			default:
				day5InternElvesNaughtyNicePart1();
				day5InternElvesNaughtyNicePart2();
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
	public void day5InternElvesNaughtyNicePart1() {
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
		
		System.out.println(CUR_YEAR + " Day 5 Part 1: " + count);
	}
	
	/**
	 * Santa needs help figuring out which strings in his text file are naughty or nice.
	 * A nice string is one with all of the following properties:
	 *    It contains a pair of any two letters that appears at least twice in the string without overlapping, like xyxy or aabcdefgaa (aa), but not like aaa.
	 *    It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa.
	 * How many strings are nice?
	 */
	public void day5InternElvesNaughtyNicePart2() {
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
		
		System.out.println(CUR_YEAR + " Day 5 Part 2: " + count);
	}
	
	/**
	 * Run all Day 6 reports.
	 */
	public void day6(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "6");
		
		switch(part) {
			case "1":
				day6FireHazardPart1();
				break;
			case "2":
				day6FireHazardPart2();
				break;
			default:
				day6FireHazardPart1();
				day6FireHazardPart2();
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
	public void day6FireHazardPart1() {
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
		
		System.out.println(CUR_YEAR + " Day 6 Part 1: " + count);
	}
	
	/**
	 * The light grid you bought actually has individual brightness controls; each light can have a brightness of zero or more. The lights all start at zero.
	 * The phrase turn on actually means that you should increase the brightness of those lights by 1.
	 * The phrase turn off actually means that you should decrease the brightness of those lights by 1, to a minimum of zero.
	 * The phrase toggle actually means that you should increase the brightness of those lights by 2.
	 * What is the total brightness of all lights combined?
	 */
	public void day6FireHazardPart2() {
		int GRID_SIZE = 1000;
		int[][] lights = new int[GRID_SIZE][GRID_SIZE];
		
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
		
		System.out.println(CUR_YEAR + " Day 6 Part 2: " + count);
	}
	
	/**
	 * Run all Day 7 reports.
	 */
	public void day7(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "7");
		
		switch(part) {
			case "1":
				day7AssemblyRequiredPart1();
				break;
			case "2":
				day7AssemblyRequiredPart2();
				break;
			default:
				day7AssemblyRequiredPart1();
				day7AssemblyRequiredPart2();
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
	public void day7AssemblyRequiredPart1() {
		Circuit circuit = day7BuildCircuit();
		
		circuit.runCircuit();
		
		System.out.println(CUR_YEAR + " Day 7 Part 1: " + circuit.getWireValue("a"));
	}
	
	/**
	 * Now, take the signal you got on wire a, override wire b to that signal, and reset the other wires (including wire a). 
	 * What new signal is ultimately provided to wire a?
	 */
	public void day7AssemblyRequiredPart2() {
		Circuit circuit = day7BuildCircuit();
		
		Wire bWire = circuit.getWire("b");
		bWire.setValue((new Integer(16076)).toString());
		circuit.addOrUpdateWire(bWire);
		
		circuit.runCircuit();
		
		System.out.println(CUR_YEAR + " Day 7 Part 2: " + circuit.getWireValue("a"));
	}
	
	/**
	 * Run all Day 8 reports.
	 */
	public void day8(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "8");
		
		switch(part) {
			case "1":
				day8MatchsticksPart1();
				break;
			case "2":
				day8MatchsticksPart2();
				break;
			default:
				day8MatchsticksPart1();
				day8MatchsticksPart2();
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
	public void day8MatchsticksPart1() {
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
		
		System.out.println(CUR_YEAR + " Day 8 Part 1: " + (actualChars - inMemoryChars));
	}
	
	public void day8MatchsticksPart2() {
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
		
		System.out.println(CUR_YEAR + " Day 8 Part 2: " + (afterEncodedChars - actualChars));
	}
	
	/**
	 * Run all Day 9 reports.
	 */
	public void day9(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "9");
		
		switch(part) {
			case "1":
				day9SingleNightShortestRoute();
				break;
			case "2":
				break;
			default:
				day9SingleNightShortestRoute();
				break;
		}
	}
	
	/**
	 * Santa has some new locations to visit; his elves have provided him the distances between every pair of locations.
	 * He can start and end at any two (different) locations he wants, but he must visit each location exactly once.
	 * What is the shortest distance he can travel to achieve this?
	 */
	// TODO:
	public void day9SingleNightShortestRoute() {
		Country country = new Country();
		
		for(String line : input) {
			String[] parts = line.split(" ");
			
			City city = country.addCity(parts[0]);
			City destination = country.addCity(parts[2]);
			Integer distance = Integer.parseInt(parts[4]);
			
			city.addConnection(destination.getName(), distance);
			destination.addConnection(city.getName(), distance);
			country.updateCity(city);
			country.updateCity(destination);
		}
		
		List<List<List<City>>> startPoints = new ArrayList<List<List<City>>>();
		for(int i = 0; i < country.getCities().size(); i++) {
			List<List<City>> startPointOptions = new ArrayList<List<City>>();
			City startCity = country.getCities().get(i);
			List<String> connections = new ArrayList<String>(startCity.getConnections().keySet());
			
			for(int j = 0; j < connections.size(); j++) {
				List<City> route = new ArrayList<City>();
				route.add(startCity);
				startPointOptions.add(country.routeBuilder(route, connections.get(j)));
			}
			
			startPoints.add(startPointOptions);
		}
		
		int shortestRoute = 0;
		for(List<List<City>> routes : startPoints) {
			for(List<City> route : routes) {
				int distance = 0;
				for(int i = 0; i < route.size()-1; i++) {
					City thisCity = route.get(i);
					City nextCity = route.get(i+1);
					System.out.print(thisCity.getName() + "->" + nextCity.getName());
					
					distance += thisCity.getConnections().get(nextCity.getName());
				}
				System.out.println();
				if((shortestRoute == 0) || distance < shortestRoute) {
					shortestRoute = distance;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 9 Part 1: " + shortestRoute);
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
