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

        int index = 0;
        int end = charsInFile.size() - 1;

        for(char currentChar : charsInFile) {

            if(!insideLiteral){

                if(maybeComparission){
                    if(categorizer.isComparissionOperator(currentInput + currentChar)){
                        currentInput += currentChar;
                        addToken(currentInput);
                        currentInput= "";
                        maybeComparission = false;
                    }else if(categorizer.isComparissionOperator(currentInput)){
                        if(!currentInput.isEmpty()){
                            addToken(currentInput);
                            currentInput = "";
                            maybeComparission = false;
                        }
                    }
                }else{
                    if(categorizer.isWhiteSpaceOrSymbol(currentChar)){
                        if(categorizer.isComparissionOperator(String.valueOf(currentChar))){
                            currentInput += currentChar;
                            maybeComparission = true;
                        }else if(categorizer.isLiteralSymbol(currentChar)){
                            if(!currentInput.isEmpty()){
                                addToken(currentInput);
                                currentInput = "";
                            }
                            insideLiteral = true;
                        }else if(categorizer.isASpecialSymbol(currentChar)){
                            if(!currentInput.isEmpty()){
                                addToken(currentInput);
                                currentInput = "";
                            }
                            addToken(String.valueOf(currentChar));
                        }else if (currentChar == ' '){
                            if(!currentInput.isEmpty()){
                                addToken(currentInput);
                                currentInput = "";
                            }
                        }else if (index == end){
                            if(!currentInput.isEmpty()){
                                addToken(currentInput);
                                currentInput = "";
                            }
                        }
                    }
                    else{
                        if(categorizer.isLetterOrNumber(currentChar)){
                            currentInput += currentChar;
                        }else{
                            System.out.println("invalid character");
                        }
                    }
                }
            }
            else{
                if(c)
                currentInput += currentChar;

            }
            index++;
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
