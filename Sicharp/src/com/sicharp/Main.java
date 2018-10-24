package com.sicharp;

import com.sicharp.lexicalAnalyzer.LexicalAnalyzer;
import com.sicharp.lexicalAnalyzer.SourceFileReader;
import com.sicharp.lexicalAnalyzer.SymbolTable;
import com.sicharp.lexicalAnalyzer.Token;
import com.sicharp.syntaxAnalyzer.SyntaxAnalyzer;

import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer("ExampleCode/sampleCode.txt");
    //    LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer("C:\\Users\\rbty2\\Documents\\sicharp\\Sicharp\\ExampleCode\\sampleCode.txt");
//        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer("C:\\Users\\roris\\Documents\\cetys\\7mo Semestre\\compiladores\\project\\sicharp\\Sicharp\\ExampleCode\\sampleCode.txt");
        System.out.println("SYMBOLTABLE");
       lexicalAnalyzer.printSymbolTable();
        SymbolTable symbolTable = lexicalAnalyzer.getSymbolTable();
       // SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(symbolTable);
        //System.out.println(syntaxAnalyzer);

    }
}
