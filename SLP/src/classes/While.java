package classes;


public class While extends Statement {

	private Expression condition;

	private Statement operation;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public While(Expression condition, Statement operation) {
		super(condition.getLine());
		this.condition = condition;
		this.operation = operation;
	}
	
	public Expression getCondition() {
		return condition;
	}

	public Statement getOperation() {
		return operation;
	}

}
