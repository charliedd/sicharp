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

    public SyntaxTree(SymbolTable symbolTable){
        List<Token> tokenList = symbolTable.getTokenList();
        Node node = new Node(tokenList);
        root = node;
        size = 1;
        createTree();
    }

    private void createTree(){
        if(!checkFirstProductionRule())return;

       // root = parse(root);
    }

    private boolean checkFirstProductionRule(){
        List<Token> currentTokens = root.getTokens();

        int size = currentTokens.size();

        Token fierroToken = currentTokens.get(0);
        Token parentesisToken = currentTokens.get(1);
        Token parentesisCerrado = currentTokens.get(2);
        Token llaveAbierto = currentTokens.get(3);
        Token llaveCerrada = currentTokens.get(size -1);

        System.out.println(fierroToken.getAttribute());
        System.out.println(parentesisToken.getAttribute());


        if(!fierroToken.getAttribute().equals("entera")){
            System.out.println("nel");
            return false;
        }
        else System.out.println("NOp");
        if(!parentesisToken.getAttribute().equals("("))return false;
        else System.out.println("NOp");
        if(!parentesisCerrado.getAttribute().equals(")"))return false;
        else System.out.println("NOp");
        if(!llaveAbierto.getAttribute().equals("{"))return false;
        else System.out.println("NOp");
        if(!llaveCerrada.getAttribute().equals("}"))return false;
        else System.out.println("NOp");

        return true;

    }

//    private Node parse(Node currentNode){
//
//    }

    @Override
    public String toString() {
        StringBuilder treeAsString = new StringBuilder();
        return treeAsString.toString();
    }

}
