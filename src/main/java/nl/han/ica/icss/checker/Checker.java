package nl.han.ica.icss.checker;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.*;

import javax.sound.midi.SysexMessage;

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
                checkDeclarations(node, definedVariables);
            }else if (node instanceof VariableAssignment) {
                checkUndefinedVariables(node, definedVariables);
                checkOperation(node, definedVariables);
            }
        }
    }


    public void checkDeclarations(ASTNode node, HashMap<String, Expression> definedVariables){
        for(ASTNode child : node.getChildren()){
            if(child instanceof Declaration) {
                if(((Declaration) child).expression instanceof ColorLiteral){

                } else if(((Declaration) child).expression instanceof PercentageLiteral){

                } else if (((Declaration) child).expression instanceof PixelLiteral){

                } else if(((Declaration) child).expression instanceof ScalarLiteral){

                }
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
        if (astNode instanceof ColorLiteral) { //Checks for color literals in operations
            astNode.setError("Cannot use color literals in an operation!");
        } else if (astNode instanceof Literal) {
            return astNode;
        } else if (astNode instanceof VariableReference) {
            return definedVariables.get(((VariableReference) astNode).name);
        } else {
            for (ASTNode child : astNode.getChildren()) {
                recOperations(child, definedVariables);
            }
        }
        checkPlusMinOperations(astNode, definedVariables);
        checkMul(astNode, definedVariables);
        return astNode;
    }

    public void checkPlusMinOperations(ASTNode astNode, HashMap<String, Expression> definedVariables) {
        if (astNode instanceof AddOperation) {
            ASTNode left = recOperations(((AddOperation) astNode).lhs, definedVariables);
            ASTNode right = recOperations(((AddOperation) astNode).rhs, definedVariables);

            if (left.getClass() != right.getClass()) {

                if (!(right instanceof Operation)) {
                    right.setError("Left and right operator do not match!");
                } else if(right instanceof  MultiplyOperation){
                    if(((MultiplyOperation) right).lhs.getClass() != left.getClass() && ((MultiplyOperation) right).rhs.getClass() != left.getClass()){
                        right.setError("Left and right operator do not match!");
                    }
                }
            }
        }
        if (astNode instanceof SubtractOperation) {
            ASTNode left = recOperations(((SubtractOperation) astNode).lhs, definedVariables);
            ASTNode right = recOperations(((SubtractOperation) astNode).rhs, definedVariables);

            if (left.getClass() != right.getClass()) {

                if (!(right instanceof Operation)) {
                    right.setError("Left and right operator do not match!");
                }
            }
        }
    }

    public void checkMul(ASTNode astNode, HashMap<String, Expression> definedVariables) { //Check if mul operation contains scalar.
        if (astNode instanceof MultiplyOperation) {
            ASTNode left = recOperations(((MultiplyOperation) astNode).lhs, definedVariables);
            ASTNode right = recOperations(((MultiplyOperation) astNode).rhs, definedVariables);

            if(!(left instanceof ScalarLiteral) && !(right instanceof ScalarLiteral)){
                astNode.setError("Multiply operations requires at least one scalar value!");
            }
        }
    }

    public void checkUndefinedVariables(ASTNode node, HashMap<String, Expression> definedVariables) { //Check if the hashmap contains the variable specified.
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
