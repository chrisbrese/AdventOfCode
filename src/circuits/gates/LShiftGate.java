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
   |	LSHIFT	  |
   |			  |
   ----------------
		  |
		  |
	     Out
	  	  |
	 	  |
*/

public class LShiftGate extends Gate{
	private String leftInputWireNameOrValue;
	private String rightInputWireNameOrValue;
	
	public LShiftGate(String outputNameOrValue) {
		super(outputNameOrValue);
	}
	
	public LShiftGate(String leftInput, String rightInput, String outputNameOrValue) {
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
		return leftValue << rightValue;
	}
}
