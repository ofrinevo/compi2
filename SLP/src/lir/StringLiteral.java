package lir;


public class StringLiteral {
	public final String var;
	public final String value;
	protected int address = -1;
	
	protected static int numberOfStringLiterals = 0;
	
	public StringLiteral(String var, String value) {
		this.var = var;
		this.value = value;
		++numberOfStringLiterals;
	}
	
	
	public static int getNumberOfStringLiterals() {
		return numberOfStringLiterals;
	}
	
	public void assignAddress(int address) {
		if (this.address != -1) {
			throw new RuntimeException("Attempt to assign an address twice to " + this);
		}
		this.address = address;
	}
	
	public int getAddress() {
		return address;
	}
	
	public String toString() {
		return var + ": " + value;
	}
}