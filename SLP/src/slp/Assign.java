package slp;

/**
 * Assignment statement AST node.
 * 
 * 
 */
public class Assign extends Statement {

	private Location variable;

	private Expression assignment;

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Constructs a new assignment statement node.
	 * 
	 * @param variable
	 *            Variable to assign a value to.
	 * @param assignment
	 *            Value to assign.
	 * @return 
	 */
	public Assign(Location variable, Expression assignment) {
		
		this.variable = variable;
		this.assignment = assignment;
	}

	public Location getVariable() {
		return variable;
	}

	public Expression getAssignment() {
		return assignment;
	}

	@Override
	public <DownType, UpType> UpType accept(
			PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}
}
