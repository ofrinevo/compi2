package classes;

import java.util.List;


public class VirtualMethod extends Method {

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public VirtualMethod(Type type, String name, List<Formal> formals,
			List<Statement> statements) {
		super(type, name, formals, statements);
	}

}
