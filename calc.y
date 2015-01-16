/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2001 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * This is a modified version of the example from                          *
 *   http://www.lincom-asg.com/~rjamison/byacc/                            *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

%{
  import java.io.*;
%}

/* HEADER XML */
%token OPEN_HEADER
%token CLOSE_HEADER
%token OPEN_DOCTIPE

/* RESERVED CHARS */
%token BOOK
%token CLOSE
%token SLASH
%token QUOTE
%token EQUAL
%token <dval> VALUE

/* STD NODES */
%token OPEN_BOOK
%token OPEN_DEDICATION
%token OPEN_PREFACE
%token OPEN_PART
%token OPEN_TOC
%token OPEN_LOF
%token OPEN_LOT
%token OPEN_ITEM
%token OPEN_CHAPTER
%token OPEN_SECTION
%token OPEN_FIGURE
%token OPEN_TABLE
%token OPEN_ROW
%token OPEN_CELL
%token OPEN_AUTHOR
%token OPEN_NOTE

%token CLOSE_BOOK
%token CLOSE_DEDICATION
%token CLOSE_PREFACE
%token CLOSE_PART
%token CLOSE_TOC
%token CLOSE_LOF
%token CLOSE_LOT
%token CLOSE_ITEM
%token CLOSE_CHAPTER
%token CLOSE_SECTION
%token CLOSE_FIGURE
%token CLOSE_TABLE
%token CLOSE_ROW
%token CLOSE_CELL
%token CLOSE_AUTHOR
%token CLOSE_NOTE

/* STD ATTR */
%token EDITION
%token ID
%token TITLE
%token CAPTION
%token PATH

      
%%


xml:  OPEN_HEADER param param CLOSE_HEADER doctipe book { };


doctipe : OPEN_DOCTIPE BOOK VALUE QUOTE VALUE QUOTE CLOSE {System.out.println("Fine DOC"); }

book : OPEN_BOOK CLOSE bookItems CLOSE_BOOK CLOSE                                   {}
     | OPEN_BOOK EDITION EQUAL QUOTE VALUE QUOTE CLOSE bookItems CLOSE_BOOK CLOSE   {}

bookItems : dedication preface partItems /*authornotes  */  {}
          | preface partItems {}

dedication : OPEN_DEDICATION CLOSE string CLOSE_DEDICATION CLOSE  {}

preface : OPEN_PREFACE CLOSE string CLOSE_PREFACE CLOSE {}

partItems : OPEN_PART ID EQUAL QUOTE VALUE QUOTE CLOSE toc CLOSE_PART CLOSE{}

toc : OPEN_TOC CLOSE items CLOSE_TOC CLOSE {}

items : item        { System.out.println("Fine ITEMS"); }
      | item items  { System.out.println("Continua ITEMS");} 

item : OPEN_ITEM ID EQUAL QUOTE VALUE QUOTE CLOSE string CLOSE_ITEM CLOSE { System.out.println("Fine uno ITEMS"); }

authornotes : /* empty */   {}
            | notes         {}

notes: note         {}
     | note notes   {}

note: element   {}

elements : element
         | element elements

element : VALUE 
         | OPEN_VALUE params CLOSE elements CLOSE_VALUE CLOSE

string : VALUE        {}
       | VALUE string {}

params : /* empty */
       | param params

param : VALUE EQUAL QUOTE VALUE QUOTE {};      

%%

  private Yylex lexer;


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error + " :: Character: " + lexer.yytext() + " @" + lexer._line_cnt );
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }


  static boolean interactive;

  public static void main(String args[]) throws IOException {
    System.out.println("BYACC/Java with JFlex Calculator Demo");

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      interactive = true;
	    yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();
    
    if (interactive) {
      System.out.println();
      System.out.println("Tutto ok");
    }
  }
