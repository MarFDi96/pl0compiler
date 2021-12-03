// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/

package compilador;

enum Terminal {

    IF, CALL, CADENA_LITERAL, NUMERO, ASIGNACION, NULO, IDENTIFICADOR, EOF, MAS,
    CONST, VAR, PROCEDURE, BEGIN, END, THEN, WHILE, DO, ODD, MENOS, POR,
    DIVIDIDO, IGUAL, COMA, PUNTO_Y_COMA, MAYOR, MENOR, MENOR_IGUAL, MAYOR_IGUAL,
    DISTINTO, PUNTO, READLN, WRITELN, WRITE, ABRE_PARENTESIS, CIERRA_PARENTESIS,
    COMENTARIO
}


public class AnalizadorLexico {

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
    "\11\0\1\7\1\6\1\0\1\7\1\5\22\0\1\7\6\0\1\4"+
    "\1\10\1\11\1\21\1\17\1\12\1\20\1\14\1\22\1\3\11\2"+
    "\1\15\1\13\1\23\1\16\1\24\2\0\1\34\1\25\1\33\1\42"+
    "\1\26\1\43\1\27\1\47\1\31\2\1\1\35\1\52\1\32\1\36"+
    "\1\44\1\1\1\45\1\40\1\41\1\46\1\50\1\51\3\1\6\0"+
    "\1\34\1\25\1\33\1\42\1\26\1\43\1\27\1\47\1\31\2\1"+
    "\1\35\1\52\1\32\1\36\1\44\1\1\1\45\1\40\1\41\1\46"+
    "\1\50\1\51\3\1\265\0\2\30\115\0\1\37\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\ufe90\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\2\3\1\1\2\4\1\5\1\6"+
    "\1\7\1\10\1\11\1\1\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\2\2\1\1\11\2\1\0\1\21"+
    "\1\22\1\23\1\24\1\25\2\2\2\26\4\2\1\27"+
    "\6\2\1\30\3\2\1\31\3\2\1\32\1\0\1\2"+
    "\1\0\1\2\1\0\1\2\1\33\1\0\2\2\1\34"+
    "\2\2\1\0\1\2\1\0\1\2\2\35\2\36\3\2"+
    "\2\37\2\40\2\2\1\41\1\0\3\2\2\42\2\2"+
    "\1\0\1\2\1\43\2\44";

