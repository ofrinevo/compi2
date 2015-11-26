package slp;
/***************************/
/* FILE NAME: LEX_FILE.lex */
/***************************/

/***************************/
/* AUTHOR: OREN ISH SHALOM */
/***************************/

/*************/
/* USER CODE */
/*************/

import java_cup.runtime.*;

/******************************/
/* DOLAR DOLAR - DON'T TOUCH! */
/******************************/
   
%%
   
/************************************/
/* OPTIONS AND DECLARATIONS SECTION */
/************************************/

/*****************************************************/ 
/* Lexer is the name of the class JFlex will create. */
/* The code will be written to the file Lexer.java.  */
/*****************************************************/ 
%class Lexer

/********************************************************************/
/* The current line number can be accessed with the variable (yyline+1) */
/* and the current column number with the variable yycolumn.  */
/********************************************************************/
%line
%column
 
/******************************************************************/
/* CUP compatibility mode interfaces with a CUP generated parser. */
/******************************************************************/
%cup
   
/****************/
/* DECLARATIONS */
/****************/
/*****************************************************************************/   
/* Code between %{ and %}, both of which must be at the beginning of a line, */
/* will be copied letter to letter into the Lexer class code.    */
/* Here you declare member variables and functions that are used inside the  */
/* scanner actions.                */  
/*****************************************************************************/   
%{   
 /*********************************************************************************/
 /* Create a new java_cup.runtime.Symbol with information about the current token */
 /*********************************************************************************/
 private Symbol symbol(int type)      {return new Symbol(type, (yyline+1), yycolumn);}
 private Symbol symbol(int type, Object value) {return new Symbol(type, (yyline+1), yycolumn, value);}
 public int getLineNumber(){return yyline+1;}
%}

/***********************/
/* MACRO DECALARATIONS */
/***********************/
LineTerminator	= \r|\n|\r\n
WhiteSpace		= {LineTerminator} | [ \t\f]
INTEGER			= 0 | [1-9][0-9]*
IDENTIFIER		= [a-z_][A-Za-z_0-9]*
STRING			= \"([^\\\"]|\\.)*\"

CLASSIDENTIFIER = [A-Z][A-Za-z_0-9]*   
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" [^\n]* {LineTerminator}
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

/******************************/
/* DOLAR DOLAR - DON'T TOUCH! */
/******************************/

%%

/************************************************************/
/* LEXER matches regular expressions to actions (Java code) */
/************************************************************/
   
/**************************************************************/
/* YYINITIAL is the state at which the lexer begins scanning. */
/* So these regular expressions will only be matched if the   */
/* scanner is in the start state YYINITIAL.       */
/**************************************************************/
   
