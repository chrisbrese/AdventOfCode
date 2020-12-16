package aoc.twentyseventeen;

import aoc.Year;
import aoc.utilities.ReadInputFile;

public class TwentySeventeen extends Year {
	
	public TwentySeventeen() {
		CUR_YEAR = 2017;
	}
    
	/**
	 * Run all Day 1 reports.
	 */
	public void day1(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "1");
		
		switch(part) {
			case "A":
				day1InverseCaptchaPart1();
				break;
			case "B":
				day1InverseCaptchaPart2();
				break;
			default:
				day1InverseCaptchaPart1();
				day1InverseCaptchaPart2();
				break;
		}		
	}
	
	/**
	 * You're standing in a room with "digitization quarantine" written in LEDs along one wall.
	 * The only door is locked, but it includes a small interface. "Restricted Area - Strictly No Digitized Users Allowed."
	 * It goes on to explain that you may only leave by solving a captcha to prove you're not a human.
	 * The captcha requires you to review a sequence of digits (your puzzle input) and find the sum of all digits that match the next digit in the list.
	 * The list is circular, so the digit after the last digit is the first digit in the list.
	 * What is the solution to your captcha?
	 */
	public void day1InverseCaptchaPart1() {
		String line = input.get(0);
		int sum = 0;
		
		for(int i = 0; i < line.length(); i++) {
			if(i == line.length()-1) {
				if(Integer.parseInt(line.substring(i)) == Integer.parseInt(line.substring(0,1))) {
					sum = sum + Integer.parseInt(line.substring(i));
				}
			}
			else {
				if(Integer.parseInt(line.substring(i,i+1)) == Integer.parseInt(line.substring(i+1,i+2))) {
					sum = sum + Integer.parseInt(line.substring(i,i+1));
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 1 Part 1: " + sum);
	}
	
	/**
	 * Apparently, the door isn't yet satisfied.
	 * Now, instead of considering the next digit, it wants you to consider the digit halfway around the circular list.
	 * That is, if your list contains 10 items, only include a digit in your sum if the digit 10/2 = 5 steps forward matches it.
	 * Fortunately, your list has an even number of elements.
	 * What is the solution to your new captcha?
	 */
	public void day1InverseCaptchaPart2() {
		String line = input.get(0);
		int sum = 0;
		int lineHalf = line.length() / 2;
		
		for(int i = 0; i < line.length(); i++) {
			int tmpI = (i+lineHalf) % line.length();
			if(Integer.parseInt(line.substring(i,i+1)) == Integer.parseInt(line.substring(tmpI,tmpI+1))) {
				sum = sum + Integer.parseInt(line.substring(i,i+1));
			}
		}
		
		System.out.println(CUR_YEAR + " Day 1 Part 2: " + sum);
	}
	
	/**
	 * Run all Day 2 reports.
	 */
	public void day2(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "2");
		
		switch(part) {
			case "A":
				day2CorruptionChecksumPart1();
				break;
			case "B":
				break;
			default:
				day2CorruptionChecksumPart1();
				break;
		}
	}
	
	/**
	 * The spreadsheet consists of rows of apparently-random numbers.
	 * To make sure the recovery process is on the right track, they need you to calculate the spreadsheet's checksum.
	 * For each row, determine the difference between the largest value and the smallest value; the checksum is the sum of all of these differences.
	 * What is the checksum for the spreadsheet in your puzzle input?
	 */
	public void day2CorruptionChecksumPart1() {
		int sum = 0;
		for(String line : input) {
			String[] parts = line.split("\t");
			
			int smallest = 0;
			int largest = 0;
			for(int i = 0; i < parts.length; i++) {
				int num = Integer.parseInt(parts[i]);
				
				if(smallest == 0 || num < smallest) {
					smallest = num;
				}
				if(largest == 0 || num > largest) {
					largest = num;
				}
			}
			
			sum += (largest - smallest);
		}
		
		System.out.println(CUR_YEAR + " Day 2 Part 1: " + sum);
	}
	
	/**
	 * Run all Day 3 reports.
	 */
	public void day3(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "3");
		
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
	 * Run all Day 4 reports.
	 */
	public void day4(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "4");
		
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
