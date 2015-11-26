package slp;

/** The super class of all AST node for program statements.
 */
public abstract class Stmt extends ASTNode {
	
	protected Stmt(int line){
		super(line);
	}
	
	/** Accepts a visitor object as part of the visitor pattern.
	 * @param visitor A visitor.
	 */
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}