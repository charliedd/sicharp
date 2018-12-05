package com.sicharp.semanticAnalyzer;

import com.sicharp.syntaxAnalyzer.Node;
import com.sicharp.syntaxAnalyzer.SyntaxTree;

import java.util.List;

public class SemanticAnalyzer {

    private SyntaxTree syntaxTree;
    private VariableList variableList;



    public SemanticAnalyzer(SyntaxTree syntaxTree) {
        this.syntaxTree = syntaxTree;
        this.variableList = new VariableList();
        start();
    }

    public void start(){
        Node root = syntaxTree.getRoot();
        traverseTree(root);
    }

    public void traverseTree(Node node){
        if(node.getType().equals("DECLARATION")){
            System.out.println("ESTO ES UNA DECLARACION: " + node.getTokens());
            List<Node> childNodes = node.getChildNodes();

            Node nodeCategory = childNodes.get(0);
            Node nodeName = childNodes.get(1);

            String category = nodeCategory.getTokens().get(0).getAttribute().toString();
            String name = nodeName.getTokens().get(0).getAttribute().toString();

            System.out.println(category);
            System.out.println(name);

            variableList.addVariable(category,name,"");


        }

        if(node.getType().equals("ASIGNDECL")){
            System.out.println("ESTO ES UNA ASIGNACIONDECLARACION: " + node.getTokens());
        }

        if(node.getType().equals("ASIGNATION")){
            System.out.println("ESTO ES UNA ASIGNACION " + node.getTokens());
        }

        for(Node childNode: node.getChildNodes()){
            traverseTree(childNode);
        }

    }

    public String getType(Node node){
        return null;
    }

    public VariableList getVariableList() {
        return variableList;
    }
}
