package aoc.twentyfifteen;

import java.util.ArrayList;
import java.util.List;

//import org.apache.commons.collections4.CollectionUtils;

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
	 * 
	 * {@link https://adventofcode.com/2015/day/1}
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
	 * {@link https://adventofcode.com/2015/day/2}
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
	 * {@link https://adventofcode.com/2015/day/3}
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
	 * {@link https://adventofcode.com/2015/day/4}
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
	
	public void day4IdealStockingStufferAdventCoins(int numZeros) {
		String line = input.get(0);
		
		List<String> hashList = MD5.findMD5HashesOfIncreasingIntegersWithNumZeros(line, numZeros, 1, false, false);
		
		System.out.println(CUR_YEAR + " Day 4 " + numZeros + " zeros: " + hashList.get(0));
	}
	
	/**
	 * {@link https://adventofcode.com/2015/day/5}
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
	 * {@link https://adventofcode.com/2015/day/6}
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
	 * {@link https://adventofcode.com/2015/day/7}
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
	
	public void day7AssemblyRequiredPart1() {
		Circuit circuit = day7BuildCircuit();
		
		circuit.runCircuit();
		
		System.out.println(CUR_YEAR + " Day 7 Part 1: " + circuit.getWireValue("a"));
	}
	
	public void day7AssemblyRequiredPart2() {
		Circuit circuit = day7BuildCircuit();
		
		Wire bWire = circuit.getWire("b");
		bWire.setValue(Integer.toString(16076));
		circuit.addOrUpdateWire(bWire);
		
		circuit.runCircuit();
		
		System.out.println(CUR_YEAR + " Day 7 Part 2: " + circuit.getWireValue("a"));
	}
	
	/**
	 * {@link https://adventofcode.com/2015/day/8}
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
	 * {@link https://adventofcode.com/2015/day/9}
	 * Run all Day 9 reports.
	 */
	public void day9(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "9");
		
		switch(part) {
			case "1":
				day9SingleNightRoute(true);
				break;
			case "2":
				day9SingleNightRoute(false);
				break;
			default:
				day9SingleNightRoute(true);
				day9SingleNightRoute(false);
				break;
		}
	}
	
	/**
	 * @param getShortest true if return shortest distance, false if return longest distance
	 */
	public void day9SingleNightRoute(boolean getShortest) {
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
		
		List<List<City>> combinations = new ArrayList<List<City>>();
		
//		combinations.addAll(CollectionUtils.permutations(country.getCities()));
		
		int shortestRoute = 0;
		int longestRoute = 0;
		for(List<City> routeSet : combinations) {
			List<City> route = new ArrayList<>(routeSet);
			int distance = 0;
			for(int i = 0; i < route.size()-1; i++) {
				City thisCity = route.get(i);
				City nextCity = route.get(i+1);
				
				distance += thisCity.getConnections().get(nextCity.getName());
			}
			
			if(shortestRoute == 0 || distance < shortestRoute) {
				shortestRoute = distance;
			}
			if(longestRoute == 0 || distance > longestRoute) {
				longestRoute = distance;
			}
		}
		
		if(getShortest) {
			System.out.println(CUR_YEAR + " Day 9 Part 1: " + shortestRoute);
		}
		else {
			System.out.println(CUR_YEAR + " Day 9 Part 2: " + longestRoute);
		}
	}
	
	/**
	 * {@link https://adventofcode.com/2015/day/10}
	 * Run all Day 10 reports.
	 */
	public void day10(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "10");
		
		switch(part) {
			case "1":
				day10LookAndSay(40);
				break;
			case "2":
				day10LookAndSay(50);
				break;
			default:
				day10LookAndSay(40);
				day10LookAndSay(50);
				break;
		}
	}
	
	/**
	 * @param numTimes the number of times to apply the look and say process
	 */
	// This takes hours!!
	public void day10LookAndSay(int numTimes) {
		String result = input.get(0);
		
		for(int num = 0; num < numTimes; num++) {
			String curResult = "";
			for(int i = 0; i < result.length(); i++) {
				String s;
				int count = 0;
				if(i == result.length() - 1) {
					s = result.substring(i);
				}
				else {
					s = result.substring(i, i+1);
				}
				count++;
				for(int j = i+1; j < result.length(); j++) {
					String t;
					if(j == result.length() - 1) {
						t = result.substring(j);
					}
					else {
						t = result.substring(j, j+1);
					}
					
					if(s.equals(t)) {
						count++;
						
						if(j == result.length() - 1) {
							i = j;
							break;
						}
					}
					else {
						i = j - 1;
						break;
					}
				}
				
				curResult += Integer.toString(count) + s;
			}

			result = curResult;
		}
		
		System.out.println(CUR_YEAR + " Day 10 Result after (" + numTimes + ") times: " + result.length());
	}
	
	/**
	 * {@link https://adventofcode.com/2015/day/11}
	 * Run all Day 11 reports.
	 */
	public void day11(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "11");
		
		switch(part) {
			case "1":
				day11CorporatePolicy(input.get(0), 1);
				break;
			case "2":
				day11CorporatePolicy(input.get(0), 2);
				break;
			default:
				day11CorporatePolicy(input.get(0), 1);
				day11CorporatePolicy(input.get(0), 2);
				break;
		}
	}
	
	public boolean isGoodPassword(String pw) {
		boolean contains3InARow = false;
		int doubleCharCount = 0;
		
		if(pw.contains("i") || pw.contains("l") || pw.contains("o")) {
			return false;
		}
		
		for(int i = 0; i <= pw.length()-3; i++) {
			int i1 = alphabetStr.indexOf(pw.substring(i,i+1));
			int i2 = alphabetStr.indexOf(pw.substring(i+1,i+2));
			int i3 = alphabetStr.indexOf(pw.substring(i+2,i+3));
			
			if(i3 - i2 == 1 && i2 - i1 == 1) {
				contains3InARow = true;
				break;
			}
		}
		
		for(int i = 0; i <= pw.length() - 2; i++) {
			if(pw.substring(i, i+1).equals(pw.substring(i+1, i+2))) {
				doubleCharCount++;
				i++; // since they can't overlap, skip an index
			}
		}
		
		return contains3InARow && (doubleCharCount >= 2);
	}
	
	/**
	 * @param pw the password to check against
	 * @param numTimes the number of different valid passwords, in order, to get
	 */
	public void day11CorporatePolicy(String pw, int numTimes) {
		int count = 0;
		String lastPw = pw;
		while(count < numTimes) {
			StringBuilder tempPw = new StringBuilder(lastPw);
			boolean success = false;
			
			int curPwIndex = tempPw.length()-1;
			int lastPwIndex = curPwIndex;
			
			while(lastPwIndex >= 0) {
				String curAlphaChar = tempPw.substring(lastPw.length()-1);
				int curAlphaIndex = alphabetStr.indexOf(curAlphaChar);
				while(curAlphaIndex < alphabetArr.length) {
					if(curAlphaIndex == alphabetArr.length-1) { // if its at 'z'
						for(int x = curPwIndex; x >= lastPwIndex; x--) {
							if(alphabetStr.indexOf(tempPw.substring(x, x+1))+1 == alphabetArr.length) { // check the previous index to see if its also at 'z'
								tempPw.replace(x, x+1, "a");
								
								if(x == lastPwIndex) {
									lastPwIndex--; // check the next index to the left
								}
							}
							else {
								if(alphabetArr[alphabetStr.indexOf(tempPw.substring(x, x+1))+1].equals("i") || 
										alphabetArr[alphabetStr.indexOf(tempPw.substring(x, x+1))+1].equals("l") || 
										alphabetArr[alphabetStr.indexOf(tempPw.substring(x, x+1))+1].equals("o")) {
									tempPw.replace(x, x+1, alphabetArr[alphabetStr.indexOf(tempPw.substring(x, x+1))+2]);
								}
								else {
									tempPw.replace(x, x+1, alphabetArr[alphabetStr.indexOf(tempPw.substring(x, x+1))+1]);
								}
								lastPwIndex++; // time to start the counter over now that the previous index has been resolved
							}	
						}
						
					}
					else { // still working in the current index
						if(alphabetArr[curAlphaIndex+1].equals("i") || alphabetArr[curAlphaIndex+1].equals("l") || alphabetArr[curAlphaIndex+1].equals("o")) {
							tempPw.replace(curPwIndex, curPwIndex+1, alphabetArr[curAlphaIndex+2]);
							curAlphaIndex++; // skip the next letter
						}
						else {
							tempPw.replace(curPwIndex, curPwIndex+1, alphabetArr[curAlphaIndex+1]);
						}
					}
					
					if(success = isGoodPassword(tempPw.toString())){
						break;
					}
					
					curAlphaIndex++;
				}
				if(success) {
					break;
				}
			}
			
			if(success) {
				count ++;
				lastPw = tempPw.toString();
				System.out.println(CUR_YEAR + " Day 11 valid pw after (" + count + ") times: " + lastPw);
			}
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