<YYINITIAL> {

";"					{ System.out.print(""+(yyline+1)+": SEMI"); System.out.println(); return symbol(sym.SEMICOLON);}
"+"					{ System.out.print(""+(yyline+1)+": PLUS"); System.out.println();   return symbol(sym.PLUS);}
"/"					{ System.out.print(""+(yyline+1)+": DIVIDE"); System.out.println(); return symbol(sym.DIVIDE);}
"("					{ System.out.print(""+(yyline+1)+": LP"); System.out.println(); return symbol(sym.LPAREN);}
")"					{ System.out.print(""+(yyline+1)+": RP"); System.out.println(); return symbol(sym.RPAREN);}
"=="				{ System.out.print(""+(yyline+1)+": EQUAL"); System.out.println();  return symbol(sym.EQUAL);}
"="					{ System.out.print(""+(yyline+1)+": ASSIGN"); System.out.println(); return symbol(sym.ASSIGN);}
","					{ System.out.print(""+(yyline+1)+": COMMA"); System.out.println();  return symbol(sym.COMMA);}
"continue"			{ System.out.print(""+(yyline+1)+": CONTINUE"); System.out.println();  return symbol(sym.CONTINUE);}
"."					{ System.out.print(""+(yyline+1)+": DOT"); System.out.println();  	  return symbol(sym.DOT);}
"class"				{ System.out.print(""+(yyline+1)+": CLASS"); System.out.println();  	  return symbol(sym.CLASS);}
"null" 				{ System.out.print(""+(yyline+1)+": NULL"); System.out.println();  	  return symbol(sym.NULL);}
"]" 				{ System.out.print(""+(yyline+1)+": RB"); System.out.println();  	  return symbol(sym.RB);}
"}" 				{ System.out.print(""+(yyline+1)+": RCBR"); System.out.println();  	  return symbol(sym.RCBR);}
"return" 			{ System.out.print(""+(yyline+1)+": RETURN"); System.out.println(); return symbol(sym.RETURN);}
"static" 			{ System.out.print(""+(yyline+1)+": STATIC"); System.out.println(); return symbol(sym.STATIC);}
"this" 				{ System.out.print(""+(yyline+1)+": THIS"); System.out.println();  	  return symbol(sym.THIS);}
"true" 				{ System.out.print(""+(yyline+1)+": TRUE"); System.out.println();  	  return symbol(sym.TRUE);}
"void" 				{ System.out.print(""+(yyline+1)+": VOID"); System.out.println();  	  return symbol(sym.VOID);}
"while" 			{ System.out.print(""+(yyline+1)+": WHILE"); System.out.println();  	  return symbol(sym.WHILE);}
"break"				{ System.out.print(""+(yyline+1)+": BREAK"); System.out.println();  return symbol(sym.BREAK);}
"extends"			{ System.out.print(""+(yyline+1)+": EXTENDS"); System.out.println();   return symbol(sym.EXTENDS);}
"else"				{ System.out.print(""+(yyline+1)+": ELSE"); System.out.println();   return symbol(sym.ELSE);}
">"					{ System.out.print(""+(yyline+1)+": GT"); System.out.println();  return symbol(sym.GT);}
">="				{ System.out.print(""+(yyline+1)+": GTE"); System.out.println();    return symbol(sym.GTE);}
"if"				{ System.out.print(""+(yyline+1)+": IF"); System.out.println();    return symbol(sym.IF);}			
"&&"				{ System.out.print(""+(yyline+1)+": LAND"); System.out.println();    return symbol(sym.LAND);}
"["					{ System.out.print(""+(yyline+1)+": LB"); System.out.println();    return symbol(sym.LB);}
"{"					{ System.out.print(""+(yyline+1)+": LCBR"); System.out.println();    return symbol(sym.LCBR);}
"length"			{ System.out.print(""+(yyline+1)+": LENGTH"); System.out.println();    return symbol(sym.LENGTH);}
"new"				{ System.out.print(""+(yyline+1)+": NEW"); System.out.println();    return symbol(sym.NEW);}
"!"					{ System.out.print(""+(yyline+1)+": LNEG"); System.out.println();    return symbol(sym.LNEG);}
"||"				{ System.out.print(""+(yyline+1)+": LOR"); System.out.println();    return symbol(sym.LOR);}
"<"					{ System.out.print(""+(yyline+1)+": LT"); System.out.println();    return symbol(sym.LT);}
"<="				{ System.out.print(""+(yyline+1)+": LTE"); System.out.println();    return symbol(sym.LTE);}
"-"					{ System.out.print(""+(yyline+1)+": MINUS"); System.out.println();    return symbol(sym.MINUS);}
"%"					{ System.out.print(""+(yyline+1)+": MOD"); System.out.println();    return symbol(sym.MOD);}
"*"					{ System.out.print(""+(yyline+1)+": MULTIPLY"); System.out.println();    return symbol(sym.MULTIPLY);}
"!="				{ System.out.print(""+(yyline+1)+": NEQUAL"); System.out.println();    return symbol(sym.NEQUAL);}
"int"				{ System.out.print(""+(yyline+1)+": INT"); System.out.println();    return symbol(sym.INT);}
"string"			{ System.out.print(""+(yyline+1)+": STRING"); System.out.println();    return symbol(sym.STRING_RESERVED);}
"boolean"			{ System.out.print(""+(yyline+1)+": BOOLEAN"); System.out.println();    return symbol(sym.BOOLEAN);}

			}

{INTEGER}			{
						System.out.print(""+(yyline+1)+": INTEGER(");
						System.out.print(yytext());
						System.out.print(")"); System.out.println();
						return symbol(sym.INTEGER, new Integer(yytext()));
					}   
{IDENTIFIER}		{
						System.out.print(""+(yyline+1)+": ID(");
						System.out.print(yytext());
						System.out.print(")"); System.out.println();
						return symbol(sym.ID, new String(yytext()));
					}
{WhiteSpace}		{ /* just skip what was found, do nothing */ }  
{STRING}				{
						System.out.print(""+(yyline+1)+": QUOTE(");
						System.out.print(yytext());
						System.out.print(")"); System.out.println();
						return symbol(sym.STRING, new String(yytext()));
					} 
{CLASSIDENTIFIER}		{
						System.out.print(""+(yyline+1)+": CLASS_ID(");
						System.out.print(yytext());
						System.out.print(")"); System.out.println();
						return symbol(sym.CLASS_ID, new String(yytext()));
					}
					
{Comment} {}
. { System.out.print((yyline+1) + ": Lexical error: illegal character '" + yytext() + "'");
	System.exit(0);}
<<EOF>> { System.out.println((yyline+2)+ ": EOF"); return symbol(sym.EOF);}
