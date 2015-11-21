package slp;

import java.util.List;

/**
 * Statements block AST node.

 */
public class StmtsBlock extends Stmt {

	private List<Stmt> statements;

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Constructs a new statements block node.
	 * 
	 * @param line
	 *            Line number where block begins.
	 * @param statements
	 *            List of all statements in block.
	 */
	public StmtsBlock(List<Stmt> statements) {
		
		this.statements = statements;
	}

	public List<Stmt> getStatements() {
		return statements;
	}
	public <DownType, UpType> UpType accept(
			PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}
}
