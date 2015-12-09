package classes;


public class UserType extends Type {

	private String name;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
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
