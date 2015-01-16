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
public final static short EQUAL=264;
public final static short VALUE=265;
public final static short OPEN_BOOK=266;
public final static short OPEN_DEDICATION=267;
public final static short OPEN_PREFACE=268;
public final static short OPEN_PART=269;
public final static short OPEN_TOC=270;
public final static short OPEN_LOF=271;
public final static short OPEN_LOT=272;
public final static short OPEN_ITEM=273;
public final static short OPEN_CHAPTER=274;
public final static short OPEN_SECTION=275;
public final static short OPEN_FIGURE=276;
public final static short OPEN_TABLE=277;
public final static short OPEN_ROW=278;
public final static short OPEN_CELL=279;
public final static short OPEN_AUTHOR=280;
public final static short OPEN_NOTE=281;
public final static short CLOSE_BOOK=282;
public final static short CLOSE_DEDICATION=283;
public final static short CLOSE_PREFACE=284;
public final static short CLOSE_PART=285;
public final static short CLOSE_TOC=286;
public final static short CLOSE_LOF=287;
public final static short CLOSE_LOT=288;
public final static short CLOSE_ITEM=289;
public final static short CLOSE_CHAPTER=290;
public final static short CLOSE_SECTION=291;
public final static short CLOSE_FIGURE=292;
public final static short CLOSE_TABLE=293;
public final static short CLOSE_ROW=294;
public final static short CLOSE_CELL=295;
public final static short CLOSE_AUTHOR=296;
public final static short CLOSE_NOTE=297;
public final static short EDITION=298;
public final static short ID=299;
public final static short TITLE=300;
public final static short CAPTION=301;
public final static short PATH=302;
public final static short sectionsAttr=303;
public final static short figure=304;
public final static short table=305;
public final static short OPEN_VALUE=306;
public final static short CLOSE_VALUE=307;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    2,    3,    4,    4,    5,    5,    6,    7,    8,
   10,   10,   11,   12,   13,   13,   15,   16,   17,   17,
   18,   20,   19,   19,   19,   19,   19,   14,   14,   21,
   22,   22,   23,   23,   24,   26,   26,   25,   25,    9,
    9,   27,   27,    1,
};
final static short yylen[] = {                            2,
    6,    7,    6,    0,    5,    3,    2,    5,    5,    6,
    5,   10,    2,    5,    1,    2,    6,   10,    1,    2,
    6,   10,    0,    2,    2,    2,    2,    1,    2,   10,
    0,    1,    1,    2,    1,    1,    2,    1,    6,    1,
    2,    0,    2,    5,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   44,    0,    0,    1,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    7,    2,    5,    0,    0,
    0,    3,    6,    0,    0,   41,    0,    0,    0,    0,
    8,    9,    0,    0,    0,    0,    0,    0,    0,    0,
   13,    0,    0,    0,    0,    0,   10,    0,    0,   16,
    0,    0,    0,   29,    0,    0,    0,    0,   14,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   20,    0,
    0,    0,    0,   17,   12,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   26,   27,   24,   25,    0,    0,
    0,   21,    0,    0,   30,   18,
};
final static short yydgoto[] = {                          2,
    4,   11,   15,   18,   26,   27,   28,   36,  100,   45,
   55,   56,   61,   65,   62,   69,   82,  101,  102,    0,
   66,    0,    0,    0,    0,    0,    0,
};
final static short yysindex[] = {                      -250,
 -256,    0, -253, -256, -251, -245, -244, -235, -248, -234,
 -241,    0, -238, -269,    0, -232, -231, -229, -230, -227,
 -263, -226, -225, -223, -222, -240, -221, -228, -213, -212,
 -215, -215, -209, -228, -265,    0,    0,    0, -215, -224,
 -220,    0,    0, -211, -207,    0, -206, -205, -203, -208,
    0,    0, -204, -198, -219, -217, -196, -202, -193, -218,
    0, -217, -242, -216, -210, -202,    0, -199, -192,    0,
 -194, -191, -187,    0, -188, -197, -186, -184,    0, -215,
 -214, -200, -197, -183, -181, -178, -175, -174,    0, -172,
 -171, -201, -259,    0,    0, -173, -170, -259, -259, -259,
 -259, -195, -215, -168,    0,    0,    0,    0, -164, -189,
 -215,    0, -163, -162,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -159,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -261,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -182, -157,    0,    0, -180,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -185,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -179,    0,    0,    0,    0, -179, -179, -179,
 -179,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   89,    0,    0,    0,    0,    0,   80,   74,  -31,    0,
    0,    0,   47,   44,    0,    0,   28,  -73,  -81,    0,
    0,    0,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=112;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         40,
   41,   40,   83,   24,   25,   39,    1,   46,    3,   83,
    5,    7,    8,   40,   12,   81,  105,  106,  107,  108,
    9,   40,   40,   10,   14,   13,   16,   40,   17,   40,
   19,   21,   20,   44,   22,   23,   29,   31,   32,   30,
   35,   33,   40,   40,   98,   99,   25,   37,   86,   39,
   38,   42,   49,   50,   51,   52,   60,   71,   47,   53,
   57,   54,   58,   48,   75,   59,   63,   67,   76,   77,
   64,  110,   78,   79,   80,   73,   84,   81,   85,  114,
   68,   90,   72,   91,   92,   93,   94,  103,   87,   88,
   95,   96,    6,  104,  111,  109,  112,  115,   97,  113,
  116,    4,   15,   11,   19,   28,   34,   43,   70,   74,
   89,   23,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         31,
   32,  263,   76,  267,  268,  265,  257,   39,  265,   83,
  264,  263,  258,  275,  263,  275,   98,   99,  100,  101,
  265,  283,  284,  259,  266,  260,  265,  289,  298,  291,
  263,  261,  264,  299,  265,  263,  263,  261,  261,  265,
  269,  282,  304,  305,  304,  305,  268,  261,   80,  265,
  263,  261,  264,  261,  261,  261,  274,  300,  283,  263,
  265,  270,  261,  284,  264,  285,  263,  261,  261,  264,
  273,  103,  264,  261,  263,  286,  263,  275,  263,  111,
  299,  265,  299,  265,  263,  261,  261,  261,  303,  290,
  263,  263,    4,  264,  263,  291,  261,  261,  300,  289,
  263,  261,  285,  261,  290,  286,   27,   34,   62,   66,
   83,  291,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=307;
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
"SLASH","QUOTE","EQUAL","VALUE","OPEN_BOOK","OPEN_DEDICATION","OPEN_PREFACE",
"OPEN_PART","OPEN_TOC","OPEN_LOF","OPEN_LOT","OPEN_ITEM","OPEN_CHAPTER",
"OPEN_SECTION","OPEN_FIGURE","OPEN_TABLE","OPEN_ROW","OPEN_CELL","OPEN_AUTHOR",
"OPEN_NOTE","CLOSE_BOOK","CLOSE_DEDICATION","CLOSE_PREFACE","CLOSE_PART",
"CLOSE_TOC","CLOSE_LOF","CLOSE_LOT","CLOSE_ITEM","CLOSE_CHAPTER",
"CLOSE_SECTION","CLOSE_FIGURE","CLOSE_TABLE","CLOSE_ROW","CLOSE_CELL",
"CLOSE_AUTHOR","CLOSE_NOTE","EDITION","ID","TITLE","CAPTION","PATH",
"sectionsAttr","figure","table","OPEN_VALUE","CLOSE_VALUE",
};
final static String yyrule[] = {
"$accept : xml",
"xml : OPEN_HEADER param param CLOSE_HEADER doctipe book",
"doctipe : OPEN_DOCTIPE BOOK VALUE QUOTE VALUE QUOTE CLOSE",
"book : OPEN_BOOK bookAttr CLOSE bookItems CLOSE_BOOK CLOSE",
"bookAttr :",
"bookAttr : EDITION EQUAL QUOTE VALUE QUOTE",
"bookItems : dedication preface part",
"bookItems : preface part",
"dedication : OPEN_DEDICATION CLOSE string CLOSE_DEDICATION CLOSE",
"preface : OPEN_PREFACE CLOSE string CLOSE_PREFACE CLOSE",
"part : OPEN_PART partAttr CLOSE partItems CLOSE_PART CLOSE",
"partAttr : ID EQUAL QUOTE VALUE QUOTE",
"partAttr : ID EQUAL QUOTE VALUE QUOTE TITLE EQUAL QUOTE VALUE QUOTE",
"partItems : toc chapters",
"toc : OPEN_TOC CLOSE items CLOSE_TOC CLOSE",
"chapters : chapter",
"chapters : chapter chapters",
"chapter : OPEN_CHAPTER chapterAttr CLOSE sections CLOSE_CHAPTER CLOSE",
"chapterAttr : ID EQUAL QUOTE string QUOTE TITLE EQUAL QUOTE string QUOTE",
"sections : section",
"sections : section sections",
"section : OPEN_SECTION sectionsAttr CLOSE sectionsItems CLOSE_SECTION CLOSE",
"sectionAttr : ID EQUAL QUOTE string QUOTE TITLE EQUAL QUOTE string QUOTE",
"sectionsItems :",
"sectionsItems : string sectionsItems",
"sectionsItems : section sectionsItems",
"sectionsItems : figure sectionsItems",
"sectionsItems : table sectionsItems",
"items : item",
"items : item items",
"item : OPEN_ITEM ID EQUAL QUOTE VALUE QUOTE CLOSE string CLOSE_ITEM CLOSE",
"authornotes :",
"authornotes : notes",
"notes : note",
"notes : note notes",
"note : element",
"elements : element",
"elements : element elements",
"element : VALUE",
"element : OPEN_VALUE params CLOSE elements CLOSE_VALUE CLOSE",
"string : VALUE",
"string : VALUE string",
"params :",
"params : param params",
"param : VALUE EQUAL QUOTE VALUE QUOTE",
};

