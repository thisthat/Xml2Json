/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2000 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

%%

%byaccj

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

NUM = [0-9]+ ("." [0-9]+)?
NL  = \n | \r | \r\n

%%

/* HEADER XML */
"<?xml"			{ return Parser.OPEN_HEADER; }
"?>"			{ return Parser.CLOSE_HEADER; }
"<!"			{ return Parser.OPEN_DOCTIPE; }

/* BASIC OPERATOR */
"<"				{return Parser.OPEN;}
">"				{return Parser.CLOSE;}
"/"				{return Parser.SLASH;}
"\""			{return Parser.QUOTE;}
"="				{return Parser.EQUAL;}

[;0-9aA-zZ.-]+ 					|
[;0-9aA-zZ.-][;0-9aA-zZ.-\n\r]+	 {yyparser.yylval = new ParserVal(yytext()); return Parser.VALUE; }

/* float
{NUM}  { yyparser.yylval = new ParserVal(Double.parseDouble(yytext()));
         return Parser.NUM; }
*/

/* newline */
{NL}   { }

/* whitespace */
[ \t]+ { }

/* \b     { System.err.println("Sorry, backspace doesn't work"); } */

/* error fallback */
[^]    { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }
