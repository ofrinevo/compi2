package Classes;

import slp.PropagatingVisitor;
import slp.Visitor;

public class Return extends Stmt {

	public Expr e = null;

	public Return(int line) {
		super(line);
	}

	public Return(int line, Expr e) {
		this(line);
		this.e = e;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public <DownType, UpType> UpType accept(PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}

	public boolean hasValue() {
		return (e != null);
	}

	public Expr getValue() {
		return e;
	}
}
