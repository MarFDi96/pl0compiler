// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: C:/Users/Skynet/Documents/NetbeansProjects/Compilador/PL0.flex

package compilador;

enum Terminal {

    IF, CALL, CADENA_LITERAL, NUMERO, ASIGNACION, NULO, IDENTIFICADOR, EOF, MAS,
    CONST, VAR, PROCEDURE, BEGIN, END, THEN, WHILE, DO, ODD, MENOS, POR,
    DIVIDIDO, IGUAL, COMA, PUNTO_Y_COMA, MAYOR, MENOR, MENOR_IGUAL, MAYOR_IGUAL,
    DISTINTO, PUNTO, READLN, WRITELN, WRITE, ABRE_PARENTESIS, CIERRA_PARENTESIS,
    HALT, ELSE
}


// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
public class AnalizadorLexico {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
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
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\1\u0100\u10fe\u0200";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
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
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\1\0\1\1\1\3\22\0\1\1"+
    "\6\0\1\4\1\5\1\6\1\7\1\10\1\11\1\12"+
    "\1\13\1\14\1\15\11\16\1\17\1\20\1\21\1\22"+
    "\1\23\2\0\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\1\33\1\34\2\35\1\36\1\35\1\37\1\40"+
    "\1\41\1\35\1\42\1\43\1\44\1\45\1\46\1\47"+
    "\3\35\6\0\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\1\33\1\34\2\35\1\36\1\35\1\37\1\40"+
    "\1\41\1\35\1\42\1\43\1\44\1\45\1\46\1\47"+
    "\3\35\265\0\2\50\115\0\1\51\u0180\0";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[768];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
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
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\2\2\1\1\1\3\1\4\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\2\13\1\1\1\14\1\15"+
    "\1\16\1\17\15\20\1\1\1\0\1\21\1\22\1\23"+
    "\1\24\1\25\3\20\1\26\3\20\1\27\7\20\1\27"+
    "\4\20\1\0\1\30\1\20\1\31\3\20\1\32\1\20"+
    "\1\0\1\20\1\0\1\20\1\0\1\33\1\20\1\0"+
    "\2\34\1\35\2\20\1\36\1\20\1\0\1\20\1\0"+
    "\2\37\2\40\2\20\2\41\2\42\1\20\1\43\1\20"+
    "\1\0\1\20\2\44\1\20\1\45";

