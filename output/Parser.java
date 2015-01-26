//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 15 "xml2json.y"
  import java.io.*;
  import java.util.*;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short OPEN_HEADER=257;
public final static short CLOSE_HEADER=258;
public final static short OPEN_DOCTIPE=259;
public final static short BOOK=260;
public final static short CLOSE=261;
public final static short SLASH=262;
public final static short QUOTE=263;
public final static short VALUE=264;
public final static short OPEN_BOOK=265;
public final static short OPEN_DEDICATION=266;
public final static short OPEN_PREFACE=267;
public final static short OPEN_PART=268;
public final static short OPEN_TOC=269;
public final static short OPEN_LOF=270;
public final static short OPEN_LOT=271;
public final static short OPEN_ITEM=272;
public final static short OPEN_CHAPTER=273;
public final static short OPEN_SECTION=274;
public final static short OPEN_FIGURE=275;
public final static short OPEN_TABLE=276;
public final static short OPEN_ROW=277;
public final static short OPEN_CELL=278;
public final static short OPEN_AUTHOR=279;
public final static short OPEN_NOTE=280;
public final static short CLOSE_BOOK=281;
public final static short CLOSE_DEDICATION=282;
public final static short CLOSE_PREFACE=283;
public final static short CLOSE_PART=284;
public final static short CLOSE_TOC=285;
public final static short CLOSE_LOF=286;
public final static short CLOSE_LOT=287;
public final static short CLOSE_ITEM=288;
public final static short CLOSE_CHAPTER=289;
public final static short CLOSE_SECTION=290;
public final static short CLOSE_FIGURE=291;
public final static short CLOSE_TABLE=292;
public final static short CLOSE_ROW=293;
public final static short CLOSE_CELL=294;
public final static short CLOSE_AUTHOR=295;
public final static short CLOSE_NOTE=296;
public final static short EDITION=297;
public final static short ID=298;
public final static short TITLE=299;
public final static short CAPTION=300;
public final static short PATH=301;
public final static short VERSION=302;
public final static short ENCODING=303;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,   35,   36,   37,    2,    4,    4,    3,    3,    3,
    3,    5,    6,   19,   19,   20,   21,   21,   22,   22,
   22,   22,   23,   24,   25,   26,   26,   27,   28,   28,
   29,   30,   31,   31,   32,   34,   33,   33,   33,   33,
   33,   18,   17,   17,   11,   12,   10,   10,    9,    8,
    8,    7,   16,   15,   15,   14,    1,    1,   13,
};
final static short yylen[] = {                            2,
    6,    4,    4,    7,    6,    0,    4,    4,    3,    3,
    2,    5,    5,    1,    2,    6,    1,    5,    2,    3,
    3,    4,    5,    5,    5,    1,    2,    6,    1,    2,
    6,    5,    1,    2,    6,    5,    0,    2,    2,    2,
    2,    4,    5,    9,    6,    5,    1,    2,    5,    1,
    2,    5,    5,    1,    2,    5,    1,    2,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    2,    0,    0,    0,    3,    0,    0,    1,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   58,    7,    0,    0,    0,    0,    0,
    0,   14,    4,    0,    0,    5,    0,    0,    0,    0,
    0,   10,   15,    0,    0,    8,    0,    0,    0,    0,
   12,   13,    0,    0,    0,    0,    0,    0,   54,    0,
   59,    0,    0,    0,    0,    0,   29,    0,    0,   55,
   18,    0,    0,   26,   16,    0,    0,    0,    0,    0,
   21,   30,    0,   53,    0,    0,   27,    0,    0,    0,
    0,   22,    0,    0,   23,    0,    0,    0,   33,    0,
    0,   56,    0,    0,    0,    0,    0,   34,    0,    0,
    0,   32,    0,   37,   31,   24,   25,   28,    0,    0,
    0,    0,    0,    0,   38,   40,   39,   41,   36,    0,
    0,    0,    0,   35,    0,    0,    0,    0,    0,   42,
    0,   47,    0,    0,    0,    0,    0,   48,    0,    0,
    0,   50,    0,   45,   46,    0,    0,    0,   51,    0,
    0,   49,    0,    0,   44,   52,
};
final static short yydgoto[] = {                          2,
   27,   18,   30,   21,   31,   32,  162,  163,  152,  153,
  136,  142,   49,   69,   70,   52,  141,  137,   41,   42,
   50,   66,   67,   90,   91,   83,   84,   76,   77,   87,
  108,  109,  130,  116,    4,    7,   14,
};
final static short yysindex[] = {                      -209,
 -277,    0, -212, -250, -207, -202, -196, -200, -199, -195,
    0, -197, -193, -194,    0, -192, -229,    0, -190, -189,
 -191, -188, -187, -261, -184, -187, -182, -186, -179, -198,
 -183, -181, -176,    0,    0, -187, -187, -175, -181, -210,
 -225,    0,    0, -213, -185,    0, -225, -174, -208, -171,
 -169,    0,    0, -168, -167,    0, -187, -166, -173, -180,
    0,    0, -164, -187, -160, -178, -165, -159,    0, -263,
    0, -158, -177, -154, -210, -224,    0, -187, -152,    0,
    0, -210, -235,    0,    0, -172, -151, -150, -149, -157,
    0,    0, -170,    0, -148, -146,    0, -147, -156, -177,
 -177,    0, -144, -187,    0, -187, -210, -248,    0, -268,
 -245,    0, -163, -143, -162, -142, -140,    0, -139, -137,
 -132,    0, -133,    0,    0,    0,    0,    0, -187, -255,
 -131, -210, -210, -130,    0,    0,    0,    0,    0, -161,
 -129, -127, -155,    0, -128, -125, -135, -123, -187,    0,
 -118,    0, -232, -187, -117, -126, -114,    0, -115, -145,
 -112,    0, -249,    0,    0, -113, -187, -110,    0, -187,
 -141,    0, -109, -104,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -103,    0,    0,    0,
    0,    0,    0,    0,    0, -260,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -122,    0,    0,    0,    0,    0, -121,    0, -100,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -120,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -119,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -124,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -26,    0,    0,    0,    0,  124,   -1,    0,   10,    0,
    0,    0,  -74,   96,    0,  120,    0,    0,  129,  -34,
    0,    0,    0,    0,   79,  -45,  -71,    0,   94,    0,
    0, -106,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=170;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         34,
   86,  118,   57,   82,   28,   29,   53,   95,   26,   44,
   45,   97,   53,   57,   57,   57,   68,  119,  107,  132,
  133,   57,   57,  138,    3,  107,   82,   57,  161,   57,
   63,   79,  115,   57,  134,   57,   82,   72,   97,   97,
  117,  120,   40,  168,  151,   88,   89,    1,   75,   96,
    5,   93,    6,   51,  110,  111,    8,  140,  143,  157,
    9,   10,   11,   13,   12,   15,   16,   20,   54,   24,
   17,   19,   22,   23,   36,   25,   26,  113,   33,  114,
   35,   37,   38,   29,   43,   46,   40,   48,   57,   59,
   58,   60,   61,   62,   82,   65,   64,   55,   71,   68,
   73,   78,  131,  135,   81,   74,   85,   75,   94,   99,
  100,  101,  104,   89,  105,  106,  112,  107,  124,  122,
  125,  126,  155,  127,  121,  103,   98,  159,  128,  129,
  144,  139,  146,  147,  149,  150,  123,   43,  145,  154,
  171,  151,  156,  173,  148,  160,  164,  165,  167,  170,
  172,  161,  174,  175,   39,  166,  176,    6,   11,    9,
   17,  169,  158,   19,   20,   80,   56,   47,  102,   92,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         26,
   75,  108,  263,  272,  266,  267,   41,   82,  264,   36,
   37,   83,   47,  274,  275,  276,  280,  286,  274,  275,
  276,  282,  283,  130,  302,  274,  272,  288,  278,  290,
   57,  295,  107,  294,  290,  296,  272,   64,  110,  111,
  289,  287,  268,  293,  277,  270,  271,  257,  273,  285,
  263,   78,  303,  279,  100,  101,  264,  132,  133,  292,
  263,  258,  263,  259,  264,  263,  260,  297,  282,  261,
  265,  264,  263,  263,  261,  264,  264,  104,  263,  106,
  263,  261,  281,  267,  261,  261,  268,  298,  263,  261,
  299,  261,  261,  261,  272,  269,  263,  283,  263,  280,
  261,  261,  129,  130,  263,  284,  261,  273,  261,  261,
  261,  261,  261,  271,  261,  263,  261,  274,  261,  263,
  261,  261,  149,  261,  288,  296,  299,  154,  261,  263,
  261,  263,  262,  261,  263,  261,  299,  262,  300,  263,
  167,  277,  261,  170,  300,  263,  261,  263,  261,  263,
  261,  278,  294,  263,   31,  301,  261,  261,  281,  281,
  261,  163,  153,  284,  284,   70,   47,   39,   90,   76,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=303;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"OPEN_HEADER","CLOSE_HEADER","OPEN_DOCTIPE","BOOK","CLOSE",
"SLASH","QUOTE","VALUE","OPEN_BOOK","OPEN_DEDICATION","OPEN_PREFACE",
"OPEN_PART","OPEN_TOC","OPEN_LOF","OPEN_LOT","OPEN_ITEM","OPEN_CHAPTER",
"OPEN_SECTION","OPEN_FIGURE","OPEN_TABLE","OPEN_ROW","OPEN_CELL","OPEN_AUTHOR",
"OPEN_NOTE","CLOSE_BOOK","CLOSE_DEDICATION","CLOSE_PREFACE","CLOSE_PART",
"CLOSE_TOC","CLOSE_LOF","CLOSE_LOT","CLOSE_ITEM","CLOSE_CHAPTER",
"CLOSE_SECTION","CLOSE_FIGURE","CLOSE_TABLE","CLOSE_ROW","CLOSE_CELL",
"CLOSE_AUTHOR","CLOSE_NOTE","EDITION","ID","TITLE","CAPTION","PATH","VERSION",
"ENCODING",
};
final static String yyrule[] = {
"$accept : xml",
"xml : OPEN_HEADER version encoding CLOSE_HEADER doctipe book",
"version : VERSION QUOTE VALUE QUOTE",
"encoding : ENCODING QUOTE VALUE QUOTE",
"doctipe : OPEN_DOCTIPE BOOK VALUE QUOTE VALUE QUOTE CLOSE",
"book : OPEN_BOOK bookAttr CLOSE bookItems CLOSE_BOOK CLOSE",
"bookAttr :",
"bookAttr : EDITION QUOTE str QUOTE",
"bookItems : dedication preface parts authornotes",
"bookItems : dedication preface parts",
"bookItems : preface parts authornotes",
"bookItems : preface parts",
"dedication : OPEN_DEDICATION CLOSE str CLOSE_DEDICATION CLOSE",
"preface : OPEN_PREFACE CLOSE str CLOSE_PREFACE CLOSE",
"parts : part",
"parts : parts part",
"part : OPEN_PART partAttr CLOSE partItems CLOSE_PART CLOSE",
"partAttr : idAttr",
"partAttr : idAttr TITLE QUOTE str QUOTE",
"partItems : toc chapters",
"partItems : toc chapters lof",
"partItems : toc chapters lot",
"partItems : toc chapters lof lot",
"toc : OPEN_TOC CLOSE items CLOSE_TOC CLOSE",
"lof : OPEN_LOF CLOSE items CLOSE_LOF CLOSE",
"lot : OPEN_LOT CLOSE items CLOSE_LOT CLOSE",
"items : item",
"items : items item",
"item : OPEN_ITEM idAttr CLOSE str CLOSE_ITEM CLOSE",
"chapters : chapter",
"chapters : chapters chapter",
"chapter : OPEN_CHAPTER chapterAttr CLOSE sections CLOSE_CHAPTER CLOSE",
"chapterAttr : idAttr TITLE QUOTE str QUOTE",
"sections : section",
"sections : sections section",
"section : OPEN_SECTION sectionAttr CLOSE sectionsItems CLOSE_SECTION CLOSE",
"sectionAttr : idAttr TITLE QUOTE str QUOTE",
"sectionsItems :",
"sectionsItems : sectionsItems str",
"sectionsItems : sectionsItems figure",
"sectionsItems : sectionsItems table",
"sectionsItems : sectionsItems section",
"figure : OPEN_FIGURE figureAttr SLASH CLOSE",
"figureAttr : idAttr CAPTION QUOTE str QUOTE",
"figureAttr : idAttr CAPTION QUOTE str QUOTE PATH QUOTE str QUOTE",
"table : OPEN_TABLE tableAttr CLOSE tableItems CLOSE_TABLE CLOSE",
"tableAttr : idAttr CAPTION QUOTE str QUOTE",
"tableItems : row",
"tableItems : tableItems row",
"row : OPEN_ROW CLOSE cells CLOSE_ROW CLOSE",
"cells : cell",
"cells : cells cell",
"cell : OPEN_CELL CLOSE str CLOSE_CELL CLOSE",
"authornotes : OPEN_AUTHOR CLOSE notes CLOSE_AUTHOR CLOSE",
"notes : note",
"notes : notes note",
"note : OPEN_NOTE CLOSE str CLOSE_NOTE CLOSE",
"str : VALUE",
"str : VALUE str",
"idAttr : ID QUOTE str QUOTE",
};

