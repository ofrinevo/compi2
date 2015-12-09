package classes;

import java.util.List;


public class StaticMethod extends Method {

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public StaticMethod(Type type, String name, List<Formal> formals,
			List<Statement> statements) {
		super(type, name, formals, statements);
	}

}
