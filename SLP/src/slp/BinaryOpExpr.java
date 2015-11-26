package slp;

/** An AST node for binary expressions.
 */
public class BinaryOpExpr extends Expr {
	public final Expr lhs;
	public final Expr rhs;
	public final BinaryOps op;
	
	public BinaryOpExpr(Expr lhs, BinaryOps op, Expr rhs) {
		super(lhs.getLine());
		this.lhs = lhs;
		this.rhs = rhs;
		this.op = op;
	}

	/** Accepts a visitor object as part of the visitor pattern.
	 * @param visitor A visitor.
	 */
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	/** Accepts a propagating visitor parameterized by two types.
	 * 
	 * @param <DownType> The type of the object holding the context.
	 * @param <UpType> The type of the result object.
	 * @param visitor A propagating visitor.
	 * @param context An object holding context information.
	 * @return The result of visiting this node.
	 */
	@Override
	public <DownType, UpType> UpType accept(
			PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}
	
	public String toString() {
		return lhs.toString() + op + rhs.toString();
	}	
	
	public Expr getFirstOperand() {
		return lhs;
	}
	
	public BinaryOps getOperator() {
		return op;
	}
	
	public Expr getSecondOperand() {
		return rhs;
	}
}