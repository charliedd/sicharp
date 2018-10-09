package com.sicharp.lexicalAnalyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SymbolTable implements Iterable<Token>{

    private List<Token> tokenList;

    public SymbolTable(){
        tokenList = new ArrayList<>();
    }

    public void addToken(Token newToken){
        tokenList.add(newToken);
    }

    @Override
    public Iterator<Token> iterator() {
        return tokenList.iterator();
    }
}
