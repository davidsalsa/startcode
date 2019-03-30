// Generated from C:/Users/David/Documents/startcode/src/main/antlr4/nl/han/ica/icss/parser\ICSS.g4 by ANTLR 4.7.2
package nl.han.ica.icss.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ICSSLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, OPEN_BRACE=4, CLOSE_BRACE=5, SEMICOLON=6, COLON=7, 
		PLUS=8, MIN=9, MUL=10, ASSIGNMENT_OPERATOR=11, PIXELSIZE=12, PERCENTAGE=13, 
		SCALAR=14, COLOR=15, ID_IDENT=16, CLASS_IDENT=17, LOWER_IDENT=18, CAPITAL_IDENT=19, 
		WS=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", "COLON", 
			"PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR", "PIXELSIZE", "PERCENTAGE", 
			"SCALAR", "COLOR", "ID_IDENT", "CLASS_IDENT", "LOWER_IDENT", "CAPITAL_IDENT", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'background-color'", "'width'", "'color'", "'{'", "'}'", "';'", 
			"':'", "'+'", "'-'", "'*'", "':='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", "COLON", 
			"PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR", "PIXELSIZE", "PERCENTAGE", 
			"SCALAR", "COLOR", "ID_IDENT", "CLASS_IDENT", "LOWER_IDENT", "CAPITAL_IDENT", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ICSSLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ICSS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u0096\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\f\3\r\6\r[\n\r\r\r\16\r\\\3\r\3\r\3\r\3\16\6\16c\n\16\r\16"+
		"\16\16d\3\16\3\16\3\17\6\17j\n\17\r\17\16\17k\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\6\21x\n\21\r\21\16\21y\3\22\3\22\6\22~\n\22"+
		"\r\22\16\22\177\3\23\3\23\7\23\u0084\n\23\f\23\16\23\u0087\13\23\3\24"+
		"\3\24\7\24\u008b\n\24\f\24\16\24\u008e\13\24\3\25\6\25\u0091\n\25\r\25"+
		"\16\25\u0092\3\25\3\25\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26\3\2\t\3\2\62;"+
		"\4\2\62;ch\5\2//\62;c|\3\2c|\3\2C\\\6\2\62;C\\aac|\5\2\13\f\17\17\"\""+
		"\2\u009d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5<\3\2\2\2"+
		"\7B\3\2\2\2\tH\3\2\2\2\13J\3\2\2\2\rL\3\2\2\2\17N\3\2\2\2\21P\3\2\2\2"+
		"\23R\3\2\2\2\25T\3\2\2\2\27V\3\2\2\2\31Z\3\2\2\2\33b\3\2\2\2\35i\3\2\2"+
		"\2\37m\3\2\2\2!u\3\2\2\2#{\3\2\2\2%\u0081\3\2\2\2\'\u0088\3\2\2\2)\u0090"+
		"\3\2\2\2+,\7d\2\2,-\7c\2\2-.\7e\2\2./\7m\2\2/\60\7i\2\2\60\61\7t\2\2\61"+
		"\62\7q\2\2\62\63\7w\2\2\63\64\7p\2\2\64\65\7f\2\2\65\66\7/\2\2\66\67\7"+
		"e\2\2\678\7q\2\289\7n\2\29:\7q\2\2:;\7t\2\2;\4\3\2\2\2<=\7y\2\2=>\7k\2"+
		"\2>?\7f\2\2?@\7v\2\2@A\7j\2\2A\6\3\2\2\2BC\7e\2\2CD\7q\2\2DE\7n\2\2EF"+
		"\7q\2\2FG\7t\2\2G\b\3\2\2\2HI\7}\2\2I\n\3\2\2\2JK\7\177\2\2K\f\3\2\2\2"+
		"LM\7=\2\2M\16\3\2\2\2NO\7<\2\2O\20\3\2\2\2PQ\7-\2\2Q\22\3\2\2\2RS\7/\2"+
		"\2S\24\3\2\2\2TU\7,\2\2U\26\3\2\2\2VW\7<\2\2WX\7?\2\2X\30\3\2\2\2Y[\t"+
		"\2\2\2ZY\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^_\7r\2\2_"+
		"`\7z\2\2`\32\3\2\2\2ac\t\2\2\2ba\3\2\2\2cd\3\2\2\2db\3\2\2\2de\3\2\2\2"+
		"ef\3\2\2\2fg\7\'\2\2g\34\3\2\2\2hj\t\2\2\2ih\3\2\2\2jk\3\2\2\2ki\3\2\2"+
		"\2kl\3\2\2\2l\36\3\2\2\2mn\7%\2\2no\t\3\2\2op\t\3\2\2pq\t\3\2\2qr\t\3"+
		"\2\2rs\t\3\2\2st\t\3\2\2t \3\2\2\2uw\7%\2\2vx\t\4\2\2wv\3\2\2\2xy\3\2"+
		"\2\2yw\3\2\2\2yz\3\2\2\2z\"\3\2\2\2{}\7\60\2\2|~\t\4\2\2}|\3\2\2\2~\177"+
		"\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080$\3\2\2\2\u0081\u0085\t\5"+
		"\2\2\u0082\u0084\t\4\2\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086&\3\2\2\2\u0087\u0085\3\2\2\2"+
		"\u0088\u008c\t\6\2\2\u0089\u008b\t\7\2\2\u008a\u0089\3\2\2\2\u008b\u008e"+
		"\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d(\3\2\2\2\u008e"+
		"\u008c\3\2\2\2\u008f\u0091\t\b\2\2\u0090\u008f\3\2\2\2\u0091\u0092\3\2"+
		"\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0095\b\25\2\2\u0095*\3\2\2\2\13\2\\dky\177\u0085\u008c\u0092\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}