package classes;

import java.util.List;


public class Program extends ASTNode {

	private List<ICClass> classes;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public Program(List<ICClass> classes) {
		super(0);
		this.classes = classes;
	}

	public List<ICClass> getClasses() {
		return classes;
	}

}
