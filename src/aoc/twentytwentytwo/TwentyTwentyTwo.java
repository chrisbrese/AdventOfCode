package aoc.twentytwentytwo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import aoc.Year;
import aoc.utilities.ReadInputFile;

public class TwentyTwentyTwo extends Year {
	
	public TwentyTwentyTwo() {
		CUR_YEAR = 2022;
	}
    
	/**
	 * Run all Day 1 reports.
	 */
	public void day1(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "1");
		
		switch(part) {
			case "1":
				day1CalorieCounting();
				break;
			case "2":
				break;
			default:
				day1CalorieCounting();
				break;
		}		
	}
	
	/**
	 * Santa's reindeer typically eat regular reindeer food, but they need a lot of magical energy to deliver presents on Christmas.
	 * For that, their favorite snack is a special type of star fruit that only grows deep in the jungle.
	 * The Elves have brought you on their annual expedition to the grove where the fruit grows.
	 * 
	 * The jungle must be too overgrown and difficult to navigate in vehicles or access from the air;
	 * the Elves' expedition traditionally goes on foot.
	 * As your boats approach land, the Elves begin taking inventory of their supplies.
	 * One important consideration is food - in particular, the number of Calories each Elf is carrying (your puzzle input).
	 * 
	 * The Elves take turns writing down the number of Calories contained by the various meals, snacks, rations, etc. that they've
	 * brought with them, one item per line.
	 * Each Elf separates their own inventory from the previous Elf's inventory (if any) by a blank line.
	 * 
	 * Find the Elf carrying the most Calories. How many total Calories is that Elf carrying?
	 * 
	 * PART 2:
	 * By the time you calculate the answer to the Elves' question, they've already realized that the Elf carrying the most
	 * Calories of food might eventually run out of snacks.
	 * 
	 * To avoid this unacceptable situation, the Elves would instead like to know the total Calories carried by the top three 
	 * Elves carrying the most Calories. That way, even if one of those Elves runs out of snacks, they still have two backups.
	 * 
	 * Find the top three Elves carrying the most Calories. How many Calories are those Elves carrying in total?
	 */
	private void day1CalorieCounting() {
		int max = 0;
		int combinedMax;
		int count = 0;
		int lineCount = 0;
		List<Integer> elfCalorieCount = new ArrayList<Integer>();
		
		while(lineCount < input.size()) {
			String line = input.get(lineCount);
			
			if(!line.isEmpty()) {
				count += Integer.parseInt(line);
			}
			else if(lineCount == input.size() - 1) {
				if(count > max) {
					max = count;
				}
				
				elfCalorieCount.add(count);
			}
			else {
				if(count > max) {
					max = count;
				}
				elfCalorieCount.add(count);
				count = 0;
			}
			
			lineCount++;
		}
		
		Collections.sort(elfCalorieCount, Collections.reverseOrder());
		combinedMax = elfCalorieCount.get(0) + elfCalorieCount.get(1) + elfCalorieCount.get(2);
		
		System.out.println(CUR_YEAR + " Day 1 Part 1: " + max);
		System.out.println(CUR_YEAR + " Day 1 Part 2: " + combinedMax);
	}
	
	/**
	 * Run all Day 2 reports.
	 */
	public void day2(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "2");
		
		switch(part) {
			case "1":
				day2RockPaperScissorsDirect();
				break;
			case "2":
				day2RockPaperScissorsEndResult();
				break;
			default:
				day2RockPaperScissorsDirect();
				day2RockPaperScissorsEndResult();
				break;
		}
	}
	
	/**
	 * The Elves begin to set up camp on the beach.
	 * To decide whose tent gets to be closest to the snack storage, a giant Rock Paper Scissors tournament is already in progress.
	 * 
	 * Appreciative of your help yesterday, one Elf gives you an encrypted strategy guide (your puzzle input) 
	 * that they say will be sure to help you win.
	 * "The first column is what your opponent is going to play: A for Rock, B for Paper, and C for Scissors. 
	 * The second column--" Suddenly, the Elf is called away to help with someone's tent.
	 * 
	 * The second column, you reason, must be what you should play in response: X for Rock, Y for Paper, and Z for Scissors.
	 * Winning every time would be suspicious, so the responses must have been carefully chosen.
	 * 
	 * The winner of the whole tournament is the player with the highest score.
	 * Your total score is the sum of your scores for each round.
	 * The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors) 
	 * plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
	 * 
	 * What would your total score be if everything goes exactly according to your strategy guide?
	 */
	private void day2RockPaperScissorsDirect() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("X", 1);
		map.put("Y", 2);
		map.put("Z", 3);
		int totalScore = 0;
		
		for(String s : input) {
			String[] battle = s.split(" ");
			String opp = battle[0];
			String me = battle[1];
			
			if(opp.equals("A") && me.equals("X") || 
			   opp.equals("B") && me.equals("Y") ||
			   opp.equals("C") && me.equals("Z")) {
				totalScore += (3 + map.get(me));
			}
			else if (opp.equals("A") && me.equals("Z") ||
					 opp.equals("B") && me.equals("X") ||
					 opp.equals("C") && me.equals("Y")) {
				totalScore += map.get(me);
			}
			else {
				totalScore += (6 + map.get(me));
			}
		}
		
		System.out.println(CUR_YEAR + " Day 2 Part 1: " + totalScore);
	}
	
	/**
	 * The Elf finishes helping with the tent and sneaks back over to you.
	 * "Anyway, the second column says how the round needs to end: X means you need to lose, 
	 * Y means you need to end the round in a draw, and Z means you need to win. Good luck!"
	 * 
	 * The total score is still calculated in the same way, but now you need to figure out what 
	 * shape to choose so the round ends as indicated.
	 * 
	 * Following the Elf's instructions for the second column, what would your total score be 
	 * if everything goes exactly according to your strategy guide?
	 */
	private void day2RockPaperScissorsEndResult() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		int totalScore = 0;
		
		for(String s : input) {
			String[] battle = s.split(" ");
			String opp = battle[0];
			String me = battle[1];
			
			if(me.equals("Y")) {
				totalScore += (3 + map.get(opp));
			}
			else if (me.equals("X")) {
				if(opp.equals("A")) {
					totalScore += map.get("C");
				}
				else if(opp.equals("B")) {
					totalScore += map.get("A");
				}
				else if(opp.equals("C")) {
					totalScore += map.get("B");
				}
			}
			else {
				if(opp.equals("A")) {
					totalScore += (6 + map.get("B"));
				}
				else if(opp.equals("B")) {
					totalScore += (6 + map.get("C"));
				}
				else if(opp.equals("C")) {
					totalScore += (6 + map.get("A"));
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 2 Part 2: " + totalScore);
	}
	
	/**
	 * Run all Day 3 reports.
	 */
	public void day3(String part) {
		input = ReadInputFile.readFile(Integer.toString(CUR_YEAR), "3");
		
		switch(part) {
			case "1":
				day3RucksackReorganization();
				break;
			case "2":
				day3RucksackReorganizationBadges();
				break;
			default:
				day3RucksackReorganization();
				day3RucksackReorganizationBadges();
				break;
		}
	}
	
	/**
	 * One Elf has the important job of loading all of the rucksacks with supplies for the jungle journey.
	 * Unfortunately, that Elf didn't quite follow the packing instructions, and so a few items now need to be rearranged.
	 * 
	 * Each rucksack has two large compartments.
	 * All items of a given type are meant to go into exactly one of the two compartments.
	 * The Elf that did the packing failed to follow this rule for exactly one item type per rucksack.
	 * 
	 * The Elves have made a list of all of the items currently in each rucksack (your puzzle input), 
	 * but they need your help finding the errors.
	 * Every item type is identified by a single lowercase or uppercase letter (that is, a and A refer to different types of items).
	 * 
	 * The list of items for each rucksack is given as characters all on a single line.
	 * A given rucksack always has the same number of items in each of its two compartments, 
	 * so the first half of the characters represent items in the first compartment, 
	 * while the second half of the characters represent items in the second compartment.
	 * 
	 * To help prioritize item rearrangement, every item type can be converted to a priority:
	 *   Lowercase item types a through z have priorities 1 through 26.
	 *   Uppercase item types A through Z have priorities 27 through 52.
	 *   
	 * Find the item type that appears in both compartments of each rucksack.
	 * What is the sum of the priorities of those item types?
	 */
	private void day3RucksackReorganization() {
		int priorityTotal = 0;
		for(String s : input) {
			String first = s.substring(0, s.length()/2);
			String sec = s.substring(s.length()/2);
			String item = "";
			
			for(int i = 0; i < first.length(); i++) {
				if(sec.contains(first.substring(i, i+1))) {
					item = first.substring(i, i+1);
					priorityTotal += alphabetStr.indexOf(item) + 1;
					break;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 3 Part 1: " + priorityTotal);
	}
	
	/**
	 * As you finish identifying the misplaced items, the Elves come to you with another issue.
	 * 
	 * For safety, the Elves are divided into groups of three.
	 * Every Elf carries a badge that identifies their group.
	 * For efficiency, within each group of three Elves, the badge is the only item type carried by all three Elves.
	 * 
	 * The problem is that someone forgot to put this year's updated authenticity sticker on the badges.
	 * All of the badges need to be pulled out of the rucksacks so the new authenticity stickers can be attached.
	 * 
	 * Additionally, nobody wrote down which item type corresponds to each group's badges.
	 * The only way to tell which item type is the right one is by finding the one item type that is common
	 * between all three Elves in each group.
	 * 
	 * Every set of three lines in your list corresponds to a single group, but each group can have a different badge item type.
	 * 
	 * Priorities for these items must still be found to organize the sticker attachment efforts.
	 * 
	 * Find the item type that corresponds to the badges of each three-Elf group.
	 * What is the sum of the priorities of those item types?
	 */
	private void day3RucksackReorganizationBadges() {
		int priorityTotal = 0;
		for(int i = 0; i < input.size(); i+=3) {
			String first = input.get(i);
			String sec = input.get(i+1);
			String third = input.get(i+2);
			String item = "";
			
			for(int j = 0; j < first.length(); j++) {
				if(sec.contains(first.substring(j, j+1)) && third.contains(first.substring(j, j+1))) {
					item = first.substring(j, j+1);
					priorityTotal += alphabetStr.indexOf(item) + 1;
					break;
				}
			}
		}
		
		System.out.println(CUR_YEAR + " Day 3 Part 2: " + priorityTotal);
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
