package nl.han.ica.icss.transforms;

import com.google.errorprone.annotations.Var;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class EvalExpressions implements Transform {

    private LinkedList<HashMap<String, Literal>> variableValues;

    HashMap<String, Expression> definedVariables;

    public EvalExpressions() {
        variableValues = new LinkedList<>();
        definedVariables = new HashMap<>();
    }

    @Override
    public void apply(AST ast) {
        variableValues = new LinkedList<>();
        ArrayList<ASTNode> body = ast.root.body;

        for (ASTNode nodes : body) {
            if (nodes instanceof VariableAssignment) {
                String variableName = ((VariableAssignment) nodes).name.name;
                Expression variableReferenceChild = (Expression) nodes.getChildren().get(1);
                definedVariables.put(variableName, variableReferenceChild);
            }
        }

        for (ASTNode node : body) {
            if (node instanceof Stylerule) {
                checkOperation(node);
            }
        }
    }


    public void checkOperation(ASTNode node) {
        for (ASTNode child : node.getChildren()) {
            if (child instanceof Declaration) {
                Expression expression = ((Declaration) child).expression;
                if (expression instanceof Operation) {
                    ((Declaration) child).expression = calculate(expression);
                }
            }
        }
    }

    private Literal calculate(Expression expression) {
        if (expression instanceof Literal) {
            return (Literal) expression;
        } else if (expression instanceof VariableReference) {
            return getValueFromVarRef(expression);
        } else if (expression instanceof Operation) {
            Literal left = calculate(((Operation) expression).lhs);
            Literal right = calculate(((Operation) expression).rhs);
            if (expression instanceof AddOperation) {
                return addLiterals(left, right);
            }
            if (expression instanceof SubtractOperation) {
                return subtractLiterals(left, right);
            }
            if (expression instanceof MultiplyOperation) {
                return multiplyLiterals(left, right);
            }

        }
        return null;
    }

    private Literal addLiterals(Literal left, Literal right) {
        if (left instanceof PixelLiteral && right instanceof PixelLiteral) {
            ((PixelLiteral) left).value = ((PixelLiteral) left).value + ((PixelLiteral) right).value;
        }
        if (left instanceof ScalarLiteral && right instanceof ScalarLiteral) {
            ((ScalarLiteral) left).value = ((ScalarLiteral) left).value + ((ScalarLiteral) right).value;
        }
        if (left instanceof PercentageLiteral) {
            ((PercentageLiteral) left).value = ((PercentageLiteral) left).value + ((PercentageLiteral) right).value;
        }
        return left;
    }

    private Literal subtractLiterals(Literal left, Literal right) {
        if (left instanceof PixelLiteral && right instanceof PixelLiteral) {
            ((PixelLiteral) left).value = ((PixelLiteral) left).value - ((PixelLiteral) right).value;
        }
        if (left instanceof ScalarLiteral && right instanceof ScalarLiteral) {
            ((ScalarLiteral) left).value = ((ScalarLiteral) left).value - ((ScalarLiteral) right).value;
        }
        if (left instanceof PercentageLiteral && right instanceof PercentageLiteral) {
            ((PercentageLiteral) left).value = ((PercentageLiteral) left).value - ((PercentageLiteral) right).value;
        }
        return left;
    }

    private Literal multiplyLiterals(Literal left, Literal right) {
        if (left instanceof PixelLiteral) {
            ((PixelLiteral) left).value = ((PixelLiteral) left).value * ((ScalarLiteral) right).value;
            return left;
        }
        if (right instanceof PixelLiteral) {
            ((PixelLiteral) right).value = ((ScalarLiteral) left).value * ((PixelLiteral) right).value;
            return right;
        }
        if (left instanceof PercentageLiteral) {
                ((PercentageLiteral) left).value = ((PercentageLiteral) left).value * ((ScalarLiteral) right).value;
                return left;
        }
        if (right instanceof PercentageLiteral) {
            ((PercentageLiteral) right).value = ((ScalarLiteral) left).value * ((PercentageLiteral) right).value;
            return right;
        }
        if (left instanceof ScalarLiteral && right instanceof ScalarLiteral) {
            ((ScalarLiteral) left).value = ((ScalarLiteral) left).value * ((ScalarLiteral) right).value;
            return left;
        }
        return null;
    }

    private Literal getValueFromVarRef(ASTNode astNode) {
        if (astNode instanceof VariableReference) { //if there's a variable reference return the definition.
            return calculate(definedVariables.get(((VariableReference) astNode).name));
        }
        return null;
    }

}

