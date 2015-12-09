package classes;

import slp.UnaryOps;


public class MathUnaryOp extends UnaryOp {

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public MathUnaryOp(UnaryOps operator, Expression operand) {
		super(operator, operand);
	}

}
