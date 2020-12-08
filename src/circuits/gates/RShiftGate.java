package circuits.gates;

import circuits.Gate;
import circuits.Wire;

/*
    |			|
    |			|
   Left         #
    |			|
    |			|
    \			/
     \		   /
   ----------------
   |			  |
   |	RSHIFT	  |
   |			  |
   ----------------
		  |
		  |
	     Out
	  	  |
	 	  |
*/

public class RShiftGate extends Gate{
	private Wire leftInputWire;
	private Integer leftInputValue = null;
	private Wire rightInputWire;
	private Integer rightInputValue = null;	
	
	public RShiftGate(Wire leftInput, int number, Wire output) {
		super(output);
		
		this.leftInputWire = leftInput;
		this.rightInputValue = number;
	}
	
	public RShiftGate(Wire output) {
		super(output);
	}
	
	public RShiftGate(Wire leftInput, Wire rightInput, Wire output) {
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
			
			getOutput().setValue(left >> right);
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
				" RSHIFT " + (rightInputWire == null ? rightInputValue : rightInputWire.getName() + "(" + rightInputWire.getValue() + ")") 
				+ " -> " + getOutput().getName() + "(" + getOutput().getValue() + ")";
	}
}
