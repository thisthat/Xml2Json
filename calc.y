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
  import java.util.*;
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
%token <sval> VALUE

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

%type <sval> str
%type <obj>  xml
%type <obj>  book
%type <obj>  bookItems
%type <obj>  bookAttr
%type <obj>  dedication
%type <obj>  preface
%type <obj>  cell
%type <obj>  cells
%type <obj>  row
%type <obj>  tableItems
%type <obj>  table
%type <obj>  tableAttr
%type <obj>  idAttr
%type <obj>  note
%type <obj>  notes
%type <obj>  authornotes
%type <obj>  figureAttr
%type <obj>  figure
%type <obj>  parts
%type <obj>  part
%type <obj>  partAttr
%type <obj>  partItems
%type <obj>  toc
%type <obj>  lof
%type <obj>  lot
%type <obj>  items
%type <obj>  item
%type <obj>  chapters
%type <obj>  chapter
%type <obj>  chapterAttr
%type <obj>  sections
%type <obj>  section
%type <obj>  sectionsItems
%type <obj>  sectionAttr

%%

xml:  OPEN_HEADER version encoding CLOSE_HEADER doctipe book { root = (AST.Root) $6; }

/* 
	These three lines are not really necessary in order to convert the 
	xml file to the json format. Thus, their actions are empty.
*/
version : VERSION QUOTE VALUE QUOTE {}

encoding : ENCODING QUOTE VALUE QUOTE {}

doctipe : OPEN_DOCTIPE BOOK VALUE QUOTE VALUE QUOTE CLOSE { }


/*
<!ELEMENT book (dedication?, preface, part+, authornotes?)>
<!ATTLIST book
    edition CDATA ""
>
*/

book : OPEN_BOOK bookAttr CLOSE bookItems CLOSE_BOOK CLOSE { $$ = _AST.new Root( (List) $2, (List) $4 );}

bookAttr : /* empty */					{ $$ = new ArrayList(); }
         | EDITION QUOTE VALUE QUOTE	{ 
         									List newList = new ArrayList<AST.ASTAttribute>();
         									AST.ASTAttribute attr = _AST.new ASTAttribute("edition", $3);
                                            newList.add(attr);
                                            $$ = null; 
                                        }

bookItems : dedication preface parts authornotes  { 
													List newList = new ArrayList();
													newList.add($1);
													newList.add($2);
													newList.add($3);
													newList.add($4);
													$$ = newList;
												}
          | dedication preface parts { 
          								List newList = new ArrayList();
										newList.add($1);
										newList.add($2);
										newList.add($3);
										$$ = newList; 
									}
          | preface parts authornotes { 
          								List newList = new ArrayList();
										newList.add($1);
										newList.add($2);
										newList.add($3);
										$$ = newList;  
									}
          | preface parts { 
          					List newList = new ArrayList();
							newList.add($1);
							newList.add($2);
							$$ = newList;
						}

/*
<!ELEMENT dedication (#PCDATA)>
*/
dedication : OPEN_DEDICATION CLOSE str CLOSE_DEDICATION CLOSE  { $$ = _AST.new Dedication($3); }

/* 
<!ELEMENT preface (#PCDATA)>
*/
preface : OPEN_PREFACE CLOSE str CLOSE_PREFACE CLOSE { $$ = _AST.new Preface($3); }

/* 
<!ELEMENT part (toc, chapter+, lof?, lot?)>
<!ATTLIST part
    id ID #REQUIRED
    title CDATA ""
>
*/
parts : part        {
						List newList = new ArrayList();
                  		newList.add($1); 
                  		$$ = newList; 
					}
      | parts part  { 	
      					List l = (List)$1;
	          			l.add($2);
	          			$$ = l;
	          		}


part : OPEN_PART partAttr CLOSE partItems CLOSE_PART CLOSE { $$ = _AST.new Part( (List) $2 , (List) $4); }

partAttr : idAttr 							{ 
												List newList = new ArrayList<AST.ASTAttribute>();
                                              	newList.add($1);
												$$ = newList;
											}
         | idAttr TITLE QUOTE VALUE QUOTE 	{ 	
         										List newList = new ArrayList<AST.ASTAttribute>();
                                              	AST.ASTAttribute attr = _AST.new ASTAttribute("title", $4);
                                              	newList.add($1);
                                              	newList.add(attr);
												$$ = newList; 
											}

