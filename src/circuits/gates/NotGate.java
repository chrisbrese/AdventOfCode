package circuits.gates;

import circuits.Gate;
import circuits.Wire;

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
	private Wire inputWire;
	
	public NotGate(Wire input, Wire output) {
		super(output);
		
		this.inputWire = input;
	}
	
	public Wire run() {
		if(!hasRun() && canRun()) {
			getOutput().setValue((~inputWire.getValue()));
			successfullyRan();
			return getOutput();
		}
		
		return null;
	}
	
	public boolean canRun() {
		return (inputWire.getValue() != null);
	}
	
	public String toString() {
		return "NOT " + inputWire.getName() + "(" + inputWire.getValue() + ") -> " + getOutput().getName() + "(" + getOutput().getValue() + ")";
	}
}
