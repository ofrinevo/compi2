package symbolTable;

import java.util.LinkedList;
import java.util.Queue;

import classes.*;
import semanticAnalysis.SemanticError;
import semanticAnalysis.SemanticErrorThrower;
import type.*;


public class SymbolsTableBuilder implements Visitor {
	
	private Queue<ASTNode> nodeHandlingQueue; // for BFS scanning
	private SymbolTable rootSymbolTable; /// a pointer to the GLOBAL symbol table

	private SymbolTable currentClassSymbolTablePoint; // for searching scope of variables which
	// called from external class location.
		
	private type.Type currentMethodType; // for the return statement to have the method type
	// which it returns value to.
	
	private TypeTable typeTable;
	
	private SemanticErrorThrower semanticErrorThrower; 
	
	int blockCounter; // for giving unique IDs to statements block symbol tables.
	
	
	public SymbolsTableBuilder(TypeTable typeTable, String tableId) {
		this.nodeHandlingQueue = new LinkedList<ASTNode>();
		this.rootSymbolTable = new SymbolTable(tableId, SymbolTableTypes.GLOBAL);
		this.currentClassSymbolTablePoint = null;
		this.currentMethodType = null;
		this.typeTable = typeTable;
		
		this.semanticErrorThrower = null;
	}

	
	public SymbolTable getSymbolTable() {
		return rootSymbolTable;
	}
	
	
	public void buildSymbolTables(Program root) throws SemanticError {
		nodeHandlingQueue.add(root);
		ASTNode currentNode;
		this.blockCounter = 0;
		while (!nodeHandlingQueue.isEmpty()) { 
			// BFS queue scan. The queue allows to scan all the classes, then all the fields and then all the methods.
			// The statements inside a method will be scanned "deeply" (DFS) and will not be added to the queue.
			currentNode = nodeHandlingQueue.poll();
			if (!(Boolean)currentNode.accept(this)) 
				semanticErrorThrower.execute();
		}
	}
	
	@Override
	public Object visit(Program program) {
		SymbolTable programSymbolTable = this.rootSymbolTable;
		for (ICClass iccls : program.getClasses()) {
			nodeHandlingQueue.add(iccls);
			if (!addEntryAndCheckDuplication(programSymbolTable, 
					new SymbolEntry(iccls.getName(), typeTable.getClassType(iccls.getName()), 
							IDSymbolsKinds.CLASS))) {
				this.semanticErrorThrower = new SemanticErrorThrower(
						iccls.getLine(), "class " + iccls.getName() + " is declared more than once");
				return false;
			}
			iccls.setEntryType(typeTable.getClassType(iccls.getName()));
			SymbolTable icclsParentSymbolTable;
			if (iccls.hasSuperClass()){ 
				icclsParentSymbolTable = programSymbolTable.findChildSymbolTable(iccls.getSuperClassName());
				if(iccls.getSuperClassName().equals("Library")){
					this.semanticErrorThrower = new SemanticErrorThrower(
							iccls.getLine(), "class " + iccls.getName() + " is extending the class Library");
					return false;
				}
			}
			else
				icclsParentSymbolTable = programSymbolTable;
			iccls.setSymbolsTable(icclsParentSymbolTable);
			
			SymbolTable currentClassSymbolTable = new SymbolTable(iccls.getName(), SymbolTableTypes.CLASS);
			currentClassSymbolTable.setParentSymbolTable(icclsParentSymbolTable);
			icclsParentSymbolTable.addTableChild(iccls.getName(), currentClassSymbolTable);
		}
		return true;
	}

	@Override
	public Object visit(ICClass icClass) {
		SymbolTable currentClassSymbolTable = this.rootSymbolTable.findChildSymbolTable(icClass.getName());
		for (Field field : icClass.getFields()) {
			nodeHandlingQueue.add(field);
			type.Type fieldType = typeTable.getTypeFromASTTypeNode(field.getType());
			if (!addEntryAndCheckDuplication(currentClassSymbolTable, 
					new SymbolEntry(field.getName(), fieldType, IDSymbolsKinds.FIELD))) {
				this.semanticErrorThrower = new SemanticErrorThrower(
						field.getLine(), "field " + field.getName() + " is declared more than once");
				return false;
			}
			field.setEntryType(fieldType);
			field.setSymbolsTable(currentClassSymbolTable);
		}
		
		for (Method method : icClass.getMethods()) {
			nodeHandlingQueue.add(method);
			type.Type methodType = typeTable.getMethodType(method);
			if (!addEntryAndCheckDuplication(currentClassSymbolTable, 
					new SymbolEntry(method.getName(), methodType, getMethodKind(method)))) {
				this.semanticErrorThrower = new SemanticErrorThrower(
						method.getLine(), "method " + method.getName() + " is declared more than once");
				return false;
			}
			method.setEntryType(methodType);
			method.setSymbolsTable(currentClassSymbolTable);
			SymbolTable currentMethodSymbolTable = new SymbolTable(method.getName(), SymbolTableTypes.METHOD);
			currentMethodSymbolTable.setParentSymbolTable(currentClassSymbolTable);
			currentClassSymbolTable.addTableChild(method.getName(), currentMethodSymbolTable);
		}
		
		return true;
	}

