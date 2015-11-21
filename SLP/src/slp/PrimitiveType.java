package slp;

import slp.DataTypes;

/**
 * Primitive data type AST node.
 * 
 * 
 */
public class PrimitiveType extends Type {

	public DataTypes type;


	/**
	 * Constructs a new primitive data type node.
	 * 
	 * @param line
	 *            Line number of type declaration.
	 * @param type
	 *            Specific primitive data type.
	 */
	public PrimitiveType(DataTypes type) {
		this.type=type;
	}

	public String getName() {
		return type.getDescription();
	}

	@Override
	public <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}