//#line 473 "xml2json.y"

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
//#line 391 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 113 "xml2json.y"
{ root = (AST.Root) val_peek(0).obj; }
break;
case 2:
//#line 119 "xml2json.y"
{}
break;
case 3:
//#line 121 "xml2json.y"
{}
break;
case 4:
//#line 123 "xml2json.y"
{ }
break;
case 5:
//#line 133 "xml2json.y"
{ yyval.obj = _AST.new Root( (List) val_peek(4).obj, (List) val_peek(2).obj );}
break;
case 6:
//#line 135 "xml2json.y"
{ yyval.obj = new ArrayList(); }
break;
case 7:
//#line 136 "xml2json.y"
{ 
         									List newList = new ArrayList<AST.ASTAttribute>();
         									AST.ASTAttribute attr = _AST.new ASTAttribute("edition", val_peek(1).sval);
                                            newList.add(attr);
                                            yyval.obj = newList; 
                                        }
break;
case 8:
//#line 143 "xml2json.y"
{ 
													List newList = new ArrayList();
													newList.add(val_peek(3).obj);
													newList.add(val_peek(2).obj);
													newList.add(val_peek(1).obj);
													newList.add(val_peek(0).obj);
													yyval.obj = newList;
												}
break;
case 9:
//#line 151 "xml2json.y"
{ 
          								List newList = new ArrayList();
										newList.add(val_peek(2).obj);
										newList.add(val_peek(1).obj);
										newList.add(val_peek(0).obj);
										yyval.obj = newList; 
									}
