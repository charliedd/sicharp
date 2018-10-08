package com.sicharp.lexicalAnalyzer;


import com.sicharp.lexicalCategories.*;
import com.sicharp.lexicalCategories.operators.ArithmeticOperator;
import com.sicharp.lexicalCategories.operators.BigInt;
import com.sicharp.lexicalCategories.operators.ComparissionOperator;
import com.sicharp.lexicalCategories.operators.LogicalOperator;

public class LexicalCategorizer {
    ArithmeticOperator arithmeticOperator;
    LogicalOperator logicalOperator;
    ComparissionOperator comparissionOperator;
    Agrupator agrupator;
    Identifier identifier;
    ReservedWord reservedWord;
    LiteralString literalString;
    BigInt floatingPoint;

    public LexicalCategorizer() {
        arithmeticOperator = new ArithmeticOperator();
        logicalOperator = new LogicalOperator();
        comparissionOperator = new ComparissionOperator();
        agrupator = new Agrupator();
        identifier = new Identifier();
        reservedWord = new ReservedWord();
        literalString = new LiteralString();
        floatingPoint = new BigInt();
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
        if(currentChar == '\n')return true;
        if(isASpecialSymbol(currentChar))return true;
        return false;
    }

    public boolean isASpecialSymbol(char currentChar){
        String tempString = String.valueOf(currentChar);
        if(arithmeticOperator.belongsToThisCategory(tempString))return true;
        if(logicalOperator.belongsToThisCategory(tempString)) return true;
        if(comparissionOperator.belongsToThisCategory(tempString))return true;
        if(agrupator.belongsToThisCategory(tempString))return true;
        if(literalString.belongsToThisCategory(tempString))return true;
        return false;
    }

    public boolean couldBeComparissionOperator(char currentChar){
        return (comparissionOperator.belongsToThisCategory(String.valueOf(currentChar)));
    }

    public boolean isComparissionOperator(String currentString){
        return comparissionOperator.belongsToThisCategory(currentString);
    }

    public boolean isLetterOrNumber(char currentChar){
        if(Character.isDigit(currentChar))return true;
        if(Character.isLetter(currentChar))return true;
        return false;
    }

    public boolean isLiteralSymbol(char currentChar){
        String tempStr = String.valueOf(currentChar);
        return literalString.belongsToThisCategory(tempStr);
    }



}
