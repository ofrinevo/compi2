package slp;

import slp.UnaryOpExpr;

/**
 * Logical unary operation AST node.
 */
public class LogicalUnaryOp extends UnaryOpExpr {


	public void accept(Visitor visitor) {
		 visitor.visit(this);
	}

	/**
	 * Constructs a new logical unary operation node.
	 * 
	 * @param operator
	 *            The operator.
	 * @param operand
	 *            The operand.
	 */
	public LogicalUnaryOp(UnaryOps operator, Expr operand) {
		super(operator, operand);
	}

}
