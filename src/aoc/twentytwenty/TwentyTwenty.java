package aoc.twentytwenty;

import aoc.Year;
import aoc.utilities.Passport;
import aoc.utilities.ReadInputFile;

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
				day1ReportRepair2Nums();
				break;
			case "B":
				day1ReportRepair3Nums();
				break;
			default:
				day1ReportRepair2Nums();
				day1ReportRepair3Nums();
				break;
		}		
	}
	
	/**
	 * Find the two entries that sum to 2020 and then multiply those two numbers together.
	 */
	public void day1ReportRepair2Nums(){
		int val1,val2 = 0;
        
        for(int i = 0; i < input.size(); i++){
            val1 = Integer.parseInt(input.get(i));
            for(int j = (i+1); j < input.size(); j++){
                val2 = Integer.parseInt(input.get(j));
                
                if((val1 + val2) == CUR_YEAR){
					System.out.println(CUR_YEAR + " Day 1 Part A: val1(" + val1 + "), val2(" + val2 + ") = " + (val1 * val2));
				}
            }
        }
	}
	
	/**
	 * What is the product of the three entries that sum to 2020?
	 */
	public void day1ReportRepair3Nums(){
		int val1, val2, val3 = 0;
        
        for(int i = 0; i < input.size(); i++){
            val1 = Integer.parseInt(input.get(i));
            for(int j = (i+1); j < input.size(); j++){
                val2 = Integer.parseInt(input.get(j));
				for(int k = (j+1); k < input.size(); k++){
					val3 = Integer.parseInt(input.get(k));
                
					if((val1 + val2 + val3) == CUR_YEAR){
						System.out.println(CUR_YEAR + " Day 1 Part B: val1(" + val1 + "), val2(" + val2 + "), val3(" + val3 + ") = " + (val1 * val2 * val3));
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
				day2PasswordPhilosophyMinMax();
				break;
			case "B":
				day2PasswordPhilosophyExactSpot();
				break;
			default:
				day2PasswordPhilosophyMinMax();
				day2PasswordPhilosophyExactSpot();
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
	public void day2PasswordPhilosophyMinMax() {
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
		
		System.out.println(CUR_YEAR + " Day 2 Part A: " + validCount);
	}
	
	/**
	 * Each policy actually describes two positions in the password, where 1 means the first character, 2 means the second character, and so on. 
	 * Exactly one of these positions must contain the given letter. Other occurrences of the letter are irrelevant for the purposes of policy enforcement.
	 * How many passwords are valid according to the new interpretation of the policies?
	 * Ex. input: 	1-3 a: abcde
	 *				1-3 b: cdefg
	 *				2-9 c: ccccccccc
	 */
	public void day2PasswordPhilosophyExactSpot() {
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
		
		System.out.println(CUR_YEAR + " Day 2 Part B: " + validCount);
	}
	
	/**
	 * Run all Day 3 reports.
	 */
	public void day3(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "3");
		
		switch(part) {
			case "A":
				day3TobogganTrajectoryTreesInSlope(3, 1);
				break;
			case "B":
				day3TobogganTrajectoryTreesMultipleSlopes();
				break;
			default:
				day3TobogganTrajectoryTreesInSlope(3, 1);
				day3TobogganTrajectoryTreesMultipleSlopes();
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
	public int day3TobogganTrajectoryTreesInSlope(int right, int down) {
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
		
		System.out.println(CUR_YEAR + " Day 3 Part A: " + treeCount);
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
	public void day3TobogganTrajectoryTreesMultipleSlopes() {
		double count1 = day3TobogganTrajectoryTreesInSlope(1, 1) * 1.0;
		double count2 = day3TobogganTrajectoryTreesInSlope(3, 1) * 1.0;
		double count3 = day3TobogganTrajectoryTreesInSlope(5, 1) * 1.0;
		double count4 = day3TobogganTrajectoryTreesInSlope(7, 1) * 1.0;
		double count5 = day3TobogganTrajectoryTreesInSlope(1, 2) * 1.0;
		
		double product = count1 * count2 * count3 * count4 * count5;
		
		System.out.println(CUR_YEAR + " Day 3 Part B: " + product);
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
				day4PassportProcessingValidation();
				break;
			default:
				day4PassportProcessingRequiredFields(0,0,0,0,0,0,false,false,false,false);
				day4PassportProcessingValidation();
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
		
		System.out.println(CUR_YEAR + " Day 4 Part a: " + validCount);
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
	public void day4PassportProcessingValidation() {
		day4PassportProcessingRequiredFields(1920,2002,2010,2020,2020,2030,true,true,true,true);
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
