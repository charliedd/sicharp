package com.sicharp.lexicalAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {

    private List<Token> tokenList;

    public SymbolTable(){
        tokenList = new ArrayList<>();
    }

    public void addToken(Token newToken){
        tokenList.add(newToken);
    }

    public List<Token> getTokenList(){
        return  tokenList;
    }

}
