package aoc.circuits;

import java.util.ArrayList;
import java.util.List;

import aoc.circuits.gates.AndGate;
import aoc.circuits.gates.LShiftGate;
import aoc.circuits.gates.MultiOutputGate;
import aoc.circuits.gates.NotGate;
import aoc.circuits.gates.OrGate;
import aoc.circuits.gates.RShiftGate;

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
	
	public Integer getWireValue(String name) {
		Wire w;
		if((w = getWire(name)) != null) {
			String valueString;
			if((valueString = w.getValue()) != null) {
				if(valueString.matches("^[a-z]+$")) {
					return getWireValue(valueString);
				}
				else {
					return Integer.parseInt(valueString);
				}
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
	
	public boolean haveAllGatesRun() {
		for(Gate g : knownGates) {
			if(!g.hasRun()) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * For 2017 Day 7 Part 1
	 */
	public Wire findFirstWireOfCircuit() {
		List<Wire> potentialWiresWithNoInputs = new ArrayList<Wire>();
		for(Gate gate : knownGates) {
			if(gate instanceof MultiOutputGate) {
				MultiOutputGate mog = (MultiOutputGate) gate;
				potentialWiresWithNoInputs.add(getWire(mog.getInputWireNameOrValue()));
			}
		}
		
		for(Wire w : potentialWiresWithNoInputs) {
			boolean inOutputs = false;
			for(Gate gate : knownGates) {
				if(gate instanceof MultiOutputGate) {
					MultiOutputGate mog = (MultiOutputGate) gate;
					if(mog.hasOutputWireNameOrValue(w.getName()) >= 0) {
						inOutputs = true;
						break;
					}
				}
			}
			if(!inOutputs) {
				return w;
			}
		}
		return null;
	}
	
	/**
	 * For 2017 Day 7 Part 2
	 */
	public void findUnbalancedTowerInCircuit() {
		Wire first = findFirstWireOfCircuit();
		MultiOutputGate firstGate = null;
		
		for(Gate gate : knownGates) {
			if(gate instanceof MultiOutputGate) {
				MultiOutputGate mog = (MultiOutputGate) gate;
				
				if(mog.getInputWireNameOrValue().equals(first.getName())) {
					firstGate = mog;
					break;
				}
			}
		}
		
		if(firstGate != null) {
			int sum = 0;
			for(String output : firstGate.getOutputWireNamesOrValues()) {
				sum += getWireValue(output);
			}
		}
	}
	
	/**
	 * For 2015 Day 7
	 */
	public void runCircuit() {
		while(!haveAllGatesRun()) {
			for(Gate gate : knownGates) {
				if(!gate.hasRun()) {
					Integer out = null;
					if(gate instanceof AndGate) {
						AndGate g = (AndGate) gate;
						
						Integer left = null, right = null;
						if(g.getLeftInputWireNameOrValue() != null) {
							if(g.getLeftInputWireNameOrValue().matches("^[a-z]+$")) {
								Wire w;
								if((w = getWire(g.getLeftInputWireNameOrValue())) != null) {
									Integer wireValue;
									if((wireValue = getWireValue(w.getName())) != null){
										left = wireValue;
									}
								}
							}
							else if(g.getLeftInputWireNameOrValue().matches("^[0-9]+$")) {
								left = Integer.parseInt(g.getLeftInputWireNameOrValue());
							}
						}
						if(g.getRightInputWireNameOrValue() != null) {
							if(g.getRightInputWireNameOrValue().matches("^[a-z]+$")) {
								Wire w;
								if((w = getWire(g.getRightInputWireNameOrValue())) != null) {
									Integer wireValue;
									if((wireValue = getWireValue(w.getName())) != null){
										right = wireValue;
									}
								}
							}
							else if(g.getRightInputWireNameOrValue().matches("^[0-9]+$")) {
								right = Integer.parseInt(g.getRightInputWireNameOrValue());
							}
						}

						if(left != null && right != null) {
							if((out = g.run(left, right)) != null) {
								Wire output = getWire(g.getOutput());
								output.setValue(out.toString());
								addOrUpdateWire(output);
								g.successfullyRan();
							}
						}
					}
					else if(gate instanceof OrGate) {
						OrGate g = (OrGate) gate;
						
						Integer left = null, right = null;
						if(g.getLeftInputWireNameOrValue() != null) {
							if(g.getLeftInputWireNameOrValue().matches("^[a-z]+$")) {
								Wire w;
								if((w = getWire(g.getLeftInputWireNameOrValue())) != null) {
									Integer wireValue;
									if((wireValue = getWireValue(w.getName())) != null){
										left = wireValue;
									}
								}
							}
							else if(g.getLeftInputWireNameOrValue().matches("^[0-9]+$")) {
								left = Integer.parseInt(g.getLeftInputWireNameOrValue());
							}
						}
						if(g.getRightInputWireNameOrValue() != null) {
							if(g.getRightInputWireNameOrValue().matches("^[a-z]+$")) {
								Wire w;
								if((w = getWire(g.getRightInputWireNameOrValue())) != null) {
									Integer wireValue;
									if((wireValue = getWireValue(w.getName())) != null){
										right = wireValue;
									}
								}
							}
							else if(g.getRightInputWireNameOrValue().matches("^[0-9]+$")) {
								right = Integer.parseInt(g.getRightInputWireNameOrValue());
							}
						}

						if(left != null && right != null) {
							if((out = g.run(left, right)) != null) {
								Wire output = getWire(g.getOutput());
								output.setValue(out.toString());
								addOrUpdateWire(output);
								g.successfullyRan();
							}
						}
					}
					else if(gate instanceof NotGate) {
						NotGate g = (NotGate) gate;
						
						Integer input = null;
						if(g.getInputWireNameOrValue() != null) {
							if(g.getInputWireNameOrValue().matches("^[a-z]+$")) {
								Wire w;
								if((w = getWire(g.getInputWireNameOrValue())) != null) {
									Integer wireValue;
									if((wireValue = getWireValue(w.getName())) != null){
										input = wireValue;
									}
								}
							}
							else if(g.getInputWireNameOrValue().matches("^[0-9]+$")) {
								input = Integer.parseInt(g.getInputWireNameOrValue());
							}
						}

						if(input != null) {
							if((out = g.run(input)) != null) {
								Wire output = getWire(g.getOutput());
								output.setValue(out.toString());
								addOrUpdateWire(output);
								g.successfullyRan();
							}
						}
					}
					else if(gate instanceof LShiftGate) {
						LShiftGate g = (LShiftGate) gate;
						
						Integer left = null, right = null;
						if(g.getLeftInputWireNameOrValue() != null) {
							if(g.getLeftInputWireNameOrValue().matches("^[a-z]+$")) {
								Wire w;
								if((w = getWire(g.getLeftInputWireNameOrValue())) != null) {
									Integer wireValue;
									if((wireValue = getWireValue(w.getName())) != null){
										left = wireValue;
									}
								}
							}
							else if(g.getLeftInputWireNameOrValue().matches("^[0-9]+$")) {
								left = Integer.parseInt(g.getLeftInputWireNameOrValue());
							}
						}
						if(g.getRightInputWireNameOrValue() != null) {
							if(g.getRightInputWireNameOrValue().matches("^[a-z]+$")) {
								Wire w;
								if((w = getWire(g.getRightInputWireNameOrValue())) != null) {
									Integer wireValue;
									if((wireValue = getWireValue(w.getName())) != null){
										right = wireValue;
									}
								}
							}
							else if(g.getRightInputWireNameOrValue().matches("^[0-9]+$")) {
								right = Integer.parseInt(g.getRightInputWireNameOrValue());
							}
						}

						if(left != null && right != null) {
							if((out = g.run(left, right)) != null) {
								Wire output = getWire(g.getOutput());
								output.setValue(out.toString());
								addOrUpdateWire(output);
								g.successfullyRan();
							}
						}
					}
					else if(gate instanceof RShiftGate) {
						RShiftGate g = (RShiftGate) gate;
						
						Integer left = null, right = null;
						if(g.getLeftInputWireNameOrValue() != null) {
							if(g.getLeftInputWireNameOrValue().matches("^[a-z]+$")) {
								Wire w;
								if((w = getWire(g.getLeftInputWireNameOrValue())) != null) {
									Integer wireValue;
									if((wireValue = getWireValue(w.getName())) != null){
										left = wireValue;
									}
								}
							}
							else if(g.getLeftInputWireNameOrValue().matches("^[0-9]+$")) {
								left = Integer.parseInt(g.getLeftInputWireNameOrValue());
							}
						}
						if(g.getRightInputWireNameOrValue() != null) {
							if(g.getRightInputWireNameOrValue().matches("^[a-z]+$")) {
								Wire w;
								if((w = getWire(g.getRightInputWireNameOrValue())) != null) {
									Integer wireValue;
									if((wireValue = getWireValue(w.getName())) != null){
										right = wireValue;
									}
								}
							}
							else if(g.getRightInputWireNameOrValue().matches("^[0-9]+$")) {
								right = Integer.parseInt(g.getRightInputWireNameOrValue());
							}
						}

						if(left != null && right != null) {
							if((out = g.run(left, right)) != null) {
								Wire output = getWire(g.getOutput());
								output.setValue(out.toString());
								addOrUpdateWire(output);
								g.successfullyRan();
							}
						}
					}
				}
			}
		}
	}
}