  private static int [] zzUnpackAction() {
    int [] result = new int[106];
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
    "\0\0\0\52\0\52\0\124\0\176\0\52\0\52\0\52"+
    "\0\52\0\52\0\52\0\52\0\52\0\52\0\250\0\322"+
    "\0\52\0\374\0\52\0\u0126\0\u0150\0\u017a\0\u01a4\0\u01ce"+
    "\0\u01f8\0\u0222\0\u024c\0\u0276\0\u02a0\0\u02ca\0\u02f4\0\u031e"+
    "\0\u0348\0\u0372\0\176\0\52\0\52\0\52\0\52\0\52"+
    "\0\u039c\0\u03c6\0\u03f0\0\u0150\0\u041a\0\u0444\0\u046e\0\u0150"+
    "\0\u0498\0\u04c2\0\u04ec\0\u0516\0\u0540\0\u056a\0\u0594\0\52"+
    "\0\u05be\0\u05e8\0\u0612\0\u063c\0\u0666\0\u0150\0\u0690\0\u0150"+
    "\0\u06ba\0\u06e4\0\u070e\0\u0150\0\u0738\0\u0762\0\u078c\0\u07b6"+
    "\0\u07e0\0\u080a\0\u0150\0\u0834\0\u085e\0\u0150\0\52\0\u0150"+
    "\0\u0888\0\u08b2\0\u0150\0\u08dc\0\u0906\0\u0930\0\u095a\0\u0150"+
    "\0\52\0\u0150\0\52\0\u0984\0\u09ae\0\u0150\0\52\0\u09d8"+
    "\0\u0a02\0\u0a2c\0\u0150\0\u0a56\0\u0a80\0\u0aaa\0\u0150\0\52"+
    "\0\u0ad4\0\u0150";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[106];
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
    "\1\2\2\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\2\25\1\32\1\33\3\25\1\34\1\35\1\36\1\25"+
    "\1\37\1\25\1\40\1\41\1\42\1\2\54\0\1\3"+
    "\47\0\4\43\1\44\45\43\15\0\2\17\55\0\1\45"+
    "\51\0\1\46\1\47\50\0\1\50\44\0\2\25\5\0"+
    "\24\25\17\0\2\25\5\0\4\25\1\51\17\25\17\0"+
    "\2\25\5\0\1\52\13\25\1\53\7\25\17\0\2\25"+
    "\5\0\14\25\1\54\7\25\17\0\2\25\5\0\12\25"+
    "\1\55\1\56\10\25\17\0\2\25\5\0\1\57\23\25"+
    "\17\0\2\25\5\0\5\25\1\60\16\25\17\0\2\25"+
    "\5\0\3\25\1\61\20\25\17\0\2\25\5\0\16\25"+
    "\1\62\5\25\17\0\2\25\5\0\4\25\1\63\17\25"+
    "\17\0\2\25\5\0\7\25\1\64\14\25\17\0\2\25"+
    "\5\0\1\65\23\25\17\0\2\25\5\0\7\25\1\66"+
    "\6\25\1\67\5\25\33\0\1\70\35\0\2\25\5\0"+
    "\6\25\1\71\15\25\17\0\2\25\5\0\12\25\1\72"+
    "\11\25\17\0\2\25\5\0\13\25\1\73\10\25\17\0"+
    "\2\25\5\0\17\25\1\74\4\25\1\0\1\75\15\0"+
    "\2\25\5\0\3\25\1\76\20\25\17\0\2\25\5\0"+
    "\12\25\1\77\11\25\17\0\2\25\5\0\3\25\1\100"+
    "\20\25\17\0\2\25\5\0\14\25\1\101\7\25\17\0"+
    "\2\25\5\0\1\102\23\25\17\0\2\25\5\0\4\25"+
    "\1\103\17\25\17\0\2\25\5\0\16\25\1\104\5\25"+
    "\17\0\2\25\5\0\10\25\1\105\13\25\1\106\16\0"+
    "\2\25\5\0\10\25\1\107\13\25\1\110\16\0\2\25"+
    "\5\0\10\25\1\111\13\25\1\112\16\0\2\25\5\0"+
    "\12\25\1\113\11\25\17\0\2\25\5\0\17\25\1\114"+
    "\4\25\1\0\1\115\15\0\2\25\5\0\4\25\1\116"+
    "\17\25\32\0\1\117\36\0\2\25\5\0\20\25\1\120"+
    "\3\25\17\0\2\25\5\0\2\25\1\121\21\25\17\0"+
    "\2\25\5\0\3\25\1\122\20\25\17\0\2\25\5\0"+
    "\13\25\1\123\10\25\17\0\2\25\5\0\12\25\1\124"+
    "\11\25\40\0\1\125\30\0\2\25\5\0\20\25\1\126"+
    "\3\25\46\0\1\127\22\0\2\25\5\0\13\25\1\130"+
    "\10\25\41\0\1\131\27\0\2\25\5\0\20\25\1\132"+
    "\3\25\46\0\1\133\22\0\2\25\5\0\4\25\1\134"+
    "\17\25\17\0\2\25\5\0\12\25\1\135\11\25\17\0"+
    "\2\25\5\0\4\25\1\136\17\25\32\0\1\137\36\0"+
    "\2\25\5\0\4\25\1\140\17\25\32\0\1\141\36\0"+
    "\2\25\5\0\3\25\1\142\20\25\17\0\2\25\5\0"+
    "\13\25\1\143\10\25\17\0\2\25\5\0\12\25\1\144"+
    "\11\25\40\0\1\145\30\0\2\25\5\0\21\25\1\146"+
    "\2\25\17\0\2\25\5\0\13\25\1\147\10\25\41\0"+
    "\1\150\27\0\2\25\5\0\16\25\1\151\5\25\17\0"+
    "\2\25\5\0\4\25\1\152\17\25\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2814];
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


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\2\11\2\1\11\11\2\1\1\11\1\1\1\11"+
    "\17\1\1\0\5\11\17\1\1\11\4\1\1\0\10\1"+
    "\1\0\1\1\1\0\1\1\1\0\2\1\1\0\1\1"+
    "\1\11\5\1\1\0\1\1\1\0\1\1\1\11\1\1"+
    "\1\11\3\1\1\11\5\1\1\0\2\1\1\11\2\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[106];
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

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  @SuppressWarnings("unused")
  private boolean zzEOFDone;

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
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
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
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  public Terminal escanear() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

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
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
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
        // peek one character ahead if it is
        // (if we have counted one line too much)
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
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
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
            // fall through
          case 38: break;
          case 2:
            { 
            }
            // fall through
          case 39: break;
          case 3:
            { s = Terminal.ABRE_PARENTESIS; return s;
            }
            // fall through
          case 40: break;
          case 4:
            { s = Terminal.CIERRA_PARENTESIS; return s;
            }
            // fall through
          case 41: break;
          case 5:
            { s = Terminal.POR; return s;
            }
            // fall through
          case 42: break;
          case 6:
            { s = Terminal.MAS; return s;
            }
            // fall through
          case 43: break;
          case 7:
            { s = Terminal.COMA; return s;
            }
            // fall through
          case 44: break;
          case 8:
            { s = Terminal.MENOS; return s;
            }
            // fall through
          case 45: break;
          case 9:
            { s = Terminal.PUNTO; return s;
            }
            // fall through
          case 46: break;
          case 10:
            { s = Terminal.DIVIDIDO; return s;
            }
            // fall through
          case 47: break;
          case 11:
            { s = Terminal.NUMERO; return s;
            }
            // fall through
          case 48: break;
          case 12:
            { s = Terminal.PUNTO_Y_COMA; return s;
            }
            // fall through
          case 49: break;
          case 13:
            { s = Terminal.MENOR; return s;
            }
            // fall through
          case 50: break;
          case 14:
            { s = Terminal.IGUAL; return s;
            }
            // fall through
          case 51: break;
          case 15:
            { s = Terminal.MAYOR; return s;
            }
            // fall through
          case 52: break;
          case 16:
            { s = Terminal.IDENTIFICADOR; return s;
            }
            // fall through
          case 53: break;
          case 17:
            { s = Terminal.CADENA_LITERAL; return s;
            }
            // fall through
          case 54: break;
          case 18:
            { s = Terminal.ASIGNACION; return s;
            }
            // fall through
          case 55: break;
          case 19:
            { s = Terminal.MENOR_IGUAL; return s;
            }
            // fall through
          case 56: break;
          case 20:
            { s = Terminal.DISTINTO; return s;
            }
            // fall through
          case 57: break;
          case 21:
            { s = Terminal.MAYOR_IGUAL; return s;
            }
            // fall through
          case 58: break;
          case 22:
            { s = Terminal.DO; return s;
            }
            // fall through
          case 59: break;
          case 23:
            { s = Terminal.IF; return s;
            }
            // fall through
          case 60: break;
          case 24:
            { s = Terminal.END; return s;
            }
            // fall through
          case 61: break;
          case 25:
            { s = Terminal.ODD; return s;
            }
            // fall through
          case 62: break;
          case 26:
            { s = Terminal.VAR; return s;
            }
            // fall through
          case 63: break;
          case 27:
            { s = Terminal.CALL; return s;
            }
            // fall through
          case 64: break;
          case 28:
            { s = Terminal.ELSE; return s;
            }
            // fall through
          case 65: break;
          case 29:
            { s = Terminal.HALT; return s;
            }
            // fall through
          case 66: break;
          case 30:
            { s = Terminal.THEN; return s;
            }
            // fall through
          case 67: break;
          case 31:
            { s = Terminal.BEGIN; return s;
            }
            // fall through
          case 68: break;
          case 32:
            { s = Terminal.CONST; return s;
            }
            // fall through
          case 69: break;
          case 33:
            { s = Terminal.WHILE; return s;
            }
            // fall through
          case 70: break;
          case 34:
            { s = Terminal.WRITE; return s;
            }
            // fall through
          case 71: break;
          case 35:
            { s = Terminal.READLN; return s;
            }
            // fall through
          case 72: break;
          case 36:
            { s = Terminal.WRITELN; return s;
            }
            // fall through
          case 73: break;
          case 37:
            { s = Terminal.PROCEDURE; return s;
            }
            // fall through
          case 74: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
