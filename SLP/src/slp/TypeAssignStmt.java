package slp;

public class TypeAssignStmt extends Stmt {

	public final Type t;

	public final VarExpr v;

	public final Expr e;

	public TypeAssignStmt(Type t, VarExpr v, Expr e) {
		super(t.getLine());
		this.t = t;
		this.v = v;
		this.e = e;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}

}
