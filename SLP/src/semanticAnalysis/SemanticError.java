package semanticAnalysis;

import java.io.IOException;

public class SemanticError extends IOException {

	private static final long serialVersionUID = 1L;
	
	public SemanticError(int line, String msg) {
		super("There is a semantic error at line " + line + ": " + msg + ".");
	}
}

