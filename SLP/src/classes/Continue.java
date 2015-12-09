package classes;


public class Continue extends Statement {

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public Continue(int line) {
		super(line);
	}

}
