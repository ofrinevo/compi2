package slp;

/**
 * Method call statement AST node.
 */
public class CallStatement extends Stmt {

	private Call call;

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	/**
	 * Constructs a new method call statement node.
	 * 
	 * @param call
	 *            Method call expression.
	 */
	public CallStatement(Call call) {
		super(call.getLine());
		this.call = call;
	}

	public Call getCall() {
		return call;
	}

	@Override
	public <DownType, UpType> UpType accept(
			PropagatingVisitor<DownType, UpType> visitor, DownType context) {
		return visitor.visit(this, context);
	}

}
