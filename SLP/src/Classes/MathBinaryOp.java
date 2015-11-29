package Classes;

import Classes.BinaryOpExpr;
import slp.Visitor;

/**
 * Mathematical binary operation AST node.
 */
public class MathBinaryOp extends BinaryOpExpr {


	public void accept(Visitor visitor) {
		 visitor.visit(this);
	}

	/**
	 * Constructs a new mathematical binary operation node.
	 * 
	 * @param operand1
	 *            The first operand.
	 * @param operator
	 *            The operator.
	 * @param operand2
	 *            The second operand.
	 */
	public MathBinaryOp(Expr operand1, BinaryOps operator, Expr operand2) {
		super(operand1, operator, operand2);
	}

}
