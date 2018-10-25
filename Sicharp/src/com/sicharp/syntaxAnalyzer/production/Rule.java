package com.sicharp.syntaxAnalyzer.production;

import com.sicharp.lexicalAnalyzer.Token;

import java.util.List;

public interface Rule {
    public boolean belongsToThisRule(List<Token> tokenList);
}
