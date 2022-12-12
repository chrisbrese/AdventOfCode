package aoc.twentytwentytwo.dayeleven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Monkey {
	private int monkeyNumber;
	private List<Integer> items;
	private HashMap<String, Integer> monkeyMap;
	private int divisibleBy;
	private String operation;
	private int itemsInspected;
	
	public Monkey(int monkeyNumber) {
		this.monkeyNumber = monkeyNumber;
		items = new ArrayList<Integer>();
		monkeyMap = new HashMap<String, Integer>();
		itemsInspected = 0;
	}
	
	public int testItem(Integer item) {
		if(item % divisibleBy == 0) {
			return monkeyMap.get("true");
		}
		else {
			return monkeyMap.get("false");
		}
	}
	
	public Integer operate(Integer item) {
		itemsInspected++;
		String[] parts = operation.split(" ");
		String operand = parts[0];
		int value = 0;

		if(parts[1].equals("old")) {
			operand = "2";
		}
		else {
			value = Integer.parseInt(parts[1]);
		}
		
		Integer newValue = 0;
		
		switch(operand) {
			case "*":
				newValue = item * value;
				break;
			case "+":
				newValue = item + value;
				break;
			case "2":
				newValue = item * item;
				break;
		}

		return newValue;
	}
	
	public int getMonkeyNumber() {
		return monkeyNumber;
	}
	
	public void addMonkey(String testResult, int monkeyNum) {
		monkeyMap.put(testResult, monkeyNum);
	}
	
	public void addItem(Integer item) {
		items.add(item);
	}
	
	public void setDivisibleBy(int divisibleBy) {
		this.divisibleBy = divisibleBy;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public List<Integer> getItems(){
		return items;
	}
	
	public HashMap<String, Integer> getMonkeyMap(){
		return monkeyMap;
	}
	
	public int getItemsInspected() {
		return itemsInspected;
	}
}
