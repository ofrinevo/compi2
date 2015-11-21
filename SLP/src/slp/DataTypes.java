package slp;

/**
 * Enum of the IC language's primitive data types.
 * 
 * 
 */
public enum DataTypes {

	INT(0, "int"), 
	BOOLEAN(false, "boolean"), 
	STRING(null, "string"), 
	VOID(null, "void");
	
	private Object value;

	private String description;

	private DataTypes(Object value, String description) {
		this.value = value;
		this.description = description;
	}

	/**
	 * Returns the default value of the data type.
	 * 
	 * @return The value.
	 */
	public Object getDefaultValue() {
		return value;
	}

	/**
	 * Returns a description of the data type.
	 * 
	 * @return The description.
	 */
	public String getDescription() {
		return description;
	}

}
