package com.sicharp.syntaxAnalyzer;

import com.sicharp.lexicalAnalyzer.SymbolTable;
import com.sicharp.lexicalAnalyzer.Token;
import com.sicharp.lexicalCategories.symbols.Symbol;
import com.sicharp.syntaxAnalyzer.production.Declaration;
import com.sicharp.syntaxAnalyzer.production.Rule;
import com.sicharp.syntaxAnalyzer.production.Start;

import java.util.List;

public class SyntaxTree {
    Node root;
    int size;
    Rule start,declaration;

    public SyntaxTree(SymbolTable symbolTable){

        List<Token> tokenList = symbolTable.getTokenList();
        Node node = new Node(tokenList);

        start = new Start();

        root = node;
        size = 1;
        createTree();
    }


    private void createTree2(){

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

    private void createTree(){
        root = parse(root);
    }

    private Node parse(Node currentNode){
        if(start.belongsToThisRule(currentNode.getTokens())){
            Node newNode = new Node()
            parse(NodecurrentNode.getTokens().get(0))
            System.out.println("es una asignacion");
        }
        else if(declaration.belongsToThisRule(currentNode.getTokens())){

        }
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
