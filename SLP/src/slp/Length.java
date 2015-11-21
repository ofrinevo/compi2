package slp;

public class Length extends ASTNode{
	
	public final Expr e;
	
	public Length(Expr e){
		this.e=e;
	}
	
	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		// TODO Auto-generated method stub
		return null;
	}

}
