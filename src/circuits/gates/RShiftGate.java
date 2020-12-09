package circuits.gates;

import circuits.Gate;

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
	private String leftInputWireNameOrValue;
	private String rightInputWireNameOrValue;
	
	public RShiftGate(String outputNameOrValue) {
		super(outputNameOrValue);
	}
	
	public RShiftGate(String leftInput, String rightInput, String outputNameOrValue) {
		super(outputNameOrValue);
		
		setLeftInputWireNameOrValue(leftInput);
		setRightInputWireNameOrValue(rightInput);
	}
	
	public void setLeftInputWireNameOrValue(String value) {
		leftInputWireNameOrValue = value;
	}
	
	public String getLeftInputWireNameOrValue() {
		return leftInputWireNameOrValue;
	}
	
	public void setRightInputWireNameOrValue(String value) {
		rightInputWireNameOrValue = value;
	}
	
	public String getRightInputWireNameOrValue() {
		return rightInputWireNameOrValue;
	}
	
	public Integer run(int leftValue, int rightValue) {
		return leftValue >> rightValue;
	}
}
