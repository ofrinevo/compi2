package slp;

/**
 * AST visitor interface. Declares methods for visiting each type of AST node.
 * 
 * @author Tovi Almozlino
 */
public interface Visitor {

	public void visit(Program program);

	public void visit(ICClass icClass);

	public void visit(Field field);

	public void visit(VirtualMethod method);

	public void visit(StaticMethod method);

	public void visit(Formal formal);

	public void visit(PrimitiveType type);

	public void visit(UserType type);

	public void visit(Assign assignment);

	public void visit(CallStatement callStatement);

	public void visit(Return returnStatement);

	public void visit(If ifStatement);

	public void visit(While whileStatement);

	public void visit(Break breakStatement);

	public void visit(Continue continueStatement);

	public void visit(StmtsBlock statementsBlock);

	public void visit(LocalVariable localVariable);

	public void visit(VariableLocation location);

	public void visit(ArrayLocation location);

	public void visit(StaticCall call);

	public void visit(VirtualCall call);

	public void visit(This thisExpression);

	public void visit(NewClass newClass);

	public void visit(NewArray newArray);

	public void visit(Length length);

	public void visit(MathBinaryOp binaryOp);

	public void visit(LogicalBinaryOp binaryOp);

	public void visit(MathUnaryOp unaryOp);

	public void visit(LogicalUnaryOp unaryOp);

	public void visit(Literal literal);

	public void visit(ExprBlock expressionBlock);

	public void visit(BinaryOpExpr binaryOpExpr);

	public void visit(LocationAssign locationAssign);

	public void visit(Method method);

	public void visit(NumberExpr numberExpr);

	public void visit(PrintStmt printStmt);

	public void visit(ReadIExpr readIExpr);

	public void visit(Stmt stmt);

	public void visit(StmtList stmtList);

	public void visit(UnaryOpExpr unaryOpExpr);

	public void visit(VarExpr varExpr);
}
