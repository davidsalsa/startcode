package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;

import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.Stack;

public class RemoveNesting implements Transform {

    ArrayList<ASTNode> nestedStylerules;

    @Override
    public void apply(AST ast) {
        nestedStylerules = new ArrayList<>();
        ArrayList<ASTNode> body = ast.root.body;
        for (ASTNode node : body) {
            updateNestedStyleRules(node); //looks through the body, finds and saves all nested nodes
        }
        for (ASTNode node : nestedStylerules) {
            ast.root.addChild(node); //adds all the nestings in to the roots child list.
        }
    }

    public void updateNestedStyleRules(ASTNode node) {
        if (node instanceof Stylerule) {
            for (ASTNode nested : node.getChildren()) {
                if (nested instanceof Stylerule) {
                    ((Stylerule)nested).selectors.add(((Stylerule) node).selectors.get(0)); //if first layer nesting, add selector of top rule
                    nestedStylerules.add(nested); //add the nesting to the list
                    getNestedStyleRules(nested); //check recursively for nesting within nesting and store into list
                    ((Stylerule) node).body.remove(nested); //removes all nestings from stylerules
                }
            }
        }
    }

    public ASTNode getNestedStyleRules(ASTNode nested) {
        for (ASTNode child : nested.getChildren()) {
            if (child instanceof Stylerule) {
                for (Selector selector : ((Stylerule) nested).selectors) {
                    ((Stylerule) child).selectors.add(selector); //adds selector to nestings within nestings
                }
                ((Stylerule) nested).body.remove(child); //removes all stylerule children from nesting
                nestedStylerules.add(child); //adds nestings to list
                getNestedStyleRules(child); //recursively checks for more nestings withing nesting

            }
        }
        return nested;
    }

}
