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

	//DEBUG
	private boolean _DEBUG_ = false;

  private Parser yyparser;

  //To take care about line number in error reporting 
  //Public to let the parser see the token line
  public int _line_cnt = 1;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

NL  = \n | \r | \r\n

%%

/* HEADER XML */
"<?xml"			{ return Parser.OPEN_HEADER; }
"?>"			{ return Parser.CLOSE_HEADER; }
"<!DOCTYPE"		{ return Parser.OPEN_DOCTIPE; }

"book"			{ return Parser.BOOK; }

/* ELEMENTS FROM DTD -> filters xml by grammar and not via statical analysis of the AST*/

/* STD NODES */
"<book"				{ return Parser.OPEN_BOOK; 		}
"<dedication"		{ return Parser.OPEN_DEDICATION; }
"<preface"			{ return Parser.OPEN_PREFACE; 	}
"<part"				{ return Parser.OPEN_PART; 		}
"<toc"				{ return Parser.OPEN_TOC;  		}
"<lof"				{ return Parser.OPEN_LOF;  		}
"<lot"				{ return Parser.OPEN_LOT; 	 	}
"<item"				{ return Parser.OPEN_ITEM; 		}
"<chapter"			{ return Parser.OPEN_CHAPTER; 	}
"<section"			{ return Parser.OPEN_SECTION; 	}
"<figure"			{ return Parser.OPEN_FIGURE;  	}
"<table"			{ return Parser.OPEN_TABLE; 	 	}
"<row"				{ return Parser.OPEN_ROW;  	 	}
"<cell"				{ return Parser.OPEN_CELL; 	 	}
"<authornotes"		{ return Parser.OPEN_AUTHOR;  	}
"<note"				{ return Parser.OPEN_NOTE; 	 	}

"</book"			{ return Parser.CLOSE_BOOK; 	}
"</dedication"		{ return Parser.CLOSE_DEDICATION; }
"</preface"			{ return Parser.CLOSE_PREFACE; 	}
"</part"			{ return Parser.CLOSE_PART; 		}
"</toc"				{ return Parser.CLOSE_TOC;  		}
"</lof"				{ return Parser.CLOSE_LOF;  		}
"</lot"				{ return Parser.CLOSE_LOT; 	 	}
"</item"			{ return Parser.CLOSE_ITEM; 		}
"</chapter"			{ return Parser.CLOSE_CHAPTER; 	}
"</section"			{ return Parser.CLOSE_SECTION; 	}
"</figure"			{ return Parser.CLOSE_FIGURE;  	}
"</table"			{ return Parser.CLOSE_TABLE; 	 	}
"</row"				{ return Parser.CLOSE_ROW;  	 	}
"</cell"			{ return Parser.CLOSE_CELL; 	 	}
"</authornotes"		{ return Parser.CLOSE_AUTHOR;  	}
"</note"			{ return Parser.CLOSE_NOTE; 	 	}

/* STD ATTRIBUTES */
"edition="		{ return Parser.EDITION; }
"id="			{ return Parser.ID;    	 }
"title="		{ return Parser.TITLE; 	 }
"caption="		{ return Parser.CAPTION; }
"path="			{ return Parser.PATH; 	 }
"version="		{ return Parser.VERSION; }
"encoding="		{ return Parser.ENCODING;}

/* RESERVED CHARS */

">"				{ return Parser.CLOSE; }
"/"				{ return Parser.SLASH; }
"\"" | "'"		{ return Parser.QUOTE; }


[;0-9aA-zZ\.\-]+ 						| /* handle single value, the bottom rules is necessary to avoid 'comma' as single VALUE token */
[;0-9aA-zZ\.\-][;0-9aA-zZ\.\-\,]+	 	{ if(this._DEBUG_){System.out.println(yytext()); } yyparser.yylval = new ParserVal(yytext()); return Parser.VALUE; }

/* newline, just increase the counter */
{NL}   { _line_cnt++; }

/* whitespace */
[ \t]+ { }


/* error sink */
[^]    { System.err.println("Error: unexpected character '"+yytext()+"' @" + _line_cnt ); return -1; }
