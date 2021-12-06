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
	 * For 2017 Day 7 Part 2
	 * @param gates
	 */
	public int day7RecursiveCircus(Gate gate) {
		if(gate !=  null) {
			MultiOutputGate mog = null;
			if(gate instanceof MultiOutputGate) {
				mog = (MultiOutputGate) gate;
				for(String wireName : mog.getOutputWireNamesOrValues()) {
					Wire w = getWire(wireName);
					System.out.println(getWire(((MultiOutputGate) gate).getInputWireNameOrValue()));
					System.out.println(w);
					List<Integer> curWeights = new ArrayList<Integer>(mog.getOutputWireNamesOrValues().size());
					curWeights.add(Integer.parseInt(w.getValue()));
					
					for(int i = 0; i < curWeights.size(); i++) {
						for(int j = 0; j < curWeights.size(); j++) {
							if(i != j) {
								if(curWeights.get(i) != curWeights.get(j)) {
									if(j != curWeights.size()-1) {
										if(curWeights.get(i) != curWeights.get(j+1)) {
											return i + (j-i);
										}
									}
									else if(curWeights.get(i) != curWeights.get(0)) {
										return i + (j-i);
									}
									else {
										return j + (i-j);
									}
								}
							}
						}
					}
				}
			}
		}
		return 0;
	}
	
	/**
	 * For 2017 Day 7
	 */
	public Gate findGateWithOutput(String outputName) {
		for(Gate g : knownGates) {
			if(g instanceof MultiOutputGate) {
				MultiOutputGate mog = (MultiOutputGate) g;
				if(mog.getOutputWireNamesOrValues().contains(outputName)) {
					return mog;
				}
			}
		}
		
		// this means the wire is not in any outputs, therefore is the first one
		return null;
	}
	
	/**
	 * For 2017 Day 7 Part 2
	 */
	public Gate findGateWithInput(String inputName) {
		for(Gate g : knownGates) {
			if(g instanceof MultiOutputGate) {
				MultiOutputGate mog = (MultiOutputGate) g;
				if(mog.getInputWireNameOrValue().equalsIgnoreCase(inputName)) {
					return mog;
				}
			}
		}
		
		// this means the wire is not an input, therefore is one of the last gates
		return null;
	}
	
	/**
	 * For 2015 Day 7
	 */
	public void runCircuit(boolean checkValueSums) {
		while(!haveAllGatesRun()) {
			for(Gate gate : knownGates) {
				if(!gate.hasRun()) {
					if(!checkValueSums) {
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
					else {
						if(gate instanceof MultiOutputGate) {
							MultiOutputGate mog = (MultiOutputGate) gate;
							
							for(String wireName : mog.getOutputWireNamesOrValues()) {
								mog.setgetWire(wireName);
							}
							
							mog.successfullyRan();
						}
					}
				}
			}
		}
	}
}
