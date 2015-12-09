package classes;


public class VariableLocation extends Location {

	private Expression location = null;

	private String name;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public VariableLocation(int line, String name) {
		super(line);
		this.name = name;
	}

	
	public VariableLocation(int line, Expression location, String name) {
		this(line, name);
		this.location = location;
	}

	public boolean isExternal() {
		return (location != null);
	}

	public Expression getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

}
