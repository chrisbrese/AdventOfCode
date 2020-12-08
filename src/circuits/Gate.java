package circuits;

public class Gate {
	private Wire output;
	private boolean hasRun;
	
	public Gate(Wire output) {
		this.output = output;
	}
	
	public Wire getOutput() {
		return output;
	}
	
	protected void successfullyRan() {
		this.hasRun = true;
	}
	
	public boolean hasRun() {
		return hasRun;
	}
}
