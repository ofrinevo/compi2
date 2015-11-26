package slp;

/**
 * Variable reference AST node.
 */
public class VariableLocation extends Location {

	private Expr location = null;

	private String name;

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Constructs a new variable reference node.
	 * 
	 * @param name
	 *            Name of variable.
	 */
	public VariableLocation(int line, String name) {
		super(line);
		this.name = name;
	}

	/**
	 * Constructs a new variable reference node, for an external location.
	 * 
	 * @param location
	 *            Location of variable.
	 * @param name
	 *            Name of variable.
	 */
	public VariableLocation(int line, Expr location, String name) {
		this(line,name);
		this.location = location;
	}

	public boolean isExternal() {
		return (location != null);
	}

	public Expr getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	@Override
	public <DownType, UpType> UpType accept(
			PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}

}
