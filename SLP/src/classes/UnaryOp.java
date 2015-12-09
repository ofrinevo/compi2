package classes;

import slp.UnaryOps;


public abstract class UnaryOp extends Expression {

	private UnaryOps operator;

	private Expression operand;

	
	protected UnaryOp(UnaryOps operator, Expression operand) {
		super(operand.getLine());
		this.operator = operator;
		this.operand = operand;
	}

	public UnaryOps getOperator() {
		return operator;
	}

	public Expression getOperand() {
		return operand;
	}

}
