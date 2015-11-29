package Classes;

import slp.PropagatingVisitor;
import slp.Visitor;

public class LocationAssign extends Stmt {

	public final Location l;
	public final Expr e;
	
	public LocationAssign(Location l, Expr e) {
		super(l.getLine());
		this.l = l;
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
