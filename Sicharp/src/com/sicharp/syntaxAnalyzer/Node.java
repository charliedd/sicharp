package com.sicharp.syntaxAnalyzer;

import com.sicharp.lexicalAnalyzer.Token;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Token> tokens;
    private List<Node> childNodes;

    public Node(List<Token> tokens) {
        this.tokens = tokens;
        childNodes = new ArrayList<Node>();
    }

    public void addChildNode(Node node){
        childNodes.add(node);
    }

    public boolean hasNoChildNodes(){
        return childNodes.isEmpty();
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        return tokens.toString();
    }


}
