package slp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import classes.ICClass;
import classes.PrettyPrinter;
import classes.Program;
import java_cup.runtime.Symbol;
import semanticAnalysis.SemanticError;
import semanticAnalysis.SemanticErrorThrower;
import symbolTable.SymbolsTableBuilder;
import type.TypeTableBuilder;
import type.TypeChecker;

public class Main {

	private static final String LIB_NAME="Library";

	public static void main(String[] args) {

		if (args.length == 0 || args.length > 4) {
			System.err.println("Error: invalid arguments");
			System.exit(-1);
		}
		ICClass libRoot = null;
		
		try {
			  
			//parse IC file
			File icFile = new File(args[0]);
			FileReader icFileReader = new FileReader(icFile);

			Lexer scanner = new Lexer(icFileReader);
			Parser parser = new Parser(scanner);

			Symbol parseSymbol = parser.parse();
			if(parser.errorFlag)
				return;
			Program ICRoot = (Program) parseSymbol.value;
			
			if (libRoot != null) { 
				if(!libRoot.getName().equals(LIB_NAME)) { //Make sure that the library class has the correct name
					(new SemanticErrorThrower(1, "Library class has incorrect name: "+libRoot.getName()+ ", expected "+LIB_NAME+".")).execute();
				} else {
					ICRoot.getClasses().add(0, libRoot); //append the library
				}
			}

			System.out.println("Parsed " + icFile.getName() +" successfully!");
			System.out.println();

			TypeTableBuilder typeTableBuilder = new TypeTableBuilder(icFile);
			typeTableBuilder.buildTypeTable(ICRoot);
			SymbolsTableBuilder s = new SymbolsTableBuilder(typeTableBuilder.getBuiltTypeTable(), icFile.getName());
			s.buildSymbolTables(ICRoot);

			TypeChecker tv = new TypeChecker(typeTableBuilder.getBuiltTypeTable());
			tv.validate(ICRoot);
			
			System.out.println("Semantic analysis passed successfuly!");
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (LexicalError e) {
			System.out.println(e.getMessage());
		} catch (SemanticError e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}