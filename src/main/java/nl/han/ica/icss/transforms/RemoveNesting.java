package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;

import java.util.ArrayList;

public class RemoveNesting implements Transform {

    private ArrayList<Stylerule> nestedStylerules ;

       @Override
    public void apply(AST ast) {
           nestedStylerules = new ArrayList<>();
           ArrayList<ASTNode> body = ast.root.body;

           for (ASTNode nodes : body) {
               for (ASTNode child : nodes.getChildren()) {
                   if (child instanceof Stylerule) {
                       Stylerule parent = (Stylerule) nodes;
                       Stylerule nested = (Stylerule) child;
                       nestedStylerules.add(getNestedStylerules(parent.selectors.get(0), nested));
                       ((Stylerule) nodes).body.remove(child);
                   }
               }
           }
           body.addAll(nestedStylerules);
       }

    private Stylerule getNestedStylerules(Selector parent, Stylerule nested) {
        Stylerule stylerule = new Stylerule();
        stylerule.selectors.add(parent);
        stylerule.selectors.addAll(nested.selectors);
        for (ASTNode child : nested.getChildren()) {
            if (child instanceof Declaration) {
                stylerule.addChild(child);
            }

        }

        return stylerule;
    }
}
