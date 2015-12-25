
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Wed Dec 23 21:28:17 IST 2015
//----------------------------------------------------

package IC.Parser;

import IC.Parser.Token;
import java_cup.runtime.*;
import IC.AST.*;
import java.util.List;
import java.util.ArrayList;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Wed Dec 23 21:28:17 IST 2015
  */
public class LibraryCup extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public LibraryCup() {super();}

  /** Constructor which sets the default scanner. */
  public LibraryCup(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public LibraryCup(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\022\000\002\002\007\000\002\002\004\000\002\004" +
    "\004\000\002\004\002\000\002\003\011\000\002\012\003" +
    "\000\002\012\003\000\002\011\005\000\002\011\003\000" +
    "\002\011\003\000\002\011\003\000\002\011\003\000\002" +
    "\005\002\000\002\005\003\000\002\006\004\000\002\007" +
    "\002\000\002\007\005\000\002\010\004" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\040\000\004\011\004\001\002\000\004\012\007\001" +
    "\002\000\004\002\006\001\002\000\004\002\000\001\002" +
    "\000\004\016\010\001\002\000\006\013\ufffe\017\ufffe\001" +
    "\002\000\006\013\012\017\013\001\002\000\014\004\023" +
    "\005\016\006\015\007\021\012\017\001\002\000\004\002" +
    "\001\001\002\000\006\013\uffff\017\uffff\001\002\000\006" +
    "\010\ufff8\020\ufff8\001\002\000\006\010\ufff9\020\ufff9\001" +
    "\002\000\006\010\ufff6\020\ufff6\001\002\000\006\010\ufffb" +
    "\020\033\001\002\000\006\010\ufff7\020\ufff7\001\002\000" +
    "\004\010\024\001\002\000\004\010\ufffc\001\002\000\004" +
    "\014\025\001\002\000\014\005\016\006\015\007\021\012" +
    "\017\015\ufff5\001\002\000\004\015\ufff4\001\002\000\004" +
    "\015\041\001\002\000\006\015\ufff2\022\035\001\002\000" +
    "\006\010\032\020\033\001\002\000\006\015\ufff0\022\ufff0" +
    "\001\002\000\004\021\034\001\002\000\006\010\ufffa\020" +
    "\ufffa\001\002\000\012\005\016\006\015\007\021\012\017" +
    "\001\002\000\004\015\ufff3\001\002\000\006\015\ufff2\022" +
    "\035\001\002\000\004\015\ufff1\001\002\000\004\023\042" +
    "\001\002\000\006\013\ufffd\017\ufffd\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\040\000\004\002\004\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\004" +
    "\004\010\001\001\000\004\003\013\001\001\000\006\011" +
    "\017\012\021\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\012\005\026\006\025\010" +
    "\027\011\030\001\001\000\002\001\001\000\002\001\001" +
    "\000\004\007\035\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\006\010\036" +
    "\011\030\001\001\000\002\001\001\000\004\007\037\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$LibraryCup$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$LibraryCup$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$LibraryCup$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


  /** Scan to get the next Symbol. */
  public java_cup.runtime.Symbol scan()
    throws java.lang.Exception
    {
 	
	return lexer.next_token();
	
    }


	/** Causes the parsr to print every token it reads.
	 * This is useful for debugging.
	 */
	public boolean printTokens;
	
	private LibLexer lexer;
	private int arrayDimentionCounter = 0;
	
	public LibParser(LibLexer lexer) {
		super(lexer);
		this.lexer = lexer;
	}
	
	public void syntax_error(Symbol s)
	{
        StringBuilder sb = new StringBuilder();
        Token token = (Token)s;
        sb.append("expected ");
        
        if (s.right > 0) {
            boolean isFirst = true;
            expected_token_ids();
            expected_token_ids();
            for (Integer expected : expected_token_ids()) {
                if (!isFirst)
                    sb.append(" or ");
                else
                    isFirst = false;

                sb.append('\'');
                sb.append(sym.terminalNames[expected]);
                sb.append('\'');
            }
        } else {
            sb.append("end of input");
        }
        if (token == null)
        {
            //throw new SyntaxError(sb.toString(), 0, 0);
        	 System.out.println("0:0 : Syntax error; " + sb.toString());
        } else {
            sb.append(", but found \'");
            sb.append(token.getTag());
            sb.append('\''); 
            //throw new SyntaxError(sb.toString(), token.getLine(), token.getColumn());
            System.out.println("" +token.getLine()+":"+token.getColumn()+" : Syntax error; " + sb.toString());
        }
	}
	
	public void report_error(String message, Object info)
	{
	
	}
	
	public void unrecovered_syntax_error(Symbol cur_token)
	{
	
	}

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$LibraryCup$actions {
  private final LibraryCup parser;

  /** Constructor */
  CUP$LibraryCup$actions(LibraryCup parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$LibraryCup$do_action(
    int                        CUP$LibraryCup$act_num,
    java_cup.runtime.lr_parser CUP$LibraryCup$parser,
    java.util.Stack            CUP$LibraryCup$stack,
    int                        CUP$LibraryCup$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$LibraryCup$result;

      /* select the action based on the action number */
      switch (CUP$LibraryCup$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // formal ::= type ID 
            {
              Formal RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).right;
		Type t = (Type)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).value;
		int fNameleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).left;
		int fNameright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).right;
		Object fName = (Object)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.peek()).value;
		 RESULT = new Formal(t, fName.toString()); 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("formal",6, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // extra_formals ::= COMMA formal extra_formals 
            {
              List<Formal> RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).left;
		int fright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).right;
		Formal f = (Formal)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).value;
		int efleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).left;
		int efright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).right;
		List<Formal> ef = (List<Formal>)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.peek()).value;
		 ef.add(0, f); RESULT = ef; 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("extra_formals",5, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-2)), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // extra_formals ::= 
            {
              List<Formal> RESULT =null;
		 RESULT = new ArrayList<Formal>(); 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("extra_formals",5, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // formal_list ::= formal extra_formals 
            {
              List<Formal> RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).left;
		int fright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).right;
		Formal f = (Formal)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).value;
		int efleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).left;
		int efright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).right;
		List<Formal> ef = (List<Formal>)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.peek()).value;
		 ef.add(0, f); RESULT = ef; 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("formal_list",4, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // formal_list_inter ::= formal_list 
            {
              List<Formal> RESULT =null;
		int flleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).left;
		int flright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).right;
		List<Formal> fl = (List<Formal>)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.peek()).value;
		 RESULT =  fl; 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("formal_list_inter",3, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // formal_list_inter ::= 
            {
              List<Formal> RESULT =null;
		 RESULT = new ArrayList<Formal>(); 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("formal_list_inter",3, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // type ::= CLASS_ID 
            {
              Type RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).right;
		Object c = (Object)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.peek()).value;
		 RESULT = new UserType(cleft, c.toString()); 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("type",7, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // type ::= STRING_KEYWORD 
            {
              Type RESULT =null;
		int strKeyleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).left;
		int strKeyright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).right;
		Object strKey = (Object)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.peek()).value;
		 RESULT = new PrimitiveType(strKeyleft, IC.DataTypes.STRING); 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("type",7, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // type ::= BOOLEAN_KEYWORD 
            {
              Type RESULT =null;
		int boolKeyleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).left;
		int boolKeyright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).right;
		Object boolKey = (Object)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.peek()).value;
		 RESULT = new PrimitiveType(boolKeyleft, IC.DataTypes.BOOLEAN); 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("type",7, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // type ::= INT_KEYWORD 
            {
              Type RESULT =null;
		int intKeyleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).left;
		int intKeyright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).right;
		Object intKey = (Object)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.peek()).value;
		 RESULT = new PrimitiveType(intKeyleft, IC.DataTypes.INT); 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("type",7, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // type ::= type LC RC 
            {
              Type RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-2)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-2)).right;
		Type t = (Type)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-2)).value;
		 t.incrementDimension(); RESULT = t; 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("type",7, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-2)), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // method_type ::= type 
            {
              Type RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).left;
		int tright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).right;
		Type t = (Type)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.peek()).value;
		 RESULT = t; 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("method_type",8, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // method_type ::= VOID_KEYWORD 
            {
              Type RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).right;
		Object v = (Object)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.peek()).value;
		 RESULT = new PrimitiveType(vleft, IC.DataTypes.VOID); 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("method_type",8, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // libmethod ::= STATIC_KEYWORD method_type ID LP formal_list_inter RP SEMICOLON 
            {
              LibraryMethod RESULT =null;
		int mtleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-5)).left;
		int mtright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-5)).right;
		Type mt = (Type)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-5)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-4)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-4)).right;
		Object id = (Object)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-4)).value;
		int flleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-2)).left;
		int flright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-2)).right;
		List<Formal> fl = (List<Formal>)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-2)).value;
		 RESULT = new LibraryMethod(mt, id.toString(), fl); 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("libmethod",1, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-6)), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // libmethod_list ::= 
            {
              List<Method> RESULT =null;
		 RESULT = new ArrayList<Method>(); 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("libmethod_list",2, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // libmethod_list ::= libmethod_list libmethod 
            {
              List<Method> RESULT =null;
		int mlleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).left;
		int mlright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).right;
		List<Method> ml = (List<Method>)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).value;
		int mleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).left;
		int mright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()).right;
		LibraryMethod m = (LibraryMethod)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.peek()).value;
		 ml.add(m); RESULT = ml; 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("libmethod_list",2, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= libic EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).right;
		ICClass start_val = (ICClass)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).value;
		RESULT = start_val;
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$LibraryCup$parser.done_parsing();
          return CUP$LibraryCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // libic ::= CLASS_KEYWORD CLASS_ID LB libmethod_list RB 
            {
              ICClass RESULT =null;
		int ckleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-4)).left;
		int ckright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-4)).right;
		Object ck = (Object)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-4)).value;
		int cidleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-3)).left;
		int cidright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-3)).right;
		Object cid = (Object)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-3)).value;
		int mlleft = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).left;
		int mlright = ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).right;
		List<Method> ml = (List<Method>)((java_cup.runtime.Symbol) CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-1)).value;
		 RESULT = new ICClass(ckleft, cid.toString(), new ArrayList<Field>(), ml); 
              CUP$LibraryCup$result = parser.getSymbolFactory().newSymbol("libic",0, ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.elementAt(CUP$LibraryCup$top-4)), ((java_cup.runtime.Symbol)CUP$LibraryCup$stack.peek()), RESULT);
            }
          return CUP$LibraryCup$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

