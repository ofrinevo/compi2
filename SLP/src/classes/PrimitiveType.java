package classes;

import slp.DataTypes;


public class PrimitiveType extends Type {

	private DataTypes type;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public PrimitiveType(int line, DataTypes type) {
		super(line);
		this.type = type;
	}

	public String getName() {
		return type.getDescription();
	}
	
	@Override
	public Type clone() {
		Type other = new PrimitiveType(getLine(), type);
		other.setDimension(getDimension());
		return other;
	}

}