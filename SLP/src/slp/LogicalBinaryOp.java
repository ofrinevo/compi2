package slp;

/**
 * Logical binary operation AST node.
 */
public class LogicalBinaryOp extends BinaryOpExpr {

	public void accept(Visitor visitor) {
		 visitor.visit(this);
	}

	/**
	 * Constructs a new logical binary operation node.
	 * 
	 * @param operand1
	 *            The first operand.
	 * @param operator
	 *            The operator.
	 * @param operand2
	 *            The second operand.
	 */
	public LogicalBinaryOp(Expr operand1, BinaryOps operator, Expr operand2) {
		super(operand1, operator, operand2);
	}

	public LogicalBinaryOp(Expr e1, BinaryOp lbop, Expr e2) {
		// TODO Auto-generated constructor stub
	}



}
