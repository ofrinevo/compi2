package classes;

/**
 * User-defined data type AST node.
 */
public class UserType extends Type {

	private String name;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	/**
	 * Constructs a new user-defined data type node.
	 * 
	 * @param line
	 *            Line number of type declaration.
	 * @param name
	 *            Name of data type.
	 */
	public UserType(int line, String name) {
		super(line);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public Type clone() {
		Type other = new UserType(getLine(), name);
		other.setDimension(getDimension());
		return other;
	}
}
