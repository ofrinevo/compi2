package slp;

/**
 * Array creation AST node.
 */
public class NewArray extends New {

	private Type type;

	private Expr size;

	public void accept(Visitor visitor) {
		 visitor.visit(this);
	}

	/**
	 * Constructs a new array creation expression node.
	 * 
	 * @param type
	 *            Data type of new array.
	 * @param size
	 *            Size of new array.
	 */
	public NewArray(Type type, Expr size) {
	
		this.type = type;
		this.size = size;
	}

	public Type getType() {
		return type;
	}

	public Expr getSize() {
		return size;
	}
	public <DownType, UpType> UpType accept(
			PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}
}
