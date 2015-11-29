package Classes;

import slp.PropagatingVisitor;
import slp.Visitor;

/**
 * User-defined data type AST node.
 */
public class UserType extends Type {

	private String name;


	/**
	 * Constructs a new user-defined data type node.
	 * 
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
	public <DownType, UpType> UpType accept(
			PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}

}