  private static int [] zzUnpackAction() {
    int [] result = new int[107];
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
    "\0\0\0\53\0\126\0\201\0\53\0\254\0\327\0\53"+
    "\0\53\0\53\0\53\0\53\0\53\0\u0102\0\53\0\53"+
    "\0\53\0\53\0\53\0\u012d\0\u0158\0\u0183\0\u01ae\0\u01d9"+
    "\0\u0204\0\u022f\0\u025a\0\u0285\0\u02b0\0\u02db\0\u0306\0\u0331"+
    "\0\u035c\0\254\0\53\0\53\0\53\0\53\0\53\0\u0387"+
    "\0\u03b2\0\53\0\126\0\u03dd\0\u0408\0\u0433\0\u045e\0\126"+
    "\0\u0489\0\u04b4\0\u04df\0\u050a\0\u0535\0\u0560\0\126\0\u058b"+
    "\0\u05b6\0\u05e1\0\126\0\u060c\0\u0637\0\u0662\0\126\0\u068d"+
    "\0\u06b8\0\u06e3\0\u070e\0\u0739\0\u0764\0\126\0\u078f\0\u07ba"+
    "\0\u07e5\0\126\0\u0810\0\u083b\0\u0866\0\u0891\0\u08bc\0\u08e7"+
    "\0\53\0\126\0\53\0\126\0\u0912\0\u093d\0\u0968\0\u0993"+
    "\0\u09be\0\53\0\126\0\u09e9\0\u0a14\0\126\0\u0a3f\0\u0a6a"+
    "\0\u0a95\0\u0ac0\0\53\0\126\0\u0aeb\0\u0b16\0\u0b41\0\u0b6c"+
    "\0\126\0\53\0\126";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[107];
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
    "\1\2\1\3\1\4\1\5\1\6\1\7\2\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\3\1\30"+
    "\1\31\1\3\1\32\2\3\1\33\1\2\1\3\1\34"+
    "\1\35\1\3\1\36\1\37\2\3\1\40\1\41\1\3"+
    "\54\0\3\3\21\0\3\3\1\0\6\3\1\0\13\3"+
    "\2\0\2\4\47\0\4\42\1\43\46\42\6\0\1\10"+
    "\62\0\1\44\52\0\1\45\5\0\1\46\44\0\1\47"+
    "\35\0\3\3\21\0\1\3\1\50\1\3\1\0\6\3"+
    "\1\0\13\3\1\0\3\3\21\0\3\3\1\0\1\3"+
    "\1\51\4\3\1\0\13\3\43\0\1\52\10\0\3\3"+
    "\21\0\3\3\1\0\6\3\1\0\3\3\1\53\7\3"+
    "\1\0\3\3\21\0\3\3\1\0\3\3\1\54\1\3"+
    "\1\55\1\0\13\3\1\0\3\3\21\0\3\3\1\0"+
    "\6\3\1\0\2\3\1\56\10\3\1\0\3\3\21\0"+
    "\3\3\1\0\6\3\1\0\7\3\1\57\3\3\1\0"+
    "\3\3\21\0\3\3\1\0\5\3\1\60\1\0\13\3"+
    "\1\0\3\3\21\0\3\3\1\0\6\3\1\0\5\3"+
    "\1\61\5\3\1\0\3\3\21\0\1\3\1\62\1\3"+
    "\1\0\6\3\1\0\13\3\1\0\3\3\21\0\3\3"+
    "\1\0\3\3\1\63\2\3\1\0\13\3\1\0\3\3"+
    "\21\0\3\3\1\0\6\3\1\0\5\3\1\64\1\3"+
    "\1\65\3\3\1\0\3\3\21\0\2\3\1\66\1\0"+
    "\6\3\1\0\13\3\1\0\3\3\21\0\3\3\1\0"+
    "\6\3\1\0\2\3\1\67\10\3\1\0\3\3\21\0"+
    "\3\3\1\0\4\3\1\70\1\3\1\0\13\3\1\0"+
    "\3\3\21\0\3\3\1\0\1\3\1\71\4\3\1\0"+
    "\12\3\1\72\1\0\3\3\21\0\3\3\1\0\6\3"+
    "\1\0\2\3\1\73\10\3\1\0\3\3\21\0\1\3"+
    "\1\74\1\3\1\0\6\3\1\0\13\3\1\0\3\3"+
    "\21\0\3\3\1\0\5\3\1\75\1\0\13\3\1\0"+
    "\3\3\21\0\3\3\1\0\3\3\1\76\2\3\1\0"+
    "\13\3\1\0\3\3\21\0\3\3\1\0\6\3\1\0"+
    "\5\3\1\77\5\3\1\0\3\3\21\0\3\3\1\100"+
    "\1\101\5\3\1\0\13\3\1\0\3\3\21\0\3\3"+
    "\1\102\1\103\5\3\1\0\13\3\1\0\3\3\21\0"+
    "\3\3\1\104\1\105\5\3\1\0\13\3\1\0\3\3"+
    "\21\0\3\3\1\0\4\3\1\106\1\3\1\0\13\3"+
    "\1\0\3\3\21\0\3\3\1\0\6\3\1\107\1\110"+
    "\12\3\1\0\3\3\21\0\1\3\1\111\1\3\1\0"+
    "\6\3\1\0\13\3\1\0\3\3\21\0\3\3\1\0"+
    "\1\3\1\112\4\3\1\0\13\3\1\0\3\3\21\0"+
    "\3\3\1\0\2\3\1\113\3\3\1\0\13\3\1\0"+
    "\3\3\21\0\3\3\1\0\6\3\1\0\2\3\1\114"+
    "\10\3\41\0\1\115\12\0\3\3\21\0\3\3\1\0"+
    "\6\3\1\0\1\3\1\116\11\3\35\0\1\117\16\0"+
    "\3\3\21\0\3\3\1\0\4\3\1\120\1\3\1\0"+
    "\13\3\32\0\1\121\21\0\3\3\21\0\3\3\1\0"+
    "\1\3\1\122\4\3\1\0\13\3\41\0\1\123\12\0"+
    "\3\3\21\0\3\3\1\0\6\3\1\0\1\3\1\124"+
    "\11\3\1\0\3\3\21\0\3\3\1\0\1\3\1\125"+
    "\4\3\1\0\13\3\1\0\3\3\21\0\1\3\1\126"+
    "\1\3\1\0\6\3\1\0\13\3\1\0\3\3\21\0"+
    "\3\3\1\0\4\3\1\127\1\3\1\0\13\3\26\0"+
    "\1\130\25\0\3\3\21\0\1\3\1\131\1\3\1\0"+
    "\6\3\1\0\13\3\26\0\1\132\25\0\3\3\21\0"+
    "\1\3\1\133\1\3\1\0\6\3\1\0\13\3\1\0"+
    "\3\3\21\0\3\3\1\0\6\3\1\0\1\3\1\134"+
    "\11\3\1\0\3\3\21\0\3\3\1\0\6\3\1\0"+
    "\2\3\1\135\10\3\1\0\3\3\21\0\3\3\1\0"+
    "\1\3\1\136\4\3\1\0\13\3\35\0\1\137\16\0"+
    "\3\3\21\0\3\3\1\0\4\3\1\140\1\3\1\0"+
    "\13\3\1\0\3\3\21\0\3\3\1\0\3\3\1\141"+
    "\2\3\1\0\13\3\1\0\3\3\21\0\3\3\1\0"+
    "\6\3\1\0\6\3\1\142\4\3\32\0\1\143\21\0"+
    "\3\3\21\0\3\3\1\0\1\3\1\144\4\3\1\0"+
    "\13\3\1\0\3\3\21\0\3\3\1\0\6\3\1\0"+
    "\5\3\1\145\5\3\1\0\3\3\21\0\3\3\1\0"+
    "\6\3\1\0\5\3\1\146\5\3\1\0\3\3\21\0"+
    "\3\3\1\147\1\150\5\3\1\0\13\3\1\0\3\3"+
    "\21\0\1\3\1\151\1\3\1\0\6\3\1\0\13\3"+
    "\36\0\1\152\15\0\3\3\21\0\3\3\1\0\5\3"+
    "\1\153\1\0\13\3";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2967];
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
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\2\1\1\11\2\1\6\11\1\1\5\11"+
    "\16\1\1\0\5\11\2\1\1\11\25\1\1\0\1\1"+
    "\1\0\1\1\1\0\2\1\1\0\5\1\1\0\1\1"+
    "\1\0\1\1\1\11\1\1\1\11\6\1\1\11\4\1"+
    "\1\0\3\1\1\11\3\1\1\0\2\1\1\11\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[107];
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
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    private Terminal s;

