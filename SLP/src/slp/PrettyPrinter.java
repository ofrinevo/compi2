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
		
		method.getType().accept(this);
		for (Formal formal : method.getFormals())
			formal.accept(this);
		for (Stmt statement : method.getStatements())
		statement.accept(this);
		
		System.out.println(output.toString()); 
		
	}
}