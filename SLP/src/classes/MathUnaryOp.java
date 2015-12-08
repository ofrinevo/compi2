package classes;

import slp.UnaryOps;

/**
 * Mathematical unary operation AST node.
 */
public class MathUnaryOp extends UnaryOp {

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	/**
	 * Constructs a new mathematical unary operation node.
	 * 
	 * @param operator
	 *            The operator.
	 * @param operand
	 *            The operand.
	 */
	public MathUnaryOp(UnaryOps operator, Expression operand) {
		super(operator, operand);
	}

}
