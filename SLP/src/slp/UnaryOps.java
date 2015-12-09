package slp;

public enum UnaryOps {

	UMINUS("-", "unary subtraction"), 
	LNEG("!", "logical negation");

	private String operator;

	private String description;

	private UnaryOps(String operator, String description) {
		this.operator = operator;
		this.description = description;
	}

	
	public String getOperatorString() {
		return operator;
	}

	
	public String getDescription() {
		return description;
	}
}