partItems : toc chapters 			{ 
										List newList = new ArrayList();
										newList.add($1);
										newList.add($2);
										$$ = newList;
										//_AST.new PartItems( (AST.TOC) $1, (List) $2); 
									}
          | toc chapters lof 		{ 
          								//$$ = _AST.new PartItems( (AST.TOC) $1, (List) $2, (AST.LOF) $3); 
          								List newList = new ArrayList();
										newList.add($1);
										newList.add($2);
										newList.add($3);
										$$ = newList;
          							}
          | toc chapters lot 		{ 
          								//$$ = _AST.new PartItems( (AST.TOC) $1, (List) $2, (AST.LOT) $3); 
          								List newList = new ArrayList();
										newList.add($1);
										newList.add($2);
										newList.add($3);
										$$ = newList;
          							}
          | toc chapters lof lot 	{ 
          								//$$ = _AST.new PartItems( (AST.TOC) $1, (List) $2, (AST.LOF) $3, (AST.LOT) $4); 
          								List newList = new ArrayList();
										newList.add($1);
										newList.add($2);
										newList.add($3);
										newList.add($4);
										$$ = newList;
          							}

/*
<!ELEMENT toc (item+)>
<!ELEMENT lof (item+)>
<!ELEMENT lot (item+)>
*/
toc : OPEN_TOC CLOSE items CLOSE_TOC CLOSE { $$ = _AST.new TOC( (List) $3);  }

lof : OPEN_LOF CLOSE items CLOSE_LOF CLOSE { $$ = _AST.new LOF( (List) $3);  }

lot : OPEN_LOT CLOSE items CLOSE_LOT CLOSE { $$ = _AST.new LOT( (List) $3);  }

/*
<!ELEMENT item (#PCDATA)>
<!ATTLIST item
  id IDREF #REQUIRED
>
*/
items : item 	{ 
					List newList = new ArrayList();
                  	newList.add($1); 
                  	$$ = newList; 
	            }
      | items item  {
						List l = (List)$1;
	          			l.add($2);
	          			$$ = l;  
	          		} 

item : OPEN_ITEM idAttr CLOSE str CLOSE_ITEM CLOSE { 
														List newList = new ArrayList<AST.ASTAttribute>();
														newList.add($2);
														$$ = _AST.new Item( newList ,  $4 ); 
													}

/*
<!ELEMENT chapter (section+)>
<!ATTLIST chapter
    id ID #REQUIRED
    title CDATA #REQUIRED
>
*/
chapters : chapter 	{
						List newList = new ArrayList();
	                    newList.add($1); 
	                    $$ = newList;
					}
         | chapters chapter {
         						List l = (List)$1;
                      			l.add($2);
                      			$$ = l;
					        }

chapter : OPEN_CHAPTER chapterAttr CLOSE sections CLOSE_CHAPTER CLOSE { $$ = _AST.new Chapter( (List) $2 , (List) $4 ); }

chapterAttr : idAttr TITLE QUOTE str QUOTE {
												List newList = new ArrayList<AST.ASTAttribute>();
                                              	AST.ASTAttribute attr = _AST.new ASTAttribute("title", $4);
                                              	newList.add($1);
                                              	newList.add(attr);
												$$ = newList;
											}

/*
<!ELEMENT section (#PCDATA|section|figure|table)*>
<!ATTLIST section
  id ID #REQUIRED
  title CDATA #REQUIRED
>
*/
sections : section { 
					List newList = new ArrayList();
                    newList.add($1); 
                    $$ = newList;
                   }
         | sections section { 
         					 	List l = (List)$1;
                      			l.add($2);
                      			$$ = l;
                  			}

section : OPEN_SECTION sectionAttr CLOSE sectionsItems CLOSE_SECTION CLOSE 
					{
						 $$ = _AST.new Section( (List) $2 , (List) $4 );
					}

sectionAttr : idAttr TITLE QUOTE str QUOTE {
												List newList = new ArrayList<AST.ASTAttribute>();
                                              	AST.ASTAttribute attr = _AST.new ASTAttribute("title", $4);
                                              	newList.add($1);
                                              	newList.add(attr);
												$$ = newList;
											}

