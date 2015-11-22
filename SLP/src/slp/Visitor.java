package slp;

/** An interface for AST visitors.
 */
public interface Visitor {
	public void visit(StmtList stmts);
	public void visit(Stmt stmt);
	public void visit(PrintStmt stmt);
	public void visit(AssignStmt stmt);
	public void visit(Expr expr);
	public void visit(ReadIExpr expr);
	public void visit(VarExpr expr);
	public void visit(NumberExpr expr);
	public void visit(UnaryOpExpr expr);
	public void visit(BinaryOpExpr expr);
	public void visit(PrimitiveType primitiveType);
	public void visit(UserType userType);
	public void visit(Assign assign);
	public void visit(Field field);
	public void visit(ICClass icClass);
	public void visit(Formal formal);
	public void visit(Program program);
	public void visit(StaticMethod staticMethod);
	public void visit(VirtualMethod virtualMethod);
}