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
		//I added this. You need to add more 
		//Add your methods to listMethods
		List<Method> listMethods=new ArrayList<Method>();
		
		Formal form=new Formal(new PrimitiveType(0,DataTypes.INT),"i");
		List<Formal> listForm=new ArrayList<Formal>();
		listForm.add(form);
		//Methods are of the class LibraryMethod
		listMethods.add(new LibraryMethod(new PrimitiveType(0,DataTypes.INT),"random",listForm));
		
		////////// PRINTLN
		form=new Formal(new PrimitiveType(0,DataTypes.STRING),"s");
		listForm=new ArrayList<Formal>();
		listForm.add(form);
		//Methods are of the class LibraryMethod
		listMethods.add(new LibraryMethod(new PrimitiveType(0,DataTypes.VOID),"println",listForm));
		//////////////PRINT
		form=new Formal(new PrimitiveType(0,DataTypes.STRING),"s");
		listForm=new ArrayList<Formal>();
		listForm.add(form);
		//Methods are of the class LibraryMethod
		listMethods.add(new LibraryMethod(new PrimitiveType(0,DataTypes.VOID),"print",listForm));
		////////////////////PRINTI (INT)
		form=new Formal(new PrimitiveType(0,DataTypes.INT),"i");
		listForm=new ArrayList<Formal>();
		listForm.add(form);
		//Methods are of the class LibraryMethod
		listMethods.add(new LibraryMethod(new PrimitiveType(0,DataTypes.VOID),"printi",listForm));
		/////////////////////PRINTB(BOOLEAN)
		form=new Formal(new PrimitiveType(0,DataTypes.BOOLEAN),"b");
		listForm=new ArrayList<Formal>();
		listForm.add(form);
		//Methods are of the class LibraryMethod
		listMethods.add(new LibraryMethod(new PrimitiveType(0,DataTypes.VOID),"printb",listForm));
		//////////////////////READI
		form=new Formal(new PrimitiveType(0,DataTypes.VOID ),"");
		listForm=new ArrayList<Formal>();
		listForm.add(form);
		//Methods are of the class LibraryMethod
		listMethods.add(new LibraryMethod(new PrimitiveType(0,DataTypes.INT),"readi",listForm));
		
		
		ICClass libClass=new ICClass(0,"Library",new ArrayList<Field>(),listMethods);
		classes.add(0, libClass);
		
	}

	public List<ICClass> getClasses() {
		return classes;
	}

}
