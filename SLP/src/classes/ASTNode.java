package classes;

import symbolTable.*;

/**
 * Abstract AST node base class.
 */
public abstract class ASTNode {

	private int line;
	private SymbolTable symbolsTable;
	
	private type.Type entryType;

	/**
	 * Double dispatch method, to allow a visitor to visit a specific subclass.
	 * 
	 * @param visitor
	 *            The visitor.
	 * @return A value propagated by the visitor.
	 */
	public abstract Object accept(Visitor visitor);

	/**
	 * Constructs an AST node corresponding to a line number in the original
	 * code. Used by subclasses.
	 * 
	 * @param line
	 *            The line number.
	 */
	protected ASTNode(int line) {
		this.symbolsTable = null;
		this.line = line;
	}

	public int getLine() {
		return line;
	}
	
	public SymbolTable getSymbolsTable() {
		return symbolsTable;
	}
	
	public void setSymbolsTable(SymbolTable table) {
		this.symbolsTable = table;
	}
	
	public type.Type getEntryType() {
		return entryType;
	}

	public void setEntryType(type.Type entryType) {
		this.entryType = entryType;
	}
}
