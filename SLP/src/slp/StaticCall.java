package slp;

import java.util.List;

import slp.Expr;;


/**
 * Static method call AST node.
 * 
 * 
 */
public class StaticCall extends Call {

	private String className;

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Constructs a new static method call node.
	 * 
	 * @param line
	 *            Line number of call.
	 * @param className
	 *            Name of method's class.
	 * @param name
	 *            Name of method.
	 * @param arguments
	 *            List of all method arguments.
	 */
	public StaticCall( String className, String name,
			List<Expr> arguments) {
		super(name, arguments);
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	@Override
	public <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		// TODO Auto-generated method stub
		return null;
	}

}
