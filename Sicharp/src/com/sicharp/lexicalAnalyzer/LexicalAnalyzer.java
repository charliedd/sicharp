package com.sicharp.lexicalAnalyzer;

import com.sicharp.lexicalCategories.LexicalCategoriesResources;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LexicalAnalyzer {

    private SymbolTable symbolTable;

    public LexicalAnalyzer(String sourceFilePath){
        symbolTable = new SymbolTable();
        Stack<Character> charsInFile = getCharsInFile(sourceFilePath);
        fillSymbolTable(charsInFile);
    }

    public Stack<Character> getCharsInFile(String sourceFilePath){
        SourceFileReader sourceFileReader = new SourceFileReader(sourceFilePath);
        return sourceFileReader.getCharsInSourceFile();
    }

    public void fillSymbolTable(Stack<Character> charsInFile){
        List<Character> currentWord = new ArrayList<>();

        for(char currentChar : charsInFile){
            if()
        }

    }

    private boolean isASpecialSymbol(char currentChar){
        if(LexicalCategoriesResources.ARITHMETIC_OPERATORS)
    }

    private boolean arrayContains(char[] charArray, char currCharr){
        for(char chara : charArray){
            if(currCharr == chara)
                return true;
        }
        return false;
    }
}
