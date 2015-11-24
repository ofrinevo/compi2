package slp;


/** Pretty-prints an SLP AST.
 */
public class PrettyPrinter implements Visitor {
	protected final ASTNode root;
	
	/** Constructs a printin visitor from an AST.
	 * 
	 * @param root The root of the AST.
	 */
	public PrettyPrinter(ASTNode root) {
		this.root = root;
	}

	/** Prints the AST with the given root.
	 */
	public void print() {
		root.accept(this);
	}
	
	
	public void visit(ICClass icClass) {
		StringBuffer output = new StringBuffer();
		
		
		output.append("Declaration of class: " + icClass.getName());
		if (icClass.hasSuperClass())
			output.append(", subclass of " + icClass.getSuperClassName());
		System.out.println(output.toString()); 
		output = new StringBuffer();
		for (Field field : icClass.getFields())
			field.accept(this);
		for (Method method : icClass.getMethods())
			method.accept(this);
		
		System.out.print(output.toString()); 
	}
	
	public void visit(StmtList stmts) {
		for (Stmt s : stmts.statements) {
			s.accept(this);
			System.out.println();
		}
	}

	public void visit(Stmt stmt) {
		throw new UnsupportedOperationException("Unexpected visit of Stmt abstract class");
	}
	
	public void visit(PrintStmt stmt) {
		System.out.print("print(");
		stmt.expr.accept(this);
		System.out.print(");");
	}
	
	public void visit(AssignStmt stmt) {
		stmt.varExpr.accept(this);
		System.out.print("=");
		stmt.rhs.accept(this);
		System.out.print(";");
	}
	
	public void visit(Expr expr) {
		throw new UnsupportedOperationException("Unexpected visit of Expr abstract class");
	}	


	public void visit(ReadIExpr expr) {
		System.out.print("readi()");
	}	
	
	public void visit(VarExpr expr) {
		System.out.print(expr.name);
	}
	
	public void visit(NumberExpr expr) {
		System.out.print(expr.value);
	}
	
	public void visit(UnaryOpExpr expr) {
		System.out.print(expr.op);
		expr.operand.accept(this);
	}
	
	public void visit(BinaryOpExpr expr) {
		expr.lhs.accept(this);
		System.out.print(expr.op);
		expr.rhs.accept(this);
	}

	
	public void visit(Assign assignment) {
		System.out.print(assignment.toString());
		
	}
	public void visit(Literal literal) {
		StringBuffer output = new StringBuffer();

		
		output.append(literal.getType().getDescription() + ": "
				+ literal.getType().toFormattedString(literal.getValue()));
		
		String out = output.toString();
		if (literal.getType() == LiteralTypes.STRING)
		{
			out = out.toString().replaceFirst("\"", "").replaceFirst("\"$", "");
		}
		System.out.println(out); 
	}
	public void visit(ArrayLocation location) {
		StringBuffer output = new StringBuffer();

	
		output.append("Reference to array");
	
		location.getArray().accept(this);
		location.getIndex().accept(this);
		
		System.out.print(output.toString()); 
	}
	public void visit(PrimitiveType type) {
		StringBuffer output = new StringBuffer();

		output.append("Primitive data type: ");
		if (type.getDimension() > 0)
			output.append(type.getDimension() + "-dimensional array of ");
		output.append(type.getName());
		System.out.println(output.toString()); 
	}
	
	public void visit(Field field) {
		StringBuffer output = new StringBuffer();

		
		output.append("Declaration of field: " + field.getName());
		
		field.getType().accept(this);
		
		System.out.println(output.toString()); 
	}

