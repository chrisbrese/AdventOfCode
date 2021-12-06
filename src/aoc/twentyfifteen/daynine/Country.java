package aoc.twentyfifteen.daynine;

import java.util.ArrayList;
import java.util.List;

public class Country {
	private List<City> cities;
	
	public Country() {
		cities = new ArrayList<City>();
	}
	
	public City updateCity(City city) {
		int index = 0;
		for(int i = 0; i < cities.size(); i++) {
			if(cities.get(i).getName().equals(city.getName())) {
				index = i;
				cities.remove(i);
				cities.add(i, city);
			}
		}
		
		return cities.get(index);
	}
	
	public City addCity(String name) {
		City c;
		if((c = getCity(name)) == null) {
			c = new City(name);
			cities.add(c);
		}
		
		return c;
	}
	
	public City getCity(String name) {
		for(City c : cities) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		
		return null;
	}
	
	public List<City> getCities(){
		return cities;
	}
	
	public String toString() {
		String s = "";
		for(City c : getCities()) {
			s += "Name: " + c.getName() + "\n";
			
			for(String d : c.getConnections().keySet()) {
				s += "---" + d + " (" + c.getConnections().get(d) + ")\n";
			}
		}
		
		return s;
	}
}
