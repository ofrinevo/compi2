package lir;


public class ParamOpPair {
	public final Memory param;
	public final Operand op;
	
	public ParamOpPair(Memory param, Operand reg) {
		this.param = param;
		this.op = reg;
	}
	
	public String toString() {
		return param + "=" + op;
	}
}