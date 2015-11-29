package Classes;

import java.util.List;

import Classes.Expr;
import slp.PropagatingVisitor;
import slp.Visitor;


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
	 * @param name
	 *            Name of method.
	 * @param arguments
	 *            List of all method arguments.
	 */
	public VirtualCall(int line, String name, List<Expr> arguments) {
		super(line, name, arguments);
	}

	/**
	 * Constructs a new virtual method call node, for an external location.
	 * 
	 * @param location
	 *            Location of method.
	 * @param name
	 *            Name of method.
	 * @param arguments
	 *            List of all method arguments.
	 */
	public VirtualCall(int line, Expr location, String name,
			List<Expr> arguments) {
		this(line, name, arguments);
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
