package com.sicharp.syntaxAnalyzer.ProductionRules;

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

    public boolean equals(List<Token> tokenList){
        int index = 0;
        int size = productionRules.size();

        for(Token token : tokenList){

            if(index >= size)return false;
            if(token.toString().equals(productionRules.)){

            }else
                return false;
        }

        return true;
    }

}
