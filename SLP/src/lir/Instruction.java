package lir;


public abstract class Instruction {
	public abstract void accept(Visitor c);
}