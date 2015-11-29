package slp;

import Classes.ArrayLocation;
import Classes.Assign;
import Classes.BinaryOpExpr;
import Classes.Break;
import Classes.CallStatement;
import Classes.Continue;
import Classes.ExprBlock;
import Classes.Field;
import Classes.Formal;
import Classes.ICClass;
import Classes.If;
import Classes.Length;
import Classes.Literal;
import Classes.LocalVariable;
import Classes.LocationAssign;
import Classes.LogicalBinaryOp;
import Classes.LogicalUnaryOp;
import Classes.MathBinaryOp;
import Classes.MathUnaryOp;
import Classes.Method;
import Classes.NewArray;
import Classes.NewClass;
import Classes.NumberExpr;
import Classes.PrimitiveType;
import Classes.PrintStmt;
import Classes.ReadIExpr;
import Classes.Return;
import Classes.StaticCall;
import Classes.StaticMethod;
import Classes.Stmt;
import Classes.StmtList;
import Classes.StmtsBlock;
import Classes.This;
import Classes.UnaryOpExpr;
import Classes.UserType;
import Classes.VarExpr;
import Classes.VariableLocation;
import Classes.VirtualCall;
import Classes.VirtualMethod;
import Classes.While;

/**
 * AST visitor interface. Declares methods for visiting each type of AST node.
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
