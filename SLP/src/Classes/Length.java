package Classes;

import Classes.Expr;
import slp.PropagatingVisitor;
import slp.Visitor;

/**
 * Array length expression AST node.
 */
public class Length extends Expr {

	private Expr array;

	public void accept(Visitor visitor) {
		 visitor.visit(this);
	}

	/**
	 * Constructs a new array length expression node.
	 * 
	 * @param array
	 *            Expression representing an array.
	 */
	public Length(Expr array) {
		super(array.getLine());
		this.array = array;
	}

	public Expr getArray() {
		return array;
	}
	public <DownType, UpType> UpType accept(
			PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}
}
