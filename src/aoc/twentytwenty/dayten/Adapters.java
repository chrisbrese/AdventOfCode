package aoc.twentytwenty.dayten;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Adapters {
	private List<Integer> adapters;
	private HashMap<Integer, Long> possiblePaths;
	
	public Adapters(List<String> input) {
		adapters = new ArrayList<Integer>(input.size());
		possiblePaths = new HashMap<Integer, Long>(input.size());
		
		// start with the jack in the wall with effective joltage of 0
		adapters.add(0);
		
		for(String line : input) {
			Integer adapter = Integer.parseInt(line);
			
			int whichIndex = 0;
			for(int i = 0; i < adapters.size(); i++) {
				if(i == adapters.size()-1) {
					if(adapter > adapters.get(i)) {
						whichIndex = adapters.size();
					}
					else {
						whichIndex = i;
					}
					break;
				}
				else if(adapter < adapters.get(i)) {
					whichIndex = i;
					break;
				}
			}
			adapters.add(whichIndex, adapter);
		}
	}
	
	public List<Integer> getAdapters(){
		return adapters;
	}
	
	public long getPaths(int index) {
		if(possiblePaths.get(index) != null && possiblePaths.get(index) > 0) {
			return possiblePaths.get(index);
		}
		
		long numPaths = 0;
		
		if(adapters.get(index) <= 3) {
			numPaths++;
		}
		
		for(int off = 1; off <= 3; off++) {
			if((index - off >= 0) && (adapters.get(index - off) >= adapters.get(index) - 3)) {
				numPaths += getPaths(index - off);
			}
		}
		
		possiblePaths.put(index, numPaths);
		
		return numPaths;
	}
}
