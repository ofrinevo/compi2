package lir;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import classes.ASTNode;
import classes.ArrayLocation;
import classes.Assignment;
import classes.Break;
import classes.CallStatement;
import classes.Continue;
import classes.Expression;
import classes.ExpressionBlock;
import classes.Field;
import classes.Formal;
import classes.ICClass;
import classes.If;
import classes.Length;
import classes.LibraryMethod;
import classes.Literal;
import classes.LocalVariable;
import classes.LogicalBinaryOp;
import classes.LogicalUnaryOp;
import classes.MathBinaryOp;
import classes.MathUnaryOp;
import classes.Method;
import classes.NewArray;
import classes.NewClass;
import classes.PrimitiveType;
import classes.Program;
import classes.Return;
import classes.Statement;
import classes.StatementsBlock;
import classes.StaticCall;
import classes.StaticMethod;
import classes.This;
import classes.UserType;
import classes.VariableLocation;
import classes.VirtualCall;
import classes.VirtualMethod;
import classes.Visitor;
import classes.While;
import lir.*;
import symTable.IDSymbolsKinds;

public class TranslationVisitor implements Visitor{
	int target;
	StringLiterals stringLiterals;
	StringBuilder emitted;
	Map<String, ClassLayout> classLayouts;
	List<Instruction> instructions;
	private boolean[] _hasErrors;
	private final String[] _errorStrings = {
			"Runtime error: Null pointer dereference!",
			"Runtime error: Array index out of bounds!",
			"Runtime error: Array allocation with negative array size!",
			"Runtime error: Division by zero!",
	};
	Map<String,Integer> arrs;

	private boolean assignmentCall = false;

	private Queue<ASTNode> nodeHandlingQueue;
	private Registers registers;

	private Labels labelHandler;
	private Stack<String> _whileLabelStack;
	private Stack<String> _endWhileLabelStack;
	private String currentClassName;
	private Map<String, List<String>> methodFullNamesMap;	
	private IDSymbolsKinds currentMethodKind;
	private Boolean isMainMethod;
	
	public TranslationVisitor() {
		this.classLayouts = new HashMap<String,ClassLayout>();
		this.stringLiterals = new StringLiterals();
		this.emitted = new StringBuilder();
		_hasErrors = new boolean[4];
		this.instructions = new ArrayList<Instruction>();
		this.registers = new Registers();
		this.labelHandler = new Labels();

		this._whileLabelStack = new Stack<String>();
		this._endWhileLabelStack = new Stack<String>();

		this.methodFullNamesMap = new HashMap<String, List<String>>();
		this.nodeHandlingQueue = new LinkedList<ASTNode>();
		this.arrs = new HashMap<String,Integer>();
	}

	public void printInstructions() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("output.lir", "UTF-8");
			for(StringLiteral sl : stringLiterals.toStringLiteralList())
				writer.println(sl.toString());

			for(ClassLayout cl : classLayouts.values()) {
				writer.println(cl);
			}