	@Override
	public Object visit(Field field) {
		return true;
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
		return visitMethod(method);
	}

	@Override
	public Object visit(Formal formal) {
		type.Type formalType = typeTable.getTypeFromASTTypeNode(formal.getType());
		if (!addEntryAndCheckDuplication(formal.getSymbolsTable(), 
				new SymbolEntry(formal.getName(), formalType, IDSymbolsKinds.FORMAL))) {
			this.semanticErrorThrower = new SemanticErrorThrower(
					formal.getLine(), "formal " + formal.getName() + " is declared more than once");
			return false;
		}
		formal.setEntryType(formalType);
		return true;
	}

	@Override
	public Object visit(PrimitiveType type) {
		// not called
		return null;
	}

	@Override
	public Object visit(UserType type) {
		// not called
		return null;
	}

	@Override
	public Object visit(Assignment assignment) {
		assignment.getVariable().setSymbolsTable(assignment.getSymbolsTable());
		if (!(Boolean)assignment.getVariable().accept(this))
			return false;
		assignment.getAssignment().setSymbolsTable(assignment.getSymbolsTable());
		if (!(Boolean)assignment.getAssignment().accept(this))
			return false;
		
		return true;
	}

	@Override
	public Object visit(CallStatement callStatement) {
		callStatement.getCall().setSymbolsTable(callStatement.getSymbolsTable());
		if (!(Boolean)callStatement.getCall().accept(this))
			return false;
		
		return true;
	}

	@Override
	public Object visit(Return returnStatement) {
		if (returnStatement.hasValue()) {
			returnStatement.getValue().setSymbolsTable(returnStatement.getSymbolsTable());
			if (!(Boolean)returnStatement.getValue().accept(this))
				return false;
		}
		
		returnStatement.setMethodType(this.currentMethodType);
		return true;
	}

	@Override
	public Object visit(If ifStatement) {
		ifStatement.getCondition().setSymbolsTable(ifStatement.getSymbolsTable());
		if (!(Boolean)ifStatement.getCondition().accept(this))
			return false;
		ifStatement.getOperation().setSymbolsTable(ifStatement.getSymbolsTable());
		if (!(Boolean)ifStatement.getOperation().accept(this))
			return false;
		if (ifStatement.hasElse()) {
			ifStatement.getElseOperation().setSymbolsTable(ifStatement.getSymbolsTable());
			if (!(Boolean)ifStatement.getElseOperation().accept(this))
				return false;
		}
		return true;
	}

	@Override
	public Object visit(While whileStatement) {
		whileStatement.getCondition().setSymbolsTable(whileStatement.getSymbolsTable());
		if (!(Boolean)whileStatement.getCondition().accept(this))
			return false;
		whileStatement.getOperation().setSymbolsTable(whileStatement.getSymbolsTable());
		if (!(Boolean)whileStatement.getOperation().accept(this))
			return false;
		
		return true;
	}

	@Override
	public Object visit(Break breakStatement) {
		return true;
	}

	@Override
	public Object visit(Continue continueStatement) {
		return true;
	}

	@Override
	public Object visit(StatementsBlock statementsBlock) {
		this.blockCounter++;
		SymbolTable blockStmntSymbolTable = new SymbolTable("block#" + blockCounter, SymbolTableTypes.STATEMENT_BLOCK);
		statementsBlock.getSymbolsTable().addTableChild(
				blockStmntSymbolTable.getId(), blockStmntSymbolTable);
		blockStmntSymbolTable.setParentSymbolTable(statementsBlock.getSymbolsTable());
		for (Statement stmnt : statementsBlock.getStatements()) {
			stmnt.setSymbolsTable(blockStmntSymbolTable);
			if (!(Boolean)stmnt.accept(this))
				return false;
		}
		
		return true;
	}

