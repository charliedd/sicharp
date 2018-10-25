package com.sicharp.syntaxAnalyzer.production;

import com.sicharp.lexicalAnalyzer.Token;
import com.sicharp.lexicalCategories.LexicalCategory;
import com.sicharp.lexicalCategories.datatypes.Integero;
import com.sicharp.lexicalCategories.operators.Asign;
import com.sicharp.lexicalCategories.others.Identifier;
import com.sicharp.lexicalCategories.others.ReservedWord;
import com.sicharp.lexicalCategories.symbols.Agrupator;
import com.sicharp.lexicalCategories.symbols.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Start implements Rule {
    List<LexicalCategory> productionRules;
    int size;

    public Start() {
        productionRules = new ArrayList<LexicalCategory>();

        size = 5;

        LexicalCategory reserved = new ReservedWord();
        LexicalCategory agrupator = new Agrupator();

        productionRules.add(reserved); //enter
        productionRules.add(agrupator); // (
    }



    @Override
    public boolean belongsToThisRule(List<Token> tokenList) {

        int tokenListSize = tokenList.size();

        if(!tokenList.get(0).getLexicalCategory().toString().equals(productionRules.get(0).toString()))
            return false;

        if(!tokenList.get(1).getLexicalCategory().toString().equals(productionRules.get(1).toString()))
            return false;

        if(!tokenList.get(2).getLexicalCategory().toString().equals(productionRules.get(1).toString()))
            return false;

        if(!tokenList.get(3).getLexicalCategory().toString().equals(productionRules.get(1).toString()))
            return false;

        if(!tokenList.get(tokenListSize - 1).getLexicalCategory().toString().equals(productionRules.get(1).toString()))
            return false;

        return true;
    }
}
