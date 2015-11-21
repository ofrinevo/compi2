package slp;

public class Return extends Stmt {

	public Expr e=null;

	public Return(){
		
	}
	
	public Return(Expr e) {
		this.e = e;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public <DownType, UpType> UpType accept(
			PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}	
}
