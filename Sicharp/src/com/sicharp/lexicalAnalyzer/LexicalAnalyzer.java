package com.sicharp.lexicalAnalyzer;

import com.sicharp.lexicalCategories.LexicalCategory;
import com.sicharp.lexicalCategories.LiteralString;

import java.util.Stack;


/*
    When the lexical analyzer read the source-code, it scans the code letter by letter; and when it encounters a whitespace,
    operator symbol, or special symbols, it decides that a word is completed.
*/

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


    public void printSymbolTable(){

        //SymbolTable symbolTable = automata.getSymbolTable();

        for(Token tkn : automata.getSymbolTable().getTokenList()){
            System.out.println(tkn);
        }
    }
}