	@Override
	public Object visit(LocalVariable localVariable) {
		if (localVariable.hasInitValue()) {
			localVariable.getInitValue().setSymbolsTable(localVariable.getSymbolsTable());
			if (!(Boolean)localVariable.getInitValue().accept(this))
				return false;
		}
		
		type.Type localVarType = typeTable.getTypeFromASTTypeNode(localVariable.getType());
		if (!addEntryAndCheckDuplication(localVariable.getSymbolsTable(), 
				new SymbolEntry(localVariable.getName(), localVarType, IDSymbolsKinds.VARIABLE))) {
			this.semanticErrorThrower = new SemanticErrorThrower(localVariable.getLine(),
					"variable " + localVariable.getName() + " is initialized more than once");
			
			return false;
		}
		
		localVariable.setEntryType(localVarType);
		return true;
	}

	@Override
	public Object visit(VariableLocation location) {
		SymbolEntry varEntry;
		if (location.isExternal()) {
			location.getLocation().setSymbolsTable(location.getSymbolsTable());
			if (!(Boolean)location.getLocation().accept(this))
				return false;
			varEntry = getVariableSymbolEntry(location.getName(), this.currentClassSymbolTablePoint);
			if (varEntry == null) {
				this.semanticErrorThrower = new SemanticErrorThrower(location.getLine(),
						"variable " + location.getName() + " is not initialized");
				return false;
			}
		}
		else {
			varEntry = getVariableSymbolEntry(location.getName(),  location.getSymbolsTable());
			if (varEntry == null) {
				this.semanticErrorThrower = new SemanticErrorThrower(location.getLine(),
						"variable " + location.getName() + " is not initialized");
				return false;
			}
			if (varEntry.getType().isClassType()) 
				this.currentClassSymbolTablePoint = this.rootSymbolTable.findChildSymbolTable(varEntry.getType().toString());	
		}
		location.setEntryType(varEntry.getType());;
		return true;
	}

	@Override
	public Object visit(ArrayLocation location) {
		location.getArray().setSymbolsTable(location.getSymbolsTable());
		if (!(Boolean)location.getArray().accept(this))
			return false;

		location.getIndex().setSymbolsTable(location.getSymbolsTable());
		if (!(Boolean)location.getIndex().accept(this))
			return false;

		return true;
	}

	@Override
	public Object visit(StaticCall call) {
		SymbolTable clsSymbolTable = this.rootSymbolTable.findChildSymbolTable(call.getClassName());
		if (clsSymbolTable == null) {
			this.semanticErrorThrower = new SemanticErrorThrower(call.getLine(),
					"the class " + call.getClassName() + " dosen't exist");
			return false;
		}
		SymbolEntry methodEntry = getMethodSymbolEntryFromExternalCall(call.getName(), IDSymbolsKinds.STATIC_METHOD, clsSymbolTable);
		if(methodEntry == null) {
			this.semanticErrorThrower = new SemanticErrorThrower(call.getLine(),
					"the method " + call.getName() + " dosen't exist");
			return false;
		}
		call.setMethodType(methodEntry.getType());
		for (Expression arg : call.getArguments()) {
			arg.setSymbolsTable(call.getSymbolsTable());
			if (!(Boolean)arg.accept(this))
				return false;
		}
		
		return true;
	}

	@Override
	public Object visit(VirtualCall call) {
		SymbolEntry methodEntry;
		if (call.isExternal()) {
			call.getLocation().setSymbolsTable(call.getSymbolsTable());
			if (!(Boolean)call.getLocation().accept(this))
				return false;
			methodEntry = getMethodSymbolEntryFromExternalCall(call.getName(), IDSymbolsKinds.VIRTUAL_METHOD, this.currentClassSymbolTablePoint);
		}
		else 
			methodEntry = getMethodSymbolEntryFromInternalCall(call.getName(), call.getSymbolsTable());

		if(methodEntry == null) {
			this.semanticErrorThrower = new SemanticErrorThrower(call.getLine(),
					"the method " + call.getName() + " dosen't exist");
			return false;
		}
		call.setMethodType(methodEntry.getType());
		for (Expression arg : call.getArguments()) {
			arg.setSymbolsTable(call.getSymbolsTable());
			if (!(Boolean)arg.accept(this))
				return false;
		}
		
		return true;
	}

	@Override
	public Object visit(This thisExpression) {
		SymbolTable bottomSymbolTable = thisExpression.getSymbolsTable();
		while (bottomSymbolTable.getId().contains("block#")) 
			bottomSymbolTable = bottomSymbolTable.getParentSymbolTable();
		
		bottomSymbolTable = bottomSymbolTable.getParentSymbolTable();
		this.currentClassSymbolTablePoint = bottomSymbolTable;
		
		return true;
	}

