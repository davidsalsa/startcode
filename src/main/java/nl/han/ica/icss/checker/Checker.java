package nl.han.ica.icss.checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.types.*;

public class Checker {

    private LinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
        ArrayList<ASTNode> body = ast.root.body;
        HashMap<String, Expression> definedVariables = new HashMap<>(); //The hashmap contains all defined variables.

        for (ASTNode nodes : body) {
            if (nodes instanceof VariableAssignment) {
                String variableName = ((VariableAssignment) nodes).name.name;
                Expression variableReferenceChild = (Expression) nodes.getChildren().get(1);
                definedVariables.put(variableName, variableReferenceChild);
            }
        }

        for (ASTNode node : body) {
            if (node instanceof Stylerule) {
                checkUndefinedVariables(node, definedVariables);
                checkOperation(node, definedVariables);

            }
        }
    }


    public void checkOperation(ASTNode node, HashMap<String, Expression> definedVariables) {
        for (ASTNode child : node.getChildren()) {
            if (child instanceof Declaration) {
                Expression expression = ((Declaration) child).expression;
                if (expression instanceof Operation) {
                    recOperations(expression, definedVariables);
                }
            }
        }
    }

    public ASTNode recOperations(ASTNode astNode, HashMap<String, Expression> definedVariables) {
        if(astNode instanceof ColorLiteral){ //Checks for color literals in operations
            astNode.setError("Cannot use color literals in an operation!");
        } else if (astNode instanceof Literal) {
            System.out.println(astNode);
            return astNode;
        } else if (astNode instanceof VariableReference) {
            System.out.println( definedVariables.get(((VariableReference) astNode).name));
            return definedVariables.get(((VariableReference) astNode).name);
        } else {
            for (ASTNode child : astNode.getChildren()) {
                recOperations(child, definedVariables);
            }
        }
        return astNode;
    }


    public void checkUndefinedVariables(ASTNode node, HashMap<String, Expression> definedVariables) { //Check if the hashmap contains the variable specified.
        for (ASTNode child : node.getChildren()) {
            if (child instanceof Declaration) {
                if (((Declaration) child).expression instanceof VariableReference) {
                    if (!definedVariables.containsKey(((VariableReference) ((Declaration) child).expression).name)) {
                        child.setError("Error! Reference to undefined variable!");
                    }
                }
            }
        }
    }
}
