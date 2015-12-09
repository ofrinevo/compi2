package classes;


public class Formal extends ASTNode {

	private Type type;

	private String name;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public Formal(Type type, String name) {
		super(type.getLine());
		this.type = type;
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public String getName() {
		return name;
	}

}