break;
case 10:
//#line 158 "xml2json.y"
{ 
          								List newList = new ArrayList();
										newList.add(val_peek(2).obj);
										newList.add(val_peek(1).obj);
										newList.add(val_peek(0).obj);
										yyval.obj = newList;  
									}
break;
case 11:
//#line 165 "xml2json.y"
{ 
          					List newList = new ArrayList();
							newList.add(val_peek(1).obj);
							newList.add(val_peek(0).obj);
							yyval.obj = newList;
						}
break;
case 12:
//#line 175 "xml2json.y"
{ yyval.obj = _AST.new Dedication(val_peek(2).sval); }
break;
case 13:
//#line 180 "xml2json.y"
{ yyval.obj = _AST.new Preface(val_peek(2).sval); }
break;
case 14:
//#line 189 "xml2json.y"
{
						List newList = new ArrayList();
                  		newList.add(val_peek(0).obj); 
                  		yyval.obj = newList; 
					}
break;
case 15:
//#line 194 "xml2json.y"
{ 	
      					List l = (List)val_peek(1).obj;
	          			l.add(val_peek(0).obj);
	          			yyval.obj = l;
	          		}
break;
case 16:
//#line 201 "xml2json.y"
{ yyval.obj = _AST.new Part( (List) val_peek(4).obj , (List) val_peek(2).obj); }
break;
case 17:
//#line 203 "xml2json.y"
{ 
												List newList = new ArrayList<AST.ASTAttribute>();
                                              	newList.add(val_peek(0).obj);
												yyval.obj = newList;
											}
