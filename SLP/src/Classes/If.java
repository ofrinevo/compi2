package Classes;

import slp.PropagatingVisitor;
import slp.Visitor;

public class If extends Stmt {

	public final Expr condition;
	public final Stmt condstmt;
	public final Stmt elsestmt;

	public If(Expr e, Stmt s, Stmt es) {
		super(e.getLine());
		this.condition = e;
		this.condstmt = s;
		this.elsestmt = es;
	}
	public If(Expr e, Stmt s) {
		super(e.getLine());
		this.condition = e;
		this.condstmt = s;
		this.elsestmt = null;
	}
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}
	
	public Expr getCondition() {
		return condition;
	}

	public Stmt getStmt() {
		return condstmt;
	}

	public boolean hasElse() {
		return (elsestmt != null);
	}

	public Stmt getElseOperation() {
		return elsestmt;
	}
}
