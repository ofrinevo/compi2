package slp;

import java.util.ArrayList;
import java.util.List;

import classes.Field;
import classes.Method;

public class ClassLines {
	public ClassLines() 
	{
		this.methods = new ArrayList<Method>(); 
		this.fields = new ArrayList<Field>();
	}
	public List<Method> methods;
	public List<Field> fields;
}
