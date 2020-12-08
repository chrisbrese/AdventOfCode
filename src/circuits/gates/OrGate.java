package circuits.gates;

import circuits.Gate;
import circuits.Wire;

/*
    |			|
    |			|
   Left        Right
    |			|
    |			|
    \			/
     \		   /
   ----------------
   |			  |
   |	  OR	  |
   |			  |
   ----------------
		  |
		  |
	     Out
	  	  |
	 	  |
*/

public class OrGate extends Gate{
	private Wire leftInputWire;
	private Integer leftInputValue;
	private Wire rightInputWire;
	private Integer rightInputValue;
	
	public OrGate(Wire output) {
		super(output);
	}
	
	public OrGate(Wire leftInput, Wire rightInput, Wire output) {
		super(output);
		
		setLeftInputWire(leftInput);
		setRightInputWire(rightInput);
	}
	
	public void setLeftInputWire(Wire wire) {
		leftInputWire = wire;
	}
	
	public void setLeftInputValue(int value) {
		leftInputValue = value;
	}
	
	public void setRightInputWire(Wire wire) {
		rightInputWire = wire;
	}
	
	public void setRightInputValue(int value) {
		rightInputValue = value;
	}
	
	// TODO: these wires aren't checking latest that circuit knows - get this method into circuit somehow?
	public Wire run() {
		if(!hasRun() && canRun()) {
			Integer left, right;
			if(leftInputWire != null) {
				left = leftInputWire.getValue();
			}
			else {
				left = leftInputValue;
			}
			
			if(rightInputWire != null) {
				right = rightInputWire.getValue();
			}
			else {
				right = rightInputValue;
			}
			
			getOutput().setValue(left | right);
			successfullyRan();
			return getOutput();
		}
		
		return null;
	}
	
	public boolean canRun() {
		return (((leftInputWire != null && leftInputWire.getValue() != null) || leftInputValue != null) && 
				((rightInputWire != null && rightInputWire.getValue() != null) || rightInputValue != null));
	}
	
	public String toString() {
		return (leftInputWire == null ? leftInputValue : leftInputWire.getName() + "(" + leftInputWire.getValue() + ")") + 
				" OR " + (rightInputWire == null ? rightInputValue : rightInputWire.getName() + "(" + rightInputWire.getValue() + ")") 
				+ " -> " + getOutput().getName() + "(" + getOutput().getValue() + ")";
	}
}
