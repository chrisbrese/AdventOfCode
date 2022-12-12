package aoc.twentytwentytwo.dayeleven;

import java.util.ArrayList;
import java.util.List;

public class Jungle {
	private List<Monkey> monkeys;
	
	public Jungle() {
		monkeys = new ArrayList<Monkey>();
	}
	
	public Monkey addMonkey(Monkey m) {
		monkeys.add(m);
		return m;
	}
	
	public Monkey getMonkey(int monkeyNum) {
		for(Monkey m : monkeys) {
			if(m.getMonkeyNumber() == monkeyNum) {
				return m;
			}
		}
		
		return null;
	}
	
	public List<Monkey> getMonkeys(){
		return monkeys;
	}
}
