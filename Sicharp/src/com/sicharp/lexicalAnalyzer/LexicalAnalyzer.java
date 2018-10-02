package com.sicharp.lexicalAnalyzer;

import com.sicharp.lexicalCategories.LexicalCategory;

import java.util.Stack;


/*
    When the lexical analyzer read the source-code, it scans the code letter by letter; and when it encounters a whitespace,
    operator symbol, or special symbols, it decides that a word is completed.
 */

public class LexicalAnalyzer {

    private SymbolTable symbolTable;
    private LexicalCategorizer lexicalCategorizer;

    public LexicalAnalyzer(String sourceFilePath){
        symbolTable = new SymbolTable();
        lexicalCategorizer = new LexicalCategorizer();

        Stack<Character> charsInFile = getCharsInFile(sourceFilePath);
        fillSymbolTable(charsInFile);
    }

    public Stack<Character> getCharsInFile(String sourceFilePath){
        SourceFileReader sourceFileReader = new SourceFileReader(sourceFilePath);
        return sourceFileReader.getCharsInSourceFile();
    }

    public void fillSymbolTable(Stack<Character> charsInFile){
        String currentInput = "";

        for(char currentChar : charsInFile){
            if(lexicalCategorizer.isWhiteSpaceOrSymbol(currentChar)){
                if(currentInput.length() > 0){
                    LexicalCategory lexicalCategory = lexicalCategorizer.getCategory(currentInput);
                    Token newToken = new Token(lexicalCategory,currentInput,currentInput);
                    symbolTable.addToken(newToken);
                }
            }

        }



    }

}
