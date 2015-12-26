package slp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import classes.ICClass;
import classes.PrettyPrinter;
import classes.Program;
import java_cup.runtime.Symbol;
import lir.TranslationVisitor;
import semanticAnalysis.SemanticError;
import semanticAnalysis.SemanticErrorThrower;
import symTable.SymbolsTableBuilder;
import type.TypeChecker;
import type.TypeTableBuilder;

public class Main {

	private static final String LIB_NAME = "Library";

	public static void main(String[] args) {

		if (args.length == 0 || args.length > 4) {
			System.err.println("Error: invalid arguments");
			System.exit(-1);
		}
		ICClass libRoot = null;

		try {

			// parse IC file
			File icFile = new File(args[0]);
			FileReader icFileReader = new FileReader(icFile);

			Lexer scanner = new Lexer(icFileReader);
			Parser parser = new Parser(scanner);

			Symbol parseSymbol = parser.parse(); // TODO right now: keeps
													// running after
													// exception!!!
			Program ICRoot = (Program) parseSymbol.value;

			System.out.println("Parsed " + icFile.getName() + " successfully!");
			System.out.println();

			TypeTableBuilder typeTableBuilder = new TypeTableBuilder(icFile.getName());
			typeTableBuilder.buildTypeTable(ICRoot);
			SymbolsTableBuilder s = new SymbolsTableBuilder(typeTableBuilder.getBuiltTypeTable(), icFile.getName());
			s.buildSymbolTables(ICRoot);

			TypeChecker tv = new TypeChecker(typeTableBuilder.getBuiltTypeTable());
			tv.validate(ICRoot);

			
			PrettyPrinter printer = new PrettyPrinter(args[0]);
			TranslationVisitor trv = new TranslationVisitor();
			trv.translate(ICRoot);

			trv.printInstructions();
			System.out.println("Created output file with lir instructions.");
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
