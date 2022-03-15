// Generated from Barnemat.g4 by ANTLR 4.9.2
package lexparse; 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BarnematParser}.
 */
public interface BarnematListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BarnematParser#fil}.
	 * @param ctx the parse tree
	 */
	void enterFil(BarnematParser.FilContext ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#fil}.
	 * @param ctx the parse tree
	 */
	void exitFil(BarnematParser.FilContext ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#hoveddel}.
	 * @param ctx the parse tree
	 */
	void enterHoveddel(BarnematParser.HoveddelContext ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#hoveddel}.
	 * @param ctx the parse tree
	 */
	void exitHoveddel(BarnematParser.HoveddelContext ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(BarnematParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(BarnematParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#testMetode}.
	 * @param ctx the parse tree
	 */
	void enterTestMetode(BarnematParser.TestMetodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#testMetode}.
	 * @param ctx the parse tree
	 */
	void exitTestMetode(BarnematParser.TestMetodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#variabelErklæring}.
	 * @param ctx the parse tree
	 */
	void enterVariabelErklæring(BarnematParser.VariabelErklæringContext ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#variabelErklæring}.
	 * @param ctx the parse tree
	 */
	void exitVariabelErklæring(BarnematParser.VariabelErklæringContext ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#variabel}.
	 * @param ctx the parse tree
	 */
	void enterVariabel(BarnematParser.VariabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#variabel}.
	 * @param ctx the parse tree
	 */
	void exitVariabel(BarnematParser.VariabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#variabeltype}.
	 * @param ctx the parse tree
	 */
	void enterVariabeltype(BarnematParser.VariabeltypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#variabeltype}.
	 * @param ctx the parse tree
	 */
	void exitVariabeltype(BarnematParser.VariabeltypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#verdiTildeling}.
	 * @param ctx the parse tree
	 */
	void enterVerdiTildeling(BarnematParser.VerdiTildelingContext ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#verdiTildeling}.
	 * @param ctx the parse tree
	 */
	void exitVerdiTildeling(BarnematParser.VerdiTildelingContext ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#utskrift}.
	 * @param ctx the parse tree
	 */
	void enterUtskrift(BarnematParser.UtskriftContext ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#utskrift}.
	 * @param ctx the parse tree
	 */
	void exitUtskrift(BarnematParser.UtskriftContext ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#les}.
	 * @param ctx the parse tree
	 */
	void enterLes(BarnematParser.LesContext ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#les}.
	 * @param ctx the parse tree
	 */
	void exitLes(BarnematParser.LesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Ganging}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void enterGanging(BarnematParser.GangingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Ganging}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void exitGanging(BarnematParser.GangingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Setning}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void enterSetning(BarnematParser.SetningContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Setning}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void exitSetning(BarnematParser.SetningContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Pluss}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void enterPluss(BarnematParser.PlussContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Pluss}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void exitPluss(BarnematParser.PlussContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Desimaltall}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void enterDesimaltall(BarnematParser.DesimaltallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Desimaltall}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void exitDesimaltall(BarnematParser.DesimaltallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ID}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void enterID(BarnematParser.IDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ID}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void exitID(BarnematParser.IDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Deling}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void enterDeling(BarnematParser.DelingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Deling}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void exitDeling(BarnematParser.DelingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parentes}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void enterParentes(BarnematParser.ParentesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parentes}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void exitParentes(BarnematParser.ParentesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Heltall}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void enterHeltall(BarnematParser.HeltallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Heltall}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void exitHeltall(BarnematParser.HeltallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void enterMinus(BarnematParser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 */
	void exitMinus(BarnematParser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#entenEller}.
	 * @param ctx the parse tree
	 */
	void enterEntenEller(BarnematParser.EntenEllerContext ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#entenEller}.
	 * @param ctx the parse tree
	 */
	void exitEntenEller(BarnematParser.EntenEllerContext ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#sammenlikningEnten}.
	 * @param ctx the parse tree
	 */
	void enterSammenlikningEnten(BarnematParser.SammenlikningEntenContext ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#sammenlikningEnten}.
	 * @param ctx the parse tree
	 */
	void exitSammenlikningEnten(BarnematParser.SammenlikningEntenContext ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#løkke1}.
	 * @param ctx the parse tree
	 */
	void enterLøkke1(BarnematParser.Løkke1Context ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#løkke1}.
	 * @param ctx the parse tree
	 */
	void exitLøkke1(BarnematParser.Løkke1Context ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#løkke2}.
	 * @param ctx the parse tree
	 */
	void enterLøkke2(BarnematParser.Løkke2Context ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#løkke2}.
	 * @param ctx the parse tree
	 */
	void exitLøkke2(BarnematParser.Løkke2Context ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#sammenlikningLøkke1}.
	 * @param ctx the parse tree
	 */
	void enterSammenlikningLøkke1(BarnematParser.SammenlikningLøkke1Context ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#sammenlikningLøkke1}.
	 * @param ctx the parse tree
	 */
	void exitSammenlikningLøkke1(BarnematParser.SammenlikningLøkke1Context ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#sammenlikningLøkke2}.
	 * @param ctx the parse tree
	 */
	void enterSammenlikningLøkke2(BarnematParser.SammenlikningLøkke2Context ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#sammenlikningLøkke2}.
	 * @param ctx the parse tree
	 */
	void exitSammenlikningLøkke2(BarnematParser.SammenlikningLøkke2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code SammenliknbarID}
	 * labeled alternative in {@link BarnematParser#sammenliknbar}.
	 * @param ctx the parse tree
	 */
	void enterSammenliknbarID(BarnematParser.SammenliknbarIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SammenliknbarID}
	 * labeled alternative in {@link BarnematParser#sammenliknbar}.
	 * @param ctx the parse tree
	 */
	void exitSammenliknbarID(BarnematParser.SammenliknbarIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SammenliknbarDesi}
	 * labeled alternative in {@link BarnematParser#sammenliknbar}.
	 * @param ctx the parse tree
	 */
	void enterSammenliknbarDesi(BarnematParser.SammenliknbarDesiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SammenliknbarDesi}
	 * labeled alternative in {@link BarnematParser#sammenliknbar}.
	 * @param ctx the parse tree
	 */
	void exitSammenliknbarDesi(BarnematParser.SammenliknbarDesiContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SammenliknbarHel}
	 * labeled alternative in {@link BarnematParser#sammenliknbar}.
	 * @param ctx the parse tree
	 */
	void enterSammenliknbarHel(BarnematParser.SammenliknbarHelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SammenliknbarHel}
	 * labeled alternative in {@link BarnematParser#sammenliknbar}.
	 * @param ctx the parse tree
	 */
	void exitSammenliknbarHel(BarnematParser.SammenliknbarHelContext ctx);
	/**
	 * Enter a parse tree produced by {@link BarnematParser#sammenlikn}.
	 * @param ctx the parse tree
	 */
	void enterSammenlikn(BarnematParser.SammenliknContext ctx);
	/**
	 * Exit a parse tree produced by {@link BarnematParser#sammenlikn}.
	 * @param ctx the parse tree
	 */
	void exitSammenlikn(BarnematParser.SammenliknContext ctx);
}