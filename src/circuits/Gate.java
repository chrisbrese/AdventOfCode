package circuits;

public class Gate {
	private Wire output;
	private Character outValue;
	
	public Gate(Wire output) {
		this.output = output;
	}
	
	public Wire getOutput() {
		return output;
	}
}
