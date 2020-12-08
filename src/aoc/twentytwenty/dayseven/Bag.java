package aoc.twentytwenty.dayseven;

import java.util.HashMap;

public class Bag {
	private String fullColor;
	private String color;
	private String colorDetail;
	private HashMap<Bag, Integer> bags;
	
	public Bag() {
		bags = new HashMap<Bag, Integer>();
	}
	
	public void setFullColor(String fullColor) {
		String[] parts = fullColor.split(" ");
		this.fullColor = fullColor;
		color = parts[1];
		colorDetail = parts[0];
	}
	
	public String getFullColor() {
		return fullColor;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getColorDetail() {
		return colorDetail;
	}
	
	public HashMap<Bag, Integer> getBags(){
		return bags;
	}
	
	public void addBag(Bag b, int quantity) {
		bags.put(b, quantity);
	}
	
	public String toString() {
		String bagString = "";
		
		bagString += fullColor + ": ";
		
		for(Bag bag : getBags().keySet()) {
			if(!bagString.substring(bagString.length()-1).equals(" ")) {
				bagString += ", ";
			}
			bagString += getBags().get(bag) + "x[" + bag.toString() + "]";
		}
		
		return bagString;
	}
	
	public boolean hasBag(String fullColor) {
		for(Bag bag : getBags().keySet()) {
			if(bag.fullColor.equals(fullColor)) {
				return true;
			}
			else {
				if(bag.hasBag(fullColor)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public int howManyBagsInside() {
		int count = 0;
		
		for(Bag bag : getBags().keySet()) {
			int numCurBag = bags.get(bag);			
			int numInsideCurBag = bag.howManyBagsInside();
			count += (numCurBag * numInsideCurBag) + numCurBag;			
		}
		
		return count;
	}
}
