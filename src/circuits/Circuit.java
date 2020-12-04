package circuits;

import java.util.ArrayList;
import java.util.List;

public class Circuit {
	private List<Wire> knownWires = new ArrayList<Wire>();
	private List<Gate> knownGates = new ArrayList<Gate>();
	
	public void addOrUpdateWire(Wire w) {
		int index;
		if((index = hasWire(w.getName())) > -1) {
			knownWires.remove(index);
			knownWires.add(index, w);
		}
		else {
			knownWires.add(w);
		}
	}
	
	public int hasWire(String name) {
		int index = 0;
		for(Wire w : knownWires) {
			if(w.getName().equalsIgnoreCase(name)) {
				return index;
			}
			
			index++;
		}
		
		return -1;
	}
	
	public Wire getWire(String name) {
		for(Wire w : knownWires) {
			if(w.getName().equalsIgnoreCase(name)) {
				return w;
			}
		}
		
		return null;
	}
	
	public void addGate(Gate g) {
		knownGates.add(g);
	}
	
	public List<Gate> getGates() {
		return knownGates;
	}
	
	public void runCircuit() {
		for(Gate g : knownGates) {
			
		}
	}
}
