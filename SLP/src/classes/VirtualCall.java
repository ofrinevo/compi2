package classes;

import java.util.List;


public class VirtualCall extends Call {

	private Expression location = null;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public VirtualCall(int line, String name, List<Expression> arguments) {
		super(line, name, arguments);
	}

	
	public VirtualCall(int line, Expression location, String name,
			List<Expression> arguments) {
		this(line, name, arguments);
		this.location = location;
	}

	public boolean isExternal() {
		return (location != null);
	}

	public Expression getLocation() {
		return location;
	}

}
