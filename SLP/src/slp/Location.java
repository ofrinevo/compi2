package slp;

/**
 * Abstract base class for variable reference AST nodes.
 */
public abstract class Location extends Expr {

	/**
	 * Constructs a new variable reference node. Used by subclasses.
	 * 
	 */
	protected Location(int line) {
		super(line);
	}

}
