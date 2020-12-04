package circuits;

import java.util.List;

public class Wire {
	private String name;
	private Character value;
	
	public Wire(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setValue(char value) {
		this.value = value;
	}
	
	public Character getValue() {
		return (char) value;
	}
}
