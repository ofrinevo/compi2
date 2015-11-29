package Classes;

/**
 * Abstract base class for data type AST nodes.
 */
public abstract class Type extends ASTNode {

	/**
	 * Number of array 'dimensions' in data type. For example, int[][] ->
	 * dimension = 2.
	 */
	private int dimension = 0;

	/**
	 * Constructs a new type node. Used by subclasses.
	 * 
	 */
	protected Type(int line) {
		super(line);
	}

	public abstract String getName();

	public int getDimension() {
		return dimension;
	}

	public void incrementDimension() {
		++dimension;
	}
}