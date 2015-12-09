package classes;


public class Length extends Expression {

	private Expression array;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public Length(Expression array) {
		super(array.getLine());
		this.array = array;
	}

	public Expression getArray() {
		return array;
	}

}
