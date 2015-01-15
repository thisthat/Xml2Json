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
%token OPEN
%token CLOSE
%token SLASH
%token QUOTE
%token EQUAL
%token <dval> VALUE

/* STD NODES */
%token BOOK
%token DEDICATION
%token PREFACE
%token PART
%token TOC
%token LOF
%token LOT
%token ITEM
%token CHAPTER
%token SECTION
%token FIGURE
%token TABLE
%token ROW
%token CELL
%token AUTHOR
%token NOTE

%token EDITION
%token ID
%token TITLE
%token CAPTION
%token PATH

      
%%


xml:  OPEN_HEADER param param CLOSE_HEADER doctipe book { };


doctipe : OPEN_DOCTIPE BOOK VALUE QUOTE VALUE QUOTE CLOSE {}

book : OPEN BOOK CLOSE bookItems OPEN SLASH BOOK CLOSE                                   {}
     | OPEN BOOK EDITION EQUAL QUOTE VALUE QUOTE CLOSE bookItems OPEN SLASH BOOK CLOSE   {}

bookItems : /*dedication /*preface partItems authornotes  */  {}

dedication : /* empty */                                               {}
           | OPEN DEDICATION CLOSE element OPEN SLASH DEDICATION CLOSE {}

preface : OPEN PREFACE CLOSE element OPEN SLASH PREFACE CLOSE {}

partItems : /* empty */ {}

authornotes : /* empty */   {}
            | notes         {}

notes: note         {}
     | note notes   {}

note: element   {}

elements :  /* empty */
         | element elements

element : VALUE 
         | OPEN VALUE params CLOSE elements OPEN SLASH VALUE CLOSE

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
    System.err.println ("Error: " + error);
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
