package Classes;

/**
 * Abstract base class for unary operation AST nodes.
 */
public abstract class UnaryOp extends Expr {

	private UnaryOps operator;

	private Expr operand;

	/**
	 * Constructs a new unary operation node. Used by subclasses.
	 * 
	 * @param operator
	 *            The operator.
	 * @param operand
	 *            The operand.
	 */
	protected UnaryOp(UnaryOps operator, Expr operand) {
		super(operand.getLine());
		this.operator = operator;
		this.operand = operand;
	}

	public UnaryOps getOperator() {
		return operator;
	}

	public Expr getOperand() {
		return operand;
	}

}
