package classes;

import java.util.ArrayList;
import java.util.List;


public class LibraryMethod extends Method {

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public LibraryMethod(Type type, String name, List<Formal> formals) {
		super(type, name, formals, new ArrayList<Statement>());
	}
}