package com.sicharp.lexicalCategories.operators;

import com.sicharp.lexicalCategories.LexicalCategoriesResources;
import com.sicharp.lexicalCategories.LexicalCategory;

public class ComparissionOperator extends LexicalCategory {
    public  String COMPARITION_OPERATORS_CATEGORY;
    public  String[] COMPARITION_OPERATORS;

    public ComparissionOperator() {
        COMPARITION_OPERATORS_CATEGORY = LexicalCategoriesResources.COMPARITION_OPERATORS_CATEGORY;
        COMPARITION_OPERATORS = LexicalCategoriesResources.COMPARITION_OPERATORS;
    }

    @Override
    public boolean belongsToThisCategory(String currentInput) {
        if(isInCategory(COMPARITION_OPERATORS,currentInput)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.COMPARITION_OPERATORS_CATEGORY;
    }
}
