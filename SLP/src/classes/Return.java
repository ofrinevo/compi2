package classes;


public class Return extends Statement {

	private Expression value = null;
	private type.Type methodType;
	
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public Return(int line) {
		super(line);
	}

	
	public Return(int line, Expression value) {
		this(line);
		this.value = value;
	}

	public boolean hasValue() {
		return (value != null);
	}

	public Expression getValue() {
		return value;
	}

	public type.Type getMethodType() {
		return methodType;
	}

	public void setMethodType(type.Type methodType) {
		this.methodType = methodType;
	}
	
	
}
