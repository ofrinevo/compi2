package slp;

/**
 * Array reference AST node.
 */
public class ArrayLocation extends Location {

	private Expr array;

	private Expr index;

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Constructs a new array reference node.
	 * 
	 * @param array
	 *            Expression representing an array.
	 * @param index
	 *            Expression representing a numeric index.
	 */
	public ArrayLocation(Expr array, Expr index) {
		super(array.getLine());
		this.array = array;
		this.index = index;
	}

	public Expr getArray() {
		return array;
	}

	public Expr getIndex() {
		return index;
	}

	public <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}
}