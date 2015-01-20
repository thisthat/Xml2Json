/* The following code was generated by JFlex 1.4.3 on 20/01/15 19.41 */

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2000 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 20/01/15 19.41 from the specification file
 * <tt>calc.flex</tt>
 */
class Yylex {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\51\1\1\2\0\1\2\22\0\1\51\1\11\1\46\4\0"+
    "\1\46\4\0\1\50\2\47\1\43\12\47\1\0\1\47\1\3\1\44"+
    "\1\10\1\4\1\0\2\47\1\14\1\12\1\20\11\47\1\13\1\17"+
    "\3\47\1\15\4\47\1\16\7\47\1\30\1\21\1\27\1\24\1\25"+
    "\1\35\1\40\1\36\1\26\1\47\1\23\1\7\1\6\1\32\1\22"+
    "\1\33\1\47\1\34\1\37\1\31\1\41\1\45\1\42\1\5\2\47"+
    "\uff85\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\2\2\2\1\1\3\1\4\6\3\1\5"+
    "\1\3\1\6\1\7\17\0\1\10\10\3\35\0\3\3"+
    "\1\11\4\3\1\0\1\12\1\13\7\0\1\14\4\0"+
    "\1\15\21\0\1\16\6\3\1\17\1\0\1\20\1\0"+
    "\1\21\1\22\3\0\1\23\1\24\3\0\1\25\1\26"+
    "\6\0\1\27\4\0\1\30\2\0\4\3\1\31\1\3"+
    "\4\0\1\32\3\0\1\33\1\0\1\34\1\35\3\0"+
    "\1\36\1\37\3\0\3\3\1\40\1\3\5\0\1\41"+
    "\4\0\1\42\3\0\4\3\2\0\1\43\1\0\1\44"+
    "\1\45\4\0\1\46\1\0\1\47\1\3\1\50\1\51"+
    "\1\52\3\0\1\53\1\0\1\54\1\55\1\56\4\0"+
    "\1\57\3\0\1\60\1\61\1\0\1\62";

