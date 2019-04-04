package nl.han.ica.icss.generator;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

public class Generator {

	private String css;
	private String indent;
	public String generate(AST ast) {
		css = ""; //empty string
		indent = "    "; //indent
		traverseTree(ast.root);
		return css;
	}

	public ASTNode traverseTree(ASTNode root){
		if(root == null){
			return null;
		}
		//Check the list for every selector type for the right order.
		if(root instanceof Stylerule){
			for(Selector selector : ((Stylerule) root).selectors){
					css += selector + " ";
			}
			css += "{ \n";
		}
		//check for declarations, get the property name and expression value
		if(root instanceof Declaration){
			css += indent + ((Declaration) root).property.name + ": ";
			if(((Declaration) root).expression instanceof PixelLiteral){
				css += ((PixelLiteral) ((Declaration) root).expression).value + "px; \n";
			}
			if(((Declaration) root).expression instanceof ColorLiteral){
				css += ((ColorLiteral) ((Declaration) root).expression).value + "; \n";
			}
			if(((Declaration) root).expression instanceof PercentageLiteral){
				css += ((PercentageLiteral) ((Declaration) root).expression).value + "%; \n";
			}

		}

		for(ASTNode child : root.getChildren()){ //recursively traverse the tree for every childnode
			traverseTree(child);
			if(child instanceof Stylerule){ //if the style rule recursion is over, close bracket
				css += "} \n \n";
			}
		}
		return root;
	}
}
