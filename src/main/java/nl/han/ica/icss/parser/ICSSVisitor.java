// Generated from C:/Users/david/startcode-2019.2.1/startcode/src/main/antlr4/nl/han/ica/icss/parser\ICSS.g4 by ANTLR 4.7.2
package nl.han.ica.icss.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ICSSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ICSSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#declarationWithoutOperations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationWithoutOperations(ICSSParser.DeclarationWithoutOperationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#declarationWithOperations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationWithOperations(ICSSParser.DeclarationWithOperationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(ICSSParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#propertyName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyName(ICSSParser.PropertyNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#background_color}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackground_color(ICSSParser.Background_colorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#width}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidth(ICSSParser.WidthContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#color}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColor(ICSSParser.ColorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#variableAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableAssignment(ICSSParser.VariableAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#variableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableName(ICSSParser.VariableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(ICSSParser.OperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(ICSSParser.OperatorContext ctx);
}