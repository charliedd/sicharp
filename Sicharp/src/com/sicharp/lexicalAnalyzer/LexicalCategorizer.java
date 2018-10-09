package com.sicharp.lexicalAnalyzer;


import com.sicharp.lexicalCategories.*;
import com.sicharp.lexicalCategories.datatypes.Integero;
import com.sicharp.lexicalCategories.operators.ArithmeticOperator;
import com.sicharp.lexicalCategories.datatypes.BigInt;
import com.sicharp.lexicalCategories.operators.Asign;
import com.sicharp.lexicalCategories.operators.ComparissionOperator;
import com.sicharp.lexicalCategories.operators.LogicalOperator;
import com.sicharp.lexicalCategories.others.Identifier;
import com.sicharp.lexicalCategories.datatypes.LiteralString;
import com.sicharp.lexicalCategories.others.ReservedWord;
import com.sicharp.lexicalCategories.others.Unknown;
import com.sicharp.lexicalCategories.symbols.Agrupator;
import com.sicharp.lexicalCategories.symbols.Symbol;

public class LexicalCategorizer {
    ArithmeticOperator arithmeticOperator;
    LogicalOperator logicalOperator;
    ComparissionOperator comparissionOperator;
    Agrupator agrupator;
    Identifier identifier;
    ReservedWord reservedWord;
    LiteralString literalString;
    BigInt floatingPoint;
    Integero integero;
    Asign asign;
    Symbol symbol;

    public LexicalCategorizer() {
        arithmeticOperator = new ArithmeticOperator();
        logicalOperator = new LogicalOperator();
        comparissionOperator = new ComparissionOperator();
        agrupator = new Agrupator();
        identifier = new Identifier();
        reservedWord = new ReservedWord();
        literalString = new LiteralString();
        floatingPoint = new BigInt();
        integero = new Integero();
        floatingPoint = new BigInt();
        asign = new Asign();
        symbol = new Symbol();
    }

    public LexicalCategory getCategory(String currentInput){

            if(literalString.belongsToThisCategory(currentInput))
                return new LiteralString();

            if(asign.belongsToThisCategory(currentInput))
                return new Asign();

            if(reservedWord.belongsToThisCategory(currentInput))
                return new ReservedWord();

            if(integero.belongsToThisCategory(currentInput))
                return new Integero();

            if(floatingPoint.belongsToThisCategory(currentInput))
                return new BigInt();

            if(symbol.belongsToThisCategory(currentInput))
                return new Symbol();

            if(arithmeticOperator.belongsToThisCategory(currentInput))
                return new ArithmeticOperator();

            if(comparissionOperator.belongsToThisCategory(currentInput))
                return new ComparissionOperator();

            if(agrupator.belongsToThisCategory(currentInput))
                return new Agrupator();

            if(logicalOperator.belongsToThisCategory(currentInput))
                return new LogicalOperator();

            if(identifier.belongsToThisCategory(currentInput))
                return new Identifier();

        return new Unknown();
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


    public boolean isComparissionOperator(String currentString){
        return comparissionOperator.belongsToThisCategory(currentString);
    }


}
