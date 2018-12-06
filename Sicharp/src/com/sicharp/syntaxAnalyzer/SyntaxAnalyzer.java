package com.sicharp.syntaxAnalyzer;

import com.sicharp.lexicalAnalyzer.SymbolTable;

public class SyntaxAnalyzer {
    SyntaxTree tree;

    public SyntaxAnalyzer(SymbolTable symbolTable){
        tree = new SyntaxTree(symbolTable);
    }

    @Override
    public String toString() {
        return tree.toString();
    }

    public SyntaxTree getTree() {
        return tree;
    }
}
