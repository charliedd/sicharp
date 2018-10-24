package com.sicharp.syntaxAnalyzer;

import com.sicharp.lexicalAnalyzer.SymbolTable;

public class SyntaxAnalyzer {
    SymbolTable symbolTable;
    Tree graph;


    public SyntaxAnalyzer(SymbolTable symbolTable){
        this.symbolTable = symbolTable;
        graph = new Tree(symbolTable.getTokenList());
    }

    @Override
    public String toString() {
        return graph.toString();
    }
}
