package Classes;

import java.util.List;

import slp.PropagatingVisitor;
import slp.Visitor;

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
	 * @param statements
	 *            List of all statements in block.
	 */
	public StmtsBlock(int line, List<Stmt> statements) {
		super(line);
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
