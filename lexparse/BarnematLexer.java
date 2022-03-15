// Generated from Barnemat.g4 by ANTLR 4.9.2
package lexparse; 
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BarnematLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, ID=29, Bokstav=30, Setning=31, 
		Heltall=32, Desimaltall=33, Sannhet=34, Ganging=35, Deling=36, Pluss=37, 
		Minus=38, StørreEnn=39, MindreEnn=40, Lik=41, Ulik=42, ESC=43, Intet=44, 
		WS=45, SL_COMMENT=46, ML_COMMENT=47;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "ID", "Bokstav", "Siffer", "Ikke_Null_Siffer", 
			"Setning", "Heltall", "Desimaltall", "Sannhet", "Ganging", "Deling", 
			"Pluss", "Minus", "StørreEnn", "MindreEnn", "Lik", "Ulik", "ESC", "Intet", 
			"WS", "SL_COMMENT", "ML_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Program'", "'Start{'", "'}Slutt'", "'Test;'", "':='", "';'", 
			"'Heltall'", "'Desimaltall'", "'Setning'", "'Sannhet'", "'Angi'", "'Skriv ut'", 
			"':'", "'Skriv inn'", "'('", "')'", "'Dersom'", "'s\u00E5'", "'{'", "'}'", 
			"'ellers'", "'!'", "'Gjenta'", "'Medan'", "'Mens'", "'medan'", "'mens'", 
			"'gjenta'", null, null, null, null, null, null, "'*'", "'/'", "'+'", 
			"'-'", "'>'", "'<'", "'='", "'!='", null, "'Null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "ID", "Bokstav", "Setning", "Heltall", 
			"Desimaltall", "Sannhet", "Ganging", "Deling", "Pluss", "Minus", "St\u0002rreEnn", 
			"MindreEnn", "Lik", "Ulik", "ESC", "Intet", "WS", "SL_COMMENT", "ML_COMMENT"
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


	public BarnematLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Barnemat.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\61\u0176\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\36\3\36\3\36\7\36\u0107\n\36\f\36\16\36\u010a\13\36\3\37\3\37\3 \3"+
		" \3!\3!\3\"\3\"\3\"\7\"\u0115\n\"\f\"\16\"\u0118\13\"\3\"\3\"\3#\3#\3"+
		"#\6#\u011f\n#\r#\16#\u0120\5#\u0123\n#\3$\3$\3$\6$\u0128\n$\r$\16$\u0129"+
		"\3%\3%\3%\3%\3%\3%\3%\3%\3%\5%\u0135\n%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3"+
		"*\3+\3+\3,\3,\3-\3-\3-\3.\3.\3.\3.\5.\u014c\n.\3/\3/\3/\3/\3/\3\60\6\60"+
		"\u0154\n\60\r\60\16\60\u0155\3\60\3\60\3\61\3\61\3\61\3\61\7\61\u015e"+
		"\n\61\f\61\16\61\u0161\13\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\7"+
		"\62\u016b\n\62\f\62\16\62\u016e\13\62\3\62\3\62\3\62\3\62\3\62\3\62\3"+
		"\62\5\u0116\u015f\u016c\2\63\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61"+
		"\32\63\33\65\34\67\359\36;\37= ?\2A\2C!E\"G#I$K%M&O\'Q(S)U*W+Y,[-]._/"+
		"a\60c\61\3\2\5\3\2\62;\b\2C\\c|\u00c7\u00c8\u00da\u00da\u00e7\u00e8\u00fa"+
		"\u00fa\5\2\13\f\17\17\"\"\2\u017f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3"+
		"\2\2\2\2a\3\2\2\2\2c\3\2\2\2\3e\3\2\2\2\5m\3\2\2\2\7t\3\2\2\2\t{\3\2\2"+
		"\2\13\u0081\3\2\2\2\r\u0084\3\2\2\2\17\u0086\3\2\2\2\21\u008e\3\2\2\2"+
		"\23\u009a\3\2\2\2\25\u00a2\3\2\2\2\27\u00aa\3\2\2\2\31\u00af\3\2\2\2\33"+
		"\u00b8\3\2\2\2\35\u00ba\3\2\2\2\37\u00c4\3\2\2\2!\u00c6\3\2\2\2#\u00c8"+
		"\3\2\2\2%\u00cf\3\2\2\2\'\u00d2\3\2\2\2)\u00d4\3\2\2\2+\u00d6\3\2\2\2"+
		"-\u00dd\3\2\2\2/\u00df\3\2\2\2\61\u00e6\3\2\2\2\63\u00ec\3\2\2\2\65\u00f1"+
		"\3\2\2\2\67\u00f7\3\2\2\29\u00fc\3\2\2\2;\u0103\3\2\2\2=\u010b\3\2\2\2"+
		"?\u010d\3\2\2\2A\u010f\3\2\2\2C\u0111\3\2\2\2E\u0122\3\2\2\2G\u0124\3"+
		"\2\2\2I\u0134\3\2\2\2K\u0136\3\2\2\2M\u0138\3\2\2\2O\u013a\3\2\2\2Q\u013c"+
		"\3\2\2\2S\u013e\3\2\2\2U\u0140\3\2\2\2W\u0142\3\2\2\2Y\u0144\3\2\2\2["+
		"\u014b\3\2\2\2]\u014d\3\2\2\2_\u0153\3\2\2\2a\u0159\3\2\2\2c\u0166\3\2"+
		"\2\2ef\7R\2\2fg\7t\2\2gh\7q\2\2hi\7i\2\2ij\7t\2\2jk\7c\2\2kl\7o\2\2l\4"+
		"\3\2\2\2mn\7U\2\2no\7v\2\2op\7c\2\2pq\7t\2\2qr\7v\2\2rs\7}\2\2s\6\3\2"+
		"\2\2tu\7\177\2\2uv\7U\2\2vw\7n\2\2wx\7w\2\2xy\7v\2\2yz\7v\2\2z\b\3\2\2"+
		"\2{|\7V\2\2|}\7g\2\2}~\7u\2\2~\177\7v\2\2\177\u0080\7=\2\2\u0080\n\3\2"+
		"\2\2\u0081\u0082\7<\2\2\u0082\u0083\7?\2\2\u0083\f\3\2\2\2\u0084\u0085"+
		"\7=\2\2\u0085\16\3\2\2\2\u0086\u0087\7J\2\2\u0087\u0088\7g\2\2\u0088\u0089"+
		"\7n\2\2\u0089\u008a\7v\2\2\u008a\u008b\7c\2\2\u008b\u008c\7n\2\2\u008c"+
		"\u008d\7n\2\2\u008d\20\3\2\2\2\u008e\u008f\7F\2\2\u008f\u0090\7g\2\2\u0090"+
		"\u0091\7u\2\2\u0091\u0092\7k\2\2\u0092\u0093\7o\2\2\u0093\u0094\7c\2\2"+
		"\u0094\u0095\7n\2\2\u0095\u0096\7v\2\2\u0096\u0097\7c\2\2\u0097\u0098"+
		"\7n\2\2\u0098\u0099\7n\2\2\u0099\22\3\2\2\2\u009a\u009b\7U\2\2\u009b\u009c"+
		"\7g\2\2\u009c\u009d\7v\2\2\u009d\u009e\7p\2\2\u009e\u009f\7k\2\2\u009f"+
		"\u00a0\7p\2\2\u00a0\u00a1\7i\2\2\u00a1\24\3\2\2\2\u00a2\u00a3\7U\2\2\u00a3"+
		"\u00a4\7c\2\2\u00a4\u00a5\7p\2\2\u00a5\u00a6\7p\2\2\u00a6\u00a7\7j\2\2"+
		"\u00a7\u00a8\7g\2\2\u00a8\u00a9\7v\2\2\u00a9\26\3\2\2\2\u00aa\u00ab\7"+
		"C\2\2\u00ab\u00ac\7p\2\2\u00ac\u00ad\7i\2\2\u00ad\u00ae\7k\2\2\u00ae\30"+
		"\3\2\2\2\u00af\u00b0\7U\2\2\u00b0\u00b1\7m\2\2\u00b1\u00b2\7t\2\2\u00b2"+
		"\u00b3\7k\2\2\u00b3\u00b4\7x\2\2\u00b4\u00b5\7\"\2\2\u00b5\u00b6\7w\2"+
		"\2\u00b6\u00b7\7v\2\2\u00b7\32\3\2\2\2\u00b8\u00b9\7<\2\2\u00b9\34\3\2"+
		"\2\2\u00ba\u00bb\7U\2\2\u00bb\u00bc\7m\2\2\u00bc\u00bd\7t\2\2\u00bd\u00be"+
		"\7k\2\2\u00be\u00bf\7x\2\2\u00bf\u00c0\7\"\2\2\u00c0\u00c1\7k\2\2\u00c1"+
		"\u00c2\7p\2\2\u00c2\u00c3\7p\2\2\u00c3\36\3\2\2\2\u00c4\u00c5\7*\2\2\u00c5"+
		" \3\2\2\2\u00c6\u00c7\7+\2\2\u00c7\"\3\2\2\2\u00c8\u00c9\7F\2\2\u00c9"+
		"\u00ca\7g\2\2\u00ca\u00cb\7t\2\2\u00cb\u00cc\7u\2\2\u00cc\u00cd\7q\2\2"+
		"\u00cd\u00ce\7o\2\2\u00ce$\3\2\2\2\u00cf\u00d0\7u\2\2\u00d0\u00d1\7\u00e7"+
		"\2\2\u00d1&\3\2\2\2\u00d2\u00d3\7}\2\2\u00d3(\3\2\2\2\u00d4\u00d5\7\177"+
		"\2\2\u00d5*\3\2\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8\7n\2\2\u00d8\u00d9"+
		"\7n\2\2\u00d9\u00da\7g\2\2\u00da\u00db\7t\2\2\u00db\u00dc\7u\2\2\u00dc"+
		",\3\2\2\2\u00dd\u00de\7#\2\2\u00de.\3\2\2\2\u00df\u00e0\7I\2\2\u00e0\u00e1"+
		"\7l\2\2\u00e1\u00e2\7g\2\2\u00e2\u00e3\7p\2\2\u00e3\u00e4\7v\2\2\u00e4"+
		"\u00e5\7c\2\2\u00e5\60\3\2\2\2\u00e6\u00e7\7O\2\2\u00e7\u00e8\7g\2\2\u00e8"+
		"\u00e9\7f\2\2\u00e9\u00ea\7c\2\2\u00ea\u00eb\7p\2\2\u00eb\62\3\2\2\2\u00ec"+
		"\u00ed\7O\2\2\u00ed\u00ee\7g\2\2\u00ee\u00ef\7p\2\2\u00ef\u00f0\7u\2\2"+
		"\u00f0\64\3\2\2\2\u00f1\u00f2\7o\2\2\u00f2\u00f3\7g\2\2\u00f3\u00f4\7"+
		"f\2\2\u00f4\u00f5\7c\2\2\u00f5\u00f6\7p\2\2\u00f6\66\3\2\2\2\u00f7\u00f8"+
		"\7o\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb\7u\2\2\u00fb"+
		"8\3\2\2\2\u00fc\u00fd\7i\2\2\u00fd\u00fe\7l\2\2\u00fe\u00ff\7g\2\2\u00ff"+
		"\u0100\7p\2\2\u0100\u0101\7v\2\2\u0101\u0102\7c\2\2\u0102:\3\2\2\2\u0103"+
		"\u0108\5=\37\2\u0104\u0107\5=\37\2\u0105\u0107\t\2\2\2\u0106\u0104\3\2"+
		"\2\2\u0106\u0105\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0108"+
		"\u0109\3\2\2\2\u0109<\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u010c\t\3\2\2"+
		"\u010c>\3\2\2\2\u010d\u010e\4\62;\2\u010e@\3\2\2\2\u010f\u0110\4\63;\2"+
		"\u0110B\3\2\2\2\u0111\u0116\7$\2\2\u0112\u0115\5[.\2\u0113\u0115\13\2"+
		"\2\2\u0114\u0112\3\2\2\2\u0114\u0113\3\2\2\2\u0115\u0118\3\2\2\2\u0116"+
		"\u0117\3\2\2\2\u0116\u0114\3\2\2\2\u0117\u0119\3\2\2\2\u0118\u0116\3\2"+
		"\2\2\u0119\u011a\7$\2\2\u011aD\3\2\2\2\u011b\u0123\5? \2\u011c\u011e\5"+
		"A!\2\u011d\u011f\5? \2\u011e\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u011e"+
		"\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0123\3\2\2\2\u0122\u011b\3\2\2\2\u0122"+
		"\u011c\3\2\2\2\u0123F\3\2\2\2\u0124\u0125\5E#\2\u0125\u0127\7.\2\2\u0126"+
		"\u0128\5? \2\u0127\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u0127\3\2\2"+
		"\2\u0129\u012a\3\2\2\2\u012aH\3\2\2\2\u012b\u012c\7u\2\2\u012c\u012d\7"+
		"c\2\2\u012d\u012e\7p\2\2\u012e\u0135\7v\2\2\u012f\u0130\7w\2\2\u0130\u0131"+
		"\7u\2\2\u0131\u0132\7c\2\2\u0132\u0133\7p\2\2\u0133\u0135\7v\2\2\u0134"+
		"\u012b\3\2\2\2\u0134\u012f\3\2\2\2\u0135J\3\2\2\2\u0136\u0137\7,\2\2\u0137"+
		"L\3\2\2\2\u0138\u0139\7\61\2\2\u0139N\3\2\2\2\u013a\u013b\7-\2\2\u013b"+
		"P\3\2\2\2\u013c\u013d\7/\2\2\u013dR\3\2\2\2\u013e\u013f\7@\2\2\u013fT"+
		"\3\2\2\2\u0140\u0141\7>\2\2\u0141V\3\2\2\2\u0142\u0143\7?\2\2\u0143X\3"+
		"\2\2\2\u0144\u0145\7#\2\2\u0145\u0146\7?\2\2\u0146Z\3\2\2\2\u0147\u0148"+
		"\7^\2\2\u0148\u014c\7$\2\2\u0149\u014a\7^\2\2\u014a\u014c\7^\2\2\u014b"+
		"\u0147\3\2\2\2\u014b\u0149\3\2\2\2\u014c\\\3\2\2\2\u014d\u014e\7P\2\2"+
		"\u014e\u014f\7w\2\2\u014f\u0150\7n\2\2\u0150\u0151\7n\2\2\u0151^\3\2\2"+
		"\2\u0152\u0154\t\4\2\2\u0153\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0153"+
		"\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u0158\b\60\2\2"+
		"\u0158`\3\2\2\2\u0159\u015a\7\61\2\2\u015a\u015b\7\61\2\2\u015b\u015f"+
		"\3\2\2\2\u015c\u015e\13\2\2\2\u015d\u015c\3\2\2\2\u015e\u0161\3\2\2\2"+
		"\u015f\u0160\3\2\2\2\u015f\u015d\3\2\2\2\u0160\u0162\3\2\2\2\u0161\u015f"+
		"\3\2\2\2\u0162\u0163\7\f\2\2\u0163\u0164\3\2\2\2\u0164\u0165\b\61\2\2"+
		"\u0165b\3\2\2\2\u0166\u0167\7\61\2\2\u0167\u0168\7,\2\2\u0168\u016c\3"+
		"\2\2\2\u0169\u016b\13\2\2\2\u016a\u0169\3\2\2\2\u016b\u016e\3\2\2\2\u016c"+
		"\u016d\3\2\2\2\u016c\u016a\3\2\2\2\u016d\u016f\3\2\2\2\u016e\u016c\3\2"+
		"\2\2\u016f\u0170\7,\2\2\u0170\u0171\7\61\2\2\u0171\u0172\3\2\2\2\u0172"+
		"\u0173\7\f\2\2\u0173\u0174\3\2\2\2\u0174\u0175\b\62\2\2\u0175d\3\2\2\2"+
		"\17\2\u0106\u0108\u0114\u0116\u0120\u0122\u0129\u0134\u014b\u0155\u015f"+
		"\u016c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}