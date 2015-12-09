package classes;

import java.util.ArrayList;
import java.util.List;

import slp.DataTypes;


public class Program extends ASTNode {

	private List<ICClass> classes;

	public Object accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public Program(List<ICClass> classes) {
		super(0);
		this.classes = classes;
//		List<Method> listMethods=new ArrayList<Method>();
//		Formal form=new Formal(new PrimitiveType(0,DataTypes.INT),"i");
//		List<Formal> liForm=new ArrayList<Formal>();
//		liForm.add(form);
//		listMethods.add(new StaticMethod(new PrimitiveType(0,DataTypes.INT),"random",liForm,new ArrayList<Statement>()));
//		
//		ICClass libClass=new ICClass(0,"Library",new ArrayList<Field>(),listMethods);
//		classes.add(libClass);
	}

	public List<ICClass> getClasses() {
		return classes;
	}

}
