package slp;

/**
 * Abstract base class for expression AST nodes.
 */
public abstract class Expression extends ASTNode {

	/**
	 * Constructs a new expression node. Used by subclasses.
	 * 
	 * @param line
	 *            Line number of expression.
	 */
	protected Expression() {
		
	}
}