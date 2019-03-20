// Generated from C:/Users/david/startcode-2019.2.1/startcode/src/main/antlr4/nl/han/ica/icss/parser\ICSS.g4 by ANTLR 4.7.2
package nl.han.ica.icss.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ICSSParser}.
 */
public interface ICSSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 */
	void enterStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylerule}.
	 * @param ctx the parse tree
	 */
	void exitStylerule(ICSSParser.StyleruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#declarationWithoutOperations}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationWithoutOperations(ICSSParser.DeclarationWithoutOperationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#declarationWithoutOperations}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationWithoutOperations(ICSSParser.DeclarationWithoutOperationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#declarationWithOperations}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationWithOperations(ICSSParser.DeclarationWithOperationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#declarationWithOperations}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationWithOperations(ICSSParser.DeclarationWithOperationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(ICSSParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(ICSSParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#propertyName}.
	 * @param ctx the parse tree
	 */
	void enterPropertyName(ICSSParser.PropertyNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#propertyName}.
	 * @param ctx the parse tree
	 */
	void exitPropertyName(ICSSParser.PropertyNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#background_color}.
	 * @param ctx the parse tree
	 */
	void enterBackground_color(ICSSParser.Background_colorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#background_color}.
	 * @param ctx the parse tree
	 */
	void exitBackground_color(ICSSParser.Background_colorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#width}.
	 * @param ctx the parse tree
	 */
	void enterWidth(ICSSParser.WidthContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#width}.
	 * @param ctx the parse tree
	 */
	void exitWidth(ICSSParser.WidthContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#color}.
	 * @param ctx the parse tree
	 */
	void enterColor(ICSSParser.ColorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#color}.
	 * @param ctx the parse tree
	 */
	void exitColor(ICSSParser.ColorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#variableAssignment}.
	 * @param ctx the parse tree
	 */
	void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#variableAssignment}.
	 * @param ctx the parse tree
	 */
	void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#variableName}.
	 * @param ctx the parse tree
	 */
	void enterVariableName(ICSSParser.VariableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#variableName}.
	 * @param ctx the parse tree
	 */
	void exitVariableName(ICSSParser.VariableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(ICSSParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(ICSSParser.OperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(ICSSParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(ICSSParser.OperatorContext ctx);
}