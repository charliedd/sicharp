package com.sicharp.syntaxAnalyzer;

import com.sicharp.lexicalAnalyzer.Token;

import java.util.ArrayList;
import java.util.List;

public class Node {
    List<Token> tokens;
    List<Node> neighborNodes;

    public Node(List<Token> tokens) {
        this.tokens = tokens;
        neighborNodes = new ArrayList<Node>();
    }

    public void addNeighborNode(Node node){
        neighborNodes.add(node);
    }

    @Override
    public String toString() {
        return tokens.toString();
    }
}
