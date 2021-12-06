package aoc.circuits.gates;

import java.util.ArrayList;
import java.util.List;

import aoc.circuits.Gate;

/*
     	  |
    	  |
   		  In
    	  |
    	  |
   ----------------
   |			  |
   |	   		  |
   |			  |
   ----------------
	  |	  |	  |
	  |	  |	  |
     Out Out Out
  	  |	  |	  |
 	  |	  |	  |
*/

/**
 * This class is used in 2017 Day 7 as a circuit/gate equivalent type problem
 */
public class MultiOutputGate extends Gate {
	private String inputWireNameOrValue;
	private List<String> outputWireNamesOrValues;
	private int outputValueSum;
	
	public MultiOutputGate() {
		super("");
		outputWireNamesOrValues = new ArrayList<String>();
	}
	
	public void setInputWireNameOrValue(String value) {
		inputWireNameOrValue = value;
	}
	
	public String getInputWireNameOrValue() {
		return inputWireNameOrValue;
	}
	
	public void addOutputWireNameOrValue(String outputNameOrValue) {
		if(!outputWireNamesOrValues.contains(outputNameOrValue)) {
			outputWireNamesOrValues.add(outputNameOrValue);
		}	
	}
	
	/**
	 * Get the index of a specific output in the list of outputs
	 * @param outputWireNameOrValue the name or value to get the index of in the list
	 * @return int value corresponding to the index of the provided value in the output list
	 */
	public int hasOutputWireNameOrValue(String outputWireNameOrValue) {
		if(outputWireNamesOrValues.contains(outputWireNameOrValue)) {
			for(int i = 0; i < outputWireNamesOrValues.size(); i++) {
				if(outputWireNamesOrValues.get(i).equals(outputWireNameOrValue)) {
					return i;
				}
			}
		}
		
		return -1;
	}
	
	public List<String> getOutputWireNamesOrValues() {
		return outputWireNamesOrValues;
	}

	public int getOutputValueSum() {
		return outputValueSum;
	}

	public void setOutputValueSum(int outputValueSum) {
		this.outputValueSum = outputValueSum;
	}
}
