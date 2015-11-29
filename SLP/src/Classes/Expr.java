package Classes;

import slp.Visitor;

/** A base class for AST nodes for expressions.
 */
public abstract class Expr extends ASTNode {
	
	protected Expr(int line){
		super(line);
	}
	protected Expr(){}
	/** Accepts a visitor object as part of the visitor pattern.
	 * @param visitor A visitor.
	 */
	public abstract void accept(Visitor visitor);
}