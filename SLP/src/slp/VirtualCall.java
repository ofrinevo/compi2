package slp;

import java.util.List;

import slp.Expr;


/**
 * Virtual method call AST node.
 * 
 * 
 */
public class VirtualCall extends Call {

	private Expr location = null;

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	/**
	 * Constructs a new virtual method call node.
	 * 
	 * @param line
	 *            Line number of call.
	 * @param name
	 *            Name of method.
	 * @param arguments
	 *            List of all method arguments.
	 */
	public VirtualCall(String name, List<Expr> arguments) {
		super(name, arguments);
	}

	/**
	 * Constructs a new virtual method call node, for an external location.
	 * 
	 * @param line
	 *            Line number of call.
	 * @param location
	 *            Location of method.
	 * @param name
	 *            Name of method.
	 * @param arguments
	 *            List of all method arguments.
	 */
	public VirtualCall(Expr location, String name,
			List<Expr> arguments) {
		this(name, arguments);
		this.location = location;
	}

	public boolean isExternal() {
		return (location != null);
	}

	public Expr getLocation() {
		return location;
	}
	@Override
	public <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}

}
