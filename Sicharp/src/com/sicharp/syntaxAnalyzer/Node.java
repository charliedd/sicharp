package com.sicharp.syntaxAnalyzer;

import com.sicharp.lexicalAnalyzer.Token;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private boolean isTerminal;
    private List<Token> tokens;
    private List<Node> childNodes;

    public Node(List<Token> tokens) {
        isTerminal = false;
        this.tokens = tokens;
        childNodes = new ArrayList<Node>();
    }

    public void addToken(Token token){
        tokens.add(token);
    }

    public void addChildNode(Node node){
        childNodes.add(node);
    }

    public List<Token> getTokens(){
        return this.tokens;
    }

    public List<Node> getChildNodes() {
        return this.childNodes;
    }

}
