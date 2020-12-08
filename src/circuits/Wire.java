package circuits;

public class Wire {
	private String name;
	private Integer value = null;
	
	public Wire(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public String toString() {
		return "Wire " + name + ": " + getValue();
	}
}
