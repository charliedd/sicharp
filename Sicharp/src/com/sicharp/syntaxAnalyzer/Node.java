package com.sicharp.syntaxAnalyzer;

import com.sicharp.lexicalAnalyzer.Token;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Token> tokens;
    private List<Node> childNodes;
    private String type;

    public Node(List<Token> tokens, String type) {
        this.type = type;
        this.tokens = tokens;
        childNodes = new ArrayList<Node>();
    }

    public Node(Token token, String type){
        this.type = type;
        this.tokens = new ArrayList<Token>();
        this.tokens.add(token);
        this.childNodes = new ArrayList<Node>();

    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
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

    public String getType() {
        return type;
    }
}
