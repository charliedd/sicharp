package com.sicharp;

import com.sicharp.lexicalAnalyzer.LexicalAnalyzer;
import com.sicharp.lexicalAnalyzer.SourceFileReader;
import com.sicharp.lexicalAnalyzer.SymbolTable;
import com.sicharp.lexicalAnalyzer.Token;
import com.sicharp.semanticAnalyzer.SemanticAnalyzer;
import com.sicharp.semanticAnalyzer.Variable;
import com.sicharp.semanticAnalyzer.VariableList;
import com.sicharp.syntaxAnalyzer.SyntaxAnalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer("/Users/carlosalbert.delgado/Documents/sicharp/Sicharp/ExampleCode/sampleCode3.txt");
        System.out.println("SYMBOLTABLE");
        lexicalAnalyzer.printSymbolTable();
        SymbolTable symbolTable = lexicalAnalyzer.getSymbolTable();
        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(symbolTable);

        System.out.println(syntaxAnalyzer.toString());

        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(syntaxAnalyzer.getTree());

        VariableList variableList = semanticAnalyzer.getVariableList();

        for (Variable variable : variableList.getVariables()){
            System.out.println(variable);

        }


    }
}
