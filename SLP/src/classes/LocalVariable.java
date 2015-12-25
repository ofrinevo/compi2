package classes;


public class LocalVariable extends Statement {

	private Type type;

	private String name;
	
	private Expression initValue = null;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public LocalVariable(Type type, String name) {
		super(type.getLine());
		this.type = type;
		this.name = name;
	}

	
	public LocalVariable(Type type, String name, Expression initValue) {
		this(type, name);
		this.initValue = initValue;
	}

	public Type getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public boolean hasInitValue() {
		return (initValue != null);
	}

	public Expression getInitValue() {
		return initValue;
	}
}
