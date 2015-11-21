package slp;

/**
 * Abstract base class for variable reference AST nodes.
 */
public abstract class Location extends Expr {

	/**
	 * Constructs a new variable reference node. Used by subclasses.
	 * 
	 * @param line
	 *            Line number of reference.
	 */
	protected Location() {
		
	}

}
