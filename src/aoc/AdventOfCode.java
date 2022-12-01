package aoc;

import aoc.twentyeighteen.TwentyEighteen;
import aoc.twentyfifteen.TwentyFifteen;
import aoc.twentynineteen.TwentyNineteen;
import aoc.twentyseventeen.TwentySeventeen;
import aoc.twentysixteen.TwentySixteen;
import aoc.twentytwenty.TwentyTwenty;
import aoc.twentytwentyone.TwentyTwentyOne;
import aoc.twentytwentytwo.TwentyTwentyTwo;

public class AdventOfCode {
	
	public static void run(String day, String part, Year year) {
		switch(day) {
			case "1":
				year.day1(part);
				break;
			case "2":
				year.day2(part);
				break;
			case "3":
				year.day3(part);
				break;
			case "4":
				year.day4(part);
				break;
			case "5":
				year.day5(part);
				break;
			case "6":
				year.day6(part);
				break;
			case "7":
				year.day7(part);
				break;
			case "8":
				year.day8(part);
				break;
			case "9":
				year.day9(part);
				break;
			case "10":
				year.day10(part);
				break;
			case "11":
				year.day11(part);
				break;
			case "12":
				year.day12(part);
				break;
			case "13":
				year.day13(part);
				break;
			case "14":
				year.day14(part);
				break;
			case "15":
				year.day15(part);
				break;
			case "16":
				year.day16(part);
				break;
			case "17":
				year.day17(part);
				break;
			case "18":
				year.day18(part);
				break;
			case "19":
				year.day19(part);
				break;
			case "20":
				year.day20(part);
				break;
			case "21":
				year.day21(part);
				break;
			case "22":
				year.day22(part);
				break;
			case "23":
				year.day23(part);
				break;
			case "24":
				year.day24(part);
				break;
			case "25":
				year.day25(part);
				break;
			default:
				year.day1(part);
				year.day2(part);
				year.day3(part);
				year.day4(part);
				year.day5(part);
				year.day6(part);
				year.day7(part);
				year.day8(part);
				year.day9(part);
				year.day10(part);
				year.day11(part);
				year.day12(part);
				year.day13(part);
				year.day14(part);
				year.day15(part);
				year.day16(part);
				year.day17(part);
				year.day18(part);
				year.day19(part);
				year.day20(part);
				year.day21(part);
				year.day22(part);
				year.day23(part);
				year.day24(part);
				year.day25(part);
				break;
		}
	}
	
    /**
	  * args[0] should be the current year e.g. 2020 (if left empty, run all years with the given day at args[1] and part at args[2])
	  * args[1] should be the day's test to run (if 0, run all!)
	  * args[2] should be the "part" (1/2) of the day to run (if left empty, or marked "Z", run all parts of the day)
	  */
    public static void main(String args[]) {
    	String year = "0";
    	String day = "0";
    	String part = "Z";
    	
    	if(args.length > 0 && args[0] != null && !args[0].isEmpty()) {
    		year = args[0];
    	}
    	if(args.length > 1 && args[1] != null && !args[1].isEmpty()) {
    		day = args[1];
    	}
    	if(args.length > 2 && args[2] != null && !args[2].isEmpty()) {
    		part = args[2];
    	}
    	
    	System.out.println("User inputs: Year - " + year + ", Day - " + day + ", Part - " + part);
    	
    	switch(year) {
	    	case "2015":
	    		run(day, part, new TwentyFifteen());
	    		break;
	    	case "2016":
	    		run(day, part, new TwentySixteen());
	    		break;
	    	case "2017":
	    		run(day, part, new TwentySeventeen());
	    		break;
	    	case "2018":
	    		run(day, part, new TwentyEighteen());
	    		break;
	    	case "2019":
	    		run(day, part, new TwentyNineteen());
	    		break;
	    	case "2020":
	    		run(day, part, new TwentyTwenty());
	    		break;
	    	case "2021":
	    		run(day, part, new TwentyTwentyOne());
	    		break;
	    	case "2022":
	    		run(day, part, new TwentyTwentyTwo());
	    		break;
    		default: // run all
    			run(day, part, new TwentyFifteen());
    			run(day, part, new TwentySixteen());
    			run(day, part, new TwentySeventeen());
    			run(day, part, new TwentyEighteen());
    			run(day, part, new TwentyNineteen());
    			run(day, part, new TwentyTwenty());
    			run(day, part, new TwentyTwentyOne());
    			run(day, part, new TwentyTwentyTwo());
    			break;
    	}
    }
}