package slp;

import java.util.List;

import Classes.ASTNode;
import Classes.ICClass;

/**
 * Root AST node for an IC program.
 */
public class Program extends ASTNode {

	private List<ICClass> classes;

	public void accept(Visitor visitor) {
		 visitor.visit(this);
	}

	/**
	 * Constructs a new program node.
	 * 
	 * @param classes
	 *            List of all classes declared in the program.
	 */
	public Program(List<ICClass> classes) {
		
		super(0);
		this.classes = classes;
	}

	public List<ICClass> getClasses() {
		return classes;
	}
	public <DownType, UpType> UpType accept(
			PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}
}
