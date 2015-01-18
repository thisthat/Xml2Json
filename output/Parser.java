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
//#line 19 "Parser.java"




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
    0,    2,    3,    4,    5,    6,    6,    7,    7,    7,
    7,    8,    9,   10,   10,   12,   13,   13,   14,   14,
   14,   14,   16,   18,   19,   20,   20,   21,   17,   17,
   22,   23,   24,   24,   25,   26,   27,   27,   27,   27,
   27,   28,   30,   30,   29,   31,   32,   32,   34,   33,
   35,   35,   36,   11,   37,   37,   38,    1,    1,   15,
};
final static short yylen[] = {                            2,
    6,    4,    4,    7,    6,    0,    4,    4,    3,    3,
    2,    5,    5,    1,    2,    6,    1,    5,    2,    3,
    3,    4,    5,    5,    5,    1,    2,    6,    1,    2,
    6,    5,    1,    2,    6,    5,    0,    2,    2,    2,
    2,    4,    5,    9,    6,    5,    1,    2,    0,    6,
    1,    2,    5,    5,    1,    2,    5,    1,    2,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    2,    0,    0,    0,    3,    0,    0,    1,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    7,    0,    0,    0,    0,    0,    0,    0,
    4,    0,    0,    0,    5,    0,    0,    0,    0,    0,
   10,   15,   59,    0,    0,    8,    0,    0,    0,    0,
   12,   13,    0,    0,    0,    0,    0,    0,    0,    0,
   60,    0,    0,    0,    0,    0,    0,    0,    0,   56,
    0,    0,    0,   16,    0,    0,    0,    0,    0,   21,
   30,   18,    0,   54,    0,    0,   27,    0,    0,    0,
    0,   22,    0,    0,   23,    0,    0,    0,    0,    0,
    0,   57,    0,    0,    0,    0,    0,   34,    0,    0,
    0,   32,    0,    0,   31,   24,   25,   28,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   38,   39,    0,   40,   41,   36,    0,    0,    0,
    0,   35,    0,   42,    0,   49,    0,    0,    0,    0,
    0,    0,   48,    0,   46,    0,   45,    0,    0,    0,
    0,    0,    0,    0,   52,    0,    0,   50,   44,    0,
   53,
};
final static short yydgoto[] = {                          2,
  132,    4,    7,   14,   18,   21,   29,   30,   31,   39,
   51,   40,   48,   65,   49,   66,   75,   89,   90,   82,
   83,   76,   86,  108,  133,  116,  134,  135,  136,  139,
  141,  157,  158,  161,  170,  171,   69,   70,
};
final static short yysindex[] = {                      -251,
 -287,    0, -244, -272, -227, -213, -207, -211, -210, -206,
    0, -208, -204, -205,    0, -203, -240,    0, -201, -200,
 -197, -199, -198, -256, -196, -195, -192, -190, -202, -194,
 -193, -187,    0, -188, -188, -184, -193, -220, -191, -193,
    0, -188, -189, -186,    0, -191, -183, -180, -217, -178,
    0,    0,    0, -177, -176,    0, -188, -182, -174, -181,
    0,    0, -173, -175, -179, -172, -170, -169, -185, -181,
    0, -168, -165, -220, -237, -172, -163, -188, -159,    0,
 -220, -171, -168,    0, -167, -158, -155, -154, -162,    0,
    0,    0, -166,    0, -153, -150,    0, -151, -161, -168,
 -168,    0, -146, -188,    0, -188, -220, -164, -161, -160,
 -156,    0, -152, -147, -157, -144, -143,    0, -141, -139,
 -138,    0, -136, -235,    0,    0,    0,    0, -188, -220,
 -220, -235, -235, -149, -235, -235, -135, -209, -133, -148,
 -137,    0,    0, -128,    0,    0,    0, -129, -126, -123,
 -140,    0, -188,    0, -188,    0, -145, -140, -120, -119,
 -116, -115,    0, -142,    0, -130,    0, -114, -111, -132,
 -130, -188, -188, -110,    0, -109, -134,    0,    0, -108,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -106,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -125, -253,
    0, -258,    0,    0,    0, -124,    0,    0, -103,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -131,
    0,    0,    0,    0, -122, -257,    0,    0,    0,    0,
    0,    0, -238,    0,    0,    0,    0,    0, -121,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -118,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -117,    0,    0,    0,    0,    0,    0,
    0, -117, -117,    0, -117, -117,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -127,    0,    0,
    0,    0,    0,  -96,    0,    0,    0,    0,    0,    0,
 -113,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
  -34,    0,    0,    0,    0,    0,    0,    0,   68,  -33,
  121,    0,    0,    0,  -72,    0,   92,    0,   80,  -80,
    0,    0,    0,   61,  -87,    0,  -90,    0,    0,    0,
    0,   14,    0,    0,    3,    0,  105,    0,
};
final static int YYTABLESIZE=180;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         43,
   44,   85,   97,   46,   58,    1,   52,   53,   95,   27,
   28,  109,   29,   29,    3,   58,   58,   58,    5,  110,
  111,  109,   63,   58,   58,   14,   29,   14,   42,   58,
    6,   58,   87,   88,  115,   58,    8,   58,  107,  130,
  131,  142,  143,   93,  145,  146,   26,   26,   26,    9,
   10,   11,   13,   12,   15,   16,   20,  138,  140,   17,
   19,   22,   23,   24,   25,   26,   32,   33,   34,  113,
   35,  114,   28,   41,   38,   42,   45,   47,   36,   57,
   58,   59,   60,   61,   62,   72,   64,   50,   67,   71,
  148,   78,   54,   77,  137,   84,   55,   37,   68,   92,
   74,   94,   99,   81,   73,  100,  101,  104,   88,   79,
  105,  106,  107,   96,  112,  122,  124,  125,  159,  126,
  160,  127,  128,  151,  117,  119,  129,  147,  149,  103,
  120,   98,  152,  153,  154,  121,  156,  176,  177,  155,
  144,  123,  164,  165,  166,  167,  162,  169,  172,  173,
  178,  150,  181,  179,    6,   11,    9,   17,  168,  180,
  174,   19,   20,   55,   47,   43,   56,   91,  102,  118,
   33,  163,   37,  175,   80,    0,    0,    0,    0,   51,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         34,
   35,   74,   83,   37,  263,  257,   40,   42,   81,  266,
  267,   99,  270,  271,  302,  274,  275,  276,  263,  100,
  101,  109,   57,  282,  283,  279,  284,  281,  264,  288,
  303,  290,  270,  271,  107,  294,  264,  296,  274,  275,
  276,  132,  133,   78,  135,  136,  285,  286,  287,  263,
  258,  263,  259,  264,  263,  260,  297,  130,  131,  265,
  264,  263,  263,  261,  264,  264,  263,  263,  261,  104,
  261,  106,  267,  261,  268,  264,  261,  298,  281,  263,
  261,  299,  261,  261,  261,  261,  269,  279,  263,  263,
  300,  261,  282,  264,  129,  261,  283,   30,  280,  263,
  273,  261,  261,  272,  284,  261,  261,  261,  271,  295,
  261,  263,  274,  285,  261,  263,  261,  261,  153,  261,
  155,  261,  261,  261,  289,  286,  263,  263,  262,  296,
  287,  299,  261,  263,  261,  288,  277,  172,  173,  263,
  290,  299,  263,  263,  261,  261,  292,  278,  263,  261,
  261,  300,  261,  263,  261,  281,  281,  261,  301,  294,
  293,  284,  284,  295,  292,  262,   46,   76,   89,  109,
  289,  158,  290,  171,   70,   -1,   -1,   -1,   -1,  293,
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
"tableItems : row tableItems",
"$$1 :",
"row : OPEN_ROW $$1 CLOSE cells CLOSE_ROW CLOSE",
"cells : cell",
"cells : cell cells",
"cell : OPEN_CELL CLOSE str CLOSE_CELL CLOSE",
"authornotes : OPEN_AUTHOR CLOSE notes CLOSE_AUTHOR CLOSE",
"notes : note",
"notes : note notes",
"note : OPEN_NOTE CLOSE str CLOSE_NOTE CLOSE",
"str : VALUE",
"str : VALUE str",
"idAttr : ID QUOTE str QUOTE",
};

