package com.sicharp.lexicalCategories.operators;

import com.sicharp.lexicalCategories.LexicalCategoriesResources;
import com.sicharp.lexicalCategories.LexicalCategory;

public class LogicalOperator extends LexicalCategory {
    public static String LOGICAL_OPERATOR_CATEGORY;
    public static String[] LOGICAL_OPERATORS;


    public LogicalOperator() {
        LOGICAL_OPERATOR_CATEGORY = LexicalCategoriesResources.LOGICAL_OPERATOR_CATEGORY;
        LOGICAL_OPERATORS = LexicalCategoriesResources.LOGICAL_OPERATORS;
    }

    @Override
    public boolean belongsToThisCategory(String currentInput) {
        if(isInCategory(LOGICAL_OPERATORS,currentInput))return true;
        return false;
    }

    @Override
    public String toString() {
        return this.LOGICAL_OPERATOR_CATEGORY;
    }
}
