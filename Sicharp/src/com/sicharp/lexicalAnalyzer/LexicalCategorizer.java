package com.sicharp.lexicalAnalyzer;


import com.sicharp.lexicalCategories.Agrupator;
import com.sicharp.lexicalCategories.Identifier;
import com.sicharp.lexicalCategories.LexicalCategory;
import com.sicharp.lexicalCategories.ReservedWord;
import com.sicharp.lexicalCategories.operators.ArithmeticOperator;
import com.sicharp.lexicalCategories.operators.ComparissionOperator;
import com.sicharp.lexicalCategories.operators.LogicalOperator;

public class LexicalCategorizer {
    ArithmeticOperator arithmeticOperator;
    LogicalOperator logicalOperator;
    ComparissionOperator comparissionOperator;
    Agrupator agrupator;
    Identifier identifier;
    ReservedWord reservedWord;


    public LexicalCategorizer() {
        arithmeticOperator = new ArithmeticOperator();
        logicalOperator = new LogicalOperator();
        comparissionOperator = new ComparissionOperator();
        agrupator = new Agrupator();
        identifier = new Identifier();
        reservedWord = new ReservedWord();
    }

    public LexicalCategory getCategory(String currentInput){
        if(isNotASymbol(currentInput)){
            if(reservedWord.belongsToThisCategory(currentInput))
                return new ReservedWord();
        }else{
            if(arithmeticOperator.belongsToThisCategory(currentInput))
                return new ArithmeticOperator();
            if(comparissionOperator.belongsToThisCategory(currentInput))
                return new ComparissionOperator();
            if(agrupator.belongsToThisCategory(currentInput))
                return new Agrupator();
            if(logicalOperator.belongsToThisCategory(currentInput))
                return new LogicalOperator();
        }
        return new Identifier();
    }

    public boolean isNotASymbol(String currentInput){
        return currentInput.length() > 2;
    }

    public boolean isWhiteSpaceOrSymbol(char currentChar){
        if(currentChar == ' ')return true;

        String tempChar = ""+currentChar;
        if(arithmeticOperator.belongsToThisCategory(tempChar))return true;
        if(logicalOperator.belongsToThisCategory(tempChar)) return true;
        if(comparissionOperator.belongsToThisCategory(tempChar))return true;
        if(agrupator.belongsToThisCategory(tempChar))return true;

        return false;
    }

}
