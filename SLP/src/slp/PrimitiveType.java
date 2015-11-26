package slp;

import slp.DataTypes;

/**
 * Primitive data type AST node.

 */
public class PrimitiveType extends Type {

	public DataTypes type;


	/**
	 * Constructs a new primitive data type node.
	 * 
	 * @param type
	 *            Specific primitive data type.
	 */
	public PrimitiveType(int line, DataTypes type) {
		super(line);
		this.type=type;
	}

	public String getName() {
		return type.getDescription();
	}

	@Override
	public <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}


	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}