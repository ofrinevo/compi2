package Classes;

import Classes.UnaryOpExpr;
import slp.Visitor;

/**
 * Mathematical unary operation AST node.
 */
public class MathUnaryOp extends UnaryOpExpr {


	public void accept(Visitor visitor) {
		 visitor.visit(this);
	}

	/**
	 * Constructs a new mathematical unary operation node.
	 * 
	 * @param operator
	 *            The operator.
	 * @param operand
	 *            The operand.
	 */
	public MathUnaryOp(UnaryOps operator, Expr operand) {
		super(operator, operand);
	}

}