	@Override
	public void visit(UserType type) {
		StringBuffer output = new StringBuffer();

		output.append("User-defined data type: ");
		if (type.getDimension() > 0)
			output.append(type.getDimension() + "-dimensional array of ");
		output.append(type.getName());
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(Formal formal) {
		StringBuffer output = new StringBuffer();
		output.append("Parameter: " + formal.getName());
		formal.getType().accept(this);
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(Program program) {
		StringBuffer output = new StringBuffer();
		output.append("Abstract Syntax Tree: " +  "\n");
		for (ICClass icClass : program.getClasses())
			icClass.accept(this);
		System.out.println(output.toString()); 
		
	}


	public void visit(StaticMethod method) {
		StringBuffer output = new StringBuffer();

		
		output.append("Declaration of static method: " + method.getName());
		
		method.getType().accept(this);
		for (Formal formal : method.getFormals())
			formal.accept(this);
		for (Stmt statement : method.getStatements())
		statement.accept(this);
		
		System.out.println(output.toString()); 
	}

	@Override
	public void visit(VirtualMethod method) {
		StringBuffer output = new StringBuffer();
		output.append("Declaration of virtual method: " + method.getName());
		System.out.println(output.toString()); 
		output = new StringBuffer();
		method.getType().accept(this);
		for (Formal formal : method.getFormals())
			formal.accept(this);
		for (Stmt statement : method.getStatements())
		statement.accept(this);
		
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(LocalVariable localVariable) {
		StringBuffer output = new StringBuffer();

		
		output.append("Declaration of local variable: "
				+ localVariable.getName());
		if (localVariable.hasInitValue()) {
			output.append(", with initial value");
			
		}
		
		localVariable.getType().accept(this);
		if (localVariable.hasInitValue()) {
			localVariable.getInitValue().accept(this);
		
		}
		
		System.out.println(output.toString()); 
		
	}

	

	@Override
	public void visit(Return return1) {
		StringBuffer output = new StringBuffer();

		
		output.append("Return statement");
		if (return1.hasValue())
			output.append(", with return value");
		if (return1.hasValue()) {
			
			return1.getValue().accept(this);
			
		}
		System.out.println(output.toString()); 
		
	}

	

	@Override
	public void visit(VariableLocation location) {
		 StringBuffer output = new StringBuffer();

		
		output.append("Reference to variable: " + location.getName());
		if (location.isExternal())
			output.append(", in external scope");
		if (location.isExternal()) {
			
			location.getLocation().accept(this);
			
		}
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(If ifStatement) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("If statement");
		if (ifStatement.hasElse())
			output.append(", with Else operation");
		ifStatement.getCondition().accept(this);
		ifStatement.getStmt().accept(this);
		if (ifStatement.hasElse())
			ifStatement.getElseOperation().accept(this);
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(While whileStatement) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("While statement");
		whileStatement.getCondition().accept(this);
		whileStatement.getStmt().accept(this);
		System.out.println(output.toString()); 
	}

	@Override
	public void visit(StmtsBlock statementsBlock) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Block of statements");
		for (Stmt statement : statementsBlock.getStatements())
			statement.accept(this);
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(StaticCall call) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Call to static method: " + call.getName()
				+ ", in class " + call.getClassName());
		for (Expr argument : call.getArguments())
			argument.accept(this);
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(VirtualCall call) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Call to virtual method: " + call.getName());
		if (call.isExternal())
			output.append(", in external scope");
		if (call.isExternal())
			call.getLocation().accept(this);
		for (Expr argument : call.getArguments())
			argument.accept(this);
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(This thisExpression) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Reference to 'this' instance");
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(NewClass newClass) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Instantiation of class: " + newClass.getName());
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(NewArray newArray) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Array allocation");
		newArray.getType().accept(this);
		newArray.getSize().accept(this);
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(Length length) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Reference to array length");
		length.getArray().accept(this);
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(MathBinaryOp binaryOp) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Mathematical binary operation: "
				+ binaryOp.getOperator().getDescription());
		binaryOp.getFirstOperand().accept(this);
		binaryOp.getSecondOperand().accept(this);
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(LogicalBinaryOp binaryOp) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Logical binary operation: "
				+ binaryOp.getOperator().getDescription());
		binaryOp.getFirstOperand().accept(this);
		binaryOp.getSecondOperand().accept(this);
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(MathUnaryOp unaryOp) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Mathematical unary operation: "
				+ unaryOp.getOperator().getDescription());
		unaryOp.getOperand().accept(this);
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(LogicalUnaryOp unaryOp) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Logical unary operation: "
				+ unaryOp.getOperator().getDescription());
		unaryOp.getOperand().accept(this);
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(ExprBlock expressionBlock) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Parenthesized expression");
		expressionBlock.getExpression().accept(this);
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(LocationAssign locationAssign) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Method method) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visit(Continue continue1) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Continue statement");
		System.out.println(output.toString()); 
		
	}

	@Override
	public void visit(Break break1) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Break statement");
		System.out.println(output.toString()); 
	}

	@Override
	public void visit(CallStatement callStatement) {
		// TODO Auto-generated method stub
		StringBuffer output = new StringBuffer();

		output.append("Method call statement");
		callStatement.getCall().accept(this);
		System.out.println(output.toString()); 
		
	}
}