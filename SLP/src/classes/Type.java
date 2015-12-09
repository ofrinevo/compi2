package classes;


public abstract class Type extends ASTNode {

	
	private int dimension = 0;

	
	protected Type(int line) {
		super(line);
	}
	
	public abstract String getName();

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	public void incrementDimension() {
		++dimension;
	}
	public void decrementDimension() {
		--dimension;
	}
}