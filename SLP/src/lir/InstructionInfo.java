package lir;

import java.util.*;


public class InstructionInfo implements Visitor {
	
	public Set<Reg> registers;

	
	public Set<Label> labels;
	
	
	public Set<Memory> variables;
	
	
	public Set<Immediate> constants;
	
	
	public void getInfo(Instruction instr) {
		registers = new HashSet<Reg>();
		labels = new HashSet<Label>();
		variables = new HashSet<Memory>();
		constants = new HashSet<Immediate>();
		
		instr.accept(this);
	}
	
	
	public void processOperand(Operand op) {
		if (op instanceof Reg)
			registers.add((Reg) op);
		else if (op instanceof Memory)
			variables.add((Memory) op);
		else if (op instanceof Immediate)
			constants.add((Immediate) op);
		else if (op instanceof Label)
			labels.add((Label) op);
		else
			throw new RuntimeException("Encountered unknown type of operand: " + 
					op.getClass() + " " + op + "!");
	}
	
	public void visit(MoveInstr instr) {
		processOperand(instr.src);
		processOperand(instr.dst);
	}

	public void visit(BinOpInstr instr) {
		processOperand(instr.src);
		processOperand(instr.dst);
	}

	public void visit(CompareInstr instr) {
		processOperand(instr.src);
		processOperand(instr.dst);
	}

	public void visit(UnaryOpInstr instr) {
		processOperand(instr.dst);
	}

	public void visit(LabelInstr instr) {
		processOperand(instr.label);
	}

	public void visit(MoveArrayInstr instr) {
		processOperand(instr.base);
		processOperand(instr.mem);
		processOperand(instr.offset);
	}

	public void visit(MoveFieldInstr instr) {
		processOperand(instr.base);
		processOperand(instr.mem);
		processOperand(instr.offset);
	}
	
	public void visit(ArrayLengthInstr instr) {
		processOperand(instr.arr);
		processOperand(instr.dst);
	}	

	public void visit(JumpInstr instr) {
		processOperand(instr.label);
	}

	public void visit(CondJumpInstr instr) {
		processOperand(instr.label);
	}

	public void visit(StaticCall instr) {
		processOperand(instr.dst);
		processOperand(instr.func);
		for (ParamOpPair pop : instr.args) {
			processOperand(pop.param);
			processOperand(pop.op);
		}
	}

	public void visit(VirtualCall instr) {
		processOperand(instr.obj);
		processOperand(instr.dst);
		processOperand(instr.func);
		for (ParamOpPair pop : instr.args) {
			processOperand(pop.param);
			processOperand(pop.op);
		}
	}

	public void visit(LibraryCall instr) {
		processOperand(instr.dst);
		processOperand(instr.func);
		for (Operand op : instr.args) {
			processOperand(op);
		}
	}

	public void visit(ReturnInstr instr) {
		processOperand(instr.dst);
	}
}