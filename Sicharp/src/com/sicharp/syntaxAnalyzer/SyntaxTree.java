package com.sicharp.syntaxAnalyzer;

import com.apple.eawt.AppEvent;
import com.sicharp.lexicalAnalyzer.SymbolTable;
import com.sicharp.lexicalAnalyzer.Token;
import com.sicharp.lexicalCategories.symbols.Symbol;
import com.sicharp.syntaxAnalyzer.production.Assignment;

import java.util.List;

public class SyntaxTree {
    Node root;
    int size;

    public SyntaxTree(SymbolTable symbolTable){

        List<Token> tokenList = symbolTable.getTokenList();
        Node node = new Node(tokenList);

        root = node;
        size = 1;
        createTree();
    }


    private void createTree(){

        Node currentNode = root;

        int startIndex = 0;
        int endIndex = 0;



        for(Token token: currentNode.getTokens()){

            if(token.getLexicalCategory() instanceof Symbol){
                Node newNode = new Node(currentNode.getTokens().subList(startIndex,++endIndex));
                parse(newNode);
                startIndex = endIndex;
                currentNode.addChildNode(newNode);
            }else{
                endIndex++;
            }

        }


    }

    private void parse(Node currentNode){
        Assignment assignment = new Assignment();
        if(assignment.belongsToThisRule(currentNode.getTokens()))
            System.out.println("es una asignacion");
        else
            System.out.println("false");
    }

    @Override
    public String toString() {
        StringBuilder treeAsString = new StringBuilder();
        recursiveTraversal(treeAsString,root);
        return treeAsString.toString();
    }


    private void recursiveTraversal(StringBuilder graphString, Node currentNode){
        graphString.append(currentNode.toString() + '\n');

        if(currentNode.hasNoChildNodes())
            return;

        for(Node node : currentNode.getChildNodes())
            recursiveTraversal(graphString,node);

    }

}
