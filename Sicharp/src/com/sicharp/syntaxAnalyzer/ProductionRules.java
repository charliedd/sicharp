package com.sicharp.syntaxAnalyzer;

import com.sicharp.lexicalAnalyzer.Token;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductionRules implements Comparable<List<Token>>{

    List<Token> declarations;
    List<Token> assignment;


    public ProductionRules() {
        declarations = new ArrayList<Token>();

    }

    public void setDeclarations(){

    }

    public void setAssignmentet(){

    }

    @Override
    public int compareTo(List<Token> o) {
        return 0;
    }
}
