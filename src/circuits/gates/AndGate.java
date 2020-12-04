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
   |	 AND	  |
   |			  |
   ----------------
		  |
		  |
	     Out
	  	  |
	 	  |
*/

public class AndGate extends Gate{
	private Wire leftInput;
	private Wire rightInput;
	
	public AndGate(Wire leftInput, Wire rightInput, Wire output) {
		super(output);
		
		this.leftInput = leftInput;
		this.rightInput = rightInput;
	}
	
	public Character run() {
		if(leftInput.getValue() != null && rightInput.getValue() != null) {
			getOutput().setValue((char) (leftInput.getValue().charValue() & rightInput.getValue().charValue()));
			return getOutput().getValue();
		}
		
		return null;
	}
}
