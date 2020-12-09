package circuits;

public class Gate {
	private String outputWireNameOrValue;
	private boolean hasRun;
	
	public Gate(String output) {
		this.outputWireNameOrValue = output;
	}
	
	public String getOutput() {
		return outputWireNameOrValue;
	}
	
	protected void successfullyRan() {
		this.hasRun = true;
	}
	
	public boolean hasRun() {
		return hasRun;
	}
}
