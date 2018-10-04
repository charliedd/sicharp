package com.sicharp.lexicalAnalyzer;

import com.sicharp.lexicalCategories.LexicalCategory;
import com.sicharp.lexicalCategories.LiteralString;

import java.util.Stack;


/*
    When the lexical analyzer read the source-code, it scans the code letter by letter; and when it encounters a whitespace,
    operator symbol, or special symbols, it decides that a word is completed.
*/

public class LexicalAnalyzer {

    private SymbolTable symbolTable;
    private LexicalCategorizer categorizer;

    public LexicalAnalyzer(String sourceFilePath){
        symbolTable = new SymbolTable();
        categorizer = new LexicalCategorizer();

        Stack<Character> charsInFile = getCharsInFile(sourceFilePath);
        fillSymbolTable(charsInFile);
    }

    public Stack<Character> getCharsInFile(String sourceFilePath){
        SourceFileReader sourceFileReader = new SourceFileReader(sourceFilePath);
        return sourceFileReader.getCharsInSourceFile();
    }

    public void fillSymbolTable(Stack<Character> charsInFile){
        String currentInput = "";
        boolean insideLiteral = false;
        boolean maybeComparission = false;


        for(char currentChar : charsInFile) {

            if(categorizer.isWhiteSpaceOrSymbol(currentChar)){
                if(!maybeComparission && categorizer.couldBeComparissionOperator(currentChar)){
                    maybeComparission = true;
                    currentInput += currentChar;
                }

                if(maybeComparission){
                    if(categorizer.isComparissionOperator(currentInput + currentChar)){
                        currentInput += currentChar;
                        addToken(currentInput);
                    }
                }

                if(categorizer.isLiteralSymbol(currentChar)){
                    if(insideLiteral){
                        addTokenWithCategory(currentInput,new LiteralString());
                        currentInput = "";
                        insideLiteral = false;
                    }else{
                        insideLiteral = true;
                    }

                }

                if(categorizer.isASpecialSymbol(currentChar)){
                    addToken(currentInput);
                    addToken(String.valueOf(currentChar));
                    currentInput = "";
                }

                if(currentChar == ' ' ){
                    addToken(currentInput);
                    currentInput = "";
                }



            }else{
                if(!categorizer.isLetterOrNumber(currentChar)){
                    System.out.println("Invalid token");
                    return;
                }
                currentInput += currentChar;
            }

            System.out.println(currentInput);
        }
    }

    public void addToken(String input){
        LexicalCategory lexicalCategory = categorizer.getCategory(input);
        Token token = new Token(lexicalCategory,input,input);
        symbolTable.addToken(token);
    }

    public void addTokenWithCategory(String input, LexicalCategory lexicalCategory){
        Token token = new Token(lexicalCategory,input,input);
        symbolTable.addToken(token);
    }

    public void printSymbolTable(){

        for(Token tkn : symbolTable.getTokenList()){
            System.out.println(tkn);
        }
    }
}
