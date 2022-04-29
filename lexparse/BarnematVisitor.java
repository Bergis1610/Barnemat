// Generated from Barnemat.g4 by ANTLR 4.9.2
package lexparse; 
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BarnematParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BarnematVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BarnematParser#fil}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFil(BarnematParser.FilContext ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#hoveddel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHoveddel(BarnematParser.HoveddelContext ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(BarnematParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#testMetode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestMetode(BarnematParser.TestMetodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#variabelErklæring}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariabelErklæring(BarnematParser.VariabelErklæringContext ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#variabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariabel(BarnematParser.VariabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#variabeltype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariabeltype(BarnematParser.VariabeltypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#verdiTildeling}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVerdiTildeling(BarnematParser.VerdiTildelingContext ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#utskrift}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUtskrift(BarnematParser.UtskriftContext ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#les}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLes(BarnematParser.LesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Ganging}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGanging(BarnematParser.GangingContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Setning}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetning(BarnematParser.SetningContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Pluss}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPluss(BarnematParser.PlussContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Desimaltall}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDesimaltall(BarnematParser.DesimaltallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ID}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitID(BarnematParser.IDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Deling}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeling(BarnematParser.DelingContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parentes}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentes(BarnematParser.ParentesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Heltall}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeltall(BarnematParser.HeltallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link BarnematParser#uttrykk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinus(BarnematParser.MinusContext ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#entenEller}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntenEller(BarnematParser.EntenEllerContext ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#sammenlikningEnten}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSammenlikningEnten(BarnematParser.SammenlikningEntenContext ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#setningsSammenlikningEnten}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetningsSammenlikningEnten(BarnematParser.SetningsSammenlikningEntenContext ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#løkke1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLøkke1(BarnematParser.Løkke1Context ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#løkke2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLøkke2(BarnematParser.Løkke2Context ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#sammenlikningLøkke1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSammenlikningLøkke1(BarnematParser.SammenlikningLøkke1Context ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#sammenlikningLøkke2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSammenlikningLøkke2(BarnematParser.SammenlikningLøkke2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code SammenliknbarID}
	 * labeled alternative in {@link BarnematParser#sammenliknbar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSammenliknbarID(BarnematParser.SammenliknbarIDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SammenliknbarDesi}
	 * labeled alternative in {@link BarnematParser#sammenliknbar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSammenliknbarDesi(BarnematParser.SammenliknbarDesiContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SammenliknbarHel}
	 * labeled alternative in {@link BarnematParser#sammenliknbar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSammenliknbarHel(BarnematParser.SammenliknbarHelContext ctx);
	/**
	 * Visit a parse tree produced by {@link BarnematParser#sammenlikn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSammenlikn(BarnematParser.SammenliknContext ctx);
}