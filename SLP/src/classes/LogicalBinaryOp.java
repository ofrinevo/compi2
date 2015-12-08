package classes;

import slp.BinaryOps;

/**
 * Logical binary operation AST node.
 */
public class LogicalBinaryOp extends BinaryOp {

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
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
	public LogicalBinaryOp(Expression operand1, BinaryOps operator,
			Expression operand2) {
		super(operand1, operator, operand2);
	}

}
