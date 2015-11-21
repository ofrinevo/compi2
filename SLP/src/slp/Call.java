package slp;

import java.util.List;

import slp.Expr;

/**
 * Abstract base class for method call AST nodes.
 * 
 * 
 */
public abstract class Call extends Expr {

	private String name;

	private List<Expr> arguments;

	/**
	 * Constructs a new method call node. Used by subclasses.
	 * 
	 * @param line
	 *            Line number of call.
	 * @param name
	 *            Name of method.
	 * @param arguments
	 *            List of all method arguments.
	 */
	protected Call(String name, List<Expr> arguments) {
		
		this.name = name;
		this.arguments = arguments;
	}

	public String getName() {
		return name;
	}

	public List<Expr> getArguments() {
		return arguments;
	}

}
