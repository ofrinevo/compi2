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
		List<Method> listMethods = new ArrayList<Method>();

		Formal form = new Formal(new PrimitiveType(0, DataTypes.INT), "i");
		List<Formal> listForm = new ArrayList<Formal>();
		listForm.add(form);
		// Methods are of the class LibraryMethod
		listMethods.add(new LibraryMethod(new PrimitiveType(0, DataTypes.INT), "random", listForm));

		////////// PRINTLN
		form = new Formal(new PrimitiveType(0, DataTypes.STRING), "s");
		listForm = new ArrayList<Formal>();
		listForm.add(form);
		listMethods.add(new LibraryMethod(new PrimitiveType(0, DataTypes.VOID), "println", listForm));
		////////////// PRINT
		form = new Formal(new PrimitiveType(0, DataTypes.STRING), "s");
		listForm = new ArrayList<Formal>();
		listForm.add(form);
		listMethods.add(new LibraryMethod(new PrimitiveType(0, DataTypes.VOID), "print", listForm));
		//////////////////// PRINTI (INT)
		form = new Formal(new PrimitiveType(0, DataTypes.INT), "i");
		listForm = new ArrayList<Formal>();
		listForm.add(form);
		listMethods.add(new LibraryMethod(new PrimitiveType(0, DataTypes.VOID), "printi", listForm));
		///////////////////// PRINTB(BOOLEAN)
		form = new Formal(new PrimitiveType(0, DataTypes.BOOLEAN), "b");

		listForm = new ArrayList<Formal>();
		listForm.add(form);
		listMethods.add(new LibraryMethod(new PrimitiveType(0, DataTypes.VOID), "printb", listForm));
		////////////////////// READI
		listForm = new ArrayList<Formal>();
		listMethods.add(new LibraryMethod(new PrimitiveType(0, DataTypes.INT), "readi", listForm));
		////////////////////// READLN
		listForm = new ArrayList<Formal>();
		listMethods.add(new LibraryMethod(new PrimitiveType(0, DataTypes.STRING), "readln", listForm));
		///////////////////// EOF
		listForm = new ArrayList<Formal>();
		listMethods.add(new LibraryMethod(new PrimitiveType(0, DataTypes.BOOLEAN), "eof", listForm));
		///////////////////// STOI
		Formal form1 = new Formal(new PrimitiveType(0, DataTypes.STRING), "s");
		form = new Formal(new PrimitiveType(0, DataTypes.INT), "i");
		listForm = new ArrayList<Formal>();
		listForm.add(form1);
		listForm.add(form);
		listMethods.add(new LibraryMethod(new PrimitiveType(0, DataTypes.INT), "stoi", listForm));

		// ITOS
		form = new Formal(new PrimitiveType(0, DataTypes.INT), "i");
		listForm = new ArrayList<Formal>();
		listForm.add(form);
		listMethods.add(new LibraryMethod(new PrimitiveType(0, DataTypes.STRING), "itos", listForm));

		// exit
		form = new Formal(new PrimitiveType(0, DataTypes.INT), "i");
		listForm = new ArrayList<Formal>();
		listForm.add(form);
		listMethods.add(new LibraryMethod(new PrimitiveType(0, DataTypes.VOID), "exit", listForm));

		///////////////////// time
		listForm = new ArrayList<Formal>();
		listMethods.add(new LibraryMethod(new PrimitiveType(0, DataTypes.INT), "time", listForm));

		// ATOS
		PrimitiveType pt1=new PrimitiveType(0, DataTypes.INT);
		pt1.incrementDimension();
		form = new Formal(pt1, "i");
		listForm = new ArrayList<Formal>();
		listForm.add(form);
		listMethods.add(new LibraryMethod(new PrimitiveType(0, DataTypes.STRING), "atos", listForm));

		//stoa
		form = new Formal(new PrimitiveType(0, DataTypes.STRING), "s");
		listForm = new ArrayList<Formal>();
		listForm.add(form);
		PrimitiveType pt=new PrimitiveType(0, DataTypes.INT);
		pt.incrementDimension();
		listMethods.add(new LibraryMethod(pt, "stoa", listForm));
		
		
		
		
		ICClass libClass = new ICClass(0, "Library", new ArrayList<Field>(), listMethods);
		classes.add(0, libClass);
	}

	public List<ICClass> getClasses() {
		return classes;
	}

}
