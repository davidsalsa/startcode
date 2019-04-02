package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class EvalExpressions implements Transform {

    private LinkedList<HashMap<String, Literal>> variableValues;
    HashMap<String, Expression> definedVariables; //stores defined variables

    public EvalExpressions() {
        variableValues = new LinkedList<>();
        definedVariables = new HashMap<>();
    }

    @Override
    public void apply(AST ast) {
        variableValues = new LinkedList<>();
        ArrayList<ASTNode> body = ast.root.body;

        for (ASTNode nodes : body) { //stores all the defined variables in hashmap.
            if (nodes instanceof VariableAssignment) {
                String variableName = ((VariableAssignment) nodes).name.name;
                Expression variableReferenceChild = (Expression) nodes.getChildren().get(1);
                definedVariables.put(variableName, variableReferenceChild);
            }
        }

        for (ASTNode node : body) {
            checkForOperations(node); //Does all the eval expressions.
        }
    }

    public void checkForOperations(ASTNode node){ //Starts looking for operations
        if (node instanceof Stylerule) {
            checkDeclaration(node);
        }else if (node instanceof VariableAssignment) {
            checkVariableAssignment(node);
        }
    }

    public void checkVariableAssignment(ASTNode node){ //checks for operations in variableAssignments
        Expression expression = ((VariableAssignment) node).expression;
        if (expression instanceof Operation) { //if expression is an operation
            ((VariableAssignment) node).expression = calculate(expression); //replaces the expression with the calculated literal.
        }
    }

    public void checkDeclaration(ASTNode node) { //looks for operations in declarations
        for (ASTNode child : node.getChildren()) {
            if (child instanceof Declaration) {
                Expression expression = ((Declaration) child).expression;
                if (expression instanceof Operation) { //if expression is an operation
                    ((Declaration) child).expression = calculate(expression); //replaces the expression with the calculated literal.
                }
            }
        }
    }

    private Literal calculate(Expression expression) { //returns literal containing the sum of values.
        if (expression instanceof Literal) { //if already literal return it
            return (Literal) expression;
        } else if (expression instanceof VariableReference) { //returns value from reference
            return getValueFromVarRef(expression);
        } else if (expression instanceof Operation) { //if operation calculate new value from left and right
            Literal left = calculate(((Operation) expression).lhs);
            Literal right = calculate(((Operation) expression).rhs);
            if (expression instanceof AddOperation) { //if add operation
                return addLiterals(left, right);
            }
            if (expression instanceof SubtractOperation) {//if subtract
                return subtractLiterals(left, right);
            }
            if (expression instanceof MultiplyOperation) {//if multiply
                return multiplyLiterals(left, right);
            }

        }
        return null;
    }

    private Literal addLiterals(Literal left, Literal right) { //sums up all the literal values, returns literal with sum as value
        if (left instanceof PixelLiteral) {
            return new PixelLiteral(((PixelLiteral) left).value + ((PixelLiteral) right).value);
        }
        if (left instanceof ScalarLiteral) {
            return new ScalarLiteral(((ScalarLiteral) left).value + ((ScalarLiteral) right).value);
        }
        if (left instanceof PercentageLiteral) {
            return new PercentageLiteral(((PercentageLiteral) left).value + ((PercentageLiteral) right).value);
        }
        return null;
    }

    private Literal subtractLiterals(Literal left, Literal right) { //subtracts all literal values, returns literal with new value.
        if (left instanceof PixelLiteral) {
            return new PixelLiteral(((PixelLiteral) left).value - ((PixelLiteral) right).value);
        }
        if (left instanceof ScalarLiteral) {
            return new ScalarLiteral(((ScalarLiteral) left).value - ((ScalarLiteral) right).value);
        }
        if (left instanceof PercentageLiteral) {
            return new PercentageLiteral(((PercentageLiteral) left).value - ((PercentageLiteral) right).value);
        }
        return null;
    }

    private Literal multiplyLiterals(Literal left, Literal right) { //multiplies all literal values, returns literal with new value
        if (left instanceof PixelLiteral) {
            return new PixelLiteral(((PixelLiteral) left).value * ((ScalarLiteral) right).value);
        }
        if (right instanceof PixelLiteral) {
            return new PixelLiteral(((ScalarLiteral) left).value * ((PixelLiteral) right).value);
        }
        if (left instanceof PercentageLiteral) {
            return new PercentageLiteral(((PercentageLiteral) left).value * ((ScalarLiteral) right).value);
        }
        if (right instanceof PercentageLiteral) {
            return new PercentageLiteral(((ScalarLiteral) left).value * ((PercentageLiteral) right).value);
        }
        if (left instanceof ScalarLiteral && right instanceof ScalarLiteral) {
            return new ScalarLiteral(((ScalarLiteral) left).value * ((ScalarLiteral) right).value);
        }
        return null;
    }

    private Literal getValueFromVarRef(ASTNode astNode) { //returns the value from a variable reference
        if (astNode instanceof VariableReference) { //if there's a variable reference return the definition.
            return calculate(definedVariables.get(((VariableReference) astNode).name));
        }
        return null;
    }

}

