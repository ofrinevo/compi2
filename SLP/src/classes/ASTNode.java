package classes;

import symTable.*;


public abstract class ASTNode {

	private int line;
	private SymbolTable symbolsTable;
	
	private type.Type entryType;
	private symTable.SymbolEntry symbolEntry;
	
	
	public abstract Object accept(Visitor visitor);

	
	protected ASTNode(int line) {
		this.symbolsTable = null;
		this.line = line;
		this.symbolEntry = null;
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
		return (this.symbolEntry == null) ? entryType : this.symbolEntry.getType();
	}

	public void setEntryType(type.Type entryType) {
		this.entryType = entryType;
	}

	public symTable.SymbolEntry getSymbolEntry() {
		return symbolEntry;
	}

	public void setSymbolEntry(symTable.SymbolEntry symbolEntry) {
		this.symbolEntry = symbolEntry;
	}
	
	
}
