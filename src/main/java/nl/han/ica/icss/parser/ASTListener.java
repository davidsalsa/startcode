package nl.han.ica.icss.parser;

import java.util.Stack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;
import org.antlr.v4.runtime.atn.SemanticContext;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {
	
	//Accumulator attributes:
	private AST ast;

	//Use this to keep track of the parent nodes when recursively traversing the ast
	private Stack<ASTNode> currentContainer;

	public ASTListener() {
		ast = new AST();
		currentContainer = new Stack<>();
		currentContainer.push(ast.root);
	}
    public AST getAST() {
        return ast;
    }

	@Override
	public void enterStylesheet(ICSSParser.StylesheetContext ctx) { //enter stack
		currentContainer = new Stack<>();
		currentContainer.push(ast.root);
	}

	@Override
	public void enterStylerule(ICSSParser.StyleruleContext ctx) {
		Stylerule stylerule = new Stylerule();
		currentContainer.peek().addChild(stylerule);

		currentContainer.push(stylerule);
	}

	@Override
	public void exitStylerule(ICSSParser.StyleruleContext ctx) {
		if (!currentContainer.empty()) {
			currentContainer.pop();
		}
	}

	@Override
	public void enterSelector(ICSSParser.SelectorContext ctx) {
		Selector selector = getSelector(ctx);

		currentContainer.peek().addChild(selector);
		currentContainer.push(selector);
	}

	@Override
	public void exitSelector(ICSSParser.SelectorContext ctx) {
		if (!currentContainer.empty()) {
			currentContainer.pop();
		}
	}

	@Override
	public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
		Declaration declaration = new Declaration(ctx.getText());
		currentContainer.peek().addChild(declaration);
		currentContainer.push(declaration);
	}

	@Override
	public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
		if (!currentContainer.empty()) {
			currentContainer.pop();
		}
	}

	@Override
	public void enterValue(ICSSParser.ValueContext ctx) {
		Expression expression = getLiteral(ctx);
		currentContainer.peek().addChild(expression);
		currentContainer.push(expression);
	}

	@Override
	public void exitValue(ICSSParser.ValueContext ctx) {
		if (!currentContainer.empty()) {
			currentContainer.pop();
		}
	}

	@Override public void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
		VariableAssignment variableAssignment = new VariableAssignment();
		currentContainer.peek().addChild(variableAssignment);
		currentContainer.push(variableAssignment);
	}

	@Override public void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
		if (!currentContainer.empty()) {
			currentContainer.pop();
		}
	}

	@Override public void enterVariableName(ICSSParser.VariableNameContext ctx) {
		VariableReference variableReference = new VariableReference(ctx.getText());
		currentContainer.peek().addChild(variableReference);
		currentContainer.push(variableReference);
	}

	@Override public void exitVariableName(ICSSParser.VariableNameContext ctx) {
		if (!currentContainer.empty()) {
			currentContainer.pop();
		}
	}


	public void enterMultiplyOperation(ICSSParser.MultiplyOperationContext ctx){
		MultiplyOperation multiplyOperation = new MultiplyOperation();
		currentContainer.peek().addChild(multiplyOperation);
		currentContainer.push(multiplyOperation);
	}

	@Override public void exitMultiplyOperation(ICSSParser.MultiplyOperationContext ctx) {
		if (!currentContainer.empty()) {
			currentContainer.pop();
		}
	}

	@Override public void enterPlusOrMinOperation(ICSSParser.PlusOrMinOperationContext ctx) {
		Operation operation = getOperation(ctx);
		currentContainer.peek().addChild(operation);
		currentContainer.push(operation);
	}

	@Override public void exitPlusOrMinOperation(ICSSParser.PlusOrMinOperationContext ctx) {
		if (!currentContainer.empty()) {
			currentContainer.pop();
		}
	}

	@Override public void enterPropertyName(ICSSParser.PropertyNameContext ctx) {
		PropertyName propertyName = new PropertyName(ctx.getText());
		currentContainer.peek().addChild(propertyName);
		currentContainer.push(propertyName);
	}

	@Override public void exitPropertyName(ICSSParser.PropertyNameContext ctx) {
		if (!currentContainer.empty()) {
			currentContainer.pop();
		}
	}

	private Operation getOperation(ICSSParser.PlusOrMinOperationContext ctx){ //returns the right type of operation
		if(ctx.PLUS()!= null){
			return new AddOperation();
		} else if (ctx.MIN() != null){
			return new SubtractOperation();
		}
		return  null;
	}

	private Expression getLiteral(ICSSParser.ValueContext ctx) {//returns the right type of literal
		if (ctx.pixelLiteral() != null) {
			return new PixelLiteral(ctx.pixelLiteral().getText());
		} else if (ctx.colorLiteral() != null) {
			return new ColorLiteral(ctx.colorLiteral().getText());
		} else if (ctx.scalarLiteral() != null) {
			return new ScalarLiteral(ctx.scalarLiteral().getText());
		} else if (ctx.percentageLiteral() != null) {
			return new PercentageLiteral(ctx.percentageLiteral().getText());
		} else if (ctx.variableName() != null) {
			return new VariableReference(ctx.variableName().getText());
		}
		return null;
	}

	private Selector getSelector(ICSSParser.SelectorContext ctx) { //returns the right type of selector
		if(ctx.classSelector() !=null){
			return new ClassSelector(ctx.getText());
		} else if(ctx.idSelector() !=null){
			return new IdSelector(ctx.getText());
		} else if(ctx.tagSelector() !=null) {
			return new TagSelector(ctx.getText());
		}
		return null;
	}
}
