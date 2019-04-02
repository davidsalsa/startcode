package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;

import java.util.ArrayList;

public class RemoveNesting implements Transform {

    ArrayList<ASTNode> nestedStylerules;

    @Override
    public void apply(AST ast) {
        nestedStylerules = new ArrayList<>();
        ArrayList<ASTNode> body = ast.root.body;
        for (ASTNode node : body) {
            updateNestedStyleRules(node);
        }
        for (ASTNode node : nestedStylerules) {
            ast.root.addChild(node);
            System.out.println(node);
        }

    }

    public void updateNestedStyleRules(ASTNode node) {
        if (node instanceof Stylerule) {
            for (ASTNode nested : node.getChildren()) {
                if (nested instanceof Stylerule) {
                    ((Stylerule)nested).selectors.add(((Stylerule) node).selectors.get(0));
                    nestedStylerules.add(nested);
                    getNestedStyleRules(nested);
                    ((Stylerule) node).body.remove(nested);
                }
            }
        }
    }

    public ASTNode getNestedStyleRules(ASTNode nested) {
            for (ASTNode child : nested.getChildren()) {
                if (child instanceof Stylerule) {
                    for (Selector selector : ((Stylerule) nested).selectors) {
                        ((Stylerule) child).selectors.add(selector);
                    }
                    ((Stylerule) nested).body.remove(child);
                    nestedStylerules.add(child);
                    getNestedStyleRules(child);

                }
            }
        return nested;
    }

}