//#line 190 "calc.y"

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
//#line 371 "Parser.java"
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
//#line 76 "calc.y"
{ }
break;
case 2:
//#line 79 "calc.y"
{System.out.println("Fine DOC"); }
break;
case 3:
//#line 89 "calc.y"
{}
break;
case 6:
//#line 94 "calc.y"
{}
break;
case 7:
//#line 95 "calc.y"
{}
break;
case 8:
//#line 100 "calc.y"
{}
break;
case 9:
//#line 105 "calc.y"
{}
break;
case 10:
//#line 114 "calc.y"
{}
break;
case 14:
//#line 124 "calc.y"
{}
break;
case 17:
//#line 136 "calc.y"
{}
break;
case 18:
//#line 138 "calc.y"
{}
break;
case 19:
//#line 147 "calc.y"
{}
break;
case 20:
//#line 148 "calc.y"
{}
break;
case 21:
//#line 150 "calc.y"
{}
break;
case 22:
//#line 152 "calc.y"
{}
break;
case 23:
//#line 154 "calc.y"
{}
break;
case 24:
//#line 155 "calc.y"
{}
break;
case 25:
//#line 156 "calc.y"
{}
break;
case 26:
//#line 157 "calc.y"
{}
break;
case 27:
//#line 158 "calc.y"
{}
break;
case 28:
//#line 162 "calc.y"
{  }
break;
case 29:
//#line 163 "calc.y"
{  }
break;
case 30:
//#line 165 "calc.y"
{ }
break;
case 31:
//#line 167 "calc.y"
{}
break;
case 32:
//#line 168 "calc.y"
{}
break;
case 33:
//#line 170 "calc.y"
{}
break;
case 34:
//#line 171 "calc.y"
{}
break;
case 35:
//#line 173 "calc.y"
{}
break;
case 40:
//#line 181 "calc.y"
{}
break;
case 41:
//#line 182 "calc.y"
{}
break;
case 44:
//#line 187 "calc.y"
{}
break;
//#line 644 "Parser.java"
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