			for (Instruction inst : instructions)
				writer.println(inst.toString());
			
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
	}

	public void translate(Program root)  {
		nodeHandlingQueue.add(root);
		ASTNode currentNode;
		while (!nodeHandlingQueue.isEmpty()) { 
			currentNode = nodeHandlingQueue.poll();
			currentNode.accept(this);
		}
	}

	@Override
	public Object visit(Program program) {
		for (ICClass iccls : program.getClasses()) 
			nodeHandlingQueue.add(iccls);

		return null;
	}

	@Override
	public Object visit(ICClass icClass) {
		ClassLayout superCl = icClass.hasSuperClass() ? 
				classLayouts.get(icClass.getSuperClassName()) : null;
				ClassLayout cl = new ClassLayout(icClass.getName(), superCl);

				for (Field field : icClass.getFields()) {
					field.accept(this);
					cl.addField(field.getName());
				}

				for (Method method : icClass.getMethods()) {
					nodeHandlingQueue.add(method);
					cl.addMethod(method.getName());
					String methodFullName = cl.getMethodString(method.getName());
					this.methodFullNamesMap.put(methodFullName, generatMethodParamsList(method));
				}
				if(!icClass.getName().equals("Library"))
					classLayouts.put(icClass.getName(), cl);
				return null;
	}

	@Override
	public Object visit(Field field) {
		return null;
	}

	@Override
	public Object visit(VirtualMethod method) {
		return visitMethod(method);
	}

	@Override
	public Object visit(StaticMethod method) {
		return visitMethod(method);
	}

	@Override
	public Object visit(LibraryMethod method) {
		return null;
	}

	private Object visitMethod(Method method)
	{
		isMainMethod = method.getName().equals("main");
		this.target = 1;
		this.currentMethodKind = method.getSymbolsTable().getEntry(method.getName()).getKind();
		currentClassName = method.getSymbolsTable().getId();
		String methodFullName = classLayouts.get(currentClassName).getMethodString(method.getName());
		instructions.add(new LabelInstr(labelHandler.requestStr(methodFullName)));

		for (Statement stmt : method.getStatements()) {
			stmt.accept(this);
		}
		
		if (isMainMethod) {
			instructions.add(new LabelInstr(labelHandler.requestStr("_PROGRAM_END")));
			List<Operand> exitSinglOperandList = new ArrayList<Operand>();
			exitSinglOperandList.add(new Immediate(0));
			instructions.add(new LibraryCall(labelHandler.requestStr("__exit"), exitSinglOperandList, registers.request(-1)));
		}
		else if (!method.doesHaveFlowWithoutReturn())
			instructions.add(new ReturnInstr(registers.request(-1)));
		isMainMethod = false;
		return null;
	}

	@Override
	public Object visit(Formal formal) {
		return null;
	}

	@Override
	public Object visit(PrimitiveType type) {
		return null;
	}

	@Override
	public Object visit(UserType type) {
		return null;
	}

	@Override
	public Object visit(Assignment assignment) {
		target++;
		assignment.getAssignment().accept(this);
		target--;
		assignmentCall=true;
		assignment.getVariable().accept(this);
		assignmentCall=false;

		return null;
	}


	@Override
	public Object visit(CallStatement callStatement) {
		callStatement.getCall().accept(this);
		return null;
	}

	@Override
	public Object visit(Return returnStatement) {
		if (!isMainMethod) {
			if(returnStatement.hasValue()) {
				returnStatement.getValue().accept(this);
				instructions.add(new ReturnInstr(registers.request(target)));
			} else {
				instructions.add(new ReturnInstr(registers.request(-1)));
			}
		}
		return null;
	}

	@Override
	public Object visit(If ifStatement) {

		ifStatement.getCondition().accept(this);
		labelHandler.increaseLabelsCounter();
		int ifLabel = labelHandler.getLabelsCounter();
		instructions.add(new CompareInstr(new Immediate(1), registers.request(target)));
		CommonLabels jumpingLabel = ifStatement.hasElse() 
				? CommonLabels.FALSE_LABEL : CommonLabels.END_LABEL;
		instructions.add(new CondJumpInstr(labelHandler.innerLabelRequest(jumpingLabel, ifLabel), Cond.False));
		ifStatement.getOperation().accept(this);
		if (ifStatement.hasElse()) {
			instructions.add(new JumpInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, ifLabel)));
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.FALSE_LABEL, ifLabel)));
			ifStatement.getElseOperation().accept(this);
		}
		instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, ifLabel)));

		return null;
	}

	@Override
	public Object visit(While whileStatement) {
		labelHandler.increaseLabelsCounter();
		int whileLabel = labelHandler.getLabelsCounter();
		instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.TEST_LABEL, whileLabel)));
		whileStatement.getCondition().accept(this);
		instructions.add(new CompareInstr(new Immediate(1), registers.request(target)));
		instructions.add(new CondJumpInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, whileLabel), Cond.False));

		this._whileLabelStack.add(CommonLabels.TEST_LABEL.toString()+whileLabel);
		this._endWhileLabelStack.add(CommonLabels.END_LABEL.toString()+whileLabel);
		whileStatement.getOperation().accept(this);
		this._whileLabelStack.pop();
		this._endWhileLabelStack.pop();
		instructions.add(new JumpInstr(labelHandler.innerLabelRequest(CommonLabels.TEST_LABEL, whileLabel)));
		instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, whileLabel)));
		return null;
	}

	@Override
	public Object visit(Break breakStatement) {
		instructions.add(new JumpInstr(labelHandler.requestStr(this._endWhileLabelStack.lastElement())));
		return null;
	}

	@Override
	public Object visit(Continue continueStatement) {
		instructions.add(new JumpInstr(labelHandler.requestStr(this._whileLabelStack.lastElement())));
		return null;
	}

	@Override
	public Object visit(StatementsBlock statementsBlock) {

		for(Statement stmnt : statementsBlock.getStatements())
		{
			stmnt.accept(this);
		}
		return null;
	}

	@Override
	public Object visit(LocalVariable localVariable) {
		if (localVariable.hasInitValue()) {
			localVariable.getInitValue().accept(this);
			instructions.add(new MoveInstr(registers.request(target), new Memory(localVariable.getSymbolEntry().getGlobalId())));
			target++;
		}
		return null;
	}

	@Override
	public Object visit(VariableLocation location) {
		if (location.isExternal()) {
			boolean tmp = assignmentCall;
			assignmentCall = false;
			location.getLocation().accept(this);
			assignmentCall = tmp;
			checkNullRefAndEmit(registers.request(target));
			String externalClsName = location.getLocation().getEntryType().toString();
			int fieldIndex = this.classLayouts.get(externalClsName).getFieldIndex(location.getName());
			if(assignmentCall)
				instructions.add(new MoveFieldInstr(registers.request(target), new Immediate(fieldIndex), registers.request(target+1), false)); 

			instructions.add(new MoveFieldInstr(registers.request(target), new Immediate(fieldIndex), registers.request(target), true));
		}
		else {
			if (location.getSymbolEntry().getKind() == IDSymbolsKinds.FIELD) {
				instructions.add(new MoveInstr(new Memory("this"), registers.request(target)));
				int fieldIndex = this.classLayouts.get(currentClassName).getFieldIndex(location.getName());
				if(assignmentCall)
					instructions.add(new MoveFieldInstr(registers.request(target), new Immediate(fieldIndex), registers.request(target+1), false)); 
				instructions.add(new MoveFieldInstr(registers.request(target), new Immediate(fieldIndex), registers.request(target), true));
			}
			else {
				Memory locationMemory = new Memory(location.getSymbolEntry().getGlobalId());
				if(assignmentCall)
					instructions.add(new MoveInstr(registers.request(target+1), locationMemory));
				instructions.add(new MoveInstr(locationMemory, registers.request(target)));
			}
		}
		return null;
	}

	@Override
	public Object visit(ArrayLocation location) {

		for(Integer cl : arrs.values()) {
			System.out.println(cl);
		}

		int assignmentTarget = target;
		target+=3;

		boolean tmp = assignmentCall;
		assignmentCall = false;
		location.getArray().accept(this);
		target--;
		location.getIndex().accept(this);
		assignmentCall = tmp;
		
		checkNullRefAndEmit(registers.request(target + 1));
		checkGTLengthAndEmit(registers.request(target),registers.request(target+1));

		checkSizeGtZeroAndEmit(registers.request(target));

		
		if(assignmentCall)
			instructions.add(new MoveArrayInstr(
					registers.request(target+1), registers.request(target),
					registers.request(assignmentTarget+1), false));
		instructions.add(new MoveArrayInstr(registers.request(target+1), registers.request(target), registers.request(assignmentTarget), true));

		target=assignmentTarget;



		return null;
	}

	private void checkGTLengthAndEmit(Reg index,Reg array)
	{
		target++;
		int labelCounter = labelHandler.increaseLabelsCounter();
		Reg currentSize=registers.request(++target);
		instructions.add(new ArrayLengthInstr(array, currentSize));	
		instructions.add(new CompareInstr(index, currentSize));
		instructions.add(new CondJumpInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter), Cond.G));
		instructions.add(new MoveInstr(new Memory("str"+stringLiterals.add(_errorStrings[1])), registers.request(target)));
		List<Operand> args = new ArrayList<Operand>();
		args.add(registers.request(target));
		instructions.add(new LibraryCall(labelHandler.requestStr("__print"), args, new Reg("Rdummy")));
		instructions.add(new JumpInstr(labelHandler.requestStr("_PROGRAM_END")));
		instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));

		target-=2;
		_hasErrors[1] = true;
	}

	@Override
	public Object visit(StaticCall call) {

		int unusedMethodTarget = target;	
		if (call.getClassName().equals("Library")) {
			List<Operand> operands = new ArrayList<Operand>();

			for (Expression arg : call.getArguments()) {
				arg.accept(this);
				operands.add(registers.request(target));
				target++;
			}
			target = unusedMethodTarget;
			int retTrarget = call.getMethodType().getReturnType().isVoidType() ? -1 : target;
			instructions.add(new LibraryCall(labelHandler.requestStr("__" + call.getName()), operands, registers.request(retTrarget)));
		} 
		else {
			List<ParamOpPair> paramOpRegs = new ArrayList<ParamOpPair>();
			String staticCallMethodFullName = classLayouts.get(call.getClassName()).getMethodString(call.getName());
			List<String> methodParams = this.methodFullNamesMap.get(staticCallMethodFullName);
			int i = 0;
			for (Expression arg : call.getArguments()) {

				arg.accept(this);
				paramOpRegs.add(new ParamOpPair(new Memory(methodParams.get(i)), registers.request(target)));
				i++;
				target++;
			}
			target = unusedMethodTarget;
			int retTrarget = call.getMethodType().getReturnType().isVoidType() ? -1 : target;
			instructions.add(new lir.StaticCall(
					labelHandler.requestStr(staticCallMethodFullName), paramOpRegs, 
					registers.request(retTrarget)));
		}
		return null;
	}

	@Override
	public Object visit(VirtualCall call) {
		if ((!call.isExternal()) && (currentMethodKind == IDSymbolsKinds.STATIC_METHOD)) {
			StaticCall staticCall = new StaticCall(call.getLine(),
					this.currentClassName, call.getName(), call.getArguments());
			staticCall.setEntryType(call.getEntryType());
			staticCall.setMethodType(call.getMethodType());
			return staticCall.accept(this);
		}
		if (call.isExternal()) {
			call.getLocation().accept(this);
		}
		else 
			instructions.add(new MoveInstr(new Memory("this"), registers.request(target)));
		
		int clsTarget = target;

		checkNullRefAndEmit(registers.request(target));

		String clsName = call.isExternal() ?
				call.getLocation().getEntryType().toString() : this.currentClassName;
				int unusedMethodTarget = target;
				target++;
				List<ParamOpPair> paramOpRegs = new ArrayList<ParamOpPair>();
				String virtualCallMethodFullName = classLayouts.get(clsName).getMethodString(call.getName());
				List<String> methodParams = this.methodFullNamesMap.get(virtualCallMethodFullName);
				int i = 0;
				for (Expression arg : call.getArguments()) {
					arg.accept(this);
					paramOpRegs.add(new ParamOpPair(new Memory(methodParams.get(i)), registers.request(target)));
					i++;
					target++;
				}
				Immediate funcIndex = new Immediate(classLayouts.get(clsName).getMethodIndex(call.getName()));
				target = unusedMethodTarget;
				int retTrarget = call.getMethodType().getReturnType().isVoidType() ? -1 : target;
				instructions.add(new lir.VirtualCall(
						registers.request(clsTarget), funcIndex, paramOpRegs, registers.request(retTrarget)));

				return null;
	}

	@Override
	public Object visit(This thisExpression) {
		instructions.add(new MoveInstr(new Memory("this"), registers.request(target)));
		return true;
	}

	@Override
	public Object visit(NewClass newClass) {
		List<Operand> args = new ArrayList<Operand>();
		args.add(new Immediate(classLayouts.get(newClass.getName()).getAllocatedSize()));
		instructions.add(new LibraryCall(labelHandler.requestStr("__allocateObject"), args , registers.request(target)));

		instructions.add(new MoveFieldInstr(registers.request(target), new Immediate(0), labelHandler.requestStr("_DV_"+newClass.getName()), false));
		return null;
	}

	@Override
	public Object visit(NewArray newArray) {

		List<Operand> args = new ArrayList<Operand>();
		target++;

		newArray.getSize().accept(this); 
		instructions.add(new BinOpInstr(new Immediate(4), registers.request(target), Operator.MUL)); //multiply size by 4
		args.add(registers.request(target--));

		checkSizeGtZeroAndEmit(args.get(0));	
		
		
		instructions.add(new LibraryCall(labelHandler.requestStr("__allocateArray"), args, registers.request(target)));
		
		return true;
	}

	private void checkSizeGtZeroAndEmit(Operand size)
	{

		int labelCounter = labelHandler.increaseLabelsCounter();
		instructions.add(new CompareInstr(new Immediate(0), size));
		instructions.add(new CondJumpInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter), Cond.GE));
		instructions.add(new MoveInstr(new Memory("str"+stringLiterals.add(_errorStrings[2])), registers.request(target)));
		List<Operand> args = new ArrayList<Operand>();
		args.add(registers.request(target));
		instructions.add(new LibraryCall(labelHandler.requestStr("__print"), args, new Reg("Rdummy")));
		instructions.add(new JumpInstr(labelHandler.requestStr("_PROGRAM_END")));
		instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));

		_hasErrors[2] = true;
	}

	@Override
	public Object visit(Length length) {
		target++;
		length.getArray().accept(this);
		target--;
		checkNullRefAndEmit(registers.request(target + 1));
		instructions.add(new ArrayLengthInstr(registers.request(target+1), registers.request(target)));

		return null;
	}

	private void checkNullRefAndEmit(Reg reg)
	{
		int labelCounter = labelHandler.increaseLabelsCounter();
		instructions.add(new CompareInstr(new Immediate(0), reg));
		instructions.add(new CondJumpInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter), Cond.False));
		instructions.add(new MoveInstr(new Memory("str"+stringLiterals.add(_errorStrings[0])), registers.request(target)));
		List<Operand> args = new ArrayList<Operand>();
		args.add(registers.request(target));
		instructions.add(new LibraryCall(labelHandler.requestStr("__print"), args , new Reg("Rdummy")));
		instructions.add(new JumpInstr(labelHandler.requestStr("_PROGRAM_END")));
		instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));

		_hasErrors[0] = true;
	}


	@Override
	public Object visit(MathBinaryOp binaryOp) {
		binaryOp.getFirstOperand().accept(this);
		target++;
		binaryOp.getSecondOperand().accept(this);
		Operator op;
		switch(binaryOp.getOperator()) {
		case PLUS:
			if(binaryOp.getFirstOperand().getEntryType().isStringType() && binaryOp.getSecondOperand().getEntryType().isStringType()){
				List<Operand> args = new ArrayList<Operand>();
				args.add(registers.request(target-1));
				args.add(registers.request(target));
				instructions.add(new LibraryCall(labelHandler.requestStr("__stringCat"), args, registers.request(--target)));
				return true;
			} else {
				op = Operator.ADD;
				break;
			}
		case MINUS:
			op = Operator.SUB;
			break;
		case DIVIDE:
			op = Operator.DIV;
			break;
		case MULTIPLY:
			op = Operator.MUL;
			break;
		case MOD:
			op = Operator.MOD;
			break;
		default:
			return false;
		}
		Reg leftReg=registers.request(target);
		Reg rightReg=registers.request(--target);

		if(binaryOp.getOperator()==slp.BinaryOps.DIVIDE || binaryOp.getOperator()==slp.BinaryOps.MOD)
			divisionCheck(leftReg);

		instructions.add(new BinOpInstr(leftReg, rightReg, op));
		return true;
	}

	@Override
	public Object visit(LogicalBinaryOp binaryOp) {

		int labelCounter =labelHandler.increaseLabelsCounter();
		switch(binaryOp.getOperator()) {
		case GT:
			binaryOp.getSecondOperand().accept(this);
			target++;
			binaryOp.getFirstOperand().accept(this);
			instructions.add(new CompareInstr(registers.request(--target), registers.request(target+1)));
			instructions.add(new CondJumpInstr(labelHandler.innerLabelRequest(CommonLabels.TRUE_LABEL,labelCounter), Cond.G));
			instructions.add(new MoveInstr(new Immediate(0), registers.request(target)));
			instructions.add(new JumpInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL,labelCounter)));
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.TRUE_LABEL,labelCounter)));
			instructions.add(new MoveInstr(new Immediate(1), registers.request(target)));
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL,labelCounter)));
			break;
		case GTE:
			binaryOp.getSecondOperand().accept(this);
			target++;
			binaryOp.getFirstOperand().accept(this);
			instructions.add(new CompareInstr(registers.request(--target), registers.request(target+1)));
			instructions.add(new CondJumpInstr(labelHandler.innerLabelRequest(CommonLabels.TRUE_LABEL,labelCounter), Cond.GE));
			instructions.add(new MoveInstr(new Immediate(0), registers.request(target)));
			instructions.add(new JumpInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL,labelCounter)));
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.TRUE_LABEL, labelCounter)));
			instructions.add(new MoveInstr(new Immediate(1), registers.request(target)));
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));
			break;
		case EQUAL:
			binaryOp.getSecondOperand().accept(this);
			target++;
			binaryOp.getFirstOperand().accept(this);
			instructions.add(new CompareInstr(registers.request(--target), registers.request(target+1)));
			instructions.add(new CondJumpInstr(labelHandler.innerLabelRequest(CommonLabels.TRUE_LABEL, labelCounter), Cond.True));
			instructions.add(new MoveInstr(new Immediate(0), registers.request(target)));
			instructions.add(new JumpInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.TRUE_LABEL, labelCounter)));
			instructions.add(new MoveInstr(new Immediate(1), registers.request(target)));
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));
			break;
		case NEQUAL:
			binaryOp.getSecondOperand().accept(this);
			target++;
			binaryOp.getFirstOperand().accept(this);
			instructions.add(new CompareInstr(registers.request(--target), registers.request(target+1)));
			instructions.add(new CondJumpInstr(labelHandler.innerLabelRequest(CommonLabels.TRUE_LABEL, labelCounter), Cond.False));
			instructions.add(new MoveInstr(new Immediate(0), registers.request(target)));
			instructions.add(new JumpInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.TRUE_LABEL, labelCounter)));
			instructions.add(new MoveInstr(new Immediate(1), registers.request(target)));
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));
			break;
		case LT:
			binaryOp.getSecondOperand().accept(this);
			target++;
			binaryOp.getFirstOperand().accept(this);
			instructions.add(new CompareInstr(registers.request(--target), registers.request(target+1)));
			instructions.add(new CondJumpInstr(labelHandler.innerLabelRequest(CommonLabels.TRUE_LABEL, labelCounter), Cond.L));
			instructions.add(new MoveInstr(new Immediate(0), registers.request(target)));
			instructions.add(new JumpInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.TRUE_LABEL, labelCounter)));
			instructions.add(new MoveInstr(new Immediate(1), registers.request(target)));
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));
			break;
		case LTE:
			binaryOp.getSecondOperand().accept(this);
			target++;
			binaryOp.getFirstOperand().accept(this);
			instructions.add(new CompareInstr(registers.request(--target), registers.request(target+1)));
			instructions.add(new CondJumpInstr(labelHandler.innerLabelRequest(CommonLabels.TRUE_LABEL, labelCounter), Cond.LE));
			instructions.add(new MoveInstr(new Immediate(0), registers.request(target)));
			instructions.add(new JumpInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));;
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.TRUE_LABEL, labelCounter)));
			instructions.add(new MoveInstr(new Immediate(1), registers.request(target)));
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));
			break;
		case LAND:
			binaryOp.getFirstOperand().accept(this);
			instructions.add(new CompareInstr(new Immediate(0), registers.request(target)));
			instructions.add(new CondJumpInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter), Cond.True));
			target++;
			binaryOp.getSecondOperand().accept(this);
			instructions.add(new BinOpInstr(registers.request(--target), registers.request(target+1), Operator.AND));
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));
			break;
		case LOR:
			binaryOp.getFirstOperand().accept(this);
			instructions.add(new CompareInstr(new Immediate(1), registers.request(target)));
			instructions.add(new CondJumpInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter), Cond.True));
			binaryOp.getSecondOperand().accept(this);
			
			instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));
			break;
		default:

		}

		return null;
	}

	@Override
	public Object visit(MathUnaryOp unaryOp) {
		target++;
		unaryOp.getOperand().accept(this);
		target--;
		instructions.add(new MoveInstr(new Immediate(0), registers.request(target)));
		instructions.add(new BinOpInstr(registers.request(target+1), registers.request(target), Operator.SUB));
		return null;
	}

	@Override
	public Object visit(LogicalUnaryOp unaryOp) {
		target++;
		unaryOp.getOperand().accept(this);
		target--;
		instructions.add(new MoveInstr(new Immediate(1), registers.request(target)));
		instructions.add(new BinOpInstr(registers.request(target+1), registers.request(target), Operator.SUB));
		return null;
	}

	@Override
	public Object visit(Literal literal) {
		switch(literal.getType()) {
		case STRING:
			instructions.add(new MoveInstr(new Memory("str"+stringLiterals.add((String)literal.getValue())), registers.request(target)));
			break;
		case INTEGER:
			instructions.add(new MoveInstr(new Immediate((Integer)literal.getValue()), registers.request(target)));
			break;
		case TRUE:
			instructions.add(new MoveInstr(new Immediate(1), registers.request(target)));
			break;
		case FALSE:
			instructions.add(new MoveInstr(new Immediate(0), registers.request(target)));
			break;
		case NULL:
			instructions.add(new MoveInstr(new Immediate(0), registers.request(target)));
			break;
		default:

		}
		return null; 
	}

	@Override
	public Object visit(ExpressionBlock expressionBlock) {
		expressionBlock.getExpression().accept(this);

		return null;
	}

	public void emit(String s) {
		emitted.append(s+"\n");
	}

	public String getEmissionString()
	{
		return this.emitted.toString();
	}

	private List<String> generatMethodParamsList(Method method) {
		List<String> output = new ArrayList<String>();
		for (Formal formal : method.getFormals()) 
			output.add(formal.getSymbolEntry().getGlobalId());
		return output;
	}

	private void divisionCheck(Reg reg) {
		_hasErrors[3] = true;

		int labelCounter = labelHandler.increaseLabelsCounter();
		instructions.add(new CompareInstr(new Immediate(0), reg));
		instructions.add(new CondJumpInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter), Cond.False));
		instructions.add(new MoveInstr(new Memory("str"+stringLiterals.add(_errorStrings[3])), registers.request(target)));
		List<Operand> args = new ArrayList<Operand>();
		args.add(registers.request(target));
		instructions.add(new LibraryCall(labelHandler.requestStr("__print"), args , new Reg("Rdummy")));
		instructions.add(new JumpInstr(labelHandler.requestStr("_PROGRAM_END")));
		instructions.add(new LabelInstr(labelHandler.innerLabelRequest(CommonLabels.END_LABEL, labelCounter)));

	}



}
