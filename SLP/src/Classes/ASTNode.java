package Classes;

import slp.PropagatingVisitor;
import slp.Visitor;

/**
 * The base class of all AST nodes in this package.
 */
public abstract class ASTNode {
	/**
	 * Accepts a visitor object as part of the visitor pattern.
	 * 
	 * @param visitor
	 *            A visitor.
	 */
	private int line;

	public abstract void accept(Visitor visitor);

	/**
	 * Accepts a propagating visitor parameterized by two types.
	 * 
	 * @param <DownType>
	 *            The type of the object holding the context.
	 * @param <UpType>
	 *            The type of the result object.
	 * @param visitor
	 *            A propagating visitor.
	 * @param context
	 *            An object holding context information.
	 * @return The result of visiting this node.
	 */

	protected ASTNode(int line) {
		this.line = line;
	}
	public ASTNode(){}

	public int getLine() {
		return line;
	}

	public abstract <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context);
}