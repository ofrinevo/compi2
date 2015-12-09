package classes;

import slp.LiteralTypes;


public class Literal extends Expression {

	private LiteralTypes type;

	private Object value;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public Literal(int line, LiteralTypes type) {
		super(line);
		this.type = type;
		value = type.getValue();
	}

	
	public Literal(int line, LiteralTypes type, Object value) {
		this(line, type);
		this.value = value;
	}

	public LiteralTypes getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

}
