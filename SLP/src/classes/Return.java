package classes;

/**
 * Return statement AST node.
 */
public class Return extends Statement {

	private Expression value = null;
	private type.Type methodType;
	
	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	/**
	 * Constructs a new return statement node, with no return value.
	 * 
	 * @param line
	 *            Line number of return statement.
	 */
	public Return(int line) {
		super(line);
	}

	/**
	 * Constructs a new return statement node.
	 * 
	 * @param line
	 *            Line number of return statement.
	 * @param value
	 *            Return value.
	 */
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
