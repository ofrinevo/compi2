package slp;

/**
 * Class instance creation AST node.
 */
public class NewClass extends New {

	private String name;

	public void accept(Visitor visitor) {
		 visitor.visit(this);
	}

	/**
	 * Constructs a new class instance creation expression node.
	 * 
	 * @param line
	 *            Line number of expression.
	 * @param name
	 *            Name of class.
	 */
	public NewClass( String name) {
		
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public <DownType, UpType> UpType accept(
			PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}

}