package classes;


public class CallStatement extends Statement {

	private Call call;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public CallStatement(Call call) {
		super(call.getLine());
		this.call = call;
	}

	public Call getCall() {
		return call;
	}

}