break;
case 18:
//#line 208 "xml2json.y"
{ 	
         										List newList = new ArrayList<AST.ASTAttribute>();
                                              	AST.ASTAttribute attr = _AST.new ASTAttribute("title", val_peek(1).sval);
                                              	newList.add(val_peek(4).obj);
                                              	newList.add(attr);
												yyval.obj = newList; 
											}
break;
case 19:
//#line 216 "xml2json.y"
{ 
										List newList = new ArrayList();
										newList.add(val_peek(1).obj);
										newList.add(val_peek(0).obj);
										yyval.obj = newList;
										/*_AST.new PartItems( (AST.TOC) $1, (List) $2); */
									}
break;
case 20:
//#line 223 "xml2json.y"
{ 
          								/*$$ = _AST.new PartItems( (AST.TOC) $1, (List) $2, (AST.LOF) $3); */
          								List newList = new ArrayList();
										newList.add(val_peek(2).obj);
										newList.add(val_peek(1).obj);
										newList.add(val_peek(0).obj);
										yyval.obj = newList;
          							}
break;
case 21:
//#line 231 "xml2json.y"
{ 
          								/*$$ = _AST.new PartItems( (AST.TOC) $1, (List) $2, (AST.LOT) $3); */
          								List newList = new ArrayList();
										newList.add(val_peek(2).obj);
										newList.add(val_peek(1).obj);
										newList.add(val_peek(0).obj);
										yyval.obj = newList;
          							}
