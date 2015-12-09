package classes;


public class ExpressionBlock extends Expression {

	private Expression expression;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public ExpressionBlock(Expression expression) {
		super(expression.getLine());
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}

}
