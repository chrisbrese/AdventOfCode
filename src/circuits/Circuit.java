package circuits;

import java.util.ArrayList;
import java.util.List;

import circuits.gates.AndGate;
import circuits.gates.LShiftGate;
import circuits.gates.NotGate;
import circuits.gates.OrGate;
import circuits.gates.RShiftGate;

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
	
	public List<Wire> getWires(){
		return knownWires;
	}
	
	public void addGate(Gate g) {
		knownGates.add(g);
	}
	
	public List<Gate> getGates() {
		return knownGates;
	}
	
	public void runCircuit() {
		int wireCount = knownWires.size();
		int num = 0;
		while(num < wireCount) {
			for(Gate gate : knownGates) {
				Wire out = null;
				if(gate instanceof AndGate) {
					AndGate g = (AndGate) gate;
					if((out = g.run()) != null) {
						addOrUpdateWire(out);
						num++;
					}
				}
				else if(gate instanceof OrGate) {
					OrGate g = (OrGate) gate;
					if((out = g.run()) != null) {
						addOrUpdateWire(out);
						num++;
					}
				}
				else if(gate instanceof NotGate) {
					NotGate g = (NotGate) gate;
					if((out = g.run()) != null) {
						addOrUpdateWire(out);
						num++;
					}
				}
				else if(gate instanceof LShiftGate) {
					LShiftGate g = (LShiftGate) gate;
					if((out = g.run()) != null) {
						addOrUpdateWire(out);
						num++;
					}
				}
				else if(gate instanceof RShiftGate) {
					RShiftGate g = (RShiftGate) gate;
					if((out = g.run()) != null) {
						addOrUpdateWire(out);
						num++;
					}
				}
			}
		}
	}
}
