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






//#line 15 "calc.y"
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
    0,    6,    7,    8,    9,   10,   10,   11,   11,   11,
   11,   12,   13,   14,   14,   16,   17,   17,   18,   18,
   18,   18,   20,   22,   23,   24,   24,   25,   21,   21,
   26,   27,   28,   28,   29,   30,   31,   31,   31,   31,
   31,   32,   34,   34,   33,   35,    5,    5,    4,    3,
    3,    2,   15,   36,   36,   37,    1,    1,   19,
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
    0,    0,    7,    0,    0,    0,    0,    0,    0,    0,
    4,    0,    0,    0,    5,    0,    0,    0,    0,    0,
   10,   15,   58,    0,    0,    8,    0,    0,    0,    0,
   12,   13,    0,    0,    0,    0,    0,    0,    0,    0,
   59,    0,    0,    0,    0,    0,    0,    0,    0,   55,
    0,    0,    0,   16,    0,    0,    0,    0,    0,   21,
   30,   18,    0,   53,    0,    0,   27,    0,    0,    0,
    0,   22,    0,    0,   23,    0,    0,    0,    0,    0,
    0,   56,    0,    0,    0,    0,    0,   34,    0,    0,
    0,   32,    0,    0,   31,   24,   25,   28,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   38,   39,    0,   40,   41,   36,    0,    0,    0,
    0,   35,    0,   42,    0,    0,   47,    0,    0,    0,
    0,    0,   48,    0,   46,    0,   50,    0,   45,    0,
    0,    0,   51,    0,    0,   49,    0,    0,   44,   52,
};
final static short yydgoto[] = {                          2,
  132,  167,  168,  157,  158,    4,    7,   14,   18,   21,
   29,   30,   31,   39,   51,   40,   48,   65,   49,   66,
   75,   89,   90,   82,   83,   76,   86,  108,  133,  116,
  134,  135,  136,  139,  141,   69,   70,
};
final static short yysindex[] = {                      -251,
 -268,    0, -232, -266, -222, -213, -211, -207, -204, -202,
    0, -201, -199, -200,    0, -198, -233,    0, -196, -195,
 -192, -193, -191, -256, -189, -188, -185, -184, -218, -187,
 -190, -182,    0, -183, -183, -179, -190, -215, -194, -190,
    0, -183, -186, -197,    0, -194, -176, -177, -210, -173,
    0,    0,    0, -171, -170,    0, -183, -175, -166, -181,
    0,    0, -165, -169, -180, -172, -164, -168, -178, -181,
    0, -167, -159, -215, -216, -172, -160, -183, -155,    0,
 -215, -174, -167,    0, -163, -154, -153, -152, -161,    0,
    0,    0, -162,    0, -149, -148,    0, -147, -156, -167,
 -167,    0, -146, -183,    0, -183, -215, -158, -156, -157,
 -145,    0, -150, -143, -151, -139, -138,    0, -137, -136,
 -135,    0, -133, -235,    0,    0,    0,    0, -183, -215,
 -215, -235, -235, -144, -235, -235, -131, -142, -134, -141,
 -128,    0,    0, -126,    0,    0,    0, -124, -120, -119,
 -132,    0, -183,    0, -183, -118,    0, -273, -116, -114,
 -127, -111,    0, -140,    0, -109,    0, -271,    0, -110,
 -183, -107,    0, -183, -130,    0, -108, -105,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -104,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -121, -253,
    0, -258,    0,    0,    0, -115,    0,    0,  -99,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -125,
    0,    0,    0,    0, -117, -257,    0,    0,    0,    0,
    0,    0, -234,    0,    0,    0,    0,    0, -113,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -112,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -122,    0,    0,    0,    0,    0,    0,
    0, -122, -122,    0, -122, -122,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -97,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -34,  -54,    0,  -31,    0,    0,    0,    0,    0,    0,
    0,    0,  133,  -25,  123,    0,    0,    0,  -72,    0,
   96,    0,   84,  -80,    0,    0,    0,   65,  -66,    0,
  -87,    0,    0,    0,    0,  105,    0,
};
final static int YYTABLESIZE=177;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         43,
   44,   85,   97,  156,   57,    1,  166,   53,   95,   27,
   28,   46,   29,   29,   52,   57,   57,   57,  162,  110,
  111,  172,   63,   57,   57,   14,   29,   14,   42,   57,
    5,   57,  109,    3,  115,   57,    6,   57,  107,  130,
  131,    8,  109,   93,  142,  143,   10,  145,  146,    9,
   26,   26,   26,   87,   88,   11,   13,  138,  140,   12,
   16,   15,   36,   20,   17,   19,   22,   23,   24,  113,
   25,  114,   26,   32,   33,   34,   35,   38,   41,   28,
   42,   45,   47,   58,   50,   55,   57,   60,   59,   61,
   62,   72,   78,   64,  137,   54,   67,   71,   68,   77,
   74,   84,   92,   73,   81,   94,   99,  100,  101,   88,
   96,  104,  105,  173,  112,  106,   79,  107,  159,  122,
  160,  124,  125,  126,  127,  128,  163,  149,  119,  129,
  117,  147,  151,  103,  152,   98,  175,  121,  153,  177,
  154,  120,  161,  155,  156,  144,  164,  123,  165,  169,
  166,  171,  174,  176,  179,  180,    6,  148,  150,   11,
  170,   17,   37,  178,   43,    9,   19,   37,   56,   54,
   20,   91,  102,  118,   80,    0,   33,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         34,
   35,   74,   83,  277,  263,  257,  278,   42,   81,  266,
  267,   37,  270,  271,   40,  274,  275,  276,  292,  100,
  101,  293,   57,  282,  283,  279,  284,  281,  264,  288,
  263,  290,   99,  302,  107,  294,  303,  296,  274,  275,
  276,  264,  109,   78,  132,  133,  258,  135,  136,  263,
  285,  286,  287,  270,  271,  263,  259,  130,  131,  264,
  260,  263,  281,  297,  265,  264,  263,  263,  261,  104,
  264,  106,  264,  263,  263,  261,  261,  268,  261,  267,
  264,  261,  298,  261,  279,  283,  263,  261,  299,  261,
  261,  261,  261,  269,  129,  282,  263,  263,  280,  264,
  273,  261,  263,  284,  272,  261,  261,  261,  261,  271,
  285,  261,  261,  168,  261,  263,  295,  274,  153,  263,
  155,  261,  261,  261,  261,  261,  158,  262,  286,  263,
  289,  263,  261,  296,  261,  299,  171,  288,  263,  174,
  261,  287,  261,  263,  277,  290,  263,  299,  263,  261,
  278,  261,  263,  261,  263,  261,  261,  300,  300,  281,
  301,  261,   30,  294,  262,  281,  284,  290,   46,  295,
  284,   76,   89,  109,   70,   -1,  289,
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
"bookAttr : EDITION QUOTE VALUE QUOTE",
"bookItems : dedication preface parts authornotes",
"bookItems : dedication preface parts",
"bookItems : preface parts authornotes",
"bookItems : preface parts",
"dedication : OPEN_DEDICATION CLOSE str CLOSE_DEDICATION CLOSE",
"preface : OPEN_PREFACE CLOSE str CLOSE_PREFACE CLOSE",
"parts : part",
"parts : part parts",
"part : OPEN_PART partAttr CLOSE partItems CLOSE_PART CLOSE",
"partAttr : idAttr",
"partAttr : idAttr TITLE QUOTE VALUE QUOTE",
"partItems : toc chapters",
"partItems : toc chapters lof",
"partItems : toc chapters lot",
"partItems : toc chapters lof lot",
"toc : OPEN_TOC CLOSE items CLOSE_TOC CLOSE",
"lof : OPEN_LOF CLOSE items CLOSE_LOF CLOSE",
"lot : OPEN_LOT CLOSE items CLOSE_LOT CLOSE",
"items : item",
"items : item items",
"item : OPEN_ITEM idAttr CLOSE str CLOSE_ITEM CLOSE",
"chapters : chapter",
"chapters : chapter chapters",
"chapter : OPEN_CHAPTER chapterAttr CLOSE sections CLOSE_CHAPTER CLOSE",
"chapterAttr : idAttr TITLE QUOTE str QUOTE",
"sections : section",
"sections : section sections",
"section : OPEN_SECTION sectionAttr CLOSE sectionsItems CLOSE_SECTION CLOSE",
"sectionAttr : idAttr TITLE QUOTE str QUOTE",
"sectionsItems :",
"sectionsItems : str sectionsItems",
"sectionsItems : section sectionsItems",
"sectionsItems : figure sectionsItems",
"sectionsItems : table sectionsItems",
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
"notes : note notes",
"note : OPEN_NOTE CLOSE str CLOSE_NOTE CLOSE",
"str : VALUE",
"str : VALUE str",
"idAttr : ID QUOTE str QUOTE",
};

