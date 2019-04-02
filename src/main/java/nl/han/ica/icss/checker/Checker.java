package nl.han.ica.icss.checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.types.*;


public class Checker {

    private LinkedList<HashMap<String, ExpressionType>> variableTypes;
    HashMap<String, Expression> definedVariables;

    public Checker() {
        definedVariables = new HashMap<>();
    }

    public void check(AST ast) {
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
                checkUndefinedVariables(node);
                checkOperation(node);
                checkDeclarations(node);
            } else if (node instanceof VariableAssignment) {
                checkUndefinedVariables(node);
                checkOperation(node);
            }
        }
    }


    public void checkDeclarations(ASTNode node) {
        for (ASTNode child : node.getChildren()) {
            if (child instanceof Declaration) {
            }
        }
    }

    public void checkOperation(ASTNode node) {
        for (ASTNode child : node.getChildren()) {
            if (child instanceof Declaration) {
                Expression expression = ((Declaration) child).expression;
                if (expression instanceof Operation) {
                    recOperations(expression);

                }
            }
        }
    }

    public ASTNode recOperations(ASTNode astNode) {
        if (astNode instanceof ColorLiteral) { //Checks for color literals in operations
            astNode.setError("Cannot use color literals in an operation!");
        } else if (astNode instanceof Literal) {
            return astNode;
        } else if (astNode instanceof VariableReference) { //the node is a reference.
            if (definedVariables.containsKey(((VariableReference) astNode).name)) { //if referenced variable is in the hashmap return definition
                return recOperations(definedVariables.get(((VariableReference) astNode).name));
            } else astNode.setError("Error! Reference to undefined variable!"); //else return undefined var error

        } else if (astNode instanceof Operation) {
            for (ASTNode child : astNode.getChildren()) {
                recOperations(child);
                checkUndefinedVariables(child);
            }
        }
        checkMul(astNode);
        checkAdd(astNode);
        return astNode;
    }

    public void checkMul(ASTNode astNode) { //Check if mul operation contains scalar.
        if (astNode instanceof MultiplyOperation) {
            ASTNode left = recOperations(((MultiplyOperation) astNode).lhs);
            ASTNode right = recOperations(((MultiplyOperation) astNode).rhs);

            if (!(left instanceof ScalarLiteral) && !(right instanceof ScalarLiteral)) {
                astNode.setError("Multiply operations requires at least one scalar value!");
            }
        }
    }

    public void checkAdd(ASTNode astNode) {

    }

    public void checkUndefinedVariables(ASTNode node) { //Check if the hashmap contains the variable specified.
        for (ASTNode child : node.getChildren()) {
            if (child instanceof Declaration) {
                if (((Declaration) child).expression instanceof VariableReference) {
                    if (!definedVariables.containsKey(((VariableReference) ((Declaration) child).expression).name)) {
                        ((Declaration) child).expression.setError("Error! Reference to undefined variable!");
                    }
                }
            }
        }
    }
}
