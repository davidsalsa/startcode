package nl.han.ica.icss.generator;

import nl.han.ica.icss.ast.*;

import java.util.ArrayList;

public class Generator {
	private ArrayList<ASTNode> list;

	public String generate(AST ast) {
		list = new ArrayList<>();
		traverseTree(ast.root);

		return list.toString();
	}

	public ASTNode traverseTree(ASTNode root){
		if(root == null){
			return null;
		}

		for(ASTNode child : root.getChildren()){
			list.add(root);
			traverseTree(child);
		}
		return root;
	}
}
