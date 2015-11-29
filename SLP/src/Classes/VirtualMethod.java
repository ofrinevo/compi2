package Classes;

import java.util.List;

import slp.PropagatingVisitor;
import slp.Visitor;

/**
 * Virtual method AST node.
 */
public class VirtualMethod extends Method {

	public void accept(Visitor visitor) {
		 visitor.visit(this);
	}

	/**
	 * Constructs a new virtual method node.
	 * 
	 * @param type
	 *            Data type returned by method.
	 * @param name
	 *            Name of method.
	 * @param formals
	 *            List of method parameters.
	 * @param statements
	 *            List of method's statements.
	 */
	public VirtualMethod(Type type, String name, List<Formal> formals,
			List<Stmt> statements) {
		super(type, name, formals, statements);
	}
	public <DownType, UpType> UpType accept(
			PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}
}
