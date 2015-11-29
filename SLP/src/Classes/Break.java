package Classes;

import slp.PropagatingVisitor;
import slp.Visitor;

public class Break extends Stmt {

	public Break(int line){
		super(line);
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}

}
