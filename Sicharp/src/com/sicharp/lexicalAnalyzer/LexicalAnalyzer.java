package com.sicharp.lexicalAnalyzer;

import java.util.List;
import java.util.Stack;



public class LexicalAnalyzer {

    private LexicalCategorizer categorizer;
    private Automata automata;

    public LexicalAnalyzer(String sourceFilePath){
        categorizer = new LexicalCategorizer();
        automata = new Automata(categorizer);

        Stack<Character> charsInFile = getCharsInFile(sourceFilePath);
        fillSymbolTable(charsInFile);
    }

    public Stack<Character> getCharsInFile(String sourceFilePath){
        SourceFileReader sourceFileReader = new SourceFileReader(sourceFilePath);
        return sourceFileReader.getCharsInSourceFile();
    }

    public void fillSymbolTable(Stack<Character> charsInFile){

        for(char currentChar : charsInFile) {
            try{
                automata.processInput(currentChar);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        automata.addStackAsToken();



    }

    public SymbolTable getSymbolTable(){
        return this.automata.getSymbolTable();
    }

    public void printSymbolTable(){

        SymbolTable symbolTable = automata.getSymbolTable();

        for(Token tkn : symbolTable){
            System.out.println(tkn);
        }
    }
}
