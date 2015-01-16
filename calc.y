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
%token VERSION
%token ENCODING

      
%%

xml:  OPEN_HEADER version encoding CLOSE_HEADER doctipe book { System.out.println("Fine DOC"); }

version : VERSION QUOTE VALUE QUOTE {}

encoding : ENCODING QUOTE VALUE QUOTE {}

doctipe : OPEN_DOCTIPE BOOK VALUE QUOTE VALUE QUOTE CLOSE {  }


/*
<!ELEMENT book (dedication?, preface, part+, authornotes?)>
<!ATTLIST book
    edition CDATA ""
>
*/

book : OPEN_BOOK bookAttr CLOSE bookItems CLOSE_BOOK CLOSE {}

bookAttr : /* empty */
         | EDITION QUOTE VALUE QUOTE

bookItems : dedication preface part /*authornotes  */  {}
          | preface part {}

/*
<!ELEMENT dedication (#PCDATA)>
*/
dedication : OPEN_DEDICATION CLOSE string CLOSE_DEDICATION CLOSE  {}

/* 
<!ELEMENT preface (#PCDATA)>
*/
preface : OPEN_PREFACE CLOSE string CLOSE_PREFACE CLOSE {}

/* 
<!ELEMENT part (toc, chapter+, lof?, lot?)>
<!ATTLIST part
    id ID #REQUIRED
    title CDATA ""
>
*/
part : OPEN_PART partAttr CLOSE partItems CLOSE_PART CLOSE {}

partAttr : ID QUOTE VALUE QUOTE
         | ID QUOTE VALUE QUOTE TITLE QUOTE VALUE QUOTE 

partItems : toc chapters

/*
<!ELEMENT toc (item+)>
*/
toc : OPEN_TOC CLOSE items CLOSE_TOC CLOSE {}

chapters : chapter
         | chapter chapters

/*
<!ELEMENT chapter (section+)>
<!ATTLIST chapter
    id ID #REQUIRED
    title CDATA #REQUIRED
>
*/
chapter : OPEN_CHAPTER chapterAttr CLOSE sections CLOSE_CHAPTER CLOSE {}

chapterAttr : ID QUOTE string QUOTE TITLE QUOTE string QUOTE {}

/*
<!ELEMENT section (#PCDATA|section|figure|table)*>
<!ATTLIST section
  id ID #REQUIRED
  title CDATA #REQUIRED
>
*/
sections : section {}
         | section sections {}

section : OPEN_SECTION sectionsAttr CLOSE sectionsItems CLOSE_SECTION CLOSE {}

sectionAttr : ID QUOTE string QUOTE TITLE QUOTE string QUOTE {}

sectionsItems : /* empty */ {}
              | string sectionsItems {}
              | section sectionsItems {}
              | figure sectionsItems {}
              | table sectionsItems {}



items : item        {  }
      | item items  {  } 

item : OPEN_ITEM ID QUOTE VALUE QUOTE CLOSE string CLOSE_ITEM CLOSE { }

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
    System.err.println ("Error: " + error + " :: Token value: " + lexer.yytext() + " @" + lexer._line_cnt );
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