//#line 254 "calc.y"

  private Yylex lexer;
  public static ASTHandler handler = new ASTHandler();

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
//#line 395 "Parser.java"
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
//#line 78 "calc.y"
{ System.out.println("Fine DOC"); }
break;
case 2:
//#line 80 "calc.y"
{}
break;
case 3:
//#line 82 "calc.y"
{}
break;
case 4:
//#line 84 "calc.y"
{  }
break;
case 5:
//#line 94 "calc.y"
{}
break;
case 8:
//#line 99 "calc.y"
{}
break;
case 9:
//#line 100 "calc.y"
{}
break;
case 10:
//#line 101 "calc.y"
{}
break;
case 11:
//#line 102 "calc.y"
{}
break;
case 12:
//#line 107 "calc.y"
{}
break;
case 13:
//#line 112 "calc.y"
{}
break;
case 14:
//#line 121 "calc.y"
{}
break;
case 15:
//#line 122 "calc.y"
{}
break;
case 16:
//#line 124 "calc.y"
{}
break;
case 23:
//#line 139 "calc.y"
{}
break;
case 24:
//#line 141 "calc.y"
{}
break;
case 25:
//#line 143 "calc.y"
{}
break;
case 26:
//#line 151 "calc.y"
{  }
break;
case 27:
//#line 152 "calc.y"
{  }
break;
case 28:
//#line 154 "calc.y"
{ }
break;
case 31:
//#line 166 "calc.y"
{}
break;
case 32:
//#line 168 "calc.y"
{}
break;
case 33:
//#line 177 "calc.y"
{}
break;
case 34:
//#line 178 "calc.y"
{}
break;
case 35:
//#line 180 "calc.y"
{}
break;
case 36:
//#line 182 "calc.y"
{}
break;
case 37:
//#line 184 "calc.y"
{}
break;
case 38:
//#line 185 "calc.y"
{}
break;
case 39:
//#line 186 "calc.y"
{}
break;
case 40:
//#line 187 "calc.y"
{}
break;
case 41:
//#line 188 "calc.y"
{}
break;
case 42:
//#line 198 "calc.y"
{}
break;
case 43:
//#line 200 "calc.y"
{}
break;
case 44:
//#line 201 "calc.y"
{}
break;
case 45:
//#line 211 "calc.y"
{}
break;
case 47:
//#line 215 "calc.y"
{}
break;
case 48:
//#line 216 "calc.y"
{}
break;
case 49:
//#line 222 "calc.y"
{System.out.println("Creo"); int i = 0;}
break;
case 50:
//#line 222 "calc.y"
{System.out.println("Riduco" + i);}
break;
case 53:
//#line 227 "calc.y"
{}
break;
case 54:
//#line 233 "calc.y"
{}
break;
case 55:
//#line 239 "calc.y"
{}
break;
case 56:
//#line 240 "calc.y"
{}
break;
case 57:
//#line 243 "calc.y"
{ handler.addNote(val_peek(2).sval); }
break;
case 58:
//#line 247 "calc.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 59:
//#line 248 "calc.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
//#line 728 "Parser.java"
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
