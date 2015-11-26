package slp;

/**
 * Abstract base class for object creation AST nodes.
 */
public abstract class New extends Expr {

	/**
	 * Constructs a new object creation expression node. Used by subclasses.
	 * 
	 */
	protected New(int line) {
		super(line);
	}

}
