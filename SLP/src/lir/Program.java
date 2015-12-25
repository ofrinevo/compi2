package lir;

import java.util.*;


public class Program {
	public final List<Object> data;
	public final List<Instruction> instructions;
	
	public Program(List<Object> strings, List<Instruction> instructions) {
		this.data = strings;
		this.instructions = instructions;
	}
}