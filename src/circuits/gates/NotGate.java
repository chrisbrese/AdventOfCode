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
	private Wire input;
	
	public NotGate(Wire input, Wire output) {
		super(output);
		
		this.input = input;
	}
	
	public Character run() {
		if(input != null) {
			getOutput().setValue((char) (~input.getValue().charValue()));
			return getOutput().getValue();
		}
		
		return null;
	}
}
