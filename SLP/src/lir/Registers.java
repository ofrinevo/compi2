package lir;

import java.util.HashMap;
import java.util.Map;

public class Registers {
	Map<Integer, Reg> regs;
	
	public Registers() {
		this.regs = new HashMap<Integer,Reg>();
		regs.put(-1,new Reg("Rdummy"));
	}
	
	
	public Reg request(int index) {
		if (regs.containsKey(index))
			return regs.get(index);
		else {
			regs.put(index,new Reg("R"+index));
			return regs.get(index);
		}
	}
}
