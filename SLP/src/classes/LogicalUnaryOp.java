package classes;

import slp.UnaryOps;

/**
 * Logical unary operation AST node.
 */
public class LogicalUnaryOp extends UnaryOp {

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	/**
	 * Constructs a new logical unary operation node.
	 * 
	 * @param operator
	 *            The operator.
	 * @param operand
	 *            The operand.
	 */
	public LogicalUnaryOp(UnaryOps operator, Expression operand) {
		super(operator, operand);
	}

}
