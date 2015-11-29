package Classes;

import slp.PropagatingVisitor;
import slp.Visitor;

public class Continue extends Stmt {

	public Continue(int line) {
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
