package slp;

/**
 * Literal value AST node.
 */
public class Literal extends Expr {

	private LiteralTypes type;

	private Object value;

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Constructs a new literal node.
	 * 
	 * @param type
	 *            Literal type.
	 */
	public Literal( LiteralTypes type) {
		
		this.type = type;
		value = type.getValue();
	}

	/**
	 * Constructs a new literal node, with a value.
	 *  
	 * @param type
	 *            Literal type.
	 * @param value
	 *            Value of literal.
	 */
	public Literal( LiteralTypes type, Object value) {
		this.type=type;
		this.value = value;
	}

	public LiteralTypes getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}
	public String toString(){
		return value.toString();
	}
	@Override
	public <DownType, UpType> UpType accept(
			PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}

}