    public String getCad() {
        return yytext();
    }

    public Terminal getS() {
        return s;
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public AnalizadorLexico(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 188) {
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
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
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
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
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
   * Internal scan buffer is resized down to its initial length, if it has grown.
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
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
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
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Terminal escanear() throws java.io.IOException {
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

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
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
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
              {
                s = Terminal.EOF; return s;
              }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { s = Terminal.NULO; return s;
            }
          case 37: break;
          case 2: 
            { s = Terminal.IDENTIFICADOR; return s;
            }
          case 38: break;
          case 3: 
            { s = Terminal.NUMERO; return s;
            }
          case 39: break;
          case 4: 
            { 
            }
          case 40: break;
          case 5: 
            { s = Terminal.ABRE_PARENTESIS; return s;
            }
          case 41: break;
          case 6: 
            { s = Terminal.CIERRA_PARENTESIS; return s;
            }
          case 42: break;
          case 7: 
            { s = Terminal.COMA; return s;
            }
          case 43: break;
          case 8: 
            { s = Terminal.PUNTO_Y_COMA; return s;
            }
          case 44: break;
          case 9: 
            { s = Terminal.PUNTO; return s;
            }
          case 45: break;
          case 10: 
            { s = Terminal.IGUAL; return s;
            }
          case 46: break;
          case 11: 
            { s = Terminal.MAS; return s;
            }
          case 47: break;
          case 12: 
            { s = Terminal.MENOS; return s;
            }
          case 48: break;
          case 13: 
            { s = Terminal.POR; return s;
            }
          case 49: break;
          case 14: 
            { s = Terminal.DIVIDIDO; return s;
            }
          case 50: break;
          case 15: 
            { s = Terminal.MENOR; return s;
            }
          case 51: break;
          case 16: 
            { s = Terminal.MAYOR; return s;
            }
          case 52: break;
          case 17: 
            { s = Terminal.CADENA_LITERAL; return s;
            }
          case 53: break;
          case 18: 
            { s = Terminal.ASIGNACION; return s;
            }
          case 54: break;
          case 19: 
            { s = Terminal.MENOR_IGUAL; return s;
            }
          case 55: break;
          case 20: 
            { s = Terminal.DISTINTO; return s;
            }
          case 56: break;
          case 21: 
            { s = Terminal.MAYOR_IGUAL; return s;
            }
          case 57: break;
          case 22: 
            { s = Terminal.IF; return s;
            }
          case 58: break;
          case 23: 
            { s = Terminal.DO; return s;
            }
          case 59: break;
          case 24: 
            { s = Terminal.END; return s;
            }
          case 60: break;
          case 25: 
            { s = Terminal.ODD; return s;
            }
          case 61: break;
          case 26: 
            { s = Terminal.VAR; return s;
            }
          case 62: break;
          case 27: 
            { s = Terminal.CALL; return s;
            }
          case 63: break;
          case 28: 
            { s = Terminal.THEN; return s;
            }
          case 64: break;
          case 29: 
            { s = Terminal.BEGIN; return s;
            }
          case 65: break;
          case 30: 
            { s = Terminal.CONST; return s;
            }
          case 66: break;
          case 31: 
            { s = Terminal.WRITE; return s;
            }
          case 67: break;
          case 32: 
            { s = Terminal.WHILE; return s;
            }
          case 68: break;
          case 33: 
            { s = Terminal.READLN; return s;
            }
          case 69: break;
          case 34: 
            { s = Terminal.WRITELN; return s;
            }
          case 70: break;
          case 35: 
            { s = Terminal.PROCEDURE; return s;
            }
          case 71: break;
          case 36: 
            { s = Terminal.COMENTARIO; return s;
            }
          case 72: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}