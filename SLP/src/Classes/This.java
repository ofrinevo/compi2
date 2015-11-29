package Classes;

import slp.PropagatingVisitor;
import slp.Visitor;

public class This extends Expr{
	public This(int line){
		super(line);
	}
	@Override
	public void accept(Visitor visitor) {
		
		visitor.visit(this);
	}

	@Override
	public <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}

}
