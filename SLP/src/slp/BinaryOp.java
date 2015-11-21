package slp;

/**
 * Abstract base class for binary operation AST nodes.
 */
public abstract class BinaryOp extends Expr {

	private Expr operand1;

	private BinaryOps operator;

	private Expr operand2;

	/**
	 * Constructs a new binary operation node. Used by subclasses.
	 * 
	 * @param operand1
	 *            The first operand.
	 * @param operator
	 *            The operator.
	 * @param operand2
	 *            The second operand.
	 */
	protected BinaryOp(Expr operand1, BinaryOps operator,
			Expr operand2) {
		this.operand1 = operand1;
		this.operator = operator;
		this.operand2 = operand2;
	}

	public BinaryOps getOperator() {
		return operator;
	}

	public Expr getFirstOperand() {
		return operand1;
	}

	public Expr getSecondOperand() {
		return operand2;
	}

}
