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

        for (ASTNode nodes : body) {//stores all the defined variables in hashmap.
            if (nodes instanceof VariableAssignment) {
                String variableName = ((VariableAssignment) nodes).name.name;
                Expression variableReferenceChild = (Expression) nodes.getChildren().get(1);
                definedVariables.put(variableName, variableReferenceChild);
            }
        }

        for (ASTNode node : body) {
            if (node instanceof Stylerule) {
                checkOperation(node);
                checkDeclarations(node);
            } else if (node instanceof VariableAssignment) { //checks for undefined variables in variable definition
                checkOperation(node);
            }
        }
    }


    public ASTNode checkDeclarations(ASTNode node) {//checks if declaration definitions have correct types
        for (ASTNode child : node.getChildren()) {
            if(child instanceof Stylerule){ // if child is stylerule, recursively check children.
                return checkDeclarations(child);
            }
            if (child instanceof Declaration) {
                if(((Declaration) child).expression instanceof VariableReference){ // if instance of var ref
                    if (definedVariables.containsKey(((VariableReference) ((Declaration) child).expression).name)) { // if var ref is defined var
                        ((Declaration) child).expression = definedVariables.get(((VariableReference) ((Declaration) child).expression).name);//expression = var ref expression.
                    } else {
                        node.setError("Error! Reference to undefined variable!"); //else return undefined var error
                        return null; //stop function
                    }
                }
                //Switch case, if expression type doesn't match property set error.
                if(((Declaration) child).property.name.matches("background-color") && !(((Declaration) child).expression instanceof ColorLiteral)){
                    ((Declaration) child).expression.setError("Expression type does not match property.");
                } else  if(((Declaration) child).property.name.matches("color") && !(((Declaration) child).expression instanceof ColorLiteral)){
                    ((Declaration) child).expression.setError("Expression type does not match property.");
                } else  if(((Declaration) child).property.name.matches("width") && ((((Declaration) child).expression instanceof ColorLiteral) || (((Declaration) child).expression instanceof ScalarLiteral))){
                    ((Declaration) child).expression.setError("Expression type does not match property.");
                } else if(((Declaration) child).property.name.matches("height") && ((((Declaration) child).expression instanceof ColorLiteral) || (((Declaration) child).expression instanceof ScalarLiteral))){
                    ((Declaration) child).expression.setError("Expression type does not match property.");
                }
            }
        }
        return  node;
    }


    public ASTNode checkOperation(ASTNode node) { //looks for operations
        for (ASTNode child : node.getChildren()) {
            if(child instanceof Stylerule){ // if child is stylerule, recursively check children.
                return checkOperation(child);
            } else if (child instanceof Declaration) {
                Expression expression = ((Declaration) child).expression;
                if (expression instanceof VariableReference) {
                    if (!definedVariables.containsKey(((VariableReference) ((Declaration) child).expression).name)) { //if
                        ((Declaration) child).expression.setError("Error! Reference to undefined variable!");
                    }
                }
                if (expression instanceof Operation) {
                    return recOperations(expression);
                }
            }
        }
        return null;
    }

    public ASTNode recOperations(ASTNode astNode) { //recursively go through all the nodes in an operation
        if (astNode instanceof ColorLiteral) { //Checks for color literals in operations
            astNode.setError("Cannot use color literals in an operation!");
        } else if (astNode instanceof Literal) {
            return astNode;
        } else if (astNode instanceof VariableReference) { //the node is a reference.
            if (definedVariables.containsKey(((VariableReference) astNode).name)) { //if referenced variable is in the hashmap return definition
                return recOperations(definedVariables.get(((VariableReference) astNode).name));
            } else astNode.setError("Error! Reference to undefined variable!"); //else return undefined var error

        }  else if (astNode instanceof Operation) { //if the node is an operation
            for (ASTNode child : astNode.getChildren()) { //for every child
                recOperations(child); //Go through the children nodes
            }
        }
        checkMul(astNode);
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
}
