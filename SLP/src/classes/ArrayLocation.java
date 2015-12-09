package classes;


public class ArrayLocation extends Location {

	private Expression array;

	private Expression index;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public ArrayLocation(Expression array, Expression index) {
		super(array.getLine());
		this.array = array;
		this.index = index;
	}

	public Expression getArray() {
		return array;
	}

	public Expression getIndex() {
		return index;
	}
}