break;
case 22:
//#line 239 "xml2json.y"
{ 
          								/*$$ = _AST.new PartItems( (AST.TOC) $1, (List) $2, (AST.LOF) $3, (AST.LOT) $4); */
          								List newList = new ArrayList();
										newList.add(val_peek(3).obj);
										newList.add(val_peek(2).obj);
										newList.add(val_peek(1).obj);
										newList.add(val_peek(0).obj);
										yyval.obj = newList;
          							}
break;
case 23:
//#line 254 "xml2json.y"
{ yyval.obj = _AST.new TOC( (List) val_peek(2).obj);  }
break;
case 24:
//#line 256 "xml2json.y"
{ yyval.obj = _AST.new LOF( (List) val_peek(2).obj);  }
break;
case 25:
//#line 258 "xml2json.y"
{ yyval.obj = _AST.new LOT( (List) val_peek(2).obj);  }
break;
case 26:
//#line 266 "xml2json.y"
{ 
					List newList = new ArrayList();
                  	newList.add(val_peek(0).obj); 
                  	yyval.obj = newList; 
	            }
break;
case 27:
//#line 271 "xml2json.y"
{
						List l = (List)val_peek(1).obj;
	          			l.add(val_peek(0).obj);
	          			yyval.obj = l;  
	          		}
break;
case 28:
//#line 277 "xml2json.y"
{ 
														List newList = new ArrayList<AST.ASTAttribute>();
														newList.add(val_peek(4).obj);
														yyval.obj = _AST.new Item( newList ,  val_peek(2).sval ); 
													}
break;
case 29:
//#line 290 "xml2json.y"
{
						List newList = new ArrayList();
	                    newList.add(val_peek(0).obj); 
	                    yyval.obj = newList;
					}
break;
case 30:
//#line 295 "xml2json.y"
{
         						List l = (List)val_peek(1).obj;
                      			l.add(val_peek(0).obj);
                      			yyval.obj = l;
					        }
break;
case 31:
//#line 301 "xml2json.y"
{ yyval.obj = _AST.new Chapter( (List) val_peek(4).obj , (List) val_peek(2).obj ); }
break;
case 32:
//#line 303 "xml2json.y"
{
												List newList = new ArrayList<AST.ASTAttribute>();
                                              	AST.ASTAttribute attr = _AST.new ASTAttribute("title", val_peek(1).sval);
                                              	newList.add(val_peek(4).obj);
                                              	newList.add(attr);
												yyval.obj = newList;
											}
break;
case 33:
//#line 318 "xml2json.y"
{ 
					List newList = new ArrayList();
                    newList.add(val_peek(0).obj); 
                    yyval.obj = newList;
                   }
break;
case 34:
//#line 323 "xml2json.y"
{ 
         					 	List l = (List)val_peek(1).obj;
                      			l.add(val_peek(0).obj);
                      			yyval.obj = l;
                  			}
break;
case 35:
//#line 330 "xml2json.y"
{
						 yyval.obj = _AST.new Section( (List) val_peek(4).obj , (List) val_peek(2).obj );
					}
break;
case 36:
//#line 334 "xml2json.y"
{
												List newList = new ArrayList<AST.ASTAttribute>();
                                              	AST.ASTAttribute attr = _AST.new ASTAttribute("title", val_peek(1).sval);
                                              	newList.add(val_peek(4).obj);
                                              	newList.add(attr);
												yyval.obj = newList;
											}
