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

  //To take care about line number in error reporting 
  private int _line_cnt = 1;

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
"<!DOCTYPE"		{ return Parser.OPEN_DOCTIPE; }

/* RESERVED CHARS */
"<"				{ return Parser.OPEN;  }
">"				{ return Parser.CLOSE; }
"/"				{ return Parser.SLASH; }
"\"" | "'"		{ return Parser.QUOTE; }
"="				{ return Parser.EQUAL; }

/* ELEMENTS FROM DTD -> filters xml by grammar and not via statical analysis of the AST*/

/* STD NODES */
"book"				{ return Parser.BOOK; 		}
"dedication"		{ return Parser.DEDICATION; }
"preface"			{ return Parser.PREFACE; 	}
"part"				{ return Parser.PART; 		}
"toc"				{ return Parser.TOC;  		}
"lof"				{ return Parser.LOF;  		}
"lot"				{ return Parser.LOT; 	 	}
"item"				{ return Parser.ITEM; 		}
"chapter"			{ return Parser.CHAPTER; 	}
"section"			{ return Parser.SECTION; 	}
"figure"			{ return Parser.FIGURE;  	}
"table"				{ return Parser.TABLE; 	 	}
"row"				{ return Parser.ROW;  	 	}
"cell"				{ return Parser.CELL; 	 	}
"authornotes"		{ return Parser.AUTHOR;  	}
"note"				{ return Parser.NOTE; 	 	}

/* STD ATTRIBUTES */
"edition"		{ return Parser.EDIT;  	 }
"id"			{ return Parser.ID;    	 }
"title"			{ return Parser.TITLE; 	 }
"caption"		{ return Parser.CAPTION; }
"path"			{ return Parser.PATH; 	 }


[;0-9aA-zZ.-]+ 					|
[;0-9aA-zZ.-][;0-9aA-zZ.-\n\r]+	 {yyparser.yylval = new ParserVal(yytext()); return Parser.VALUE; }

/* float
{NUM}  { yyparser.yylval = new ParserVal(Double.parseDouble(yytext()));
         return Parser.NUM; }
*/

/* newline, just increase the counter */
{NL}   { _line_cnt++; }

/* whitespace */
[ \t]+ { }


/* error sink */
[^]    { System.err.println("Error: unexpected character '"+yytext()+"' @" + _line_cnt ); return -1; }
