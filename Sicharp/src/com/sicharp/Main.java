package com.sicharp;

import com.sicharp.lexicalAnalyzer.LexicalAnalyzer;
import com.sicharp.lexicalAnalyzer.SourceFileReader;
import com.sicharp.lexicalAnalyzer.SymbolTable;
import com.sicharp.lexicalAnalyzer.Token;
import com.sicharp.syntaxAnalyzer.SyntaxAnalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer("C:\\Users\\rbty2\\Documents\\sicharp\\Sicharp\\ExampleCode\\sampleCode.txt");
        System.out.println("SYMBOLTABLE");
        lexicalAnalyzer.printSymbolTable();
        SymbolTable symbolTable = lexicalAnalyzer.getSymbolTable();
        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(symbolTable);

        System.out.println("El arbolito");
        System.out.println(syntaxAnalyzer);

        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);

        System.out.println(lista.subList(1,3));

    }
}
