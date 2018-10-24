package com.sicharp.syntaxAnalyzer.production;

import com.sicharp.lexicalAnalyzer.Token;
import com.sicharp.lexicalCategories.LexicalCategory;
import com.sicharp.lexicalCategories.datatypes.Integero;
import com.sicharp.lexicalCategories.operators.Asign;
import com.sicharp.lexicalCategories.others.Identifier;
import com.sicharp.lexicalCategories.others.ReservedWord;
import com.sicharp.lexicalCategories.symbols.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Assignment{

    List<LexicalCategory> productionRules;

    public Assignment() {
        productionRules = new ArrayList<LexicalCategory>();

        LexicalCategory reserved = new ReservedWord();
        LexicalCategory identifier = new Identifier();
        LexicalCategory assignation = new Asign();
        LexicalCategory literal = new Integero();
        LexicalCategory symbol = new Symbol();

        productionRules.add(reserved);
        productionRules.add(identifier);
        productionRules.add(assignation);
        productionRules.add(literal);
        productionRules.add(symbol);
    }

    public boolean belongsToThisRule(List<Token> tokenList){
        int index = 0;
        int size = productionRules.size();

        System.out.println(productionRules);
        System.out.println(tokenList);

        if(tokenList.size() > productionRules.size()){
            System.out.println("NOPE");
            return false;
        }

        for(Token token : tokenList){
            if(index >= size)return false;
            if(!token.getLexicalCategory().toString().equals(productionRules.get(index++).toString())) {
                System.out.println("NOPE token: |" + token.getLexicalCategory().toString() + "| prod: |" + productionRules.get(index).toString() + "|");
                return false;
            }

        }
        return true;
    }

}
