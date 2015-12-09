package classes;

import slp.UnaryOps;


public class LogicalUnaryOp extends UnaryOp {

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public LogicalUnaryOp(UnaryOps operator, Expression operand) {
		super(operator, operand);
	}

}
