package classes;

import symbolTable.*;


public abstract class ASTNode {

	private int line;
	private SymbolTable symbolsTable;
	
	private type.Type entryType;

	
	public abstract Object accept(Visitor visitor);

	
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
