package aoc.circuits.gates;

import aoc.circuits.Gate;

/*
     	  |
    	  |
   		 In
    	  |
    	  |
   ----------------
   |			  |
   |	 NOT	  |
   |			  |
   ----------------
		  |
		  |
	     Out
	  	  |
	 	  |
*/

public class NotGate extends Gate{
	private String inputWireNameOrValue;
	
	public NotGate(String inputWireNameOrValue, String outputNameOrValue) {
		super(outputNameOrValue);
		
		setInputWireNameOrValue(inputWireNameOrValue);
	}
	
	public void setInputWireNameOrValue(String value) {
		inputWireNameOrValue = value;
	}
	
	public String getInputWireNameOrValue() {
		return inputWireNameOrValue;
	}
	
	public Integer run(int input) {
		return ~input;
	}
}
