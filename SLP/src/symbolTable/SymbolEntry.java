package symbolTable;

import type.Type;


public class SymbolEntry {
	
	private String id;
	private Type type;
	private IDSymbolsKinds kind;
	  
	
	public SymbolEntry(String id, Type type, IDSymbolsKinds kind) {
		this.id =id;
	    this.type = type;
	    this.kind = kind;
	}
	
	
	public String getId() {
		return id;
	}

	
	public Type getType() {
		return type;
	}
	
	
	public IDSymbolsKinds getKind() {
		return kind;
	}
	
	@Override
	public String toString() {
		if (kind == IDSymbolsKinds.CLASS)
			return kind.toString() + ": " + id;
		if (kind.isMethodKind())
			return kind.toString() + ": " + id + " {" + type.toString() + "}";
		
		return kind.toString() + ": " + type.toString() + " " + id;
	}
}
