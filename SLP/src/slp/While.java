package slp;

public class While extends Stmt {

	public final Expr condition;
	public final Stmt stmt;
	
	public While(Expr condition, Stmt stmt) {
		this.condition = condition;
		this.stmt = stmt;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}
}