  private static int [] zzUnpackAction() {
    int [] result = new int[235];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\52\0\52\0\124\0\176\0\250\0\322\0\52"+
    "\0\374\0\u0126\0\u0150\0\u017a\0\u01a4\0\u01ce\0\52\0\u01f8"+
    "\0\52\0\u0222\0\u024c\0\u0276\0\u02a0\0\u02ca\0\u02f4\0\u031e"+
    "\0\u0348\0\u0372\0\u039c\0\u03c6\0\u03f0\0\u041a\0\u0444\0\u046e"+
    "\0\u0498\0\52\0\u04c2\0\u04ec\0\u0516\0\u0540\0\u056a\0\u0594"+
    "\0\u05be\0\u05e8\0\u0612\0\u063c\0\u0666\0\u0690\0\u06ba\0\u06e4"+
    "\0\u070e\0\u0738\0\u0762\0\u078c\0\u07b6\0\u07e0\0\u080a\0\u0834"+
    "\0\u085e\0\u0888\0\u08b2\0\u08dc\0\u0906\0\u0930\0\u095a\0\u0984"+
    "\0\u09ae\0\u09d8\0\u0a02\0\u0a2c\0\u0a56\0\u0a80\0\u0aaa\0\u0ad4"+
    "\0\u0afe\0\u0b28\0\52\0\u0b52\0\u0b7c\0\u0ba6\0\u0bd0\0\u0bfa"+
    "\0\52\0\52\0\u0c24\0\u0c4e\0\u0c78\0\u0ca2\0\u0ccc\0\u0cf6"+
    "\0\u0d20\0\52\0\u0d4a\0\u0d74\0\u0d9e\0\u0dc8\0\52\0\u0df2"+
    "\0\u0e1c\0\u0e46\0\u0e70\0\u0e9a\0\u0ec4\0\u0eee\0\u0f18\0\u0f42"+
    "\0\u0f6c\0\u0f96\0\u0fc0\0\u0fea\0\u1014\0\u103e\0\u1068\0\u1092"+
    "\0\322\0\u10bc\0\u10e6\0\u1110\0\u113a\0\u1164\0\u118e\0\52"+
    "\0\u11b8\0\52\0\u11e2\0\52\0\52\0\u120c\0\u1236\0\u1260"+
    "\0\52\0\52\0\u128a\0\u12b4\0\u12de\0\52\0\52\0\u1308"+
    "\0\u1332\0\u135c\0\u1386\0\u13b0\0\u13da\0\52\0\u1404\0\u142e"+
    "\0\u1458\0\u1482\0\52\0\u14ac\0\u14d6\0\u1500\0\u152a\0\u1554"+
    "\0\u157e\0\52\0\u15a8\0\u15d2\0\u15fc\0\u1626\0\u1650\0\52"+
    "\0\u167a\0\u16a4\0\u16ce\0\52\0\u16f8\0\52\0\52\0\u1722"+
    "\0\u174c\0\u1776\0\52\0\52\0\u17a0\0\u17ca\0\u17f4\0\u181e"+
    "\0\u1848\0\u1872\0\52\0\u189c\0\u18c6\0\u18f0\0\u191a\0\u1944"+
    "\0\u196e\0\52\0\u1998\0\u19c2\0\u19ec\0\u1a16\0\52\0\u1a40"+
    "\0\u1a6a\0\u1a94\0\u1abe\0\u1ae8\0\u1b12\0\u1b3c\0\u1b66\0\u1b90"+
    "\0\52\0\u1bba\0\52\0\52\0\u1be4\0\u1c0e\0\u1c38\0\u1c62"+
    "\0\52\0\u1c8c\0\52\0\u1cb6\0\52\0\52\0\52\0\u1ce0"+
    "\0\u1d0a\0\u1d34\0\52\0\u1d5e\0\52\0\52\0\52\0\u1d88"+
    "\0\u1db2\0\u1ddc\0\u1e06\0\52\0\u1e30\0\u1e5a\0\u1e84\0\52"+
    "\0\52\0\u1eae\0\52";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[235];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\3\7\1\10\1\2"+
    "\7\7\1\11\3\7\1\12\1\13\1\14\1\7\1\15"+
    "\1\7\1\16\7\7\1\17\1\2\1\20\1\21\1\7"+
    "\1\2\1\22\53\0\1\3\54\0\1\23\2\0\1\24"+
    "\1\0\1\25\7\0\1\26\2\0\1\27\1\0\1\30"+
    "\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\0"+
    "\1\40\3\0\1\41\16\0\1\42\46\0\3\7\2\0"+
    "\31\7\2\0\1\7\1\0\2\7\6\0\3\7\2\0"+
    "\10\7\1\43\20\7\2\0\1\7\1\0\2\7\6\0"+
    "\3\7\2\0\12\7\1\44\5\7\1\45\10\7\2\0"+
    "\1\7\1\0\2\7\6\0\3\7\2\0\12\7\1\46"+
    "\16\7\2\0\1\7\1\0\2\7\6\0\3\7\2\0"+
    "\16\7\1\47\12\7\2\0\1\7\1\0\2\7\6\0"+
    "\3\7\2\0\14\7\1\50\14\7\2\0\1\7\1\0"+
    "\2\7\6\0\3\7\2\0\16\7\1\51\12\7\2\0"+
    "\1\7\1\0\2\7\6\0\3\7\2\0\13\7\1\52"+
    "\15\7\2\0\1\7\1\0\2\7\52\0\1\22\5\0"+
    "\1\53\66\0\1\54\41\0\1\55\61\0\1\56\54\0"+
    "\1\57\55\0\1\60\45\0\1\61\10\0\1\62\54\0"+
    "\1\63\32\0\1\64\5\0\1\65\43\0\1\66\57\0"+
    "\1\67\3\0\1\70\37\0\1\71\55\0\1\72\50\0"+
    "\1\73\33\0\1\74\11\0\1\75\2\0\1\76\1\0"+
    "\1\77\1\100\1\101\1\102\1\103\1\104\1\105\1\106"+
    "\1\0\1\107\17\0\3\7\2\0\10\7\1\110\20\7"+
    "\2\0\1\7\1\0\2\7\6\0\3\7\2\0\14\7"+
    "\1\111\14\7\2\0\1\7\1\0\2\7\6\0\3\7"+
    "\2\0\15\7\1\112\13\7\2\0\1\7\1\0\2\7"+
    "\6\0\3\7\2\0\31\7\1\0\1\113\1\7\1\0"+
    "\2\7\6\0\3\7\2\0\21\7\1\114\7\7\2\0"+
    "\1\7\1\0\2\7\6\0\3\7\2\0\17\7\1\115"+
    "\11\7\2\0\1\7\1\0\2\7\6\0\3\7\2\0"+
    "\17\7\1\116\11\7\2\0\1\7\1\0\2\7\6\0"+
    "\3\7\2\0\22\7\1\117\6\7\2\0\1\7\1\0"+
    "\2\7\7\0\1\120\74\0\1\121\3\0\1\122\27\0"+
    "\1\123\60\0\1\124\53\0\1\125\52\0\1\126\33\0"+
    "\1\127\72\0\1\130\52\0\1\131\47\0\1\132\43\0"+
    "\1\133\61\0\1\134\54\0\1\135\42\0\1\136\66\0"+
    "\1\137\47\0\1\140\40\0\1\141\44\0\1\142\51\0"+
    "\1\143\54\0\1\144\55\0\1\145\45\0\1\146\10\0"+
    "\1\147\54\0\1\150\32\0\1\151\5\0\1\152\43\0"+
    "\1\153\57\0\1\154\3\0\1\155\37\0\1\156\55\0"+
    "\1\157\50\0\1\160\31\0\3\7\2\0\11\7\1\161"+
    "\17\7\2\0\1\7\1\0\2\7\6\0\3\7\2\0"+
    "\17\7\1\162\11\7\2\0\1\7\1\0\2\7\6\0"+
    "\3\7\2\0\10\7\1\163\20\7\2\0\1\7\1\0"+
    "\2\7\6\0\3\7\2\0\17\7\1\164\11\7\2\0"+
    "\1\7\1\0\2\7\6\0\2\7\1\165\2\0\31\7"+
    "\2\0\1\7\1\0\2\7\6\0\3\7\2\0\24\7"+
    "\1\166\4\7\2\0\1\7\1\0\2\7\6\0\3\7"+
    "\2\0\25\7\1\167\3\7\2\0\1\7\1\0\2\7"+
    "\10\0\1\170\56\0\1\171\60\0\1\172\54\0\1\173"+
    "\31\0\1\174\52\0\1\175\75\0\1\176\54\0\1\177"+
    "\22\0\1\200\67\0\1\201\55\0\1\202\55\0\1\203"+
    "\55\0\1\204\41\0\1\205\51\0\1\206\3\0\1\207"+
    "\36\0\1\210\53\0\1\211\52\0\1\212\33\0\1\213"+
    "\72\0\1\214\52\0\1\215\47\0\1\216\43\0\1\217"+
    "\61\0\1\220\54\0\1\221\42\0\1\222\66\0\1\223"+
    "\47\0\1\224\40\0\1\225\27\0\3\7\2\0\14\7"+
    "\1\226\14\7\2\0\1\7\1\0\2\7\6\0\3\7"+
    "\2\0\12\7\1\227\16\7\2\0\1\7\1\0\2\7"+
    "\6\0\3\7\2\0\14\7\1\230\14\7\2\0\1\7"+
    "\1\0\2\7\6\0\3\7\2\0\13\7\1\231\15\7"+
    "\2\0\1\7\1\0\2\7\6\0\3\7\2\0\31\7"+
    "\1\0\1\232\1\7\1\0\2\7\6\0\3\7\2\0"+
    "\14\7\1\233\14\7\2\0\1\7\1\0\2\7\16\0"+
    "\1\234\63\0\1\235\53\0\1\236\42\0\1\237\54\0"+
    "\1\240\54\0\1\241\55\0\1\242\43\0\1\243\46\0"+
    "\1\244\54\0\1\245\31\0\1\246\52\0\1\247\75\0"+
    "\1\250\54\0\1\251\22\0\1\252\67\0\1\253\55\0"+
    "\1\254\55\0\1\255\55\0\1\256\41\0\1\257\25\0"+
    "\3\7\2\0\10\7\1\260\20\7\2\0\1\7\1\0"+
    "\2\7\6\0\3\7\2\0\14\7\1\261\14\7\2\0"+
    "\1\7\1\0\2\7\6\0\3\7\2\0\10\7\1\262"+
    "\20\7\2\0\1\7\1\0\2\7\6\0\3\7\2\0"+
    "\31\7\1\0\1\263\1\7\1\0\2\7\6\0\3\7"+
    "\2\0\10\7\1\264\20\7\2\0\1\7\1\0\2\7"+
    "\17\0\1\265\63\0\1\266\46\0\1\267\60\0\1\270"+
    "\44\0\1\271\47\0\1\272\46\0\1\273\56\0\1\274"+
    "\53\0\1\275\42\0\1\276\54\0\1\277\54\0\1\300"+
    "\55\0\1\301\43\0\1\302\30\0\3\7\2\0\20\7"+
    "\1\303\10\7\2\0\1\7\1\0\2\7\6\0\3\7"+
    "\2\0\20\7\1\304\10\7\2\0\1\7\1\0\2\7"+
    "\6\0\3\7\2\0\20\7\1\305\10\7\2\0\1\7"+
    "\1\0\2\7\6\0\3\7\2\0\20\7\1\306\10\7"+
    "\2\0\1\7\1\0\2\7\20\0\1\307\63\0\1\310"+
    "\54\0\1\311\47\0\1\312\44\0\1\313\56\0\1\314"+
    "\47\0\1\315\46\0\1\316\60\0\1\317\44\0\1\320"+
    "\47\0\1\321\46\0\1\322\34\0\3\7\2\0\31\7"+
    "\1\0\1\323\1\7\1\0\2\7\6\0\3\7\2\0"+
    "\26\7\1\324\2\7\2\0\1\7\1\0\2\7\6\0"+
    "\3\7\2\0\31\7\1\0\1\325\1\7\1\0\2\7"+
    "\6\0\3\7\2\0\31\7\1\0\1\326\1\7\1\0"+
    "\2\7\21\0\1\327\57\0\1\330\45\0\1\331\60\0"+
    "\1\332\54\0\1\333\47\0\1\334\44\0\1\335\56\0"+
    "\1\336\24\0\3\7\2\0\31\7\1\0\1\337\1\7"+
    "\1\0\2\7\23\0\1\340\60\0\1\341\46\0\1\342"+
    "\45\0\1\343\61\0\1\344\44\0\1\345\46\0\1\346"+
    "\60\0\1\347\57\0\1\350\44\0\1\351\44\0\1\352"+
    "\63\0\1\353\12\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[7896];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\2\11\4\1\1\11\6\1\1\11\1\1\1\11"+
    "\1\1\17\0\1\11\10\1\35\0\3\1\1\11\4\1"+
    "\1\0\2\11\7\0\1\11\4\0\1\11\21\0\7\1"+
    "\1\11\1\0\1\11\1\0\2\11\3\0\2\11\3\0"+
    "\2\11\6\0\1\11\4\0\1\11\2\0\4\1\1\11"+
    "\1\1\4\0\1\11\3\0\1\11\1\0\2\11\3\0"+
    "\2\11\3\0\3\1\1\11\1\1\5\0\1\11\4\0"+
    "\1\11\3\0\4\1\2\0\1\11\1\0\2\11\4\0"+
    "\1\11\1\0\1\11\1\1\3\11\3\0\1\11\1\0"+
    "\3\11\4\0\1\11\3\0\2\11\1\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[235];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */

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

  public void print(){
  	System.out.println(yytext());
  }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Yylex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Yylex(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 122) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 13: 
          { return Parser.OPEN_ROW;
          }
        case 51: break;
        case 9: 
          { return Parser.ID;
          }
        case 52: break;
        case 22: 
          { return Parser.CLOSE_LOF;
          }
        case 53: break;
        case 18: 
          { return Parser.OPEN_CELL;
          }
        case 54: break;
        case 17: 
          { return Parser.OPEN_ITEM;
          }
        case 55: break;
        case 15: 
          { return Parser.OPEN_HEADER;
          }
        case 56: break;
        case 11: 
          { return Parser.OPEN_LOF;
          }
        case 57: break;
        case 49: 
          { return Parser.CLOSE_DEDICATION;
          }
        case 58: break;
        case 2: 
          { _line_cnt++;
          }
        case 59: break;
        case 46: 
          { return Parser.ENCODING;
          }
        case 60: break;
        case 41: 
          { return Parser.VERSION;
          }
        case 61: break;
        case 27: 
          { return Parser.CLOSE_BOOK;
          }
        case 62: break;
        case 33: 
          { return Parser.OPEN_FIGURE;
          }
        case 63: break;
        case 26: 
          { return Parser.OPEN_TABLE;
          }
        case 64: break;
        case 25: 
          { return Parser.PATH;
          }
        case 65: break;
        case 30: 
          { return Parser.CLOSE_NOTE;
          }
        case 66: break;
        case 23: 
          { return Parser.CLOSE_TOC;
          }
        case 67: break;
        case 44: 
          { return Parser.CLOSE_PREFACE;
          }
        case 68: break;
        case 4: 
          { return Parser.CLOSE;
          }
        case 69: break;
        case 40: 
          { return Parser.CAPTION;
          }
        case 70: break;
        case 31: 
          { return Parser.CLOSE_PART;
          }
        case 71: break;
        case 12: 
          { return Parser.OPEN_TOC;
          }
        case 72: break;
        case 42: 
          { return Parser.OPEN_DOCTIPE;
          }
        case 73: break;
        case 36: 
          { return Parser.OPEN_PREFACE;
          }
        case 74: break;
        case 16: 
          { return Parser.OPEN_BOOK;
          }
        case 75: break;
        case 14: 
          { return Parser.BOOK;
          }
        case 76: break;
        case 34: 
          { return Parser.CLOSE_TABLE;
          }
        case 77: break;
        case 8: 
          { return Parser.CLOSE_HEADER;
          }
        case 78: break;
        case 21: 
          { return Parser.CLOSE_LOT;
          }
        case 79: break;
        case 19: 
          { return Parser.OPEN_NOTE;
          }
        case 80: break;
        case 10: 
          { return Parser.OPEN_LOT;
          }
        case 81: break;
        case 20: 
          { return Parser.OPEN_PART;
          }
        case 82: break;
        case 38: 
          { return Parser.CLOSE_FIGURE;
          }
        case 83: break;
        case 48: 
          { return Parser.OPEN_AUTHOR;
          }
        case 84: break;
        case 45: 
          { return Parser.CLOSE_SECTION;
          }
        case 85: break;
        case 3: 
          { yyparser.yylval = new ParserVal(yytext()); return Parser.VALUE;
          }
        case 86: break;
        case 37: 
          { return Parser.OPEN_SECTION;
          }
        case 87: break;
        case 47: 
          { return Parser.OPEN_DEDICATION;
          }
        case 88: break;
        case 32: 
          { return Parser.TITLE;
          }
        case 89: break;
        case 43: 
          { return Parser.CLOSE_CHAPTER;
          }
        case 90: break;
        case 1: 
          { System.err.println("Error: unexpected character '"+yytext()+"' @" + _line_cnt ); return -1;
          }
        case 91: break;
        case 29: 
          { return Parser.CLOSE_CELL;
          }
        case 92: break;
        case 5: 
          { return Parser.SLASH;
          }
        case 93: break;
        case 28: 
          { return Parser.CLOSE_ITEM;
          }
        case 94: break;
        case 39: 
          { return Parser.EDITION;
          }
        case 95: break;
        case 35: 
          { return Parser.OPEN_CHAPTER;
          }
        case 96: break;
        case 24: 
          { return Parser.CLOSE_ROW;
          }
        case 97: break;
        case 6: 
          { return Parser.QUOTE;
          }
        case 98: break;
        case 50: 
          { return Parser.CLOSE_AUTHOR;
          }
        case 99: break;
        case 7: 
          { 
          }
        case 100: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
