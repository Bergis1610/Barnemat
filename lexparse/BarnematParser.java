// Generated from Barnemat.g4 by ANTLR 4.9.2
package lexparse; 
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BarnematParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, ID=30, Bokstav=31, Setning=32, 
		Heltall=33, Desimaltall=34, Sannhet=35, Ganging=36, Deling=37, Pluss=38, 
		Minus=39, StørreEnn=40, MindreEnn=41, Lik=42, Ulik=43, ESC=44, Intet=45, 
		WS=46, SL_COMMENT=47, ML_COMMENT=48;
	public static final int
		RULE_fil = 0, RULE_hoveddel = 1, RULE_argument = 2, RULE_testMetode = 3, 
		RULE_variabelErklæring = 4, RULE_variabel = 5, RULE_variabeltype = 6, 
		RULE_verdiTildeling = 7, RULE_utskrift = 8, RULE_les = 9, RULE_uttrykk = 10, 
		RULE_entenEller = 11, RULE_sammenlikningEnten = 12, RULE_setningsSammenlikningEnten = 13, 
		RULE_løkke1 = 14, RULE_løkke2 = 15, RULE_sammenlikningLøkke1 = 16, RULE_sammenlikningLøkke2 = 17, 
		RULE_sammenliknbar = 18, RULE_sammenlikn = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"fil", "hoveddel", "argument", "testMetode", "variabelErklæring", "variabel", 
			"variabeltype", "verdiTildeling", "utskrift", "les", "uttrykk", "entenEller", 
			"sammenlikningEnten", "setningsSammenlikningEnten", "løkke1", "løkke2", 
			"sammenlikningLøkke1", "sammenlikningLøkke2", "sammenliknbar", "sammenlikn"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Program'", "'Start{'", "'}Slutt'", "'Test;'", "':='", "';'", 
			"'Heltall'", "'Desimaltall'", "'Setning'", "'Sannhet'", "'Angi'", "'Skriv'", 
			"'ut'", "':'", "'inn'", "'('", "')'", "'Dersom'", "'s\u00E5'", "'{'", 
			"'}'", "'ellers'", "'!'", "'Gjenta'", "'gjenta'", "'Medan'", "'Mens'", 
			"'medan'", "'mens'", null, null, null, null, null, null, "'*'", "'/'", 
			"'+'", "'-'", "'>'", "'<'", "'='", "'!='", null, "'Null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "ID", "Bokstav", "Setning", "Heltall", 
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

	@Override
	public String getGrammarFileName() { return "Barnemat.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BarnematParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FilContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BarnematParser.ID, 0); }
		public HoveddelContext hoveddel() {
			return getRuleContext(HoveddelContext.class,0);
		}
		public FilContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fil; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterFil(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitFil(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitFil(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilContext fil() throws RecognitionException {
		FilContext _localctx = new FilContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_fil);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(T__0);
			setState(41);
			match(ID);
			setState(42);
			hoveddel();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HoveddelContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public HoveddelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hoveddel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterHoveddel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitHoveddel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitHoveddel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HoveddelContext hoveddel() throws RecognitionException {
		HoveddelContext _localctx = new HoveddelContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_hoveddel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(T__1);
			setState(46); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(45);
				argument();
				}
				}
				setState(48); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__17) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28))) != 0) );
			setState(50);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public VariabelErklæringContext variabelErklæring() {
			return getRuleContext(VariabelErklæringContext.class,0);
		}
		public VerdiTildelingContext verdiTildeling() {
			return getRuleContext(VerdiTildelingContext.class,0);
		}
		public EntenEllerContext entenEller() {
			return getRuleContext(EntenEllerContext.class,0);
		}
		public Løkke1Context løkke1() {
			return getRuleContext(Løkke1Context.class,0);
		}
		public Løkke2Context løkke2() {
			return getRuleContext(Løkke2Context.class,0);
		}
		public UtskriftContext utskrift() {
			return getRuleContext(UtskriftContext.class,0);
		}
		public LesContext les() {
			return getRuleContext(LesContext.class,0);
		}
		public TestMetodeContext testMetode() {
			return getRuleContext(TestMetodeContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_argument);
		try {
			setState(60);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				variabelErklæring();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
				verdiTildeling();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(54);
				entenEller();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(55);
				løkke1();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(56);
				løkke2();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(57);
				utskrift();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(58);
				les();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(59);
				testMetode();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TestMetodeContext extends ParserRuleContext {
		public TestMetodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testMetode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterTestMetode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitTestMetode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitTestMetode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestMetodeContext testMetode() throws RecognitionException {
		TestMetodeContext _localctx = new TestMetodeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_testMetode);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariabelErklæringContext extends ParserRuleContext {
		public VariabelContext variabel() {
			return getRuleContext(VariabelContext.class,0);
		}
		public UttrykkContext uttrykk() {
			return getRuleContext(UttrykkContext.class,0);
		}
		public VariabelErklæringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variabelErklæring; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterVariabelErklæring(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitVariabelErklæring(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitVariabelErklæring(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariabelErklæringContext variabelErklæring() throws RecognitionException {
		VariabelErklæringContext _localctx = new VariabelErklæringContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variabelErklæring);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			variabel();
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(65);
				match(T__4);
				{
				setState(66);
				uttrykk(0);
				}
				}
			}

			setState(69);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariabelContext extends ParserRuleContext {
		public VariabeltypeContext variabeltype() {
			return getRuleContext(VariabeltypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(BarnematParser.ID, 0); }
		public VariabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterVariabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitVariabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitVariabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariabelContext variabel() throws RecognitionException {
		VariabelContext _localctx = new VariabelContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variabel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			variabeltype();
			setState(72);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariabeltypeContext extends ParserRuleContext {
		public VariabeltypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variabeltype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterVariabeltype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitVariabeltype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitVariabeltype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariabeltypeContext variabeltype() throws RecognitionException {
		VariabeltypeContext _localctx = new VariabeltypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variabeltype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VerdiTildelingContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BarnematParser.ID, 0); }
		public UttrykkContext uttrykk() {
			return getRuleContext(UttrykkContext.class,0);
		}
		public VerdiTildelingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_verdiTildeling; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterVerdiTildeling(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitVerdiTildeling(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitVerdiTildeling(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VerdiTildelingContext verdiTildeling() throws RecognitionException {
		VerdiTildelingContext _localctx = new VerdiTildelingContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_verdiTildeling);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(T__10);
			setState(77);
			match(ID);
			setState(78);
			match(T__4);
			{
			setState(79);
			uttrykk(0);
			}
			setState(80);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UtskriftContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BarnematParser.ID, 0); }
		public TerminalNode Setning() { return getToken(BarnematParser.Setning, 0); }
		public UtskriftContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_utskrift; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterUtskrift(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitUtskrift(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitUtskrift(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UtskriftContext utskrift() throws RecognitionException {
		UtskriftContext _localctx = new UtskriftContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_utskrift);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__11);
			setState(83);
			match(T__12);
			setState(84);
			match(T__13);
			setState(85);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==Setning) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(86);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LesContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BarnematParser.ID, 0); }
		public LesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_les; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterLes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitLes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitLes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LesContext les() throws RecognitionException {
		LesContext _localctx = new LesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_les);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__11);
			setState(89);
			match(T__14);
			setState(90);
			match(T__13);
			setState(91);
			match(ID);
			setState(92);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UttrykkContext extends ParserRuleContext {
		public UttrykkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uttrykk; }
	 
		public UttrykkContext() { }
		public void copyFrom(UttrykkContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class GangingContext extends UttrykkContext {
		public List<UttrykkContext> uttrykk() {
			return getRuleContexts(UttrykkContext.class);
		}
		public UttrykkContext uttrykk(int i) {
			return getRuleContext(UttrykkContext.class,i);
		}
		public TerminalNode Ganging() { return getToken(BarnematParser.Ganging, 0); }
		public GangingContext(UttrykkContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterGanging(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitGanging(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitGanging(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SetningContext extends UttrykkContext {
		public TerminalNode Setning() { return getToken(BarnematParser.Setning, 0); }
		public SetningContext(UttrykkContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterSetning(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitSetning(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitSetning(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PlussContext extends UttrykkContext {
		public List<UttrykkContext> uttrykk() {
			return getRuleContexts(UttrykkContext.class);
		}
		public UttrykkContext uttrykk(int i) {
			return getRuleContext(UttrykkContext.class,i);
		}
		public TerminalNode Pluss() { return getToken(BarnematParser.Pluss, 0); }
		public PlussContext(UttrykkContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterPluss(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitPluss(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitPluss(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DesimaltallContext extends UttrykkContext {
		public TerminalNode Desimaltall() { return getToken(BarnematParser.Desimaltall, 0); }
		public DesimaltallContext(UttrykkContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterDesimaltall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitDesimaltall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitDesimaltall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IDContext extends UttrykkContext {
		public TerminalNode ID() { return getToken(BarnematParser.ID, 0); }
		public IDContext(UttrykkContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitID(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DelingContext extends UttrykkContext {
		public List<UttrykkContext> uttrykk() {
			return getRuleContexts(UttrykkContext.class);
		}
		public UttrykkContext uttrykk(int i) {
			return getRuleContext(UttrykkContext.class,i);
		}
		public TerminalNode Deling() { return getToken(BarnematParser.Deling, 0); }
		public DelingContext(UttrykkContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterDeling(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitDeling(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitDeling(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParentesContext extends UttrykkContext {
		public UttrykkContext uttrykk() {
			return getRuleContext(UttrykkContext.class,0);
		}
		public ParentesContext(UttrykkContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterParentes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitParentes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitParentes(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class HeltallContext extends UttrykkContext {
		public TerminalNode Heltall() { return getToken(BarnematParser.Heltall, 0); }
		public HeltallContext(UttrykkContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterHeltall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitHeltall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitHeltall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MinusContext extends UttrykkContext {
		public List<UttrykkContext> uttrykk() {
			return getRuleContexts(UttrykkContext.class);
		}
		public UttrykkContext uttrykk(int i) {
			return getRuleContext(UttrykkContext.class,i);
		}
		public TerminalNode Minus() { return getToken(BarnematParser.Minus, 0); }
		public MinusContext(UttrykkContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitMinus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UttrykkContext uttrykk() throws RecognitionException {
		return uttrykk(0);
	}

	private UttrykkContext uttrykk(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		UttrykkContext _localctx = new UttrykkContext(_ctx, _parentState);
		UttrykkContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_uttrykk, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				_localctx = new IDContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(95);
				match(ID);
				}
				break;
			case Heltall:
				{
				_localctx = new HeltallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(96);
				match(Heltall);
				}
				break;
			case Desimaltall:
				{
				_localctx = new DesimaltallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(97);
				match(Desimaltall);
				}
				break;
			case Setning:
				{
				_localctx = new SetningContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(98);
				match(Setning);
				}
				break;
			case T__15:
				{
				_localctx = new ParentesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(99);
				match(T__15);
				setState(100);
				uttrykk(0);
				setState(101);
				match(T__16);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(119);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(117);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new GangingContext(new UttrykkContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_uttrykk);
						setState(105);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(106);
						match(Ganging);
						setState(107);
						uttrykk(10);
						}
						break;
					case 2:
						{
						_localctx = new DelingContext(new UttrykkContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_uttrykk);
						setState(108);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(109);
						match(Deling);
						setState(110);
						uttrykk(9);
						}
						break;
					case 3:
						{
						_localctx = new PlussContext(new UttrykkContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_uttrykk);
						setState(111);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(112);
						match(Pluss);
						setState(113);
						uttrykk(8);
						}
						break;
					case 4:
						{
						_localctx = new MinusContext(new UttrykkContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_uttrykk);
						setState(114);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(115);
						match(Minus);
						setState(116);
						uttrykk(7);
						}
						break;
					}
					} 
				}
				setState(121);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class EntenEllerContext extends ParserRuleContext {
		public SammenlikningEntenContext sammenlikningEnten() {
			return getRuleContext(SammenlikningEntenContext.class,0);
		}
		public SetningsSammenlikningEntenContext setningsSammenlikningEnten() {
			return getRuleContext(SetningsSammenlikningEntenContext.class,0);
		}
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public EntenEllerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entenEller; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterEntenEller(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitEntenEller(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitEntenEller(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntenEllerContext entenEller() throws RecognitionException {
		EntenEllerContext _localctx = new EntenEllerContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_entenEller);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(T__17);
			setState(123);
			match(T__15);
			setState(126);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(124);
				sammenlikningEnten();
				}
				break;
			case 2:
				{
				setState(125);
				setningsSammenlikningEnten();
				}
				break;
			}
			setState(128);
			match(T__16);
			setState(129);
			match(T__18);
			setState(130);
			match(T__19);
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__17) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) {
				{
				{
				setState(131);
				argument();
				}
				}
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(137);
			match(T__20);
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(138);
				match(T__21);
				setState(139);
				match(T__19);
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__17) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) {
					{
					{
					setState(140);
					argument();
					}
					}
					setState(145);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(146);
				match(T__20);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SammenlikningEntenContext extends ParserRuleContext {
		public List<SammenliknbarContext> sammenliknbar() {
			return getRuleContexts(SammenliknbarContext.class);
		}
		public SammenliknbarContext sammenliknbar(int i) {
			return getRuleContext(SammenliknbarContext.class,i);
		}
		public SammenliknContext sammenlikn() {
			return getRuleContext(SammenliknContext.class,0);
		}
		public TerminalNode ID() { return getToken(BarnematParser.ID, 0); }
		public TerminalNode Sannhet() { return getToken(BarnematParser.Sannhet, 0); }
		public TerminalNode Ulik() { return getToken(BarnematParser.Ulik, 0); }
		public TerminalNode Lik() { return getToken(BarnematParser.Lik, 0); }
		public SammenlikningEntenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sammenlikningEnten; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterSammenlikningEnten(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitSammenlikningEnten(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitSammenlikningEnten(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SammenlikningEntenContext sammenlikningEnten() throws RecognitionException {
		SammenlikningEntenContext _localctx = new SammenlikningEntenContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_sammenlikningEnten);
		int _la;
		try {
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(149);
				sammenliknbar();
				setState(150);
				sammenlikn();
				setState(151);
				sammenliknbar();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(153);
				match(ID);
				setState(154);
				_la = _input.LA(1);
				if ( !(_la==Lik || _la==Ulik) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(155);
				match(Sannhet);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__22) {
					{
					setState(156);
					match(T__22);
					}
				}

				setState(159);
				match(ID);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetningsSammenlikningEntenContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(BarnematParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(BarnematParser.ID, i);
		}
		public List<TerminalNode> Setning() { return getTokens(BarnematParser.Setning); }
		public TerminalNode Setning(int i) {
			return getToken(BarnematParser.Setning, i);
		}
		public TerminalNode Ulik() { return getToken(BarnematParser.Ulik, 0); }
		public TerminalNode Lik() { return getToken(BarnematParser.Lik, 0); }
		public SetningsSammenlikningEntenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setningsSammenlikningEnten; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterSetningsSammenlikningEnten(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitSetningsSammenlikningEnten(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitSetningsSammenlikningEnten(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetningsSammenlikningEntenContext setningsSammenlikningEnten() throws RecognitionException {
		SetningsSammenlikningEntenContext _localctx = new SetningsSammenlikningEntenContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_setningsSammenlikningEnten);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(162);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==Setning) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(163);
			_la = _input.LA(1);
			if ( !(_la==Lik || _la==Ulik) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(164);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==Setning) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Løkke1Context extends ParserRuleContext {
		public SammenlikningLøkke1Context sammenlikningLøkke1() {
			return getRuleContext(SammenlikningLøkke1Context.class,0);
		}
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public Løkke1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_løkke1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterLøkke1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitLøkke1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitLøkke1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Løkke1Context løkke1() throws RecognitionException {
		Løkke1Context _localctx = new Løkke1Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_løkke1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			_la = _input.LA(1);
			if ( !(_la==T__23 || _la==T__24) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(167);
			match(T__19);
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__17) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) {
				{
				{
				setState(168);
				argument();
				}
				}
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(174);
			match(T__20);
			setState(175);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(176);
			match(T__15);
			setState(177);
			sammenlikningLøkke1();
			setState(178);
			match(T__16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Løkke2Context extends ParserRuleContext {
		public SammenlikningLøkke2Context sammenlikningLøkke2() {
			return getRuleContext(SammenlikningLøkke2Context.class,0);
		}
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public Løkke2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_løkke2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterLøkke2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitLøkke2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitLøkke2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Løkke2Context løkke2() throws RecognitionException {
		Løkke2Context _localctx = new Løkke2Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_løkke2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(181);
			match(T__15);
			setState(182);
			sammenlikningLøkke2();
			setState(183);
			match(T__16);
			setState(184);
			_la = _input.LA(1);
			if ( !(_la==T__23 || _la==T__24) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(185);
			match(T__19);
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__17) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) {
				{
				{
				setState(186);
				argument();
				}
				}
				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(192);
			match(T__20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SammenlikningLøkke1Context extends ParserRuleContext {
		public List<SammenliknbarContext> sammenliknbar() {
			return getRuleContexts(SammenliknbarContext.class);
		}
		public SammenliknbarContext sammenliknbar(int i) {
			return getRuleContext(SammenliknbarContext.class,i);
		}
		public SammenliknContext sammenlikn() {
			return getRuleContext(SammenliknContext.class,0);
		}
		public TerminalNode ID() { return getToken(BarnematParser.ID, 0); }
		public TerminalNode Sannhet() { return getToken(BarnematParser.Sannhet, 0); }
		public TerminalNode Ulik() { return getToken(BarnematParser.Ulik, 0); }
		public TerminalNode Lik() { return getToken(BarnematParser.Lik, 0); }
		public SammenlikningLøkke1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sammenlikningLøkke1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterSammenlikningLøkke1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitSammenlikningLøkke1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitSammenlikningLøkke1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SammenlikningLøkke1Context sammenlikningLøkke1() throws RecognitionException {
		SammenlikningLøkke1Context _localctx = new SammenlikningLøkke1Context(_ctx, getState());
		enterRule(_localctx, 32, RULE_sammenlikningLøkke1);
		int _la;
		try {
			setState(205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(194);
				sammenliknbar();
				setState(195);
				sammenlikn();
				setState(196);
				sammenliknbar();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(198);
				match(ID);
				setState(199);
				_la = _input.LA(1);
				if ( !(_la==Lik || _la==Ulik) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(200);
				match(Sannhet);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__22) {
					{
					setState(201);
					match(T__22);
					}
				}

				setState(204);
				match(ID);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SammenlikningLøkke2Context extends ParserRuleContext {
		public List<SammenliknbarContext> sammenliknbar() {
			return getRuleContexts(SammenliknbarContext.class);
		}
		public SammenliknbarContext sammenliknbar(int i) {
			return getRuleContext(SammenliknbarContext.class,i);
		}
		public SammenliknContext sammenlikn() {
			return getRuleContext(SammenliknContext.class,0);
		}
		public TerminalNode ID() { return getToken(BarnematParser.ID, 0); }
		public TerminalNode Sannhet() { return getToken(BarnematParser.Sannhet, 0); }
		public TerminalNode Ulik() { return getToken(BarnematParser.Ulik, 0); }
		public TerminalNode Lik() { return getToken(BarnematParser.Lik, 0); }
		public SammenlikningLøkke2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sammenlikningLøkke2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterSammenlikningLøkke2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitSammenlikningLøkke2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitSammenlikningLøkke2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SammenlikningLøkke2Context sammenlikningLøkke2() throws RecognitionException {
		SammenlikningLøkke2Context _localctx = new SammenlikningLøkke2Context(_ctx, getState());
		enterRule(_localctx, 34, RULE_sammenlikningLøkke2);
		int _la;
		try {
			setState(218);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(207);
				sammenliknbar();
				setState(208);
				sammenlikn();
				setState(209);
				sammenliknbar();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(211);
				match(ID);
				setState(212);
				_la = _input.LA(1);
				if ( !(_la==Lik || _la==Ulik) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(213);
				match(Sannhet);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__22) {
					{
					setState(214);
					match(T__22);
					}
				}

				setState(217);
				match(ID);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SammenliknbarContext extends ParserRuleContext {
		public SammenliknbarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sammenliknbar; }
	 
		public SammenliknbarContext() { }
		public void copyFrom(SammenliknbarContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SammenliknbarHelContext extends SammenliknbarContext {
		public TerminalNode Heltall() { return getToken(BarnematParser.Heltall, 0); }
		public SammenliknbarHelContext(SammenliknbarContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterSammenliknbarHel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitSammenliknbarHel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitSammenliknbarHel(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SammenliknbarIDContext extends SammenliknbarContext {
		public TerminalNode ID() { return getToken(BarnematParser.ID, 0); }
		public SammenliknbarIDContext(SammenliknbarContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterSammenliknbarID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitSammenliknbarID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitSammenliknbarID(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SammenliknbarDesiContext extends SammenliknbarContext {
		public TerminalNode Desimaltall() { return getToken(BarnematParser.Desimaltall, 0); }
		public SammenliknbarDesiContext(SammenliknbarContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterSammenliknbarDesi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitSammenliknbarDesi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitSammenliknbarDesi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SammenliknbarContext sammenliknbar() throws RecognitionException {
		SammenliknbarContext _localctx = new SammenliknbarContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_sammenliknbar);
		try {
			setState(223);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new SammenliknbarIDContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(220);
				match(ID);
				}
				break;
			case Desimaltall:
				_localctx = new SammenliknbarDesiContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(221);
				match(Desimaltall);
				}
				break;
			case Heltall:
				_localctx = new SammenliknbarHelContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(222);
				match(Heltall);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SammenliknContext extends ParserRuleContext {
		public TerminalNode StørreEnn() { return getToken(BarnematParser.StørreEnn, 0); }
		public TerminalNode MindreEnn() { return getToken(BarnematParser.MindreEnn, 0); }
		public TerminalNode Lik() { return getToken(BarnematParser.Lik, 0); }
		public TerminalNode Ulik() { return getToken(BarnematParser.Ulik, 0); }
		public SammenliknContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sammenlikn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).enterSammenlikn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BarnematListener ) ((BarnematListener)listener).exitSammenlikn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BarnematVisitor ) return ((BarnematVisitor<? extends T>)visitor).visitSammenlikn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SammenliknContext sammenlikn() throws RecognitionException {
		SammenliknContext _localctx = new SammenliknContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_sammenlikn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << StørreEnn) | (1L << MindreEnn) | (1L << Lik) | (1L << Ulik))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return uttrykk_sempred((UttrykkContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean uttrykk_sempred(UttrykkContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\62\u00e6\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\3\3\3\6\3\61\n\3\r\3"+
		"\16\3\62\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4?\n\4\3\5\3\5\3\6"+
		"\3\6\3\6\5\6F\n\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\5\fj\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\7\fx\n\f\f\f\16\f{\13\f\3\r\3\r\3\r\3\r\5\r\u0081\n\r\3\r\3\r"+
		"\3\r\3\r\7\r\u0087\n\r\f\r\16\r\u008a\13\r\3\r\3\r\3\r\3\r\7\r\u0090\n"+
		"\r\f\r\16\r\u0093\13\r\3\r\5\r\u0096\n\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\5\16\u00a0\n\16\3\16\5\16\u00a3\n\16\3\17\3\17\3\17\3\17\3"+
		"\20\3\20\3\20\7\20\u00ac\n\20\f\20\16\20\u00af\13\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u00be\n\21\f\21"+
		"\16\21\u00c1\13\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5"+
		"\22\u00cd\n\22\3\22\5\22\u00d0\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\5\23\u00da\n\23\3\23\5\23\u00dd\n\23\3\24\3\24\3\24\5\24\u00e2\n"+
		"\24\3\25\3\25\3\25\2\3\26\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(\2\b\3\2\t\f\4\2  \"\"\3\2,-\3\2\32\33\3\2\34\37\3\2*-\2\u00f3\2*\3"+
		"\2\2\2\4.\3\2\2\2\6>\3\2\2\2\b@\3\2\2\2\nB\3\2\2\2\fI\3\2\2\2\16L\3\2"+
		"\2\2\20N\3\2\2\2\22T\3\2\2\2\24Z\3\2\2\2\26i\3\2\2\2\30|\3\2\2\2\32\u00a2"+
		"\3\2\2\2\34\u00a4\3\2\2\2\36\u00a8\3\2\2\2 \u00b6\3\2\2\2\"\u00cf\3\2"+
		"\2\2$\u00dc\3\2\2\2&\u00e1\3\2\2\2(\u00e3\3\2\2\2*+\7\3\2\2+,\7 \2\2,"+
		"-\5\4\3\2-\3\3\2\2\2.\60\7\4\2\2/\61\5\6\4\2\60/\3\2\2\2\61\62\3\2\2\2"+
		"\62\60\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65\7\5\2\2\65\5\3\2\2\2"+
		"\66?\5\n\6\2\67?\5\20\t\28?\5\30\r\29?\5\36\20\2:?\5 \21\2;?\5\22\n\2"+
		"<?\5\24\13\2=?\5\b\5\2>\66\3\2\2\2>\67\3\2\2\2>8\3\2\2\2>9\3\2\2\2>:\3"+
		"\2\2\2>;\3\2\2\2><\3\2\2\2>=\3\2\2\2?\7\3\2\2\2@A\7\6\2\2A\t\3\2\2\2B"+
		"E\5\f\7\2CD\7\7\2\2DF\5\26\f\2EC\3\2\2\2EF\3\2\2\2FG\3\2\2\2GH\7\b\2\2"+
		"H\13\3\2\2\2IJ\5\16\b\2JK\7 \2\2K\r\3\2\2\2LM\t\2\2\2M\17\3\2\2\2NO\7"+
		"\r\2\2OP\7 \2\2PQ\7\7\2\2QR\5\26\f\2RS\7\b\2\2S\21\3\2\2\2TU\7\16\2\2"+
		"UV\7\17\2\2VW\7\20\2\2WX\t\3\2\2XY\7\b\2\2Y\23\3\2\2\2Z[\7\16\2\2[\\\7"+
		"\21\2\2\\]\7\20\2\2]^\7 \2\2^_\7\b\2\2_\25\3\2\2\2`a\b\f\1\2aj\7 \2\2"+
		"bj\7#\2\2cj\7$\2\2dj\7\"\2\2ef\7\22\2\2fg\5\26\f\2gh\7\23\2\2hj\3\2\2"+
		"\2i`\3\2\2\2ib\3\2\2\2ic\3\2\2\2id\3\2\2\2ie\3\2\2\2jy\3\2\2\2kl\f\13"+
		"\2\2lm\7&\2\2mx\5\26\f\fno\f\n\2\2op\7\'\2\2px\5\26\f\13qr\f\t\2\2rs\7"+
		"(\2\2sx\5\26\f\ntu\f\b\2\2uv\7)\2\2vx\5\26\f\twk\3\2\2\2wn\3\2\2\2wq\3"+
		"\2\2\2wt\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z\27\3\2\2\2{y\3\2\2\2|"+
		"}\7\24\2\2}\u0080\7\22\2\2~\u0081\5\32\16\2\177\u0081\5\34\17\2\u0080"+
		"~\3\2\2\2\u0080\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\7\23\2\2\u0083"+
		"\u0084\7\25\2\2\u0084\u0088\7\26\2\2\u0085\u0087\5\6\4\2\u0086\u0085\3"+
		"\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089"+
		"\u008b\3\2\2\2\u008a\u0088\3\2\2\2\u008b\u0095\7\27\2\2\u008c\u008d\7"+
		"\30\2\2\u008d\u0091\7\26\2\2\u008e\u0090\5\6\4\2\u008f\u008e\3\2\2\2\u0090"+
		"\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2"+
		"\2\2\u0093\u0091\3\2\2\2\u0094\u0096\7\27\2\2\u0095\u008c\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\31\3\2\2\2\u0097\u0098\5&\24\2\u0098\u0099\5(\25"+
		"\2\u0099\u009a\5&\24\2\u009a\u00a3\3\2\2\2\u009b\u009c\7 \2\2\u009c\u009d"+
		"\t\4\2\2\u009d\u00a3\7%\2\2\u009e\u00a0\7\31\2\2\u009f\u009e\3\2\2\2\u009f"+
		"\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\7 \2\2\u00a2\u0097\3\2"+
		"\2\2\u00a2\u009b\3\2\2\2\u00a2\u009f\3\2\2\2\u00a3\33\3\2\2\2\u00a4\u00a5"+
		"\t\3\2\2\u00a5\u00a6\t\4\2\2\u00a6\u00a7\t\3\2\2\u00a7\35\3\2\2\2\u00a8"+
		"\u00a9\t\5\2\2\u00a9\u00ad\7\26\2\2\u00aa\u00ac\5\6\4\2\u00ab\u00aa\3"+
		"\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00b0\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b1\7\27\2\2\u00b1\u00b2\t"+
		"\6\2\2\u00b2\u00b3\7\22\2\2\u00b3\u00b4\5\"\22\2\u00b4\u00b5\7\23\2\2"+
		"\u00b5\37\3\2\2\2\u00b6\u00b7\t\6\2\2\u00b7\u00b8\7\22\2\2\u00b8\u00b9"+
		"\5$\23\2\u00b9\u00ba\7\23\2\2\u00ba\u00bb\t\5\2\2\u00bb\u00bf\7\26\2\2"+
		"\u00bc\u00be\5\6\4\2\u00bd\u00bc\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf\u00bd"+
		"\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2"+
		"\u00c3\7\27\2\2\u00c3!\3\2\2\2\u00c4\u00c5\5&\24\2\u00c5\u00c6\5(\25\2"+
		"\u00c6\u00c7\5&\24\2\u00c7\u00d0\3\2\2\2\u00c8\u00c9\7 \2\2\u00c9\u00ca"+
		"\t\4\2\2\u00ca\u00d0\7%\2\2\u00cb\u00cd\7\31\2\2\u00cc\u00cb\3\2\2\2\u00cc"+
		"\u00cd\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d0\7 \2\2\u00cf\u00c4\3\2"+
		"\2\2\u00cf\u00c8\3\2\2\2\u00cf\u00cc\3\2\2\2\u00d0#\3\2\2\2\u00d1\u00d2"+
		"\5&\24\2\u00d2\u00d3\5(\25\2\u00d3\u00d4\5&\24\2\u00d4\u00dd\3\2\2\2\u00d5"+
		"\u00d6\7 \2\2\u00d6\u00d7\t\4\2\2\u00d7\u00dd\7%\2\2\u00d8\u00da\7\31"+
		"\2\2\u00d9\u00d8\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\3\2\2\2\u00db"+
		"\u00dd\7 \2\2\u00dc\u00d1\3\2\2\2\u00dc\u00d5\3\2\2\2\u00dc\u00d9\3\2"+
		"\2\2\u00dd%\3\2\2\2\u00de\u00e2\7 \2\2\u00df\u00e2\7$\2\2\u00e0\u00e2"+
		"\7#\2\2\u00e1\u00de\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e0\3\2\2\2\u00e2"+
		"\'\3\2\2\2\u00e3\u00e4\t\7\2\2\u00e4)\3\2\2\2\25\62>Eiwy\u0080\u0088\u0091"+
		"\u0095\u009f\u00a2\u00ad\u00bf\u00cc\u00cf\u00d9\u00dc\u00e1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}