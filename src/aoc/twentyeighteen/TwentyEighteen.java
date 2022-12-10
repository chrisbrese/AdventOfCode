package aoc.twentyeighteen;

import java.util.HashMap;

import aoc.Year;
import aoc.utilities.ReadInputFile;

public class TwentyEighteen extends Year {
	
	public TwentyEighteen() {
		CUR_YEAR = 2018;
	}
    
	/**
	 * Run all Day 1 reports.
	 */
	public void day1(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "1");
		
		switch(part) {
			case "1":
				day1ChronalCalibration(false);
				break;
			case "2":
				day1ChronalCalibration(true);
				break;
			default:
				day1ChronalCalibration(false);
				day1ChronalCalibration(true);
				break;
		}		
	}
	
	/**
	 * After feeling like you've been falling for a few minutes, you look at the device's tiny screen.
	 * "Error: Device must be calibrated before first use. Frequency drift detected. Cannot maintain destination lock."
	 * Below the message, the device shows a sequence of changes in frequency (your puzzle input).
	 * 
	 * Starting with a frequency of zero, what is the resulting frequency after all of the changes in frequency have been applied?
	 * 
	 * Part 2:
	 * 
	 * You notice that the device repeats the same frequency change list over and over. To calibrate the device, 
	 * you need to find the first frequency it reaches twice.
	 * 
	 * Note that your device might need to repeat its list of frequency changes many times before a duplicate 
	 * frequency is found, and that duplicates might be found while in the middle of processing the list.
	 * 
	 * What is the first frequency your device reaches twice?
	 * 
	 * @param part2 - true if running part2
	 */
	private void day1ChronalCalibration(boolean part2) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		
		int start = 0;
		map.put(start, "0");
		boolean found = false;
		
		while(!found) {
			for(String line : input) {
				start += Integer.parseInt(line);
				if(part2 && !found && map.get(start) != null) {
					System.out.println(CUR_YEAR + " Day 1 Part 2: " + start);
					found = true;
				}
				
				map.put(start, line);
			}
			
			if(!part2) {
				found = true;
				System.out.println(CUR_YEAR + " Day 1 Part 1: " + start);
			}
		}
	}
	
	/**
	 * {@link https://adventofcode.com/2018/day/2}
	 * Run all Day 2 reports.
	 */
	public void day2(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "2");
		
		switch(part) {
			case "1":
				day2InventoryManagementSystemChecksum();
				break;
			case "2":
				day2InventoryMangementSystemCommonLetters();
				break;
			default:
				day2InventoryManagementSystemChecksum();
				day2InventoryMangementSystemCommonLetters();
				break;
		}
	}
	
	private void day2InventoryManagementSystemChecksum() {
		int twoCount = 0;
		int threeCount = 0;
		for(String line : input) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			for(int i = 0; i < line.length(); i++) {
				String temp = "";
				if(i == line.length() - 1) {
					temp = line.substring(i);
				}
				else {
					temp = line.substring(i, i+1);
				}

				if(map.get(temp) == null) {
					map.put(temp, 0);
				}

				map.put(temp, map.get(temp) + 1);
			}

			boolean foundTwo = false;
			boolean foundThree = false;
			for (Integer i : map.values()) {
				if(!foundTwo || !foundThree){
					if (!foundTwo && i == 2) {
						foundTwo = true;
						twoCount++;
					}
					else if (!foundThree && i == 3) {
						foundThree = true;
						threeCount++;
					}
				}
			}
		}

		System.out.println(CUR_YEAR + " Day 2 Part 1: " + (twoCount * threeCount));
	}
	
	private void day2InventoryMangementSystemCommonLetters() {
		boolean found = false;
		String str = "";

		// compare each line with all other lines
		for(int i = 0; (!found && i < input.size()); i++) {
			for(int j = i+1; (!found && j < input.size()); j++) {
				// one by one, remove a character from each line to compare with
				for(int x = 0; (!found && x < input.get(i).length()); x++) {
					String xTemp = input.get(i).substring(0, x) + input.get(i).substring(x+1);
					for(int y = 0; (!found && y < input.get(j).length()); y++){
						String yTemp = input.get(j).substring(0, y) + input.get(j).substring(y+1);

						if(xTemp.equals(yTemp)){
							found = true;
							str = xTemp;
							break;
						}
					}
				}
			}	
		}

		System.out.println(CUR_YEAR + " Day 2 Part 2: " + str);
	}
	
	/**
	 * Run all Day 3 reports.
	 */
	public void day3(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "3");
		
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
	 * Run all Day 4 reports.
	 */
	public void day4(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "4");
		
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
	 * Run all Day 5 reports.
	 */
	public void day5(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "5");
		
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
	 * Run all Day 6 reports.
	 */
	public void day6(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "6");
		
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
	 * Run all Day 7 reports.
	 */
	public void day7(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "7");
		
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
	 * Run all Day 8 reports.
	 */
	public void day8(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "8");
		
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
	 * Run all Day 9 reports.
	 */
	public void day9(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "9");
		
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
