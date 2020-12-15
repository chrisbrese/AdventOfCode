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
	 * Run all Day 1 reports.
	 */
	public void day1(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "1");
		
		switch(part) {
			case "A":
				day1ReportRepairPart1();
				break;
			case "B":
				day1ReportRepairPart2();
				break;
			default:
				day1ReportRepairPart1();
				day1ReportRepairPart2();
				break;
		}		
	}
	
	/**
	 * Find the two entries that sum to 2020 and then multiply those two numbers together.
	 */
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
	
	/**
	 * What is the product of the three entries that sum to 2020?
	 */
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
	 * Run all Day 2 reports.
	 */
	public void day2(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "2");
		
		switch(part) {
			case "A":
				day2PasswordPhilosophyPart1();
				break;
			case "B":
				day2PasswordPhilosophyPart2();
				break;
			default:
				day2PasswordPhilosophyPart1();
				day2PasswordPhilosophyPart2();
				break;
		}
	}
	
	/**
	 * Each line gives the password policy and then the password. The password policy indicates the lowest and highest number of times a given letter must appear for the password to be valid.
	 * How many passwords are valid according to their policies?
	 * Ex. input: 	1-3 a: abcde
	 *				1-3 b: cdefg
	 *				2-9 c: ccccccccc
	 */
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
	
	/**
	 * Each policy actually describes two positions in the password, where 1 means the first character, 2 means the second character, and so on. 
	 * Exactly one of these positions must contain the given letter. Other occurrences of the letter are irrelevant for the purposes of policy enforcement.
	 * How many passwords are valid according to the new interpretation of the policies?
	 * Ex. input: 	1-3 a: abcde
	 *				1-3 b: cdefg
	 *				2-9 c: ccccccccc
	 */
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
	 * Run all Day 3 reports.
	 */
	public void day3(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "3");
		
		switch(part) {
			case "A":
				day3TobogganTrajectoryPart1(3, 1);
				break;
			case "B":
				day3TobogganTrajectoryTreesPart2();
				break;
			default:
				day3TobogganTrajectoryPart1(3, 1);
				day3TobogganTrajectoryTreesPart2();
				break;
		}
	}
	
	/**
	 * You'll need to see which angles will take you near the fewest trees.
	 * Due to the local geology, trees in this area only grow on exact integer coordinates in a grid. 
	 * You make a map of the open squares (.) and trees (#) you can see.
	 * You start on the open square (.) in the top-left corner and need to reach the bottom (below the bottom-most row on your map).
	 * Starting at the top-left corner of your map and following a slope of "right" and "down", how many trees would you encounter?
	 */
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
	
	/**
	 * Determine the number of trees you would encounter if, for each of the following slopes, you start at the top-left corner and traverse the map all the way to the bottom:
	 *    Right 1, down 1.
	 *    Right 3, down 1.
	 *    Right 5, down 1.
	 *    Right 7, down 1.
	 *    Right 1, down 2.
	 * What do you get if you multiply together the number of trees encountered on each of the listed slopes?
	 */
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
	 * Run all Day 4 reports.
	 */
	public void day4(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "4");
		
		switch(part) {
			case "A":
				day4PassportProcessingRequiredFields(0,0,0,0,0,0,false,false,false,false);
				break;
			case "B":
				day4PassportProcessingPart2();
				break;
			default:
				day4PassportProcessingRequiredFields(0,0,0,0,0,0,false,false,false,false);
				day4PassportProcessingPart2();
				break;
		}
	}
	
	/**
	 * The automatic passport scanners are slow because they're having trouble detecting which passports have all required fields.
	 * The expected fields are as follows:
	 *    byr (Birth Year)
	 *    iyr (Issue Year)
	 *    eyr (Expiration Year)
	 *    hgt (Height)
	 *    hcl (Hair Color)
	 *    ecl (Eye Color)
	 *    pid (Passport ID)
	 *    cid (Country ID)
	 * Passport data is validated in batch files.
	 * Each passport is represented as a sequence of key:value pairs separated by spaces or newlines.
	 * Passports are separated by blank lines.
	 * Count the number of valid passports - those that have all required fields.
	 * Treat cid as optional.
	 * In your batch file, how many passports are valid?
	 */
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
	
	/**
	 * Better add some data validation, quick!
	 * You can continue to ignore the cid field, but each other field has strict rules about what values are valid:
	 *    byr (Birth Year) - four digits; at least 1920 and at most 2002.
	 *    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
	 *    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
	 *    hgt (Height) - a number followed by either cm or in:
	 *       If cm, the number must be at least 150 and at most 193.
	 *       If in, the number must be at least 59 and at most 76.
	 *    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
	 *    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
	 *    pid (Passport ID) - a nine-digit number, including leading zeroes.
	 *    cid (Country ID) - ignored, missing or not.
	 * Your job is to count the passports where all required fields are both present and valid according to the above rules.
	 * In your batch file, how many passports are valid?
	 */
	public void day4PassportProcessingPart2() {
		day4PassportProcessingRequiredFields(1920,2002,2010,2020,2020,2030,true,true,true,true);
	}
	
	/**
	 * Run all Day 5 reports.
	 */
	public void day5(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "5");
		
		switch(part) {
			case "A":
				day5BinaryBoardingPart1();
				break;
			case "B":
				day5BinaryBoardingPart2();
				break;
			default:
				day5BinaryBoardingPart1();
				day5BinaryBoardingPart2();
				break;
		}
	}
	
	/**
	 * Perform a recursive binary search on a given string.
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
	
	/**
	 * Instead of zones or groups, this airline uses binary space partitioning to seat people.
	 * A seat might be specified like FBFBBFFRLR, where F means "front", B means "back", L means "left", and R means "right".
	 * The first 7 characters will either be F or B; these specify exactly one of the 128 rows on the plane (numbered 0 through 127).
	 * Each letter tells you which half of a region the given seat is in.
	 * Start with the whole list of rows; the first letter indicates whether the seat is in the front (0 through 63) or the back (64 through 127).
	 * The next letter indicates which half of that region the seat is in, and so on until you're left with exactly one row.
	 * The last three characters will be either L or R; these specify exactly one of the 8 columns of seats on the plane (numbered 0 through 7).
	 * The same process as above proceeds again, this time with only three steps. L means to keep the lower half, while R means to keep the upper half.
	 * Every seat also has a unique seat ID: multiply the row by 8, then add the column.
	 * What is the highest seat ID on a boarding pass?
	 */
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
	
	/**
	 * It's a completely full flight, so your seat should be the only missing boarding pass in your list. 
	 * However, there's a catch: some of the seats at the very front and back of the plane don't exist on this aircraft, so they'll be missing from your list as well.
	 * Your seat wasn't at the very front or back, though; the seats with IDs +1 and -1 from yours will be in your list.
	 * What is the ID of your seat?
	 */
	public void day5BinaryBoardingPart2() {
		int ROWS = 128;
		int COLUMNS = 8;
		
		Integer[][] seats = new Integer[ROWS][COLUMNS];
		
		for(String line : input) {			
			int row = day5BinarySearch(1,128,line,0,6);
			int column = day5BinarySearch(1,8,line,7,9);
			
			int seatId = (row*8) + column;
			seats[row][column] = new Integer(seatId);
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
	 * Run all Day 6 reports.
	 */
	public void day6(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "6");
		
		switch(part) {
			case "A":
				day6CustomCustomsPart1();
				break;
			case "B":
				day6CustomCustomsPart2();
				break;
			default:
				day6CustomCustomsPart1();
				day6CustomCustomsPart2();
				break;
		}
	}
	
	/**
	 * As your flight approaches the regional airport where you'll switch to a much larger plane, customs declaration forms are distributed to the passengers.
	 * The form asks a series of 26 yes-or-no questions marked a through z. All you need to do is identify the questions for which anyone in your group answers "yes".
	 * For each of the people in their group, you write down the questions for which they answer "yes", one per line.
	 * Eventually you've collected answers from every group on the plane (your puzzle input).
	 * Each group's answers are separated by a blank line, and within each group, each person's answers are on a single line.
	 * For each group, count the number of questions to which anyone answered "yes". What is the sum of those counts?
	 */
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
	
	/**
	 * You don't need to identify the questions to which anyone answered "yes"; you need to identify the questions to which everyone answered "yes"!
	 * For each group, count the number of questions to which everyone answered "yes".
	 * What is the sum of those counts?
	 */
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
	 * Run all Day 7 reports.
	 */
	public void day7(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "7");
		
		switch(part) {
			case "A":
				day7HandyHaversacksPart1();
				break;
			case "B":
				day7HandyHaversacksPart2();
				break;
			default:
				day7HandyHaversacksPart1();
				day7HandyHaversacksPart2();
				break;
		}
	}
	
	/**
	 * All flights are currently delayed due to issues in luggage processing.
	 * Due to recent aviation regulations, many rules (your puzzle input) are being enforced about bags and their contents;
	 *    bags must be color-coded and must contain specific quantities of other color-coded bags.
	 * You have a shiny gold bag. If you wanted to carry it in at least one other bag, how many different bag colors would be valid for the outermost bag?
	 * (In other words: how many colors can, eventually, contain at least one shiny gold bag?)
	 * How many bag colors can eventually contain at least one shiny gold bag?
	 */
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
	
	/**
	 * How many individual bags are required inside your single shiny gold bag?
	 */
	public void day7HandyHaversacksPart2() {
		Carousel carousel = new Carousel();
		
		for(String line : input) {
			carousel.addBag(line);
		}
		
		Bag b = carousel.getBag("shiny gold");
		
		System.out.println(CUR_YEAR + " Day 7 Part 2: " + b.howManyBagsInside());
	}
	
	/**
	 * Run all Day 8 reports.
	 */
	public void day8(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "8");
		
		switch(part) {
			case "A":
				day8HandheldHaltingPart1();
				break;
			case "B":
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
	
	/**
	 * Their handheld game console won't turn on! They ask if you can take a look.
	 * You narrow the problem down to a strange infinite loop in the boot code (your puzzle input) of the device.
	 * You should be able to fix it, but first you need to be able to run the code in isolation.
	 * The boot code is represented as a text file with one instruction per line of text. Each instruction consists of an operation (acc, jmp, or nop) and an argument (a signed number like +4 or -20).
	 *    acc increases or decreases a single global value called the accumulator by the value given in the argument. The accumulator starts at 0. After an acc instruction, the instruction immediately below it is executed next.
	 *    jmp jumps to a new instruction relative to itself. The next instruction to execute is found using the argument as an offset from the jmp instruction
	 *    nop stands for No OPeration - it does nothing. The instruction immediately below it is executed next.
	 * Run your copy of the boot code. Immediately before any instruction is executed a second time, what value is in the accumulator?
	 */
	public void day8HandheldHaltingPart1() {
		int accumulator = day8RunBootCode(input).get(0);
		
		System.out.println(CUR_YEAR + " Day 8 Part 1: " + accumulator);
	}
	
	/**
	 * You believe that exactly one instruction is corrupted.
	 * Somewhere in the program, either a jmp is supposed to be a nop, or a nop is supposed to be a jmp. (No acc instructions were harmed in the corruption of this boot code.)
	 * The program is supposed to terminate by attempting to execute an instruction immediately after the last instruction in the file.
	 * By changing exactly one jmp or nop, you can repair the boot code and make it terminate correctly.
	 * Fix the program so that it terminates normally by changing exactly one jmp (to nop) or nop (to jmp).
	 * What is the value of the accumulator after the program terminates?
	 */
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
	 * Run all Day 9 reports.
	 */
	public void day9(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "9");
		
		switch(part) {
			case "A":
				day9EncodingErrorPart1();
				break;
			case "B":
				day9EncodingErrorPart2();
				break;
			default:
				day9EncodingErrorPart1();
				day9EncodingErrorPart2();
				break;
		}
	}
	
	/**
	 * XMAS starts by transmitting a preamble of 25 numbers.
	 * After that, each number you receive should be the sum of any two of the 25 immediately previous numbers.
	 * The two numbers will have different values, and there might be more than one such pair.
	 * The first step of attacking the weakness in the XMAS data is to find the first number in the list (after the preamble)
	 *    which is not the sum of two of the 25 numbers before it.
	 * What is the first number that does not have this property?
	 */
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
	 * Run all Day 10 reports.
	 */
	public void day10(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "10");
		
		switch(part) {
			case "A":
				day10AdapterArrayPart1();
				break;
			case "B":
				day10AdapterArrayPart2();
				break;
			default:
				day10AdapterArrayPart1();
				day10AdapterArrayPart2();
				break;
		}
	}
	
	/**
	 * Your device suddenly turns off! Its battery is dead. You'll need to plug it in.
	 * There's only one problem: the charging outlet near your seat produces the wrong number of jolts. Always prepared, you make a list of all of the joltage adapters in your bag.
	 * Each of your joltage adapters is rated for a specific output joltage (your puzzle input).
	 * Any given adapter can take an input 1, 2, or 3 jolts lower than its rating and still produce its rated output joltage.
	 * In addition, your device has a built-in joltage adapter rated for 3 jolts higher than the highest-rated adapter in your bag.
	 * Treat the charging outlet near your seat as having an effective joltage rating of 0.
	 * If you use every adapter in your bag at once, what is the distribution of joltage differences between the charging outlet, the adapters, and your device?
	 * Find a chain that uses all of your adapters to connect the charging outlet to your device's built-in adapter and 
	 *    count the joltage differences between the charging outlet, the adapters, and your device.
	 * What is the number of 1-jolt differences multiplied by the number of 3-jolt differences?
	 */
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
	
	/**
	 * To completely determine whether you have enough adapters, you'll need to figure out how many different ways they can be arranged.
	 * Every arrangement needs to connect the charging outlet to your device. The previous rules about when adapters can successfully connect still apply.
	 * You glance back down at your bag and try to remember why you brought so many adapters; there must be more than a trillion valid ways to arrange them!
	 * Surely, there must be an efficient way to count the arrangements.
	 * What is the total number of distinct ways you can arrange the adapters to connect the charging outlet to your device?
	 */
	public void day10AdapterArrayPart2() {
		Adapters adapters = new Adapters(input);
		
		int deviceJoltage = adapters.getAdapters().get(adapters.getAdapters().size()-1) + 3;
		adapters.getAdapters().add(deviceJoltage);
		
		long numPaths = adapters.getPaths(adapters.getAdapters().size()-1);
		
		// why does this have to be divided by 2?
		System.out.println(CUR_YEAR + " Day 10 Part 2: " + numPaths/2);
	}
	
	/**
	 * Run all Day 11 reports.
	 */
	public void day11(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "11");
		
		switch(part) {
			case "A":
				day11SeatingSystemSeatEveryone();
				break;
			case "B":
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
	
	/**
	 * The final leg of your journey is on a ferry.
	 * By modeling the process people use to choose (or abandon) their seat in the waiting area, you're pretty sure you can predict the best place to sit.
	 * You make a quick map of the seat layout (your puzzle input).
	 * The seat layout fits neatly on a grid. Each position is either floor (.), an empty seat (L), or an occupied seat (#).
	 * Now, you just need to model the people who will be arriving shortly. Fortunately, people are entirely predictable and always follow a simple set of rules.
	 * All decisions are based on the number of occupied seats adjacent to a given seat (one of the eight positions immediately up, down, left, right, or diagonal from the seat).
	 * The following rules are applied to every seat simultaneously:
	 *    If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
	 *    If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
	 *    Otherwise, the seat's state does not change.
	 *    Floor (.) never changes; seats don't move, and nobody sits on the floor.
	 * At this point, something interesting happens: the chaos stabilizes and further applications of these rules cause no seats to change state!
	 * Simulate your seating area by applying the seating rules repeatedly until no seats change state.
	 * How many seats end up occupied?
	 */
	public void day11SeatingSystemSeatEveryone() {
		int seatCount = day11SeatingSystem(4, false);
		
		System.out.println(CUR_YEAR + " Day 11 Part 1: " + seatCount);
	}
	
	/**
	 * As soon as people start to arrive, you realize your mistake. People don't just care about adjacent seats - they care about the first seat they can see in each of those eight directions!
	 * Now, instead of considering just the eight immediately adjacent seats, consider the first seat in each of those eight directions.
	 * Also, people seem to be more tolerant than you expected: it now takes five or more visible occupied seats for an occupied seat to become empty.
	 * The other rules still apply: empty seats that see no occupied seats become occupied, seats matching no rule don't change, and floor never changes.
	 * Again, at this point, people stop shifting around and the seating area reaches equilibrium.
	 * Given the new visibility method and the rule change for occupied seats becoming empty, once equilibrium is reached, how many seats end up occupied?
	 */
	public void day11SeatingSystemExtendedSeatView() {
		int seatCount = day11SeatingSystem(5, true);
		
		System.out.println(CUR_YEAR + " Day 11 Part 2: " + seatCount);
	}
	
	/**
	 * Run all Day 12 reports.
	 */
	public void day12(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "12");
		
		switch(part) {
			case "A":
				day12RainRiskPart1();
				break;
			case "B":
				break;
			default:
				day12RainRiskPart1();
				break;
		}
	}
	
	/**
	 * The navigation instructions (your puzzle input) consists of a sequence of single-character actions paired with integer input values.
	 *    Action N means to move north by the given value.
	 *    Action S means to move south by the given value.
	 *    Action E means to move east by the given value.
	 *    Action W means to move west by the given value.
	 *    Action L means to turn left the given number of degrees.
	 *    Action R means to turn right the given number of degrees.
	 *    Action F means to move forward by the given value in the direction the ship is currently facing.
	 * The ship starts by facing east. Only the L and R actions change the direction the ship is facing. 
	 *    (That is, if the ship is facing east and the next instruction is N10, the ship would move north 10 units, but would still move east if the following action were F.)
	 * Figure out where the navigation instructions lead.
	 * What is the Manhattan distance (sum of the absolute values of its east/west position and its north/south position) from its starting position?
	 */
	public void day12RainRiskPart1() {
		int blocks = GridUtilities.howManyBlocks(input, 5000, "E", false, 0, true);
		
		System.out.println(CUR_YEAR + " Day 12 Part 1: " + blocks);
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
