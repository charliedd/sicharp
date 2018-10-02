package com.sicharp.lexicalCategories.operators;

import com.sicharp.lexicalCategories.LexicalCategoriesResources;
import com.sicharp.lexicalCategories.LexicalCategory;

public class ArithmeticOperator extends LexicalCategory {
    public static String ARITHMETIC_OPERATOR_CATEGORY;
    public static String[] ARITHMETIC_OPERATORS;

    public ArithmeticOperator() {
        ARITHMETIC_OPERATOR_CATEGORY = LexicalCategoriesResources.ARITHMETIC_OPERATOR_CATEGORY;
        ARITHMETIC_OPERATORS = LexicalCategoriesResources.ARITHMETIC_OPERATORS;
    }

    @Override
    public boolean belongsToThisCategory(String currentInput) {
        if(isInCategory(ARITHMETIC_OPERATORS,currentInput))return true;
        return false;
    }

    @Override
    public String toString() {
        return ARITHMETIC_OPERATOR_CATEGORY;
    }
}
