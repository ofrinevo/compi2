package classes;


public class This extends Expression {

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public This(int line) {
		super(line);
	}

}
