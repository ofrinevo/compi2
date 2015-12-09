package classes;


public class NewClass extends New {

	private String name;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public NewClass(int line, String name) {
		super(line);
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
