package slp;

/**
 * Pretty-prints an SLP AST.
 */
public class PrettyPrinter implements Visitor {
	protected final ASTNode root;
	private String ICFilePath;

	/**
	 * Constructs a printin visitor from an AST.
	 * 
	 * @param root
	 *            The root of the AST.
	 */
	public PrettyPrinter(ASTNode root,String ICFilePath) {
		this.root = root;
		this.ICFilePath = ICFilePath;
	}

	/**
	 * Prints the AST with the given root.
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

		for (Field field : icClass.getFields())
			field.accept(this);
		for (Method method : icClass.getMethods())
			method.accept(this);

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
		System.out.println(");");
	}

	public void visit(AssignStmt assignment) {
		// stmt.varExpr.accept(this);
		// System.out.print("=");
		// stmt.rhs.accept(this);
		// System.out.println(";");

	}

	public void visit(Expr expr) {
		throw new UnsupportedOperationException("Unexpected visit of Expr abstract class");
	}

	public void visit(ReadIExpr expr) {
		System.out.println("readi()");
	}

	public void visit(VarExpr expr) {
		System.out.println(expr.name);
	}

	public void visit(NumberExpr expr) {
		System.out.println(expr.value);
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
		StringBuffer output = new StringBuffer();

		output.append("Assignment statement");
		System.out.println(output.toString());
		assignment.variable.accept(this);
		assignment.assignment.accept(this);

	}

	public void visit(Literal literal) {
		StringBuffer output = new StringBuffer();

		output.append(
				literal.getType().getDescription() + ": " + literal.getType().toFormattedString(literal.getValue()));

		String out = output.toString();
		if (literal.getType() == LiteralTypes.STRING) {
			out = out.toString().replaceFirst("\"", "").replaceFirst("\"$", "");
		}
		System.out.println(out);
	}

	public void visit(ArrayLocation location) {
		StringBuffer output = new StringBuffer();

		output.append("Reference to array");
		System.out.println(output.toString());
		location.getArray().accept(this);
		location.getIndex().accept(this);

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
		System.out.println(output.toString());
		field.getType().accept(this);

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
		System.out.println(output.toString());
		formal.getType().accept(this);

	}

	@Override
	public void visit(Program program) {
		StringBuffer output = new StringBuffer();
		output.append("Abstract Syntax Tree: " + ICFilePath+ "\n");
		System.out.println(output.toString());
		for (ICClass icClass : program.getClasses())
			icClass.accept(this);

	}

	public void visit(StaticMethod method) {
		StringBuffer output = new StringBuffer();

		output.append("Declaration of static method: " + method.getName());
		System.out.println(output.toString());

		method.getType().accept(this);
		for (Formal formal : method.getFormals())
			formal.accept(this);
		for (Stmt statement : method.getStatements())
			statement.accept(this);

	}

	@Override
	public void visit(VirtualMethod method) {
		StringBuffer output = new StringBuffer();
		output.append("Declaration of virtual method: " + method.getName());
		System.out.println(output.toString());

		method.getType().accept(this);
		for (Formal formal : method.getFormals())
			formal.accept(this);
		for (Stmt statement : method.getStatements())
			statement.accept(this);
	}

	@Override
	public void visit(LocalVariable localVariable) {
		StringBuffer output = new StringBuffer();

		output.append("Declaration of local variable: " + localVariable.getName());
		if (localVariable.hasInitValue()) {
			output.append(", with initial value");

		}
		System.out.println(output.toString());

		localVariable.getType().accept(this);
		if (localVariable.hasInitValue()) {
			localVariable.getInitValue().accept(this);

		}

	}

	@Override
	public void visit(Return return1) {
		StringBuffer output = new StringBuffer();

		output.append("Return statement");
		if (return1.hasValue())
			output.append(", with return value");
		System.out.println(output.toString());

		if (return1.hasValue()) {

			return1.getValue().accept(this);

		}

	}

	@Override
	public void visit(VariableLocation location) {
		StringBuffer output = new StringBuffer();
		if (location.getName() != null)
			output.append("Reference to variable: " + location.getName());
		if (location.isExternal())
			output.append(", in external scope");
		System.out.println(output.toString());
		if (location.isExternal()) {
			location.getLocation().accept(this);
		}
	}

	@Override
	public void visit(If ifStatement) {

		StringBuffer output = new StringBuffer();

		output.append("If statement");
		if (ifStatement.hasElse())
			output.append(", with Else operation");
		System.out.println(output.toString());
		ifStatement.getCondition().accept(this);
		ifStatement.getStmt().accept(this);
		if (ifStatement.hasElse())
			ifStatement.getElseOperation().accept(this);

	}

	@Override
	public void visit(While whileStatement) {

		StringBuffer output = new StringBuffer();

		output.append("While statement");
		System.out.println(output.toString());
		whileStatement.getCondition().accept(this);
		whileStatement.getStmt().accept(this);

	}

	@Override
	public void visit(StmtsBlock statementsBlock) {

		StringBuffer output = new StringBuffer();

		output.append("Block of statements");
		System.out.println(output.toString());
		for (Stmt statement : statementsBlock.getStatements())
			statement.accept(this);
	}

	@Override
	public void visit(StaticCall call) {

		StringBuffer output = new StringBuffer();

		output.append("Call to static method: " + call.getName() + ", in class " + call.getClassName());
		System.out.println(output.toString());
		for (Expr argument : call.getArguments())
			argument.accept(this);

	}

	@Override
	public void visit(VirtualCall call) {

		StringBuffer output = new StringBuffer();

		output.append("Call to virtual method: " + call.getName());
		if (call.isExternal())
			output.append(", in external scope");
		System.out.println(output.toString());
		if (call.isExternal())
			call.getLocation().accept(this);
		
		for (Expr argument : call.getArguments())
			argument.accept(this);

	}

	@Override
	public void visit(This thisExpression) {

		StringBuffer output = new StringBuffer();

		output.append("Reference to 'this' instance");
		System.out.println(output.toString());

	}

	@Override
	public void visit(NewClass newClass) {

		StringBuffer output = new StringBuffer();

		output.append("Instantiation of class: " + newClass.getName());
		System.out.println(output.toString());

	}

	@Override
	public void visit(NewArray newArray) {
		StringBuffer output = new StringBuffer();
		output.append("Array allocation");
		System.out.println(output.toString());
		newArray.getType().accept(this);
		newArray.getSize().accept(this);
	}

	@Override
	public void visit(Length length) {

		StringBuffer output = new StringBuffer();

		output.append("Reference to array length");
		System.out.println(output.toString());
		length.getArray().accept(this);

	}

	@Override
	public void visit(MathBinaryOp binaryOp) {

		StringBuffer output = new StringBuffer();

		output.append("Mathematical binary operation: " + binaryOp.getOperator().getDescription());
		System.out.println(output.toString());
		binaryOp.getFirstOperand().accept(this);
		binaryOp.getSecondOperand().accept(this);

	}

	@Override
	public void visit(LogicalBinaryOp binaryOp) {

		StringBuffer output = new StringBuffer();

		output.append("Logical binary operation: " + binaryOp.getOperator().getDescription());
		System.out.println(output.toString());
		binaryOp.getFirstOperand().accept(this);
		binaryOp.getSecondOperand().accept(this);

	}

	@Override
	public void visit(MathUnaryOp unaryOp) {

		StringBuffer output = new StringBuffer();

		output.append("Mathematical unary operation: " + unaryOp.getOperator().getDescription());
		System.out.println(output.toString());
		unaryOp.getOperand().accept(this);

	}

	@Override
	public void visit(LogicalUnaryOp unaryOp) {

		StringBuffer output = new StringBuffer();

		output.append("Logical unary operation: " + unaryOp.getOperator().getDescription());
		System.out.println(output.toString());
		unaryOp.getOperand().accept(this);

	}

	@Override
	public void visit(ExprBlock expressionBlock) {

		StringBuffer output = new StringBuffer();

		output.append("Parenthesized expression");
		System.out.println(output.toString());
		expressionBlock.getExpression().accept(this);

	}

	@Override
	public void visit(LocationAssign locationAssign) {

	}

	@Override
	public void visit(Method method) {

	}

	@Override
	public void visit(Continue continue1) {

		StringBuffer output = new StringBuffer();

		output.append("Continue statement");
		System.out.println(output.toString());

	}

	@Override
	public void visit(Break break1) {

		StringBuffer output = new StringBuffer();

		output.append("Break statement");
		System.out.println(output.toString());
	}

	@Override
	public void visit(CallStatement callStatement) {

		StringBuffer output = new StringBuffer();

		output.append("Method call statement");
		System.out.println(output.toString());
		callStatement.getCall().accept(this);

	}
}