package slp;

import Classes.ArrayLocation;
import Classes.Assign;
import Classes.AssignStmt;
import Classes.BinaryOpExpr;
import Classes.Expr;
import Classes.Field;
import Classes.Formal;
import Classes.ICClass;
import Classes.Method;
import Classes.NumberExpr;
import Classes.PrimitiveType;
import Classes.PrintStmt;
import Classes.ReadIExpr;
import Classes.Stmt;
import Classes.StmtList;
import Classes.UnaryOpExpr;
import Classes.UserType;
import Classes.VarExpr;

/** An interface for a propagating AST visitor.
 * The visitor passes down objects of type <code>DownType</code>
 * and propagates up objects of type <code>UpType</code>.
 */
public interface PropagatingVisitor<DownType,UpType> {
	public UpType visit(StmtList stmts, DownType d);
	public UpType visit(Stmt stmt, DownType d);
	public UpType visit(PrintStmt stmt, DownType d);
	public UpType visit(AssignStmt stmt, DownType d);
	public UpType visit(Expr expr, DownType d);
	public UpType visit(ArrayLocation location, DownType d);
	public UpType visit(ReadIExpr expr, DownType d);
	public UpType visit(VarExpr expr, DownType d);
	public UpType visit(NumberExpr expr, DownType d);
	public UpType visit(UnaryOpExpr expr, DownType d);
	public UpType visit(BinaryOpExpr expr, DownType d);
	public UpType visit(UserType userType, DownType d);
	public UpType visit(Assign assign, DownType d);
	public UpType visit(PrimitiveType primitiveType, DownType d);
	public UpType visit(Field field, DownType d);
	public UpType visit(ICClass icClass, DownType d);
	public UpType visit(Formal formal, DownType d);
	public UpType visit(Method method, DownType d);
	public UpType visit(Program program, DownType d);
}