	@Override
	public Object visit(NewClass newClass) {
		SymbolTable clsSymbolTable = this.rootSymbolTable.findChildSymbolTable(newClass.getName());
		if (clsSymbolTable == null) {
			this.semanticErrorThrower = new SemanticErrorThrower(newClass.getLine(),
					"the class " + newClass.getName() + " dosen't exist");
			return false;
		}
		
		this.currentClassSymbolTablePoint = clsSymbolTable;
		return true;
	}

	@Override
	public Object visit(NewArray newArray) {
		newArray.getSize().setSymbolsTable(newArray.getSymbolsTable());
		if (!(Boolean)newArray.getSize().accept(this))
			return false;

		return true;
	}

	@Override
	public Object visit(Length length) {
		length.getArray().setSymbolsTable(length.getSymbolsTable());
		if (!(Boolean)length.getArray().accept(this))
			return false;
		
		return true;
	}

	@Override
	public Object visit(MathBinaryOp binaryOp) {
		binaryOp.getFirstOperand().setSymbolsTable(binaryOp.getSymbolsTable());
		if(!(Boolean)binaryOp.getFirstOperand().accept(this))
			return false;
		binaryOp.getSecondOperand().setSymbolsTable(binaryOp.getSymbolsTable());
		if(!(Boolean)binaryOp.getSecondOperand().accept(this))
			return false;

		return true;
	}

	@Override
	public Object visit(LogicalBinaryOp binaryOp) {
		binaryOp.getFirstOperand().setSymbolsTable(binaryOp.getSymbolsTable());
		if(!(Boolean)binaryOp.getFirstOperand().accept(this))
			return false;
		binaryOp.getSecondOperand().setSymbolsTable(binaryOp.getSymbolsTable());
		if(!(Boolean)binaryOp.getSecondOperand().accept(this))
			return false;
		
		return true;
	}

	@Override
	public Object visit(MathUnaryOp unaryOp) {
		unaryOp.getOperand().setSymbolsTable(unaryOp.getSymbolsTable());
		if(!(Boolean)unaryOp.getOperand().accept(this))
			return false;
		
		return true;
	}

	@Override
	public Object visit(LogicalUnaryOp unaryOp) {
		unaryOp.getOperand().setSymbolsTable(unaryOp.getSymbolsTable());
		if(!(Boolean)unaryOp.getOperand().accept(this))
			return false;
		
		return true;
	}

	@Override
	public Object visit(Literal literal) {
		return true;
	}

