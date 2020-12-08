package aoc.twentytwenty.dayseven;

import java.util.ArrayList;
import java.util.List;

public class Carousel {
	private List<Bag> bags;
	
	public Carousel() {
		bags = new ArrayList<Bag>();
	}
	
	public Bag addBag(String input) {
		String[] bagParts = input.split(" ");
		String fullColor = bagParts[0] + " " + bagParts[1];
		
		Bag bag = getBag(fullColor);
		if(bag == null) {
			bag = new Bag();
			
			bag.setFullColor(fullColor);
		}
		
		String bagList = "";
		for(int i = 4; i < bagParts.length; i++) {
			if(i == bagParts.length-1) {
				bagList += bagParts[i];
			}
			else {
				bagList += bagParts[i] + " ";
			}
		}
		
		bagList = bagList.trim();
		if((bagList.contains(",") || !bagList.startsWith("no")) && bagList.length() > 2) {
			String[] bagSplit;
			if(bagList.contains(",")) {
				bagSplit = bagList.split(",");
			}
			else {
				bagSplit = new String[1];
				bagSplit[0] = bagList;
			}
			
			for(String b : bagSplit) {
				String[] curBag = b.trim().split(" ");
				
				Bag newBag = getBag(curBag[1] + " " + curBag[2]);
				if(newBag == null) {
					newBag = addBag(curBag[1] + " " + curBag[2]);
				}
				
				bag.addBag(newBag, Integer.parseInt(curBag[0]));
			}
		}
		
		int i = bags.indexOf(getBag(fullColor));
		if(i == -1) {
			bags.add(bag);
		}
		else {
			bags.remove(i);
			bags.add(i, bag);
		}
		
		return bag;
	}
	
	public List<Bag> getBags(){
		return bags;
	}
	
	public Bag getBag(String fullColor) {
		for(Bag bag : bags) {
			if(bag.getFullColor().equals(fullColor)) {
				return bag;
			}
		}
		
		return null;
	}
	
	public String toString() {
		String carouselString = "";
		
		for(Bag b : getBags()) {
			carouselString += b.toString() + "\n";
		}
		
		return carouselString;
	}
}
