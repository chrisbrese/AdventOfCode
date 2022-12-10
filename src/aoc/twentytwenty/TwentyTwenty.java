package aoc.twentytwenty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aoc.Year;
import aoc.twentytwenty.dayeleven.Ferry;
import aoc.twentytwenty.dayfour.Passport;
import aoc.twentytwenty.dayseven.Bag;
import aoc.twentytwenty.dayseven.Carousel;
import aoc.twentytwenty.dayten.Adapters;
import aoc.utilities.ReadInputFile;
import aoc.utilities.grid.GridUtilities;

public class TwentyTwenty extends Year {
	
	public TwentyTwenty() {
		CUR_YEAR = 2020;
	}
    
	/**
	 * {@link https://adventofcode.com/2020/day/1}
	 * Run all Day 1 reports.
	 */
	public void day1(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "1");
		
		switch(part) {
			case "1":
				day1ReportRepairPart1();
				break;
			case "2":
				day1ReportRepairPart2();
				break;
			default:
				day1ReportRepairPart1();
				day1ReportRepairPart2();
				break;
		}		
	}
	
	public void day1ReportRepairPart1(){
		int val1,val2 = 0;
        
        for(int i = 0; i < input.size(); i++){
            val1 = Integer.parseInt(input.get(i));
            for(int j = (i+1); j < input.size(); j++){
                val2 = Integer.parseInt(input.get(j));
                
                if((val1 + val2) == CUR_YEAR){
					System.out.println(CUR_YEAR + " Day 1 Part 1: val1(" + val1 + "), val2(" + val2 + ") = " + (val1 * val2));
				}
            }
        }
	}
	
	public void day1ReportRepairPart2(){
		int val1, val2, val3 = 0;
        
        for(int i = 0; i < input.size(); i++){
            val1 = Integer.parseInt(input.get(i));
            for(int j = (i+1); j < input.size(); j++){
                val2 = Integer.parseInt(input.get(j));
				for(int k = (j+1); k < input.size(); k++){
					val3 = Integer.parseInt(input.get(k));
                
					if((val1 + val2 + val3) == CUR_YEAR){
						System.out.println(CUR_YEAR + " Day 1 Part 2: val1(" + val1 + "), val2(" + val2 + "), val3(" + val3 + ") = " + (val1 * val2 * val3));
					}
				}
            }
        }
	}
	
	/**
	 * {@link https://adventofcode.com/2020/day/2}
	 * Run all Day 2 reports.
	 */
	public void day2(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "2");
		
		switch(part) {
			case "1":
				day2PasswordPhilosophyPart1();
				break;
			case "2":
				day2PasswordPhilosophyPart2();
				break;
			default:
				day2PasswordPhilosophyPart1();
				day2PasswordPhilosophyPart2();
				break;
		}
	}
	
	public void day2PasswordPhilosophyPart1() {
		int validCount = 0;
		
		for(int i = 0; i < input.size(); i++) {
			String cur = input.get(i);
			
			String[] parts = cur.split(" ");
			String[] limitsStr = parts[0].split("-");
			char[] charCheck = new char[2];
			parts[1].getChars(0, 1, charCheck, 0);
			String pw = parts[2];
			
			int count = 0;
			for(int j = 0; j < pw.length(); j++) {
				char c = pw.charAt(j);
								
				if(c == charCheck[0]) {
					count++;
				}
			}
			
			if((count >= Integer.parseInt(limitsStr[0])) &&
				count <= Integer.parseInt(limitsStr[1])) {
				validCount++;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 2 Part 1: " + validCount);
	}
	
	public void day2PasswordPhilosophyPart2() {
		int validCount = 0;
		
		for(int i = 0; i < input.size(); i++) {
			String cur = input.get(i);
			
			String[] parts = cur.split(" ");
			String[] posStr = parts[0].split("-");
			char[] charCheck = new char[2];
			parts[1].getChars(0, 1, charCheck, 0);
			String pw = parts[2];
			
			char char1 = pw.charAt(Integer.parseInt(posStr[0]) - 1);
			char char2 = pw.charAt(Integer.parseInt(posStr[1]) - 1);
			
			if(char1 != char2) {
				if(char1 == charCheck[0] ||
				   char2 == charCheck[0]) {
					validCount++;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 2 Part 2: " + validCount);
	}
	
	/**
	 * {@link https://adventofcode.com/2020/day/3}
	 * Run all Day 3 reports.
	 */
	public void day3(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "3");
		
		switch(part) {
			case "1":
				day3TobogganTrajectoryPart1(3, 1);
				break;
			case "2":
				day3TobogganTrajectoryTreesPart2();
				break;
			default:
				day3TobogganTrajectoryPart1(3, 1);
				day3TobogganTrajectoryTreesPart2();
				break;
		}
	}
	
	public int day3TobogganTrajectoryPart1(int right, int down) {
		int width = input.get(0).length();
		int height = input.size();
		
		String[][] treeMap = new String[width][height];
		
		// fill in the map
		int count = 0;
		for(String line : input) {
			for(int i = 0; i < line.length(); i++) {
				String charac = line.substring(i, i+1);
				treeMap[i][count] = charac;
			}
			count++;
		}
		
		int curX = 0;
		int curY = 0;
		int treeCount = 0;
		
		while(curY < height) {
			if(treeMap[curX][curY].equals("#")) {
				treeCount++;
			}
			
			if(curX + right >= width) {
				curX = (curX + right) - width;
			}
			else {
				curX += right;
			}
			
			curY += down;
		}
		
		System.out.println(CUR_YEAR + " Day 3 Part 1: " + treeCount);
		return treeCount;
	}
	
	public void day3TobogganTrajectoryTreesPart2() {
		double count1 = day3TobogganTrajectoryPart1(1, 1) * 1.0;
		double count2 = day3TobogganTrajectoryPart1(3, 1) * 1.0;
		double count3 = day3TobogganTrajectoryPart1(5, 1) * 1.0;
		double count4 = day3TobogganTrajectoryPart1(7, 1) * 1.0;
		double count5 = day3TobogganTrajectoryPart1(1, 2) * 1.0;
		
		double product = count1 * count2 * count3 * count4 * count5;
		
		System.out.println(CUR_YEAR + " Day 3 Part 2: " + product);
	}
	
	/**
	 * {@link https://adventofcode.com/2020/day/4}
	 * Run all Day 4 reports.
	 */
	public void day4(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "4");
		
		switch(part) {
			case "1":
				day4PassportProcessingRequiredFields(0,0,0,0,0,0,false,false,false,false);
				break;
			case "2":
				day4PassportProcessingPart2();
				break;
			default:
				day4PassportProcessingRequiredFields(0,0,0,0,0,0,false,false,false,false);
				day4PassportProcessingPart2();
				break;
		}
	}
	
	public void day4PassportProcessingRequiredFields(int birthYearMin, int birthYearMax, int issueYearMin, int issueYearMax,
			int expYearMin, int expYearMax, boolean useHeightLimits, boolean validateHairColor,
			boolean validateEyeColor, boolean validatePassportId) {
		int lineCount = 0;
		int validCount = 0;
		
		while(lineCount < input.size()) {
			Passport passport = new Passport();
			
			for(int i = lineCount; i < input.size(); i++) {
				String line = input.get(i);
				
				String parts[] = line.split(" ");
				
				for(String p : parts) {
					String[] fields = p.split(":");
					
					switch(fields[0]) {
						case "byr":
							passport.setBirthYear(Integer.parseInt(fields[1]));
							break;
						case "iyr":
							passport.setIssueYear(Integer.parseInt(fields[1]));
							break;
						case "eyr":
							passport.setExpYear(Integer.parseInt(fields[1]));
							break;
						case "hgt":
							passport.setHeight(fields[1]);
							break;
						case "hcl":
							passport.setHairColor(fields[1]);
							break;
						case "ecl":
							passport.setEyeColor(fields[1]);
							break;
						case "pid":
							passport.setPassportId(fields[1]);
							break;
						case "cid":
							passport.setCountryId(fields[1]);
							break;
					}
				}
				
				if(line.trim().isEmpty() || (i == input.size()-1)) {
					if(passport.isValid(birthYearMin,birthYearMax,issueYearMin,issueYearMax,expYearMin,expYearMax,useHeightLimits,validateHairColor,validateEyeColor,validatePassportId)) {
						validCount++;
					}
					
					lineCount = i+1;
					break;
				}
				else {
					
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 4: " + validCount);
	}
	
	public void day4PassportProcessingPart2() {
		day4PassportProcessingRequiredFields(1920,2002,2010,2020,2020,2030,true,true,true,true);
	}
	
	/**
	 * {@link https://adventofcode.com/2020/day/5}
	 * Run all Day 5 reports.
	 */
	public void day5(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "5");
		
		switch(part) {
			case "1":
				day5BinaryBoardingPart1();
				break;
			case "2":
				day5BinaryBoardingPart2();
				break;
			default:
				day5BinaryBoardingPart1();
				day5BinaryBoardingPart2();
				break;
		}
	}
	
	/**
	 * @param min lowest number allowed in search
	 * @param max highest number allowed in search
	 * @param searchString the string to use for the search
	 * @param curPos the current position in the string to search for
	 * @param maxPos the last position of the string to use
	 * @return an int representing the number between min/max that represents the search string
	 */
	public int day5BinarySearch(int min, int max, String searchString, int curPos, int maxPos) {
		String curString = searchString.substring(curPos,curPos+1);
		if(curPos < maxPos) {
			if(curString.equals("F") || curString.equals("L")) {
				return day5BinarySearch(min,min+((max-(min-1))/2)-1,searchString,curPos+1,maxPos);
			}
			else if(curString.equals("B") || curString.equals("R")) {
				return day5BinarySearch(((max-(max-(min-1))/2)+1),max,searchString,curPos+1,maxPos);
			}
		}
		else if(curPos == maxPos) {
			if(curString.equals("F") || curString.equals("L")) {
				return min-1;
			}
			else if(curString.equals("B") || curString.equals("R")) {
				return max-1;
			}
		}
		
		return 0;
	}
	
	public void day5BinaryBoardingPart1() {
		int highestSeatId = 0;
		
		for(String line : input) {			
			int row = day5BinarySearch(1,128,line,0,6);
			int column = day5BinarySearch(1,8,line,7,9);
			
			int seatId = (row*8) + column;
			
			if(seatId > highestSeatId) {
				highestSeatId = seatId;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 5 Part 1: " + highestSeatId);
	}
	
	public void day5BinaryBoardingPart2() {
		int ROWS = 128;
		int COLUMNS = 8;
		
		Integer[][] seats = new Integer[ROWS][COLUMNS];
		
		for(String line : input) {			
			int row = day5BinarySearch(1,128,line,0,6);
			int column = day5BinarySearch(1,8,line,7,9);
			
			int seatId = (row*8) + column;
			seats[row][column] = seatId;
		}
		
		List<Integer> seatIds = new ArrayList<Integer>();
		List<Integer> nullSeatIds = new ArrayList<Integer>();
		for(int y = 1; y < ROWS-1; y++) {
			for(int x = 0; x < COLUMNS; x++) {
				if(seats[y][x] == null) {
					int myRow = y;
					int myColumn = x;
					int mySeatId = (myRow*8) + myColumn;
					nullSeatIds.add(mySeatId);
				}
				else {
					seatIds.add(seats[y][x]);
				}
			}
		}
		
		for(Integer s : nullSeatIds) {
			if(seatIds.contains(s-1) && seatIds.contains(s+1)) {
				System.out.println(CUR_YEAR + " Day 5 Part 2: " + s);
			}
		}
	}
	
	/**
	 * {@link https://adventofcode.com/2020/day/6}
	 * Run all Day 6 reports.
	 */
	public void day6(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "6");
		
		switch(part) {
			case "1":
				day6CustomCustomsPart1();
				break;
			case "2":
				day6CustomCustomsPart2();
				break;
			default:
				day6CustomCustomsPart1();
				day6CustomCustomsPart2();
				break;
		}
	}
	
	public void day6CustomCustomsPart1() {
		int sum = 0;
		List<String> yes = new ArrayList<String>();
		int count = 0;
		for(String line : input) {
			count++;
			for(int i = 0; i < line.length(); i++) {
				String cur = line.substring(i,i+1);
				
				if(!yes.contains(cur)) {
					yes.add(cur);
				}
			}
			
			if(line.trim().isEmpty() || input.size() == count) {
				sum += yes.size();
				for(int i = 0; i < yes.size(); i++) {
				}
				yes.clear();
			}
		}
		
		System.out.println(CUR_YEAR + " Day 6 Part 1: " + sum);
	}
	
	public void day6CustomCustomsPart2() {
		int sum = 0;
		List<List<String>> yeses = new ArrayList<List<String>>();
		int count = 0;
		for(String line : input) {
			count++;
			if(!line.trim().isEmpty()) {
				List<String> curYes = new ArrayList<String>();
								
				for(int i = 0; i < line.length(); i++) {
					String cur = line.substring(i,i+1);				
					curYes.add(cur);
				}
				yeses.add(curYes);
			}
			
			if(line.trim().isEmpty() || input.size() == count) {
				List<String> finalCount = new ArrayList<String>();
				int c = 0;
				for(List<String> yes : yeses) {
					if(c == 0) {
						finalCount.addAll(yes);
					}
					else {
						finalCount.retainAll(yes);
					}
					c++;
				}
				sum += finalCount.size();
				yeses.clear();
			}
		}
		
		System.out.println(CUR_YEAR + " Day 6 Part 2: " + sum);
	}
	
	/**
	 * {@link https://adventofcode.com/2020/day/7}
	 * Run all Day 7 reports.
	 */
	public void day7(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "7");
		
		switch(part) {
			case "1":
				day7HandyHaversacksPart1();
				break;
			case "2":
				day7HandyHaversacksPart2();
				break;
			default:
				day7HandyHaversacksPart1();
				day7HandyHaversacksPart2();
				break;
		}
	}
	
	public void day7HandyHaversacksPart1() {
		Carousel carousel = new Carousel();
		int numBags = 0;
		
		for(String line : input) {
			carousel.addBag(line);
		}
		
		for(Bag b : carousel.getBags()) {
			if(b.hasBag("shiny gold")) {
				numBags++;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 7 Part 1: " + numBags);
	}
	
	public void day7HandyHaversacksPart2() {
		Carousel carousel = new Carousel();
		
		for(String line : input) {
			carousel.addBag(line);
		}
		
		Bag b = carousel.getBag("shiny gold");
		
		System.out.println(CUR_YEAR + " Day 7 Part 2: " + b.howManyBagsInside());
	}
	
	/**
	 * {@link https://adventofcode.com/2020/day/8}
	 * Run all Day 8 reports.
	 */
	public void day8(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "8");
		
		switch(part) {
			case "1":
				day8HandheldHaltingPart1();
				break;
			case "2":
				day8HandheldHaltingPart2();
				break;
			default:
				day8HandheldHaltingPart1();
				day8HandheldHaltingPart2();
				break;
		}
	}
	
	public List<Integer> day8RunBootCode(List<String> input) {
		int accumulator = 0;
		List<Integer> returnList = new ArrayList<Integer>(2);
		
		HashMap<Integer, Boolean> instructionRun = new HashMap<Integer, Boolean>(input.size());
		
		for(int i = 0; i < input.size(); i++) {
			// if the map contains this instruction, then we've already ran it, and we are in an infinite loop
			if(instructionRun.get(i) == null || !instructionRun.get(i)) {
				String line = input.get(i);
				String[] parts = line.split(" ");
				
				int num = Integer.parseInt(parts[1]);
				
				switch(parts[0]) {
					case "jmp":
						i += num - 1;
						break;
					case "acc":
						accumulator += num;
						break;
					case "nop":
						break;
				}
				
				instructionRun.put(i, true);
			}
			// in the infinite loop - cut it off and announce
			else {
				returnList.add(accumulator);
				returnList.add(i);
				break;
			}
		}
		
		// this means we reached the end!
		if(returnList.size() == 0) {
			returnList.add(accumulator);
			returnList.add(input.size());
		}
		
		return returnList;
	}
	
	public void day8HandheldHaltingPart1() {
		int accumulator = day8RunBootCode(input).get(0);
		
		System.out.println(CUR_YEAR + " Day 8 Part 1: " + accumulator);
	}
	
	public void day8HandheldHaltingPart2() {
		int accumulator = 0;
		
		List<String> newInput;
		List<Integer> modifiedIndexAttempts = new ArrayList<Integer>(input.size());
		
		int count = 0;
		for(String line : input) {
			newInput = new ArrayList<String>();
			newInput.addAll(input);
			String[] parts = line.split(" ");
			
			switch(parts[0]) {
				case "jmp":
					if(!modifiedIndexAttempts.contains(count)) {
						newInput.remove(count);
						newInput.add(count, "nop " + parts[1]);
						modifiedIndexAttempts.add(count);
					}
					break;
				case "nop":
					if(!modifiedIndexAttempts.contains(count)) {
						newInput.remove(count);
						newInput.add(count, "jmp " + parts[1]);
						modifiedIndexAttempts.add(count);
					}
					break;
			}
			List<Integer> resultList = day8RunBootCode(newInput);
			if(resultList.size() > 0 && resultList.get(1).equals(input.size())) {
				accumulator = resultList.get(0);
				break;
			}
			count++;
		}
		
		System.out.println(CUR_YEAR + " Day 8 Part 2: " + accumulator);
	}
	
	/**
	 * {@link https://adventofcode.com/2020/day/9}
	 * Run all Day 9 reports.
	 */
	public void day9(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "9");
		
		switch(part) {
			case "1":
				day9EncodingErrorPart1();
				break;
			case "2":
				day9EncodingErrorPart2();
				break;
			default:
				day9EncodingErrorPart1();
				day9EncodingErrorPart2();
				break;
		}
	}
	
	public void day9EncodingErrorPart1() {
		List<Long> longs = new ArrayList<Long>(input.size());
		for(String line : input) {
			long l = Long.parseLong(line);
			longs.add(l);
		}
		
		long badLong = 0;
		for(int i = 25; i < longs.size(); i++) {
			boolean found = false;
			long cur = longs.get(i);
			for(int j = (i-25); j < i; j++) {
				long cur1 = longs.get(j);
				for(int h = (i-25); (h != j) && h < i; h++) {
					long cur2 = longs.get(h);
					
					if(cur1 + cur2 == cur) {
						found = true;
						break;
					}
				}
				if(found) {
					break;
				}
			}
			
			if(!found) {
				badLong = longs.get(i);
				break;
			}
		}
		
		System.out.println(CUR_YEAR + " Day 9 Part 1: " + badLong);
	}
	
	public void day9EncodingErrorPart2() {
		List<Long> longs = new ArrayList<Long>(input.size());
		for(String line : input) {
			long l = Long.parseLong(line);
			longs.add(l);
		}
		
		long badLong = 31161678;
		
		List<Long> sumList = null;
		long tmpSum = 0;
		boolean done = false;
		
		int curIndex = 0;
		while(!done || curIndex == longs.size()) {
			sumList = new ArrayList<Long>();
			tmpSum = 0;
			for(int i = curIndex; i < longs.size(); i++) {
				tmpSum += longs.get(i);
				sumList.add(longs.get(i));
				if(tmpSum == badLong) {
					done = true;
					break;
				}
				else if(tmpSum > badLong) {
					break;
				}
			}
			curIndex++;
		}
		
		Long smallest = null;
		Long biggest = null;
		if(done) {
			for(Long l : sumList) {
				if((smallest == null) || l < smallest) {
					smallest = l;
				}
				if((biggest == null) || l > biggest) {
					biggest = l;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 9 Part 2: " + (smallest + biggest));
	}
	
	/**
	 * {@link https://adventofcode.com/2020/day/10}
	 * Run all Day 10 reports.
	 */
	public void day10(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "10");
		
		switch(part) {
			case "1":
				day10AdapterArrayPart1();
				break;
			case "2":
				day10AdapterArrayPart2();
				break;
			default:
				day10AdapterArrayPart1();
				day10AdapterArrayPart2();
				break;
		}
	}
	
	public void day10AdapterArrayPart1() {
		Adapters adapters = new Adapters(input);
		
		int deviceJoltage = adapters.getAdapters().get(adapters.getAdapters().size()-1) + 3;
		adapters.getAdapters().add(deviceJoltage);
		
		int num1JoltDiff = 0;
		int num3JoltDiff = 0;
		for(int i = 0; i < adapters.getAdapters().size(); i++) {
			if(i+1 != adapters.getAdapters().size()) {
				int diff = adapters.getAdapters().get(i+1) - adapters.getAdapters().get(i);
				if(diff == 1) {
					num1JoltDiff ++;
				}
				else if(diff == 3) {
					num3JoltDiff ++;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 10 Part 1: " + (num1JoltDiff * num3JoltDiff));
	}
	
	public void day10AdapterArrayPart2() {
		Adapters adapters = new Adapters(input);
		
		int deviceJoltage = adapters.getAdapters().get(adapters.getAdapters().size()-1) + 3;
		adapters.getAdapters().add(deviceJoltage);
		
		long numPaths = adapters.getPaths(adapters.getAdapters().size()-1);
		
		// why does this have to be divided by 2?
		System.out.println(CUR_YEAR + " Day 10 Part 2: " + numPaths/2);
	}
	
	/**
	 * {@link https://adventofcode.com/2020/day/11}
	 * Run all Day 11 reports.
	 */
	public void day11(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "11");
		
		switch(part) {
			case "1":
				day11SeatingSystemSeatEveryone();
				break;
			case "2":
				day11SeatingSystemExtendedSeatView();
				break;
			default:
				day11SeatingSystemSeatEveryone();
				day11SeatingSystemExtendedSeatView();
				break;
		}
	}
	
	public int day11SeatingSystem(int numAdjacentSeats, boolean extendedView) {
		Ferry ferry = new Ferry(input);
		
		int lastHistory = 0;		
		int newHistory =  -1;
		
		while(newHistory != lastHistory) {
			lastHistory = newHistory;
			newHistory = ferry.seatPeople(numAdjacentSeats, extendedView);
		}
		
		String[][] seating = ferry.getHistory(newHistory);
		int seatCount = 0;
		for(int curRow = 0; curRow < seating.length; curRow++) {
			String[] row = seating[curRow];
			for(int curCol = 0; curCol < row.length; curCol++) {
				if(seating[curRow][curCol].equals("#")) {
					seatCount++;
				}
			}
		}
		
		return seatCount;
	}
	
	public void day11SeatingSystemSeatEveryone() {
		int seatCount = day11SeatingSystem(4, false);
		
		System.out.println(CUR_YEAR + " Day 11 Part 1: " + seatCount);
	}
	
	public void day11SeatingSystemExtendedSeatView() {
		int seatCount = day11SeatingSystem(5, true);
		
		System.out.println(CUR_YEAR + " Day 11 Part 2: " + seatCount);
	}
	
	/**
	 * {@link https://adventofcode.com/2020/day/12}
	 * Run all Day 12 reports.
	 */
	public void day12(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "12");
		
		switch(part) {
			case "1":
				day12RainRiskPart1();
				break;
			case "2":
				day12RainRiskPart2();
				break;
			default:
				day12RainRiskPart1();
				day12RainRiskPart2();
				break;
		}
	}
	
	public void day12RainRiskPart1() {
		int blocks = GridUtilities.howManyBlocks(input, 5000, "E", false, 0, true, false);
		
		System.out.println(CUR_YEAR + " Day 12 Part 1: " + blocks);
	}
	
	public void day12RainRiskPart2() {
		int blocks = GridUtilities.howManyBlocks(input, 10000, "E", false, 0, false, true);
		
		System.out.println(CUR_YEAR + " Day 12 Part 2: " + blocks);
	}
	
	/**
	 * {@link https://adventofcode.com/2020/day/13}
	 * Run all Day 13 reports.
	 */
	public void day13(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "13");
		
		switch(part) {
			case "1":
				day13ShuttleSearchPart1();
				break;
			case "2":
				// TODO:
				break;
			default:
				day13ShuttleSearchPart1();
				break;
		}
	}
	
	public void day13ShuttleSearchPart1() {
		long myTimestamp = 0;
		String busListStr;
		String[] busListWithXs;
		
		List<Long> possibleBusTimes = new ArrayList<Long>();
		
		myTimestamp = Long.parseLong(input.get(0));
		busListStr = input.get(1);
		
		busListWithXs = busListStr.split(",");
		int[] busList = new int[busListWithXs.length];
		
		for(int i = 0; i < busListWithXs.length; i++) {
			if(!busListWithXs[i].equals("x")) {
				int busTime = Integer.parseInt(busListWithXs[i]);
				long busTimeLong = busTime;
				
				while(busTimeLong < myTimestamp) {
					busTimeLong += busTime;
				}
				busList[i] = busTime;
				possibleBusTimes.add(busTimeLong);
			}
		}
		
		Long soonest = 0L;
		int soonestIndex = 0;
		
		for(int i = 0; i < possibleBusTimes.size(); i++) {
			if(i == 0) {
				soonest = possibleBusTimes.get(0);
			}
			else {
				if(possibleBusTimes.get(i) < soonest) {
					soonest = possibleBusTimes.get(i);
					soonestIndex = i;
				}
			}
		}
		
		Long waitTime = soonest - myTimestamp;
		
		System.out.println(CUR_YEAR + " Day 13 Part 1: " + (waitTime * busList[soonestIndex]));
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
