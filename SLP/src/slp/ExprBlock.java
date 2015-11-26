package slp;

/**
 * AST node for expression in parentheses.
 */
public class ExprBlock extends Expr {

	private Expr expression;

	public void accept(Visitor visitor) {
		 visitor.visit(this);
	}

	/**
	 * Constructs a new expression in parentheses node.
	 * 
	 * @param expression
	 *            The expression.
	 */
	public ExprBlock(Expr expression) {
		super(expression.getLine());
		this.expression = expression;
	}

	public Expr getExpression() {
		return expression;
	}
	public <DownType, UpType> UpType accept(
			PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}
}