	@Override
	public Object visit(ExpressionBlock expressionBlock) {
		expressionBlock.getExpression().setSymbolsTable(expressionBlock.getSymbolsTable());
		if (!(Boolean)expressionBlock.getExpression().accept(this))
			return false;

		return true;
	}
	
	
	private Object visitMethod(Method method) {
		SymbolTable currentMethodSymbolTable = method.getSymbolsTable().findChildSymbolTable(
				method.getName());
		this.currentMethodType = method.getEntryType();
		for (Formal formal : method.getFormals()) {
			formal.setSymbolsTable(currentMethodSymbolTable);
			if (!(Boolean)formal.accept(this))
				return false;
		}
		
		for (Statement stmnt : method.getStatements()) {
			stmnt.setSymbolsTable(currentMethodSymbolTable);
			if(!(Boolean)stmnt.accept(this))
				return false;
		}
		
		return true;
	}
	
	
	private Boolean addEntryAndCheckDuplication(SymbolTable table, SymbolEntry entry) {
		if (table.hasEntry(entry.getId()))
			return false;
		if (entry.getKind() == IDSymbolsKinds.FIELD) {
			SymbolTable scanningTable = table.getParentSymbolTable();
			while (scanningTable.getTableType() != SymbolTableTypes.GLOBAL) {
				if (scanningTable.hasEntry(entry.getId()))
					return false;
				scanningTable = scanningTable.getParentSymbolTable();
			}
		}
		if (entry.getKind().isMethodKind()) {
			SymbolTable scanningTable = table.getParentSymbolTable();
			while (scanningTable.getTableType() != SymbolTableTypes.GLOBAL) {
				if (scanningTable.hasEntry(entry.getId())) {
					if (!scanningTable.getEntry(entry.getId()).getType().equals(entry.getType()))
						return false;
					else
						break;
				}
			}
		}
		table.addEntry(entry.getId(), entry);
		
		return true;
	}
	
	
	private IDSymbolsKinds getMethodKind(Method method) {
		if (method instanceof VirtualMethod)
			return IDSymbolsKinds.VIRTUAL_METHOD;
		
		return IDSymbolsKinds.STATIC_METHOD;
	}
	
	
	private SymbolEntry getVariableSymbolEntry(String name, SymbolTable bottomSymbolTable) {
		if ((bottomSymbolTable.getTableType() == SymbolTableTypes.METHOD) || 
				(bottomSymbolTable.getTableType() == SymbolTableTypes.STATEMENT_BLOCK)) {
			// Climbing the symbol table tree up to the symbol table of the method from which contains the variable.
			while (bottomSymbolTable.getTableType().equals(SymbolTableTypes.STATEMENT_BLOCK)) {
				if (bottomSymbolTable.hasEntry(name))
					return bottomSymbolTable.getEntry(name);
				bottomSymbolTable = bottomSymbolTable.getParentSymbolTable();
			}
			
			// Checking method table:
			if (bottomSymbolTable.hasEntry(name))
				return bottomSymbolTable.getEntry(name);
			
			String containigMethodName = bottomSymbolTable.getId();
			bottomSymbolTable = bottomSymbolTable.getParentSymbolTable();
			
			// If a variable was called from a static method, it can't be a filed.
			// There so, it must be initialized in one of the method parameters or inside the method.
			// In that case, the check is done and the entry was not found.
			if (bottomSymbolTable.getEntry(containigMethodName).getKind() == 
					IDSymbolsKinds.STATIC_METHOD)
				return null;
		}
		
		// Checking class tables for a suitable field:
		while (bottomSymbolTable.getTableType() != SymbolTableTypes.GLOBAL) {
			SymbolTable clsTable = bottomSymbolTable;
			if (clsTable.hasEntry(name))
				if (clsTable.getEntry(name).getKind() == IDSymbolsKinds.FIELD)
					return clsTable.getEntry(name);
			bottomSymbolTable = bottomSymbolTable.getParentSymbolTable();
		}

		return null;
	}
	
	
	private SymbolEntry getMethodSymbolEntryFromExternalCall(
			String name, IDSymbolsKinds methodKind, SymbolTable bottomClassSymbolTable) {
		while (bottomClassSymbolTable != null) {
			if (bottomClassSymbolTable.hasEntry(name))
				if (bottomClassSymbolTable.getEntry(name).getKind() == methodKind) // An external method mast be consistent with the call type.
					return bottomClassSymbolTable.getEntry(name);
			bottomClassSymbolTable = bottomClassSymbolTable.getParentSymbolTable();
		}
		return null;
	}
	
	
	private SymbolEntry getMethodSymbolEntryFromInternalCall(
			String name, SymbolTable bottomSymbolTable) {
		
		// Climbing the symbol table tree up to the symbol table of the method from which the call was executed.
		while (bottomSymbolTable.getTableType().equals(SymbolTableTypes.STATEMENT_BLOCK)) 
			bottomSymbolTable = bottomSymbolTable.getParentSymbolTable();
		
		// Identifying kind of the method from which the call was executed (static or virtual).
		IDSymbolsKinds scopeMethodKind = bottomSymbolTable.getParentSymbolTable().
				getEntry(bottomSymbolTable.getId()).getKind();
		SymbolTable bottomClassSymbolTable = bottomSymbolTable = bottomSymbolTable.getParentSymbolTable();
		while (bottomClassSymbolTable.getTableType() != SymbolTableTypes.GLOBAL) {
			if (bottomClassSymbolTable.hasEntry(name)) {
				if (bottomClassSymbolTable.getEntry(name).getKind().isMethodKind()) { //found a method with same name
					if (scopeMethodKind == IDSymbolsKinds.VIRTUAL_METHOD) // from a virtual method the call can be to both static and virtual methods.
						return bottomClassSymbolTable.getEntry(name);
					if (scopeMethodKind == IDSymbolsKinds.STATIC_METHOD) { // from a static method the call must be to a static method.
						if (bottomClassSymbolTable.getEntry(name).getKind() == IDSymbolsKinds.STATIC_METHOD)
							return bottomClassSymbolTable.getEntry(name);
						else
							return null; // calling to a virtual method in a virtual call without external scope from a static call is illegal.
					}
				}
					
			}
			bottomClassSymbolTable = bottomClassSymbolTable.getParentSymbolTable();
		}
		return null;
	}
}
