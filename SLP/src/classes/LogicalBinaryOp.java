package classes;

import slp.BinaryOps;


public class LogicalBinaryOp extends BinaryOp {

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public LogicalBinaryOp(Expression operand1, BinaryOps operator,
			Expression operand2) {
		super(operand1, operator, operand2);
	}

}
