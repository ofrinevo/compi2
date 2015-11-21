package slp;

public class If extends Stmt {

	public final Expr condition;
	public final Stmt condstmt;
	public final Stmt elsestmt;

	public If(Expr e, Stmt s, Stmt es) {
		this.condition = e;
		this.condstmt = s;
		this.elsestmt = es;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}
}