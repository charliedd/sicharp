package com.sicharp.semanticAnalyzer;

import com.sicharp.syntaxAnalyzer.Node;
import com.sicharp.syntaxAnalyzer.SyntaxTree;

public class SemanticAnalyzer {

    private SyntaxTree syntaxTree;



    public SemanticAnalyzer(SyntaxTree syntaxTree) {
        this.syntaxTree = syntaxTree;
        start();
    }

    public void start(){
        Node root = syntaxTree.getRoot();
        traverseTree(root);
    }

    public void traverseTree(Node node){
        if(node.getType().equals("DECLARATION")){
            System.out.println("ESTO ES UNA DECLARACION: " + node.getTokens());
        }

        if(node.getType().equals("ASIGNDECL")){
            System.out.println("ESTO ES UNA ASIGNACIONDECLARACION: " + node.getTokens());
        }


        for(Node childNode: node.getChildNodes()){
            traverseTree(childNode);
        }

    }



}
