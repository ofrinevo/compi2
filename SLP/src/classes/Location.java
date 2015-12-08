package classes;

/**
 * Abstract base class for variable reference AST nodes.
 */
public abstract class Location extends Expression {

	/**
	 * Constructs a new variable reference node. Used by subclasses.
	 * 
	 * @param line
	 *            Line number of reference.
	 */
	protected Location(int line) {
		super(line);
	}

}
