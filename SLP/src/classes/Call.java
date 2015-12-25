package classes;

import java.util.List;


public abstract class Call extends Expression {

	private String name;
	private List<Expression> arguments;
	private type.Type methodType;
	
	protected Call(int line, String name, List<Expression> arguments) {
		super(line);
		this.name = name;
		this.arguments = arguments;
	}

	public String getName() {
		return name;
	}

	public List<Expression> getArguments() {
		return arguments;
	}

	public type.Type getMethodType() {
		return methodType;
	}

	public void setMethodType(type.Type methodTtype) {
		this.methodType = methodTtype;
	}

}
