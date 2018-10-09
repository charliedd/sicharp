package com.sicharp.lexicalAnalyzer;

import com.sicharp.lexicalCategories.LexicalCategory;


public class Automata {
    private enum State{
        LITERAL,INTEGER, FLOAT, COMPARISION, DEFAULT;
    }

    State state;
    public String inputStack;
    LexicalCategorizer categorizer;
    private SymbolTable symbolTable;


    public Automata(LexicalCategorizer categorizer) {
        this.symbolTable = new SymbolTable();
        this.categorizer = categorizer;
        inputStack = "";
        state = State.DEFAULT;
    }


    public void processInput(char currentChar) throws Exception {
        System.out.println(inputStack);

        switch (state){
            case LITERAL:

                if(currentChar == '"'){
                    inputStack += currentChar;
                    addStackAsToken();
                    state = State.DEFAULT;
                }else{
                    inputStack += currentChar;
                }

                break;
            case INTEGER:

                if(isWhiteSpaceOrNewLine(currentChar)){
                    addStackAsToken();
                    state = State.DEFAULT;
                }
                else if(Character.isDigit(currentChar)){
                    inputStack += currentChar;
                }else if(currentChar == '.'){
                    inputStack += currentChar;
                    state = State.FLOAT;
                }else if(currentChar == ';'){
                    addStackAsToken();
                    state = State.DEFAULT;
                }else{
                    throw new Exception(inputStack + currentChar + " is not a valid input.");
                }

                break;
            case FLOAT:
                if(isWhiteSpaceOrNewLine(currentChar)){
                    addStackAsToken();
                    state = State.DEFAULT;
                }
                else if(Character.isDigit(currentChar)) {
                    inputStack += currentChar;
                }else{
                    throw new Exception(inputStack + currentChar + " is not a valid input.");
                }

                break;
            case COMPARISION:
                if(isWhiteSpaceOrNewLine(currentChar)){
                    addStackAsToken();
                    state = State.DEFAULT;
                }
                else if (categorizer.isComparissionOperator(inputStack + currentChar)){
                    inputStack += currentChar;
                    addStackAsToken();
                    inputStack = "";
                    state = State.DEFAULT;
                }else{
                    addStackAsToken();
                    state = State.DEFAULT;
                    inputStack = "";
                }

                break;
            case DEFAULT:
                if(isWhiteSpaceOrNewLine(currentChar)){
                    addStackAsToken();
                }
                else if(categorizer.isComparissionOperator(String.valueOf(currentChar))){
                    addStackAsToken();
                    inputStack+= currentChar;
                    state = State.COMPARISION;
                }
                else if(Character.isDigit(currentChar)){
                    addStackAsToken();
                    inputStack+= currentChar;
                    state = State.INTEGER;
                }
                else if(currentChar == '"'){
                    addStackAsToken();
                    inputStack+= currentChar;
                    state = State.LITERAL;
                }
                else if(categorizer.isASpecialSymbol(currentChar)){
                    addStackAsToken();
                    inputStack+= currentChar;
                    addStackAsToken();
                }
                else{
                    inputStack += currentChar;
                }

                break;
        }



    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public Token getToken(String input){
        LexicalCategory lexicalCategory = categorizer.getCategory(input);
        return new Token(lexicalCategory,input,input);
    }
    public boolean isWhiteSpaceOrNewLine(char currentChar){
        if(currentChar == ' ' || currentChar == '\n')return true;
        else return false;
    }
    public void addStackAsToken(){
        if(!inputStack.isEmpty()) {
            Token token = getToken(String.valueOf(inputStack));
            symbolTable.addToken(token);
            inputStack = "";
        }
    }
}
