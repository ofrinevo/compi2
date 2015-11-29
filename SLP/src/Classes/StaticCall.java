package Classes;

import java.util.List;

import Classes.Expr;
import slp.PropagatingVisitor;
import slp.Visitor;


/**
 * Static method call AST node.
 */
public class StaticCall extends Call {

	private String className;

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Constructs a new static method call node.
	 * 
	 * @param className
	 *            Name of method's class.
	 * @param name
	 *            Name of method.
	 * @param arguments
	 *            List of all method arguments.
	 */
	public StaticCall(int line, String className, String name,
			List<Expr> arguments) {
		super(line, name, arguments);
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	@Override
	public <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}

}
