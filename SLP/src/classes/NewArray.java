package classes;


public class NewArray extends New {

	private Type type;

	private Expression size;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public NewArray(Type type, Expression size) {
		super(type.getLine());
		this.type = type;
		this.size = size;
	}

	public Type getType() {
		return type;
	}

	public Expression getSize() {
		return size;
	}

}
