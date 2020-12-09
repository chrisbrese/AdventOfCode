package circuits;

public class Wire {
	private String name;
	private String value;
	
	public Wire(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return "Wire " + name + ": " + getValue();
	}
}