//#line 276 "calc.y"

  private Yylex lexer;
  public static ASTHandler handler = new ASTHandler();
  public static AST _AST = new AST();

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
//#line 393 "Parser.java"
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
//#line 87 "calc.y"
{ }
break;
case 2:
//#line 89 "calc.y"
{}
break;
case 3:
//#line 91 "calc.y"
{}
break;
case 4:
//#line 93 "calc.y"
{  }
break;
case 5:
//#line 103 "calc.y"
{}
break;
case 8:
//#line 108 "calc.y"
{}
break;
case 9:
//#line 109 "calc.y"
{}
break;
case 10:
//#line 110 "calc.y"
{}
break;
case 11:
//#line 111 "calc.y"
{}
break;
case 12:
//#line 116 "calc.y"
{}
break;
case 13:
//#line 121 "calc.y"
{}
break;
case 14:
//#line 130 "calc.y"
{}
break;
case 15:
//#line 131 "calc.y"
{}
break;
case 16:
//#line 133 "calc.y"
{}
break;
case 23:
//#line 148 "calc.y"
{}
break;
case 24:
//#line 150 "calc.y"
{}
break;
case 25:
//#line 152 "calc.y"
{}
break;
case 26:
//#line 160 "calc.y"
{  }
break;
case 27:
//#line 161 "calc.y"
{  }
break;
case 28:
//#line 163 "calc.y"
{ }
break;
case 31:
//#line 175 "calc.y"
{}
break;
case 32:
//#line 177 "calc.y"
{}
break;
case 33:
//#line 186 "calc.y"
{}
break;
case 34:
//#line 187 "calc.y"
{}
break;
case 35:
//#line 189 "calc.y"
{}
break;
case 36:
//#line 191 "calc.y"
{}
break;
case 37:
//#line 193 "calc.y"
{}
break;
case 38:
//#line 194 "calc.y"
{}
break;
case 39:
//#line 195 "calc.y"
{}
break;
case 40:
//#line 196 "calc.y"
{}
break;
case 41:
//#line 197 "calc.y"
{}
break;
case 42:
//#line 207 "calc.y"
{}
break;
case 43:
//#line 209 "calc.y"
{}
break;
case 44:
//#line 210 "calc.y"
{}
break;
case 45:
//#line 220 "calc.y"
{}
break;
case 47:
//#line 224 "calc.y"
{  List newList = new ArrayList();
                    newList.add(val_peek(0).obj); 
                    yyval.obj = newList;  
                  }
break;
case 48:
//#line 228 "calc.y"
{
                              List l = (List)val_peek(1).obj;
                              l.add(val_peek(0).obj);
                            }
break;
case 49:
//#line 237 "calc.y"
{ yyval.obj = _AST.new Row((List)val_peek(2).obj);}
break;
case 50:
//#line 239 "calc.y"
{ 
                     List newList = new ArrayList();
                     newList.add(val_peek(0).obj); 
                     yyval.obj = newList; 
                   }
break;
case 51:
//#line 244 "calc.y"
{ 
                      List l = (List)val_peek(1).obj;
                      l.add(val_peek(0).obj);
                   }
break;
case 52:
//#line 249 "calc.y"
{ yyval.obj = _AST.new Cell(val_peek(2).sval); }
break;
case 53:
//#line 255 "calc.y"
{}
break;
case 54:
//#line 261 "calc.y"
{}
break;
case 55:
//#line 262 "calc.y"
{}
break;
case 56:
//#line 265 "calc.y"
{  }
break;
case 57:
//#line 269 "calc.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 58:
//#line 270 "calc.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
//#line 743 "Parser.java"
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
