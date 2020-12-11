package aoc.twentyfifteen.daynine;

import java.util.HashMap;

public class City {
	private String name;
	private HashMap<String, Integer> connections;
	
	public City(String name) {
		this.name = name;
		
		connections = new HashMap<String, Integer>();
	}
	
	public String getName() {
		return name;
	}
	
	public void addConnection(String name, int distance) {
		connections.put(name, distance);
	}
	
	public HashMap<String, Integer> getConnections(){
		return connections;
	}
}
