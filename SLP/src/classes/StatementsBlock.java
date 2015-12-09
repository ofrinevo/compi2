package classes;

import java.util.List;


public class StatementsBlock extends Statement {

	private List<Statement> statements;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public StatementsBlock(int line, List<Statement> statements) {
		super(line);
		this.statements = statements;
	}

	public List<Statement> getStatements() {
		return statements;
	}

}
