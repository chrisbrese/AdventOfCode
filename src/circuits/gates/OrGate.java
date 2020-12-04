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
	private Wire leftInput;
	private Wire rightInput;
	
	public OrGate(Wire leftInput, Wire rightInput, Wire output) {
		super(output);
		
		this.leftInput = leftInput;
		this.rightInput = rightInput;
	}
	
	public Character run() {
		if(leftInput.getValue() != null && rightInput.getValue() != null) {
			getOutput().setValue((char) (leftInput.getValue().charValue() | rightInput.getValue().charValue()));
			return getOutput().getValue();
		}
		
		return null;
	}
}
