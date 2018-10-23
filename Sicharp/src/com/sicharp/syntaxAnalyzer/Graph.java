package com.sicharp.syntaxAnalyzer;

import com.sicharp.lexicalAnalyzer.Token;
import com.sicharp.lexicalCategories.symbols.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    Node root;
    int size;

    public Graph(List<Token> stmnt){
        Node node = new Node(stmnt);
        root = node;
        size = 1;
        parseGraph();
    }

    @Override
    public String toString() {
        StringBuilder graphString = new StringBuilder();
        traversal(graphString,root);
        return graphString.toString();
    }

    private void parseGraph(){

        Node currentNode = root;

        int startIndex = 0;
        int endIndex = 0;



        for(Token token: currentNode.tokens){

            if(token.getLexicalCategory() instanceof Symbol){
                Node newNode = new Node(currentNode.tokens.subList(startIndex,++endIndex));
                startIndex = endIndex;
                currentNode.addNeighborNode(newNode);
            }else{
                endIndex++;
            }

        }


        System.out.println(currentNode.neighborNodes);

    }

    private void parse(Node currentNode){

    }

    private void traversal(StringBuilder graphString, Node currentNode){
        graphString.append(currentNode.toString());
        //System.out.println(currentNode.toString());
        if(currentNode.neighborNodes.isEmpty())
            return;

        for(Node node : currentNode.neighborNodes)
            traversal(graphString,node);

    }

}
