package com.sicharp;

import com.sicharp.lexicalAnalyzer.LexicalAnalyzer;
import com.sicharp.lexicalAnalyzer.SourceFileReader;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer("/Users/carlosalbert.delgado/Documents/sicharp/Sicharp/ExampleCode/sampleCode.txt");

        System.out.println("dd");
       lexicalAnalyzer.printSymbolTable();


    }
}