break;
case 37:
//#line 342 "xml2json.y"
{ yyval.obj = new ArrayList(); }
break;
case 38:
//#line 343 "xml2json.y"
{ 
              						List l = (List)val_peek(1).obj;
									l.add(val_peek(0).sval);
									yyval.obj = l;
              					  }
break;
case 39:
//#line 348 "xml2json.y"
{ 
              							List l = (List)val_peek(1).obj;
										l.add(val_peek(0).obj);
										yyval.obj = l; 
									}
break;
case 40:
//#line 353 "xml2json.y"
{ 
              							List l = (List)val_peek(1).obj;
										l.add(val_peek(0).obj);
										yyval.obj = l; 
									 }
break;
case 41:
//#line 358 "xml2json.y"
{ 
              							List l = (List)val_peek(1).obj;
										l.add(val_peek(0).obj);
										yyval.obj = l; 
									}
break;
case 42:
//#line 372 "xml2json.y"
{
												yyval.obj = _AST.new Figure((List)val_peek(2).obj);
											}
break;
case 43:
//#line 376 "xml2json.y"
{ 
												List newList = new ArrayList<AST.ASTAttribute>();
                                              	AST.ASTAttribute attr = _AST.new ASTAttribute("caption", val_peek(1).sval);
                                              	newList.add(val_peek(4).obj);
                                              	newList.add(attr);
												yyval.obj = newList;
											}
break;
case 44:
//#line 383 "xml2json.y"
{
           															List newList = new ArrayList<AST.ASTAttribute>();
					                                              	AST.ASTAttribute caption = _AST.new ASTAttribute("caption", val_peek(5).sval);
					                                              	AST.ASTAttribute path = _AST.new ASTAttribute("path", val_peek(1).sval);
					                                              	newList.add(val_peek(8).obj);
					                                              	newList.add(caption);
					                                              	newList.add(path);
																	yyval.obj = newList;
																}
break;
case 45:
//#line 401 "xml2json.y"
{ yyval.obj = _AST.new Table((List) val_peek(4).obj, (List) val_peek(2).obj);}
break;
case 46:
//#line 403 "xml2json.y"
{ 
                                              List newList = new ArrayList<AST.ASTAttribute>();
                                              AST.ASTAttribute attr = _AST.new ASTAttribute("caption", val_peek(1).sval);
                                              newList.add(val_peek(4).obj);
                                              newList.add(attr);
                                              yyval.obj = newList;    
                                            }
break;
case 47:
//#line 411 "xml2json.y"
{  List newList = new ArrayList();
                    newList.add(val_peek(0).obj); 
                    yyval.obj = newList;  
                  }
break;
case 48:
//#line 415 "xml2json.y"
{
                              List l = (List)val_peek(1).obj;
                              l.add(val_peek(0).obj);
                              yyval.obj = l;
                            }
break;
case 49:
//#line 425 "xml2json.y"
{ yyval.obj = _AST.new Row((List)val_peek(2).obj);}
break;
case 50:
//#line 427 "xml2json.y"
{ 
                     List newList = new ArrayList();
                     newList.add(val_peek(0).obj); 
                     yyval.obj = newList; 
                   }
break;
case 51:
//#line 432 "xml2json.y"
{ 
                      List l = (List)val_peek(1).obj;
                      l.add(val_peek(0).obj);
                      yyval.obj = l;
                   }
break;
case 52:
//#line 438 "xml2json.y"
{ yyval.obj = _AST.new Cell(val_peek(2).sval); }
break;
case 53:
//#line 444 "xml2json.y"
{ yyval.obj = _AST.new AuthorNotes((List)val_peek(2).obj); }
break;
case 54:
//#line 450 "xml2json.y"
{
						List newList = new ArrayList();
                     	newList.add(val_peek(0).obj); 
                     	yyval.obj = newList; 
                    }
break;
case 55:
//#line 455 "xml2json.y"
{ 
      					List l = (List)val_peek(1).obj;
                      	l.add(val_peek(0).obj);
                      	yyval.obj = l;
                    }
break;
case 56:
//#line 462 "xml2json.y"
{ yyval.obj =  _AST.new Note(val_peek(2).sval); }
break;
case 57:
//#line 466 "xml2json.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 58:
//#line 467 "xml2json.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 59:
//#line 469 "xml2json.y"
{ yyval.obj = _AST.new ASTAttribute("id", val_peek(1).sval); }
break;
//#line 954 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
