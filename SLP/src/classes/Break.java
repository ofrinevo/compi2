package classes;


public class Break extends Statement {

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public Break(int line) {
		super(line);
	}

}