sectionsItems : /* empty */ 	  { $$ = new ArrayList(); }
              | sectionsItems str { 
              						List l = (List)$1;
									l.add($2);
									$$ = l;
              					  }
              | sectionsItems figure  { 
              							List l = (List)$1;
										l.add($2);
										$$ = l; 
									}
              | sectionsItems table   { 
              							List l = (List)$1;
										l.add($2);
										$$ = l; 
									 }
              | sectionsItems section { 
              							List l = (List)$1;
										l.add($2);
										$$ = l; 
									}

/* 
<!ELEMENT figure EMPTY>
<!ATTLIST figure
  id ID #REQUIRED
  caption CDATA #REQUIRED
  path CDATA "placeholder.jpg" -> by rules reduction
>
*/
figure : OPEN_FIGURE figureAttr SLASH CLOSE {
												$$ = _AST.new Figure((List)$2);
											}

figureAttr : idAttr CAPTION QUOTE str QUOTE { 
												List newList = new ArrayList<AST.ASTAttribute>();
                                              	AST.ASTAttribute attr = _AST.new ASTAttribute("caption", $4);
                                              	newList.add($1);
                                              	newList.add(attr);
												$$ = newList;
											}
           | idAttr CAPTION QUOTE str QUOTE PATH QUOTE str QUOTE {
           															List newList = new ArrayList<AST.ASTAttribute>();
					                                              	AST.ASTAttribute caption = _AST.new ASTAttribute("caption", $4);
					                                              	AST.ASTAttribute path = _AST.new ASTAttribute("path", $8);
					                                              	newList.add($1);
					                                              	newList.add(caption);
					                                              	newList.add(path);
																	$$ = newList;
																}


/*
<!ELEMENT table (row+)>
<!ATTLIST table
  id ID #REQUIRED
  caption CDATA #REQUIRED
>
*/
table : OPEN_TABLE tableAttr CLOSE tableItems CLOSE_TABLE CLOSE { $$ = _AST.new Table((List) $2, (List) $4);}

tableAttr : idAttr CAPTION QUOTE str QUOTE  { 
                                              List newList = new ArrayList<AST.ASTAttribute>();
                                              AST.ASTAttribute attr = _AST.new ASTAttribute("caption", $4);
                                              newList.add($1);
                                              newList.add(attr);
                                              $$ = newList;    
                                            }

tableItems : row {  List newList = new ArrayList();
                    newList.add($1); 
                    $$ = newList;  
                  }
           | tableItems row {
                              List l = (List)$1;
                              l.add($2);
                              $$ = l;
                            }

/*
<!ELEMENT row (cell+)>
<!ELEMENT cell (#PCDATA)>
*/
row : OPEN_ROW CLOSE cells CLOSE_ROW CLOSE { $$ = _AST.new Row((List)$3);}

cells : cell       { 
                     List newList = new ArrayList();
                     newList.add($1); 
                     $$ = newList; 
                   }
      | cells cell { 
                      List l = (List)$1;
                      l.add($2);
                      $$ = l;
                   }

cell : OPEN_CELL CLOSE str CLOSE_CELL CLOSE { $$ = _AST.new Cell($3); }


/*
<!ELEMENT authornotes (note+)>
*/
authornotes : OPEN_AUTHOR CLOSE notes CLOSE_AUTHOR CLOSE  { $$ = _AST.new AuthorNotes((List)$3); }

/*
<!ELEMENT note (#PCDATA)>
*/

notes : note        {
						List newList = new ArrayList();
                     	newList.add($1); 
                     	$$ = newList; 
                    }
      | notes note  { 
      					List l = (List)$1;
                      	l.add($2);
                      	$$ = l;
                    }


note : OPEN_NOTE CLOSE str CLOSE_NOTE CLOSE { $$ =  _AST.new Note($3); }



str : VALUE        { $$ = $1; }
    | VALUE str    { $$ = $1 + " " + $2; }      

idAttr :  ID QUOTE str QUOTE { $$ = _AST.new ASTAttribute("id", $3); }


%%

  private Yylex lexer;
  public static AST _AST = new AST();
  public AST.Root root;

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

  /* Custom error report function */
  public void yyerror (String error) {
    System.err.println ("Error: " + error + " :: Token value: " + lexer.yytext() + " @" + lexer._line_cnt );
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }
